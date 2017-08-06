package einsnull;

public abstract class Karte {
	
	protected Spieler besitzer;
	protected String name;
	
	abstract String getName();
	
	abstract boolean nutzen(Feld spielbrett);
	//Bilder für karten in den Karten speichern.
	
}
