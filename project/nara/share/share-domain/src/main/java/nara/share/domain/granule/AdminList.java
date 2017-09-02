package nara.share.domain.granule;

import nara.share.domain.Tier;
import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class AdminList {
    //
    private List<Admin> admins;

    public AdminList() {
        //
        this.admins = new ArrayList<>();
    }

    public AdminList(Admin admin) {
        //
        this();
        this.admins.add(admin);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdminList{");
        sb.append("admins=").append(admins);
        sb.append('}');
        return sb.toString();
    }

    public static AdminList getSample() {
        //
        AdminList sample = new AdminList(Admin.getSample());

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static AdminList fromJson(String json) {
        //
        return JsonUtil.fromJson(json, AdminList.class);
    }

    public void add(Admin admin) {
        // 
        this.admins.add(admin);
    }

    public void addAll(List<Admin> admins) {
        // 
        this.admins.addAll(admins);
    }

    public List<Admin> getList() {
        // 
        return admins;
    }

    public Admin find(String id) {
        //
        return this.admins.stream().filter(admin -> id.equals(admin.getId())).findFirst().orElse(null);
    }

    public void remove(String id) {
        //
        Admin admin = find(id);
        if (admin == null) return;
        this.admins.remove(admin);
    }

    public boolean contains(String id) {
        //
        return find(id) != null;
    }

    public Admin getPrimaryAdmin() {
        for (Admin admin : this.admins) {
            if (Tier.Primary == admin.getTier()) {
                return admin;
            }
        }
        return null;
    }

    public int size() {
        //
        return admins.size();
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().toJson());
        System.out.println(fromJson(getSample().toJson()));
    }
}