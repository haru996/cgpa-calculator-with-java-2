package cgpa;

import java.sql.*;
import javax.swing.*;//Retrieved from https://www.youtube.com/watch?v=JuYb-VZaD1g
public class Mysql { //This is the class to connect the database into the java
Connection conn = null;

public static Connection ConnectSQL() throws ClassNotFoundException, SQLException{
    try{
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:subject.sqlite"); //Connect the table data of the database
        JOptionPane.showMessageDialog(null, "Connect"); //This message will show if the database is connected to java
        
        return conn;
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    return null;
}
}
