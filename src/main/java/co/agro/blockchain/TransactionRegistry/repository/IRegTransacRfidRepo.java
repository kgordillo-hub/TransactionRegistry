package co.agro.blockchain.TransactionRegistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.agro.blockchain.TransactionRegistry.model.db.RegTransactRfid;
import co.agro.blockchain.TransactionRegistry.model.db.RegTransactRfidPk;

public interface IRegTransacRfidRepo extends JpaRepository<RegTransactRfid, RegTransactRfidPk>{

}
