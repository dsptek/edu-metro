package nara.metro.da.mybatis.typehandler;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;

@MappedTypes(ZonedDateTime.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ZonedDateTimeTypeHandler implements TypeHandler<ZonedDateTime> {
    //
    static final Gson gson = Converters.registerZonedDateTime(new GsonBuilder()).create();

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, ZonedDateTime zonedDateTime, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, zonedDateTime != null ? gson.toJson(zonedDateTime) : null);
    }

    @Override
    public ZonedDateTime getResult(ResultSet resultSet, String columnName) throws SQLException {
        return gson.fromJson(resultSet.getString(columnName), ZonedDateTime.class);
    }

    @Override
    public ZonedDateTime getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return gson.fromJson(resultSet.getString(columnIndex), ZonedDateTime.class);
    }

    @Override
    public ZonedDateTime getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return gson.fromJson(callableStatement.getString(columnIndex), ZonedDateTime.class);
    }
}
