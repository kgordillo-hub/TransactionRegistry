package co.agro.blockchain.TransactionRegistry.model.wrapper;

import co.agro.blockchain.TransactionRegistry.model.MetaDataDto;

public class MetadataWrapper {

	private String transactionId;
	private String genesisAssetId;
	private String privateKey;
	private String destinationPublicKey;
	private String userId;
	private String rfidTag;
	private MetaDataDto metaData;
	
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public MetaDataDto getMetaData() {
		return metaData;
	}
	public void setMetaData(MetaDataDto metaData) {
		this.metaData = metaData;
	}
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getDestinationPublicKey() {
		return destinationPublicKey;
	}
	public void setDestinationPublicKey(String destinationPublicKey) {
		this.destinationPublicKey = destinationPublicKey;
	}
	public String getGenesisAssetId() {
		return genesisAssetId;
	}
	public void setGenesisAssetId(String genesisAssetId) {
		this.genesisAssetId = genesisAssetId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRfidTag() {
		return rfidTag;
	}
	public void setRfidTag(String rfidTag) {
		this.rfidTag = rfidTag;
	}
	
}
