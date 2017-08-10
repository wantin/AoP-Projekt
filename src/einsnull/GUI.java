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
	
	private JButton ppbttn = new JButton("Player vs. Player");
	private JButton pkbttn = new JButton("Player vs. KI");
	private JButton start = new JButton("START");
	
	private JLabel plyr1 = new JLabel("Spieler 1");	
	private JLabel plyr2 = new JLabel ("Spieler 2");
	private JLabel plyr1z = new JLabel ("Spielzug");
	private JLabel plyr2z = new JLabel ("Spielzug");
	private JLabel plyr1k = new JLabel ("Kartenstapel");
	private JLabel plyr2k = new JLabel ("Kartenstapel");
	private JLabel text1 = new JLabel();
	private JLabel text2 = new JLabel();
	private JLabel text3 = new JLabel();
	private JLabel text4 = new JLabel();
	private JLabel plyr1n = new JLabel();
	//protected JLabel sp2n = new JLabel();
		
	private JMenuItem beendenitem = new JMenuItem("Exit");
	
	private JTextArea linkerplyr = new JTextArea();
	private JTextArea rechterplyr = new JTextArea();
	
	private Spieler plyr;

	Random zufall = new Random();
	
	public void setSpieler(Spieler spieler){
		plyr = spieler;
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
		text1.setFont(new Font(text1.getText(), Font.ITALIC, 20));
		text1.setBounds(250,10,500,60);
		text1.setForeground(auswahl.getForeground());
		
		text2.setText("Wollt Ihr gegen die KI spielen , oder zu zweit an einem Computer?");
		text2.setFont(new Font(text2.getText(), Font.PLAIN, 16));
		text2.setForeground(auswahl.getForeground());
		text2.setBounds(230,100,600,30);		
		
		ppbttn.setVisible(true);
		ppbttn.setLayout(null);
		ppbttn.setForeground(auswahl.getForeground());
		ppbttn.setBounds(230,200,150,50);
		ppbttn.setBackground(auswahl.getBackground());
		ppbttn.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {						

				if(ppbttn != null){
					text2.setVisible(false);
					text3.setVisible(true);
					pkbttn.setVisible(false);
					ppbttn.setVisible(false);
					linkerplyr.setVisible(true);
					rechterplyr.setVisible(true);
					start.setVisible(true);
				}	
			}			
		});		
		
		//Spieler auf linker Seite deshalb bttn true
		pkbttn.setVisible(true);
		pkbttn.setLayout(null);
		pkbttn.setForeground(auswahl.getForeground());
		pkbttn.setBounds(500,200,150,50);
		pkbttn.setBackground(auswahl.getBackground());
		pkbttn.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
							
				if(pkbttn != null){
					text2.setVisible(false);
					text4.setVisible(true);
					pkbttn.setVisible(false);
					ppbttn.setVisible(false);
					linkerplyr.setVisible(true);
					rechterplyr.setVisible(false);
					start.setVisible(true);
				}	
			}			
		});
			
		text3.setVisible(false);
		text3.setText("Gebt Eure Titel ein(Max. 20 Zeichen):");
		text3.setFont(text2.getFont());
		text3.setBounds(50,100,270,20);
		text3.setForeground(auswahl.getForeground());
		
		linkerplyr.setVisible(false);
		linkerplyr.setFont(new Font("", Font.ITALIC, 18));
		linkerplyr.setBounds(350,100,200,30);
		linkerplyr.setForeground(Color.white);
		linkerplyr.setBackground(new Color(40,10,10));
		
		rechterplyr.setVisible(false);	
		rechterplyr.setFont(linkerplyr.getFont());
		rechterplyr.setBounds(600,100,200,30);
		rechterplyr.setForeground(Color.black);
		rechterplyr.setBackground(new Color(245,240,200));
		
		text4.setVisible(false);
		text4.setText("Gebt Euren Titel ein(Max. 20 Zeichen):");
		text4.setFont(text2.getFont());
		text4.setBounds(50,100,250,40);
		text4.setForeground(auswahl.getForeground());
		
		start.setVisible(false);
		start.setBounds(500,250,140,60);
		start.setForeground(auswahl.getForeground());
		start.setBackground(auswahl.getBackground());
		
		//regelt die Länge der Namen(beschränkt auf 20 Zeichen
		//panel bleibt solange sichtbar bis richtige länge, dann unsichtbar
		start.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String lname = linkerplyr.getText();
				String rname = rechterplyr.getText();

				if(lname.length() == 0 && rname.length() == 0 ){
					start.isEnabled();
					auswahl.setVisible(true);			
				}
					
				else if(lname.length() != 0 & rname.length() != 0){
					if(lname.length() < 21 & rname.length() < 21){
						start.isEnabled();
						auswahl.setVisible(false);
					}
					else{
						start.isEnabled();
						auswahl.setVisible(true);
					}
						
				}					
		}		
	});	
		
		auswahl.add(text1);
		auswahl.add(text2);
		auswahl.add(text3);
		auswahl.add(text4);	
		
		auswahl.add(linkerplyr);
		auswahl.add(rechterplyr);
		
		auswahl.add(ppbttn);
		auswahl.add(pkbttn);
		auswahl.add(start);
	
		
		//spieler 1 im linken Feld
		lfeld.setLayout(null);
		lfeld.setOpaque(true);
		lfeld.setForeground(Color.white);
		lfeld.setBackground(new Color(40,10,10));
		lfeld.setBounds(0,0,300,1000);
		
		plyr1.setLocation(120,10);
		plyr1.setSize(60,40);
		plyr1.setForeground(lfeld.getForeground());
		
		plyr1z.setLocation(123,150);
		plyr1z.setSize(80,40);
		plyr1z.setForeground(lfeld.getForeground());

		plyr1k.setLocation(117,300);
		plyr1k.setSize(90,40);
		plyr1k.setForeground(lfeld.getForeground());
		
		//plyr1n.setText(plyr.getName());	
		plyr1n.setHorizontalAlignment(JLabel.CENTER);
		plyr1n.setLocation(70,70);
		plyr1n.setSize(140,40);
		plyr1n.setForeground(lfeld.getForeground());
		plyr1n.setBackground(lfeld.getBackground());		
		
		
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
		
		lfeld.add(plyr1);
		lfeld.add(plyr1z);
		lfeld.add(plyr1k);
		lfeld.add(plyr1n);
		lfeld.add(lpanel);		
			
		//spielfeld
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
		
		//spieler 2 im rechten Feld
		rfeld.setLayout(null);
		rfeld.setOpaque(true);
		rfeld.setForeground(Color.black);
		rfeld.setBackground(new Color(245,240,200));
		rfeld.setBounds(1200,0,300,1000);
		
		plyr2.setLocation(120,10);
		plyr2.setSize(60,40);
		plyr2.setForeground(rfeld.getForeground());
		
		plyr2z.setLocation(123,150);
		plyr2z.setSize(80,40);
		plyr2z.setForeground(rfeld.getForeground());		

		plyr2k.setLocation(117,300);
		plyr2k.setSize(90,40);
		plyr2k.setForeground(rfeld.getForeground());
		
		/*plyr2n.setLocation( 120,15);
		plyr2n.setSize(120,20);
		plyr2n.setForeground(rfeld.getForeground());*/
		
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
		
		rfeld.add(plyr2);
		rfeld.add(plyr2z);
		rfeld.add(plyr2k);
		//rfeld.add(plyr2n);
		rfeld.add(rpanel);
		

		content.add(auswahl);
		content.add(mitte);
		content.add(lfeld);
		content.add(rfeld);
	}
	
	private void beenden() {
		System.out.println("Möge der Krieg..äh das Spiel beginnen!");
		System.exit(0);
	}
	

}


