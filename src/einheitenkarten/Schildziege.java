package einheitenkarten;

import einsnull.Einheit;
import einsnull.Feld;
import einsnull.Spieler;

public class Schildziege extends Einheit{
	
	public Schildziege(Spieler besitzer) {
		this.besitzer= besitzer;
		bildPfad = "bilder/einheiten/schildziege.jpg";
		int vor =1;
		if (besitzer.getSeite()=="rechts")vor=-1;
		//vor (vorzeichen) sollte 1 f√ºr den linken Spieler und -1 f√ºr den rechten Spieler sein
		staerke= 7;
		ruestung= 3;
		name= "Schildziege";
		preis = 200;
		tooltipPfad = "Schildziege_anzeige.jpg";
		//ich wei√ü nicht, ob das nicht eleganter geht..
		
		//also so sollte das uebrigens eleganter und k¸rzer sein, falls ich das richtig verstanden habe
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int[] temp = {i*vor, j*vor}; bewegung.add(temp); angriff.add(temp);				
				}
		}
	}
	@Override
	public void verteidigen(Feld spielbrett, Einheit angreifer){
		if(angreifer.getName()=="Bogensch¸tze"){//bessere verteidigung gegen Bogensch¸tzen
			staerke--;
		}else{//andere Einheiten
			if(angreifer.getStaerke()-ruestung < 1) { //check auf Schadensh√∂he
				staerke--; //Mindestschaden
			}else { //regul√§rer Schaden				
				staerke-= angreifer.getStaerke()-ruestung;
			}
		}
		if(staerke<1) {
			besitzer.getTruppen().remove(this);
			spielbrett.getInhalt(position[0], position[1]).remove(this);
		}
	}

}
