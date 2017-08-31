package einsnull;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class GUI extends JFrame{

	static Scanner input = new Scanner(System.in);

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JLabel auswahl;
	private JLabel kaufPane;
	private JLabel lfeld;
	private JLabel rfeld;

	private JButton abbrechenLinks = new JButton();
	private JButton abbrechenRechts = new JButton();
	private JButton[] kaufButtons= new JButton[3];
	private JButton[][] feldButtons;
	private JButton[] linksHandkarten = new JButton[8];
	private JButton[] rechtsHandkarten = new JButton[8];
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
	
	public void aktualisierenHand(Spieler links, Spieler rechts){
		
		//Handkarten des linken Spielers aktualisiert darstellen
		for(int i = 0; i < links.getHand().size(); i++){ 
			linksHandkarten[i].setIcon(new ImageIcon(((new ImageIcon(links.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH)));
		}
		//Handkarten des rechten Spielers aktualisiert darstellen
		for(int i = 0; i < rechts.getHand().size(); i++){ 
			rechtsHandkarten[i].setIcon(new ImageIcon(((new ImageIcon(rechts.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH)));
		}		
	}
	
	//Konstruktor
	public GUI(Feld spielbrett, Spieler links, Spieler rechts){

		this.setTitle("Vona");
		this.setBounds(400, 100, 1200, 730);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	

		//Hintergrund;
		content = (JPanel) this.getContentPane();
		content.setLayout(null);

		//Spielart und Namen auswahl
		Icon aIcon = new ImageIcon(getClass().getResource("feld.png"));
		auswahl = new JLabel(aIcon);
		auswahl.setLayout(null);
		auswahl.setForeground(Color.white);
		auswahl.setBounds(275,150,650,400);		
		//auswahl.setVisible(false); //falls man den Anfang �berspringen will
		
		//Karten kaufen
		kaufPane = new JLabel(aIcon);
		kaufPane.setLayout(null);
		kaufPane.setForeground(Color.white);
		kaufPane.setBackground(new Color(150,100,50));
		kaufPane.setBounds(275,150,650,400);
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
		ppbttn.setOpaque(false);
		ppbttn.setContentAreaFilled(false);
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
		pkbttn.setOpaque(false);
		pkbttn.setContentAreaFilled(false);
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
		Icon lIcon = new ImageIcon(getClass().getResource("seiten.png"));
		JLabel lfeld = new JLabel(lIcon);
		lfeld.setBounds(0,0,250,730);		
		lfeld.setLayout(null);
		lfeld.setForeground(Color.white);

		//angezeigter Text Spieler und Handkarten
		plyr1.setBounds(25,20,200,40);
		plyr1.setHorizontalAlignment(SwingConstants.CENTER);
		plyr1.setForeground(lfeld.getForeground());
		plyr1.setFont(new Font(plyr1.getText(), Font.ITALIC, 16));
		plyr1k.setBounds(100,180,90,40);
		plyr1k.setForeground(lfeld.getForeground());

		//Kartenfeld linkes Feld
		JLabel lLabel = new JLabel();
		lLabel.setLayout(null);
		lLabel.setBounds(0,230,240,470);
		lLabel.setLayout(new GridLayout(4,2,4,2));//Einteilung Panel und Zwischenabstände	

		for(int i = 0; i < 8; i++){  //8 als Handkartenlimit
		    linksHandkarten[i]= new JButton();
		    linksHandkarten[i].setOpaque(false);
		    linksHandkarten[i].setContentAreaFilled(false); 
		    final int final_i=i;
		    linksHandkarten[i].addActionListener(new ActionListener(){
		        @Override
		        public void actionPerformed(ActionEvent arg0){
		            optionenZeigenHandkarte(links.getHand().get(final_i), spielbrett);
		            links.setAktionsAuswahl0(final_i);
		        }
		    });
		    lLabel.add(linksHandkarten[i]);	
		} 
		
		//Abbrechenbutton links
		abbrechenLinks.setLayout(null);
		abbrechenLinks.setBounds(50, 90, 150, 40);
		abbrechenLinks.setOpaque(false);
		abbrechenLinks.setContentAreaFilled(false);;
		abbrechenLinks.setText("Zug abbrechen");
		abbrechenLinks.setFont(new Font(abbrechenLinks.getText(), Font.PLAIN, 14));
	    
	    if (abbrechenLinks != null){
	    	//zug rückgängig machen
	    }
		
		
		//Verziehrung links
		Icon oIcon = new ImageIcon(getClass().getResource("ornament.png"));
		JLabel lOrnament = new JLabel(oIcon);
		lOrnament.setVisible(true);
		lOrnament.setLayout(null);
		lOrnament.setBounds(185, 0, 70, 700);

		lfeld.add(plyr1);
		lfeld.add(plyr1k);
		lfeld.add(abbrechenLinks);
		lfeld.add(lLabel);
		lfeld.add(lOrnament);

		//spielfeld
		Icon mIcon = new ImageIcon(getClass().getResource("background.png"));
		JLabel mitte = new JLabel(mIcon);	
		mitte.setLayout(null);
		mitte.setForeground(Color.orange);
		mitte.setBounds(250,0,700,730);
		
		//Schleife Buttons f�r mittleres Feld
		int m,n;
		m = 6;
		n = 6;
		JLabel mLabel = new JLabel();
		mLabel.setLayout(null);
		mLabel.setBounds(0,0,700,700);
		mLabel.setLayout(new GridLayout(6,6,4,2));
		feldButtons = new JButton[m][n];
		
		for(int c = 0; c < m; c++){
			for(int d = 0; d < n; d++){
				
				staerkeKarte[c][d] = new JLabel();
				staerkeKarte[c][d].setLayout(null);
				staerkeKarte[c][d].setForeground(new Color(0,55,55));
				staerkeKarte[c][d].setBounds(50,0,10,10);
				staerkeKarte[c][d].setText(Integer.toString(c+d));

				feldButtons[c][d] = new JButton();
				feldButtons[c][d].setLayout(null);
				feldButtons[c][d].setOpaque(false);
				feldButtons[c][d].setContentAreaFilled(false);
				feldButtons[0][0].setIcon(new ImageIcon(((new ImageIcon("bilder/einheiten/hasenritter.jpg")).getImage()).getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH)));
				feldButtons[c][d].add(staerkeKarte[c][d]);
				feldButtons[c][d].addActionListener(new ActionListener(){

					//muss noch abge�ndert werden, da bisher der attbttn(f�r angreifbare einheiten) genutzt wird(auch wenn freies feld)
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
			mLabel.add(feldButtons[c][d]);	 
			}
		}
		mitte.add(mLabel);

		//spieler 2 im rechten Feld
		Icon rIcon = new ImageIcon(getClass().getResource("seiten.png"));
		JLabel rfeld = new JLabel(rIcon);
		rfeld.setLayout(null);
		rfeld.setForeground(Color.WHITE);
		rfeld.setBounds(950, 0, 250, 730);

		//angezeigter Text Spieler und Handkarten
		plyr2.setBounds(25,20,200,40);
		plyr2.setHorizontalAlignment(SwingConstants.CENTER);
		plyr2.setForeground(rfeld.getForeground());
		plyr2.setFont(new Font(plyr1.getText(), Font.ITALIC, 16));
		plyr2k.setBounds(100,180,90,40);
		plyr2k.setForeground(rfeld.getForeground());
		
		//Abbruchbutton rechts
		abbrechenRechts.setLayout(null);
		abbrechenRechts.setBounds(50, 90, 150, 40);
		abbrechenRechts.setOpaque(false);
		abbrechenRechts.setContentAreaFilled(false);
		abbrechenRechts.setText("Zug abbrechen");
		abbrechenRechts.setFont(new Font(abbrechenLinks.getText(), Font.PLAIN, 14));
	    
	    if (abbrechenRechts != null){
	    	//zug rückgängig machen
	    }

		//Kartenfeld rechtes Feld
		JLabel rLabel = new JLabel();
		rLabel.setLayout(null);
		rLabel.setBounds(10,230,240,470);
		rLabel.setLayout(new GridLayout(4,2,4,2));//Einteilung Panel und Zwischenabständes
		
		//neu
		for(int i = 0; i < 8; i++){  //8 als Handkartenlimit
			rechtsHandkarten[i]= new JButton();
			rechtsHandkarten[i].setOpaque(false);
			rechtsHandkarten[i].setContentAreaFilled(false);
			rLabel.add(rechtsHandkarten[i]);	
		}
		
		//Verziehrung rechts
		Icon oIcon1 = new ImageIcon(getClass().getResource("ornament1.png"));
		JLabel rOrnament = new JLabel(oIcon1);
		rOrnament.setLayout(null);
		rOrnament.setBounds(-5, 0, 70, 700);

		rfeld.add(plyr2);
		rfeld.add(plyr2k);
		rfeld.add(abbrechenRechts);
		rfeld.add(rLabel);
		rfeld.add(rOrnament);

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
		linkerplyr.setBounds(100,200,200,30);
		linkerplyr.setForeground(Color.white);
		linkerplyr.setOpaque(false);
		linkerplyr.setBorder(BorderFactory.createLineBorder(Color.darkGray));

		rechterplyr.setVisible(false);
		rechterplyr.setFont(linkerplyr.getFont());
		rechterplyr.setBounds(370,200,200,30);
		rechterplyr.setForeground(Color.black);
		rechterplyr.setOpaque(false);
		rechterplyr.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		text4.setVisible(false);
		text4.setText("Gebt Euren Titel ein(Max. 20 Zeichen):");
		text4.setFont(text2.getFont());
		text4.setBounds(100,100,270,40);
		text4.setForeground(auswahl.getForeground());

		start.setVisible(false);
		start.setOpaque(false);
		start.setContentAreaFilled(false);
		start.setBounds(320,300,160,60);
		start.setForeground(auswahl.getForeground());
		start.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//regelt die Länge der Namen(beschränkt auf 20 Zeichen
		//panel bleibt solange sichtbar bis richtige länge, dann unsichtbar
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
	
	public void optionenKeine(){
		for (int i = 0; i < rechtsHandkarten.length; i++) {
			rechtsHandkarten[i].setEnabled(false);
			linksHandkarten[i].setEnabled(false);
		}
		for (int i = 0; i < feldButtons.length; i++) {
			for (int j = 0; j < feldButtons[0].length; j++) {
				feldButtons[i][j].setEnabled(false);
			}
		}
		abbrechenLinks.setEnabled(false);
		abbrechenRechts.setEnabled(false);
	}
	
	public void optionenZeigenHandkarte(Karte auszuspielende, Feld spielbrett){
		optionenKeine();
		if(auszuspielende.getBesitzer().getSeite()=="links"){
			abbrechenLinks.setEnabled(true);
		}else{
			abbrechenRechts.setEnabled(true);
		}
		if(auszuspielende.getArt()=="einheit"){
			if(auszuspielende.getBesitzer().getSeite()=="links"){
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 2; j++) {
						feldButtons[i][j].setEnabled(true);
					}
				}
			}else{ //rechter Spieler setzt auf die rechte Seite
				for (int i = 0; i < 6; i++) {
					for (int j = 4; j < 6; j++) {
						feldButtons[i][j].setEnabled(true);
					}
				}
			}
		}else{ //Effektkarten Ziel w�hlen
			if(auszuspielende.getArt()=="fluch"){ //fl�che wirkt man auf gegner
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if(spielbrett.besetzt(i, j)){ //vermeiden einer nullpointerexception bei dem n�chsten Test
							if(spielbrett.getEinheit(i, j).getBesitzer() != auszuspielende.getBesitzer()){
								feldButtons[i][j].setEnabled(true);
							}
						}
					}
				}
			}else{ //da es kein Fluch ist muss es ein Segen sein.
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if(spielbrett.besetzt(i, j)){
							if(spielbrett.getEinheit(i, j).getBesitzer() == auszuspielende.getBesitzer()){ //Diesmal muss es eine eigene Einheit sein
								feldButtons[i][j].setEnabled(true);
							}
						}
					}
				}
			}
		}
	}
	
	public void optionenZeigenSpieler(Spieler aktiverSpieler){
		optionenKeine();
		if(aktiverSpieler.getSeite()=="links"){
			for (int i = 0; i < linksHandkarten.length; i++) {
				linksHandkarten[i].setEnabled(true);
			}
		}else{
			for (int i = 0; i < rechtsHandkarten.length; i++) {
				rechtsHandkarten[i].setEnabled(true);
			}
		}
	}

	
	//oben genutzte Methode, die auf Buttoneingabe reagiert.
	public void setPlayer(Spieler rechts, boolean human){
		if(human);
		else rechts= new KI();
	}

	//was ist das?
	public void setup0(Spieler rechts){} //PvP (true) oder PvE (false)
		//auswahl.setToolTipText(text); //wenn Cursor darÃƒÂ¼ber liegt erscheinender Text

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
			
			final int innerI = i;
			
			ActionListener l = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					kaufender.getHand().add(angebot[innerI]);
					kaufender.setGold(kaufender.getGold()-angebot[innerI].getPreis());
					if(kaufender.getSeite()=="links"){
						aktualisierenHand(kaufender, anderer);
					}else{
						aktualisierenHand(anderer, kaufender);
					}
					content.repaint();
					
					if (kaufender.getHand().size() < maxHand && kaufender.getGold() >= minPreis){ //weitereinkaufen
						kaufen(kaufender, anderer); 
					}
					else{
						if(anderer.getHand().size() == 0){ //der andere kauft
							anderer.kaufen(anzeige, kaufender);
						}else{	//aufräumen
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