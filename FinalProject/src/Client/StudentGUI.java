package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StudentGUI {

	private JFrame frame;
	private JTextField tfUser;
	private JTextField tfName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGUI window = new StudentGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 701, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUserid = new JLabel("UserID:");
		lblUserid.setBounds(15, 16, 69, 20);
		frame.getContentPane().add(lblUserid);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(15, 51, 69, 20);
		frame.getContentPane().add(lblName);

		tfUser = new JTextField();
		tfUser.setEditable(false);
		tfUser.setBounds(79, 13, 146, 26);
		frame.getContentPane().add(tfUser);
		tfUser.setColumns(10);

		tfName = new JTextField();
		tfName.setEditable(false);
		tfName.setBounds(79, 48, 146, 26);
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(96, 160, 338, 26);
		frame.getContentPane().add(comboBox);

		JList list = new JList();
		list.setBounds(96, 225, 338, 203);
		frame.getContentPane().add(list);

		JButton btnDownload = new JButton("Download");
		btnDownload.setBounds(520, 222, 115, 29);
		frame.getContentPane().add(btnDownload);

		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(520, 278, 115, 29);
		frame.getContentPane().add(btnUpload);

		JButton btnEmail = new JButton("Email");
		btnEmail.setBounds(520, 333, 115, 29);
		frame.getContentPane().add(btnEmail);
		
		ImageIcon image1 = new ImageIcon(getClass().getResource("simple.jpg"));
		JLabel imagelabel = new JLabel(image1);
		imagelabel.setBounds(477, 16, 158, 112);
		frame.getContentPane().add(imagelabel);


	}
}
