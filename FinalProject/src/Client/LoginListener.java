package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {
	private EmailHelper loginwindow;
	
	public LoginListener(EmailHelper loginwindow) {
		this.loginwindow = loginwindow;
	}

	public void actionPerformed(ActionEvent arg0) {
		/**
		 * this should call the createSession in EmailHelper
		 */
		if (arg0.getSource() == loginwindow.loginB) {

		}

	}

}
