package einsnull;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GUI extends JFrame{
		
	private static final long serialVersionUID = 1L;
	private JPanel cp;
	private JPanel mitte = new JPanel();
	private JPanel links = new JPanel();
	private JPanel rechts = new JPanel();
	private JLabel sp1 = new JLabel("Spieler 1");	
	private JLabel sp2 = new JLabel ("Spieler 2");
	private JLabel sp1z = new JLabel ("Spielzug");
	private JLabel sp2z = new JLabel ("Spielzug");
	private JLabel sp1k = new JLabel ("Kartenstapel");
	private JLabel sp2k = new JLabel ("Kartenstapel");
	Random zufall = new Random();
		
	public GUI(){
		
		this.setTitle("Spielbrett");
		this.setSize(1500, 1000);
		this.setResizable(false);
		this.setLocation(200, 40);
	
		//Hintergrund;
		cp = (JPanel) this.getContentPane();
		cp.setLayout(null);
		
		//Spieler 1 im linken Feld
		links.setLayout(null);
		links.setBackground(new Color(0,5,5));
		links.setBounds(0,0,300,1000);
		
		sp1.setLocation(120,10);
		sp1.setSize(60,40);
		sp1.setForeground(Color.white);
		
		sp1z.setLocation(110,150);
		sp1z.setSize(80,40);
		sp1z.setForeground(Color.white);
		

		sp1k.setLocation(110,400);
		sp1k.setSize(90,40);
		sp1k.setForeground(Color.white);
		
		links.add(sp1);
		links.add(sp1z);
		links.add(sp1k);
		
			
		//Spielfeld
		mitte.setLayout(null);
		mitte.setLayout(new GridLayout(6,6));
		mitte.setForeground(Color.orange);
		mitte.setBackground(new Color(100,200,0));
		mitte.setBounds(300,0,900,1000);
		
		//Schleife Buttons für mittleres Feld
		int m,n;
		m = 6;
		n = 6;
		JButton[][] button = new JButton[m][n];
		for(int i = 0; i < button.length; i++){
			for(int j = 0; j < button.length; j++){
				button[i][j] = new JButton();
				button[i][j].setLayout(null);
				button[i][j].setForeground(mitte.getForeground());
				button[i][j].setSize(mitte.getPreferredSize());
			    button[i][j].setBackground(mitte.getBackground());
				button[i][j].addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JButton freebttn = new JButton();
						freebttn.setLayout(null);
						freebttn.setSize(getPreferredSize());
						freebttn.setForeground(mitte.getForeground());
						System.out.println("Das Feld ist leer");
					
						JButton attbttn = new JButton();
						if(attbttn != null){
							attbttn.setBackground(Color.red);
							System.out.println("Das Feld ist besetzt von");//karte des gegners einfügen
						}	
							
					}
						
				});
				mitte.add(button[i][j]);
			}
		}		
		
		//Spieler 2 im rechten Feld
		rechts.setLayout(null);
		rechts.setOpaque(true);
		rechts.setBackground(new Color(255,255,250));
		rechts.setBounds(1200,0,300,1000);
		
		sp2.setLocation(120,10);
		sp2.setSize(60,40);
		sp2.setForeground(Color.black);
		
		sp2z.setLocation(110,150);
		sp2z.setSize(80,40);
		sp2z.setForeground(Color.black);
		

		sp2k.setLocation(110,400);
		sp2k.setSize(90,40);
		sp2k.setForeground(Color.black);
		
		rechts.add(sp2);
		rechts.add(sp2z);
		rechts.add(sp2k);
			
		cp.add(mitte);
		cp.add(links);
		cp.add(rechts);
		
		
	}
	

}


