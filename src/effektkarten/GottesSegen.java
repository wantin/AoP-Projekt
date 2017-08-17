package effektkarten;

import einsnull.Effekt;
import einsnull.Feld;
import einsnull.Spieler;

public class GottesSegen extends Effekt {
	
	public GottesSegen(Spieler besitzer) {
		this.besitzer = besitzer;
		name = "Gottes Segen";
		preis = 50;
		bildPfad = "bilder/effekte/gottessegen.jpg";
	}
	
	@Override
	 public void ausspielen(int zeile, int spalte, Feld spielbrett) {
		besitzer.getHand().remove(this);
		/*ich wollte eigentlich der verteidigten Einheit eine 50% Chance geben immer wenn sie sich verteidigt keinen Schaden zu nehmen,
		aber ich weiß doch nicht wie, weil ich ja nich direkt in die verteidigen Funktion schreiben kann... also macht Gottes Segen nichts, 
		was ja auch nicht allzu unpassend ist :P */
	}

}
