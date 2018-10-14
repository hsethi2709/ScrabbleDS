package GameGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import ClientSide_Demo.Packet;
import Protocol.GameList;
import Protocol.Insert;
import Protocol.Invite;
import Protocol.Reply;
import Protocol.Vote;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;											
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class GameWindow {

	protected JFrame frame;
	private JTable table;
	private DefaultTableModel myModel;
	private JLabel lblPlayer;
	private JLabel lblPlayer_1;
	private JLabel lblPlayer_2;
	private JLabel lblPlayer_3;
	private JButton btnFreeze;
	private JButton btnClearTable;
	private JButton btnpass;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField score_p1;
	private JTextField score_p2;
	private JTextField score_p3;
	private JTextField score_p4;
	private JButton btnStartGame;
	private JTextField jtf;
	private ArrayList<JTextField> textfield_array;
	private ArrayList<JLabel> label_array;
	
	private JButton btnInvite;
	
	private static int s_p1 = 0;
	private static int s_p2 = 0;
	private static int s_p3 = 0;
	private static int s_p4 = 0;
	private int c = 0, row_t = 0, column_t = 0;
	
    private Gson gson;
	private BufferedWriter out;
	private String usrnm;
	static boolean addedmouseevent = false;
	
	private JList<String> list_gw;
	private HashMap<String, String> hm = new HashMap<String, String>();

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
				
																					// New Code added for mouse listener
				//Container content = frame.getContentPane();
			    //content.add(new MouseMovement(), content);
			    frame.setVisible(true);
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
		
		frame = new JFrame(usrnm);
		frame.setBounds(100, 100, 823, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		table = new JTable();
		myModel = new MyDefaultTableModel(21, 21);				//Calling Class for setting CellEditable Feature
		table.setModel(myModel);
		setTableEditable(true);																						
		table.setGridColor(new Color(0, 0, 0));
		table.setBackground(new Color(100, 149, 237));
		table.addMouseListener(new MouseAdapter() {
			});

		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		
		
		table.setBounds(10, 11, 577, 336);
		frame.getContentPane().add(table);
		
		JTextField jtf = new JTextField();
	    jtf.setDocument(new LimitedPlainDocument(1));
	    for (int i = 0; i < table.getColumnCount(); i++)
	    	table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(jtf));

	    
		btnClearTable = new JButton("Reset Game");
		btnClearTable.setEnabled(false);
		btnClearTable.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				winner(s_p1, s_p2, s_p3, s_p4);
				tableInit();				
			}
		});
		btnClearTable.setBounds(630, 480, 134, 24);
		frame.getContentPane().add(btnClearTable);
		
		lblPlayer = new JLabel("Player 1");
		lblPlayer.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer.setBounds(102, 358, 86, 24);
		lblPlayer.setOpaque(true);
		frame.getContentPane().add(lblPlayer);
		
		lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer_1.setBounds(214, 358, 86, 24);
		lblPlayer_1.setOpaque(true);
		frame.getContentPane().add(lblPlayer_1);
		
		lblPlayer_2 = new JLabel("Player 3");
		lblPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer_2.setBounds(328, 358, 86, 24);
		lblPlayer_2.setOpaque(true);
		frame.getContentPane().add(lblPlayer_2);
		
		lblPlayer_3 = new JLabel("Player 4");
		lblPlayer_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayer_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPlayer_3.setBounds(439, 358, 86, 24);
		lblPlayer_3.setOpaque(true);
		frame.getContentPane().add(lblPlayer_3);
		
		label_array=new ArrayList<JLabel>();
		label_array.add(lblPlayer);
		label_array.add(lblPlayer_1);
		label_array.add(lblPlayer_2);
		label_array.add(lblPlayer_3);
		
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
		
		JLabel lblWaitlist = new JLabel("Waitlist");										// Players waiting in Game Lobby
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
				String[] invite_list = list_gw.getSelectedValuesList().toArray(new String[] {});
				if(invite_list.length >= 1)
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
		
		JButton calculate_score = new JButton("Calculate Score");
		calculate_score.setEnabled(false);
		calculate_score.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateScore();
			}
		});
		calculate_score.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		calculate_score.setBounds(630, 426, 134, 23);
		frame.getContentPane().add(calculate_score);
		
		btnpass = new JButton("Pass");
		btnpass.setEnabled(false);
		btnpass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Packet<Reply> outPacket=new Packet<Reply>("Pass",new Reply(null,true,null),usrnm);
				try {
					out.write(gson.toJson(outPacket)+ "\n");
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnpass.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnpass.setBounds(630, 392, 134, 23);
		frame.getContentPane().add(btnpass);
		
		btnFreeze = new JButton("Submit");
		btnFreeze.setEnabled(false);
		btnFreeze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculate_score.setEnabled(true);
				btnpass.setEnabled(true);
				fillTable();
				if (!addedmouseevent)
					mouseEvent();
				addedmouseevent = true;
					// For setting cells non editable
				setTableEditable(false);
	            
                for(int row = 0; row < table.getRowCount();row++)
                {
                    for(int column = 0; column < table.getColumnCount();column++)
                    {
                        //char c = (char) table.getValueAt(row, column);
                        if((table.getValueAt(row, column)!= null))
                        {
                            if(row < 20)
                            {
                                ((MyDefaultTableModel) myModel).setCellEditable(row+1,column,true);
                            }
                            if(column < 20)
                            {
                                ((MyDefaultTableModel) myModel).setCellEditable(row,column+1,true);
                            }
                            if(row > 0)
                            {
                            ((MyDefaultTableModel) myModel).setCellEditable(row-1,column,true);
                            }
                            if(column > 0)
                            {
                            ((MyDefaultTableModel) myModel).setCellEditable(row,column-1,true);
                            }
                        }
                    }
                }
				sendWord();
				
				//table.setEnabled(false);			
			}
		});
		btnFreeze.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnFreeze.setBounds(630, 359, 134, 23);
		frame.getContentPane().add(btnFreeze);
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);
				startGame();
				Packet<Reply> outPacket=new Packet<Reply>("StartGame",new Reply(null,true,null),usrnm);
				try {
					out.write(gson.toJson(outPacket)+ "\n");
					out.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
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
	
	public void fillTable() {																// Fill values in Hash Map 
		String str_v;
		String str_k;
		for(int i=0; i<20; i++)
			for(int j=0; j<20; j++) {
				str_v = (String) table.getValueAt(i, j);
				if(str_v != null && !(str_v.isEmpty())) {
					str_k = Integer.toString(i) + "_" + Integer.toString(j);
					if(hm.get(str_k) == null)
						hm.put(str_k, str_v);
				}
			}
	}
	
	public void mouseEvent() {				// New Code for making cell non editable
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String val="";
					table = (JTable)e.getSource();
					row_t = table.getSelectedRow();
					column_t = table.getSelectedColumn();
					
					String hm_cv = Integer.toString(row_t) + "_" + Integer.toString(column_t);
					try {
					val = (String) table.getValueAt(row_t, column_t);
					}
					catch(Exception e1) {
						JOptionPane.showMessageDialog(frame,"You are not allowed to Edit!!!", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					System.out.println("Value of selected Row: " + row_t + " , Column: " + column_t + " Value: " + val);
					
					if (val != null && !(val.isEmpty())) {
						if(hm.get(hm_cv) != null)
							JOptionPane.showMessageDialog(frame,"Cannot Edit Filled Cell!!!", "Warning", JOptionPane.WARNING_MESSAGE);
					} 
				}
			});
	}
	
	public void winner(int s_p1, int s_p2, int s_p3, int s_p4) {							// Deciding Winner
		if(s_p1 > s_p2 && s_p1 > s_p3 && s_p1 > s_p4)
			JOptionPane.showMessageDialog(frame,"Player 1 Wins!!!");
		else if (s_p2 > s_p1 && s_p2 > s_p3 && s_p2 > s_p4)
			JOptionPane.showMessageDialog(frame,"Player 2 Wins!!!");
		else if (s_p3 > s_p1 && s_p3 > s_p2 && s_p3 > s_p4)
			JOptionPane.showMessageDialog(frame,"Player 3 Wins!!!");
		else if (s_p4 > s_p1 && s_p4 > s_p2 && s_p4 > s_p3)
			JOptionPane.showMessageDialog(frame,"Player 4 Wins!!!");
		else if (s_p1 == s_p2 && s_p2 != 0)
			JOptionPane.showMessageDialog(frame,"Tie between Player 1 & 2!!!");
		else if (s_p1 == s_p3 && s_p3 != 0)
			JOptionPane.showMessageDialog(frame,"Tie between Player 1 & 3!!!");
		else if (s_p1 == s_p4 && s_p4 != 0)
			JOptionPane.showMessageDialog(frame,"Tie between Player 1 & 4!!!");
		else if (s_p2 == s_p3 && s_p3 != 0)
			JOptionPane.showMessageDialog(frame,"Tie between Player 2 & 3!!!");
		else if (s_p2 == s_p4 && s_p4 != 0)
			JOptionPane.showMessageDialog(frame,"Tie between Player 2 & 4!!!");
		else if (s_p3 == s_p4 && s_p4 != 0)
			JOptionPane.showMessageDialog(frame,"Tie between Player 3 & 4!!!");							 
		else
			JOptionPane.showMessageDialog(frame,"No Winner!!!");
	}
	
	public void tableInit() {
		myModel = new MyDefaultTableModel(21, 21);				//Calling Class for setting CellEditable Feature
		table.setModel(myModel);
		setTableEditable(true);
		table.setEnabled(true);

		
		s_p1 = 0;
		s_p2 = 0;
		s_p3 = 0;
		s_p4 = 0;
		
		score_p1.setText("");
		score_p2.setText("");
		score_p3.setText("");
		score_p4.setText("");
		
		jtf = new JTextField();
	    jtf.setDocument(new LimitedPlainDocument(1));
	    for (int i = 0; i < table.getColumnCount(); i++)
	    	table.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(jtf));

	}
	public void startGame() {
		btnStartGame.setEnabled(false);
		
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
		for (int i=0;i<4;i++)
			textfield_array.get(i).setText("");
		btnStartGame.setEnabled(true);
		btnInvite.setEnabled(true);
		for (int j=0;j<4;j++)
				label_array.get(j).setBackground(new JLabel().getBackground());
		try {
			
        Packet<GameList> outPacket = new Packet<GameList>("EndGame", null, usrnm);
        out.write(gson.toJson(outPacket) + "\n");
        out.flush();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateTable(Packet<Insert> packet) {
		HashMap<Integer, String> word=packet.getContent().getCharacter();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		if (packet.getContent().getColumn() == -1) {
			for (Integer key: word.keySet()) {
				model.setValueAt(word.get(key), packet.getContent().getRow(),key);
			}
		}
		else if(packet.getContent().getRow()==-1) {
			for (Integer key: word.keySet()) {
				model.setValueAt(word.get(key), key, packet.getContent().getColumn());
			}
		}
		else {
			for (Integer key: word.keySet()) {
				model.setValueAt(word.get(key), packet.getContent().getRow(), packet.getContent().getColumn());
			}
		}
				model.fireTableDataChanged();
	}
	
	public void callVote(StringBuffer word) {						// Sending word for voting
		System.out.print("Inside CallVote: "+word);
		Packet<Reply> outPacket=new Packet<Reply>("CallVote",new Reply(usrnm, false, word.toString()),usrnm);
		
		try {
			out.write(gson.toJson(outPacket) + "\n");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
	}
	
	public void sendWord() {									// Sending word for table updation
		int rowMax = table.getSelectionModel().getMaxSelectionIndex();
		int rowMin = table.getSelectionModel().getMinSelectionIndex();
		int columnMax = table.getColumnModel().getSelectionModel().getMaxSelectionIndex();
		int columnMin = table.getColumnModel().getSelectionModel().getMinSelectionIndex();
		StringBuffer wordString=new StringBuffer();
		int flag=0;
		int rowDiff = rowMax-rowMin;
        int columnDiff = columnMax-columnMin; 
        HashMap<Integer, String> word=new HashMap<>();
        if(rowDiff > 0) {
        	c = 0;
        	for(int i=rowMin; i<=rowMax; i++) { 
        		if(table.getValueAt(i, columnMax) == null) {
        			c = 1;
        			break;
        		}
        		else {
        			word.put(i, (String) table.getValueAt(i, columnMax));
        			wordString.append((String)table.getValueAt(i, columnMax));
        			
        		}
        	}
        	    if(c == 0) {
        	    	Packet<Insert> outPacket=new Packet<Insert>("Insert",new Insert(word, columnMax, -1), usrnm );
        	    	try {
						out.write(gson.toJson(outPacket)+"\n");
						out.flush();
						flag=1;
					} catch (IOException e) {
						e.printStackTrace();
					}
        	    	
        	    }

        	   else
        		   JOptionPane.showMessageDialog(frame,"Row Word selected with Blank Cell!!!", "Warning", JOptionPane.WARNING_MESSAGE);
         }
           
         else if(columnDiff > 0) {
        	 c = 0;
        	 for(int j=columnMin; j<=columnMax; j++) {
        		 if(table.getValueAt(rowMax, j) == null) {
        			c = 1;
        			break;
        		 }
        		 else {
         			word.put(j, (String) table.getValueAt(rowMax, j));
         			wordString.append((String)table.getValueAt(rowMax, j));
         		}
        	 }
	         if(c == 0) {										//Successful selection of correct word
	        	 Packet<Insert> outPacket=new Packet<Insert>("Insert",new Insert(word,-1,rowMax), usrnm );
     	    	try {
						out.write(gson.toJson(outPacket)+"\n");
						out.flush();
						flag=1;
					} catch (IOException e) {
						e.printStackTrace();
					}      		   
	         }
	         else
	        	 JOptionPane.showMessageDialog(frame,"Column Word selected with Blank Cell!!!", "Warning", JOptionPane.WARNING_MESSAGE);
         }
        else if(table.getValueAt(rowMin, columnMin) != null) {
         	//JOptionPane.showMessageDialog(new GameWindow().frame,"One letter word is not allowed!!!", "Warning", JOptionPane.WARNING_MESSAGE);
        	 word.put(rowMin, (String)table.getValueAt(rowMin, columnMin));
        	 wordString.append((String)table.getValueAt(rowMin, columnMax));
        	 Packet<Insert> outPacket=new Packet<Insert>("Insert",new Insert(word,columnMin, rowMin), usrnm );
  	    	try {
						out.write(gson.toJson(outPacket)+"\n");
						out.flush();
						flag=1;
					} catch (IOException e) {
						e.printStackTrace();
					}      		  
         }
         else
           JOptionPane.showMessageDialog(frame,"Blank Cell selected!!!", "Warning", JOptionPane.WARNING_MESSAGE);
        
        
        if(flag==1) {								// Calling for Vote
        	if (JOptionPane.showConfirmDialog(frame, "Do you want to send word for vote?", "Vote?", JOptionPane.YES_NO_OPTION,
        			JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
        		callVote(wordString);
        	}
        }
        
        
        
	}
	
	public void calculateScore() {															//Calculates score
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
         else
           JOptionPane.showMessageDialog(frame,"Blank Cell selected!!!", "Warning", JOptionPane.WARNING_MESSAGE);
	}
	
	public void closeGameGUI() {
		frame.dispose();
	}
	
	public void disableInviteButton() {
		btnInvite.setEnabled(false);
	}
	
	public void updateChance(int i) {
		for (int j=0;j<4;j++) {
			if(j==i) {
				label_array.get(j).setBackground(Color.green);				
				}
			else {
				label_array.get(j).setBackground(new JLabel().getBackground());
			}
			
		}
		System.out.println(textfield_array.get(i).getText().trim()+" "+usrnm);
		if(textfield_array.get(i).getText().trim().equals(usrnm)) {
			table.setEnabled(true);
			btnFreeze.setEnabled(true);
			btnpass.setEnabled(true);
			btnClearTable.setEnabled(true);
		}
		else {
			table.setEnabled(false);
			btnFreeze.setEnabled(false);
			btnpass.setEnabled(false);
			btnClearTable.setEnabled(false);
		}
	}
	


	


	public void setTableEditable(boolean editable) {   								//Multiple times can be called to enable/disable cell
        for(int row = 0; row < table.getRowCount(); row++){
            for(int column = 0; column < table.getColumnCount(); column++){
                //char c = (char) table.getValueAt(row, column);
                ((MyDefaultTableModel) myModel).setCellEditable(row, column, editable);
            }
        }
    }


}

@SuppressWarnings("serial")
class LimitedPlainDocument extends javax.swing.text.PlainDocument {					//Restriction Of Characters
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
	
class MyListener implements ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent e) {
			System.out.println("Row First Index: " + e.getFirstIndex() + " " + "Row Last Index: " + e.getLastIndex());
			JOptionPane.showMessageDialog(new GameWindow(null, null).frame,"Only 1 Entry is allowed!!", "Warning", JOptionPane.WARNING_MESSAGE);
	}
}
