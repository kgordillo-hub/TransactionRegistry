package co.agro.blockchain.TransactionRegistry.model;

public class AssetDto {

	private String assetId;
	private String idTransaccion;
	private String fechaHora;
	private MetaDataDto metaData;
	private ProductDto productData;
	
	
	public AssetDto() {
		
	}

	public AssetDto(MetaDataDto metaData, ProductDto productData, final String assetId, final String idTransaccion) {
		this.metaData = metaData;
		this.productData = productData;
		this.assetId = assetId;
		this.idTransaccion = idTransaccion;
	}
	
	public MetaDataDto getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaDataDto metaData) {
		this.metaData = metaData;
	}

	public ProductDto getProductData() {
		return productData;
	}

	public void setProductData(ProductDto productData) {
		this.productData = productData;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

}
