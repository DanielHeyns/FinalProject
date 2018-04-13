package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;

public class StudentGUI {

	JFrame frame;
	JTextField tfUser;
	JTextField tfName;
	Client client;
	DefaultComboBoxModel comboBoxModel;
	JComboBox comboBox;
	JList list;
	DefaultListModel listModel;
	JButton btnDownload;
	JButton btnUpload;
	JButton btnEmail;
	StudentListener listener;

	/**
	 * Create the application.
	 */
	public StudentGUI(Client c) {
		listener = new StudentListener(c,this);
		client = c;
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

		comboBoxModel = new DefaultComboBoxModel();
		comboBox = new JComboBox<String>();
		comboBox.setModel(comboBoxModel);
		comboBox.setBounds(96, 160, 338, 26);
		comboBox.addActionListener(listener);
		frame.getContentPane().add(comboBox);

		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setBounds(96, 225, 338, 203);
		frame.getContentPane().add(list);

		btnDownload = new JButton("Download");
		btnDownload.setBounds(520, 222, 115, 29);
		frame.getContentPane().add(btnDownload);

		btnUpload = new JButton("Upload");
		btnUpload.setBounds(520, 278, 115, 29);
		btnUpload.addActionListener(listener);
		frame.getContentPane().add(btnUpload);

		btnEmail = new JButton("Email");
		btnEmail.setBounds(520, 333, 115, 29);
		btnEmail.addActionListener(listener);
		frame.getContentPane().add(btnEmail);

		ImageIcon image1 = new ImageIcon(getClass().getResource("simple.jpg"));
		JLabel imagelabel = new JLabel(image1);
		imagelabel.setBounds(477, 16, 158, 112);
		frame.getContentPane().add(imagelabel);

		frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.closeClient();
            }
						@Override
						public void windowClosed(WindowEvent e) {
								client.closeClient();
						}
        });

		frame.setVisible(true);
	}

	/**
	 *	a func to make the Student's data visible
	 */
	public void displayUser(){
		tfUser.setText(client.user.getId() + "");
		tfName.setText(client.user.getFirstName() + " " +
												client.user.getLastName());
		listener.updateCourses();
		listener.updateAssigns();
	}

	/**
	 *	A class to listen to all of the Professor's GUI Components
	 */
	public class StudentListener implements ActionListener {
		private Client client;
		private StudentGUI sGUI;

		/**
		 *	ProfListener constructor
		 */
	public StudentListener(Client c, StudentGUI s){
		client = c; sGUI = s;
	}

	/**
	 * GUI listening cases
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sGUI.comboBox) {
			updateAssigns();
		}
<<<<<<< HEAD
		if (e.getSource() == sGUI.btnDownload) {
			download();
		}
		if (e.getSource() == sGUI.btnUpload) {
			upload();
			updateAssigns();
		}
		if (e.getSource() == sGUI.btnEmail) {
			email();
		}
	}

	public void email(){
		String str[];
		try{
		str = sGUI.list.getSelectedValue().split(", ");
	}catch(NullPointerException e){return;}
		if(str == null){return;}
		int id = Integer.parseInt(str[0]);
		client.emailProf(id);
	}

	public void download(){
		String str[];
		try{
		str = sGUI.list.getSelectedValue().split(", ");
		}catch(NullPointerException e){return;}
		if(str == null){return;}
		int sid = Integer.parseInt(str[0]);
		client.downloadAssign(sid);
	}

	public void upload(){
		try{
		String assignname = (String) sGUI.list.getSelectedValue();
		String stuff[] = assignname.split(", ");
		String subID = JOptionPane.showInputDialog("Please Enter Submission ID");
		String subtitle = JOptionPane.showInputDialog("Please Enter Submission Title");
		FileHelper filehelp = new FileHelper();			//initializes FileHelper
		File file = filehelp.fileChooserFile(frmCourseWindow);
		byte[] content = filehelp.createByteArray(file);//uses two functions from filehelper to get the specified file
																							//from the user and converts the file to a byte array
		try {
			//(int i, int ai, int si, String t, String p, String ts, int g,String c)
			Submission sub = new Submission(Integer.parseInt(subID), Integer.parseInt(stuff[0]),
					client.user.getId(), assigntitle, file.getAbsolutePath(), null, -1, null);
			sub.setByte(content);
			sub.stamp();												// adds the byte array to the assignment
			client.upload(sub);													// sends the assignment with the byte array to the client to be sent
		} catch (NumberFormatException e1) {e1.printStackTrace();}
	}catch(IOException e){e.printStackTrace();}
		return;
	}
=======
	}

>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45

	/**
	 *
	 */
	public void updateAssigns(){
		sGUI.listModel.removeAllElements();
		String str[];
		try{
		str = ((String) sGUI.comboBox.getSelectedItem()).split(", ");
		}catch(NullPointerException e) {return;}
		if(str == null){return;}
		int id = Integer.parseInt(str[0]);
		for(int i = 0; i<client.assigns.size(); i++){
			if(client.assigns.get(i) == null){break;}
			if(client.assigns.get(i).getCourseID() == id){
<<<<<<< HEAD
			 sGUI.listModel.addElement(client.assigns.get(i).toString());
			 addSub(client.assigns.get(i).getAssignId());
		 }
		}
	}

	public void addSub(int aid){
		profGUI.subListModel.removeAllElements();
			for(int i = 0; i<client.submissions.size() ; i++){
				if(client.submissions.get(i).getAssignId() == aid){
				sGUI.listModel.addElement(client.submissions.get(i).toString());
				return;
				}
			}
			sGUI.listModel.addElement("No submission yet");
			return;
		}

=======
			 sGUI.listModel.addElement(client.assigns.get(i).toString());}
		}
	}
>>>>>>> 60774e0f3685951d5c991dd79408a074f2744a45
	/**
	 *
	 */
	public void updateCourses(){
		sGUI.comboBoxModel.removeAllElements();
		for(int i = 0; i<client.courses.length && client.courses[i] != null; i++){
			 sGUI.comboBoxModel.addElement(client.courses[i].toString());
		}
	}

	}
}
