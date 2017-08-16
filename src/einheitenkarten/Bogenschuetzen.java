package einheitenkarten;

import einsnull.Einheit;
import einsnull.Spieler;

public class Bogenschuetzen extends Einheit {

	public Bogenschuetzen(Spieler besitzer) {
		this.besitzer= besitzer;
		bildPfad = "bilder/einheiten/hasenritter";
		staerke= 5;
		ruestung= 0;
		name= "Bogenschütze";
		preis = 100;
		
		//eleganter und so
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int[] temp= {i, j};
				bewegung.add(temp);
			}
		}
		
		for (int i = -3; i < 4; i++) {
			for (int j = -3; j < 4; j++) {
				if(Math.abs(i)+Math.abs(j)<5 && Math.abs(i)+Math.abs(j)>2) {
					int[] temp= {i, j};
					angriff.add(temp);
				}
			}
		}
	}	

}
