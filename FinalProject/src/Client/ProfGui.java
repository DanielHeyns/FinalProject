<<<<<<< HEAD
package client;
=======
package Client;

>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
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
import javax.swing.JScrollPane;
import javax.swing.event.*;
import java.io.File;
import java.io.IOException;

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
	JButton btnEnroll;
	JButton btnUnenroll;
	JComboBox<String> comboBox_1;
	DefaultComboBoxModel comboBoxModel_1;
	JList<String> aList;
	DefaultListModel aListModel;
	JButton btnUpload;
	JButton btnActivate;
	ProfListener listener;
	DefaultListModel subListModel;
	JList<String> subList;
	JLabel lblSubmissions;
	JButton btnGrade;
	JButton btnDownload;
	JButton btnEmailStu;

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

<<<<<<< HEAD
		//panel containing the name and id of the user logged in
=======
		// panel containing the name and id of the user logged in
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
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

		// ImageIcon image1 = new ImageIcon(getClass().getResource("simple.jpg"));
		// JLabel image1label = new JLabel(image1);
		// GridBagConstraints gbc_image1label = new GridBagConstraints();
		// gbc_image1label.gridx = 4;
		// gbc_image1label.gridy = 1;
		// topP.add(image1label, gbc_image1label);

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

		btnEmailStu = new JButton("Email Course");
		btnEmailStu.setBounds(546, 218, 152, 37);
		btnEmailStu.addActionListener(listener);
		coursesP.add(btnEmailStu);

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
		rdbtnAll.addActionListener(listener);
		studentsP.add(rdbtnAll);

		rdbtnInCourse = new JRadioButton("In Course");
		rdbtnInCourse.setBounds(574, 122, 155, 29);
		rbgroup.add(rdbtnInCourse);
		rdbtnInCourse.addActionListener(listener);
		studentsP.add(rdbtnInCourse);
		rdbtnInCourse.setSelected(true);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(574, 163, 115, 29);
		btnSearch.addActionListener(listener);
		studentsP.add(btnSearch);

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
		comboBox_1.setBounds(32, 47, 193, 26);
		comboBox_1.addActionListener(listener);
		assignP.add(comboBox_1);

		//list of assignments in selected course
		aListModel = new DefaultListModel<String>();
		aList = new JList<String>(aListModel);
		aList.setBounds(32, 89, 296, 192);
		assignP.add(aList);
		aList.addListSelectionListener(new ListSelectionListener() {
<<<<<<< HEAD
				@Override
				public void valueChanged(ListSelectionEvent e){
					listener.updateSubs();
				}
=======
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listener.updateSubs();
			}
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
		});

		btnUpload = new JButton("Upload");
		btnUpload.setBounds(592, 103, 115, 29);
		btnUpload.addActionListener(listener);
		assignP.add(btnUpload);

		btnActivate = new JButton("Activate");
		btnActivate.setBounds(592, 161, 115, 29);
		btnActivate.addActionListener(listener);
		assignP.add(btnActivate);

		JScrollPane scrollPane = new JScrollPane();
		subListModel = new DefaultListModel<String>();
<<<<<<< HEAD
		subList =  new JList<String>(subListModel);
=======
		subList = new JList<String>(subListModel);
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
		scrollPane.setViewportView(subList);
		scrollPane.setBounds(343, 89, 239, 192);
		assignP.add(scrollPane);

		lblSubmissions = new JLabel("Submissions");
		lblSubmissions.setBounds(343, 50, 120, 20);
		assignP.add(lblSubmissions);

		btnGrade = new JButton("Grade");
		btnGrade.setBounds(592, 193, 115, 29);
		btnGrade.addActionListener(listener);
		assignP.add(btnGrade);

		btnDownload = new JButton("Download");
		btnDownload.setBounds(592, 238, 115, 29);
		btnDownload.addActionListener(listener);
		assignP.add(btnDownload);

		frmCourseWindow.addWindowListener(new WindowAdapter() {
<<<<<<< HEAD
            @Override
            public void windowClosing(WindowEvent e) {
                client.closeClient();
            }
						@Override
						public void windowClosed(WindowEvent e) {
								client.closeClient();
						}
        });
