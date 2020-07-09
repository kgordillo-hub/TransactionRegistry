package co.agro.blockchain.TransactionRegistry.serviceImpl.sql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccion;
import co.agro.blockchain.TransactionRegistry.model.db.RegistroTransaccionPk;
import co.agro.blockchain.TransactionRegistry.repository.IRegistroTransaccionRepo;
import co.agro.blockchain.TransactionRegistry.service.RegTransaccionSqlService;

@Service
public class RegTransaccionSqlServiceImpl implements RegTransaccionSqlService {

	@Autowired
	IRegistroTransaccionRepo registroTransact;


	@Override
	public List<RegistroTransaccion> listarTransacciones(String idUsuario) {
		final RegistroTransaccion regTransaccion = new RegistroTransaccion();
		regTransaccion.setIdUsuario(idUsuario);
		Example<RegistroTransaccion> example = Example.of(regTransaccion);
		return registroTransact.findAll(example);
	}

	@Override
	public Optional<RegistroTransaccion> listarTransaccionesByIdBloque(String idTransaccion) {
		final RegistroTransaccion regTransaccion = new RegistroTransaccion();
		regTransaccion.setRegistroPk(new RegistroTransaccionPk(idTransaccion));
		Example<RegistroTransaccion> example = Example.of(regTransaccion);
		return registroTransact.findOne(example);
	}

	@Override
	public List<RegistroTransaccion> listarTransaccionesByIdAsset(String idAsset) {
		final RegistroTransaccion regTransaccion = new RegistroTransaccion();
		regTransaccion.setIdAssetGenesis(idAsset);
		Example<RegistroTransaccion> example = Example.of(regTransaccion);
		return registroTransact.findAll(example);
	}

	@Override
	public Boolean insertarTransaccion(RegistroTransaccion registroTransaccion) {
		if (registroTransaccion != null && registroTransaccion.getRegistroPk() != null
				&& !listarTransaccionesByIdBloque(registroTransaccion.getRegistroPk().getIdBloqueBc()).isPresent()) {
			registroTransact.save(registroTransaccion);
			return true;
		}
		return false;
	}

	@Override
	public List<RegistroTransaccion> listarTransaccionesByRfid(String rfidId) {
		return registroTransact.selectTransactionsByTagId(rfidId);
	}

}
