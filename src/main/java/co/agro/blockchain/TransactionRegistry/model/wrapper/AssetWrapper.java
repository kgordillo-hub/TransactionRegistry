package co.agro.blockchain.TransactionRegistry.model.wrapper;

import co.agro.blockchain.TransactionRegistry.model.AssetDto;

public class AssetWrapper {

	private String privateKey;
	private String userId;
	private AssetDto asset;
	private String genesisAssetId;
	
	
	
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public AssetDto getAsset() {
		return asset;
	}
	public void setAsset(AssetDto asset) {
		this.asset = asset;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGenesisAssetId() {
		return genesisAssetId;
	}
	public void setGenesisAssetId(String genesisAssetId) {
		this.genesisAssetId = genesisAssetId;
	}
	
	
}
