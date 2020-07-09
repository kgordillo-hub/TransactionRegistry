package co.agro.blockchain.TransactionRegistry.model.db;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "REG_TRANSACTION_RFID", schema = "AGRO_CHAIN_PLATFORM")
public class RegTransactRfid implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744396540887487073L;

	@EmbeddedId
	private RegTransactRfidPk regTranscPk;

	public RegTransactRfid() {

	}

	public RegTransactRfid(RegTransactRfidPk regTranscPk) {
		this.regTranscPk = regTranscPk;
	}

	public RegTransactRfidPk getRegTranscPk() {
		return regTranscPk;
	}

	public void setRegTranscPk(RegTransactRfidPk regTranscPk) {
		this.regTranscPk = regTranscPk;
	}

}
