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
		bildPfad = "sauregranate.jpg";
		art = "fluch";
		tooltipPfad = "Saeuregranate_anzeige.jpg";
	}
	
	@Override
	 public void ausspielen(int zeile, int spalte, Feld spielbrett) {
		besitzer.getHand().remove(this);
		Random rnjesus = new Random();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if(zeile+i>-1 && zeile+i<6 && spalte+j>-1 && spalte+j<6){
					if(spielbrett.besetzt(zeile+i, spalte+j)){
						spielbrett.getEinheit(zeile+i, spalte+j).setRuestung(
							Math.max(0, //nie unter 0
									spielbrett.getEinheit(zeile+i, spalte+j).getRuestung()
									-rnjesus.nextInt(6) //reduziert um bis zu 6
							)
						);
					}
				}
			}
		}
	}

}
