package advanceProject;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Menu extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static String username1, username2;
	JLabel lbl;
	JTextField text1, text2;
	JButton nemesis, single;
	JButton highScores;
	GridLayout gl;
	UserContent uc;
	BallPanel bp = new BallPanel();
	BallPanelSingle bps = new BallPanelSingle();
	
	
	public Menu() {
		super("3 ball billard menu");
		super.setLocationRelativeTo(null);
	}
	
	public void showMenu() {
		gl = new GridLayout(6,1);
		setLayout(gl);
		setSize(200, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		uc = new UserContent();
		
		single = new JButton("Single Player");
		nemesis = new JButton("1 vs 1");
		highScores = new JButton("High Scores");
		lbl = new JLabel("Usernames:");
		text1 = new JTextField();
		text2 = new JTextField();
		nemesis.addActionListener(this);
		single.addActionListener(this);
		
		
		add(lbl);
		add(text1);
		add(text2);
		add(nemesis);
		add(single);
		add(highScores);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == nemesis) {
			
			setVisible(false);
			username1 = text1.getText().toString();
			username2 = text2.getText().toString();
			
			JFrame f = new JFrame("BallAnimation");
			f.add(bp);
			f.setSize(1016,508);
			f.setVisible(true);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			
		if(e.getSource() == single) {
			
			setVisible(false);
			username1 = text1.getText().toString();
//			username2 = text2.getText().toString();
			
			JFrame f2 = new JFrame("BallAnimation");
			f2.add(bps);
			f2.setSize(1016,508);
			f2.setVisible(true);
			f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		
		
	}

	
	

}
