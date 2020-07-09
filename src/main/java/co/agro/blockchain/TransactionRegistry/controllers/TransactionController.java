package co.agro.blockchain.TransactionRegistry.controllers;

import org.springframework.http.ResponseEntity;

import co.agro.blockchain.TransactionRegistry.model.wrapper.AssetWrapper;
import co.agro.blockchain.TransactionRegistry.model.wrapper.MetadataWrapper;

public interface TransactionController {

	
	ResponseEntity<?> registerProduct(final AssetWrapper assetInfo);
	
	ResponseEntity<?> updateProductStatus(final MetadataWrapper metadataInfo);
	
	ResponseEntity<?> queryTransaction(final String transactionId);
	
	ResponseEntity<?> getLastTransactionFromRfid(final String rfidTag);
	
	ResponseEntity<?> getTransactionsFromRfid(final String rfidTag);
}
