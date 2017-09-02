package nara.metro.da.jpa.springdata;

import nara.metro.da.jpa.jpo.DisqualifiedCitizenJpo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DisqualifiedCitizenRepository extends PagingAndSortingRepository<DisqualifiedCitizenJpo, String> {
    //
    List<DisqualifiedCitizenJpo> findByMetroId(String metroId);
}
