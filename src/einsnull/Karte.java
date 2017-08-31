package einsnull;

public abstract class Karte {
	
	protected Spieler besitzer;
	protected String name;
	protected int preis;
	protected String tooltipPfad;
	protected String bildPfad;
	protected String art; 
	//ich schaffe es nicht anders festzustellen, ob etwas eine Einheit oder ein Effekt ist.. wenn jemand da eine Lösung kennt können wir das ja einfach ändern indem man nach art sucht.
	
	abstract String getArt();
	abstract String getBildPfad();
	abstract String getName();
	abstract int getPreis();
	abstract boolean nutzen(Feld spielbrett);
	abstract Spieler getBesitzer();
	//Bilder für karten in den Karten speichern.
	
}
