package nara.metro.cp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nara.metro.domain.store.CitizenStore;
import nara.metro.domain.store.LoginStore;
import nara.metro.domain.store.MetroStore;
import nara.metro.domain.store.MetroStoreLycler;

@Component
public class MetroStoreSpringLycler implements MetroStoreLycler {
    //
    @Autowired
    private MetroStore metroStore;

    @Autowired
    private CitizenStore citizenStore;

    @Autowired
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
