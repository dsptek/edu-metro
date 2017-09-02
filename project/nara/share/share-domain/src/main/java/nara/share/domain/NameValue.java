package nara.share.domain;

import java.util.StringTokenizer;

/**
 * Name-Value pair object </br>
 * Name is not nullable, but value is nullable. </br>
 * NameValue is immutable, so you can't not update any field of this object.
 *
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2016. 3. 29.
 */
public class NameValue {
    //
    private String name;
    private String value;

    public NameValue() {
        //
    }

    public NameValue(String name, String value) {
    	this.setName(name);
    	this.setValue(value);
    }

    public NameValue(String json) {
        //
        if (json == null) {
            throw new IllegalArgumentException("JSON for the NameValue class is not nullable.");
        }

        String nameValueStr[] = json.split(":");        // {"nameA":"nameB"}

        String nameStr = nameValueStr[0];               // {"nameA"
        String valueStr = nameValueStr[1];              // "valueA"}

        String cleanName = nameStr.split("\"")[1];      // "name"
        String cleanValue = valueStr.split("\"")[1];    // "value"

        this.setName(cleanName);
        this.setValue(cleanValue);
    }

     public static NameValue fromJson(String json) {
        //
        return new NameValue(json);
    }

    @Override
    public String toString() {
        //
        return name + ":" + value;
    }

    public static NameValue fromString(String nameValueStr) {
        //
        StringTokenizer tokenizer = new StringTokenizer(nameValueStr, ":");
        String name = tokenizer.nextToken();
        String value = tokenizer.nextToken();

        return new NameValue(name, value);
    }

    public boolean equalsName(String name) {
        //
        return this.name != null && this.name.equals(name);
    }

    public boolean getValueAsBoolean() {
        //
        return Boolean.valueOf(this.value);
    }

    public String toJson() {
        //
        return String.format("{\"%s\":\"%s\"}", name, value);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
    	// immutable field.
    	if (name == null) {
    		throw new IllegalArgumentException("Name should not be a null value.");
    	}

        this.name = name;
    }

    public void setValue(String value) {
        // immutable field
        this.value = value;
    }
}