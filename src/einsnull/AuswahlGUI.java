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

public class AuswahlGUI extends JFrame{

	static Scanner input = new Scanner(System.in);

	private static final long serialVersionUID = 1L;
	private JPanel content;
	private JLabel auswahl;

	private JButton ppbttn = new JButton("Player vs. Player");
	private JButton pkbttn = new JButton("Player vs. KI");

	private JLabel text1 = new JLabel();
	private JLabel text2 = new JLabel();
	
	//Konstruktor
	public AuswahlGUI(Spieler links){

		AuswahlGUI diese = this; //benötigt für actionlistener
		//Spielart und Namen auswahl
		Icon aIcon = new ImageIcon(getClass().getResource("feld.png"));
		auswahl = new JLabel(aIcon);
		auswahl.setLayout(null);
		auswahl.setForeground(Color.white);
		auswahl.setBounds(0,0,650,400);
		content = (JPanel) this.getContentPane();
		content.setLayout(null);
		this.setBounds(275,150,650,400);
		content.setVisible(true);
		auswahl.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//auswahl.setVisible(false); //falls man den Anfang ï¿½berspringen will
		
		auswahl.add(text1);
		auswahl.add(text2);
		auswahl.add(ppbttn);
		auswahl.add(pkbttn);

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
		ppbttn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				links.setAktionsAuswahl0(2);
				diese.setVisible(false);
			}
		});

		//Spieler auf linker Seite deshalb bttn true
		pkbttn.setVisible(true);
		pkbttn.setLayout(null);
		pkbttn.setOpaque(false);
		pkbttn.setContentAreaFilled(false);
		pkbttn.setForeground(auswahl.getForeground());
		pkbttn.setBounds(350,200,200,50);
		pkbttn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(pkbttn != null){
					links.setAktionsAuswahl0(1);
					diese.setVisible(false);
				}
			}
		});
		content.add(auswahl);
	}
}