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
public class Users {

    Conn conn = new Conn();

    public ResultSet getUsers() {
        String query = "SELECT * FROM users";
        try {
            Statement st = this.conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int logIn(String username, char[] password) {
        String query = "SELECT * FROM users WHERE users.username = ? AND users.password = ?";

        try (PreparedStatement ps = this.conn.getConnection().prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, new String(password));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public int register(String username, char[] password, String email) {
        String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement ps = this.conn.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, new String(password));

            int affectedRows = ps.executeUpdate();
            
            System.out.println(affectedRows);

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
}
