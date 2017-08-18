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
		name= "Ritter";
		preis = 400;
		
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
		
		System.out.println("Mögliche Felder um anzustürmen:");
		for (int i = 0; i < bewegung.size(); i++) {
			//check fuer Rand Feldgröße flexibel
			if(position[0]+bewegung.get(i)[0] < spielbrett.getAnzahlZeilen() && position[0]+bewegung.get(i)[0] >= 0 && position[1]+bewegung.get(i)[1] < spielbrett.getAnzahlSpalten() && position[1]+bewegung.get(i)[1] >= 0
					//es darf keine Einheit auf dem Feld sein
					&& !spielbrett.besetzt(position[0]+bewegung.get(i)[0], position[1]+bewegung.get(i)[1])
					//muss angrenzend an das angegriffene Feld sein.
					&& (((position[0]+bewegung.get(i)[0]-zeile == -1 || position[0]+bewegung.get(i)[0]-zeile == 1) && position[1]+bewegung.get(i)[1]-zeile == 0) ||
							((position[1]+bewegung.get(i)[1]-zeile == -1 || position[1]+bewegung.get(i)[1]-zeile == 1) && position[0]+bewegung.get(i)[0]-zeile == 0))
					) {
				System.out.println((position[0]+bewegung.get(i)[0]) + ", " + (position[1]+bewegung.get(i)[1]));
			}
		}
		System.out.println("Geben Sie die Zeile an.");
		int x, y, bonus;
		x= input.nextInt();
		System.out.println("Geben Sie die Spalte an.");
		y= input.nextInt();
		bonus= Math.abs(x - position[0])+Math.abs(y-position[1]); //bonusschaden für anstürmen
		this.bewegen(spielbrett, x, y);
		staerke += bonus;
		spielbrett.getInhalt(zeile, spalte).get(0).verteidigen(spielbrett, this);
		staerke -= bonus;
	}

}
