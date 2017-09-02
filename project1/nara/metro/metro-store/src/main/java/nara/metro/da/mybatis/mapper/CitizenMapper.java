package nara.metro.da.mybatis.mapper;

import nara.metro.domain.entity.Citizen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CitizenMapper {
    //
    void insert(Citizen citizen);
    boolean exists(String id);
    boolean existsByMetroIdAndUsername(@Param("metroId") String metroId, @Param("username") String username);
    Citizen findOne(String id);
    List<Citizen> findByMetroIdAndDisplayName(@Param("metroId") String metroId, @Param("displayName") String displayName);
    Citizen findByMetroIdAndEmail(@Param("metroId") String metroId, @Param("email") String email);
    Citizen findByMetroIdAndUsername(@Param("metroId") String metroId, @Param("username") String username);
    List<Citizen> findByEmail(String email);
    List<Citizen> findByMetroId(@Param("metroId") String metroId, @Param("offset") int offset, @Param("limit") int limit);
    int update(Citizen citizen);
    int delete(String id);
}
