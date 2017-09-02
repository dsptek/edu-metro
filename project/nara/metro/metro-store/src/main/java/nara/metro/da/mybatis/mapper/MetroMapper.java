package nara.metro.da.mybatis.mapper;

import nara.metro.domain.entity.Metro;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MetroMapper {
    //
    void insert(Metro metro);
    boolean exists(String id);
    boolean existByName(String name);
    Metro findOne(String id);
    List<Metro> findAll();
    int update(Metro metro);
    int delete(String id);
}
