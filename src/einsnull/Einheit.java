package einsnull;

import java.util.ArrayList;
import java.util.Scanner;


public class Einheit extends Karte {
	protected int staerke;
	protected int ruestung;
	protected ArrayList<int[]>  bewegung= new ArrayList<int[]> ();
	protected ArrayList<int[]>  angriff = new ArrayList< int[]>();
	protected String name;
	protected int[] position = {-1, -1};

static Scanner input = new Scanner(System.in);

	//Diese Funktion zeigt einem auf, wie man eine Karte(hier Einheit) nutzen kann und ruft dann die entsprechenden Funktionen auf (siehe unten)
	public boolean nutzen(Feld spielbrett) {
		if(position[0]==-1) { //prüft ob die Karte noch auf der Hand ist.
			System.out.println("Diese Karte können Sie ausspielen. Geben Sie Zeile und Spalte eines freien Feldes in Ihrem Spielbereich an, auf welches Sie die Karte spielen wollen.");
			System.out.println("Geben Sie die Zeile an, oder -1 um abzubrechen");
			int x, y;
			x= input.nextInt();
			if(x == -1) return false;
			System.out.println("Geben Sie die Spalte an");
			y= input.nextInt();
			this.ausspielen(spielbrett, x, y); //keine Korrektur brauch korrekte x, y
			return true;
		}else {
			System.out.println("Diese Karte können Sie angreifen lassen oder bewegen.");
			System.out.println("Mögliche Bewegungen:");
			for (int i = 0; i < bewegung.size(); i++) {
				//check fuer Rand Feldgröße 6x6
				if(position[0]+bewegung.get(i)[0] < 6 && position[0]+bewegung.get(i)[0] >= 0 && position[1]+bewegung.get(i)[1] < 6 && position[1]+bewegung.get(i)[1] >= 0
						//es darf keine Einheit auf dem Feld sein
						&& !spielbrett.besetzt(position[0]+bewegung.get(i)[0], position[1]+bewegung.get(i)[1]) 
						) {
					System.out.println((position[0]+bewegung.get(i)[0]) + ", " + (position[1]+bewegung.get(i)[1]));
				}
			}
			System.out.println("Mögliche Angriffe:");
			for (int i = 0; i < angriff.size(); i++) {
				//check fuer Rand Feldgröße 6x6
				if(position[0]+angriff.get(i)[0] < 6 && position[0]+angriff.get(i)[0] >= 0 && position[1]+angriff.get(i)[1] < 6 && position[1]+angriff.get(i)[1] >= 0
						//es muss eine Einheit auf dem Feld sein
						&& spielbrett.besetzt(position[0]+angriff.get(i)[0], position[1]+angriff.get(i)[1]) 
						) {
					System.out.println((position[0]+bewegung.get(i)[0]) + ", " + (position[1]+bewegung.get(i)[1]));
				}
			}
			System.out.println("Bitte geben Sie die Zeile und Spalte des Feldes an, auf das Sie bewegen oder angreifen lassen wollen.");
			System.out.println("Geben Sie die Zeile an. Geben Sie -1 an, wenn Sie abbrechen wollen.");
			int x, y;
			x= input.nextInt();
			if(x == -1) return false;
			System.out.println("Geben Sie die Spalte an");
			y= input.nextInt();
			if(spielbrett.besetzt(x, y)) this.angreifen(spielbrett, x, y); //benötigt korrekte x, y
			else this.bewegen(spielbrett, x, y);
			return true;
		}
	}	
	
	//die drei Funktionen, die nutzen aufruft
	public void ausspielen(Feld spielbrett, int zeile, int spalte){
		spielbrett.getInhalt(zeile, spalte).add(this);
		besitzer.getHand().remove(this);
		besitzer.getTruppen().add(this);
		position[0]= zeile;
		position[1]= spalte;
	}
	
	public void bewegen(Feld spielbrett, int zeile, int spalte) {
		spielbrett.getInhalt(zeile, spalte).add(this);
		spielbrett.getInhalt(position[0], position[1]).remove(this);
		position[0]= zeile;
		position[1]= spalte;
	}
	
	//Wenn wir Einheiteneffekte haben wollen, wenn Einheiten angegriffen werden müssen wir das vielleicht noch anders machen.
	public void angreifen(Feld spielbrett, int zeile, int spalte) {
		spielbrett.getInhalt(zeile, spalte).get(0).setStaerke( 
				+spielbrett.getInhalt(zeile, spalte).get(0).getStaerke()
				-staerke
				+spielbrett.getInhalt(zeile, spalte).get(0).getRuestung()
		);
	}

	
	//Setters und Getters
	
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
