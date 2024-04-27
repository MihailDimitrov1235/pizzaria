/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

import java.text.DecimalFormat;

/**
 *
 * @author might
 */
public class OrderClass {
    private int id;
    private boolean delivered;
    private String date;
    private Pizza[] pizzas;
    private DecimalFormat df; 

    public OrderClass() {
        this.df = new DecimalFormat("#.##");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Pizza[] getPizzas() {
        return pizzas;
    }

    public void setPizzas(Pizza[] pizzas) {
        this.pizzas = pizzas;
    }
    
    @Override
    public String toString(){
        return id + " " + delivered + " " + date + "\n" + pizzas[0].toString();
    }
    
    public String pizzasToString(){
        String result = "";
        for(Pizza pizza : this.pizzas){
            result += pizza.getName() + " " + df.format(pizza.getPrice()) + " x " + pizza.getQuantity() + "\n";
        }
        System.out.println(result);
        return result;
    }
    
    public float getTotal(){
        float result = 0;
        for(Pizza pizza : this.pizzas){
            result += pizza.getPrice() * pizza.getQuantity();
        }
        return result;
    }
    
}
