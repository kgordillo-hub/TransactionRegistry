package co.agro.blockchain.TransactionRegistry.model.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="REGISTRO_TRANSACCION", schema = "AGRO_CHAIN_PLATFORM")
public class RegistroTransaccion implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8200270990272459583L;
	@EmbeddedId
	private RegistroTransaccionPk registroPk;
	@Column(name="ID_ESTADO_PRODUCTO")
	private String idEstadoProd;
	@Column(name="ID_USUARIO")
	private String idUsuario;
	@Column(name="FECHA_TRANSACCION")
	private Date fechaTransaccion;
	@Column(name="ID_ORDEN_COMPRA")
	private String idOrdenCompra;
	@Column(name="ID_ASSET_GENESIS")
	private String idAssetGenesis;
	
	public RegistroTransaccion() {
		//default constructor
	}

	public RegistroTransaccionPk getRegistroPk() {
		return registroPk;
	}

	public void setRegistroPk(RegistroTransaccionPk registroPk) {
		this.registroPk = registroPk;
	}

	public String getIdEstadoProd() {
		return idEstadoProd;
	}

	public void setIdEstadoProd(String idEstadoProd) {
		this.idEstadoProd = idEstadoProd;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public String getIdOrdenCompra() {
		return idOrdenCompra;
	}

	public void setIdOrdenCompra(String idOrdenCompra) {
		this.idOrdenCompra = idOrdenCompra;
	}

	public String getIdAssetGenesis() {
		return idAssetGenesis;
	}

	public void setIdAssetGenesis(String idAssetGenesis) {
		this.idAssetGenesis = idAssetGenesis;
	}
	
	
}
