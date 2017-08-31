package einheitenkarten;

import java.util.Scanner;
import einsnull.Einheit;
import einsnull.Feld;
import einsnull.Spieler;

public class Ritter extends Einheit {
	
	static Scanner input= new Scanner(System.in);
	
	public Ritter(Spieler besitzer) {
		this.besitzer= besitzer;
		bildPfad = "bilder/einheiten/hasenritter.jpg";
		int vor =1;
		if (besitzer.getSeite()=="rechts")vor=-1;
		//vor (vorzeichen) sollte 1 für den linken Spieler und -1 für den rechten Spieler sein
		staerke= 9;
		ruestung= 5;
		name= "Hasenritter";
		preis = 400;
		tooltipPfad = "Hasenritter_anzeige.jpg";
		
		//eleganter und so
		for (int i = -2; i < 3; i++) {
			for (int j = -2; j < 3; j++) {
				int[] temp= {i*vor, j*vor}; //vor braucht es eigentlich nicht, da die Bewegung vollkommen symmetrisch ist.
				bewegung.add(temp);
				angriff.add(temp);
			}
		}
	}
	
	@Override
	public void angreifen(Feld spielbrett, int zeile, int spalte) {
		
		
		
		int x, y, bonus, min;
		x= position[0];
		y= position[1];
		min = 100;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if(zeile+i>-1 && zeile+i<6 && spalte+j>-1 && spalte+j<6){
					if(!spielbrett.besetzt(zeile+i, spalte+j)
							|| (position[0] == zeile+i && position[1] == spalte+j) // das Feld darf von dem Ritter selbst belegt sein	
					){
						int temp = Math.abs(position[0]-(zeile+i)) + Math.abs(position[1]-(spalte+j));
						if(temp<min){
							min = temp;
							x=zeile+i;
							y=spalte+j;
						}
						
					}
				}
			}
		}
		
		bonus= Math.max( Math.abs(x - position[0]), Math.abs(y-position[1])); //bonusschaden für anstürmen
		bewegen(spielbrett, x, y);
		staerke += bonus;
		spielbrett.getInhalt(zeile, spalte).get(0).verteidigen(spielbrett, this);
		staerke -= bonus;
	}

}
