import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class ProfGui {

	private JFrame frmCourseWindow;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfGui window = new ProfGui();
					window.frmCourseWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProfGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCourseWindow = new JFrame();
		frmCourseWindow.setTitle("D1L - Daniel Heyns and Jesse Hooper");
		frmCourseWindow.setSize(872,618);
		frmCourseWindow.setFont(new Font("Algerian", Font.PLAIN, 12));
		frmCourseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCourseWindow.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmCourseWindow.getContentPane().add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {30, 30, 10, 10, 10};
		gbl_panel.rowHeights = new int[] {10, 10, 10};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		panel.setBorder(new EmptyBorder(40, 10, 10, 10));
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 0;
		panel.add(lblName, gbc_lblName);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 0;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(0, 0, 0, 5);
		gbc_lblId.gridx = 2;
		gbc_lblId.gridy = 1;
		panel.add(lblId, gbc_lblId);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		ImageIcon image1 = new ImageIcon(getClass().getResource("simple.jpg"));
		JLabel image1label = new JLabel(image1);
		GridBagConstraints gbc_image1label = new GridBagConstraints();
		gbc_image1label.gridx = 4;
		gbc_image1label.gridy = 1;
		panel.add(image1label, gbc_image1label);
		
		JPanel panel_1 = new JPanel();
		frmCourseWindow.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnGoToCur = new JButton("Go To Courses");
		panel_1.add(btnGoToCur);
		
		JButton btnNewButton = new JButton("Go To Students");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Go To Assignments");
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		frmCourseWindow.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new CardLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "name_1526742415187808");
		panel_3.setLayout(null);
		
		JList<String> list = new JList<String>();
		list.setBackground(Color.WHITE);
		list.setForeground(Color.DARK_GRAY);
		String [] names = new String[2];
		names[0] = "Default1";
		names[1] = "Default2";
		list.setBounds(131, 58, 351, 207);
		panel_3.add(list);
		list.setListData(names);
		
		JButton btnNewButton_2 = new JButton("New Course");
		btnNewButton_2.setBounds(546, 86, 152, 37);
		panel_3.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Set Active");
		btnNewButton_3.setBounds(546, 152, 152, 37);
		panel_3.add(btnNewButton_3);
		
		JLabel lblCourses = new JLabel("Courses:");
		lblCourses.setBounds(131, 32, 69, 20);
		panel_3.add(lblCourses);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, "name_1526746518113682");
		panel_4.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(160, 33, 189, 26);
		panel_4.add(comboBox);
		
		JList list_1 = new JList();
		list_1.setBounds(160, 88, 299, 194);
		panel_4.add(list_1);
		
		JRadioButton rdbtnAll = new JRadioButton("All");
		rdbtnAll.setBounds(574, 85, 155, 29);
		panel_4.add(rdbtnAll);
		
		JRadioButton rdbtnInCourse = new JRadioButton("In Course");
		rdbtnInCourse.setBounds(574, 122, 155, 29);
		panel_4.add(rdbtnInCourse);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(574, 163, 115, 29);
		panel_4.add(btnSearch);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(574, 207, 115, 29);
		panel_4.add(btnReset);
		
		JButton btnNewButton_4 = new JButton("Enroll\r\n");
		btnNewButton_4.setBounds(514, 252, 115, 29);
		panel_4.add(btnNewButton_4);
		
		JButton btnUnenroll = new JButton("Unenroll");
		btnUnenroll.setBounds(648, 253, 115, 29);
		panel_4.add(btnUnenroll);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, "name_1526748614009756");
		panel_5.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(156, 38, 193, 26);
		panel_5.add(comboBox_1);
		
		JList list_2 = new JList();
		list_2.setBounds(156, 90, 296, 192);
		panel_5.add(list_2);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(592, 103, 115, 29);
		panel_5.add(btnUpload);
		
		JButton btnActivate = new JButton("Activate");
		btnActivate.setBounds(592, 161, 115, 29);
		panel_5.add(btnActivate);
	}
}
