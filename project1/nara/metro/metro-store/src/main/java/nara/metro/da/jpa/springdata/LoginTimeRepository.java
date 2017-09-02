package nara.metro.da.jpa.springdata;

import nara.metro.da.jpa.jpo.LoginTimeJpo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LoginTimeRepository extends PagingAndSortingRepository<LoginTimeJpo, String> {
    //
    List<LoginTimeJpo> findByCitizenId(String citizenId, Pageable pageable);

    @Modifying
    @Transactional
    @Query("delete from MT_LOGIN_TIME t where t.citizenId = ?1")
    void deleteByCitizenId(String citizenId);
}
