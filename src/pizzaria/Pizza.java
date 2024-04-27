/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mihailvd
 */
class Pizza {
    private String name;
    private int id;
    private double price;
    private int quantity;
    private DecimalFormat df = new DecimalFormat("#.00");

    public Pizza(String name,  double price,int id) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = 0;
    }
    
    public Pizza() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }
    
    @Override
    public String toString() {
        return this.name + " " + this.getPrice() + " " + this.getQuantity() + "\n";
    }
    
    public JPanel createPizzaPanel(java.lang.Runnable updateTotal) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel nameLabel = new JLabel(this.getName() + " - $" + df.format(this.getPrice()) + " x " + this.getQuantity());
        panel.add(nameLabel);

        JButton plusButton = new JButton("+");
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                increaseQuantity();
                nameLabel.setText(getName() + " - $" + df.format(getPrice()) + " x " + getQuantity());
                updateTotal.run();
            }
        });
        panel.add(plusButton);

        JButton minusButton = new JButton("-");
        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decreaseQuantity();
                if (getQuantity() >= 0) {
                    nameLabel.setText(getName() + " - $" + df.format(getPrice()) + " x " + getQuantity());
                    updateTotal.run();
                }
            }
        });
        panel.add(minusButton);
        
        plusButton.setMargin(new Insets(2, 5, 2, 5));
        minusButton.setMargin(new Insets(2, 5, 2, 5));

        panel.add(Box.createRigidArea(new Dimension(10, 0))); // Add a gap between the buttons and the text


        return panel;
    }
}