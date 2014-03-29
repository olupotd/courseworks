import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * @author unix
 * 
 */
public class Server {

	private File file;
	private Socket socket;
	private ServerSocket server;
	private ArrayList<String> menu;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;


	public Server(int port)  throws ClassNotFoundException{
		try {
			server = new ServerSocket(port);
			menu = new ArrayList<String>();
			menu.add("1---Text File");
			menu.add("2---Audio File");
			menu.add("3---Video File");
			menu.add("4---Word Document");
			while (true) {
				System.out.println("Waiting for connections on port: " + port);
				socket = server.accept();
				// initialize the reader stream and writer
				run();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() throws IOException, ClassNotFoundException {
		oos = new ObjectOutputStream(socket.getOutputStream());
		ois = new ObjectInputStream(socket.getInputStream());
		
			String msg = (String)ois.readObject();
			if(msg.equalsIgnoreCase("ShowMenu")){
			System.out.println("Sending Menu to client...");
			//send the menu of the 3 files to the client
			oos.writeObject(menu);
			oos.flush();
			int resp = Integer.parseInt(ois.readObject().toString());
			try{
			switch(resp)
			{
				//text file
				case 1:
					file = new File("/home/unix/Client/classes/TextFile.txt");
					if(file.exists())
					oos.writeObject(file);
					oos.flush();
					System.out.println("Sending file: " + file.getAbsolutePath()
					+ " of Size: " + file.length());
					System.out.println("File Sent... Awating Response");
			ois = new ObjectInputStream(socket.getInputStream());
				System.out.println("Client: " + ois.readObject());
		
				break;
				//Audio File
				case 2:
					file = new File("/home/unix/Client/music.mp3");
					if(file.exists())
					oos.writeObject(file);
					oos.flush();
					System.out.println("Sending file: " + file.getAbsolutePath()
					+ " of Size: " + file.length());
					System.out.println("File Sent... Awating Response");
			ois = new ObjectInputStream(socket.getInputStream());
				System.out.println("Client: " + ois.readObject());
				
				break;
				//Video File
				case 3:
					file = new File("/home/unix/Client/video.mp4");
					if(file.exists())
					oos.writeObject(file);
					oos.flush();
					System.out.println("Sending file: " + file.getAbsolutePath()
					+ " of Size: " + file.length());
					System.out.println("File Sent... Awating Response");
			ois = new ObjectInputStream(socket.getInputStream());
				System.out.println("Client: " + ois.readObject());
		
				break;
				//Word Document
				case 4:
					file = new File("/home/unix/Desktop/Cloud Computing.odt");
					if(file.exists())
					oos.writeObject(file);
					oos.flush();
					System.out.println("Sending file: " + file.getAbsolutePath()
					+ " of Size: " + file.length());
					System.out.println("File Sent... Awating Response");
			ois = new ObjectInputStream(socket.getInputStream());
				System.out.println("Client: " + ois.readObject());
		
				break;
				//Error occured
				default:
					file = new File("");
					if(file.exists())
					oos.writeObject(file);
					oos.flush();
					System.out.println("Sending file: " + file.getAbsolutePath()
					+ " of Size: " + file.length());
					System.out.println("File Sent... Awating Response");
			ois = new ObjectInputStream(socket.getInputStream());
				System.out.println("Client: " + ois.readObject());
		
				break;
			}
			
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			
	
		oos.close();
		ois.close();
		

	}

	public static void main(String[] args) {
		int port = 9999;
		

		try {
			new Server(port);
		} catch (Exception except) {
			except.printStackTrace();
		}

	}

}

