
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Lesson {
    public static void main(String[]args){
        Connection con =null;//хранит соединение с базой данных
        Statement stmt=null;//хранит и выполняет sql запросы
        ResultSet result = null;//получает результаты sql запросов
        try {
            //динамическая регистрация драйвера sqllite
            Driver driver = (Driver)Class.forName("org.sqlite.JDBC").newInstance();
            //создание подключения по пути указанному в урле
            String url ="jdbc:sqlite:c:\\\\Users\\\\Home\\\\Documents\\\\CarShop";
            con =DriverManager.getConnection(url);
            //подготовка SQL запроса
            String sql="SELECT * FROM sp_Brand";
            stmt=con.createStatement();
            //выполнение SQL запроса
            result=stmt.executeQuery(sql);
            //обработка результатов
            while(result.next()){
                System.out.println(result.getInt("id")+ ":"+ result.getString("name") );
                
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Lesson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Lesson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Lesson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Lesson.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                if(result!=null)result.close();
                if(stmt!=null)stmt.close();
                if(con!=null)con.close();
            }catch(Exception ex){
                
            }
        }
        
    }
}
