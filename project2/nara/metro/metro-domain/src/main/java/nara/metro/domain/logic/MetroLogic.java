package nara.metro.domain.logic;

import nara.metro.domain.entity.Metro;
import nara.metro.domain.spec.MetroProvider;
import nara.metro.domain.spec.MetroService;
import nara.metro.domain.spec.sdo.MetroCdo;
import nara.metro.domain.store.MetroStore;
import nara.metro.domain.store.MetroStoreLycler;
import nara.share.domain.NameValueList;

import java.util.List;

public class MetroLogic implements MetroService, MetroProvider {
    //
    private MetroStore metroStore;

    public MetroLogic(MetroStoreLycler storeLycler) {
        //
        this.metroStore = storeLycler.requestMetroStore();
    }

    @Override
    public String buildMetro(MetroCdo metroCdo) {
        //
        long sequence = metroStore.nextSequenceBook().getSequence();
        String name = metroCdo.getName();
        String memo = metroCdo.getMemo();
        Metro metro = new Metro(sequence, name, memo);

        metroStore.create(metro);

        return metro.getId();
    }

    @Override
    public Metro findMetro(String metroId) {
        //
        return metroStore.retrieve(metroId);
    }

    @Override
    public Metro findMetroByName(String name) {
        //
        return metroStore.retrieveByName(name);
    }

    @Override
    public boolean existMetroByName(String name) {
        //
        return metroStore.existByName(name);
    }

    @Override
    public List<Metro> findMetros() {
        //
        return metroStore.retrieveAll();
    }

    @Override
    public void modifyMetro(String metroId, NameValueList nameValues) {
        //
        Metro metro = this.findMetro(metroId);
        metro.setValues(nameValues);

        metroStore.update(metro);
    }

    @Override
    public long nextPavilionSequence(String metroId) {
        //
        Metro metro = this.findMetro(metroId);
        long nextPavilionSequence = metro.nextPavilionSequence();
        metroStore.update(metro);

        return nextPavilionSequence;
    }

    @Override
    public void removeMetro(String metroId) {
        //
        Metro metro = metroStore.retrieve(metroId);
        metroStore.delete(metro);
    }

}