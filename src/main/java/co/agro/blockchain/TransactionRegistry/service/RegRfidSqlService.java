package co.agro.blockchain.TransactionRegistry.service;

import java.util.List;

import co.agro.blockchain.TransactionRegistry.model.db.EtiquetaRfid;
import co.agro.blockchain.TransactionRegistry.model.db.RegTransactRfid;

public interface RegRfidSqlService {

	List<RegTransactRfid> listAllTransactionsWithRfid();

	List<RegTransactRfid> listTransactByRfid(final String rfidTag);

	Boolean insertRfidTag(final EtiquetaRfid etiquetaRfid);

	Boolean insertRfidTransaction(final RegTransactRfid regTransaction);
	
}
