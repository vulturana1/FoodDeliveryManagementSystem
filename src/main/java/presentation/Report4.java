package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import business.DeliveryService;

public class Report4 {

	private static JFrame frame;
	private JTextField textField;

	public Report4(DeliveryService deliveryService) {

		frame = new JFrame();

		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Date(yyyy-MM-dd):");
		lblNewLabel.setBounds(27, 27, 204, 13);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(27, 50, 153, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.setBounds(41, 91, 124, 21);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String date = textField.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dateTime = LocalDate.parse(date, formatter);
				try {
					deliveryService.generateProductsDayReport(dateTime);
					JOptionPane.showMessageDialog(null, "Report generated.", null, JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Report 4");
		frame.setBounds(100, 100, 232, 181);

		frame.setVisible(true);
	}

}
