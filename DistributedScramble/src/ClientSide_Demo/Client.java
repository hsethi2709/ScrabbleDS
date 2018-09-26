package ClientSide_Demo;

import GameGUI.WaitListGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.google.gson.Gson;

import Protocol.*;
import javax.swing.JTextField;

public class Client {
    private static String ip = ""; 		//"localhost";
	private static String usrnm = "";		
    private static int port = 0;		//3000;
    private static Gson gson = new Gson();
    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    
    public Client() {
    	initialize();
    }
    
    private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 476, 256);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPlayersConnected = new JLabel("Port Number");
		lblPlayersConnected.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblPlayersConnected.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayersConnected.setBounds(54, 11, 165, 30);
		frame.getContentPane().add(lblPlayersConnected);
		
		textField = new JTextField();
		textField.setBounds(242, 11, 174, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblServerIphostname = new JLabel("Server IP/Hostname");
		lblServerIphostname.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerIphostname.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblServerIphostname.setBounds(54, 64, 165, 30);
		frame.getContentPane().add(lblServerIphostname);
		
		textField_1 = new JTextField();
		textField_1.setBounds(242, 64, 174, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		
		//if(!textField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals(""))
			//btnConnect.setEnabled(true);
		
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				port = Integer.parseInt(textField.getText().trim());
				ip = textField_1.getText().trim();
				usrnm = textField.getText().trim();

		        try (Socket socket = new Socket(ip, port);) {
		            ListeningThread listeningThread = new ListeningThread(socket);
		            listeningThread.start();
		            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
		            Packet<Login> outPacket = new Packet<Login>("Login", new Login(usrnm));
		            out.write(gson.toJson(outPacket) + "\n");
		            out.flush();
		            Thread.sleep(1000);
		            listeningThread.close();
		            socket.close();
		        }
		        catch(UnknownHostException uexp) {
		        	System.out.println(uexp);
		        }
		        catch(IOException ioexp) {
		        	System.out.println(ioexp);
		        }
		        catch(InterruptedException iexp) {
		        	System.out.println(iexp);
		        }
		        
		        new WaitListGUI().main(usrnm);;
		        frame.setVisible(false);
			}
		});
		btnConnect.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnConnect.setBounds(172, 176, 116, 30);
		frame.getContentPane().add(btnConnect);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(54, 119, 165, 30);
		frame.getContentPane().add(lblUsername);
		
		textField_2 = new JTextField();
		textField_2.setBounds(242, 119, 174, 30);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
	}
}
