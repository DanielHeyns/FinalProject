package objects;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileHelper {

	/**
	 * creates a window that can be interacted with by a user to select a file form their system
	 * @param frame where the window will be presented
	 * @return the file that the user selected
	 * @throws IOException when nothing is selected
	 */
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
	/**
	 * copies the contents of one file to another, overwriting all previous data
	 * @param sourceFile is the file to be copied
	 * @param destFile is the file to be copied to
	 * @throws IOException when the source or destfiles could not be found
	 */
	@SuppressWarnings("resource")
	private static void copyFile(File sourceFile, File destFile) throws IOException {
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

	private static String getExtension(String path) {

		String ext[] = path.split("\\.");
		return ext[1];
	}
	/**
	 * converts a file to a byte array
	 * @param file to be converted
	 * @return the byte array with the data from the file
	 */
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

	/**
	 * used to save the assignment file provided to the system that this function is called on
	 * @param assign the assignment to be saved
	 */
	public void saveAssign(Assignment assign) {
		try {
			File file = new File(assign.getTitle());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(assign.getByte());
			fileOutputStream.close();

			File copy = new File("C:\\Assigns\\" + assign.getCourseID() + "\\" + assign.getId() + "."
					+ getExtension(file.getAbsolutePath()));
			// assign.setPath(copy.getAbsolutePath());
			copy.getParentFile().mkdir();
			copy.createNewFile();
			copyFile(file, copy);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
