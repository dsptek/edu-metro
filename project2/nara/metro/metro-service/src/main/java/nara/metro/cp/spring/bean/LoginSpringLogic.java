package nara.metro.cp.spring.bean;

import nara.metro.domain.logic.LoginLogic;
import nara.metro.domain.store.MetroStoreLycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("loginLogic")
@Transactional
public class LoginSpringLogic extends LoginLogic {
    //
    @Autowired
    public LoginSpringLogic(MetroStoreLycler storeLycler) {
        //
        super(storeLycler);
    }
}
