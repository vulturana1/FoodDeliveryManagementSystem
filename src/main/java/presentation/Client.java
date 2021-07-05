package presentation;

import javax.swing.JFrame;

import business.*;
import data.Serializator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Client extends JFrame {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_7;

	List<BaseProduct> products = new ArrayList<>();
	BaseProduct b = new BaseProduct();
	private JTextField textField_8;
	private JTextField textField_9;

	ArrayList<MenuItem> order = new ArrayList<>();

	DeliveryService deliveryService = new DeliveryService();
	private JTextField textField_2;

	public Client(DeliveryService deliveryService, User user) {
		this.setTitle("Client");
		getContentPane().setLayout(null);
		this.deliveryService = deliveryService;

		JButton btnMenu = new JButton("View menu");
		btnMenu.setBounds(176, 29, 123, 21);
		getContentPane().add(btnMenu);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 509, 314);
		btnMenu.setBounds(180, 23, 142, 21);

		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewMenu menu = new ViewMenu(deliveryService.products);
				Serializator.serialization(deliveryService);
			}
		});
		getContentPane().add(btnMenu);

		JLabel lblNewLabel = new JLabel("Product name:");
		lblNewLabel.setBounds(34, 134, 88, 13);
		getContentPane().add(lblNewLabel);

		textField_7 = new JTextField();
		textField_7.setBounds(14, 157, 155, 19);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);

		JButton btnNewButton = new JButton("Add product");
		btnNewButton.setBounds(180, 156, 142, 21);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deliveryService.addOrderItem(textField_7.getText(), user.getId());
			}
		});
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Finish order");
		btnNewButton_1.setBounds(341, 156, 138, 21);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent es) {
				try {
					Serializator.serialization(deliveryService);
					deliveryService.createOrder(user.getId(), deliveryService.items);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				deliveryService.clearItems();
			}
		});
		getContentPane().add(btnNewButton_1);

		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setBounds(34, 54, 45, 13);
		getContentPane().add(lblTitle);

		JLabel lblRating = new JLabel("Rating:");
		lblRating.setBounds(162, 54, 45, 13);
		getContentPane().add(lblRating);

		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(265, 54, 45, 13);
		getContentPane().add(lblPrice);

		textField = new JTextField();
		textField.setBounds(10, 78, 125, 19);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(155, 78, 73, 19);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_2 = new JButton("Search product");
		btnNewButton_2.setBounds(337, 77, 142, 21);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<MenuItem> products = new ArrayList<>();
				if (textField.getText().isEmpty() == false && textField_1.getText().isEmpty() == false && textField_2.getText() .isEmpty() == false) {
					// search by title,rating,price
					products = deliveryService.searchByTitleRatingPrice(textField.getText(), Float.parseFloat(textField_1.getText()), Integer.parseInt(textField_2.getText()));
					if(products.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Products not found.", null,
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						ViewMenu viewMenu = new ViewMenu(products);
					}
				} else if (textField.getText().isEmpty() == false && textField_1.getText().isEmpty()
						&& textField_2.getText().isEmpty()) {
					// search by title
					products = deliveryService.searchByTitle(textField.getText());
					if(products.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Products not found.", null,
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						ViewMenu viewMenu = new ViewMenu(products);
					}
				} else if (textField.getText().isEmpty() == false && textField_1.getText().isEmpty()
						&& textField_2.getText().isEmpty() == false) {
					// search by title,price
					products = deliveryService.searchByTitleAndPrice(textField.getText(), Integer.parseInt(textField_2.getText()));
					if(products.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Products not found.", null,
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						ViewMenu viewMenu = new ViewMenu(products);
					}
				} else if (textField.getText().isEmpty() == false && textField_1.getText().isEmpty() == false
						&& textField_2.getText().isEmpty()) {
					// search by title, rating
					products = deliveryService.searchByTitleAndRaiting(textField.getText(), Float.parseFloat(textField_1.getText()));
					if(products.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Products not found.", null,
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						ViewMenu viewMenu = new ViewMenu(products);
					}
				} else if (textField_1.getText().isEmpty() && textField_2.getText().isEmpty() == false) {
					// search by price
					products = deliveryService.searchByPrice(Integer.parseInt(textField_2.getText()));
					if(products.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Products not found.", null,
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						ViewMenu viewMenu = new ViewMenu(products);
					}
				} else if (textField_1.getText().isEmpty() == false && textField_2.getText().isEmpty()) {
					// search by rating
					products = deliveryService.searchByRating(Float.parseFloat(textField_1.getText()));
					if(products.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Products not found.", null,
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						ViewMenu viewMenu = new ViewMenu(products);
					}
				} else if (textField_1.getText().isEmpty() && textField_2.getText().isEmpty()) {
					// search by rating, price
					products = deliveryService.searchByRatingPrice(Float.parseFloat(textField_1.getText()), Integer.parseInt(textField_2.getText()));
					if(products.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Products not found.", null,
								JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						ViewMenu viewMenu = new ViewMenu(products);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Insert at least 1 criteria to search product.", null,
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		getContentPane().add(btnNewButton_2);

		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setBounds(34, 210, 76, 13);
		getContentPane().add(lblNewLabel_1);

		textField_8 = new JTextField();
		textField_8.setBounds(14, 230, 108, 19);
		getContentPane().add(textField_8);
		textField_8.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setBounds(162, 210, 96, 13);
		getContentPane().add(lblNewLabel_2);

		textField_9 = new JTextField();
		textField_9.setBounds(148, 230, 125, 19);
		getContentPane().add(textField_9);
		textField_9.setColumns(10);

		JButton btnNewButton_3 = new JButton("Register");
		btnNewButton_3.setBounds(288, 229, 96, 21);
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deliveryService.registerUser(textField_8.getText(), textField_9.getText());
			}
		});
		getContentPane().add(btnNewButton_3);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(251, 78, 73, 19);
		getContentPane().add(textField_2);

		this.setVisible(true);
	}
}
