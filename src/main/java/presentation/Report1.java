package presentation;

import javax.swing.*;

import business.DeliveryService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report1 {

	private static JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	public Report1(DeliveryService deliveryService) {

		frame = new JFrame();

		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Start date(yyyy-MM-dd HH:mm):");
		lblNewLabel.setBounds(27, 27, 204, 13);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Finish date(yyyy-MM-dd HH:mm):");
		lblNewLabel_1.setBounds(241, 27, 217, 13);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(27, 50, 153, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(241, 50, 153, 19);
		frame.getContentPane().add(textField_1);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.setBounds(146, 91, 124, 21);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String start = textField.getText();
				String finish = textField_1.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime startData = LocalDateTime.parse(start, formatter);
				LocalDateTime finishData = LocalDateTime.parse(finish, formatter);
				try {
					deliveryService.generateTimeReport(startData, finishData);
					JOptionPane.showMessageDialog(null, "Report generated.", null, JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Report 1");
		frame.setBounds(100, 100, 455, 181);

		frame.setVisible(true);
	}
}
