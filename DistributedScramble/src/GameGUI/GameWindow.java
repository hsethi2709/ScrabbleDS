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
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class GameWindow {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

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
		frame.setBounds(100, 100, 823, 576);
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
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column",
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setBounds(10, 11, 577, 336);
		frame.getContentPane().add(table);
		
		//DefaultTableModel tm = (DefaultTableModel)table.getModel();
		
		JButton btnClearTable = new JButton("Clear Table");
		btnClearTable.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//tm.getDataVector().removeAllElements();
				//table.repaint();
				//tm.fireTableDataChanged();
			}
		});
		btnClearTable.setBounds(630, 445, 134, 24);
		frame.getContentPane().add(btnClearTable);
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer.setBounds(102, 358, 86, 24);
		frame.getContentPane().add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer_1.setBounds(214, 358, 86, 24);
		frame.getContentPane().add(lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player 3");
		lblPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer_2.setBounds(328, 358, 86, 24);
		frame.getContentPane().add(lblPlayer_2);
		
		JLabel lblPlayer_3 = new JLabel("Player 4");
		lblPlayer_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer_3.setBounds(439, 358, 86, 24);
		frame.getContentPane().add(lblPlayer_3);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(102, 393, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(214, 393, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(328, 393, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(439, 393, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblWaitlist = new JLabel("Waitlist");
		lblWaitlist.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWaitlist.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaitlist.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblWaitlist.setBounds(630, 11, 134, 24);
		frame.getContentPane().add(lblWaitlist);
		
        DefaultListModel<String> listPlayer = new DefaultListModel<>();
        listPlayer.addElement("Player_1");
        listPlayer.addElement("Player_2");
        listPlayer.addElement("Player_3");
        listPlayer.addElement("Player_4");
        listPlayer.addElement("Player_5");
		
		JList<String> list_1 = new JList<String>(listPlayer);

		list_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_1.setBounds(630, 46, 134, 219);
		frame.getContentPane().add(list_1);
		
		JButton btnInvite = new JButton("Invite");
		btnInvite.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnInvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInvite.setBounds(653, 286, 89, 23);
		frame.getContentPane().add(btnInvite);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblScore.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblScore.setBounds(10, 427, 64, 20);
		frame.getContentPane().add(lblScore);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(102, 427, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(214, 427, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(328, 427, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(439, 427, 86, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 393, 64, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Calculate Score");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				something();
			}
		});
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton.setBounds(630, 411, 134, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pass");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnNewButton_1.setBounds(630, 377, 134, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnFreeze = new JButton("Freeze");
		btnFreeze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFreeze.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnFreeze.setBounds(630, 343, 134, 23);
		frame.getContentPane().add(btnFreeze);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnStartGame.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnStartGame.setBounds(154, 481, 89, 23);
		frame.getContentPane().add(btnStartGame);
		
		JButton btnEndGame = new JButton("End Game");
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEndGame.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnEndGame.setBounds(381, 481, 89, 23);
		frame.getContentPane().add(btnEndGame);
	}
	
	
	JTable theTable = new JTable();									//your table
	boolean pressingCTRL=false;										//flag, if pressing CTRL it is true, otherwise it is false.
	Vector<int[]> selectedCells = new Vector<int[]>();				//int[]because every entry will store {cellX,cellY}

	public void something(){										// To Calculate the score as per selected Cells
	   KeyListener tableKeyListener = new KeyAdapter() {

	      @Override
	      public void keyPressed(KeyEvent e) {
	         if(e.getKeyCode()==KeyEvent.VK_CONTROL){					//check if user is pressing CTRL key
	            pressingCTRL=true;
	         }
	      }

	      @Override
	      public void keyReleased(KeyEvent e) {
	         if(e.getKeyCode()==KeyEvent.VK_CONTROL){					//check if user released CTRL key
	            pressingCTRL=false;
	         }
	      }
	   };

	   MouseListener tableMouseListener = new MouseAdapter() {

	      @Override
	      public void mouseClicked(MouseEvent e) {
	         if(pressingCTRL){										//check if user is pressing CTRL key
	            int row = theTable.rowAtPoint(e.getPoint());		//get mouse-selected row
	            int col = theTable.columnAtPoint(e.getPoint());		//get mouse-selected col
	            int[] newEntry = new int[]{row,col};				//{row,col}=selected cell
	            if(selectedCells.contains(newEntry)){
	            													//cell was already selected, de-select it
	               selectedCells.remove(newEntry);
	            }else{
	            													//cell was not selected
	               selectedCells.add(newEntry);
	            }
	         }
	      }
	   };
	   theTable.addKeyListener(tableKeyListener);
	   theTable.addMouseListener(tableMouseListener);
	}
}
