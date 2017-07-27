package einheitenkarten;

/*
 * Ich bin vorerst nur am herumexperimentieren, keine Ahnung wie wir alles lösen
 * Vielleicht ist die Position.java wegen der Feld.java gar nicht nötig und es
 * lässt sich auch ohne das hier implementieren. Müssen wir uns noch mal überlegen.
 */

public class Position {

	private int x;
	private int y;
	
	public Position (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX (int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
}
