package co.agro.blockchain.TransactionRegistry.serviceImpl;

import java.security.KeyPair;
import java.util.Base64;

import org.springframework.stereotype.Service;

import com.bigchaindb.util.KeyPairUtils;

import co.agro.blockchain.TransactionRegistry.model.KeyPairDto;
import co.agro.blockchain.TransactionRegistry.service.BigchainDbUtilService;
import net.i2p.crypto.eddsa.KeyPairGenerator;

@Service
public class BigchainDbUtilServiceImpl implements BigchainDbUtilService{

	@Override
	public KeyPairDto generateKeyPair() {
		final KeyPairGenerator kpgenerator = new KeyPairGenerator();
		final KeyPair kp = kpgenerator.generateKeyPair();
		final String privateKey = KeyPairUtils.encodePrivateKeyBase64(kp);
		final String publicKey = Base64.getEncoder().encodeToString(kp.getPublic().getEncoded());
		return new KeyPairDto(privateKey, publicKey);
	}

}
