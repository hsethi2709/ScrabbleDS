package GameGUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WaitListGUI {

	private JFrame frame;
	private JList<String> list_wl;
	GameWindow gw;
	/**
	 * Launch the application.
	 */
	public void main(String args) {
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
	
	public WaitListGUI() {
		initialize();
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
		
		JButton btnCreateGame = new JButton("Create Game");								//Creating game
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gw = new GameWindow();
				gw.main(null);
				frame.setVisible(false);
			}
		});
		btnCreateGame.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnCreateGame.setBounds(54, 317, 165, 23);
		frame.getContentPane().add(btnCreateGame);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnLogout.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnLogout.setBounds(54, 398, 165, 23);
		frame.getContentPane().add(btnLogout);
		
		JButton btnJoinGame = new JButton("Join Game");
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnJoinGame.setEnabled(false);
		btnJoinGame.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnJoinGame.setBounds(54, 351, 165, 23);
		frame.getContentPane().add(btnJoinGame);
	}
	
	public void updateWlGUI(String[] wait_list)	{													//Updating waiting player list
		DefaultListModel<String> listPlayer_wl=(DefaultListModel<String>) list_wl.getModel();
		
		listPlayer_wl.removeAllElements();
		System.out.println("Inside GUI: " + wait_list[0]);
		for(String item:wait_list) {
        	listPlayer_wl.addElement(item);
        }
	}
}