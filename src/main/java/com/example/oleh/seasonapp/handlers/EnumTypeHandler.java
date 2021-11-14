package com.example.oleh.seasonapp.handlers;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.ibatis.type.ArrayTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * Handler to provide correct storing and reading Arrays of enums
 * To trigger it, this class has to de defined as handler for concrete enum in
 * CBMConfiguration.xml
 *
 * @author oleh.halushko
 *         Created 30.12.2020.
 */
public class EnumTypeHandler<T extends Enum<T>> extends ArrayTypeHandler {

  private Class<T> t;

  public EnumTypeHandler(Class<T> t) {
    this.t = t;
  }

  public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
      throws SQLException {
    if (parameter instanceof Enum<?>[]) {
      Collection<Enum<?>> enumArr = Arrays.asList((Enum<?>[]) parameter);
      Object[] objArray = enumArr.stream().map(Enum::name).toArray();
      Array arr = ps.getConnection().createArrayOf("varchar", objArray);
      ps.setArray(i, arr);
    }
    else if (parameter instanceof Enum<?>) {
      ps.setObject(i, ((Enum<?>) parameter).name());
    }

    else {
      System.out.println("gg");
    }
  }

  @Override
  public Object getResult(ResultSet rs, String columnName) throws SQLException {
    String[] strings = (String[]) rs.getArray(columnName).getArray();
    if (strings.length == 0) {
      return java.lang.reflect.Array.newInstance(t, 0);
    }

    List<T> list = Stream.of(strings).map(a -> T.valueOf(t, a)).collect(Collectors.toList());
    return list
        .toArray((T[]) java.lang.reflect.Array.newInstance(list.get(0).getClass(), list.size()));
  }

}
