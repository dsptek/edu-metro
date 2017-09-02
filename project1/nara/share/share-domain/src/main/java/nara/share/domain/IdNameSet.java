package nara.share.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class IdNameSet {
    //
    private Set<IdName> idNames;

    public IdNameSet() {
        //
        this.idNames = new LinkedHashSet<>();
    }

    public IdNameSet(Set<IdName> idNames) {
        //
        this.idNames = idNames;
    }

    public IdNameSet(IdName idName) {
        //
        this();
        this.idNames.add(idName);
    }

    public IdNameSet(String id, String name) {
        //
        this(new IdName(id, name));
    }

    @Override
    public String toString() {
        //
        return idNames.toString();
    }

    public void remove(String id) {
        //
        IdName foundIdName = null;

        for(IdName idName : idNames) {
            if (idName.getId().equals(id)) {
                foundIdName = idName;
                break;
            }
        }

        if (foundIdName != null) {
            idNames.remove(foundIdName);
        }
    }

    public void add(IdName idName) {
        //
        this.idNames.add(idName);
    }

    public void add(String id, String name) {
        //
        this.idNames.add(new IdName(id, name));
    }

    public void addAll(Set<IdName> idNames) {
        //
        this.idNames.addAll(idNames);
    }

    public Set<IdName> getSet() {
        //
        return idNames;
    }

    public int size() {
        return idNames.size();
    }
}