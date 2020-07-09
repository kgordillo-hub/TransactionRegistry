package co.agro.blockchain.TransactionRegistry.serviceImpl;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigchaindb.api.TransactionsApi;
import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.FulFill;
import com.bigchaindb.model.GenericCallback;
import com.bigchaindb.model.Transaction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import co.agro.blockchain.TransactionRegistry.model.AssetDto;
import co.agro.blockchain.TransactionRegistry.model.MetaDataDto;
import co.agro.blockchain.TransactionRegistry.model.ProductDto;
import co.agro.blockchain.TransactionRegistry.model.db.EtiquetaRfid;
import co.agro.blockchain.TransactionRegistry.model.db.RegTransactRfid;
import co.agro.blockchain.TransactionRegistry.model.db.RegTransactRfidPk;
import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccion;
import co.agro.blockchain.TransactionRegistry.model.wrapper.AssetWrapper;
import co.agro.blockchain.TransactionRegistry.model.wrapper.MetadataWrapper;
import co.agro.blockchain.TransactionRegistry.service.BigchainDbService;
import co.agro.blockchain.TransactionRegistry.service.RegRfidSqlService;
import co.agro.blockchain.TransactionRegistry.service.RegTransaccionSqlService;
import co.agro.blockchain.TransactionRegistry.utils.UtilsTransacReg;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import okhttp3.Response;

@Service
public class BigchainDbServiceImpl implements BigchainDbService {

	@Autowired
	RegTransaccionSqlService regTransaccionSql;

	@Autowired
	RegRfidSqlService regRfidService;

	@Override
	public String createTransaction(AssetWrapper asset) {
		try {
			conectar();
			final KeyPair kp = UtilsTransacReg.keyPairDecodification(asset.getPrivateKey());
			System.out.println("Creating transaction in the Blockchain...");
			final Transaction transaction = BigchainDbTransactionBuilder.init()
					.addAssets(asset.getAsset().getProductData(), ProductDto.class).operation(Operations.CREATE)
					.addMetaData(asset.getAsset().getMetaData())
					.buildAndSign((EdDSAPublicKey) kp.getPublic(), (EdDSAPrivateKey) kp.getPrivate())
					.sendTransaction(handleServerResponse());
			final JsonObject jsonObject = new Gson().fromJson(transaction.toString(), JsonObject.class);
			final String assetId = jsonObject.get("asset").getAsJsonObject().get("id") != null
					? jsonObject.get("asset").getAsJsonObject().get("id").getAsString()
					: null;
			final RegistroTransaccion regTransact = UtilsTransacReg.crearRegTransaccion(asset, transaction.getId(),
					assetId == null ? transaction.getId() : assetId);
			final EtiquetaRfid etiquetaRfid = UtilsTransacReg.crearRfidTag(asset);
			regTransaccionSql.insertarTransaccion(regTransact);
			regRfidService.insertRfidTag(etiquetaRfid);
			regRfidService.insertRfidTransaction(new RegTransactRfid(new RegTransactRfidPk(regTransact, etiquetaRfid)));

			System.out.println("If transaction successfully completed, transaction ID will appear below...");
			System.out.println(transaction.getId());
			System.out.println(transaction.toString());
			return transaction.getId();
		} catch (final Exception e) {
			System.out.println("Error creating transaction." + e);
			return null;
		}

	}

