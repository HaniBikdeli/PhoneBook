import java.sql.*;
//import com.microsoft.sqlserver.jdbc.SQLServerDriver;


public class DataSource extends PhoneBookHP{

    public static void dbConnection() throws SQLException{
        String sql =
                "Use PhoneBook" +
                " insert into dbo.users (username, password, access) values ('userC' , '1234c' , '2')";
        ResultSet rs = statement.executeQuery(sql);
        System.out.println("connected to data source");
    }
    public static void main(String[] args) throws SQLException {
        dbConnection();
    }
}
