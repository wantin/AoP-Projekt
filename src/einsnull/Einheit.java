package einsnull;

import java.util.ArrayList;
import java.util.Scanner;


public class Einheit extends Karte {
	protected int staerke;
	protected int ruestung;
	protected ArrayList<int[]>  bewegung= new ArrayList<int[]> ();
	protected ArrayList<int[]>  angriff = new ArrayList< int[]>();
	protected String name;
	protected int[] position = {};

static Scanner input = new Scanner(System.in);


	public void ausspielen() {
		//erfragen des ortes vom Spieler
		System.out.println("Sie können diese Einheit auf den Feldern (x,y) platzieren mit x aus [0, 1] und y aus [0, 5]");
		System.out.println("Bitte geben Sie x an");
		int auswahlX= input.nextInt();
		System.out.println("Bitte geben Sie y an");
		int auswahlY= input.nextInt();
		if (besitzer.getSeite() == "rechts") auswahlX= 5-auswahlX; //fuer Rechte Seite umstellen
		int[] pos= {auswahlX, auswahlY};
		this.setPosition(pos);
	}

	//man könnte vielleicht zwei funktionen machen. eine zum zeigen und eine zum bewegen
	public void bewegen(){
		//change from console to GUI
		System.out.println("Es ist möglich die Einheit auf folgende Felder zu bewegen:");
		for (int i = 0; i < bewegung.size(); i++) {
			//check fuer Rand Feldgröße 6x6
			if(position[0]+bewegung.get(i)[0] < 6 && position[0]+bewegung.get(i)[0] >= 0 && position[1]+bewegung.get(i)[1] < 6 && position[1]+bewegung.get(i)[1] >= 0
					//es darf keine Einheit auf dem Feld sein
					) {
				System.out.println(( i + ".: " + position[0]+bewegung.get(i)[0]) + ", " + (position[1]+bewegung.get(i)[1]));
			}
		}
		for (int i = 0; i < angriff.size(); i++) {
			//check fuer Rand Feldgröße 6x6
			if(position[0]+angriff.get(i)[0] < 6 && position[0]+angriff.get(i)[0] >= 0 && position[1]+angriff.get(i)[1] < 6 && position[1]+angriff.get(i)[1] >= 0
					//es muss eine Einheit auf dem Feld sein
					) {
				System.out.println(( i + ".: " + position[0]+bewegung.get(i)[0]) + ", " + (position[1]+bewegung.get(i)[1]));
			}
		}
		System.out.println("Bitte geben Sie ein welche der Optionen Sie wählen wollen.");
		int auswahl= input.nextInt();
		//bisher kein Fehlerabfangen
		position[0]+= bewegung.get(auswahl)[0];
		position[1]+= bewegung.get(auswahl)[1];
	}
	
	
	public ArrayList<int[]> getAngriff() {
		return angriff;
	}
	public void setAngriff(ArrayList<int[]> angriff) {
		this.angriff = angriff;
	}
	
	public Spieler getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(Spieler besitzer) {
		this.besitzer = besitzer;
	}
			
	public int getRuestung() {
		return ruestung;
	}

	public void setRuestung(int ruestung) {
		this.ruestung = ruestung;
	}

	public ArrayList<int[]> getBewegung() {
		return bewegung;
	}

	public void setBewegung(ArrayList<int[]> bewegung) {
		this.bewegung = bewegung;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public void setStaerke(int staerke) {
		this.staerke = staerke;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStaerke() {
		return staerke;
	}
	
	public String getName() {
		return name;
	}
	
	/*
	 * Ist es vielleicht besser Einheit.java als Interface zu nehmen
	 * und die beiden nachfolgenden Methoden einzeln f�r die jeweiligen
	 * Einheitenkarten zu definieren? Hei�t getMoveable() wie eine Art
	 * Bauer zu definieren, w�hrend getAttackable() alle in einem Umkreis
	 * von 2 Feldern angreifbar macht. Ich hoffe das war verst�ndlich genug.
	 * 
	 * Au�erdem brauchen wir glaube ich noch eine weitere Klasse um zu sagen
	 * wem die Einheiten geh�ren. Also ob die Einheit wie beim Schach schwarz
	 * oder wei� ist. 
	 */
	
	/* getMoveable() 
	 * Methode die ermittelt wohin sich eine Figur bewegen kann 
	 */
	
	/* getAttackable()
	 * Methode die ermittelt welche Figur angreifbar ist
	 */
}
