package einsnull;

import einheitenkarten.*;
import effektkarten.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GUI extends JFrame{
		
	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JPanel auswahl = new JPanel();
	private JPanel mitte = new JPanel();
	private JPanel lfeld = new JPanel();
	private JPanel rfeld = new JPanel();
	private JLabel sp1 = new JLabel("Spieler 1");	
	private JLabel sp2 = new JLabel ("Spieler 2");
	private JLabel sp1z = new JLabel ("Spielzug");
	private JLabel sp2z = new JLabel ("Spielzug");
	private JLabel sp1k = new JLabel ("Kartenstapel");
	private JLabel sp2k = new JLabel ("Kartenstapel");
	protected JLabel text1 = new JLabel();
	protected JLabel text2 = new JLabel();
	protected JLabel text3 = new JLabel();
	protected JLabel text4 = new JLabel();
	private JMenuItem beendenitem = new JMenuItem("Exit");
	private JTextArea linkersp = new JTextArea();
	private JTextArea rechtersp = new JTextArea();
	private Spieler sp;
	protected JLabel sp1n = new JLabel();
	//protected JLabel sp2n = new JLabel();
	Random zufall = new Random();
	

	
	public void setSpieler(Spieler spieler){
		sp = spieler;
	}
	
	
	/*protected void aktion(){
		  JButton bildbttn = new JButton();
		  try {
		    Image img = ImageIO.read(getClass().getResource("bilder/schneckentyp.jpg"));
		    bildbttn.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
	}*/
		
	public GUI(){
		
		this.setTitle("Spielbrett");
		this.setSize(1500, 1000);
		this.setResizable(false);
		this.setLocation(200, 40);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Hintergrund;
		content = (JPanel) this.getContentPane();
		content.setLayout(null);
		
		beendenitem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				beenden();
			}
		});
		
		//Abfrage, Kartenauswahl etc
		auswahl.setLayout(null);
		auswahl.setOpaque(true);
		auswahl.setForeground(Color.white);
		auswahl.setBackground(new Color(150,130,50));
		auswahl.setBounds(300,168,900,497);
		
		//da panel verschwand wenn man auf die dahinterliegenden buttons geklickt hat musste ich das dazu machen
		//und bei des setBounds bisschen tricksen^^
		auswahl.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {
				setVisible(true);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {} 
			
		});
		
		

		//auswahl.setToolTipText(text); //wenn Cursor darüber liegt erscheinender Text
		
		text1.setText("Willkommen zum spannenden Duell der Krieger!");
		text1.setFont(new Font("Willkommen zum spannenden Duell der Krieger!", Font.PLAIN, 18));
		text1.setLocation(300,10);
		text1.setSize(400,60);
		text1.setForeground(auswahl.getForeground());
		
		
		text2.setText("Gebt Euren Titel ein:");
		text2.setFont(new Font("Bitte gebt Euren Titel ein:", Font.PLAIN, 14));
		text2.setLocation(90,100);
		text2.setSize(180,20);
		text2.setForeground(auswahl.getForeground());
		
		linkersp.setLocation(300,100);
		linkersp.setFont(new Font("", Font.PLAIN, 14));
		linkersp.setSize(200,20);
		linkersp.setForeground(auswahl.getForeground());
		linkersp.setBackground(new Color(170,80,50));
		
		rechtersp.setLocation(600,100);
		rechtersp.setFont(new Font("", Font.PLAIN, 14));
		rechtersp.setSize(200,20);
		rechtersp.setForeground(auswahl.getForeground());
		rechtersp.setBackground(new Color(170,80,60));		
		
		text3.setText("bla");
		text3.setFont(new Font("", Font.PLAIN, 14));
		text3.setLocation(300,90);
		text3.setSize(60,40);
		text3.setForeground(auswahl.getForeground());
		
		text4.setText("blabla");
		text4.setFont(new Font("", Font.PLAIN, 14));
		text4.setLocation(300,150);
		text4.setSize(60,40);
		text4.setForeground(auswahl.getForeground());
		
		
		auswahl.add(text1);
		auswahl.add(linkersp);
		auswahl.add(rechtersp);
		auswahl.add(text2);
		auswahl.add(text3);
		auswahl.add(text4);	
	
		
		//Spieler 1 im linken Feld
		lfeld.setLayout(null);
		lfeld.setOpaque(true);
		lfeld.setForeground(Color.white);
		lfeld.setBackground(new Color(40,10,10));
		lfeld.setBounds(0,0,300,1000);
		
		sp1.setLocation(120,10);
		sp1.setSize(60,40);
		sp1.setForeground(lfeld.getForeground());
		
		sp1z.setLocation(123,150);
		sp1z.setSize(80,40);
		sp1z.setForeground(lfeld.getForeground());

		sp1k.setLocation(117,300);
		sp1k.setSize(90,40);
		sp1k.setForeground(lfeld.getForeground());
		
		//sp1n.setText(sp.getName());	
		sp1n.setHorizontalAlignment(JLabel.CENTER);
		sp1n.setLocation(70,70);
		sp1n.setSize(140,40);
		sp1n.setForeground(lfeld.getForeground());
		sp1n.setBackground(lfeld.getBackground());
		
		
		//Kartenfeld linkes Feld
		int k,l;
		k = 2;
		l = 4;
		JPanel lpanel = new JPanel();
		lpanel.setLayout(null);
		lpanel.setLocation(0,400);
		lpanel.setSize(300,500);
		lpanel.setLayout(new GridLayout(4,2,4,5));//Einteilung Panel und Zwischenabstände 
		lpanel.setBackground(lfeld.getBackground());
		
		for(int a = 0; a < k; a++){
			for(int b = 0; b < l; b++){		
				JButton lbttn = new JButton();
				lbttn.setBackground(lfeld.getBackground());
	
				lbttn.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0){
						//aktion();
					}
				});
				lpanel.add(lbttn);
			}
		}		

		//Einbindung Bild zu Testzwecken
		/*JButton bildbttn = new JButton(new ImageIcon(((new ImageIcon("bilder/söldner.jpg")).getImage()).getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH)));	 
	   	bildbttn.setLayout(null);
		lpanel.add(bildbttn);
		*/
		
		lfeld.add(sp1);
		lfeld.add(sp1z);
		lfeld.add(sp1k);
		lfeld.add(sp1n);
		lfeld.add(lpanel);		
			
		//Spielfeld
		mitte.setLayout(null);
		mitte.setLayout(new GridLayout(6,6));
		//mitte.setForeground(Color.orange);
		mitte.setBackground(new Color(150,50,15));
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
				button[c][d].setRolloverEnabled(false);//falls Maus geklickt scheint button durch!
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
		rfeld.setLayout(null);
		rfeld.setOpaque(true);
		rfeld.setForeground(Color.black);
		rfeld.setBackground(new Color(245,240,200));
		rfeld.setBounds(1200,0,300,1000);
		
		sp2.setLocation(120,10);
		sp2.setSize(60,40);
		sp2.setForeground(rfeld.getForeground());
		
		sp2z.setLocation(123,150);
		sp2z.setSize(80,40);
		sp2z.setForeground(rfeld.getForeground());		

		sp2k.setLocation(117,300);
		sp2k.setSize(90,40);
		sp2k.setForeground(rfeld.getForeground());
		
		/*sp2n.setLocation( 120,15);
		sp2n.setSize(120,20);
		sp2n.setForeground(rfeld.getForeground());*/
		
		//Kartenfeld rechtes Feld
		JPanel rpanel = new JPanel();
		rpanel.setLayout(null);
		rpanel.setLocation(0,400);
		rpanel.setSize(300,500);
		rpanel.setLayout(new GridLayout(4,2,4,5));//Einteilung Panel und Zwischenabstände 
		rpanel.setBackground(rfeld.getBackground());
		
		for(int e = 0; e < k; e++){
			for(int f = 0; f < l; f++){		
				JButton rbttn = new JButton();
				rbttn.setBackground(rfeld.getBackground());
				rpanel.add(rbttn);
			}
		}
		
		rfeld.add(sp2);
		rfeld.add(sp2z);
		rfeld.add(sp2k);
		//rfeld.add(sp2n);
		rfeld.add(rpanel);
		

		content.add(auswahl);
		content.add(mitte);
		content.add(lfeld);
		content.add(rfeld);
	}
	
	private void beenden() {
		System.out.println("Möge das Spiel beginnen!");
		System.exit(0);
	}
	

}


