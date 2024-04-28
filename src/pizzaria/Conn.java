/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

import java.io.*;
import java.sql.*;
import java.util.Arrays;

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
    
    private static String readFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }
    
    public void initDatabase(){
    
        String sqlFilePath = "database.sql";

        try {
            String[] sqlCommands = readFile(sqlFilePath).split(";"); 

            Statement statement = this.c.createStatement();
            for (String sqlCommand : sqlCommands) {
                statement.addBatch(sqlCommand);
            }

            statement.executeBatch();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
        
    
//    public static void main(String[] args) {
//        Conn c = new Conn();
//        System.out.println(c.getConnection());
//        
//    }
}
