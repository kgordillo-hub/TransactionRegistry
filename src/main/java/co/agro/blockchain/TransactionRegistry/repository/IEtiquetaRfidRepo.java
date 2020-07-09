package co.agro.blockchain.TransactionRegistry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.agro.blockchain.TransactionRegistry.model.db.EtiquetaRfid;

public interface IEtiquetaRfidRepo extends JpaRepository<EtiquetaRfid, String>{

}
