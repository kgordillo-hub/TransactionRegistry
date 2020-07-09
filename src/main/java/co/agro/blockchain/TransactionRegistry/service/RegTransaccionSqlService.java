package co.agro.blockchain.TransactionRegistry.service;

import java.util.List;
import java.util.Optional;

import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccion;

public interface RegTransaccionSqlService {

	List<RegistroTransaccion> listarTransacciones(final String idUsuario);

	Optional<RegistroTransaccion> listarTransaccionesByIdBloque(final String idTransaccion);

	List<RegistroTransaccion> listarTransaccionesByIdAsset(final String idAsset);
	
	Boolean insertarTransaccion(final RegistroTransaccion registroTransaccion);
	
	List<RegistroTransaccion> listarTransaccionesByRfid(final String rfidId);
}
