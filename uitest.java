import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class uitest {

	private JFrame frame;
	private JTextField textField;
	public static Socket clientSocket = null;
	public static TreeMap<String, String> dictmap = new TreeMap<String, String>();

	/**
	 * Launch the application.
	 */
	public static TreeMap<String, String> search_word(TreeMap<String, String> dictmap,Socket clientSocket,String ser_word) {
		
		try {
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		oos.writeObject(dictmap);
		oos.flush();
//		TreeMap<String, String> dictmap = new TreeMap<String, String>();
		ObjectInputStream ios = new ObjectInputStream(clientSocket.getInputStream());
		try {
			dictmap = (TreeMap<String, String>) ios.readObject();
//			System.out.print("lol");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set set = dictmap.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			System.out.print("Key:"+mentry.getKey()+" Value is:"+mentry.getValue());
		}
		
		Scanner sc = new Scanner(System.in);	
//		System.out.println("Enter word to be searched");
//		String ser_word = sc.nextLine();
		if(dictmap.containsKey(ser_word)) {
//			System.out.println("Word already exists add a new word or go back to main menu");
			System.out.println(dictmap.get(ser_word));
		}
		else {

			System.out.println("Word doesn't exist");
//			String word_meaning = sc.nextLine();
//			dictmap.put(new_word, word_meaning);
		}
		set = dictmap.entrySet();
		iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			System.out.print("Key:"+mentry.getKey()+" Value is:"+mentry.getValue());
		}
		}catch (IOException e)
		{
			e.printStackTrace();
		} 
		return dictmap;
	}
	public static void client_call() {
//		Socket clientSocket = null;
		try 
		{
			
			clientSocket = new Socket("localhost", 9043);
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			
		    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
			System.out.println("Send data");

			
			Scanner sc = new Scanner(System.in);
			
			
			
			System.out.println("Enter 1 for add 2 for delete 3 for retrieve");

			int option = sc.nextInt();
			TreeMap<String, String>dictmap_updated = new TreeMap<String, String>();
			switch(option) {
			case 1:

//				dictmap=add_word(dictmap,clientSocket);
				System.out.println("Still error?");
				client_call();
//				oos.writeObject(dictmap_updated);
//				oos.flush();
				break;
			case 2:
//				dictmap=delete_word(dictmap,clientSocket);
				System.out.println("Still error?");
				client_call();
//				oos.writeObject(dictmap_updated);
//				oos.flush();
				break;
			case 3:
//				search_word(dictmap);
//				dictmap=search_word(dictmap,clientSocket);
				System.out.println("Still error?");
				client_call();
//				oos.writeObject(dictmap_updated);
//				oos.flush();
				break;
			default:
				System.out.print("default");
				break;
			}
			
		} 
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			// Close the socket
			if (clientSocket != null)
			{
				try
				{
					clientSocket.close();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}

	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					uitest window = new uitest();
					window.frame.setVisible(true);
//					client_call();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public uitest() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
//		client_call();
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search_val = textField.getText();
				TreeMap<String, String> dictmap1;
				dictmap1 = search_word(dictmap,clientSocket,search_val);
				client_call();
			}
		});
		frame.getContentPane().add(btnSearch, BorderLayout.CENTER);
		
		JButton btnAdd = new JButton("Add");
		frame.getContentPane().add(btnAdd, BorderLayout.EAST);
	}

}
