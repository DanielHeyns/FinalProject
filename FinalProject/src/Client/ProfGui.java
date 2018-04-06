package client;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;

import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.*;
import java.awt.event.ActionEvent;

import objects.*;

public class ProfGui {

	JFrame frmCourseWindow;
	JTextField textField;
	JTextField textField_1;
	private Login lWindow;
	Client client;


	// GUI Components
	JPanel centerP;
	JButton btnGoToCur;
	JButton btnGoToStu;
	JButton btnGoToAssign;
	JList<String> cList;
	DefaultListModel cListModel;
	JButton btnNewCourse;
	JButton btnSetActive;
	JComboBox<String> comboBox;
	DefaultComboBoxModel comboBoxModel;

	JList<String> sList;
	DefaultListModel sListModel;
	JRadioButton rdbtnAll;
	JRadioButton rdbtnInCourse;
	ButtonGroup rbgroup;
	JButton btnSearch;
	JButton btnReset;
	JButton btnEnroll;
	JButton btnUnenroll;
	JComboBox<String> comboBox_1;
	DefaultComboBoxModel comboBoxModel_1;
	JList<String> aList;
	DefaultListModel aListModel;
	JButton btnUpload;
	JButton btnActivate;
	ProfListener listener;

	/**
	 * Create the application.
	 */
	public ProfGui(Client c) {
		client = c;
		listener = new ProfListener(c,this);
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
		ImageIcon image1 = new ImageIcon(getClass().getResource("simple.jpg"));
		JLabel image1label = new JLabel(image1);
		GridBagConstraints gbc_image1label = new GridBagConstraints();
		gbc_image1label.gridx = 4;
		gbc_image1label.gridy = 1;
		topP.add(image1label, gbc_image1label);

		//bottom panel where the buttons to make the center panel change between its 3 possible panels
		JPanel bottomP = new JPanel();
		frmCourseWindow.getContentPane().add(bottomP, BorderLayout.SOUTH);
		bottomP.setLayout(new GridLayout(1, 0, 0, 0));

		btnGoToCur = new JButton("Go To Courses");
		btnGoToCur.addActionListener(listener);
		bottomP.add(btnGoToCur);

		btnGoToStu = new JButton("Go To Students");
		btnGoToStu.addActionListener(listener);
		bottomP.add(btnGoToStu);

		btnGoToAssign = new JButton("Go To Assignments");
		btnGoToAssign.addActionListener(listener);
		bottomP.add(btnGoToAssign);

		//center panel that will switch between three panels as buttons in the bottom panel are pushed
		centerP = new JPanel();
		frmCourseWindow.getContentPane().add(centerP, BorderLayout.CENTER);
		centerP.setLayout(new CardLayout(0, 0));

		//courses panel for the center panel
		JPanel coursesP = new JPanel();
		centerP.add(coursesP, "Courses");
		coursesP.setLayout(null);

		//list where all the courses will be listed
		cListModel = new DefaultListModel<String>();
		cList = new JList<String>(cListModel);
		cList.setBackground(Color.WHITE);
		cList.setForeground(Color.DARK_GRAY);
		cList.setBounds(131, 58, 351, 207);
		coursesP.add(cList);

		btnNewCourse = new JButton("New Course");
		btnNewCourse.setBounds(546, 86, 152, 37);
		btnNewCourse.addActionListener(listener);
		coursesP.add(btnNewCourse);

		btnSetActive = new JButton("Set Active");
		btnSetActive.setBounds(546, 152, 152, 37);
		btnSetActive.addActionListener(listener);
		coursesP.add(btnSetActive);

		JLabel lblCourses = new JLabel("Courses:");
		lblCourses.setBounds(131, 32, 69, 20);
		coursesP.add(lblCourses);

		//students panel for he center panel
		JPanel studentsP = new JPanel();
		centerP.add(studentsP, "Students");
		studentsP.setLayout(null);

		//combo box that will be used to select a course for searching and enrollment/unenrollment
		comboBoxModel = new DefaultComboBoxModel();
		comboBox = new JComboBox<String>();
		comboBox.setModel(comboBoxModel);
		comboBox.setBounds(160, 33, 189, 26);
		comboBox.addActionListener(listener);
		studentsP.add(comboBox);

		//list of students
		sListModel = new DefaultListModel<String>();
		sList = new JList<String>(sListModel);
		sList.setBounds(160, 88, 299, 194);
		studentsP.add(sList);

		rbgroup = new ButtonGroup();
		rdbtnAll = new JRadioButton("All");
		rdbtnAll.setBounds(574, 85, 155, 29);
		rbgroup.add(rdbtnAll);
		studentsP.add(rdbtnAll);

		rdbtnInCourse = new JRadioButton("In Course");
		rdbtnInCourse.setBounds(574, 122, 155, 29);
		rbgroup.add(rdbtnInCourse);
		studentsP.add(rdbtnInCourse);
		rdbtnInCourse.setSelected(true);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(574, 163, 115, 29);
		btnSearch.addActionListener(listener);
		studentsP.add(btnSearch);

		btnReset = new JButton("Reset");
		btnReset.setBounds(574, 207, 115, 29);
		btnReset.addActionListener(listener);
		studentsP.add(btnReset);

		btnEnroll = new JButton("Enroll\r\n");
		btnEnroll.setBounds(514, 252, 115, 29);
		btnEnroll.addActionListener(listener);
		studentsP.add(btnEnroll);

		btnUnenroll = new JButton("Unenroll");
		btnUnenroll.setBounds(648, 253, 115, 29);
		btnUnenroll.addActionListener(listener);
		studentsP.add(btnUnenroll);

		//Assignment panel for the center panel
		JPanel assignP = new JPanel();
		centerP.add(assignP, "Assignments");
		assignP.setLayout(null);

		//combo box to select the desired course to view assignments for
		comboBoxModel_1 = new DefaultComboBoxModel();
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(comboBoxModel_1);
		comboBox_1.setBounds(156, 38, 193, 26);
		comboBox_1.addActionListener(listener);
		assignP.add(comboBox_1);

		//list of assignments in selected course
		aListModel = new DefaultListModel<String>();
		aList = new JList<String>(aListModel);
		aList.setBounds(156, 90, 296, 192);
		assignP.add(aList);

		btnUpload = new JButton("Upload");
		btnUpload.setBounds(592, 103, 115, 29);
		btnUpload.addActionListener(listener);
		assignP.add(btnUpload);

		btnActivate = new JButton("Activate");
		btnActivate.setBounds(592, 161, 115, 29);
		btnActivate.addActionListener(listener);
		assignP.add(btnActivate);

		lWindow = new Login(client, frmCourseWindow);
	}

