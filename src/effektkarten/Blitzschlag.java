package effektkarten;

import java.util.Random;

import einsnull.Effekt;
import einsnull.Feld;
import einsnull.Spieler;

public class Blitzschlag extends Effekt {
	
	public Blitzschlag(Spieler besitzer) {
		this.besitzer = besitzer;
		name = "Blitzschlag";
		preis = 150;
		bildPfad = "blitz.jpg";
		art = "fluch";
		tooltipPfad = "Blitzsturm_anzeige.jpg";
	}

	@Override
	 public void ausspielen(int zeile, int spalte, Feld spielbrett) {
		besitzer.getHand().remove(this);
		Random rnjesus = new Random();
		//Einheit nimmt 4 absoluten Schaden
		spielbrett.getEinheit(zeile, spalte).setStaerke(spielbrett.getEinheit(zeile, spalte).getStaerke()-4);
		if(spielbrett.getEinheit(zeile, spalte).getStaerke() < 1) {
			spielbrett.getEinheit(zeile, spalte).getBesitzer().getTruppen().remove(spielbrett.getEinheit(zeile, spalte));
			spielbrett.getInhalt(zeile, spalte).remove(spielbrett.getEinheit(zeile, spalte));
		}
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if(zeile+i>-1 && zeile+i<6 && spalte+j>-1 && spalte+j<6){
					if(i!=0 || j!=0) {
						if(spielbrett.besetzt(zeile+i, spalte+j)){
							if(rnjesus.nextInt(3)==0) {
								//springt weiter mit 1/3 Wahrscheinlichkeit
								ausspielen(zeile+i, spalte+j, spielbrett);
								//bisher kann der Blitz wieder zurückspringen. wenn wir das nicht wollen müssen wir das ändern.
							}
						}
					}
				}
			}
		}
	}
}
