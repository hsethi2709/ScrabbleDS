package GameGUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.google.gson.Gson;

import ClientSide_Demo.Packet;
import Protocol.GameList;
import Protocol.Reply;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.EventQueue;

import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class WaitListGUI {

	private JFrame frame;
	private JList<String> list_wl;
	GameWindow gw;
	BufferedWriter send_message;
	Gson gson;
	JButton btnCreateGame;
	JButton btnJoinGame;
	
	private String username;
	/**
	 * Launch the application.
	 */

	public void waitGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public WaitListGUI(BufferedWriter out,String username) {
		gson = new Gson();
		send_message = out;
		initialize();
		this.username = username;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 299, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPlayersConnected = new JLabel("Players Connected");
		lblPlayersConnected.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblPlayersConnected.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayersConnected.setBounds(54, 11, 165, 30);
		frame.getContentPane().add(lblPlayersConnected);
		
        DefaultListModel<String> listPlayer_wl = new DefaultListModel<>();				//Waiting player list
		list_wl = new JList<String>(listPlayer_wl);
		
		list_wl.setBorder(new LineBorder(new Color(0, 0, 0)));
		list_wl.setBounds(54, 52, 165, 240);
		frame.getContentPane().add(list_wl);
		

		btnCreateGame = new JButton("Create Game");								//Creating game
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        Packet<GameList> outPacket = new Packet<GameList>("CreateGame",null,username);
		        try {
					send_message.write(gson.toJson(outPacket) + "\n");
					send_message.flush();  
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
		});
		btnCreateGame.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnCreateGame.setBounds(54, 317, 165, 23);
		frame.getContentPane().add(btnCreateGame);
		
		btnJoinGame = new JButton("Join Game");
		
		btnJoinGame.setEnabled(false);
		btnJoinGame.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnJoinGame.setBounds(54, 351, 165, 23);
		frame.getContentPane().add(btnJoinGame);
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Packet<Reply> outPacket = new Packet<Reply>("JoinGame",null,username);
		        try {
					send_message.write(gson.toJson(outPacket) + "\n");
					send_message.flush();  
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
		});
		
		JButton btnLogout = new JButton("Logout");										//Calling Socket Close
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					logOut();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogout.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnLogout.setBounds(54, 398, 165, 23);
		frame.getContentPane().add(btnLogout);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);							// Preventing Frame from Closing abruptly
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {						// Frame window controls
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure you want to close this window?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            logOut();															// Calling closeGame() 
		        }
		    }
		});
	}																							// Closing Initialize()
	
	public void logOut() {
		try {
	        Packet<GameList> outPacket = new Packet<GameList>("Logout", null, username);
	        send_message.write(gson.toJson(outPacket) + "\n");
	        send_message.flush();
			} catch (Exception e) {
				System.out.println(e);
			}
		frame.dispose();
	}
	
	public void updateWlGUI(String[] wait_list)	{													//Updating waiting player list
		DefaultListModel<String> listPlayer_wl = (DefaultListModel<String>) list_wl.getModel();
		
		listPlayer_wl.removeAllElements();
		System.out.println("Inside GUI: " + wait_list[0]);
		for(String item:wait_list) {
        	listPlayer_wl.addElement(item);
        }
	}
	
	public void enableJoinButton() {
		btnJoinGame.setEnabled(true);
	}
	
	public void disbleJoinButton() {
		btnJoinGame.setEnabled(false);
	}
	
	public void enableCreateButton() {
		btnCreateGame.setEnabled(true);
	}

	public void disableCreateButton() {
		btnCreateGame.setEnabled(false);
	}
}