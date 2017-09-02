package nara.share.domain.granule;

import nara.share.domain.Tier;
import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

public class Email implements ValueObject {
    //
    private Category category;
    private Tier tier;
    private String email;

    public Email() {
        //
    }

    public Email(String email) {
        //
        this.tier = Tier.Secondary;
        this.email = email;
        this.category = Category.Others;
    }

    public Email(Category category, String email) {
        //
        this.category = category;
        this.tier = Tier.Secondary;
        this.email = email;
    }

    public Email(Category category, Tier tier, String email) {
        //
        this.category = category;
        this.tier = tier;
        this.email = email;
    }

    @Override
    public String toString() {
        //
        StringBuilder builder = new StringBuilder();

        builder.append("category:").append(category);
        builder.append(", tier:").append(tier);
        builder.append(", email:").append(email);

        return builder.toString();
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static Email fromJson(String json) {
        return JsonUtil.fromJson(json, Email.class);
    }

    public static Email getSample() {
        //
        Category category = Category.Company;
        String email = "jhone@company.com";

        return new Email(category, Tier.Primary, email);
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public enum Category {
        //
        Company,
        Personal,
        Others
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().toJson());
    }
}