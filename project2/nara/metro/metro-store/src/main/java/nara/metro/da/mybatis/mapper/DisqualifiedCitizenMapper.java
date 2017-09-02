package nara.metro.da.mybatis.mapper;

import nara.metro.domain.entity.DisqualifiedCitizen;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DisqualifiedCitizenMapper {
    //
    void insert(DisqualifiedCitizen citizen);
    boolean exists(String id);
    DisqualifiedCitizen findOne(String id);
    List<DisqualifiedCitizen> findByMetroId(String metroId);
    int delete(String id);
}
