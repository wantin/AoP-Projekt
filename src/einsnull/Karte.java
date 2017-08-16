package einsnull;

public abstract class Karte {
	
	protected Spieler besitzer;
	protected String name;
	protected int preis;
	protected String bildPfad;
	
	abstract String getBildPfad();
	abstract String getName();
	abstract int getPreis();
	
	abstract boolean nutzen(Feld spielbrett);
	//Bilder für karten in den Karten speichern.
	
}
