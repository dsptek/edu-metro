package nara.metro.da.mybatis.mapper;

import nara.metro.domain.entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginUserMapper {
    //
    void insert(LoginUser loginUser);
    boolean exists(String id);
    boolean existsByMetroIdAndUsername(@Param("metroId") String metroId, @Param("username") String username);
    LoginUser findOne(String id);
    LoginUser findByMetroIdAndEmail(@Param("metroId") String metroId, @Param("email") String email);
    LoginUser findByMetroIdAndUsername(@Param("metroId") String metroId, @Param("username") String username);
    List<LoginUser> findByMetroId(@Param("metroId") String metroId, @Param("offset") int offset, @Param("limit") int limit);
    int update(LoginUser loginUser);
    int delete(String id);
}
