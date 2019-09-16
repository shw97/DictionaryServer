/**
 * 
 */

/**
 *@author Shwetha Srinivasan
 * @student_id 1073180
 *
 */
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
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

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

//import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;  
public class Dict{


	
	/**
	 * @param argss
	 */
	/**
	 * @param args
	 */
	public static TreeMap<String, String> dictmap = new TreeMap<String, String>();
	public static String add_word(Socket clientSocket,String add_word,String add_meaning) {
		String return_val="Not Working";
		try {
			
//			TreeMap<String, String> dictmap = new TreeMap<String, String>();
			ObjectInputStream ios = new ObjectInputStream(clientSocket.getInputStream());
			try {
				dictmap = (TreeMap<String, String>) ios.readObject();
				System.out.print("lol");
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
//		System.out.println("Enter word to be added");
//		String new_word = sc.nextLine();
		if(dictmap.containsKey(add_word)) {
			System.out.println("Word already exists add a new word or go back to main menu");
			return_val="Word already exists add a new word";
		}
		else {

//			System.out.println("Enter the meaning");
//			String word_meaning = sc.nextLine();
			dictmap.put(add_word, add_meaning);
			return_val="Word Added";
		}
		set = dictmap.entrySet();
		iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			System.out.print("Key:"+mentry.getKey()+" Value is:"+mentry.getValue());
		}
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		oos.writeObject(dictmap);
		oos.flush();
		}catch (IOException e)
		{
			e.printStackTrace();
		} 
//		return dictmap;
//		update(clientSocket);
//		search_word(clientSocket,add_word);
		return return_val;
	}
	public static String delete_word(Socket clientSocket, String delete_word) {
		String return_val="Not_working";
//		Set set = dictmap.entrySet();
//		Iterator iterator = set.iterator();
//		while(iterator.hasNext()) {
//			Map.Entry mentry = (Map.Entry)iterator.next();
//			System.out.print("Key:"+mentry.getKey()+" Value is:"+mentry.getValue());
//		}
		try {
			
			
//			TreeMap<String, String> dictmap = new TreeMap<String, String>();
			ObjectInputStream ios = new ObjectInputStream(clientSocket.getInputStream());
			try {
				dictmap = (TreeMap<String, String>) ios.readObject();
				System.out.print("lol");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		Scanner sc = new Scanner(System.in);	
//		System.out.println("Enter word to be deleted");
//		String del_word = sc.nextLine();
		if(dictmap.containsKey(delete_word)) {
//			System.out.println("Word already exists add a new word or go back to main menu");
			dictmap.remove(delete_word);
			return_val = "Word Removed";
		}
		else {

			System.out.println("Word doesn't exist");
//			String word_meaning = sc.nextLine();
//			dictmap.put(new_word, word_meaning);
			return_val = "Word doesn't exist";
		}
		Set set = dictmap.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			System.out.print("Key:"+mentry.getKey()+" Value is:"+mentry.getValue());
		}
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		oos.writeObject(dictmap);
		oos.flush();
		}catch (IOException e)
		{
			e.printStackTrace();
		} 
//		update(clientSocket);
//		search_word(clientSocket,delete_word);
		return return_val; 
//		return dictmap;
	}
//	public static String update(Socket clientSocket) {
//		String return_val = "Updated";
//		try {
//		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
//		oos.writeObject(dictmap);
//		oos.flush();
////		TreeMap<String, String> dictmap = new TreeMap<String, String>();
//		ObjectInputStream ios = new ObjectInputStream(clientSocket.getInputStream());
//		try {
//			dictmap = (TreeMap<String, String>) ios.readObject();
////			System.out.print("lol");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Set set = dictmap.entrySet();
//		Iterator iterator = set.iterator();
//		while(iterator.hasNext()) {
//			Map.Entry mentry = (Map.Entry)iterator.next();
//			System.out.print("Key:"+mentry.getKey()+" Value is:"+mentry.getValue());
//		}
//		}catch (IOException e)
//		{
//			e.printStackTrace();
//		} 
//		return return_val;
//	}
	public static String search_word(Socket clientSocket, String search_val) {
		String return_val="Not_worked";
		try {
			
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
		if(dictmap.containsKey(search_val)) {
//			System.out.println("Word already exists add a new word or go back to main menu");
			System.out.println(dictmap.get(search_val));
			return_val = dictmap.get(search_val);
		}
		else {

			System.out.println("Word doesn't exist");
			return_val = "Word doesn't exist";
//			String word_meaning = sc.nextLine();
//			dictmap.put(new_word, word_meaning);
		}
		set = dictmap.entrySet();
		iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next();
			System.out.print("Key:"+mentry.getKey()+" Value is:"+mentry.getValue());
		}
		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
		oos.writeObject(dictmap);
		oos.flush();
//		ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
//		oos.writeObject(dictmap);
//		oos.flush();
		}catch (IOException e)
		{
			e.printStackTrace();
		} 
//		return dictmap;
//		update(clientSocket);
		return return_val;
	}
	
	public static String client_call(int option,String word,String meaning, String ip_val) {
		Socket clientSocket = null;
		String return_val = "Client_NW";
//		Set set = dictmap.entrySet();
//		Iterator iterator = set.iterator();
//		while(iterator.hasNext()) {
//			Map.Entry mentry = (Map.Entry)iterator.next();
//			System.out.print("Key:"+mentry.getKey()+" Value is:"+mentry.getValue());
//			System.out.println("In client");
//		}
//		String ip_val = args[0];
		try 
		{
			
			clientSocket = new Socket(ip_val, 9043);
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			
		    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
		    output.writeUTF("Welcome");
			System.out.println("Send data");
//			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			
			
			Scanner sc = new Scanner(System.in);
			if(option == 3) {
				return_val=search_word(clientSocket,word);
//				oos.writeObject(dictmap);
//				oos.flush();
			}
			else if(option == 2) {
				return_val=delete_word(clientSocket,word);
			}
			else if(option == 1) {
				return_val=add_word(clientSocket,word,meaning);
			}
			
//			
//			System.out.println("Enter 1 for add 2 for delete 3 for retrieve");
//
//			int option = sc.nextInt();
//			TreeMap<String, String>dictmap_updated = new TreeMap<String, String>();
//			switch(option) {
//			case 1:
//
//				dictmap=add_word(dictmap,clientSocket);
//				System.out.println("Still error?");
//				client_call();
////				oos.writeObject(dictmap_updated);
////				oos.flush();
//				break;
//			case 2:
//				dictmap=delete_word(dictmap,clientSocket);
//				System.out.println("Still error?");
//				client_call();
////				oos.writeObject(dictmap_updated);
////				oos.flush();
//				break;
//			case 3:
////				search_word(dictmap);
////				dictmap=search_word(dictmap,clientSocket);
//				System.out.println("Still error?");
////				client_call();
////				oos.writeObject(dictmap_updated);
////				oos.flush();
//				break;
//			}
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
		return return_val;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.print("Hello World");
		String ip_val = args[0];
		JFrame f=new JFrame();//creating instance of JFrame  
		final JTextField tf=new JTextField();  
	    tf.setBounds(50,50, 150,20);  
		JButton bsearch=new JButton("Search");//creating instance of JButton  
		bsearch.setBounds(50,200,150, 20);//x axis, y axis, width, height
		JButton bdelete=new JButton("Delete");//creating instance of JButton  
		bdelete.setBounds(50,250,150, 20);//x axis, y axis, width, height
		JButton badd=new JButton("Add");//creating instance of JButton  
		badd.setBounds(50,300,150, 20);//x axis, y axis, width, height
//		JButton bupdate=new JButton("Update Server");//creating instance of JButton  
//		bupdate.setBounds(50,400,150, 20);//x axis, y axis, width, height
		final JTextField tf2=new JTextField();  
	    tf2.setBounds(50,100, 150,20);  
		f.add(tf);         
		f.add(bsearch);//adding button in JFrame  
		f.add(tf2); 
		f.add(bdelete);
		f.add(badd);
//		f.add(bupdate);
		
		f.setSize(400,500);//400 width and 500 height  
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible  
		bsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search_val=tf.getText();
//				System.out.println(search_val);
				if(search_val.length()<1) {
					tf2.setText("Enter word to search");
				}
				else {
				String return_val=client_call(3,search_val,null,ip_val);
//				String search_val=tf.getText();
				tf2.setText(return_val);
				}
			}
		});
		bdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String delete_val=tf.getText();
				if(delete_val.length()<1) {
					tf2.setText("Enter word to delete");
//					break;
				}
				else {
				String return_val=client_call(2,delete_val,null,ip_val);
//				String search_val=tf.getText();
				tf2.setText(return_val);
				}
			}
		});
		badd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String add_val=tf.getText();
				String add_val_mean=tf2.getText();
				if(add_val.length()<1) {
					tf2.setText("Enter word to add");
				}
				else if(add_val_mean.length()<1) {
					tf2.setText("Enter meaning");
				}
				else {
				String return_val=client_call(1,add_val,add_val_mean,ip_val);
//				String search_val=tf.getText();
				tf2.setText(return_val);
				}
			}
		});
//		bupdate.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//				String return_val=client_call(4,null,null);
////				String search_val=tf.getText();
//				tf2.setText(return_val);
//			}
//		});
//		client_call();
	}
	
}
