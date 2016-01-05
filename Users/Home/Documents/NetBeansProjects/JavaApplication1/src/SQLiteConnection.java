
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;


public class SQLiteConnection {
    private static Connection con;
    public static Connection getConnection(){
        try{
            //динамическая регистрация драйвера SQLite
            Driver driver=(Driver)Class.forName("org.sqlite.JDBC").newInstance();
            String url="jdbc:sqlite:c:\\\\Users\\\\Home\\\\Documents\\\\CarShop";
           if(con==null) con=DriverManager.getConnection(url);
            return con;
        }catch(Exception ex){}
        return null;
        
                   
        }
    }

