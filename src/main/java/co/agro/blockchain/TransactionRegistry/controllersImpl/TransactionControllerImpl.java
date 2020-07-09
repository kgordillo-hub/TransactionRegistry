package co.agro.blockchain.TransactionRegistry.controllersImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import co.agro.blockchain.TransactionRegistry.controllers.TransactionController;
import co.agro.blockchain.TransactionRegistry.model.AssetDto;
import co.agro.blockchain.TransactionRegistry.model.ProductDto;
import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccion;
import co.agro.blockchain.TransactionRegistry.model.wrapper.AssetWrapper;
import co.agro.blockchain.TransactionRegistry.model.wrapper.MetadataWrapper;
import co.agro.blockchain.TransactionRegistry.service.BigchainDbService;

@RestController
public class TransactionControllerImpl implements TransactionController {

	@Autowired
	BigchainDbService bigChainDbService;

	@Override
	@PostMapping(value = "/transaction/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerProduct(@RequestBody final AssetWrapper assetInfo) {
		final String transactionId = bigChainDbService.createTransaction(assetInfo);
		if (transactionId != null && !transactionId.isEmpty()) {
			return ResponseEntity.ok(transactionId);
		}
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error al crear transacción");
	}

	@Override
	@PostMapping(value = "/transaction/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateProductStatus(@RequestBody final MetadataWrapper metadataInfo) {
		final String transactionId = bigChainDbService.updateTransaction(metadataInfo);
		if (transactionId != null && !transactionId.isEmpty()) {
			return ResponseEntity.ok(transactionId);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El id de la transacción no existe.");
	}

	@Override
	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> queryTransaction(@PathVariable String id) {
		final AssetDto assetProduct = bigChainDbService.queryTransaction(id);
		if (assetProduct != null) {
			return ResponseEntity.ok(new Gson().toJson(assetProduct));
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.body("No se ha encontrado ninguna transacción con el id: '" + id + "'");
	}

	@Override
	@RequestMapping(value = "/transaction/rfidTransact/{rfidTag}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getLastTransactionFromRfid(@PathVariable String rfidTag) {
		final RegistroTransaccion regTransact = bigChainDbService.getLastTransactionByRfidId(rfidTag);
		if (regTransact != null) {
			final AssetDto assetProduct = bigChainDbService
					.queryTransaction(regTransact.getRegistroPk().getIdBloqueBc());
			if (assetProduct != null) {
				final ProductDto product = bigChainDbService.queryTransaction(regTransact.getIdAssetGenesis())
						.getProductData();
				assetProduct.setProductData(product);
				final AssetWrapper assetWrap = new AssetWrapper();
				assetWrap.setAsset(assetProduct);
				assetWrap.setGenesisAssetId(regTransact.getIdAssetGenesis());
				return ResponseEntity.ok(new Gson().toJson(assetWrap));
			}
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.body("No se ha encontrado ninguna transacción con el RFID id: '" + rfidTag + "'");
	}

	@Override
	@RequestMapping(value = "/transaction/listRfidTransact/{rfidTag}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTransactionsFromRfid(@PathVariable String rfidTag) {
		final List<RegistroTransaccion> listTransactions = bigChainDbService.getTransactionsByRfid(rfidTag);
		if (listTransactions != null && !listTransactions.isEmpty()) {
			final ProductDto product = bigChainDbService.queryTransaction(listTransactions.get(0).getIdAssetGenesis())
					.getProductData();
			final List<AssetDto> assetList = new ArrayList<AssetDto>();
			for (final RegistroTransaccion registroTransaccion : listTransactions) {
				final AssetDto assetProduct = bigChainDbService
						.queryTransaction(registroTransaccion.getRegistroPk().getIdBloqueBc());
				if (assetProduct != null) {
					assetProduct.setProductData(product);
					assetProduct.setFechaHora(registroTransaccion.getFechaTransaccion() != null
							? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(registroTransaccion.getFechaTransaccion())
							: null);
					assetList.add(assetProduct);
				}
			}
			return ResponseEntity.ok(new Gson().toJson(assetList));
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.body("No se ha encontrado ninguna transacción con el RFID id: '" + rfidTag + "'");
	}

}
