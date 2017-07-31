package einsnull;

import einheitenkarten.Einheit;

public class Spieler {
	String name;
	Karte[] hand;
	Einheit[] truppen;
	int gold;
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	//ich muss erstmal wieder ins Programmieren reinkommen, keine Ahnung ob die Methode hier Sinn macht (Dong)
	//umbenannt zu print. falls wir die brauchen haben wir die dann ja
	public void printHand() {
		for (int i = 0; i <= hand.length; i++) {
			System.out.println(hand[i]);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Karte[] getHand() {
		return hand;
	}

	public void setHand(Karte[] hand) {
		this.hand = hand;
	}

	public Einheit[] getTruppen() {
		return truppen;
	}

	public void setTruppen(Einheit[] truppen) {
		this.truppen = truppen;
	}
}
