package effektkarten;

import java.util.Random;

import einsnull.Effekt;
import einsnull.Feld;
import einsnull.Spieler;

public class Saeuregranate extends Effekt {
	
	public Saeuregranate(Spieler besitzer) {
		this.besitzer = besitzer;
		name = "Säuregranate";
		preis = 100;
		bildPfad = "bilder/effekte/sauregranate";
	}
	
	@Override
	 public void ausspielen(int zeile, int spalte, Feld spielbrett) {
		besitzer.getHand().remove(this);
		Random rnjesus = new Random();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				spielbrett.getEinheit(zeile+i, spalte+j).setRuestung(
					Math.max(0, //Rüstung nie unter 0
							spielbrett.getEinheit(zeile+i, spalte+j).getRuestung()
							-rnjesus.nextInt(6)
					)
				);
			}
		}
	}

}
