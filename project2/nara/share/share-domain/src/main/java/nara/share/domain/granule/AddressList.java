package nara.share.domain.granule;

import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class AddressList {
    //
    private List<Address> addresses;

    public AddressList() {
        //
        this.addresses = new ArrayList<>();
    }

    public AddressList(Address address) {
        //
        this();
        this.addresses.add(address);
    }

    @Override
    public String toString() {
        //
        return addresses.toString();
    }

    public static AddressList getSample() {
        //
        AddressList sample = new AddressList();
        sample.add(Address.getKoreanSample());
        sample.add(Address.getUSSample());

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static AddressList fromJson(String json) {
        //
        return JsonUtil.fromJson(json, AddressList.class);
    }

    public void add(Address address) {
        // 
        this.addresses.add(address);
    }

    public void addAll(List<Address> addresss) {
        // 
        this.addresses.addAll(addresss);
    }

    public List<Address> getList() {
        // 
        return addresses;
    }

    public boolean contains(String zipCode) {
        //
        boolean found = false;
        for(Address address : this.addresses) {
            if (address.getZipCode().equals(zipCode)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public int size() {
        return addresses.size();
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().toJson());
        System.out.println(fromJson(getSample().toJson()));
    }
}