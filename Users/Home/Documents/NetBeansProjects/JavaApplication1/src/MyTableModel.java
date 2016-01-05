
import com.sun.javafx.image.impl.IntArgb;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class MyTableModel extends AbstractTableModel{

   

   
    private Object[][]contents;//хранит данные
    private String[] columnNames;//хранит имена столбцов
    private Class[] columnClasses;//хранит типы столбцов
    public MyTableModel(Connection con, String tableName)throws SQLException{
        super();
        getTableContents(con, tableName);
        
        
    }
    private void getTableContents(Connection con,String tableName)throws SQLException{
        //позволяет отображать методанные по какой либо таблице
        DatabaseMetaData meta=con.getMetaData();
        ResultSet rs=meta.getColumns(null, null, tableName, null);//получить методанные по столбцам
        ArrayList colNameList=new ArrayList();//список имён столбцов
        ArrayList colTypeList=new ArrayList();//список типов столбцов
        //цикл по всем столбцам таблицы
        //для каждого столбца определить имя и тип
        while (rs.next()){
            colNameList.add(rs.getString("COLUMN_NAME"));//добавить в список имя столбца
            int dbType=rs.getInt("DATA_TYPE");//определить тип столбца
            //выбрать нужный тип
            switch(dbType){
                case Types.INTEGER:
                    colTypeList.add(Integer.class);
                    break;
                 case Types.FLOAT:
                    colTypeList.add(Float.class);
                    break;
                 case Types.DOUBLE:
                 case Types.REAL:
                    colTypeList.add(Double.class);
                    break; 
                 case Types.DATE:
                 case Types.TIME:
                 case Types.TIMESTAMP:
                     colTypeList.add(java.sql.Date.class);
                     break;
                     default:
                         colTypeList.add(String.class);
                         break;
                         
            };
            
        }
    //имена столбцов сохранить в отдельный массив columnNames
    columnNames=new String[colNameList.size()];
    colNameList.toArray(columnNames);
    //типы столбцов сохраняем в отдельный массив columnTypes
    columnClasses=new Class[colTypeList.size()];
    colTypeList.toArray(columnClasses);
        Statement statement=con.createStatement();
        rs=statement.executeQuery("SELECT * FROM "+ tableName);
        ArrayList rowList=new ArrayList();//хранит записи из таблиц
        // цикл по всем записям таблицы
        while(rs.next()){
            ArrayList cellList=new ArrayList();//хранит данные по каждому столбцу (ячейки)
            for(int i=0;i<columnClasses.length;i++){
                Object cellValue=null;
                if(columnClasses[i]==String.class)cellValue=rs.getString(columnNames[i]);
                else if(columnClasses[i]==Integer.class)cellValue=new Integer(rs.getInt(columnNames[i]));
                 else if(columnClasses[i]==Float.class)cellValue=new Float(rs.getInt(columnNames[i]));
                 else if(columnClasses[i]==Double.class)cellValue=new Double(rs.getInt(columnNames[i]));
                 else if(columnClasses[i]==java.sql.Date.class)cellValue=rs.getDate(columnNames[i]);
                 else System.out.println("Не могу определить тип поля "+columnNames[i]);
                cellList.add(cellValue);          
            }
            Object[]cells=cellList.toArray(); //переводим в массив и добавляем в rowList
            rowList.add(cells);   
        }
        contents=new Object[rowList.size()][];
        for(int i=0;i<contents.length;i++){
            contents[i]=(Object[])rowList.get(i);
        }
        //закрываем соединение
        rs.close();
        statement.close();
        
    }

 @Override
 //возвращает кол-во строк, в данном случае кол-во строк это длина массива с данными
    public int getRowCount() {
        return contents.length;
    }

    @Override
    public int getColumnCount() {
       if(contents.length==0){
           return 0;}
       else {
           return contents[0].length;
       }
         
       }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       return contents[rowIndex][columnIndex];
    }
    
 

    @Override
    public Class getColumnClass(int columnIndex) {
       return columnClasses[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }}
        
    
    

   