	public void displayUser(){
		textField_1.setText(client.professor.getId() + "");
		textField.setText(client.professor.getFirstName() + " " +
												client.professor.getLastName());
		listener.updateCourses();
		listener.updateAssigns();
	}

	public class ProfListener implements ActionListener {
		private Client client;
		private ProfGui profGUI;

	public ProfListener(Client c, ProfGui p){
		client = c; profGUI = p;
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == profGUI.btnGoToStu) {
			CardLayout cardLayout = (CardLayout) profGUI.centerP.getLayout();
			cardLayout.show(profGUI.centerP, "Students");
		}
		if (e.getSource() == profGUI.btnGoToCur) {
			CardLayout cardLayout = (CardLayout) profGUI.centerP.getLayout();
			cardLayout.show(profGUI.centerP, "Courses");
		}
		if (e.getSource() == profGUI.btnGoToAssign) {
			CardLayout cardLayout = (CardLayout) profGUI.centerP.getLayout();
			cardLayout.show(profGUI.centerP, "Assignments");
		}
		if(e.getSource() == profGUI.btnNewCourse){ // add new course
			if(client.courses[4] != null){ // if full load, stop
				JOptionPane.showMessageDialog(null, "You have a full course load.",
				"Unable to add Course",  JOptionPane.PLAIN_MESSAGE);}
			else{addNewCourse();} // else continue
		}
		if(e.getSource() == profGUI.btnActivate){
			activateAssign();
		}
		if(e.getSource() == profGUI.btnSetActive){
			activateCourse();
		}
		if(e.getSource() == profGUI.comboBox_1){
			// update assigns for selection
			updateAssigns();
		}
		if(e.getSource() == profGUI.comboBox){
			// update students for selection
			updateStudents();
		}
	}

	public void activateCourse(){
		String str[];
		try{
		str = profGUI.cList.getSelectedValue().split(", ");
	}catch(NullPointerException e){return;}
		if(str == null){return;}
		int id = Integer.parseInt(str[0]);
		client.activateCourse(id);
	}

	public void activateAssign(){
		String str[];
		try{
		str = profGUI.aList.getSelectedValue().split(", ");
	}catch(NullPointerException e){return;}
		if(str == null){return;}
		int id = Integer.parseInt(str[0]);
		client.activateAssign(id);
	}

	public void addNewCourse(){
		while(true){
			String name = JOptionPane.showInputDialog("Course Name: ");
			if(name == null){break;}
			String id = JOptionPane.showInputDialog("Course ID: ");
			if(id == null || !isInteger(id)){break;}
			int i = Integer.parseInt(id);

			Course c = new Course(i,name,client.professor.getId(),
									client.professor.getLastName(),false,new ArrayList<Integer>());
			// (int i, String cn, int pi, String pn, boolean a, ArrayList<Integer> arr)
			client.addCourse(c);
			updateCourses();
			break;
		}
	}

	public boolean isInteger(String s) {
    try {
        Integer.parseInt(s);
    } catch(NumberFormatException e) {
        return false;
    } catch(NullPointerException e) {
        return false;
    }
    return true;
	}

	public void updateAssigns(){
		profGUI.aListModel.removeAllElements();
		String str[];
		try{
		str = ((String) profGUI.comboBox_1.getSelectedItem()).split(", ");
		}catch(NullPointerException e) {return;}
		if(str == null){return;}
		int id = Integer.parseInt(str[0]);
		for(int i = 0; i<client.assigns.size(); i++){
			if(client.assigns.get(i).getCourseID() == id){
			 aListModel.addElement(client.assigns.get(i).toString());}
		}
	}

	public void updateStudents(){
		profGUI.aListModel.removeAllElements();
		String str[];
		try{
		str = ((String) profGUI.comboBox_1.getSelectedItem()).split(", ");
		}catch(NullPointerException e) {return;}
		if(str == null){return;}
		int id = Integer.parseInt(str[0]);
		for(int i = 0; i<client.assigns.size(); i++){
			if(client.assigns.get(i).getCourseID() == id){
			 aListModel.addElement(client.assigns.get(i).toString());}
		}
	}

	public void updateCourses(){
		profGUI.cListModel.removeAllElements();
		profGUI.comboBoxModel.removeAllElements();
		profGUI.comboBoxModel_1.removeAllElements();
		for(int i = 0; i<client.courses.length && client.courses[i] != null; i++){
			 cListModel.addElement(client.courses[i].toString());
			 profGUI.comboBoxModel.addElement(client.courses[i].toString());
			 profGUI.comboBoxModel_1.addElement(client.courses[i].toString());
		}
	}
	}


}
