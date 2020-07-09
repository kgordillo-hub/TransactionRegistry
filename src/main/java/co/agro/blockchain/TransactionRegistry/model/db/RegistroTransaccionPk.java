package co.agro.blockchain.TransactionRegistry.model.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RegistroTransaccionPk implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4776390776889209232L;
	@Column(name="ID_TRANSACCION")
	private String idTransaccion;
	@Column(name="ID_BLOQUE_BC")
	private String idBloqueBc;
	
	public RegistroTransaccionPk() {
		//Basic constructor
	}
	
	public RegistroTransaccionPk(final String idBloqueBc) {
		this.idBloqueBc = idBloqueBc;
	}
	
	public RegistroTransaccionPk(final String idBloqueBc, final String idTransaccion) {
		this.idBloqueBc = idBloqueBc;
		this.idTransaccion = idTransaccion;
	}
	
	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getIdBloqueBc() {
		return idBloqueBc;
	}

	public void setIdBloqueBc(String idBloqueBc) {
		this.idBloqueBc = idBloqueBc;
	}
	
	
}
