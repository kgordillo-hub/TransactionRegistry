package co.agro.blockchain.TransactionRegistry.serviceImpl.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import co.agro.blockchain.TransactionRegistry.model.db.EtiquetaRfid;
import co.agro.blockchain.TransactionRegistry.model.db.RegTransactRfid;
import co.agro.blockchain.TransactionRegistry.model.db.RegTransactRfidPk;
import co.agro.blockchain.TransactionRegistry.repository.IEtiquetaRfidRepo;
import co.agro.blockchain.TransactionRegistry.repository.IRegTransacRfidRepo;
import co.agro.blockchain.TransactionRegistry.service.RegRfidSqlService;

@Service
public class RegRfidSqlServiceImpl implements RegRfidSqlService {

	@Autowired
	IRegTransacRfidRepo regTransactRepo;

	@Autowired
	IEtiquetaRfidRepo rfidRepo;

	@Override
	public List<RegTransactRfid> listAllTransactionsWithRfid() {
		return regTransactRepo.findAll();
	}

	@Override
	public List<RegTransactRfid> listTransactByRfid(String rfidTag) {
		final RegTransactRfidPk regRfidPk = new RegTransactRfidPk();
		regRfidPk.setEtiquetaRfid(new EtiquetaRfid(rfidTag));
		final RegTransactRfid regTransactRfid = new RegTransactRfid(regRfidPk);
		Example<RegTransactRfid> example = Example.of(regTransactRfid);
		return regTransactRepo.findAll(example);
	}

	@Override
	public Boolean insertRfidTag(EtiquetaRfid etiquetaRfid) {
		if (etiquetaRfid != null && etiquetaRfid.getIdRfid() != null && !etiquetaRfid.getIdRfid().isEmpty()
				&& !rfidRepo.findById(etiquetaRfid.getIdRfid()).isPresent()) {
			rfidRepo.save(etiquetaRfid);
			return true;
		}
		return false;
	}

	@Override
	public Boolean insertRfidTransaction(RegTransactRfid regTransaction) {
		if (regTransaction != null && regTransaction.getRegTranscPk() != null) {
			System.out.println(
					"bloque: " + regTransaction.getRegTranscPk().getRegTransc().getRegistroPk().getIdBloqueBc());
			System.out.println(
					"transact: " + regTransaction.getRegTranscPk().getRegTransc().getRegistroPk().getIdTransaccion());
			regTransactRepo.save(regTransaction);
			return true;
		}
		return false;
	}

}
