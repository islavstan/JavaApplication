
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;


public class Start {
     public static void main(String[] args) {
      try{
      Scanner scanner= new Scanner(new FileInputStream("C:\\stas\\sql.txt"),"UTF-8");
      String dbPath =scanner.nextLine();
         System.out.println("Базза данных "+dbPath);
         
         if (! new File(dbPath).exists()){
         System.out.println("Файл баз данных не найден");
         return;
     }
         DBUtils.openConnection(dbPath);
         
         StringBuilder sBuider=new StringBuilder();
         try{
         while(scanner.hasNextLine()){
             sBuider.append(scanner.nextLine());
         }}finally{
             scanner.close();
         }
         //разделяем sql запросы
         String[]sql=sBuider.toString().split(";");
         StringBuilder resultBuilder = new StringBuilder();
         for(String sqlStr:sql){
             resultBuilder.append("запрос "+sqlStr+"\n");
             resultBuilder.append("Результат "+"\n");
             ArrayList<SPRObject>list=DBUtils.getResultList(sqlStr);
             for(SPRObject o:list){
                 resultBuilder.append(o.getId()+","+o.getName());
             }
             //просто разделение, переход на новую строку
            // resultBuilder.append("/n");
         }
         System.out.println(resultBuilder.toString());
         writeTextToFile(resultBuilder.toString());
         
     
         DBUtils.closeConnection();
    }catch(FileNotFoundException ex){}
     }
//запись текстовых данных в файл
    private static void writeTextToFile(String str) {
        try{
            Writer wr=new FileWriter("C:\\stas\\result.txt") ;
            wr.write(str);
            wr.flush();
            wr.close();}
        catch(IOException ex){}
}
        }
    
       

