package nara.share.domain.granule;

import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

import java.util.HashSet;
import java.util.Set;

public class AddressSet implements ValueObject {
    //
    private Set<LocaledAddress> addresses;

    public AddressSet() {
        //
        this.addresses = new HashSet<>();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddressSet{");
        sb.append("addresses=").append(addresses);
        sb.append('}');
        return sb.toString();
    }

    public static AddressSet getSample() {
        //
        AddressSet sample = new AddressSet();
        sample.getAddresses().add(LocaledAddress.getKoreanSample());
        sample.getAddresses().add(LocaledAddress.getUSSample());

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static AddressSet fromJson(String json) {
        //
        return JsonUtil.fromJson(json, AddressSet.class);
    }

    public LocaledAddress requestAddressByTag(String tag) {
        //
        for(LocaledAddress address : addresses) {
            if (address.getTag().equals(tag)) {
                return address;
            }
        }

        return null;
    }

    public Set<LocaledAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<LocaledAddress> addresses) {
        this.addresses = addresses;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}