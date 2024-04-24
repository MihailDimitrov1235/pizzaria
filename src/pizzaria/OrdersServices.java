/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

import java.sql.*;

/**
 *
 * @author mihailvd
 */
public class OrdersServices {
    Conn conn = new Conn();
    
    void CreateOrder (Pizza[] pizzas, int userID){
        String query = "SELECT * FROM pizzas";
        String countQ = "SELECT COUNT(*) FROM pizzas";
        try {
            Statement st = this.conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
