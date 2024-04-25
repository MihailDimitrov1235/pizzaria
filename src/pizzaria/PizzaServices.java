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
    
    public int createPizza(String name, float price) {
        String query = "INSERT INTO `pizzas`(`name`, `price`) VALUES (?,?)";
        try (PreparedStatement ps = this.conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setFloat(2, price);

            int affectedRows = ps.executeUpdate();
            
            System.out.println(affectedRows);

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return 0;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    
}
