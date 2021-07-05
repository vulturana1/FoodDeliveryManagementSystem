package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import business.DeliveryService;

public class Report2 {

	private static JFrame frame;
	private JTextField textField;

	public Report2(DeliveryService deliveryService) {

		frame = new JFrame();

		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Number of times ordered:");
		lblNewLabel.setBounds(27, 27, 153, 13);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(27, 50, 153, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Generate");
		btnNewButton.setBounds(46, 91, 124, 21);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int nr = Integer.parseInt(textField.getText());
				try {
					deliveryService.generateProductsReport(nr);
					JOptionPane.showMessageDialog(null, "Report generated.", null, JOptionPane.INFORMATION_MESSAGE);
				} catch (FileNotFoundException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Report 2");
		frame.setBounds(100, 100, 225, 181);

		frame.setVisible(true);
	}

}
