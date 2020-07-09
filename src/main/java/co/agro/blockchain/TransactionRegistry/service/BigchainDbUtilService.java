package co.agro.blockchain.TransactionRegistry.service;

import co.agro.blockchain.TransactionRegistry.model.KeyPairDto;

public interface BigchainDbUtilService {

	KeyPairDto generateKeyPair();
	
}
