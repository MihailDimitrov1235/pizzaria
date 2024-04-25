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

    int CreateOrder(Pizza[] pizzas, int userID) {
        String query = "INSERT INTO `orders`(`user_id`, `order_date`) VALUES (?,?)";
        String query2 = "INSERT INTO `orderdetails`(`order_id`, `pizza_id`, `quantity`) VALUES (?,?,?)";

        try (PreparedStatement ps = this.conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userID);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            int affectedRows = ps.executeUpdate();

            System.out.println(affectedRows);

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int orderID = rs.getInt(1);
                    for (Pizza pizza : pizzas) {
                        if (pizza.getQuantity() > 0) {
                            System.out.println(pizza.toString());
                            try (PreparedStatement ps2 = this.conn.getConnection().prepareStatement(query2, Statement.RETURN_GENERATED_KEYS)) {
                                ps2.setInt(1, orderID);
                                ps2.setInt(2, pizza.getId());
                                ps2.setInt(3, pizza.getQuantity());

                                int affectedRows2 = ps2.executeUpdate();
                            } catch (SQLException e) {
                                System.out.println(e);
                                return -1;
                            }
                        }
                    }
                    return 0;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;

    }
}
