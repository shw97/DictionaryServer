/**
 * 
 */

/**
 * @author Shwetha Srinivasan
 * @student_id 1073180
 *
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import javax.net.ServerSocketFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ServerDict {

	/**
	 * @param args
	 */
	public static TreeMap<String, String> dictmap = new TreeMap<String, String>();
	public static TreeMap<String, String> dictmap_null = new TreeMap<String, String>();
	
	static void ClientInteract(Socket clientSocket) {
//		JFrame f=new JFrame();//creating instance of JFrame  
//		final JTextField tf=new JTextField();  
//	    tf.setBounds(50,50, 150,20);  
////		JButton bsearch=new JButton("Search");//creating instance of JButton  
////		bsearch.setBounds(50,200,150, 20);//x axis, y axis, width, height
////		JButton bdelete=new JButton("Delete");//creating instance of JButton  
////		bdelete.setBounds(50,250,150, 20);//x axis, y axis, width, height
////		JButton badd=new JButton("Add");//creating instance of JButton  
////		badd.setBounds(50,300,150, 20);//x axis, y axis, width, height
////		JButton bupdate=new JButton("Update Server");//creating instance of JButton  
////		bupdate.setBounds(50,400,150, 20);//x axis, y axis, width, height
//		final JTextField tf2=new JTextField(); 
//		JLabel l = new JLabel(); 
//	    tf2.setBounds(50,100, 150,20);
//	    l.setText("Server is Up and Running");
//		f.add(tf); 
//		f.add(l);
		System.out.println("New thread for the requested connection and action is created");
		try {

			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			// Output Stream
		    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println("Connected, sending dictionary");
			String opt = null;
			int i = 1,option = 0;

			opt = "not_null";
			String value = input.readUTF();
			

				ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
				oos.writeObject(dictmap);
				oos.flush();
				System.out.println("Response sent");
				ObjectInputStream ios = new ObjectInputStream(clientSocket.getInputStream());
//				while(true) {
				
					try {
						dictmap_null = (TreeMap<String, String>) ios.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(!dictmap_null.isEmpty()) {
						dictmap = dictmap_null;
						FileOutputStream fileOut =new FileOutputStream("sample_txt.txt");
							         ObjectOutputStream out = new ObjectOutputStream(fileOut);
							         out.writeObject(dictmap);
							         out.close();
							         fileOut.close();
						
//						Files.write(file, lines, StandardCharsets.UTF_8);
					}
//					if(opt == null) {
						
//					}
					System.out.println("Updated dictionary from client  : " + dictmap);
		}
//		}}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
//		ClientInteract(clientSocket);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		f.add(bsearch);//adding button in JFrame  
//		f.add(tf2); 
//		f.add(bdelete);
//		f.add(badd);
		try {
			InetAddress ip = InetAddress.getLocalHost();
			System.out.println("IP address is:"+ip);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		int port_no = 9043;
		int port_no = Integer.parseInt(args[0]);
//		ServerSocket DictServerSock = null;  //Important Initialization
		ServerSocketFactory thread_factory = ServerSocketFactory.getDefault();
//		dictmap.put("hello","hie");
//		dictmap.put("tell","say");
//		dictmap.put("yellow","color");
//		Set set = dictmap.entrySet();
//		Iterator iterator = set.iterator();
//		while(iterator.hasNext()) {
//			Map.Entry mentry = (Map.Entry)iterator.next();
//			System.out.print("Key:"+mentry.getKey()+" Value is:"+mentry.getValue());
//		}
		
		File file = new File(args[1]);
//		if(!dictmap_null.isEmpty()) {
//			FileOutputStream fileOut =
//			         new FileOutputStream("/tmp/employee.ser");
//			         ObjectOutputStream out = new ObjectOutputStream(fileOut);
//			         out.writeObject(e);
//			         out.close();
//			         fileOut.close();
//		}
		try{
			Scanner scanner = new Scanner(file);
		
//		TreeMap<String, String> map = new TreeMap<String, String>();
		while (scanner.hasNextLine()) {
//			  System.out.println("Entered the while loop");
		      String word = scanner.nextLine();
		      String[] word_parts = word.split("\\s+");
//		      Integer count = map.get(word);
//		      count = (count == null ? 1 : count + 1);
//		      map.put(word, count);
		      dictmap.put(word_parts[0],word_parts[1]);
		    }
		System.out.println("Dictionary file obtained");
		Set set = dictmap.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			System.out.println("Key: "+mentry.getKey()+" Value is: "+mentry.getValue());
		}
	
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
		try(ServerSocket DictServerSock = thread_factory.createServerSocket(port_no)) {
	
			while (true) 
			{
				System.out.println("Server listening on port " + port_no+" for a connection");
				
				//Accept an incoming client connection request 
				Socket clientSocket = DictServerSock.accept(); //This method will block until a connection request is received

				Thread new_client = new Thread(() -> ClientInteract(clientSocket));
				new_client.start();
				
				

			}
		} 
		catch (SocketException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}


