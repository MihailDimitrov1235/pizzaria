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
        String query = "INSERT INTO `orders`(`user_id`, `order_date`, `delivered`) VALUES (?,?,false)";
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

    OrderClass[] GetUserOrders(int userID) {
        String countQuery = "SELECT Count(*) FROM `orders` WHERE `user_id` LIKE ?";
        String query = "SELECT * FROM `orders` WHERE `user_id` LIKE ?";
        String detailsQuery = "SELECT p.id ,p.name,  p.price, od.quantity FROM orders AS o JOIN orderdetails AS od ON o.id = od.order_id JOIN pizzas AS p ON od.pizza_id = p.id WHERE o.user_id LIKE ? AND o.id LIKE ?";
        String countDetailsQuery = "SELECT Count(*) FROM orders AS o JOIN orderdetails AS od ON o.id = od.order_id JOIN pizzas AS p ON od.pizza_id = p.id WHERE o.user_id LIKE ? AND o.id LIKE ?";

        int rowCount = 0;

        try (PreparedStatement ps = this.conn.getConnection().prepareStatement(countQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                rowCount = rs.getInt("Count(*)");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        if (rowCount == 0) {
            return new OrderClass[0];
        }

        try (PreparedStatement ps = this.conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            OrderClass[] result = new OrderClass[rowCount];
            int i = 0;
            while (rs.next()) {
                int o_id = rs.getInt("id");
                result[i] = new OrderClass();
                result[i].setId(rs.getInt("id"));
                result[i].setDelivered(rs.getBoolean("delivered"));
                result[i].setDate(rs.getString("order_date"));
                
                int pizzasCount = 0;

                try (PreparedStatement ps2 = this.conn.getConnection().prepareStatement(countDetailsQuery, Statement.RETURN_GENERATED_KEYS)) {
                    ps2.setInt(1, userID);
                    ps2.setInt(2, o_id);
                    ResultSet rs2 = ps2.executeQuery();

                    if (rs2.next()) {
                        pizzasCount = rs2.getInt("Count(*)");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                Pizza[] pizzas = new Pizza[pizzasCount];
                try (PreparedStatement ps2 = this.conn.getConnection().prepareStatement(detailsQuery, Statement.RETURN_GENERATED_KEYS)) {
                    ps2.setInt(1, userID);
                    ps2.setInt(2, o_id);
                    ResultSet rs2 = ps2.executeQuery();
                    
                     
                    int j = 0;
                    while(rs2.next()) {
                        pizzas[j] = new Pizza();
                        pizzas[j].setId(rs2.getInt("id"));
                        pizzas[j].setName(rs2.getString("name"));
                        pizzas[j].setPrice(rs2.getFloat("price"));
                        pizzas[j].setQuantity(rs2.getInt("quantity"));
                        j++;
                    }

                } catch (Exception e) {
                    System.out.println("pizzas");
                    System.out.println(e);
                }
                
                result[i].setPizzas(pizzas);
                
                System.out.println(result[i].toString());
                i++;
            }
            
            return result;

        } catch (Exception e) {
                    System.out.println("orders");
            System.out.println(e);
        }
        return new OrderClass[0];

    }
    
    public static void main(String[] args) {
        OrdersServices os = new OrdersServices();
        os.GetUserOrders(1);
        
    }
}
