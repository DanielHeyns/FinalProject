package client;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileHelper {
	private String courseID;
	private String AssignID;

	public FileHelper(String ID, String Assign) {
		this.courseID = ID;
		this.AssignID = Assign;
	}

	public String fileChooserFile(JFrame frame) throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String ext = getExtension(chooser.getSelectedFile().getAbsolutePath());
			File copy = new File("/Users/jesse/Desktop/Assignments/"+ courseID + "/" + AssignID + "." +ext);
			copy.getParentFile().mkdir();
			copy.createNewFile();
			copyFile(file,copy);
			return ("/Users/jesse/Desktop/Assignments/"+ courseID + "/" + AssignID + "." +ext);
		}
		else
			return null;
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	public static String getExtension(String path) {

		String ext[] = path.split("\\.");
		return ext[1];
	}

}
