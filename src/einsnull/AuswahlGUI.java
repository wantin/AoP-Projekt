package einsnull;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AuswahlGUI extends JFrame {

	static Scanner inputScanner = new Scanner(System.in);

	private JPanel contentPN;
	private JLabel auswahlLBL;

	private JButton pvpBTN = new JButton("Spieler vs. Spieler");
	private JButton pvkiBTN = new JButton("Spieler vs. KI");

	private JLabel text1LBL = new JLabel();
	private JLabel text2LBL = new JLabel();
	
	//Konstruktor
	public AuswahlGUI(Spieler links){

		AuswahlGUI anzeige = this; //benoetigt fuer actionlistener
		
		//Spielart und Namen auswahl
		Icon aIcon = new ImageIcon(getClass().getResource("feld.png"));
		auswahlLBL = new JLabel(aIcon);
		auswahlLBL.setLayout(null);
		auswahlLBL.setForeground(Color.white);
		auswahlLBL.setBounds(0,0,650,400);
		contentPN = (JPanel) this.getContentPane();
		contentPN.setLayout(null);
		this.setBounds(275,150,650,400);
		contentPN.setVisible(true);
		auswahlLBL.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		
		//auswahl.setVisible(false); //falls man den Anfang ueberspringen will
		
		auswahlLBL.add(text1LBL);
		auswahlLBL.add(text2LBL);
		auswahlLBL.add(pvpBTN);
		auswahlLBL.add(pvkiBTN);

		text1LBL.setText("Wir hei\u00dfen eure Majest\u00e4ten willkommen zu Eldotin.");
		text1LBL.setFont(new Font(text1LBL.getText(), Font.ITALIC, 20));
		text1LBL.setForeground(auswahlLBL.getForeground());
		text1LBL.setBounds(0, 40, 650, 30);
		text1LBL.setHorizontalAlignment(SwingConstants.CENTER);

		text2LBL.setText("Wollt Ihr gegen die KI spielen oder zu zweit an einem Computer?");
		text2LBL.setFont(new Font(text2LBL.getText(), Font.PLAIN, 16));
		text2LBL.setForeground(auswahlLBL.getForeground());
		text2LBL.setBounds(0, 110, 650, 30);
		text2LBL.setHorizontalAlignment(SwingConstants.CENTER);

		pvpBTN.setVisible(true);
		pvpBTN.setLayout(null);
		pvpBTN.setOpaque(false);
		pvpBTN.setContentAreaFilled(false);
		pvpBTN.setForeground(auswahlLBL.getForeground());
		pvpBTN.setBounds(100,200,200,50);
		pvpBTN.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				links.setAktionsAuswahl0(2);
				anzeige.setVisible(false);
			}
		});

		//Spieler auf linker Seite deshalb bttn true
		pvkiBTN.setVisible(true);
		pvkiBTN.setLayout(null);
		pvkiBTN.setOpaque(false);
		pvkiBTN.setContentAreaFilled(false);
		pvkiBTN.setForeground(auswahlLBL.getForeground());
		pvkiBTN.setBounds(350, 200, 200, 50);
		pvkiBTN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (pvkiBTN != null) {
					links.setAktionsAuswahl0(1);
					anzeige.setVisible(false);
				}
			}
		});
		contentPN.add(auswahlLBL);
	}
}