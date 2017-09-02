package nara.metro.sp.spring.web;

import nara.metro.domain.entity.LoginTime;
import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.spec.LoginService;
import nara.metro.domain.spec.sdo.LoginUserCdo;
import nara.share.domain.NameValueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/s/login-users")
public class LoginServiceResource implements LoginService {
    //
    @Autowired
    @Qualifier("loginLogic")
    private LoginService loginService;

    @Override
    @PostMapping("{citizenId}")
    public String registerLoginUser(@PathVariable String citizenId, @RequestBody LoginUserCdo loginUserCdo) {
        //
        return loginService.registerLoginUser(citizenId, loginUserCdo);
    }

    @Override
    @GetMapping("{citizenId}")
    public LoginUser findLoginUser(@PathVariable String citizenId) {
        //
        return loginService.findLoginUser(citizenId);
    }

    @Override
    @GetMapping(params = {"metroId", "offset", "limit"})
    public List<LoginUser> findLoginUserByMetro(@RequestParam String metroId, @RequestParam int offset, @RequestParam int limit) {
        //
        return loginService.findLoginUserByMetro(metroId, offset, limit);
    }

    @Override
    @PutMapping("{citizenId}")
    public void modifyLoginUser(@PathVariable String citizenId, @RequestBody NameValueList nameValues) {
        //
        loginService.modifyLoginUser(citizenId, nameValues);
    }

    @Override
    @DeleteMapping("{citizenId}")
    public void removeLoginUser(@PathVariable String citizenId) {
        //
        loginService.removeLoginUser(citizenId);
    }

    @Override
    @GetMapping("{citizenId}/login-times")
    public List<LoginTime> findLoginTimeByCitizen(@PathVariable String citizenId, @RequestParam int offset, @RequestParam int limit) {
        //
        return loginService.findLoginTimeByCitizen(citizenId, offset, limit);
    }

    @Override
    @DeleteMapping("{citizenId}/login-times")
    public void removeLoginTimesByCitizen(@PathVariable String citizenId) {
        //
        loginService.removeLoginTimesByCitizen(citizenId);
    }

}
