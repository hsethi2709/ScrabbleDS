package GameGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WaitListGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaitListGUI window = new WaitListGUI();
					window.frame.setVisible(true);
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
		frame.setBounds(100, 100, 299, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPlayersConnected = new JLabel("Players Connected");
		lblPlayersConnected.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblPlayersConnected.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayersConnected.setBounds(54, 11, 165, 30);
		frame.getContentPane().add(lblPlayersConnected);
		
        DefaultListModel<String> listPlayer = new DefaultListModel<>();
        listPlayer.addElement("Player_1");
        listPlayer.addElement("Player_2");
        listPlayer.addElement("Player_3");
        listPlayer.addElement("Player_4");
        listPlayer.addElement("Player_5");
        
		JList<String> list = new JList<String>(listPlayer);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(54, 52, 165, 240);
		frame.getContentPane().add(list);
		
		JButton btnCreateGame = new JButton("Create Game");
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnLogout.setBounds(54, 351, 165, 23);
		frame.getContentPane().add(btnLogout);
	}
}
