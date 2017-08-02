package einsnull;

/*
 * Die Klasse Feld ist eine Art Schachbrett auf dem die
 * Figuren bzw. Einheiten sich befinden. Durch bestimmte
 * Befehle k�nnen diese sich sp�ter darauf bewegen und 
 * andere sich auf dem Feld befindliche Einheiten angreifen. 
 */

public class Feld {
	
	/*
	 * Hab ich erstmal so stehen lassen
	 * passt aber glaube ich nicht zu der gleichen vorstellung von umsetzung von Feld
	 * 
	private boolean belegt;
	private Einheit[] darauf;
	
	public boolean isBelegt() {
		return belegt;
	}

	public void setBelegt(boolean belegt) {
		this.belegt = belegt;
	}

	public Einheit[] getDarauf() {
		return darauf;
	}

	public void setDarauf(Einheit[] darauf) {
		this.darauf = darauf;
	}
*/
	
	public Einheit[][][] getInhalt() {
		return inhalt;
	}

	public void setInhalt(Einheit[][][] inhalt) {
		this.inhalt = inhalt;
	}


	
	/*
	 * Ein zweidimensionales Array, in dem die Besetzung gespeichert wird
	 */
	private Einheit[][][] inhalt;
	
	/*
	 * Konstruktor (bei Bedarf nur ein Parameter, da quadratisches Feld?) (Dong)
	 * würde ich uns offen lassen vielleicht ist es besser wenn es breiter oder tiefer ist. (Valentin)
	 * @param zeile - Anzahl der Zeilen
	 * @param spalte - Anzahl der Spalten
	 */
	public Feld(int zeile, int spalte) {
		inhalt = new Einheit[zeile][spalte][0];
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
	public Einheit[] getInhalt (int zeile, int spalte) {
		return inhalt[zeile][spalte];
	}
	
	public boolean besetzt (int zeile, int spalte) {
		if (inhalt[zeile][spalte].length == 0) return false;
			else return true;
	}

}
