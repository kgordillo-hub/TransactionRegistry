package co.agro.blockchain.TransactionRegistry.service;

import java.util.List;

import co.agro.blockchain.TransactionRegistry.model.AssetDto;
import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccion;
import co.agro.blockchain.TransactionRegistry.model.wrapper.AssetWrapper;
import co.agro.blockchain.TransactionRegistry.model.wrapper.MetadataWrapper;

public interface BigchainDbService {

	String createTransaction(AssetWrapper assetWrap);
	
	String updateTransaction(MetadataWrapper metaDataWrap);
	
	AssetDto queryTransaction(String transaction);
	
	RegistroTransaccion getLastTransactionByRfidId(final String rfidId);
	
	List<RegistroTransaccion> getTransactionsByRfid(final String rfidId);
}