package nara.share.domain.granule;

import nara.share.domain.ValueObject;
import nara.share.util.json.JsonUtil;

import java.util.HashMap;
import java.util.Map;

public class NameSet implements ValueObject {
    //
    private String defaultLangCode;
    private String displayName;
    private Map<String,LocaledName> nameMap;

    public NameSet(LocaledName name) {
        //
        this.defaultLangCode = name.getLangCode();
        this.displayName = name.getDisplayName();
        this.nameMap = new HashMap<>();
        this.nameMap.put(name.getLangCode(), name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NameSet{");
        sb.append("defaultLangCode='").append(defaultLangCode).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", nameMap=").append(nameMap);
        sb.append('}');
        return sb.toString();
    }

    public static NameSet getSample() {
        //
        LocaledName name = LocaledName.getSample();
        NameSet sample = new NameSet(name);

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static NameSet fromJson(String json) {
        //
        return JsonUtil.fromJson(json, NameSet.class);
    }

    public LocaledName getLocaedName(String langCode) {
        //
        LocaledName resultName = nameMap.get(langCode);
        if (resultName == null) {
            resultName = getDefault();
        }

        return resultName;
    }

    public LocaledName getDefault() {
        //
        return nameMap.get(defaultLangCode);
    }

    public String getDefaultLangCode() {
        return defaultLangCode;
    }

    public void setDefaultLangCode(String defaultLangCode) {
        this.defaultLangCode = defaultLangCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Map<String, LocaledName> getNameMap() {
        return nameMap;
    }

    public void setNameMap(Map<String, LocaledName> nameMap) {
        this.nameMap = nameMap;
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
    }
}