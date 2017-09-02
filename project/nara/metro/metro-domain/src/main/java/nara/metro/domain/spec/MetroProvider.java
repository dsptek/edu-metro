package nara.metro.domain.spec;

import nara.metro.domain.entity.Metro;
import nara.metro.domain.spec.sdo.MetroCdo;
import nara.share.domain.NameValueList;

import java.util.List;

public interface MetroProvider {
    //
    String buildMetro(MetroCdo metroCdo);
    Metro findMetro(String metroId);
    Metro findMetroByName(String name);
    List<Metro> findMetros();
    boolean existMetroByName(String name);
    void modifyMetro(String metroId, NameValueList nameValues);
    long nextPavilionSequence(String metroId);
}