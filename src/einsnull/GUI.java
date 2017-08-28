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
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI extends JFrame{

	static Scanner input = new Scanner(System.in);

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JPanel auswahl = new JPanel();
	private JPanel kaufPane = new JPanel();
	private JPanel mitte = new JPanel();
	private JPanel lfeld = new JPanel();
	private JPanel rfeld = new JPanel();

	private JButton[] kaufButtons= new JButton[3];
	private JLabel[][][] feldButtonLabels = new JLabel[6][6][2]; //Rüstung und Stärke
	private JButton[][] feldButtons;
	private JButton ppbttn = new JButton("Player vs. Player");
	private JButton pkbttn = new JButton("Player vs. KI");
	private JButton start = new JButton("START");

	private JLabel[] preisLabel = new JLabel[3];
	private JLabel[][] staerkeKarte = new JLabel[6][6];
	private JLabel goldAnzeige = new JLabel();
	private JLabel kaufLabel = new JLabel("Klicken Sie auf eine der drei Karten um sie zu kaufen.");
	private JLabel plyr1 = new JLabel("Spieler 1");
	private JLabel plyr2 = new JLabel ("Spieler 2");
	private JLabel plyr1k = new JLabel ("Handkarten");
	private JLabel plyr2k = new JLabel ("Handkarten");
	private JLabel text1 = new JLabel();
	private JLabel text2 = new JLabel();
	private JLabel text3 = new JLabel();
	private JLabel text4 = new JLabel();

	private JTextArea linkerplyr = new JTextArea();
	private JTextArea rechterplyr = new JTextArea();
	GUI anzeige = this;


	Random zufall = new Random();
	
	//da panel verschwand wenn man auf die dahinterliegenden buttons geklickt hat musste ich das dazu machen (Elina)
	//Ich habe das mal rausgeholt, damit man es mehrfach verwenden kann (Valentin)
	MouseListener bleibHier = new MouseListener(){

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

	};
	
	//Konstruktor
	public GUI(Feld spielbrett, Spieler links, Spieler rechts){

		this.setTitle("Vona");
		this.setBounds(400, 100,1200, 630);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Hintergrund;
		content = (JPanel) this.getContentPane();
		content.setLayout(null);

		//Spielart und Namen auswahl
		//auswahl.setVisible(false); falls man den Anfang überspringen will
		auswahl.setLayout(null);
		auswahl.setForeground(Color.white);
		auswahl.setBackground(new Color(150,130,50));
		auswahl.setBounds(300,100,600,400);
		auswahl.addMouseListener(bleibHier);

		
		//Karten kaufen
		kaufPane.setLayout(null);
		kaufPane.setForeground(Color.white);
		kaufPane.setBackground(new Color(150,100,50));
		kaufPane.setBounds(300,100,600,400);
		kaufPane.setVisible(false);
		
		for (int i = 0; i < 3; i++) {
			kaufButtons[i]= new JButton();
			kaufButtons[i].setBounds(40 + i*190, 150, 160, 160);
			kaufPane.add(kaufButtons[i]);
			preisLabel[i] = new JLabel();
			preisLabel[i].setLayout(null);
			preisLabel[i].setForeground(new Color(200, 180, 70));
			preisLabel[i].setBounds(100+i*220, 340, 160, 30);
		}
		
		goldAnzeige.setBounds(100, 30, 1000, 30);
		kaufLabel.setBounds(100, 80, 1000, 30);
		kaufPane.add(goldAnzeige);
		kaufPane.add(kaufLabel);
		kaufPane.addMouseListener(bleibHier);
		

		text1.setText("Willkommen zu Vona!");
		text1.setFont(new Font(text1.getText(), Font.ITALIC, 20));
		text1.setBounds(250,20,500,60);
		text1.setForeground(auswahl.getForeground());

		text2.setText("Wollt Ihr gegen die KI spielen , oder zu zweit an einem Computer?");
		text2.setFont(new Font(text2.getText(), Font.PLAIN, 16));
		text2.setForeground(auswahl.getForeground());
		text2.setBounds(100,110,500,30);

		ppbttn.setVisible(true);
		ppbttn.setLayout(null);
		ppbttn.setForeground(auswahl.getForeground());
		ppbttn.setBounds(100,200,200,50);
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
					setPlayer(rechts, true);
				}
			}
		});

		//Spieler auf linker Seite deshalb bttn true
		pkbttn.setVisible(true);
		pkbttn.setLayout(null);
		pkbttn.setForeground(auswahl.getForeground());
		pkbttn.setBounds(350,200,200,50);
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
					setPlayer(rechts, false);
				}
			}
		});

		//spieler 1 im linken Feld
		lfeld.setLayout(null);
		lfeld.setForeground(Color.white);
		lfeld.setBackground(new Color(40,10,10));
		lfeld.setBounds(0,0,300,600);


		plyr1.setBounds(120,10,200,40);
		plyr1.setForeground(lfeld.getForeground());

		plyr1k.setBounds(120,180,90,40);
		plyr1k.setForeground(lfeld.getForeground());

		//Kartenfeld linkes Feld
		int k,l;
		k = 2;
		l = 4;
		JPanel lpanel = new JPanel();
		lpanel.setLayout(null);
		lpanel.setBounds(0,220,300,350);
		lpanel.setLayout(new GridLayout(4,2,4,5));//Einteilung Panel und ZwischenabstÃ¤nde
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

		lfeld.add(plyr1);
		lfeld.add(plyr1k);
		lfeld.add(lpanel);

		//spielfeld
		mitte.setLayout(null);
		mitte.setLayout(new GridLayout(6,6));

		mitte.setForeground(Color.orange);
		mitte.setBackground(new Color(150,50,15));
		mitte.setBounds(300,0,600,600);
		


		//Schleife Buttons für mittleres Feld
		int m,n;
		m = 6;
		n = 6;
		feldButtons = new JButton[m][n];
		for(int c = 0; c < m; c++){
			for(int d = 0; d < n; d++){
				
				staerkeKarte[c][d] = new JLabel();
				staerkeKarte[c][d].setLayout(null);
				staerkeKarte[c][d].setForeground(Color.orange);
				staerkeKarte[c][d].setBounds(0,0,20,20);
				staerkeKarte[c][d].setText(Integer.toString(c+d));

				feldButtons[c][d] = new JButton();
				feldButtons[c][d].setLayout(null);
				feldButtons[c][d].add(staerkeKarte[c][d]);
				feldButtons[c][d].setIcon(new ImageIcon(((new ImageIcon("bilder/holzHintergrund.jpg")).getImage()).getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
				feldButtons[c][d].addActionListener(new ActionListener(){

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
							System.out.println("Das Feld ist besetzt von");//karte des gegners einfÃ¼gen
						}
					}
				});
			mitte.add(feldButtons[c][d]);
			}
		}

		//spieler 2 im rechten Feld
		rfeld.setLayout(null);
		rfeld.setOpaque(true);
		rfeld.setForeground(Color.black);
		rfeld.setBackground(new Color(245,240,200));
		rfeld.setBounds(900,0,300,600);

		plyr2.setBounds(120,10,200,40);
		plyr2.setForeground(rfeld.getForeground());

		plyr2k.setBounds(120,180,90,40);
		plyr2k.setForeground(rfeld.getForeground());

		//Kartenfeld rechtes Feld
		JPanel rpanel = new JPanel();
		rpanel.setLayout(null);
		rpanel.setBounds(0,220,300,350);
		rpanel.setLayout(new GridLayout(4,2,4,5));//Einteilung Panel und ZwischenabstÃ¤nde
		rpanel.setBackground(rfeld.getBackground());

		for(int e = 0; e < k; e++){
			for(int f = 0; f < l; f++){
				JButton rbttn = new JButton();
				rbttn.setBackground(rfeld.getBackground());
				rpanel.add(rbttn);
			}
		}

		rfeld.add(plyr2);
		rfeld.add(plyr2k);
		//rfeld.add(plyr2n);
		rfeld.add(rpanel);


		content.add(auswahl);
		content.add(kaufPane);
		content.add(mitte);
		content.add(lfeld);
		content.add(rfeld);
	
	}


	//Namen wÃ¤helen
	public void setup1(Spieler links, Spieler rechts){

		text3.setVisible(false);
		text3.setText("Gebt Eure Titel ein (Max. 20 Zeichen):");
		text3.setFont(text2.getFont());
		text3.setBounds(100,100,270,20);
		text3.setForeground(auswahl.getForeground());

		linkerplyr.setVisible(false);
		linkerplyr.setFont(new Font("", Font.ITALIC, 18));
		linkerplyr.setBounds(50,200,200,30);
		linkerplyr.setForeground(Color.white);
		linkerplyr.setBackground(new Color(40,10,10));

		rechterplyr.setVisible(false);
		rechterplyr.setFont(linkerplyr.getFont());
		rechterplyr.setBounds(320,200,200,30);
		rechterplyr.setForeground(Color.black);
		rechterplyr.setBackground(new Color(245,240,200));

		text4.setVisible(false);
		text4.setText("Gebt Euren Titel ein(Max. 20 Zeichen):");
		text4.setFont(text2.getFont());
		text4.setBounds(100,100,270,40);
		text4.setForeground(auswahl.getForeground());

		start.setVisible(false);
		start.setBounds(320,300,160,60);
		start.setForeground(auswahl.getForeground());
		start.setBackground(auswahl.getBackground());
		
		//regelt die LÃ¤nge der Namen(beschrÃ¤nkt auf 20 Zeichen
		//panel bleibt solange sichtbar bis richtige lÃ¤nge, dann unsichtbar
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String lname = linkerplyr.getText();
				String rname = rechterplyr.getText();

				if (ppbttn != null){
				if(lname.length() != 0 && rname.length() != 0){
					if(lname.length() < 21 && rname.length() < 21){//anfangen bei PvP

						start.isEnabled();
						auswahl.setVisible(false);
						links.setName(lname);
						rechts.setName(rname);
						plyr2.setText(rechts.getName());
						plyr1.setText(links.getName());
						kaufPane.setVisible(true);
						links.kaufen(anzeige, rechts);
					}
					else{//nicht anfangen
						start.isEnabled();
						auswahl.setVisible(true);
					}
				}
				if(pkbttn != null){ //anfangen bei PvE
					if(lname.length() != 0 && lname.length() < 21){

							start.isEnabled();
							auswahl.setVisible(false);
							links.setName(lname);
							rechts.setName(rname);
							plyr1.setText(links.getName());
							plyr2.setText(rechts.getName());
							kaufPane.setVisible(true);
							links.kaufen(anzeige, rechts);
					}
						else{//nicht anfangen
							start.isEnabled();
							auswahl.setVisible(true);
						}
					
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
	}

	
	//oben genutzte Methode, die auf Buttoneingabe reagiert.
	public void setPlayer(Spieler rechts, boolean human){
		if(human);
		else rechts= new KI();
	}

	public void versteckeKauf(){
		kaufPane.setVisible(false);
	}

	//was ist das?
	public void setup0(Spieler rechts){} //PvP (true) oder PvE (false)
		//auswahl.setToolTipText(text); //wenn Cursor darÃ¼ber liegt erscheinender Text

	public void kaufen(Spieler kaufender, Spieler anderer){
		
		Karte[] angebot = new Karte[3];
		int minPreis = 50;
		int maxHand = 8;
		
		for(int i = 0; i <= 2; i++) {
			angebot[i] = kaufender.generateEinheit();
			while (kaufender.getGold() < angebot[i].getPreis() && kaufender.getGold() >= minPreis) {
				angebot[i] = kaufender.generateEinheit(); System.out.println(i + "Nicht genug Gold. Generiere neue Einheit."); // Nur zum Testen.
			}
			kaufButtons[i].setIcon(new ImageIcon(((new ImageIcon(angebot[i].getBildPfad())).getImage()).getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
			preisLabel[i].setText(Integer.toString(angebot[i].getPreis()));
			
			final int innerI = i; //was macht das?
			
			ActionListener l = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					kaufender.getHand().add(angebot[innerI]);
					kaufender.setGold(kaufender.getGold()-angebot[innerI].getPreis());
					content.repaint();
					
					if (kaufender.getHand().size() < maxHand && kaufender.getGold() >= minPreis) kaufen(kaufender, anderer); //weitereinkaufen
					else{
						if(anderer.getHand().size() == 0){ //der andere kauft
							anderer.kaufen(anzeige, kaufender);
						}else{	//aufr�umen
							kaufPane.setVisible(false);
						}
					}

					//Kontrollausgabe
					System.out.println(kaufender.getName() + " hat "+ angebot[innerI].getName() + " gekauft. Jetziges Gold: " + kaufender.getGold() + " / Handkartenanzahl: " + kaufender.getHand().size());
				}
			};
			
			for( ActionListener temp : kaufButtons[i].getActionListeners() ) {
				kaufButtons[i].removeActionListener( temp );
			}
			kaufButtons[innerI].addActionListener(l);
		}
		goldAnzeige.setText(kaufender.getName() + ", Sie haben " + kaufender.getGold() + " Gulden.");
		
		System.out.println(kaufender.getName() + " hat nun folgende Karten:");kaufender.printHand();
	
	}
}