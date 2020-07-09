package co.agro.blockchain.TransactionRegistry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccion;
import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccionPk;

public interface IRegistroTransaccionRepo extends JpaRepository<RegistroTransaccion, RegistroTransaccionPk>{

	
	@Query(value = "SELECT * FROM AGRO_CHAIN_PLATFORM.REGISTRO_TRANSACCION RT WHERE RT.ID_TRANSACCION IN (SELECT ID_TRANSACCION FROM AGRO_CHAIN_PLATFORM.REG_TRANSACTION_RFID RTRF WHERE RTRF.ID_RFID = ?1) ORDER BY RT.FECHA_TRANSACCION ASC",nativeQuery = true)
	List<RegistroTransaccion> selectTransactionsByTagId(final String rfidTag);
}
