package co.agro.blockchain.TransactionRegistry.controllers;

import co.agro.blockchain.TransactionRegistry.model.KeyPairDto;

public interface KeyGeneratorController {

	KeyPairDto generateKeyPair();
	
}
