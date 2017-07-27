package einsnull;

import einheitenkarten.Einheit;

/*
 * Die Klasse Feld ist eine Art Schachbrett auf dem die
 * Figuren bzw. Einheiten sich befinden. Durch bestimmte
 * Befehle können diese sich später darauf bewegen und 
 * andere sich auf dem Feld befindliche Einheiten angreifen. 
 */

public class Feld {
	
	/*
	 * Hab ich erstmal so stehen lassen
	 */
	boolean belegt;
	Einheit[] darauf;
	
	/*
	 * Ein zweidimensionales Array, in dem die Besetzung gespeichert wird
	 */
	protected Einheit[][] inhalt;
	
	/*
	 * Konstruktor (bei Bedarf nur ein Parameter, da quadratisches Feld?)
	 * @param zeile - Anzahl der Zeilen
	 * @param spalte - Anzahl der Spalten
	 */
	public Feld(int zeile, int spalte) {
		inhalt = new Einheit[zeile][spalte];
	}
	
	/*Ermittelt die Anzahl der Zeilen */
	public int getAnzahlZeilen() {
		return inhalt[0].length;
	}
	
	/*Ermittelt die Anzahl der Spalten */
	public int getAnzahlSpalten() {
		return inhalt.length;
	}
	
	/*
	 * Ermittelt den Inhalt eines Feldes bei den bestimmten Koordinaten
	 * @param zeile - Zeilennummer ab Index 0
	 * @param spalte - Spaltennummer ab Index 0
	 * @return - Inhalt des Feldes, wenn leer return null
	 */
	public Einheit getInhalt (int zeile, int spalte) {
		return inhalt[zeile][spalte];
	}

}
