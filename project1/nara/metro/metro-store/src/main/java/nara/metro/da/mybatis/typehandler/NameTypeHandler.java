package nara.metro.da.mybatis.typehandler;

import nara.share.domain.granule.Name;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Name.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class NameTypeHandler implements TypeHandler<Name> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Name name, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, name != null ? name.toJson() : null);
    }

    @Override
    public Name getResult(ResultSet resultSet, String columnName) throws SQLException {
        return Name.fromJson(resultSet.getString(columnName));
    }

    @Override
    public Name getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return Name.fromJson(resultSet.getString(columnIndex));
    }

    @Override
    public Name getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return Name.fromJson(callableStatement.getString(columnIndex));
    }
}
