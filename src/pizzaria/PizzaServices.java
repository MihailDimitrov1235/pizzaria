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
public class PizzaServices {
    
    Conn conn = new Conn();
    
    public Pizza[] getPizzas() {
        String query = "SELECT * FROM pizzas";
        String countQ = "SELECT COUNT(*) FROM pizzas";
        try {
            Statement st = this.conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(countQ);
            int rowcount = 0;
            if(rs.next()){
                rowcount = rs.getInt("Count(*)");
            }
            Pizza[] ps = new Pizza[rowcount];
            int i = 0;
            Statement st2 = this.conn.getConnection().createStatement();
            ResultSet rs2 = st.executeQuery(query);
            while(rs2.next()){
                ps[i] = new Pizza(rs2.getString("name"), rs2.getFloat("price"), rs2.getInt("id"));
                i++;
            }
            return ps;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
}
