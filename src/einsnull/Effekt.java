package einsnull;

public class Effekt extends Karte {
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//diese Funktion sollte im allgemeinen von den einzelnen Effekten überschrieben werden.
	void ausspielen(Spieler ausspielender) {
		System.out.println(ausspielender.getName() + " hat " + name + " ausgespielt.");
	}

}
