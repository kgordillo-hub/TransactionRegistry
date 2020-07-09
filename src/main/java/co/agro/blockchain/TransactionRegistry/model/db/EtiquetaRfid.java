package co.agro.blockchain.TransactionRegistry.model.db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ETIQUETAS_RFID", schema = "AGRO_CHAIN_PLATFORM")
public class EtiquetaRfid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6916784492649588996L;
	@Column(name = "ID_RFID")
	@Id
	private String idRfid;
	@Column(name = "DESCRIPCION")
	private String descripcion;

	public EtiquetaRfid() {

	}

	public EtiquetaRfid(final String idRfid) {
		this.idRfid = idRfid;
	}
	
	public EtiquetaRfid(String idRfid, String descripcion) {
		this.idRfid = idRfid;
		this.descripcion = descripcion;
	}

	public String getIdRfid() {
		return idRfid;
	}

	public void setIdRfid(String idRfid) {
		this.idRfid = idRfid;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
