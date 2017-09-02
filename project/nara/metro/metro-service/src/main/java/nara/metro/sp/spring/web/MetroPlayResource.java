package nara.metro.sp.spring.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MetroPlayResource {
    //
    @Value("${nara.common.asset.location}")
    private String commonAssetLocation;

    @RequestMapping("/**")
    public String index(
            Model model
    ) {
        //
        model.addAttribute("commonAssetLocation", commonAssetLocation);
        return "index";
    }
}
