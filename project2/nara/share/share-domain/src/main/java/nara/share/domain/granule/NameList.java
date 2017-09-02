package nara.share.domain.granule;

import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NameList {
    //
    private List<Name> names;

    public NameList() {
        //
        this.names = new ArrayList<>();
    }

    public NameList(Name name) {
        //
        this();
        this.names.add(name);
    }

    public NameList(Locale langLocale, String firstName, String familyName) {
        //
        this();
        this.names.add(new Name(langLocale, firstName, familyName));
    }

    @Override
    public String toString() {
        //
        return names.toString();
    }

    public static NameList getSample() {
        //
        NameList sample = new NameList();
        sample.add(Name.getSample());

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static NameList fromJson(String json) {
        //
        return JsonUtil.fromJson(json, NameList.class);
    }

    public void add(Name name) {
        // 
        this.names.add(name);
    }

    public void add(Locale langLocale, String firstName, String familyName) {
        // 
        this.names.add(new Name(langLocale, firstName, familyName));
    }

    public void addAll(List<Name> names) {
        // 
        this.names.addAll(names);
    }

    public List<Name> getList() {
        // 
        return names;
    }

    public Name getFirst() {
        //
        return names.get(0);
    }

    public Name get(String firstName, String familyName) {
        //
        Name foundName = null;
        for(Name name : this.names) {
            if(name.getFirstName().equals(firstName) &&
                    name.getFirstName().equals(familyName)) {
                foundName = name;
                break;
            }
        }

        return foundName;
    }

    public boolean contains(String firstName, String familyName) {
        //
        boolean found = false;
        for(Name name : this.names) {
            if(name.getFirstName().equals(firstName) &&
                    name.getFamilyName().equals(familyName)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public int size() {
        return names.size();
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().toJson());
        System.out.println(fromJson(getSample().toJson()));
    }
}