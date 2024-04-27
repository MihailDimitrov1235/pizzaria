/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pizzaria;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author might
 */
public class DeliveryHome extends JFrame {
    private JButton[] orderButtons;
    private JCheckBox[] deliveryCheckboxes;
    private JLabel[] orderLabels;
    private OrderClass[] orders;

    public DeliveryHome(int userID) {
        setTitle("Orders");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        OrdersServices os = new OrdersServices();
        this.orders = os.GetUndeliveredOrders();

        JPanel mainPanel = new JPanel(new GridLayout(orders.length + 1, 2));

        // Back button
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setSize(100, 30);
        JButton backButton = new JButton("Back");
        backButtonPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn obj = new LogIn();
                obj.setVisible(true);
                dispose();
            }
        });
        mainPanel.add(backButtonPanel);
        mainPanel.add(new JLabel()); // Empty space

        orderButtons = new JButton[orders.length];
        deliveryCheckboxes = new JCheckBox[orders.length];
        orderLabels = new JLabel[orders.length];

        for (int i = 0; i < orders.length; i++) {
            OrderClass order = orders[i];
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align button to the left
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add margin to the button panel

            JButton button = new JButton("Order " + order.getId());
            button.setPreferredSize(new Dimension(80, 20)); // Set smaller button size
            button.addActionListener(new OrderButtonListener(order , userID));
            orderButtons[i] = button;

            JCheckBox checkBox = new JCheckBox("Delivered");
            checkBox.setSelected(order.isDelivered());
            checkBox.setEnabled(false); // Make checkbox unchangeable
            deliveryCheckboxes[i] = checkBox;

            JLabel label = new JLabel("Date: " + order.getDate());
            orderLabels[i] = label;

            buttonPanel.add(button); // Add button to the button panel
            buttonPanel.add(checkBox);
            buttonPanel.add(label);
            mainPanel.add(buttonPanel); // Add button panel to the main panel
//            mainPanel.add(checkBox);
//            mainPanel.add(label);
        }

        add(mainPanel);
        setVisible(true);
    }

    private class OrderButtonListener implements ActionListener {
        private OrderClass order;
        private int userID;

        public OrderButtonListener(OrderClass order, int userID) {
            this.order = order;
            this.userID = userID;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Open OrderDetailsFrame with the corresponding order ID
            OrderDetailsFrame obj = new OrderDetailsFrame(this.order, this.userID, true);
            obj.setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        // Example usage
        SwingUtilities.invokeLater(() -> new DeliveryHome(1)); 
    }
}
