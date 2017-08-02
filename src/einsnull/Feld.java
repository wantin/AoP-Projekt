package einsnull;

import java.util.ArrayList;

/*
 * Die Klasse Feld ist eine Art Schachbrett auf dem die
 * Figuren bzw. Einheiten sich befinden. Durch bestimmte
 * Befehle k�nnen diese sich sp�ter darauf bewegen und 
 * andere sich auf dem Feld befindliche Einheiten angreifen. 
 */

public class Feld {
	
	/*
	 * Ein zweidimensionales Array, in dem die Besetzung gespeichert wird
	 * Die Besetzung wird in einem dritten Array gespeichert, in dem üblicherweise 0 oder 1 Element/Einheiten sind
	 * Umstellungen auf Listen, weil Arrays und Listen zusammen nicht wollen und weil die innerste keine feste Größe hat.
	 * Falls das davor schon prima war war das wohl unnötiger Aufwand.. aber mal schauen. ich hoffe es passt so.
	 */
	private  ArrayList<ArrayList<ArrayList<Einheit>>> inhalt;// = new ArrayList<ArrayList<ArrayList<Einheit>>>(6);
	
	
	/*
	 * Konstruktor (bei Bedarf nur ein Parameter, da quadratisches Feld?) (Dong)
	 * würde ich uns offen lassen vielleicht ist es besser wenn es breiter oder tiefer ist. (Valentin)
	 * @param zeile - Anzahl der Zeilen
	 * @param spalte - Anzahl der Spalten
	 */
	public Feld(int zeile, int spalte) {
		System.out.println("ausgeführt");
		inhalt = new ArrayList<ArrayList<ArrayList<Einheit>>>(zeile);
		for (int i = 0; i < zeile; i++) {
			System.out.println("innen");
			inhalt.add(new ArrayList<ArrayList<Einheit>>(spalte));
			for (int j = 0; j < spalte; j++) {
				inhalt.get(i).add(new ArrayList<Einheit>(1));
			}
		}
	}
	
	//zur Kontrollausgabe
	public void print () {
		for (int i = 0; i < inhalt.size(); i++) {
			for (int j = 0; j < inhalt.get(0).size(); j++) {
				if(getInhalt(i, j).size()<2) {
					if(besetzt(i, j)) {
						System.out.print(getEinheit(i, j).getName() + "\t");
					}else {
						System.out.print("leer \t");
					}
				}else {
					System.out.print("viele \t"); //sollte nach derzeitiger Planung nicht vorkommen
				}
			}
			System.out.println("");
		}
	}
	
	//Ich glaube die beiden braucht es nicht wirklich weil man über getInhalt dann auch einfach außerhalb .size() benutzen kann
	/*Ermittelt die Anzahl der Zeilen */
	public int getAnzahlZeilen() {
		return inhalt.size();
	}
	
	/*Ermittelt die Anzahl der Spalten */
	public int getAnzahlSpalten() {
		return inhalt.get(0).size();
	}
	
	/*
	 * Ermittelt den Inhalt eines Feldes bei den bestimmten Koordinaten
	 * @param zeile - Zeilennummer ab Index 0
	 * @param spalte - Spaltennummer ab Index 0
	 * @return - Inhalt des Feldes, wenn leer return null
	 */
	public ArrayList<Einheit> getInhalt (int zeile, int spalte) {
		return inhalt.get(zeile).get(spalte);
	}
	
	public Einheit getEinheit (int zeile, int spalte) {
		return inhalt.get(zeile).get(spalte).get(0);
	}
	
	public boolean besetzt (int zeile, int spalte) {
		if (inhalt.get(zeile).get(spalte).isEmpty()) return false;
			else return true;
	}

}
