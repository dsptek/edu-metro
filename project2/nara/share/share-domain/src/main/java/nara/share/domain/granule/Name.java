package nara.share.domain.granule;

import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

import java.util.Locale;

public class Name implements ValueObject {
	//
	private Locale langLocale;

	private String firstName; 
	private String middleName; 
	private String familyName; 
	private String displayName; 

	public Name() {
		//
	}

	public Name(Locale langLocale, String firstName, String familyName) {
		//
		this.langLocale = langLocale;
		this.firstName = firstName; 
		this.familyName = familyName; 
		this.middleName = ""; 
		this.displayName = firstName + " " + familyName; 
	}

	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 

		builder.append("langLocale:").append(langLocale);
		builder.append(", firstName:").append(firstName);
		builder.append(", middleName:").append(middleName);
		builder.append(", familyName:").append(familyName); 
		builder.append(", displayName:").append(displayName); 
		
		return builder.toString(); 
	}
	
	public static Name getSample() {
		//
		Locale langLocale = Locale.US;
		String firstName = "Steve";
		String familyName = "Jobs";
		String displayName = "Steve Jobs";
		
		Name sample = new Name(langLocale, firstName, familyName);
		sample.setDisplayName(displayName);
		
		return sample; 
	}

	public static Name getSecondSample() {
		//
		Locale langLocale = Locale.US;
		String firstName = "Dan"; 
		String familyName = "Brown"; 
		String displayName = "Dan Brown"; 
		
		Name sample = new Name(langLocale, firstName, familyName);
		sample.setMiddleName("F"); 
		sample.setDisplayName(displayName);
		
		return sample; 
	}

	public String toJson() {
		//
		return JsonUtil.toJson(this);
	}

	public static Name fromJson(String json) {
		//
		return JsonUtil.fromJson(json, Name.class);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public static void main(String[] args) {
		// 
		System.out.println(getSample());
		System.out.println(getSample().toJson());
		System.out.println(fromJson(getSample().toJson()));
	}
}