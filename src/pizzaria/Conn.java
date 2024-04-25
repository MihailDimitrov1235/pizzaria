/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

import java.sql.*;

/**
 *
 * @author might
 */
public class Conn {

    String username = "root";
    String password = ""; // "Npmg2022!"
    Connection c = null;

    Conn() {
        try {
            this.c = DriverManager.getConnection("jdbc:mysql://localhost/pizzaria", this.username, this.password);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    Connection getConnection(){
        return this.c;
    }
    
//    public static void main(String[] args) {
//        Conn c = new Conn();
//        System.out.println(c.getConnection());
//        
//    }
}
