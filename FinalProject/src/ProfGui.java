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
		
		//panel containing the name and id of the professor logged in
		JPanel topP = new JPanel();
		frmCourseWindow.getContentPane().add(topP, BorderLayout.NORTH);
		GridBagLayout gbl_topP = new GridBagLayout();
		gbl_topP.columnWidths = new int[] {30, 30, 10, 10, 10};
		gbl_topP.rowHeights = new int[] {10, 10, 10};
		gbl_topP.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_topP.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		topP.setLayout(gbl_topP);
		topP.setBorder(new EmptyBorder(40, 10, 10, 10));
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 0;
		topP.add(lblName, gbc_lblName);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 0;
		topP.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(0, 0, 0, 5);
		gbc_lblId.gridx = 2;
		gbc_lblId.gridy = 1;
		topP.add(lblId, gbc_lblId);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.WEST;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 1;
		topP.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
//		ImageIcon image1 = new ImageIcon(getClass().getResource("simple.jpg"));
//		JLabel image1label = new JLabel(image1);
//		GridBagConstraints gbc_image1label = new GridBagConstraints();
//		gbc_image1label.gridx = 4;
//		gbc_image1label.gridy = 1;
//		topP.add(image1label, gbc_image1label);
		
		//bottom panel where the buttons to make the center panel change between its 3 possible panels
		JPanel bottomP = new JPanel();
		frmCourseWindow.getContentPane().add(bottomP, BorderLayout.SOUTH);
		bottomP.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnGoToCur = new JButton("Go To Courses");
		bottomP.add(btnGoToCur);
		
		JButton btnGoToStu = new JButton("Go To Students");
		bottomP.add(btnGoToStu);
		
		JButton btnGoToAssign = new JButton("Go To Assignments");
		bottomP.add(btnGoToAssign);
		
		//center panel that will switch between three panels as buttons in the bottom panel are pushed
		JPanel centerP = new JPanel();
		frmCourseWindow.getContentPane().add(centerP, BorderLayout.CENTER);
		centerP.setLayout(new CardLayout(0, 0));
		
		//courses panel for the center panel
		JPanel coursesP = new JPanel();
		centerP.add(coursesP, "name_1526742415187808");
		coursesP.setLayout(null);
		
		//list where all the courses will be listed
		JList<String> cList = new JList<String>();
		cList.setBackground(Color.WHITE);
		cList.setForeground(Color.DARK_GRAY);
		String [] names = new String[2];
		names[0] = "Default1";
		names[1] = "Default2";
		cList.setBounds(131, 58, 351, 207);
		coursesP.add(cList);
		cList.setListData(names);
		
		JButton btnNewCourse = new JButton("New Course");
		btnNewCourse.setBounds(546, 86, 152, 37);
		coursesP.add(btnNewCourse);
		
		JButton btnSetActive = new JButton("Set Active");
		btnSetActive.setBounds(546, 152, 152, 37);
		coursesP.add(btnSetActive);
		
		JLabel lblCourses = new JLabel("Courses:");
		lblCourses.setBounds(131, 32, 69, 20);
		coursesP.add(lblCourses);
		
		//students panel for he center panel
		JPanel studentsP = new JPanel();
		centerP.add(studentsP, "name_1526746518113682");
		studentsP.setLayout(null);
		
		//combo box that will be used to select a course for searching and enrollment/unenrollment
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(160, 33, 189, 26);
		studentsP.add(comboBox);
		
		//list of students
		JList<String> sList = new JList<String>();
		sList.setBounds(160, 88, 299, 194);
		studentsP.add(sList);
		
		JRadioButton rdbtnAll = new JRadioButton("All");
		rdbtnAll.setBounds(574, 85, 155, 29);
		studentsP.add(rdbtnAll);
		
		JRadioButton rdbtnInCourse = new JRadioButton("In Course");
		rdbtnInCourse.setBounds(574, 122, 155, 29);
		studentsP.add(rdbtnInCourse);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(574, 163, 115, 29);
		studentsP.add(btnSearch);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(574, 207, 115, 29);
		studentsP.add(btnReset);
		
		JButton btnEnroll = new JButton("Enroll\r\n");
		btnEnroll.setBounds(514, 252, 115, 29);
		studentsP.add(btnEnroll);
		
		JButton btnUnenroll = new JButton("Unenroll");
		btnUnenroll.setBounds(648, 253, 115, 29);
		studentsP.add(btnUnenroll);
		
		//Assignment panel for the center panel
		JPanel assignP = new JPanel();
		centerP.add(assignP, "name_1526748614009756");
		assignP.setLayout(null);
		
		//combo box to select the desired course to view assignments for
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(156, 38, 193, 26);
		assignP.add(comboBox_1);
		
		//list of assignments in selected course
		JList<String> aList = new JList<String>();
		aList.setBounds(156, 90, 296, 192);
		assignP.add(aList);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBounds(592, 103, 115, 29);
		assignP.add(btnUpload);
		
		JButton btnActivate = new JButton("Activate");
		btnActivate.setBounds(592, 161, 115, 29);
		assignP.add(btnActivate);
	}
}
