package nara.metro.cp.spring.bean;

import nara.metro.domain.logic.MetroLogic;
import nara.metro.domain.store.MetroStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("metroLogic")
@Transactional
public class MetroSpringLogic extends MetroLogic {
    //
    @Autowired
    public MetroSpringLogic(MetroStoreLycler storeLycler) {
        //
        super(storeLycler);
    }
}
