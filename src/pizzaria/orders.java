/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pizzaria;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
/**
 *
 * @author mihailvd
 */
public class orders extends JFrame {
    private JTextArea displayArea;
    private double totalSum;
    private DecimalFormat df = new DecimalFormat("#.00");

    public orders( int userID) {
        
        musicPlayer mp = new musicPlayer();
    
        mp.play();
        
        PizzaServices pser = new PizzaServices();
        Pizza[] pizzas = pser.getPizzas();
        
        Runnable updateTotal = () -> {
            totalSum = 0;
            for (Pizza pizza : pizzas) {
                totalSum += pizza.getPrice() * pizza.getQuantity();
            }
            displayArea.setText("Total: $" + df.format(totalSum));
        };
        
        
        setTitle("Pizza Order");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pizzaPanel = new JPanel();
        pizzaPanel.setLayout(new BoxLayout(pizzaPanel, BoxLayout.Y_AXIS));

        for (Pizza pizza : pizzas) {
            JPanel panel = pizza.createPizzaPanel(updateTotal);
            pizzaPanel.add(panel);
            totalSum += pizza.getPrice() * pizza.getQuantity();
        }

        JScrollPane scrollPane = new JScrollPane(pizzaPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton printButton = new JButton("Order");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrdersServices oserv = new OrdersServices();
                int created = oserv.CreateOrder(pizzas, userID);
                if(created == 0){
                    home obj = new home(userID);
                    obj.setVisible(true);
                    dispose();
                }
            }
        });
        buttonPanel.add(printButton);
        add(buttonPanel, BorderLayout.SOUTH);

        displayArea = new JTextArea();
        displayArea.append("Total: $" + totalSum);
        buttonPanel.add(displayArea);
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new orders(1).setVisible(true);
            }
        });
    }

}
