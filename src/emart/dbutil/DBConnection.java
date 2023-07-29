
package emart.dbutil;
import java.sql.*;
import javax.swing.*;


public class DBConnection {
    private static Connection conn;
    
   static{
       try{
           Class.forName("oracle.jdbc.OracleDriver");
           conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-B41S5D8:1521/XE","grocery","grocery");
           JOptionPane.showMessageDialog(null, "Connected SucesssFully");
           
    
}
       
catch(ClassNotFoundException ex){
    JOptionPane.showMessageDialog(null, "Class Not Found");
     ex.printStackTrace();
     System.exit(1);
    
}
catch(SQLException ex){
    JOptionPane.showMessageDialog(null, "Error in Db");
     ex.printStackTrace();
     System.exit(1);
    
}
   }
   public static Connection getConnection(){
       return conn;
   }
   public static void  closeConnection(){
       try{
           conn.close();
            JOptionPane.showMessageDialog(null," Closed SuccessFuly");
           
       }
       catch(SQLException ex){
           JOptionPane.showMessageDialog(null," not Closed SuccessFuly");
           ex.printStackTrace();
       }
   }
    public static void main(String[] args) {
        
    }
    
}
