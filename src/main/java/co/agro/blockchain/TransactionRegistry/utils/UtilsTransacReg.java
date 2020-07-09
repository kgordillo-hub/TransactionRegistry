package co.agro.blockchain.TransactionRegistry.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;

import com.bigchaindb.util.KeyPairUtils;

import co.agro.blockchain.TransactionRegistry.model.db.EtiquetaRfid;
import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccion;
import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccionPk;
import co.agro.blockchain.TransactionRegistry.model.wrapper.AssetWrapper;
import co.agro.blockchain.TransactionRegistry.model.wrapper.MetadataWrapper;
import co.agro.blockchain.TransactionRegistry.serviceImpl.BigchainDbServiceImpl;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.Utils;

public final class UtilsTransacReg {

	
	public static final String getProperty(final String propertyName) throws IOException {
		try (final InputStream input = BigchainDbServiceImpl.class.getClassLoader()
				.getResourceAsStream("config.properties")) {

			final Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return null;
			}
			// load a properties file from class path, inside static method
			prop.load(input);
			return prop.getProperty(propertyName);
		} catch (final IOException ex) {
			throw ex;
		}
	}
	
	public static final KeyPair keyPairDecodification(final String privateKey) {
		return KeyPairUtils.decodeKeyPair(privateKey);
	}
	
	public static final EdDSAPublicKey getPublicKeyFromString(final String publicKeyStr) throws InvalidKeySpecException {
		byte[] decoded = Base64.getDecoder().decode(publicKeyStr);
		String hexString = Hex.encodeHexString(decoded);
		X509EncodedKeySpec encoded = new X509EncodedKeySpec(Utils.hexToBytes(hexString));
		EdDSAPublicKey keyIn = new EdDSAPublicKey(encoded);
		return keyIn;
	}

	public static final RegistroTransaccion crearRegTransaccion(final AssetWrapper asset, final String transactionId, final String assetId) {
		final RegistroTransaccion regTransacction = new RegistroTransaccion();
		final String idTx = UUID.randomUUID().toString().replace("-", "").substring(0,25);
		
		regTransacction.setRegistroPk(new RegistroTransaccionPk(transactionId, idTx));
		regTransacction.setFechaTransaccion(new Date());
		regTransacction.setIdAssetGenesis(assetId);
		regTransacction.setIdEstadoProd(asset.getAsset().getMetaData().getStatusId());
		regTransacction.setIdUsuario(asset.getUserId());
		return regTransacction;
	}
	
	public static final RegistroTransaccion crearRegTransaccion(final MetadataWrapper metaData, final String transactionId, final String assetId) {
		final RegistroTransaccion regTransacction = new RegistroTransaccion();
		final String idTx = UUID.randomUUID().toString().replace("-", "").substring(0,25);
		
		regTransacction.setRegistroPk(new RegistroTransaccionPk(transactionId, idTx));
		regTransacction.setFechaTransaccion(new Date());
		regTransacction.setIdAssetGenesis(assetId);
		regTransacction.setIdEstadoProd(metaData.getMetaData().getStatusId());
		regTransacction.setIdUsuario(metaData.getUserId());
		return regTransacction;
	}
	
	public static final EtiquetaRfid crearRfidTag(final AssetWrapper asset) {
		final String rfidTag = asset.getAsset().getProductData().getIdRfidTag();
		final String description = asset.getAsset().getProductData().getProductName();
		return new EtiquetaRfid(rfidTag, description);
	}
	
	public static final EtiquetaRfid crearRfidTag(final String rfidId) {
		return new EtiquetaRfid(rfidId);
	}
	
}
