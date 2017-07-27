package einsnull;

import einheitenkarten.Einheit;

public class Spieler {
	Karte[] hand;
	Einheit[] truppen;
	int gold;
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	//ich muss erstmal wieder ins Programmieren reinkommen, keine Ahnung ob die Methode hier Sinn macht
	public void getHand() {
		for (int i = 0; i <= hand.length; i++) {
			System.out.println(hand[i]);
		}
	}
}
