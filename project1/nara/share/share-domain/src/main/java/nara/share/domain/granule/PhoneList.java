package nara.share.domain.granule;

import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class PhoneList {
    //
    private List<Phone> phones;

    public PhoneList() {
        //
        this.phones = new ArrayList<>();
    }

    public PhoneList(Phone phone) {
        //
        this();
        this.phones.add(phone);
    }

    @Override
    public String toString() {
        //
        return phones.toString();
    }

    public static PhoneList getSample() {
        //
        PhoneList sample = new PhoneList();
        sample.add(Phone.getSample());

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static PhoneList fromJson(String json) {
        //
        return JsonUtil.fromJson(json, PhoneList.class);
    }

    public void add(Phone phone) {
        // 
        this.phones.add(phone);
    }

    public void add(Phone.Category category, String countryCode, String carrierCode, String fullNumber) {
        // 
        Phone newPhone = new Phone(countryCode, carrierCode, fullNumber);
        newPhone.setCategory(category);
        this.phones.add(newPhone);
    }

    public void addAll(List<Phone> phones) {
        // 
        this.phones.addAll(phones);
    }

    public List<Phone> getList() {
        // 
        return phones;
    }

    public Phone getFirst() {
        //
        return phones.get(0);
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public boolean contains(String fullNumber) {
        //
        for(Phone phone : this.phones) {
            if (phone.getFullNumber().equals(fullNumber)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return phones.size();
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().toJson());
        System.out.println(fromJson(getSample().toJson()));
    }
}