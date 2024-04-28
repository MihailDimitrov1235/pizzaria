/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

/**
 *
 * @author might
 */
public class Pizzaria {

    public static void main(String[] args) {

        try {
            musicPlayer mp = new musicPlayer();
            mp.play();
        } catch (Exception e) {
            System.out.println("Problem with music player");
            System.out.println(e);
        }
        
        Conn c = new Conn();
        c.initDatabase();

        LogIn obj = new LogIn();
        obj.setVisible(true);
    }
}
