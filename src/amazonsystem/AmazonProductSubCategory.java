package amazonsystem;

public class AmazonProductSubCategory{
	private String subCategoryName;
	private AmazonProductCategory category;
	
	public AmazonProductSubCategory(String name, AmazonProductCategory category) {
		this.subCategoryName = name;
		this.category = category;
	}
	
	public String getCategory() {
		return category.getCategory();
	}
	
	public String getSubCategory() {
		return this.subCategoryName;
	}
	
	public void setCategory(AmazonProductCategory category) {
		this.category = category;
	}
	
	public void setSubCategory(String name) {
		this.subCategoryName = name;
	}
	

}
