package GameGUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWindow {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
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
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 757, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBorder(new LineBorder(Color.GREEN, 2));
		table.setCellSelectionEnabled(true);

		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(10, 11, 577, 336);
		frame.getContentPane().add(table);
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer.setBounds(20, 358, 86, 24);
		frame.getContentPane().add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer_1.setBounds(130, 358, 86, 24);
		frame.getContentPane().add(lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player 3");
		lblPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer_2.setBounds(241, 358, 86, 24);
		frame.getContentPane().add(lblPlayer_2);
		
		JLabel lblPlayer_3 = new JLabel("Player 4");
		lblPlayer_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer_3.setBounds(352, 358, 86, 24);
		frame.getContentPane().add(lblPlayer_3);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(20, 393, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(130, 393, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(241, 393, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(352, 393, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblWaitlist = new JLabel("Waitlist");
		lblWaitlist.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWaitlist.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaitlist.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblWaitlist.setBounds(597, 11, 134, 24);
		frame.getContentPane().add(lblWaitlist);
		
        DefaultListModel<String> listPlayer = new DefaultListModel<>();
        listPlayer.addElement("Player_1");
        listPlayer.addElement("Player_2");
        listPlayer.addElement("Player_3");
        listPlayer.addElement("Player_4");
        listPlayer.addElement("Player_5");
		
		JList<String> list_1 = new JList<String>(listPlayer);

		list_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_1.setBounds(597, 46, 134, 219);
		frame.getContentPane().add(list_1);
		
		JButton btnInvite = new JButton("Invite");
		btnInvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInvite.setBounds(618, 288, 89, 23);
		frame.getContentPane().add(btnInvite);
	}
}
