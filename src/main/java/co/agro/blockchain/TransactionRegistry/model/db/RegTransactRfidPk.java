package co.agro.blockchain.TransactionRegistry.model.db;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
public class RegTransactRfidPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2666516291146184436L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "ID_BLOQUE_BC", nullable = true),
			@JoinColumn(name = "ID_TRANSACCION", nullable = true) })
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private RegistroTransaccion regTransc;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RFID", nullable = true)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private EtiquetaRfid etiquetaRfid;

	public RegTransactRfidPk() {
		// default constructor
	}

	public RegTransactRfidPk(RegistroTransaccion regTransc, EtiquetaRfid etiquetaRfid) {
		this.regTransc = regTransc;
		this.etiquetaRfid = etiquetaRfid;
	}

	public RegistroTransaccion getRegTransc() {
		return regTransc;
	}

	public void setRegTransc(RegistroTransaccion regTransc) {
		this.regTransc = regTransc;
	}

	public EtiquetaRfid getEtiquetaRfid() {
		return etiquetaRfid;
	}

	public void setEtiquetaRfid(EtiquetaRfid etiquetaRfid) {
		this.etiquetaRfid = etiquetaRfid;
	}

}
