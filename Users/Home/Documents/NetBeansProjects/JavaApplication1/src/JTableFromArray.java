
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class JTableFromArray {
    public static void main(String[]args){
        Object[]columnHeader={"Фамилия","Имя","Отчество"};//массив с названиями столбцов
        //массив с данными для таблиц
        Object[][]tableData={
        {"Иванов","Иван","Иванович"},
                {"Петров","Пётр","Петрович"},
            {"Сергеев","Сергей","Сергеевич"},
            {"Горобец","Станислав","Олегович"},
            {"Ильин","Илья","Степанович"}};
        
        JFrame frame=new JFrame("Пример данных из таблиц");
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTable tableFIO = new JTable(tableData,columnHeader);
        JScrollPane pane=new JScrollPane(tableFIO);
        //размеры прокручиваемой области
        tableFIO.setPreferredScrollableViewportSize(new Dimension(400,200));
        frame.getContentPane().add(pane);
        frame.setVisible(true);
        }
    }
    

