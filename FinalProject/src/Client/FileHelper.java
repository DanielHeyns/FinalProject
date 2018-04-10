package Client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Objects.Assignment;

public class FileHelper {

	public File fileChooserFile(JFrame frame) throws IOException {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			return file;

		}
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

	public byte[] createByteArray(File file) {
		long length = file.length();
		byte[] content = new byte[(int) length];
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fis);
			bos.read(content, 0, (int) length);
			bos.close();
			fis.close();
			return content;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public File recieveAssign(ObjectInputStream in) {
		try {
			Assignment assign = (Assignment) in.readObject();
			File file = new File(assign.getTitle());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(assign.getByte());
			fileOutputStream.close();

			File copy = new File("C:\\Assigns\\" + assign.getCourseID() + "\\" + assign.getId() + "."
					+ getExtension(file.getAbsolutePath()));
			copy.getParentFile().mkdir();
			copy.createNewFile();
			copyFile(file, copy);
			return copy;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
