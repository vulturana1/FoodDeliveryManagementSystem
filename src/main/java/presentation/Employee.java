package presentation;

import business.DeliveryService;
import business.Order;
import business.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Employee implements Observer {

    public JFrame frame;
    private JPanel jPanel;
    private ArrayList<OrderBox> orderBoxes;
    DeliveryService deliveryService;

    public Employee(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        orderBoxes = new ArrayList<OrderBox>();
        frame = new JFrame("Employee");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        jPanel = new JPanel(new GridLayout(0, 2));
        JScrollPane scrollPanel = new JScrollPane(jPanel);
        scrollPanel.setPreferredSize(new Dimension(600, 400));
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.ipady = 10;

        gridBagConstraints.ipady = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.CENTER;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;

        panel.add(scrollPanel, gridBagConstraints);
        gridBagConstraints.gridy = 1;
        frame.add(panel);
        frame.pack();
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        ArrayList<MenuItem> products = deliveryService.orders.get(order);
        OrderBox newBox = new OrderBox(order, products);
        orderBoxes.add(newBox);
        jPanel.add(newBox.getScrollablePane());
        newBox.getScrollablePane().setPreferredSize(new Dimension(200, 200));
        jPanel.revalidate();
    }
}
