package einsnull;

public class Einheit extends Karte {
	private int staerke;
	private int[] bewegung;
	private String name;
	
	public void ausspielen(Spieler ausspielender) {
		//erfragen des ortes vom Spieler
	}
	
	public int[] getBewegung() {
		return bewegung;
	}

	public void setBewegung(int[] bewegung) {
		this.bewegung = bewegung;
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
