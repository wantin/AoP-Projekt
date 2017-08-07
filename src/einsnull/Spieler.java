package einsnull;

import java.util.ArrayList;

public class Spieler {
	
	private String name;
	private ArrayList<Karte> hand = new ArrayList<Karte>();
	private ArrayList<Einheit> truppen = new ArrayList<Einheit>();
	private int gold = 1000;
	private String seite;
	
	public void resetBereit() {
		for (int i = 0; i < truppen.size(); i++) {
			truppen.get(i).setBereit(1);
		}
	}
	
	//Kontrollausgabemethoden
	
	public void printTruppen() {
		for (int i = 0; i < truppen.size(); i++) {
			System.out.println(truppen.get(i).getName());
		}
	}
	
	public void printHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i).getName());
		}
	}
	
	//Setter und Getter
	
	public String getSeite() {
		return seite;
	}

	public void setSeite(String seite) {
		this.seite = seite;
	}

	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Karte> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Karte> hand) {
		this.hand = hand;
	}

	public ArrayList<Einheit> getTruppen() {
		return truppen;
	}

	public void setTruppen(ArrayList<Einheit> truppen) {
		this.truppen = truppen;
	}

}
