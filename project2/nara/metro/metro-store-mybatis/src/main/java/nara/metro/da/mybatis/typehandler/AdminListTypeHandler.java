package nara.metro.da.mybatis.typehandler;

import nara.share.domain.granule.AdminList;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(AdminList.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class AdminListTypeHandler implements TypeHandler<AdminList> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, AdminList adminList, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, adminList != null ? adminList.toJson() : null);
    }

    @Override
    public AdminList getResult(ResultSet resultSet, String columnName) throws SQLException {
        return AdminList.fromJson(resultSet.getString(columnName));
    }

    @Override
    public AdminList getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return AdminList.fromJson(resultSet.getString(columnIndex));
    }

    @Override
    public AdminList getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return AdminList.fromJson(callableStatement.getNString(columnIndex));
    }
}
