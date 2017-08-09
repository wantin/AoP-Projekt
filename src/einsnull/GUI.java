package einsnull;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
	
	
	protected void aktion(){
		  JButton bildbttn = new JButton();
		  try {
		    Image img = ImageIO.read(getClass().getResource("bilder/schneckentyp.jpg"));
		    bildbttn.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
	}
		
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
		links.setBackground(new Color(20,10,10));
		links.setBounds(0,0,300,1000);
		
		sp1.setLocation(120,10);
		sp1.setSize(60,40);
		sp1.setForeground(Color.white);
		
		sp1z.setLocation(123,150);
		sp1z.setSize(80,40);
		sp1z.setForeground(Color.white);
		

		sp1k.setLocation(117,300);
		sp1k.setSize(90,40);
		sp1k.setForeground(Color.white);
		
		//Kartenfeld linkes Feld
		int k,l;
		k = 2;
		l = 4;
		JPanel lpanel = new JPanel();
		lpanel.setLayout(null);
		lpanel.setLocation(0,400);
		lpanel.setSize(300,500);
		lpanel.setLayout(new GridLayout(4,2,4,5));//Einteilung Panel und Zwischenabstände 
		lpanel.setBackground(links.getBackground());
		
		for(int a = 0; a < k; a++){
			for(int b = 0; b < l; b++){		
				JButton lbttn = new JButton();
				lbttn.setBackground(links.getBackground());
				lbttn.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0){
						aktion();
					}
				});
				lpanel.add(lbttn);
			}
		}
		

		//Einbindung Bild zu Testzwecken
		/*JButton bildbttn = new JButton(new ImageIcon(((new ImageIcon("bilder/söldner.jpg")).getImage()).getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH)));	 
	   	bildbttn.setLayout(null);
		lpanel.add(bildbttn);*/
		
		
		links.add(sp1);
		links.add(sp1z);
		links.add(sp1k);
		links.add(lpanel);		
			
		//Spielfeld
		mitte.setLayout(null);
		mitte.setLayout(new GridLayout(6,6));
		//mitte.setForeground(Color.orange);
		mitte.setBackground(new Color(100,200,0));
		mitte.setBounds(300,0,900,1000);
		
		//Schleife Buttons für mittleres Feld
		int m,n;
		m = 6;
		n = 6;
		JButton[][] button = new JButton[m][n];
		for(int c = 0; c < m; c++){
			for(int d = 0; d < n; d++){
				button[c][d] = new JButton();
				button[c][d].setLayout(null);
				button[c][d].setForeground(mitte.getForeground());
				button[c][d].setSize(mitte.getPreferredSize());
			    button[c][d].setBackground(mitte.getBackground());
				button[c][d].addActionListener(new ActionListener(){
					
					//muss noch abgeändert werden, da bisher der attbttn(für angreifbare einheiten) genutzt wird(auch wenn freies feld)
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JButton freebttn = new JButton();
						freebttn.setLayout(null);
						freebttn.setSize(getPreferredSize());
						freebttn.setForeground(mitte.getForeground());
						if(freebttn == null){
							System.out.println("Das Feld ist leer");
						}
					
						JButton attbttn = new JButton();
						if(attbttn != null){
							attbttn.setBackground(Color.red);
							System.out.println("Das Feld ist besetzt von");//karte des gegners einfügen
						}	
							
					}
						
				});
				mitte.add(button[c][d]);
			}
		}	
		
		//Spieler 2 im rechten Feld
		rechts.setLayout(null);
		rechts.setOpaque(true);
		rechts.setBackground(new Color(245,240,200));
		rechts.setBounds(1200,0,300,1000);
		
		sp2.setLocation(120,10);
		sp2.setSize(60,40);
		sp2.setForeground(Color.black);
		
		sp2z.setLocation(123,150);
		sp2z.setSize(80,40);
		sp2z.setForeground(Color.black);		

		sp2k.setLocation(117,300);
		sp2k.setSize(90,40);
		sp2k.setForeground(Color.black);
		
		//Kartenfeld rechtes Feld
		JPanel rpanel = new JPanel();
		rpanel.setLayout(null);
		rpanel.setLocation(0,400);
		rpanel.setSize(300,500);
		rpanel.setLayout(new GridLayout(4,2,4,5));//Einteilung Panel und Zwischenabstände 
		rpanel.setBackground(rechts.getBackground());
		
		for(int e = 0; e < k; e++){
			for(int f = 0; f < l; f++){		
				JButton rbttn = new JButton();
				rbttn.setBackground(rechts.getBackground());
				rpanel.add(rbttn);
			}
		}
		
		rechts.add(sp2);
		rechts.add(sp2z);
		rechts.add(sp2k);
		rechts.add(rpanel);
			
		cp.add(mitte);
		cp.add(links);
		cp.add(rechts);
	}
	
	

}


