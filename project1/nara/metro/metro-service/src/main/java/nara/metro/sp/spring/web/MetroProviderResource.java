package nara.metro.sp.spring.web;

import nara.metro.domain.entity.Metro;
import nara.metro.domain.spec.MetroProvider;
import nara.metro.domain.spec.sdo.MetroCdo;
import nara.share.domain.NameValueList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/p/metros")
public class MetroProviderResource implements MetroProvider {
    //
    @Autowired
    @Qualifier("metroLogic")
    private MetroProvider metroProvider;

    @Override
    @PostMapping
    public String buildMetro(@RequestBody MetroCdo metroCdo) {
        //
        return metroProvider.buildMetro(metroCdo);
    }

    @Override
    @GetMapping("{metroId}")
    public Metro findMetro(@PathVariable String metroId) {
        //
        return metroProvider.findMetro(metroId);
    }

    @Override
    @GetMapping(params = "name")
    public Metro findMetroByName(@RequestParam String name) {
        //
        return metroProvider.findMetroByName(name);
    }

    @Override
    @GetMapping
    public List<Metro> findMetros() {
        //
        return metroProvider.findMetros();
    }

    @Override
    @GetMapping(value = "exists", params = "name")
    public boolean existMetroByName(@RequestParam String name) {
        //
        return metroProvider.existMetroByName(name);
    }

    @Override
    @PutMapping("{metroId}")
    public void modifyMetro(@PathVariable String metroId, @RequestBody NameValueList nameValues) {
        //
        metroProvider.modifyMetro(metroId, nameValues);
    }

    @Override
    @GetMapping("{metroId}/next-pavilion-sequence")
    public long nextPavilionSequence(@PathVariable String metroId) {
        //
        return metroProvider.nextPavilionSequence(metroId);
    }
}
