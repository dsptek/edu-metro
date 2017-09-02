package nara.metro.da.jpa.springdata;

import nara.metro.da.jpa.jpo.CitizenJpo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CitizenRepository extends PagingAndSortingRepository<CitizenJpo, String> {
    //
    List<CitizenJpo> findByMetroIdAndDisplayName(String metroId, String name);
    CitizenJpo findByMetroIdAndUsername(String metroId, String username);
    CitizenJpo findByMetroIdAndEmail(String metroId, String email);
    List<CitizenJpo> findByEmail(String email);
    List<CitizenJpo> findByMetroIdOrderBySequence(String metroId, Pageable pageable);
    int countByMetroIdAndUsername(String metroId, String username);
}
