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
	protected int preis;
	int bereit;
	protected String bildPfad;
	protected String art = "einheit";

static Scanner input = new Scanner(System.in);

	//Diese Funktion zeigt einem auf, wie man eine Karte(hier Einheit) nutzen kann und ruft dann die entsprechenden Funktionen auf (siehe unten)
	public boolean nutzen(Feld spielbrett) {
		
		if(position[0]==-1) { //pr√ºft ob die Karte noch auf der Hand ist.
			this.ausspielen(spielbrett, besitzer.getAktionsAuswahlZeile(), besitzer.getAktionsAuswahlSpalte()); //keine Korrektur brauch korrekte Zeile und Spalte
			return true;
		}else {
			if(bereit==0) { //das sollte vorher schon abgefangen werden, wird es derzeit noch nicht 
				return false;
			}
			if(spielbrett.besetzt(besitzer.getAktionsAuswahlZeile(), besitzer.getAktionsAuswahlSpalte())){ //braucht korrekte zeile und spalte
				this.angreifen(spielbrett, besitzer.getAktionsAuswahlZeile(), besitzer.getAktionsAuswahlSpalte());
			}
			else this.bewegen(spielbrett, besitzer.getAktionsAuswahlZeile(), besitzer.getAktionsAuswahlSpalte());
			return true;
		}
	}
	
	public ArrayList<int[]> zeigeAngriff(Feld spielbrett){
		ArrayList<int[]> ausgabe = new ArrayList<int[]> ();
		for (int i = 0; i < angriff.size(); i++) {
			//check fuer Rand Feldgrˆ√üe flexibel
			if(position[0]+angriff.get(i)[0] < spielbrett.getAnzahlZeilen() && position[0]+angriff.get(i)[0] >= 0 && position[1]+angriff.get(i)[1] < spielbrett.getAnzahlSpalten() && position[1]+angriff.get(i)[1] >= 0
					//es muss eine Einheit auf dem Feld sein
					&& spielbrett.besetzt(position[0]+angriff.get(i)[0], position[1]+angriff.get(i)[1])
			) {
				//die Einheit darf nicht eine eigene sein
				//dieser Test ist innen, damit getEinheit nicht bei einem leeren Feld aufgerufen wird
				if(spielbrett.getEinheit(position[0]+angriff.get(i)[0], position[1]+angriff.get(i)[1]).getBesitzer() != this.besitzer){
					int[] temp = {position[0]+angriff.get(i)[0], position[1]+angriff.get(i)[1]};
					ausgabe.add(temp);
				}
			}
		}
		return ausgabe;
	}
	
	public ArrayList<int[]> zeigeBewegung(Feld spielbrett){
		ArrayList<int[]> ausgabe = new ArrayList<int[]> ();
		for (int i = 0; i < bewegung.size(); i++) {
			//check fuer Rand Feldgr√∂√üe flexibel
			if(position[0]+bewegung.get(i)[0] < spielbrett.getAnzahlZeilen() && position[0]+bewegung.get(i)[0] >= 0 && position[1]+bewegung.get(i)[1] < spielbrett.getAnzahlSpalten() && position[1]+bewegung.get(i)[1] >= 0
					//es darf keine Einheit auf dem Feld sein
					&& !spielbrett.besetzt(position[0]+bewegung.get(i)[0], position[1]+bewegung.get(i)[1]) 
					) {
				int[] temp = {position[0]+bewegung.get(i)[0], position[1]+bewegung.get(i)[1]};
				ausgabe.add(temp);
			}
		}
		return ausgabe;
	}
	
	//die drei Funktionen, die nutzen aufruft
	public void ausspielen(Feld spielbrett, int zeile, int spalte){
		spielbrett.getInhalt(zeile, spalte).add(this);
		besitzer.getHand().remove(this);
		besitzer.getTruppen().add(this);
		position[0]= zeile;
		position[1]= spalte;
		bereit=0;
	}
	
	public void bewegen(Feld spielbrett, int zeile, int spalte) {
		spielbrett.getInhalt(zeile, spalte).add(this);
		spielbrett.getInhalt(position[0], position[1]).remove(this);
		position[0]= zeile;
		position[1]= spalte;
		bereit--;
	}
	
	//zweigeteiltes Angreifen sollte sp‰tere Effekte einfacher machen und sieht sch√∂ner aus
	// angegriffene Einheit: spielbrett.getInhalt(zeile, spalte).get(0)
	public void angreifen(Feld spielbrett, int zeile, int spalte) {
		spielbrett.getInhalt(zeile, spalte).get(0).verteidigen(spielbrett, this);
		bereit--;
	}
	
	public void verteidigen(Feld spielbrett, Einheit angreifer) { //wird von der verteidigenden Einheit aus aufgerufen
		if(angreifer.getStaerke()-ruestung < 1) { //check auf Schadenshˆhe
			staerke--; //Mindestschaden
		}else { //regul‰rer Schaden
			staerke-= angreifer.getStaerke()-ruestung;
		}
		if(staerke<1) {
			besitzer.getTruppen().remove(this);
			spielbrett.getInhalt(position[0], position[1]).remove(this);
		}
	}
	
	//Kontrollausgaben; genutzt in KI
	public void printBewegungen(Feld spielbrett) {
		ArrayList<int[]> moeglicheBewegungen = this.zeigeBewegung(spielbrett);
		for (int i = 0; i < moeglicheBewegungen.size(); i++) {
			System.out.print("(" + moeglicheBewegungen.get(i)[0] + "/" + moeglicheBewegungen.get(i)[1] + ") ");
		}
	}
	
	public void printAngriffe(Feld spielbrett) {
		ArrayList<int[]> moeglicheAngriffe = this.zeigeAngriff(spielbrett);
		for (int i = 0; i < moeglicheAngriffe.size(); i++) {
			System.out.print("(" + moeglicheAngriffe.get(i)[0] + "/" + moeglicheAngriffe.get(i)[1] + ") ");
		}
	}
	
	//Setters und Getters
	public String getBildPfad(){
		return bildPfad;
	}
	
	public String getArt() {
		return art;
	}

	public int getBereit() {
		return bereit;
	}

	public void setBereit(int bereit) {
		this.bereit = bereit;
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
	
	public int getStaerke() {
		return staerke;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setPreis(int preis){
		this.preis = preis;
	}
	
	public int getPreis() {
		return preis;
	}

	@Override
	String getTooltipPfad() {
		return tooltipPfad;
	}
}
