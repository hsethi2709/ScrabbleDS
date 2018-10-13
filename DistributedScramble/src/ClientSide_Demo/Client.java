package ClientSide_Demo;

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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

import com.google.gson.Gson;
import Protocol.Login;


public class Client {
    private static String ip = ""; 		//"localhost";
	private static String usrnm = "";		
    private static int port = 0;		//3000;
    
    private Gson gson;
    private static JFrame frame;
    private Socket socket;
    
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    
    private BufferedWriter out;

    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }
    
    public Client() {
		gson=new Gson();			 
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
		
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ip = textField_1.getText().trim();
					port = Integer.parseInt(textField.getText().trim());
					usrnm = textField_2.getText().trim();
					

					try {
						socket=new Socket(ip, port);												//Socket Connection
					} catch (IOException e1) {
						System.out.println("Server is not listening!!!");
						JOptionPane.showMessageDialog(frame, "Server is not listening", "Error", 1);
					} catch (NumberFormatException e1) {
						System.out.println("Wrong Format entered!!!");
					} 
					
					try {
						ListeningThread listeningThread = new ListeningThread(socket,usrnm);				//Calling Listening Thread
						listeningThread.start();
					} catch (NullPointerException e1) {
						System.out.println("No connection with Server!!!");
						
					}

			            
			        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			        Packet<Login> outPacket = new Packet<Login>("Login", new Login(usrnm),usrnm);
			        out.write(gson.toJson(outPacket) + "\n");
			        out.flush();    
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
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
