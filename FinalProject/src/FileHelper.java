import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileHelper {
	public String fileChooser(JFrame frame) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(frame);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		   
		        return chooser.getSelectedFile().getAbsolutePath();
		}
		else
			return null;
	}
}
