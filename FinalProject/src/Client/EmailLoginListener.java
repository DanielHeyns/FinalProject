package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailLoginListener implements ActionListener {
	private EmailHelper loginwindow;

	public EmailLoginListener(EmailHelper loginwindow) {
		this.loginwindow = loginwindow;
	}

	public void actionPerformed(ActionEvent arg0) {
		/**
		 * this should call the createSession in EmailHelper
		 */
		if (arg0.getSource() == loginwindow.loginB) {
			setupEmail();
		}
	}

	public void setupEmail(){
		try{
		String username = userT.getText();
		String password = passT.getText();
		loginwindow.createSession(username, password);
	} catch(IOException e){}
	}

}