=======
			@Override
			public void windowClosing(WindowEvent e) {
				client.closeClient();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				client.closeClient();
			}
		});
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45

		frmCourseWindow.setVisible(true);
	}

	/**
<<<<<<< HEAD
	 *	a func to make the Prof's data visible
	 */
	public void displayUser(){
		textField_1.setText(client.user.getId() + "");
		textField.setText(client.user.getFirstName() + " " +
												client.user.getLastName());
=======
	 * a func to make the Prof's data visible
	 */
	public void displayUser() {
		textField_1.setText(client.user.getId() + "");
		textField.setText(client.user.getFirstName() + " " + client.user.getLastName());
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
		listener.updateCourses();
		listener.updateAssigns();
	}

<<<<<<< HEAD

	/**
	 *	A class to listen to all of the Professor's GUI Components
=======
	/**
	 * A class to listen to all of the Professor's GUI Components
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
	 */
	public class ProfListener implements ActionListener {
		private Client client;
		private ProfGui profGUI;

		/**
<<<<<<< HEAD
		 *	ProfListener constructor
		 */
	public ProfListener(Client c, ProfGui p){
		client = c; profGUI = p;
	}

	/**
	 * GUI listening cases
	 */
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
		if(e.getSource() == profGUI.rdbtnAll){
			updateStudents();
=======
		 * ProfListener constructor
		 */
		public ProfListener(Client c, ProfGui p) {
			client = c;
			profGUI = p;
		}

		/**
		 * GUI listening cases
		 */
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
			if (e.getSource() == profGUI.btnNewCourse) { // add new course
				if (client.courses[4] != null) { // if full load, stop
					JOptionPane.showMessageDialog(null, "You have a full course load.", "Unable to add Course",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					addNewCourse();
				} // else continue
			}
			if (e.getSource() == profGUI.btnActivate) {
				activateAssign();
			}
			if (e.getSource() == profGUI.btnSetActive) {
				activateCourse();
			}
			if (e.getSource() == profGUI.comboBox_1) {
				// update assigns for selection
				updateAssigns();
			}
			if (e.getSource() == profGUI.comboBox) {
				// update students for selection
				updateStudents();
			}
			if (e.getSource() == profGUI.rdbtnAll) {
				updateStudents();
			}
			if (e.getSource() == profGUI.rdbtnInCourse) {
				updateStudents();
			}
			if (e.getSource() == profGUI.btnEnroll) {
				enrollStudent();
			}
			if (e.getSource() == profGUI.btnUnenroll) {
				unenrollStudent();
			}
			if (e.getSource() == profGUI.btnUpload) {
				uploadFile();
			}
			if (e.getSource() == profGUI.btnSearch) {
				searchStudents();
			}
			if (e.getSource() == profGUI.btnGrade) {
				gradeSubmission();
			}
			if (e.getSource() == profGUI.btnDownload) {
				try {
					downloadSub();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		public void downloadSub() throws ClassNotFoundException {
			String str[];
			try {
				str = profGUI.subList.getSelectedValue().split(" ");
			} catch (NullPointerException e) {
				return;
			}
			if (str == null) {
				return;
			}
			int sid = Integer.parseInt(str[0]);
			client.downloadSub(sid);
		}

		/**
		 *
		 */
		public void enrollStudent() {
			String str[];
			try {
				str = profGUI.sList.getSelectedValue().split(", ");
			} catch (NullPointerException e) {
				return;
			}
			if (str == null) {
				return;
			}
			int sid = Integer.parseInt(str[0]);
			try {
				str = ((String) profGUI.comboBox.getSelectedItem()).split(", ");
			} catch (NullPointerException e) {
				return;
			}
			if (str == null) {
				return;
			}
			int cid = Integer.parseInt(str[0]);
			client.enrollStudent(sid, cid);
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
		}
		if(e.getSource() == profGUI.rdbtnInCourse){
			updateStudents();
		}
		if(e.getSource() == profGUI.btnEnroll){
			enrollStudent();
		}
		if(e.getSource() == profGUI.btnUnenroll){
			unenrollStudent();
		}
		if (e.getSource() == profGUI.btnUpload) {
			uploadFile();
		}
		if (e.getSource() == profGUI.btnSearch) {
			searchStudents();
		}
		if (e.getSource() == profGUI.btnGrade) {
			gradeSubmission();
		}
		if (e.getSource() == profGUI.btnDownload) {
			downloadSub();
		}
		if (e.getSource() == profGUI.btnEmailStu) {
			emailCourse();
		}
	}

<<<<<<< HEAD
	public void downloadSub(){
		String str[];
		try{
		str = profGUI.subList.getSelectedValue().split(" ");
		}catch(NullPointerException e){return;}
		if(str == null){return;}
		int sid = Integer.parseInt(str[0]);
		client.downloadSub(sid);
	}

	/**
	 *
	 */
	public void enrollStudent(){
		String str[];
		try{
		str = profGUI.sList.getSelectedValue().split(", ");
		}catch(NullPointerException e){return;}
		if(str == null){return;}
		int sid = Integer.parseInt(str[0]);
		try{
		str = ((String) profGUI.comboBox.getSelectedItem()).split(", ");
		}catch(NullPointerException e) {return;}
		if(str == null){return;}
		int cid = Integer.parseInt(str[0]);
		client.enrollStudent(sid,cid);
	}

	public void gradeSubmission(){
		String str[];
		try{
		str = profGUI.subList.getSelectedValue().split(" ");
		}catch(NullPointerException e){return;}
		if(str == null){return;}
		int cid = Integer.parseInt(str[0]);
		String input = JOptionPane.showInputDialog("Please Enter the grade as a percent:");
		while(!isInteger(input)){
			input = JOptionPane.showInputDialog("Please Enter the grade as a percent:");
=======
		public void gradeSubmission() {
			String str[];
			try {
				str = profGUI.subList.getSelectedValue().split(" ");
			} catch (NullPointerException e) {
				return;
			}
			if (str == null) {
				return;
			}
			int cid = Integer.parseInt(str[0]);
			String input = JOptionPane.showInputDialog("Please Enter the grade as a percent:");
			while (!isInteger(input)) {
				input = JOptionPane.showInputDialog("Please Enter the grade as a percent:");
			}
			String input2 = JOptionPane.showInputDialog("Please Enter any comments:");
			client.gradeSubmission(cid, Integer.parseInt(input), input2);
		}

		/**
		 *
		 */
		public void unenrollStudent() {
			String str[];
			try {
				str = profGUI.sList.getSelectedValue().split(", ");
			} catch (NullPointerException e) {
				return;
			}
			if (str == null) {
				return;
			}
			int sid = Integer.parseInt(str[0]);
			try {
				str = ((String) profGUI.comboBox.getSelectedItem()).split(", ");
			} catch (NullPointerException e) {
				return;
			}
			if (str == null) {
				return;
			}
			int cid = Integer.parseInt(str[0]);
			client.unenrollStudent(sid, cid);
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
		}
		String input2 = JOptionPane.showInputDialog("Please Enter any comments:");
		client.gradeSubmission(cid, Integer.parseInt(input),input2);
	}


<<<<<<< HEAD
	/**
	 *
	 */
	public void unenrollStudent(){
		String str[];
		try{
		str = profGUI.sList.getSelectedValue().split(", ");
		}catch(NullPointerException e){return;}
		if(str == null){return;}
		int sid = Integer.parseInt(str[0]);
		try{
		str = ((String) profGUI.comboBox.getSelectedItem()).split(", ");
		}catch(NullPointerException e) {return;}
		if(str == null){return;}
		int cid = Integer.parseInt(str[0]);
		client.unenrollStudent(sid,cid);
	}

	/**
	 *
	 */
	public void searchStudents() {
			String input = JOptionPane.showInputDialog("Please Enter a LastName or StudentID");
			if(input == null || input.equals("")){return;} // if no input, don't search
			if(isInteger(input)) {
=======
		/**
		 *
		 */
		public void searchStudents() {
			String input = JOptionPane.showInputDialog("Please Enter a LastName or StudentID");
			if (input == null || input.equals("")) {
				return;
			} // if no input, don't search
			if (isInteger(input)) {
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
				client.searchStudentID(Integer.parseInt(input));
			} else {
				client.searchStudentLN(input);
			}
		}

<<<<<<< HEAD

		/**
		 *gets a file from the user and sends it to the client to be sent over a socket to the server.
		 */
		public void uploadFile() {
			try{
			String coursename = (String) profGUI.comboBox_1.getSelectedItem();
			String stuff[] = coursename.split(", ");
			String assignID = JOptionPane.showInputDialog("Please Enter Assignment ID");		//Receives assignment id, title and duedate
			String assigntitle = JOptionPane.showInputDialog("Please Enter Assignment Title");
			String assignDue = JOptionPane.showInputDialog("Please Enter Assignment DueDate");
			FileHelper filehelp = new FileHelper();			//initializes FileHelper
			File file = filehelp.fileChooserFile(frmCourseWindow);
			byte[] content = filehelp.createByteArray(file);//uses two functions from filehelper to get the specified file
																								//from the user and converts the file to a byte array
			try {
				Assignment assignment = new Assignment(Integer.parseInt(assignID), Integer.parseInt(stuff[0]), //creates an assignment class with the previously gained info
						assigntitle, false, file.getAbsolutePath(), assignDue);
				assignment.setByte(content);													// adds the byte array to the assignment
				client.uploadAssign(assignment);													// sends the assignment with the byte array to the client to be sent
			} catch (NumberFormatException e1) {e1.printStackTrace();}
		}catch(IOException e){e.printStackTrace();}
			return;
		}

		public void emailCourse(){
=======
		/**
		 * gets a file from the user and sends it to the client to be sent over a socket
		 * to the server.
		 */
		public void uploadFile() {
			try {
				String coursename = (String) profGUI.comboBox_1.getSelectedItem();
				String stuff[] = coursename.split(", ");
				String assignID = JOptionPane.showInputDialog("Please Enter Assignment ID"); // Receives assignment id,
																								// title and duedate
				String assigntitle = JOptionPane.showInputDialog("Please Enter Assignment Title");
				String assignDue = JOptionPane.showInputDialog("Please Enter Assignment DueDate");
				FileHelper filehelp = new FileHelper(); // initializes FileHelper
				File file = filehelp.fileChooserFile(frmCourseWindow);
				byte[] content = filehelp.createByteArray(file);// uses two functions from filehelper to get the
																// specified file
																// from the user and converts the file to a byte array
				try {
					Assignment assignment = new Assignment(Integer.parseInt(assignID), Integer.parseInt(stuff[0]), // creates
																													// an
																													// assignment
																													// class
																													// with
																													// the
																													// previously
																													// gained
																													// info
							assigntitle, false, file.getAbsolutePath(), assignDue);
					assignment.setByte(content); // adds the byte array to the assignment
					client.uploadAssign(assignment); // sends the assignment with the byte array to the client to be
														// sent
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		/**
		 *
		 */
		public void activateCourse() {
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
			String str[];
			try{
			str = profGUI.cList.getSelectedValue().split(", ");
		}catch(NullPointerException e){return;}
			if(str == null){return;}
			int id = Integer.parseInt(str[0]);
			client.emailCourse(id);
		}

		/**
		 *
		 */
<<<<<<< HEAD
	public void activateCourse(){
		String str[];
		try{
		str = profGUI.cList.getSelectedValue().split(", ");
	}catch(NullPointerException e){return;}
		if(str == null){return;}
		int id = Integer.parseInt(str[0]);
		client.activateCourse(id);
	}

	/**
	 *
	 */
	public void activateAssign(){
		String str[];
		try{
		str = profGUI.aList.getSelectedValue().split(", ");
	}catch(NullPointerException e){return;}
		if(str == null){return;}
		int id = Integer.parseInt(str[0]);
		client.activateAssign(id);
	}

	/**
	 *
	 */
	public void addNewCourse(){
		while(true){
			String name = JOptionPane.showInputDialog("Course Name: ");
			if(name == null){break;}
			String id = JOptionPane.showInputDialog("Course ID: ");
			if(id == null || !isInteger(id)){break;}
			int i = Integer.parseInt(id);

			Course c = new Course(i,name,client.user.getId(),
									client.user.getLastName(),false,new ArrayList<Integer>());
			// (int i, String cn, int pi, String pn, boolean a, ArrayList<Integer> arr)
			client.addCourse(c);
			updateCourses();
			break;
=======
		public void activateAssign() {
			String str[];
			try {
				str = profGUI.aList.getSelectedValue().split(", ");
			} catch (NullPointerException e) {
				return;
			}
			if (str == null) {
				return;
			}
			int id = Integer.parseInt(str[0]);
			client.activateAssign(id);
		}

		/**
		 *
		 */
		public void addNewCourse() {
			while (true) {
				String name = JOptionPane.showInputDialog("Course Name: ");
				if (name == null) {
					break;
				}
				String id = JOptionPane.showInputDialog("Course ID: ");
				if (id == null || !isInteger(id)) {
					break;
				}
				int i = Integer.parseInt(id);

				Course c = new Course(i, name, client.user.getId(), client.user.getLastName(), false,
						new ArrayList<Integer>());
				// (int i, String cn, int pi, String pn, boolean a, ArrayList<Integer> arr)
				client.addCourse(c);
				updateCourses();
				break;
			}
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
		}
	}

<<<<<<< HEAD
	/**
	 * A simple func to determine if a String can be seen as a integer
	 */
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

	/**
	 *
	 */
	public void updateAssigns(){
		profGUI.aListModel.removeAllElements();
		profGUI.subListModel.removeAllElements();
		String str[];
		try{
		str = ((String) profGUI.comboBox_1.getSelectedItem()).split(", ");
		}catch(NullPointerException e) {return;}
		if(str == null){return;}
		int id = Integer.parseInt(str[0]);
		for(int i = 0; i<client.assigns.size(); i++){
			if(client.assigns.get(i).getCourseID() == id){
			 aListModel.addElement(client.assigns.get(i).toString());
			 subListModel.addElement(client.profSubmissionString(client.assigns.get(i).getId()));
		 }
=======
		/**
		 * A simple func to determine if a String can be seen as a integer
		 */
		public boolean isInteger(String s) {
			try {
				Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return false;
			} catch (NullPointerException e) {
				return false;
			}
			return true;
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
		}
	}

<<<<<<< HEAD

	public void updateSubs(){
		profGUI.subListModel.removeAllElements();
		String str[];
		try{
		str = profGUI.aList.getSelectedValue().split(", ");
		}catch(NullPointerException e){return;}
		int aid = Integer.parseInt(str[0]);
			for(int i = 0; i<client.submissions.size() ; i++){
				if(client.submissions.get(i).getAssignId() == aid){
				subListModel.addElement(client.submissions.get(i).toString());
=======
		/**
		 *
		 */
		public void updateAssigns() {
			profGUI.aListModel.removeAllElements();
			profGUI.subListModel.removeAllElements();
			String str[];
			try {
				str = ((String) profGUI.comboBox_1.getSelectedItem()).split(", ");
			} catch (NullPointerException e) {
				return;
			}
			if (str == null) {
				return;
			}
			int id = Integer.parseInt(str[0]);
			for (int i = 0; i < client.assigns.size(); i++) {
				if (client.assigns.get(i).getCourseID() == id) {
					aListModel.addElement(client.assigns.get(i).toString());
					subListModel.addElement(client.profSubmissionString(client.assigns.get(i).getId()));
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
				}
			}
		}

<<<<<<< HEAD
/**
 *
 */
	public void updateStudents(){
		profGUI.sListModel.removeAllElements();
		if(profGUI.rdbtnAll.isSelected()){
			for(int i = 0; i<client.students.size(); i++){
				 profGUI.sListModel.addElement(client.students.get(i).toString());
			}
		}else{
			String str[];
			try{
			str = ((String) profGUI.comboBox.getSelectedItem()).split(", ");
			}catch(NullPointerException e) {return;}
			if(str == null){return;}
			int id = Integer.parseInt(str[0]);
			for(int i = 0; i<client.students.size(); i++){
				if(client.students.get(i).inCourse(id))
				 profGUI.sListModel.addElement(client.students.get(i).toString());
=======
		public void updateSubs() {
			profGUI.subListModel.removeAllElements();
			String str[];
			try {
				str = profGUI.aList.getSelectedValue().split(", ");
			} catch (NullPointerException e) {
				return;
			}
			int aid = Integer.parseInt(str[0]);
			for (int i = 0; i < client.submissions.size(); i++) {
				if (client.submissions.get(i).getAssignId() == aid) {
					subListModel.addElement(client.submissions.get(i).toString());
				}
			}
		}

		/**
		 *
		 */
		public void updateStudents() {
			profGUI.sListModel.removeAllElements();
			if (profGUI.rdbtnAll.isSelected()) {
				for (int i = 0; i < client.students.size(); i++) {
					profGUI.sListModel.addElement(client.students.get(i).toString());
				}
			} else {
				String str[];
				try {
					str = ((String) profGUI.comboBox.getSelectedItem()).split(", ");
				} catch (NullPointerException e) {
					return;
				}
				if (str == null) {
					return;
				}
				int id = Integer.parseInt(str[0]);
				for (int i = 0; i < client.students.size(); i++) {
					if (client.students.get(i).inCourse(id))
						profGUI.sListModel.addElement(client.students.get(i).toString());
				}
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
			}
		}
	}

<<<<<<< HEAD
	/**
	 *
	 */
	public void updateCourses(){
		profGUI.cListModel.removeAllElements();
		profGUI.comboBoxModel.removeAllElements();
		profGUI.comboBoxModel_1.removeAllElements();
		for(int i = 0; i<client.courses.length && client.courses[i] != null; i++){
			 cListModel.addElement(client.courses[i].toString());
			 profGUI.comboBoxModel.addElement(client.courses[i].toString());
			 profGUI.comboBoxModel_1.addElement(client.courses[i].toString());
=======
		/**
		 *
		 */
		public void updateCourses() {
			profGUI.cListModel.removeAllElements();
			profGUI.comboBoxModel.removeAllElements();
			profGUI.comboBoxModel_1.removeAllElements();
			for (int i = 0; i < client.courses.length && client.courses[i] != null; i++) {
				cListModel.addElement(client.courses[i].toString());
				profGUI.comboBoxModel.addElement(client.courses[i].toString());
				profGUI.comboBoxModel_1.addElement(client.courses[i].toString());
			}
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
		}
	}
	}


}
