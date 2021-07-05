package presentation;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

import business.BaseProduct;
import business.DeliveryService;
import data.Serializator;

import javax.swing.text.DateFormatter;
import data.Serializator;

public class Administrator extends JFrame {

    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;

    DeliveryService deliveryService = new DeliveryService();

    public Administrator(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        this.setTitle("Administrator");
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 509, 290);

        JButton btnMenu = new JButton("View menu");
        btnMenu.setBounds(180, 23, 142, 21);
        btnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMenu menu = new ViewMenu(deliveryService.products);
                Serializator.serialization(deliveryService);
            }
        });
        getContentPane().add(btnMenu);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(26, 55, 45, 13);
        getContentPane().add(lblTitle);

        JLabel lblRating = new JLabel("Rating:");
        lblRating.setBounds(92, 55, 45, 13);
        getContentPane().add(lblRating);

        JLabel lblCalories = new JLabel("Calories:");
        lblCalories.setBounds(162, 55, 45, 13);
        getContentPane().add(lblCalories);

        JLabel lblProtein = new JLabel("Protein:");
        lblProtein.setBounds(228, 54, 45, 13);
        getContentPane().add(lblProtein);

        JLabel lblFat = new JLabel("Fat:");
        lblFat.setBounds(306, 55, 45, 13);
        getContentPane().add(lblFat);

        JLabel lblSodium = new JLabel("Sodium:");
        lblSodium.setBounds(361, 55, 63, 13);
        getContentPane().add(lblSodium);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(434, 55, 45, 13);
        getContentPane().add(lblPrice);

        textField = new JTextField();
        textField.setBounds(10, 78, 59, 19);
        getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(79, 78, 59, 19);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(148, 78, 59, 19);
        getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(217, 78, 59, 19);
        getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(286, 78, 59, 19);
        getContentPane().add(textField_4);

        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(355, 78, 59, 19);
        getContentPane().add(textField_5);

        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(424, 78, 59, 19);
        getContentPane().add(textField_6);

        JButton btnAdd = new JButton("Add product");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseProduct product = new BaseProduct(textField.getText(), Float.parseFloat(textField_1.getText()),
                        Integer.parseInt(textField_2.getText()), Integer.parseInt(textField_3.getText()),
                        Integer.parseInt(textField_4.getText()), Integer.parseInt(textField_5.getText()),
                        Integer.parseInt(textField_6.getText()));

                deliveryService.addProduct(product);
                Serializator.serialization(deliveryService);
                JOptionPane.showMessageDialog(null, "Product added.", null, JOptionPane.INFORMATION_MESSAGE);
            }
        });
        btnAdd.setBounds(26, 107, 114, 21);
        getContentPane().add(btnAdd);

        JButton btnDelete = new JButton("Delete product");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(deliveryService.deleteProduct(textField.getText())){
                    Serializator.serialization(deliveryService);
                    JOptionPane.showMessageDialog(null, "Product deleted.", null, JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Product not found.", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btnDelete.setBounds(169, 107, 125, 21);
        getContentPane().add(btnDelete);

        JButton btnModify = new JButton("Modify product");
        btnModify.setBounds(320, 107, 140, 21);
        btnModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(deliveryService.updateProduct(textField.getText(), Integer.parseInt(textField_6.getText()))){
                    Serializator.serialization(deliveryService);
                    JOptionPane.showMessageDialog(null, "Product modified.", null, JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Product not found.", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        getContentPane().add(btnModify);

        textField_7 = new JTextField();
        textField_7.setBounds(10, 161, 87, 19);
        getContentPane().add(textField_7);
        textField_7.setColumns(10);

        JLabel lblProd = new JLabel("Composed product name:");
        lblProd.setBounds(26, 138, 59, 13);
        getContentPane().add(lblProd);

        JLabel lblProd1 = new JLabel("Product 1:");
        lblProd1.setBounds(122, 138, 59, 13);
        getContentPane().add(lblProd1);

        JLabel lblProd2 = new JLabel("Product 2:");
        lblProd2.setBounds(228, 138, 59, 13);
        getContentPane().add(lblProd2);

        JLabel lblProd3 = new JLabel("Product 3:");
        lblProd3.setBounds(320, 138, 59, 13);
        getContentPane().add(lblProd3);

        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(107, 161, 87, 19);
        getContentPane().add(textField_8);

        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(204, 161, 87, 19);
        getContentPane().add(textField_9);

        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(303, 161, 87, 19);
        getContentPane().add(textField_10);

        JButton btnCreate = new JButton("Create");
        btnCreate.setBounds(400, 160, 85, 21);
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryService.createCompositeProduct(textField_7.getText(), textField_8.getText(), textField_9.getText(), textField_10.getText());
                Serializator.serialization(deliveryService);
                JOptionPane.showMessageDialog(null, "Composite product created.", null, JOptionPane.INFORMATION_MESSAGE);
            }
        });
        getContentPane().add(btnCreate);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(119, 205, 114, 21);
        comboBox.addItem("Select report");
        comboBox.addItem("Report 1");
        comboBox.addItem("Report 2");
        comboBox.addItem("Report 3");
        comboBox.addItem("Report 4");
        getContentPane().add(comboBox);

        JButton btnGenerate = new JButton("Generate");
        btnGenerate.setBounds(243, 205, 120, 21);
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem().toString().equals("Report 1")) {
                	Report1 r = new Report1(deliveryService);
                    
                } else {
                    if (comboBox.getSelectedItem().toString().equals("Report 2")) {
                    	Report2 r = new Report2(deliveryService);
                    } else {
                        if (comboBox.getSelectedItem().toString().equals("Report 3")) {
                           Report3 r = new Report3(deliveryService);
                        } else {
                            Report4 r = new Report4(deliveryService);
                        }
                    }
                }
            }
        });
        getContentPane().add(btnGenerate);
        this.setVisible(true);
    }
}
