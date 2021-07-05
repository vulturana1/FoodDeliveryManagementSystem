package presentation;

import business.CompositeProduct;
import business.*;
import business.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderBox {

    private JScrollPane scrollPane;
    private Order order;
    private ArrayList<MenuItem> content;
    public Box box;

    public OrderBox(Order order, ArrayList<MenuItem> content) {
        this.order = order;
        this.content = content;
        box = Box.createVerticalBox();
        JLabel orderNumber = new JLabel("Order number: " + order.getOrderID());
        orderNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
        orderNumber.setForeground(Color.BLUE);
        box.add(orderNumber);
        JLabel prod = new JLabel("Products: ");
        prod.setFont(new Font("Tahoma", Font.BOLD, 14));
        box.add(prod);
        int s = 0;
        for (MenuItem m : content) {
            s = s+m.getPrice();
            JLabel temp = new JLabel("   " + m.getTitle());
            temp.setFont(new Font("Tahoma", Font.PLAIN, 13));
            box.add(temp);
            if (m instanceof CompositeProduct) {
                for (MenuItem item : ((CompositeProduct) m).getItems()) {
                    JLabel subTemp = new JLabel("   " + item.getTitle());
                    subTemp.setFont(new Font("Tahoma", Font.PLAIN, 13));
                    box.add(subTemp);
                }
            }
        }
        JLabel price = new JLabel("\nTotal price: " + s);
        price.setFont(new Font("Tahoma", Font.BOLD, 14));
        box.add(price);
        scrollPane = new JScrollPane(box);
        scrollPane.setPreferredSize(new Dimension(250, 250));
    }

    public JScrollPane getScrollablePane() {
        return scrollPane;
    }

    public Order getOrder() {
        return order;
    }
}
