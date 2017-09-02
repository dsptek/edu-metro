package nara.metro.da.jpa.springdata;

import nara.metro.da.jpa.jpo.MetroJpo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MetroRepository extends PagingAndSortingRepository<MetroJpo, String> {
    //
    MetroJpo findByName(String name);
}
