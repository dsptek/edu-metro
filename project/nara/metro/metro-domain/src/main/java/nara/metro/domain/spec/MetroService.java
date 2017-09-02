package nara.metro.domain.spec;

import nara.metro.domain.entity.Metro;
import nara.metro.domain.spec.sdo.MetroCdo;
import nara.share.domain.NameValueList;

import java.util.List;

public interface MetroService {
    //
    String buildMetro(MetroCdo metroCdo);
    Metro findMetro(String metroId);
    boolean existMetroByName(String name);
    List<Metro> findMetros();
    void modifyMetro(String metroId, NameValueList nameValues);
    void removeMetro(String metroId);
}