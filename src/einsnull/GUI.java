package einsnull;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class GUI extends JFrame {

	static Scanner inputScanner = new Scanner(System.in);

	private JPanel contentPNL;
	private JLabel auswahlLBL;
	private JLabel kaufOberflLBL;
	private JLabel lfeldLBL;
	private JLabel rfeldLBL;
	private JLabel siegLBL;

	private JButton linksCancelBTN = new JButton();
	private JButton rechtsCancelBTN = new JButton();
	private JButton linksPassBTN = new JButton();
	private JButton rechtsPassBTN = new JButton();
	private JButton[] kaufBTNS= new JButton[3];
	private JButton[][] feldButtons;
	private JButton[] linksHandkartenBTNS = new JButton[8];
	private JButton[] rechtsHandkartenBTNS = new JButton[8];
	private JButton pvpBTN = new JButton("Player vs. Player");
	private JButton pvkiBTN = new JButton("Player vs. KI");
	private JButton startBTN = new JButton("START");

	private JLabel[] preisLBL = new JLabel[3];
	private JLabel[][] ruestungKarteLBL = new JLabel[6][6];
	private JLabel[][] staerkeKarteLBL = new JLabel[6][6];
	private JLabel goldLBL = new JLabel();
	private JLabel kaufLBL = new JLabel("Klicken Sie auf eine der drei Karten um sie zu kaufen.");
	private JLabel player1LBL = new JLabel("Spieler 1");
	private JLabel player2LBL = new JLabel ("Spieler 2");
	private JLabel player1HandkartenLBL = new JLabel ("Handkarten");
	private JLabel player2HandkartenLBL = new JLabel ("Handkarten");
	private JLabel text2LBL = new JLabel();
	private JLabel text3LBL = new JLabel();
	private JLabel text4LBL = new JLabel();
	private JLabel text5LBL = new JLabel();
	private JLabel text6LBL = new JLabel();

	private JTextArea playerlinksTA = new JTextArea();
	private JTextArea playerrechtsTA = new JTextArea();
	GUI anzeige = this;


	Random zufall = new Random();
	
	public void aktualisierenHand(Spieler aktiver) {
		for (int i = 0; i < aktiver.getHand().size(); i++) {
			if (aktiver.getSeite()=="links") {
				linksHandkartenBTNS[i].setIcon(new ImageIcon(((new ImageIcon(aktiver.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
				linksHandkartenBTNS[i].setToolTipText("<html><img src=\"" + Main.class.getResource(aktiver.getHand().get(i).getTooltipPfad()));
			} else {
				rechtsHandkartenBTNS[i].setIcon(new ImageIcon(((new ImageIcon(aktiver.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
				rechtsHandkartenBTNS[i].setToolTipText("<html><img src= \"" + Main.class.getResource(aktiver.getHand().get(i).getTooltipPfad()));
			}
		}
		for (int i = aktiver.getHand().size(); i < 8; i++) {
			if (aktiver.getSeite()=="links") {
				linksHandkartenBTNS[i].setIcon(null);
				linksHandkartenBTNS[i].setToolTipText("");
			} else {
				rechtsHandkartenBTNS[i].setIcon(null);
				rechtsHandkartenBTNS[i].setToolTipText("");
			}
		}
	}
	
	public void aktualisierenHand(Spieler links, Spieler rechts) {
		
		//Handkarten des linken Spielers aktualisiert darstellen
		for (int i = 0; i < links.getHand().size(); i++) { 
			linksHandkartenBTNS[i].setIcon(new ImageIcon(((new ImageIcon(links.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
			linksHandkartenBTNS[i].setToolTipText("<html><img src= \"" + Main.class.getResource(links.getHand().get(i).getTooltipPfad()));
		}
		//leere Felder leeren
		for (int i = links.getHand().size(); i < 8; i++) {
			linksHandkartenBTNS[i].setIcon(null);
			linksHandkartenBTNS[i].setToolTipText("");
		}
		//Handkarten des rechten Spielers aktualisiert darstellen
		for (int i = 0; i < rechts.getHand().size(); i++) { 
			rechtsHandkartenBTNS[i].setIcon(new ImageIcon(((new ImageIcon(rechts.getHand().get(i).getBildPfad())).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
			rechtsHandkartenBTNS[i].setToolTipText("<html><img src= \"" + Main.class.getResource(rechts.getHand().get(i).getTooltipPfad()));
		}
		for (int i = rechts.getHand().size(); i < 8; i++) {
			rechtsHandkartenBTNS[i].setIcon(null);
			rechtsHandkartenBTNS[i].setToolTipText("");
		}
	}
	
	public void aktualisierenFeld(Feld spielbrett) {
		for (int i = 0; i < feldButtons.length; i++) {
			for (int j = 0; j < feldButtons.length; j++) {
				if (spielbrett.besetzt(i, j)) {
					staerkeKarteLBL[i][j].setText(Integer.toString(spielbrett.getEinheit(i, j).getStaerke()));
					ruestungKarteLBL[i][j].setText(Integer.toString(spielbrett.getEinheit(i, j).getRuestung()));
					String pfad = spielbrett.getEinheit(i, j).getBildPfad();
					feldButtons[i][j].setIcon(new ImageIcon(((new ImageIcon(pfad)).getImage()).getScaledInstance(114, 114, java.awt.Image.SCALE_SMOOTH)));
					feldButtons[i][j].setToolTipText("<html><img src=\"" + Main.class.getResource(spielbrett.getEinheit(i, j).getTooltipPfad()));
				} else {
					feldButtons[i][j].setIcon(null);
					staerkeKarteLBL[i][j].setText("");
					ruestungKarteLBL[i][j].setText("");
					feldButtons[i][j].setToolTipText("");
				}
			}
		}
	}
	
	
	//Konstruktor
	public GUI(Feld spielbrett, Spieler links, Spieler rechts) {

		this.setTitle("Eldotin");
		this.setSize(1200, 730);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setLocationRelativeTo(null);

		//Hintergrund;
		contentPNL = (JPanel) this.getContentPane();
		contentPNL.setLayout(null);

		//Spielart und Namen auswahl
		Icon aIcon = new ImageIcon(getClass().getResource("feld.png"));
		auswahlLBL = new JLabel(aIcon);
		auswahlLBL.setLayout(null);
		auswahlLBL.setForeground(Color.white);
		auswahlLBL.setBounds(275,150,650,400);		
		//auswahl.setVisible(false); //falls man den Anfang �berspringen will
		
		//Karten kaufen
		kaufOberflLBL = new JLabel(aIcon);
		kaufOberflLBL.setLayout(null);
		kaufOberflLBL.setForeground(Color.white);
		kaufOberflLBL.setBounds(275,150,650,400);
		kaufOberflLBL.setVisible(false);
		
		for (int i = 0; i < 3; i++) {
			kaufBTNS[i]= new JButton();
			kaufBTNS[i].setBounds(40 + i*190, 150, 160, 160);
			kaufOberflLBL.add(kaufBTNS[i]);
			preisLBL[i] = new JLabel();
			preisLBL[i].setLayout(null);
			preisLBL[i].setForeground(new Color(200, 180, 70));
			preisLBL[i].setBounds(100+i*220, 340, 160, 30);
		}
		
		goldLBL.setBounds(100, 30, 1000, 30);
		kaufLBL.setBounds(100, 80, 1000, 30);
		kaufOberflLBL.add(goldLBL);
		kaufOberflLBL.add(kaufLBL);
		
		//spieler 1 im linken Feld
		Icon lIcon = new ImageIcon(getClass().getResource("seiten.png"));
		lfeldLBL = new JLabel(lIcon);
		lfeldLBL.setBounds(0,0,250,730);		
		lfeldLBL.setLayout(null);
		lfeldLBL.setForeground(Color.white);

		//angezeigter Text Spieler und Handkarten
		player1LBL.setBounds(25,20,200,40);
		player1LBL.setHorizontalAlignment(SwingConstants.CENTER);
		player1LBL.setForeground(lfeldLBL.getForeground());
		player1LBL.setFont(new Font(player1LBL.getText(), Font.ITALIC, 16));
		player1HandkartenLBL.setBounds(100,180,90,40);
		player1HandkartenLBL.setForeground(lfeldLBL.getForeground());

		//Kartenfeld linkes Feld
		JLabel lLabel = new JLabel();
		lLabel.setLayout(null);
		lLabel.setBounds(0,235,232,465);
		lLabel.setLayout(new GridLayout(4,2,4,4));//Einteilung Panel und Zwischenabstände	

		for (int i = 0; i < 8; i++) {  //8 als Handkartenlimit
		    linksHandkartenBTNS[i]= new JButton();
		    linksHandkartenBTNS[i].setOpaque(false);
		    linksHandkartenBTNS[i].setContentAreaFilled(false); 
		    final int final_i=i;
		    linksHandkartenBTNS[i].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent arg0) {
		            optionenZeigenHandkarte(links.getHand().get(final_i), spielbrett);	// erzeugt IOOB-error!!!
		            links.setAktionsAuswahl0(final_i);
		            links.setAktionAuswahlHand(true);
					links.setAuswahlPhase(1);
					rechts.setAuswahlPhase(1);
		        }
		    });
		    lLabel.add(linksHandkartenBTNS[i]);	
		} 
		
		//Abbrechenbutton links
		linksCancelBTN.setLayout(null);
		linksCancelBTN.setBounds(50, 80, 150, 40);
		linksCancelBTN.setOpaque(false);
		linksCancelBTN.setContentAreaFilled(false);;
		linksCancelBTN.setText("Aktion abbrechen");
		linksCancelBTN.setForeground(lfeldLBL.getForeground());
		linksCancelBTN.setFont(new Font(linksCancelBTN.getText(), Font.BOLD, 14));
		linksCancelBTN.setToolTipText("Eure Exzellenz: Wollt ihr eine andere Aktion wählen?");
	    
	    if (linksCancelBTN != null) {
	    	//zug rückgängig machen
	    }
	    
		//Passenbutton links
		linksPassBTN.setLayout(null);
		linksPassBTN.setBounds(50, 130, 150, 40);
		linksPassBTN.setOpaque(false);
		linksPassBTN.setContentAreaFilled(false);;
		linksPassBTN.setText("Passen");
		linksPassBTN.setForeground(lfeldLBL.getForeground());
		linksPassBTN.setFont(new Font(linksCancelBTN.getText(), Font.BOLD, 14));
		linksPassBTN.setToolTipText("Eure Exzellenz: Hiermit lasst ihr eine eurer Aktionen verstreichen.");
	    
	    if (linksPassBTN != null) {
	    	//zug rückgängig machen
	    }
		
		
		//Verziehrung links
		Icon oIcon = new ImageIcon(getClass().getResource("ornament.png"));
		JLabel lOrnament = new JLabel(oIcon);
		lOrnament.setVisible(true);
		lOrnament.setLayout(null);
		lOrnament.setBounds(185, 0, 70, 700);

		lfeldLBL.add(player1LBL);
		lfeldLBL.add(player1HandkartenLBL);
		lfeldLBL.add(linksCancelBTN);
		lfeldLBL.add(linksPassBTN);
		lfeldLBL.add(lLabel);
		lfeldLBL.add(lOrnament);

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
		
		for (int c = 0; c < m; c++) {
			for (int d = 0; d < n; d++) {
				
				staerkeKarteLBL[c][d] = new JLabel();
				staerkeKarteLBL[c][d].setLayout(null);
				staerkeKarteLBL[c][d].setForeground(new Color(50,0,0));
				staerkeKarteLBL[c][d].setBounds(90,90,14,14);
				staerkeKarteLBL[c][d].setText("");
				
				ruestungKarteLBL[c][d] = new JLabel();
				ruestungKarteLBL[c][d].setLayout(null);
				ruestungKarteLBL[c][d].setForeground(new Color(0,50,0));
				ruestungKarteLBL[c][d].setBounds(24,90,14,14);
				ruestungKarteLBL[c][d].setText("");

				feldButtons[c][d] = new JButton();
				feldButtons[c][d].setLayout(null);
				feldButtons[c][d].setOpaque(false);
				feldButtons[c][d].setContentAreaFilled(false);
				feldButtons[c][d].add(ruestungKarteLBL[c][d]);
				feldButtons[c][d].add(staerkeKarteLBL[c][d]);
				final int zeile = c;
				final int spalte = d;
				feldButtons[c][d].addActionListener(new ActionListener() {
					//muss noch abgeändert werden, da bisher der attbttn(für angreifbare einheiten) genutzt wird(auch wenn freies feld)
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (links.getAuswahlPhase() == 0 || rechts.getAuswahlPhase() == 0) { 
							//Einheit ausw�hlen um Aktion durchzuf�hren
							rechts.setAktionAuswahlEinheit(spielbrett.getEinheit(zeile, spalte));
							links.setAktionAuswahlEinheit(spielbrett.getEinheit(zeile, spalte));
							optionenZeigenEinheit(spielbrett.getEinheit(zeile, spalte), spielbrett);
							links.setAuswahlPhase(1);
							rechts.setAuswahlPhase(1);
							links.setAktionAuswahlHand(false);
							rechts.setAktionAuswahlHand(false);
							
						} else { //Ziel ausw�hlen
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

		linksPassBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				links.setPassen(true);
			}
		});
		rechtsPassBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rechts.setPassen(true);
			}
		});
		
		linksCancelBTN.addActionListener(new ActionListener() {
			
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
		
		rechtsCancelBTN.addActionListener(new ActionListener() {
			
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
		rfeldLBL = new JLabel(rIcon);
		rfeldLBL.setLayout(null);
		rfeldLBL.setForeground(Color.WHITE);
		rfeldLBL.setBounds(950, 0, 250, 730);

		//angezeigter Text Spieler und Handkarten
		player2LBL.setBounds(25,20,200,40);
		player2LBL.setHorizontalAlignment(SwingConstants.CENTER);
		player2LBL.setForeground(rfeldLBL.getForeground());
		player2LBL.setFont(new Font(player1LBL.getText(), Font.ITALIC, 16));
		player2HandkartenLBL.setBounds(100,180,90,40);
		player2HandkartenLBL.setForeground(rfeldLBL.getForeground());
		
		//Abbruchbutton rechts
		rechtsCancelBTN.setLayout(null);
		rechtsCancelBTN.setBounds(50, 80, 150, 40);
		rechtsCancelBTN.setOpaque(false);
		rechtsCancelBTN.setContentAreaFilled(false);
		rechtsCancelBTN.setText("Aktion abbrechen");
		rechtsCancelBTN.setForeground(rfeldLBL.getForeground());
		rechtsCancelBTN.setFont(new Font(linksCancelBTN.getText(), Font.PLAIN, 14));
		rechtsCancelBTN.setToolTipText("Eure Exzellenz: Wollt ihr eine andere Aktion wählen?");
	    
	    if (rechtsCancelBTN != null) {
	    	//zug rückgängig machen
	    }
	    
		//Passenbutton rechts

		rechtsPassBTN.setLayout(null);
		rechtsPassBTN.setBounds(50, 130, 150, 40);
		rechtsPassBTN.setOpaque(false);
		rechtsPassBTN.setContentAreaFilled(false);;
		rechtsPassBTN.setText("Passen");
		rechtsPassBTN.setForeground(rfeldLBL.getForeground());
		rechtsPassBTN.setFont(new Font(linksCancelBTN.getText(), Font.PLAIN, 14));
		rechtsPassBTN.setToolTipText("Eure Exzellenz: Hiermit lasst ihr eine eurer Aktionen verstreichen.");
	    
	    if (rechtsPassBTN != null) {
	    	//zug rückgängig machen
	    }

		//Kartenfeld rechtes Feld
		JLabel rLabel = new JLabel();
		rLabel.setLayout(null);
		rLabel.setBounds(18,235,232,465);
		rLabel.setLayout(new GridLayout(4,2,4,4));//Einteilung Panel und Zwischenabständes
		
		//neu
		for (int i = 0; i < 8; i++) {  //8 als Handkartenlimit
			rechtsHandkartenBTNS[i]= new JButton();
			rechtsHandkartenBTNS[i].setOpaque(false);
			rechtsHandkartenBTNS[i].setContentAreaFilled(false);
			final int final_i=i;
		    rechtsHandkartenBTNS[i].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent arg0) {
		            optionenZeigenHandkarte(rechts.getHand().get(final_i), spielbrett);
		            rechts.setAktionsAuswahl0(final_i);
		            rechts.setAktionAuswahlHand(true);
					links.setAuswahlPhase(1);
					rechts.setAuswahlPhase(1);
		        }
		    });
			rLabel.add(rechtsHandkartenBTNS[i]);	
		}
				
		//Verziehrung rechts
		Icon oIcon1 = new ImageIcon(getClass().getResource("ornament1.png"));
		JLabel rOrnament = new JLabel(oIcon1);
		rOrnament.setLayout(null);
		rOrnament.setBounds(-5, 0, 70, 700);

		rfeldLBL.add(player2LBL);
		rfeldLBL.add(player2HandkartenLBL);
		rfeldLBL.add(rechtsCancelBTN);
		rfeldLBL.add(rechtsPassBTN);
		rfeldLBL.add(rLabel);
		rfeldLBL.add(rOrnament);
		
		//Sieger Bekanntgabe
		Icon sIcon = new ImageIcon(getClass().getResource("sieg.png"));
		siegLBL = new JLabel(sIcon);
		siegLBL.setLayout(null);
		siegLBL.setVisible(false);
		siegLBL.setBounds(275,150,650,400);
		siegLBL.setForeground(Color.WHITE);

		text5LBL.setBounds(125,100,400,60);
		text5LBL.setFont(new Font(text5LBL.getText(), Font.BOLD, 30));
		text5LBL.setForeground(siegLBL.getForeground());
		text5LBL.setHorizontalAlignment(SwingConstants.CENTER);
		text6LBL.setBounds(50,180,600,60);
		text6LBL.setFont(new Font(text6LBL.getText(), Font.BOLD, 30));
		text6LBL.setForeground(siegLBL.getForeground());
		text6LBL.setHorizontalAlignment(SwingConstants.CENTER);
		siegLBL.add(text5LBL);
		siegLBL.add(text6LBL);
		
		contentPNL.add(siegLBL);
		contentPNL.add(auswahlLBL);
		contentPNL.add(kaufOberflLBL);
		contentPNL.add(mitte);
		contentPNL.add(lfeldLBL);
		contentPNL.add(rfeldLBL);
	

	}

	//Namen waehlen
	public void setup1(Spieler links, Spieler rechts) {
		
		if (rechts.getClass() == KI.class) {
			text3LBL.setVisible(false);
			playerrechtsTA.setVisible(false);
			
		} else
			text4LBL.setVisible(false);

		text3LBL.setText("Gebt Eure Titel ein (max. 20 Zeichen):");
		text3LBL.setFont(text2LBL.getFont());
		text3LBL.setBounds(100,100,270,20);
		text3LBL.setForeground(auswahlLBL.getForeground());

		playerlinksTA.setFont(new Font("", Font.ITALIC, 18));
		playerlinksTA.setBounds(100,200,200,30);
		playerlinksTA.setForeground(Color.white);
		playerlinksTA.setOpaque(false);
		playerlinksTA.setBorder(BorderFactory.createLineBorder(Color.darkGray));

		playerrechtsTA.setFont(playerlinksTA.getFont());
		playerrechtsTA.setBounds(370,200,200,30);
		playerrechtsTA.setForeground(Color.black);
		playerrechtsTA.setOpaque(false);
		playerrechtsTA.setBorder(BorderFactory.createLineBorder(Color.lightGray));

		text4LBL.setText("Gebt Euren Titel ein (max. 20 Zeichen):");
		text4LBL.setFont(text2LBL.getFont());
		text4LBL.setBounds(100,100,270,40);
		text4LBL.setForeground(auswahlLBL.getForeground());

		startBTN.setOpaque(false);
		startBTN.setContentAreaFilled(false);
		startBTN.setBounds(320,300,160,60);
		startBTN.setForeground(auswahlLBL.getForeground());
		startBTN.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		// regelt die Laenge der Namen (beschr aenkt auf 20 Zeichen) 
		// panel bleibt solange sichtbar bis richtige laenge, dann unsichtbar
		startBTN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String lname = playerlinksTA.getText();
				String rname = playerrechtsTA.getText();

				if (rechts.getClass() != KI.class) {
					if (lname.length() != 0 && rname.length() != 0) {
						if (lname.length() < 21 && rname.length() < 21) {	//anfangen bei PvP	
							startBTN.isEnabled();
							auswahlLBL.setVisible(false);
							links.setName(lname);
							rechts.setName(rname);
							player2LBL.setText(rechts.getName());
							player1LBL.setText(links.getName());
							kaufOberflLBL.setVisible(true);
							links.kaufen(anzeige, rechts);
						} else {		//nicht anfangen
							startBTN.isEnabled();
							auswahlLBL.setVisible(true);
						}
					}
				} else {	//anfangen bei PvE // TODO PvE?!
					if (lname.length() != 0 && lname.length() < 21) {
						startBTN.isEnabled();
						auswahlLBL.setVisible(false);
						links.setName(lname);
						player1LBL.setText(links.getName());
						player2LBL.setText(rechts.getName());
						kaufOberflLBL.setVisible(true);
						links.kaufen(anzeige, rechts);
					} else {	//nicht anfangen
						startBTN.isEnabled();
						auswahlLBL.setVisible(true);
					}
				}
			}
		});

		auswahlLBL.add(text3LBL);
		auswahlLBL.add(text4LBL);
		auswahlLBL.add(playerlinksTA);
		auswahlLBL.add(playerrechtsTA);
		auswahlLBL.add(pvpBTN);
		auswahlLBL.add(pvkiBTN);
		auswahlLBL.add(startBTN);
	}
	
	public void siegerBekanntgabe(Spieler links, Spieler rechts) {
		if (!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) {
			text5LBL.setText(links.getName());
			text6LBL.setText("hat gewonnen!!!");
		} else {
			if (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty()) {
				text5LBL.setText(rechts.getName());
				text6LBL.setText("hat gewonnen!!!");
			} else {
				text6LBL.setText("Die Partie endet unentschieden.");
			}
		}	
		siegLBL.setVisible(true);
	}
	
	//Hilfsfunktion, die alle Buttons disabled
	public void optionenKeine() {
		for (int i = 0; i < rechtsHandkartenBTNS.length; i++) {
			rechtsHandkartenBTNS[i].setEnabled(false);
			linksHandkartenBTNS[i].setEnabled(false);
		}
		for (int i = 0; i < feldButtons.length; i++)
			for (int j = 0; j < feldButtons[0].length; j++)
				feldButtons[i][j].setEnabled(false);
		
		linksCancelBTN.setEnabled(false);
		rechtsCancelBTN.setEnabled(false);
		linksPassBTN.setEnabled(false);
		rechtsPassBTN.setEnabled(false);
	}
	
	// Zeigt fuer eine Handkarte an, was man damit machen kann
	public void optionenZeigenHandkarte(Karte auszuspielende, Feld spielbrett) {
		optionenKeine();
		if (auszuspielende.getBesitzer().getSeite() == "links")
			linksCancelBTN.setEnabled(true);
		else
			rechtsCancelBTN.setEnabled(true);
		
		if (auszuspielende.getArt() == "einheit") {
			if (auszuspielende.getBesitzer().getSeite() == "links") {
				for (int i = 0; i < 6; i++)
					for (int j = 0; j < 2; j++)
						if (!spielbrett.besetzt(i, j))
							feldButtons[i][j].setEnabled(true);
			} else {	// rechter Spieler setzt auf die rechte Seite
				for (int i = 0; i < 6; i++)
					for (int j = 4; j < 6; j++)
						feldButtons[i][j].setEnabled(true);
			}
		} else {	// Effektkartenziel waehlen
			if (auszuspielende.getArt()=="fluch") {		//flueche wirft man auf gegner
				for (int i = 0; i < 6; i++)
					for (int j = 0; j < 6; j++)
						if (spielbrett.besetzt(i, j))	// vermeiden einer nullpointerexception bei dem naechsten Test
							if (spielbrett.getEinheit(i, j).getBesitzer() != auszuspielende.getBesitzer())
								feldButtons[i][j].setEnabled(true);
			} else {	// da es kein Fluch ist muss es ein Segen sein.
				for (int i = 0; i < 6; i++)
					for (int j = 0; j < 6; j++)
						if (spielbrett.besetzt(i, j))
							if (spielbrett.getEinheit(i, j).getBesitzer() == auszuspielende.getBesitzer())	// dieses mal muss es eine eigene Einheit sein
								feldButtons[i][j].setEnabled(true);
			}
		}
	}
	
	// zeigt an, was man mit einer ausgespielten Einheit f�r Optionen hat.
	public void optionenZeigenEinheit(Einheit aktive, Feld spielbrett) {
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
		if (aktive.getBesitzer().getSeite() == "links")
			linksCancelBTN.setEnabled(true);
		else
			rechtsCancelBTN.setEnabled(true);
	}
	
	// zeigt an, was ein Spieler in seinem Zug tun kann
	public void optionenZeigenSpieler(Spieler aktiverSpieler) {
		optionenKeine();
		if (aktiverSpieler.getSeite()=="links") {
			linksPassBTN.setEnabled(true);
			for (int i = 0; i < linksHandkartenBTNS.length; i++)
				linksHandkartenBTNS[i].setEnabled(true);
		} else {
			rechtsPassBTN.setEnabled(true);
			for (int i = 0; i < rechtsHandkartenBTNS.length; i++)
				rechtsHandkartenBTNS[i].setEnabled(true);
		}
		for (int i = 0; i < aktiverSpieler.getTruppen().size(); i++) {
			if (aktiverSpieler.getTruppen().get(i).getBereit() > 0) {
				int koordinaten[] = aktiverSpieler.getTruppen().get(i).getPosition();
				feldButtons[koordinaten[0]][koordinaten[1]].setEnabled(true);
			}
		}
	}

	// TODO was is das von setPlayer (komisches if-statement) bis inkl setup0?!
	// oben genutzte Methode, die auf Buttoneingabe reagiert.
	public void setPlayer(Spieler rechts, boolean human) {
		if (human);
		else rechts = new KI();
	}

	// was ist das?
	public void setup0(Spieler rechts) {} // PvP (true) oder PvE (false)
		//auswahl.setToolTipText(text); // wenn Cursor darueber liegt erscheinender Text

	public void kaufen(Spieler kaufender, Spieler anderer) {
		
		Karte[] angebot = new Karte[3];
		int minPreis = 50;
		int maxHand = 8;
		for (int i = 0; i <= 2; i++) {
			angebot[i] = kaufender.generateEinheit();
			while (kaufender.getGold() < angebot[i].getPreis() && kaufender.getGold() >= minPreis) {
				angebot[i] = kaufender.generateEinheit(); System.out.println(i + "Nicht genug Gold. Generiere neue Einheit."); // Nur zum Testen.
			}
			kaufBTNS[i].setIcon(new ImageIcon(((new ImageIcon(angebot[i].getBildPfad())).getImage()).getScaledInstance(160, 160, java.awt.Image.SCALE_SMOOTH)));
			preisLBL[i].setText(Integer.toString(angebot[i].getPreis()));
			kaufBTNS[i].setToolTipText("<html><img src=\"" + Main.class.getResource(angebot[i].getTooltipPfad()));
			
			final int innerI = i;
			
			ActionListener l = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					kaufender.getHand().add(angebot[innerI]);
					kaufender.setGold(kaufender.getGold()-angebot[innerI].getPreis());
					if (kaufender.getSeite()=="links")
						aktualisierenHand(kaufender, anderer);
					else
						aktualisierenHand(anderer, kaufender);
					
					contentPNL.repaint();
					
					if (kaufender.getHand().size() < maxHand && kaufender.getGold() >= minPreis)	// weiter einkaufen
						kaufen(kaufender, anderer); 
					else {
						if (anderer.getHand().size() == 0)		// der andere kauft
							anderer.kaufen(anzeige, kaufender);
						else	//aufraeumen
							kaufOberflLBL.setVisible(false);
					}
					//Kontrollausgabe
					System.out.println(kaufender.getName() + " hat "+ angebot[innerI].getName() + " gekauft. Jetziges Gold: " + kaufender.getGold() + " / Handkartenanzahl: " + kaufender.getHand().size());
				}
			};
			
			for (ActionListener temp : kaufBTNS[i].getActionListeners())
				kaufBTNS[i].removeActionListener(temp);
			kaufBTNS[innerI].addActionListener(l);
		}
		
		goldLBL.setText(kaufender.getName() + ", Sie haben " + kaufender.getGold() + " Gulden.");
		
		System.out.println(kaufender.getName() + " hat nun folgende Karten:");
		kaufender.printHand();
	}
	
	
	public void kaufenVerstecken() {
		kaufOberflLBL.setVisible(false);
	}
}