	@Override
	public String updateTransaction(MetadataWrapper metaDataWrap) {
		try {
			conectar();
			final FulFill fulFill = new FulFill();
			fulFill.setTransactionId(metaDataWrap.getTransactionId());
			fulFill.setOutputIndex(0);
			final KeyPair kp = UtilsTransacReg.keyPairDecodification(metaDataWrap.getPrivateKey());
			Transaction transferTransaction = BigchainDbTransactionBuilder.init()
					.addMetaData(metaDataWrap.getMetaData()).addInput(null, fulFill, (EdDSAPublicKey) kp.getPublic())
					.addOutput("1", UtilsTransacReg.getPublicKeyFromString(metaDataWrap.getDestinationPublicKey()))
					.addAssets(metaDataWrap.getGenesisAssetId(), String.class).operation(Operations.TRANSFER)
					.buildAndSign((EdDSAPublicKey) kp.getPublic(), (EdDSAPrivateKey) kp.getPrivate())
					.sendTransaction(handleServerResponse());
			final JsonObject jsonObject = new Gson().fromJson(transferTransaction.toString(), JsonObject.class);
			final String assetId = jsonObject.get("asset").getAsJsonObject().get("id") != null
					? jsonObject.get("asset").getAsJsonObject().get("id").getAsString()
					: null;
			final RegistroTransaccion regTransact = UtilsTransacReg.crearRegTransaccion(metaDataWrap,
					transferTransaction.getId(), assetId == null ? transferTransaction.getId() : assetId);
			final EtiquetaRfid etiquetaRfid = UtilsTransacReg.crearRfidTag(metaDataWrap.getRfidTag());
			regTransaccionSql.insertarTransaccion(regTransact);
			regRfidService.insertRfidTransaction(new RegTransactRfid(new RegTransactRfidPk(regTransact, etiquetaRfid)));

			System.out.println(transferTransaction.getId());
			return transferTransaction.getId();
		} catch (final Exception e) {
			System.out.println("Error updating transaction.");
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public AssetDto queryTransaction(String transactionId) {
		try {
			conectar();
			Gson gson = new GsonBuilder().serializeNulls().create();
			final Transaction transaction = TransactionsApi.getTransactionById(transactionId);
			final JsonObject jsonObject = new Gson().fromJson(transaction.toString(), JsonObject.class);
			final MetaDataDto metaDataDto = gson.fromJson(jsonObject.get("metadata"), MetaDataDto.class);
			final String assetId = jsonObject.get("asset").getAsJsonObject().get("id") != null
					? jsonObject.get("asset").getAsJsonObject().get("id").getAsString()
					: null;
			final ProductDto productDto = gson.fromJson(jsonObject.get("asset").getAsJsonObject().get("data"),
					ProductDto.class);
			return new AssetDto(metaDataDto, productDto, assetId,transaction.getId());
		} catch (final Exception e) {
			System.out.println("Error when try to query transaction ");
			e.printStackTrace();
			return null;
		}

	}

	public RegistroTransaccion getLastTransactionByRfidId(final String rfidId) {
		final List<RegistroTransaccion> listaTransacciones = regTransaccionSql.listarTransaccionesByRfid(rfidId);
		if (listaTransacciones != null && !listaTransacciones.isEmpty()) {
			RegistroTransaccion temporalTransact = null;
			for (final RegistroTransaccion regTransactRfid : listaTransacciones) {
				final Date tmpDate = regTransactRfid.getFechaTransaccion();
				if (temporalTransact == null) {
					temporalTransact = regTransactRfid;
				} else {
					temporalTransact = temporalTransact.getFechaTransaccion().before(tmpDate) ? regTransactRfid
							: temporalTransact;
				}
			}
			return temporalTransact;
		}
		return null;
	}

	
	@Override
	public List<RegistroTransaccion> getTransactionsByRfid(String rfidId) {
		return regTransaccionSql.listarTransaccionesByRfid(rfidId);
	}
	private final void conectar() throws IOException {
		final String bigChainDbEp = UtilsTransacReg.getProperty("bigchainDbEndPoint");
		BigchainDbConfigBuilder.baseUrl(bigChainDbEp).setup();
	}

	private GenericCallback handleServerResponse() {
		// define callback methods to verify response from BigchainDBServer
		GenericCallback callback = new GenericCallback() {
			public void transactionMalformed(Response response) {
				System.out.println("malformed " + response.message());
				System.out.println(response.toString());
				onFailure();
			}

			public void pushedSuccessfully(Response response) {
				System.out.println("pushedSuccessfully");
				onSuccess(response);
			}

			public void otherError(Response response) {
				System.out.println("otherError" + response.message());
				System.out.println(response.toString());
				onFailure();
			}
		};

		return callback;
	}

	private static void onFailure() {
		System.out.println("Transaction failed");
	}

	private static void onSuccess(Response response) {
		System.out.println("Transaction posted successfully");
	}

}
