package nara.metro.sp.spring.web;

import nara.metro.domain.entity.LoginUser;
import nara.metro.domain.spec.LoginProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/p/login-users")
public class LoginProviderResource implements LoginProvider {
    //
    @Autowired
    @Qualifier("loginLogic")
    private LoginProvider loginProvider;

    @Override
    @GetMapping(params = {"metroId", "username"})
    public LoginUser findLoginUserByUsername(@RequestParam("metroId") String metroId, @RequestParam String username) {
        //
        return loginProvider.findLoginUserByUsername(metroId, username);
    }

    @Override
    @GetMapping(params = {"metroId", "email"})
    public LoginUser findLoginUserByEmail(@RequestParam("metroId") String metroId, @RequestParam String email) {
        //
        return loginProvider.findLoginUserByEmail(metroId, email);
    }

    @Override
    @PostMapping("{citizenId}")
    public void registerLoginTime(@PathVariable String citizenId) {
        //
        loginProvider.registerLoginTime(citizenId);
    }
}
