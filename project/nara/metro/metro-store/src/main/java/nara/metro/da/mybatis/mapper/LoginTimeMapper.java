package nara.metro.da.mybatis.mapper;

import nara.metro.domain.entity.LoginTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginTimeMapper {
    //
    void insert(LoginTime loginTime);
    boolean exists(String id);
    List<LoginTime> findByCitizenId(@Param("citizenId") String citizenId, @Param("offset") int offset, @Param("limit") int limit);
    int delete(String id);
    int deleteByCitizenId(String citizenId);
}
