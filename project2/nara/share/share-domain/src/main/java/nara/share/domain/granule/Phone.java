package nara.share.domain.granule;

import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

public class Phone implements ValueObject {
	//
	private Category category;

	private String countryCode;
	private String carrierCode;

	private String frontNumber;
	private String backNumber;

	private String fullNumber; 			// 91150909, 34569878,
	private String displayNumber; 		// +82 10-9112-0909

	public Phone() {
		//
	}

	public Phone(String countryCode, String carrierCode, String fullNumber) {
		//
		this(
				countryCode,
				carrierCode,
				fullNumber.substring(0, fullNumber.length()-4),
				fullNumber.substring(fullNumber.length()-4)
		);
	}

	public Phone(String countryCode, String carrierCode, String frontNumber, String backNumber) {
		//
		this.category = Category.Mobile;

		this.countryCode = countryCode; 
		this.carrierCode = carrierCode.startsWith("0") ? carrierCode.substring(1) : carrierCode; 
		this.frontNumber = frontNumber; 
		this.backNumber = backNumber;
		this.fullNumber = frontNumber + backNumber;
		this.displayNumber = String.format("+%s %s-%s-%s", countryCode, carrierCode, frontNumber, backNumber); 
	}
	
	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 

		builder.append("category:").append(category);
		builder.append(", countryCode:").append(countryCode);
		builder.append(", carrierCode:").append(carrierCode); 
		builder.append(", frontNumber:").append(frontNumber);
		builder.append(", backNumber:").append(backNumber);
		builder.append(", displayNumber:").append(displayNumber);
		
		return builder.toString(); 
	}

	public static Phone getSample() {
		// 
		String countryCode = "82"; 
		String carrierCode = "010"; 
		String frontNumber = "1235"; 
		String backNumber = "3903";
		
		Phone sample = new Phone(countryCode, carrierCode, frontNumber, backNumber);
		sample.setCategory(Category.Mobile);
		
		return sample; 
	}

	public static Phone getSecondSample() {
		// 
		String countryCode = "82"; 
		String carrierCode = "02";
		String frontNumber = "2225"; 
		String backNumber = "2323";
		
		Phone sample = new Phone(countryCode, carrierCode, frontNumber, backNumber);
		sample.setCategory(Category.Office);
		
		return sample; 
	}

	public String toJson() {
		//
		return JsonUtil.toJson(this);
	}

	public static Phone fromJson(String json) {
		//
		return JsonUtil.fromJson(json, Phone.class);
	}

	public String getNumberOnly() {
		// 
		return String.format("%s%s%s%s", countryCode, carrierCode, frontNumber, backNumber); 
	}

	public Category getCategory() {
		return category;
	}

	public String getFullNumber() {
		return fullNumber;
	}

	public void setFullNumber(String fullNumber) {
		this.fullNumber = fullNumber;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getFrontNumber() {
		return frontNumber;
	}

	public void setFrontNumber(String frontNumber) {
		this.frontNumber = frontNumber;
	}

	public String getBackNumber() {
		return backNumber;
	}

	public void setBackNumber(String backNumber) {
		this.backNumber = backNumber;
	}

	public String getDisplayNumber() {
		return displayNumber;
	}

	public void setDisplayNumber(String displayNumber) {
		this.displayNumber = displayNumber;
	}

	public enum Category {
		//
		Mobile,
		Office,
		Home,
		Others
	}

	public static void main(String[] args) {
		// 
		System.out.println(getSample().getNumberOnly()); 
		System.out.println(getSample().toJson());
		System.out.println(fromJson(getSample().toJson()));
	}
}