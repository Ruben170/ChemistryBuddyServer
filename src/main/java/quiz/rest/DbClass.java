package quiz.rest;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.Map;


public class DbClass {

    private String url;
    private Connection connection = null;

    public DbClass(){
        url = "";
    }
    public void executeNonQuery(String procedure, Map map){
        try{
            connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(procedure);

            for (int i = 1; i <= map.size(); i++) {
                statement.setObject( i, map.get(i));
            }

            statement.executeUpdate();
            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public CachedRowSet executeQuery(String procedure, Map map) throws SQLException {
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        ResultSet resultSet;
        try{
            connection = DriverManager.getConnection(url);
            PreparedStatement statement = connection.prepareStatement(procedure);

            for (int i = 1; i <= map.size(); i++) {
                statement.setObject( i, map.get(i));
            }

            resultSet = statement.executeQuery();
            crs.populate(resultSet);
            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }
        return crs;
    }
}
