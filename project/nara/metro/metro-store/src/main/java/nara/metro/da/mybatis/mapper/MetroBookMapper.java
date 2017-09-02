package nara.metro.da.mybatis.mapper;

import nara.metro.domain.entity.MetroBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MetroBookMapper {
    //
    void insert(MetroBook metroBook);
    boolean exists(String id);
    MetroBook findOne(String id);
    int update(MetroBook metroBook);
}
