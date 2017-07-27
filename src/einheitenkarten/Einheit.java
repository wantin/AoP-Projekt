package einheitenkarten;

import einsnull.Karte;

public class Einheit extends Karte {
	int staerke;
	int[] bewegung;
	String name;
	
	public int getStaerke() {
		return staerke;
	}
	
	public String getName() {
		return name;
	}
	
	/*
	 * Ist es vielleicht besser Einheit.java als Interface zu nehmen
	 * und die beiden nachfolgenden Methoden einzeln für die jeweiligen
	 * Einheitenkarten zu definieren? Heißt getMoveable() wie eine Art
	 * Bauer zu definieren, während getAttackable() alle in einem Umkreis
	 * von 2 Feldern angreifbar macht. Ich hoffe das war verständlich genug.
	 * 
	 * Außerdem brauchen wir glaube ich noch eine weitere Klasse um zu sagen
	 * wem die Einheiten gehören. Also ob die Einheit wie beim Schach schwarz
	 * oder weiß ist. 
	 */
	
	/* getMoveable() 
	 * Methode die ermittelt wohin sich eine Figur bewegen kann 
	 */
	
	/* getAttackable()
	 * Methode die ermittelt welche Figur angreifbar ist
	 */
}
