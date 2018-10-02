package GameGUI;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import ClientSide_Demo.Packet;
import Protocol.GameList;
import Protocol.Invite;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class GameWindow {

	protected JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField score_p1;
	private JTextField score_p2;
	private JTextField score_p3;
	private JTextField score_p4;
	private ArrayList<JTextField> textfield_array;
	private JButton btnInvite;
	
	private static int s_p1 = 0;
	private static int s_p2 = 0;
	private static int s_p3 = 0;
	private static int s_p4 = 0;
	private int c = 0;
	
    private Gson gson;
	private BufferedWriter out;
	private String usrnm;
	
	private JList<String> list_gw;

	/**
	 * Launch the application.
	 */
	public void gameGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//new GameWindow();
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
	public GameWindow(BufferedWriter out, String usrnm) {
		this.out = out;
		this.usrnm = usrnm;
		gson = new Gson();
		
		initialize();
	}
	
	//public GameWindow() {}

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
		
		JTextField jtf = new JTextField();
	    jtf.setDocument(new LimitedPlainDocument(1));
	    for (int i = 0; i < table.getColumnCount(); i++)
	    	table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(jtf));

	    
		JButton btnClearTable = new JButton("Reset Game");
		btnClearTable.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				winner(s_p1, s_p2, s_p3, s_p4);
				tableInit();				
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
		
		textfield_array=new ArrayList<JTextField>();
		textfield_array.add(textField);
		textfield_array.add(textField_1);
		textfield_array.add(textField_2);
		textfield_array.add(textField_3);
		
		JLabel lblWaitlist = new JLabel("Waitlist");
		lblWaitlist.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWaitlist.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaitlist.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblWaitlist.setBounds(630, 11, 134, 24);
		frame.getContentPane().add(lblWaitlist);
		
        DefaultListModel<String> listPlayer_gw = new DefaultListModel<>();						// Player List
		list_gw = new JList<String>(listPlayer_gw);

		list_gw.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		list_gw.setBounds(630, 46, 134, 219);
		frame.getContentPane().add(list_gw);
		
		
		
		btnInvite = new JButton("Invite");									//Inviting Players to join the game
		btnInvite.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnInvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] invite_list=list_gw.getSelectedValuesList().toArray(new String[] {});
				invitePlayers(invite_list);
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
		
		score_p1 = new JTextField();
		score_p1.setEditable(false);
		score_p1.setBounds(102, 427, 86, 20);
		frame.getContentPane().add(score_p1);
		score_p1.setColumns(10);
		
		score_p2 = new JTextField();
		score_p2.setEditable(false);
		score_p2.setBounds(214, 427, 86, 20);
		frame.getContentPane().add(score_p2);
		score_p2.setColumns(10);
		
		score_p3 = new JTextField();
		score_p3.setEditable(false);
		score_p3.setBounds(328, 427, 86, 20);
		frame.getContentPane().add(score_p3);
		score_p3.setColumns(10);
		
		score_p4 = new JTextField();
		score_p4.setEditable(false);
		score_p4.setBounds(439, 427, 86, 20);
		frame.getContentPane().add(score_p4);
		score_p4.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 393, 64, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Calculate Score");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableOperations();
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
				table.setEnabled(false);			
			}
		});
		btnFreeze.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnFreeze.setBounds(630, 343, 134, 23);
		frame.getContentPane().add(btnFreeze);
		
		JButton btnUnfreeze = new JButton("Unfreeze");
		btnUnfreeze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);
			}
		});
		btnUnfreeze.setBorder(new LineBorder(new Color(0,0,0),2));
		btnUnfreeze.setBounds(630, 484, 134, 20);
		frame.getContentPane().add(btnUnfreeze);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStartGame.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnStartGame.setBounds(154, 481, 89, 23);
		frame.getContentPane().add(btnStartGame);
		
		JButton btnEndGame = new JButton("End Game");
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				winner(s_p1, s_p2, s_p3, s_p4);												// Calling winner function
				closeGame();
			}
		});
		btnEndGame.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnEndGame.setBounds(381, 481, 89, 23);
		frame.getContentPane().add(btnEndGame);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {						// Frame window controls
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure you want to close this window?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            closeGame();															// Calling closeGame() 
		        }
		    }
		});
	}																						// Close initialize()
	

	public void winner(int s_p1, int s_p2, int s_p3, int s_p4) {							// Deciding Winner
		if(s_p1 > s_p2 && s_p1 > s_p3 && s_p1 > s_p4)
			JOptionPane.showMessageDialog(frame,"Player 1 Wins!!!");
		else if (s_p2 > s_p1 && s_p2 > s_p3 && s_p2 > s_p4)
			JOptionPane.showMessageDialog(frame,"Player 2 Wins!!!");
		else if (s_p3 > s_p1 && s_p3 > s_p2 && s_p3 > s_p4)
			JOptionPane.showMessageDialog(frame,"Player 3 Wins!!!");
		else if (s_p4 > s_p1 && s_p4 > s_p2 && s_p4 > s_p3)
			JOptionPane.showMessageDialog(frame,"Player 4 Wins!!!");
		else
			JOptionPane.showMessageDialog(frame,"No Winner!!!");
	}
	
	public void tableInit() {
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
				}));
		
		s_p1 = 0;
		s_p2 = 0;
		s_p3 = 0;
		s_p4 = 0;
		
		score_p1.setText("");
		score_p2.setText("");
		score_p3.setText("");
		score_p4.setText("");
		
		JTextField jtf = new JTextField();
	    jtf.setDocument(new LimitedPlainDocument(1));
	    for (int i = 0; i < table.getColumnCount(); i++)
	    	table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(jtf));

	}

	public void updateGwGUI(String[] wait_list)	{												//Updating list in Game Window
		DefaultListModel<String> listPlayer_gw=(DefaultListModel<String>) list_gw.getModel();
		
		listPlayer_gw.removeAllElements();
		System.out.println("Inside Game GUI: " + wait_list[0]);
		for(String item:wait_list) {
        	listPlayer_gw.addElement(item);
        }
	}
	
	
	JTable theTable = new JTable();									//your table
	boolean pressingCTRL=false;										//flag, if pressing CTRL it is true, otherwise it is false.
	Vector<int[]> selectedCells = new Vector<int[]>();				//int[]because every entry will store {cellX,cellY}

	
	public void tableOperations() {										// To Calculate the score as per selected Cells
		
	try {
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
	   
		int rc1 = table.getSelectionModel().getMaxSelectionIndex();
		int rc2 = table.getSelectionModel().getMinSelectionIndex();
		int cc1 = table.getColumnModel().getSelectionModel().getMaxSelectionIndex();
		int cc2 = table.getColumnModel().getSelectionModel().getMinSelectionIndex();
		
		int rs = rc1-rc2;
        int cs = cc1-cc2; 
           
        if(rs > 0) {
        	c = 0;
        	for(int i=rc2; i<=rc1; i++) 
        		if(table.getValueAt(i, cc1) == null) {
        			c = 1;
        			break;
        		}
        	    if(c == 0) {
        	    	s_p1 = s_p1 + (rs + 1);
        	    	score_p1.setText(Integer.toString(s_p1));
        	    }

        	   else
        		   JOptionPane.showMessageDialog(frame,"Row Word selected with Blank Cell!!!", "Warning", JOptionPane.WARNING_MESSAGE);
         }
           
         else if(cs > 0) {
        	 c = 0;
        	 for(int j=cc2; j<=cc1; j++)
        		 if(table.getValueAt(rc1, j) == null) {
        			c = 1;
        			break;
        		 }
	         if(c == 0) {										//Successful selection of correct word
	        	 s_p1 = s_p1 + (cs + 1);
        		 score_p1.setText(Integer.toString(s_p1));      		   
	         }
	         else
	        	 JOptionPane.showMessageDialog(frame,"Column Word selected with Blank Cell!!!", "Warning", JOptionPane.WARNING_MESSAGE);
         }
           
         else if(table.getValueAt(rc2, cc2) != null) {
        	//JOptionPane.showMessageDialog(new GameWindow().frame,"One letter word is not allowed!!!", "Warning", JOptionPane.WARNING_MESSAGE);
           	s_p1 = s_p1 + (cs + 1);
  		 	score_p1.setText(Integer.toString(s_p1));
         }
         else
           JOptionPane.showMessageDialog(frame,"Blank Cell selected!!!", "Warning", JOptionPane.WARNING_MESSAGE);
	}
	catch(Exception e) {
		JOptionPane.showMessageDialog(frame,"No Word Selection!!!", "Warning", JOptionPane.WARNING_MESSAGE);
	}
	}
	
	public void invitePlayers(String[] invitation_list)  				//Sending Invitation List to the Server
	{
		try {
		Packet<Invite> outPacket=new Packet<Invite>("Invite",new Invite(invitation_list),usrnm);
		out.write(gson.toJson(outPacket)+ "\n");
		out.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// Updating the Game List into the TextField's
	
	public void updateGameList(String[] game_list) {
		for (int i=0;i<game_list.length;i++) {
			System.out.println(game_list[i]);
			textfield_array.get(i).setText(game_list[i]);
		}
	}
	
	public void closeGame() {											// Closing Window
		
		try {
			
        Packet<GameList> outPacket = new Packet<GameList>("EndGame", null, usrnm);
        out.write(gson.toJson(outPacket) + "\n");
        out.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void closeGameGUI() {
		frame.dispose();
	}
	
	public void disableInviteButton() {
		btnInvite.setEnabled(false);
	}
	

}



@SuppressWarnings("serial")
class LimitedPlainDocument extends javax.swing.text.PlainDocument {
	  private int maxLen = -1;
	  /** Creates a new instance of LimitedPlainDocument */     
	  public LimitedPlainDocument() {}
	  public LimitedPlainDocument(int maxLen) { this.maxLen = maxLen; }
	  public void insertString(int param, String str, javax.swing.text.AttributeSet attributeSet) throws javax.swing.text.BadLocationException {
	    if (str != null && maxLen > 0 && this.getLength() + str.length() > maxLen) {
	      java.awt.Toolkit.getDefaultToolkit().beep();
		  JOptionPane.showMessageDialog(new GameWindow(null, null).frame,"Only 1 Alphabet at a time!!", "Warning", JOptionPane.WARNING_MESSAGE);
	      return;
	    }
	    else if(!str.matches("[A-Za-z]"))
	    {
	    	java.awt.Toolkit.getDefaultToolkit().beep();
	    	JOptionPane.showMessageDialog(new GameWindow(null, null).frame,"Only Alphabets are allowed!!", "Warning", JOptionPane.WARNING_MESSAGE);
	    	return;
	    }
	    super.insertString(param, str, attributeSet);
	  }
	}
