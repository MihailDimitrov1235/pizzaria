/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

import javax.swing.JCheckBox;

/**
 *
 * @author mihailvd
 */
class Pizza {
    private String name;
    private int id;
    private double price;
    private int quantity;

    public Pizza(String name, int id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = 0;
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
}