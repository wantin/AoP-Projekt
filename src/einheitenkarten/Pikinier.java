package einheitenkarten;

import einsnull.Einheit;
import einsnull.Feld;
import einsnull.Spieler;

public class Pikinier extends Einheit {
	
	public Pikinier(Spieler besitzer){
		this.besitzer= besitzer;
		bildPfad = "bilder/einheiten/pikinier.jpg"; //noch nicht gut
		int vor =1;
		if (besitzer.getSeite()=="rechts")vor=-1;
		/* vor (vorzeichen) sollte 1 für den linken Spieler und -1 für den rechten Spieler sein
		*  Pikiniere können eine Ausrichtung meiner Meinung nach auch ganz gut gebrauchen, 
		*  ich glaube in allen anderen Einheiten kann man das eigentlich raus nehmen
		*/
		staerke= 6;
		ruestung= 3;
		name= "Pikinier";
		preis = 200;
		tooltipPfad = "Pikinier_anzeige.jpg"; //noch nicht gut
		
		int[] a= {0, 1*vor};bewegung.add(a);
		int[] b= {1, 1*vor};bewegung.add(b);
		int[] c= {1, 0*vor};bewegung.add(c);
		int[] e= {-1, 1*vor};bewegung.add(e);
		int[] g= {-1, 0*vor};bewegung.add(g);
		//keine rückwärtsbewegung
		
		int[] z= {1, 1*vor};angriff.add(z);
		int[] w= {0, 1*vor};angriff.add(w);
		int[] v= {0, 2*vor};angriff.add(v);
		int[] u= {1, 2*vor};angriff.add(u);
		int[] t= {-1, 1*vor};angriff.add(t);
		int[] s= {-1, 2*vor};angriff.add(s);
		//nur vorwärts angreifen, aber auch mit zwei reichweite
	}
	
	@Override
	public void verteidigen(Feld spielbrett, Einheit angreifer){
		
		//Erstschlag, wenn von vorne attackiert
		int vor =1;
		if (besitzer.getSeite()=="rechts")vor=-1;
		
		boolean frontalangriff = angreifer.getPosition()[0]==1*vor;
		
		if(frontalangriff){
			if(angreifer.getName()!= "Pikinier"){
				angreifer.verteidigen(spielbrett, this);
			}else{ //Sonderfall für Pikinier gegen Pikinier sodass nur der eigentlich angreifende Bonusschaden nimmt
				//analog zum normalen verteidigen nur nicht von Pikinier überschrieben
				if(staerke - angreifer.getRuestung() < 1){
					angreifer.setStaerke(angreifer.getStaerke()-1);
				}else{
					angreifer.setStaerke(angreifer.getStaerke()-(staerke-angreifer.getRuestung()));
				}
				//angreifer tot
				if(angreifer.getStaerke()<1){
					angreifer.getBesitzer().getTruppen().remove(angreifer);
					spielbrett.getInhalt(angreifer.getPosition()[0], angreifer.getPosition()[1]).remove(angreifer);
				}
			}
		}
		//check, dass nicht tote Einheiten noch 1 Schaden machen.
		if(angreifer.getStaerke() > 0){ 
			//Standartablauf des eigentlichen Angriffs
			if(angreifer.getStaerke()-ruestung < 1) { //check auf Schadensh�he
				staerke--; //Mindestschaden
			}else { //regul�rer Schaden
				staerke-= angreifer.getStaerke()-ruestung;
			}
			if(staerke<1) {
				besitzer.getTruppen().remove(this);
				spielbrett.getInhalt(position[0], position[1]).remove(this);
			} 
		}
	}
	
}
