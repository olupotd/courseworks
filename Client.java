import java.awt.FileDialog;
import java.awt.Frame;
import java.net.Socket;
import java.io.*;
import java.util.ArrayList;

/**
 * @author unix
 * 
 */
public class Client {

	private File local;
	private Socket client;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public Client() throws IOException {
		client = null;
		String host = "localhost";// "10.42.0.1";
		int port = 9999;

		try {
			client = new Socket(host, port);
			System.out.println("Connection Successful...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois = new ObjectInputStream(client.getInputStream());
		oos = new ObjectOutputStream(client.getOutputStream());
		// read the contents of the server
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			//create an input reader for the keyboard 
			oos.writeObject("ShowMenu".toString());
			oos.flush();
			ArrayList list = (ArrayList) ois.readObject();
			for(int i=0;i< list.size(); i++)
			{
				System.out.println(list.get(i));
			}
			System.out.println("Please select a number for the respective file");			
			//take in the response of the client for the selected file
			String input = reader.readLine();
			//sent the repsonse back to the server
			oos.writeObject(input);
			oos.flush();
			//read the file from the server and select where to save it on client PC
			local = (File) ois.readObject();
			System.out.println("Found Name:: "
					+ local.getName() + "\nLocation" + local.getAbsolutePath()
					+ "\nFile Size: " + local.length() + "Bytes");
			// choose where to store the file
			FileDialog dialog = new FileDialog(new Frame(), "choose file name",
					FileDialog.SAVE);
			dialog.setDirectory(null);
			dialog.setFile(local.getName());
			dialog.setVisible(true);
			// combine the directory selected with file name typed
			String targetDir = dialog.getDirectory() + dialog.getFile();
			System.out.println("Saving File to: " + targetDir);
			copyBytes(local, targetDir);
			System.out.println("File Saved...");
			oos.writeObject("Received the file " + local);
			oos.flush();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void copyBytes(File local2, String targetDir) {

		int c;
		try {
			FileInputStream fis = new FileInputStream(local2);
			FileOutputStream fos = new FileOutputStream(targetDir);

			while ((c = fis.read()) != -1) {
				//System.out.println("Copying File..." + c + "%");
				fos.write(c);
			}
			fis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		new Client();
	}

}

