package presentation;

import business.DeliveryService;
import business.User;
import data.Serializator;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame {
    private JTextField textField;
    private JPasswordField passwordField;

    Employee employee;
    public int employeeView = 1;

    public Login(DeliveryService deliveryService) {
        getContentPane().setLayout(null);
        employee = new Employee(deliveryService);

        deliveryService.addObserver(employee);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setTitle("Food Delivery Management System");
        this.setBounds(100, 100, 500, 230);
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(216, 25, 73, 43);
        getContentPane().add(lblNewLabel);

        JButton btnAdmin = new JButton("Administrator");
        btnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAdmin.setBounds(40, 148, 117, 21);
        btnAdmin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = new String(passwordField.getPassword());
                if (textField.getText().equals("admin") && pass.equals("admin")) {
                    Administrator a = new Administrator(deliveryService);
                } else {
                    JOptionPane.showMessageDialog(null, "User not found.", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        getContentPane().add(btnAdmin);

        JButton btnClient = new JButton("Client");
        btnClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnClient.setBounds(190, 148, 117, 21);
        btnClient.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int found = 0;
                String pass = new String(passwordField.getPassword());
                if (deliveryService.users.isEmpty()) {
                    deliveryService.registerUser(textField.getText(), pass);
                    JOptionPane.showMessageDialog(null, "Client registered.", null, JOptionPane.INFORMATION_MESSAGE);
                }
                for (User i : deliveryService.users) {
                    if (i.getUsername().equals(textField.getText()) && i.getPassword().equals(pass)) {
                        Client c = new Client(deliveryService, i);
                        found = 1;
                    }
                }
                if (found == 0) {
                    JOptionPane.showMessageDialog(null, "User not found.", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });
        getContentPane().add(btnClient);

        JButton btnEmployee = new JButton("Employee");
        btnEmployee.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (employeeView == 1) {
                    employee.frame.setVisible(true);
                    employeeView = 0;
                } else {
                    employee.frame.dispose();
                    employeeView = 1;
                }
            }
        });
        btnEmployee.setBounds(335, 148, 117, 21);
        getContentPane().add(btnEmployee);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblUsername.setBounds(40, 80, 73, 13);
        getContentPane().add(lblUsername);

        JLabel lblPassw = new JLabel("Password");
        lblPassw.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblPassw.setBounds(40, 110, 73, 13);
        getContentPane().add(lblPassw);

        textField = new JTextField();
        textField.setBounds(112, 77, 128, 19);
        getContentPane().add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(112, 103, 128, 19);
        passwordField.setEchoChar('*');
        getContentPane().add(passwordField);
        this.setVisible(true);
    }
}
