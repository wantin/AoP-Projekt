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
	private JLabel sieg;

	private JButton abbrechenLinks = new JButton();
	private JButton abbrechenRechts = new JButton();
	private JButton passenLinks = new JButton();
	private JButton passenRechts = new JButton();
	private JButton[] kaufButtons= new JButton[3];
	private JButton[][] feldButtons;
	private JButton[] linksHandkarten = new JButton[8];
	private JButton[] rechtsHandkarten = new JButton[8];
	private JButton ppbttn = new JButton("Player vs. Player");
	private JButton pkbttn = new JButton("Player vs. KI");
	private JButton start = new JButton("START");

	private JLabel[] preisLabel = new JLabel[3];
	private JLabel[][] ruestungKarte = new JLabel[6][6];
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
	private JLabel text5 = new JLabel();
	private JLabel text6 = new JLabel();

	private JTextArea linkerplyr = new JTextArea();
	private JTextArea rechterplyr = new JTextArea();
	GUI anzeige = this;


	Random zufall = new Random();
	
	public void aktualisierenHand(Spieler aktiver){
		for (int i = 0; i < aktiver.getHand().size(); i++) {
			if(aktiver.getSeite()=="links"){
				linksHandkarten[i].setIcon(new ImageIcon(((new ImageIcon(aktiver.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
			}else{
				rechtsHandkarten[i].setIcon(new ImageIcon(((new ImageIcon(aktiver.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
			}
		}
		for (int i = aktiver.getHand().size(); i < 8; i++) {
			if(aktiver.getSeite()=="links"){
				linksHandkarten[i].setIcon(null);
			}else{
				rechtsHandkarten[i].setIcon(null);
			}
		}
	}
	
	public void aktualisierenHand(Spieler links, Spieler rechts){
		
		//Handkarten des linken Spielers aktualisiert darstellen
		for(int i = 0; i < links.getHand().size(); i++){ 
			linksHandkarten[i].setIcon(new ImageIcon(((new ImageIcon(links.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
		}
		//leere Felder leeren
		for (int i = links.getHand().size(); i < 8; i++) {
			linksHandkarten[i].setIcon(null);
		}
		//Handkarten des rechten Spielers aktualisiert darstellen
		for(int i = 0; i < rechts.getHand().size(); i++){ 
			rechtsHandkarten[i].setIcon(new ImageIcon(((new ImageIcon(rechts.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
		}
		for (int i = rechts.getHand().size(); i < 8; i++) {
			rechtsHandkarten[i].setIcon(null);
		}
	}
	
	public void aktualisierenFeld(Feld spielbrett){
		for (int i = 0; i < feldButtons.length; i++) {
			for (int j = 0; j < feldButtons.length; j++) {
				if(spielbrett.besetzt(i, j)){
					staerkeKarte[i][j].setText(Integer.toString(spielbrett.getEinheit(i, j).getStaerke()));
					ruestungKarte[i][j].setText(Integer.toString(spielbrett.getEinheit(i, j).getRuestung()));
					String pfad = spielbrett.getEinheit(i, j).getBildPfad();
					feldButtons[i][j].setIcon(new ImageIcon(((new ImageIcon(pfad)).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
				}else{
					feldButtons[i][j].setIcon(null);
					staerkeKarte[i][j].setText("");
					ruestungKarte[i][j].setText("");
				}
			}
		}
	}
	
	
	//Konstruktor
	public GUI(Feld spielbrett, Spieler links, Spieler rechts){

		this.setTitle("Vona");
		this.setSize(1200, 730);
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
		
		//spieler 1 im linken Feld
		Icon lIcon = new ImageIcon(getClass().getResource("seiten.png"));
		lfeld = new JLabel(lIcon);
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
		lLabel.setBounds(0,235,232,465);
		lLabel.setLayout(new GridLayout(4,2,4,4));//Einteilung Panel und Zwischenabstände	

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
		            links.setAktionAuswahlHand(true);
					links.setAuswahlPhase(1);
					rechts.setAuswahlPhase(1);
		        }
		    });
		    lLabel.add(linksHandkarten[i]);	
		} 
		
		//Abbrechenbutton links
		abbrechenLinks.setLayout(null);
		abbrechenLinks.setBounds(50, 80, 150, 40);
		abbrechenLinks.setOpaque(false);
		abbrechenLinks.setContentAreaFilled(false);;
		abbrechenLinks.setText("Aktion abbrechen");
		abbrechenLinks.setForeground(lfeld.getForeground());
		abbrechenLinks.setFont(new Font(abbrechenLinks.getText(), Font.BOLD, 14));
	    
	    if (abbrechenLinks != null){
	    	//zug rückgängig machen
	    }
	    
		//Passenbutton links
		passenLinks.setLayout(null);
		passenLinks.setBounds(50, 130, 150, 40);
		passenLinks.setOpaque(false);
		passenLinks.setContentAreaFilled(false);;
		passenLinks.setText("Passen");
		passenLinks.setForeground(lfeld.getForeground());
		passenLinks.setFont(new Font(abbrechenLinks.getText(), Font.BOLD, 14));
	    
	    if (passenLinks != null){
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
		lfeld.add(passenLinks);
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
		mLabel.setLayout(new GridLayout(6,6,4,4));
		feldButtons = new JButton[m][n];
		
		for(int c = 0; c < m; c++){
			for(int d = 0; d < n; d++){
				
				staerkeKarte[c][d] = new JLabel();
				staerkeKarte[c][d].setLayout(null);
				staerkeKarte[c][d].setForeground(new Color(50,0,0));
				staerkeKarte[c][d].setBounds(90,90,14,14);
				staerkeKarte[c][d].setText("");
				
				ruestungKarte[c][d] = new JLabel();
				ruestungKarte[c][d].setLayout(null);
				ruestungKarte[c][d].setForeground(new Color(0,50,0));
				ruestungKarte[c][d].setBounds(24,90,14,14);
				ruestungKarte[c][d].setText("");

				feldButtons[c][d] = new JButton();
				feldButtons[c][d].setLayout(null);
				feldButtons[c][d].setOpaque(false);
				feldButtons[c][d].setContentAreaFilled(false);
				feldButtons[c][d].add(ruestungKarte[c][d]);
				feldButtons[c][d].add(staerkeKarte[c][d]);
				final int zeile = c;
				final int spalte = d;
				feldButtons[c][d].addActionListener(new ActionListener(){
					//muss noch abgeändert werden, da bisher der attbttn(für angreifbare einheiten) genutzt wird(auch wenn freies feld)
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(links.getAuswahlPhase() == 0 || rechts.getAuswahlPhase() == 0){ 
							//Einheit ausw�hlen um Aktion durchzuf�hren
							rechts.setAktionAuswahlEinheit(spielbrett.getEinheit(zeile, spalte));
							links.setAktionAuswahlEinheit(spielbrett.getEinheit(zeile, spalte));
							optionenZeigenEinheit(spielbrett.getEinheit(zeile, spalte), spielbrett);
							links.setAuswahlPhase(1);
							rechts.setAuswahlPhase(1);
							links.setAktionAuswahlHand(false);
							rechts.setAktionAuswahlHand(false);
						}else{ //Ziel ausw�hlen
							links.setAktionsAuswahlZeile(zeile);
							rechts.setAktionsAuswahlZeile(zeile);
							links.setAktionsAuswahlSpalte(spalte);
							rechts.setAktionsAuswahlSpalte(spalte);
						}
					}
				});
			mLabel.add(feldButtons[c][d]);	 
			}
		}
		mitte.add(mLabel);

		passenLinks.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				links.setPassen(true);
			}
		});
		passenRechts.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rechts.setPassen(true);
			}
		});
		
		abbrechenLinks.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//reset
				links.setPassen(false);
				links.setAktionsAuswahlZeile(-1);
				links.setAktionsAuswahlSpalte(-1);
				links.setAktionsAuswahl0(-1);
				links.setAktionAuswahlEinheit(null);
				links.setAuswahlPhase(0);
				optionenZeigenSpieler(links);
			}
		});
		
		abbrechenRechts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//reset
				rechts.setPassen(false);
				rechts.setAktionsAuswahlZeile(-1);
				rechts.setAktionsAuswahlSpalte(-1);
				rechts.setAktionsAuswahl0(-1);
				rechts.setAktionAuswahlEinheit(null);
				rechts.setAuswahlPhase(0);
				optionenZeigenSpieler(rechts);
			}
		});
		
		//spieler 2 im rechten Feld
		Icon rIcon = new ImageIcon(getClass().getResource("seiten.png"));
		rfeld = new JLabel(rIcon);
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
		abbrechenRechts.setBounds(50, 80, 150, 40);
		abbrechenRechts.setOpaque(false);
		abbrechenRechts.setContentAreaFilled(false);
		abbrechenRechts.setText("Zug abbrechen");
		abbrechenRechts.setForeground(rfeld.getForeground());
		abbrechenRechts.setFont(new Font(abbrechenLinks.getText(), Font.PLAIN, 14));
	    
	    if (abbrechenRechts != null){
	    	//zug rückgängig machen
	    }
	    
		//Passenbutton rechts

		passenRechts.setLayout(null);
		passenRechts.setBounds(50, 130, 150, 40);
		passenRechts.setOpaque(false);
		passenRechts.setContentAreaFilled(false);;
		passenRechts.setText("Passen");
		passenRechts.setForeground(rfeld.getForeground());
		passenRechts.setFont(new Font(abbrechenLinks.getText(), Font.PLAIN, 14));
	    
	    if (passenRechts != null){
	    	//zug rückgängig machen
	    }

		//Kartenfeld rechtes Feld
		JLabel rLabel = new JLabel();
		rLabel.setLayout(null);
		rLabel.setBounds(18,235,232,465);
		rLabel.setLayout(new GridLayout(4,2,4,4));//Einteilung Panel und Zwischenabständes
		
		//neu
		for(int i = 0; i < 8; i++){  //8 als Handkartenlimit
			rechtsHandkarten[i]= new JButton();
			rechtsHandkarten[i].setOpaque(false);
			rechtsHandkarten[i].setContentAreaFilled(false);
			final int final_i=i;
		    rechtsHandkarten[i].addActionListener(new ActionListener(){
		        @Override
		        public void actionPerformed(ActionEvent arg0){
		            optionenZeigenHandkarte(rechts.getHand().get(final_i), spielbrett);
		            rechts.setAktionsAuswahl0(final_i);
		            rechts.setAktionAuswahlHand(true);
					links.setAuswahlPhase(1);
					rechts.setAuswahlPhase(1);
		        }
		    });
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
		rfeld.add(passenRechts);
		rfeld.add(rLabel);
		rfeld.add(rOrnament);
		
		//Sieger Bekanntgabe
		Icon sIcon = new ImageIcon(getClass().getResource("sieg.png"));
		sieg = new JLabel(sIcon);
		sieg.setLayout(null);
		sieg.setVisible(true);
		sieg.setBounds(275,150,650,400);
		sieg.setForeground(Color.WHITE);

		text5.setBounds(125,100,400,60);
		text5.setFont(new Font(text5.getText(), Font.BOLD, 30));
		text5.setForeground(sieg.getForeground());
		text5.setHorizontalAlignment(SwingConstants.CENTER);
		text6.setBounds(50,180,600,60);
		text6.setFont(new Font(text6.getText(), Font.BOLD, 30));
		text6.setForeground(sieg.getForeground());
		text6.setHorizontalAlignment(SwingConstants.CENTER);
		sieg.add(text5);
		sieg.add(text6);
		
		if(!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) {
			text5.setText(links.getName());
			text6.setText("hat gewonnen!!!");
		}else {
			if(!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty()) {
				text5.setText(rechts.getName());
				text6.setText("hat gewonnen!!!");
			}else {
				text6.setText("Die Partie endet unentschieden.");
			}
		}	

		content.add(auswahl);
		content.add(kaufPane);
		content.add(mitte);
		content.add(lfeld);
		content.add(rfeld);
		content.add(sieg);

	}

	//Namen wÃ¤helen
	public void setup1(Spieler links, Spieler rechts){
		
		if(rechts.getClass()==KI.class){
			text3.setVisible(false);
			rechterplyr.setVisible(false);
			
		}else{
			text4.setVisible(false);
		}

		text3.setText("Gebt Eure Titel ein (Max. 20 Zeichen):");
		text3.setFont(text2.getFont());
		text3.setBounds(100,100,270,20);
		text3.setForeground(auswahl.getForeground());

		linkerplyr.setFont(new Font("", Font.ITALIC, 18));
		linkerplyr.setBounds(100,200,200,30);
		linkerplyr.setForeground(Color.white);
		linkerplyr.setOpaque(false);
		linkerplyr.setBorder(BorderFactory.createLineBorder(Color.darkGray));

		rechterplyr.setFont(linkerplyr.getFont());
		rechterplyr.setBounds(370,200,200,30);
		rechterplyr.setForeground(Color.black);
		rechterplyr.setOpaque(false);
		rechterplyr.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		text4.setText("Gebt Euren Titel ein(Max. 20 Zeichen):");
		text4.setFont(text2.getFont());
		text4.setBounds(100,100,270,40);
		text4.setForeground(auswahl.getForeground());

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

				if (rechts.getClass()!=KI.class){
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
				}else{ //anfangen bei PvE
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
		});

		auswahl.add(text3);
		auswahl.add(text4);
		auswahl.add(linkerplyr);
		auswahl.add(rechterplyr);
		auswahl.add(ppbttn);
		auswahl.add(pkbttn);
		auswahl.add(start);
	}
	
	//Hilfsfunktion, die alle Buttons disabled
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
		passenLinks.setEnabled(false);
		passenRechts.setEnabled(false);
	}
	
	//Zeigt f�r eine Handkarte an, was man damit machen kann
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
						if(!spielbrett.besetzt(i, j)){
							feldButtons[i][j].setEnabled(true);
						}
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
	
	//zeigt an, was man mit einer ausgespielten Einheit f�r Optionen hat.
	public void optionenZeigenEinheit(Einheit aktive, Feld spielbrett){
		optionenKeine();
		for (int i = 0; i < aktive.zeigeBewegung(spielbrett).size(); i++) {
			int x = aktive.zeigeBewegung(spielbrett).get(i)[0];
			int y = aktive.zeigeBewegung(spielbrett).get(i)[1];
			feldButtons[x][y].setEnabled(true);
		}
		for (int i = 0; i < aktive.zeigeAngriff(spielbrett).size(); i++) {
			int x = aktive.zeigeAngriff(spielbrett).get(i)[0];
			int y = aktive.zeigeAngriff(spielbrett).get(i)[1];
			feldButtons[x][y].setEnabled(true);
		}		
	}
	
	//Zeigt an, was ein Spieler in seinem Zug tun kann
	public void optionenZeigenSpieler(Spieler aktiverSpieler){
		optionenKeine();
		if(aktiverSpieler.getSeite()=="links"){
			passenLinks.setEnabled(true);
			for (int i = 0; i < linksHandkarten.length; i++) {
				linksHandkarten[i].setEnabled(true);
			}
		}else{
			passenRechts.setEnabled(true);
			for (int i = 0; i < rechtsHandkarten.length; i++) {
				rechtsHandkarten[i].setEnabled(true);
			}
		}
		for (int i = 0; i < aktiverSpieler.getTruppen().size(); i++) {
			if(aktiverSpieler.getTruppen().get(i).getBereit() > 0){
				int koordinaten[] = aktiverSpieler.getTruppen().get(i).getPosition();
				feldButtons[koordinaten[0]][koordinaten[1]].setEnabled(true);
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
			kaufButtons[i].setIcon(new ImageIcon(((new ImageIcon(angebot[i].getBildPfad())).getImage()).getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH)));
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