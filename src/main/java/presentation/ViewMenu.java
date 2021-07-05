package presentation;

import business.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ViewMenu {

    private static JFrame frame;
    private JTable table;
    private JScrollPane sp;
    List<MenuItem> products;

    public ViewMenu(List<MenuItem> products) {

        this.products = products;
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 979, 519);

        String[] columns = { "Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price" };

        int i = 0;
        String[][] data = new String[products.size()][7];
        for (MenuItem p : products) {
            data[i][0] = p.getTitle();
            data[i][1] = String.valueOf(p.getRating());
            data[i][2] = String.valueOf(p.getCalories());
            data[i][3] = String.valueOf(p.getProteins());
            data[i][4] = String.valueOf(p.getFats());
            data[i][5] = String.valueOf(p.getSodium());
            data[i][6] = String.valueOf(p.getPrice());
            i++;
        }
        table = new JTable(data, columns);
        table.setBounds(100, 100, 494, 168);
        sp = new JScrollPane(table);
        frame.getContentPane().add(sp);
        frame.setVisible(true);
    }
}
