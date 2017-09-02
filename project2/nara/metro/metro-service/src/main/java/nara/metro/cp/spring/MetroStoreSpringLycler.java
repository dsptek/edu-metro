package nara.metro.cp.spring;

import nara.metro.domain.store.CitizenStore;
import nara.metro.domain.store.LoginStore;
import nara.metro.domain.store.MetroStore;
import nara.metro.domain.store.MetroStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MetroStoreSpringLycler implements MetroStoreLycler {
    //
    @Autowired
    @Qualifier("metroJpaStore")
    private MetroStore metroStore;

    @Autowired
    @Qualifier("citizenJpaStore")
    private CitizenStore citizenStore;

    @Autowired
    @Qualifier("loginJpaStore")
    private LoginStore loginStore;

    @Override
    public MetroStore requestMetroStore() {
        //
        return metroStore;
    }

    @Override
    public CitizenStore requestCitizenStore() {
        //
        return citizenStore;
    }

    @Override
    public LoginStore requestLoginStore() {
        //
        return loginStore;
    }
}
