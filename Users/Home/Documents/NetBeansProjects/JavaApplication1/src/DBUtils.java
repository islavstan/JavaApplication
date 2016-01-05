
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBUtils {
    private static Connection con;
    public static void openConnection(String path){
           try {
            //динамическая регистрация драйвера sqllite
            Driver driver = (Driver)Class.forName("org.sqlite.JDBC").newInstance();
            //создание подключения по пути указанному в урле
            String url ="jdbc:sqlite:"+path;
            if(con==null){
            con =DriverManager.getConnection(url);}

    }catch (SQLException|ClassNotFoundException |InstantiationError |IllegalAccessException|InstantiationException ex){
        
    }}
           public static ArrayList <SPRObject>getResultList(String sql){
               ArrayList<SPRObject>list =new ArrayList<>();
               Statement statement=null;
               ResultSet resSet=null;
               try{
                   statement=con.createStatement();
                   resSet=statement.executeQuery(sql);
                   while(resSet.next()){
                       SPRObject obj=new SPRObject();
                       obj.setId(resSet.getInt("id"));
                       obj.setName(resSet.getString("name"));
                       list.add(obj);
                       
                   }
               }catch(SQLException ex){
                   
               }
               try{
                   resSet.close();
                   
                 statement.close();
                 
               }catch(SQLException e){
                   
               }return list;
           }
           
           
           public static void closeConnection(){
               try{
                   if(con!=null){
                       con.close();
                   }
               }catch(SQLException sq){
                   
               }
           }
           }

