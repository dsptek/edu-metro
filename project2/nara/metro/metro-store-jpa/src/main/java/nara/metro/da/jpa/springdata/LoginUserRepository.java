package nara.metro.da.jpa.springdata;

import nara.metro.da.jpa.jpo.LoginUserJpo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LoginUserRepository extends PagingAndSortingRepository<LoginUserJpo, String> {
    //
    LoginUserJpo findByMetroIdAndEmail(String metroId, String email);
    LoginUserJpo findByMetroIdAndUsername(String metroId, String username);
    List<LoginUserJpo> findByMetroIdOrderByEmail(String metroId, Pageable pageable);

    int countByMetroIdAndUsername(String metroId, String username);
}
