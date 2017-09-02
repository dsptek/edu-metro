package nara.metro.da.jpa.springdata;

import nara.metro.da.jpa.jpo.MetroBookJpo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MetroBookRepository extends PagingAndSortingRepository<MetroBookJpo, String> {
}
