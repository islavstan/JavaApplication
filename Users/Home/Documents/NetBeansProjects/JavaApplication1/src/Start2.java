
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class Start2 {
    public static void main(String[] args) {
        try{
            Connection con=SQLiteConnection.getConnection();
            TableModel mod=new MyTableModel(con,"Carcc");
            JTable jTable=new JTable(mod);
            
            //добавляем сортировщик
            TableRowSorter<TableModel>sorter=new TableRowSorter<TableModel>(mod);
            //здесь указываем что для 2го столбца используем наш компаратор, отсчёт идёт с 0
            sorter.setComparator(2, new MyComparator());
            jTable.setRowSorter(sorter);
            
           
            
            jTable.setDefaultRenderer(Object.class, new MyTableRenderer());
            JScrollPane pane=new JScrollPane(jTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            JFrame frame=new JFrame("Загрузка данных в JTable");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(pane);
            frame.pack();
            frame.setVisible(true);
            con.close();
        }catch(Exception e){e.printStackTrace();}
                    
        }
    }

