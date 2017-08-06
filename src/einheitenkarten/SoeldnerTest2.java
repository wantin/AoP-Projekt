package einheitenkarten;

import einsnull.Einheit;
import einsnull.Spieler;

public class SoeldnerTest2 extends Einheit{
	
	public SoeldnerTest2(Spieler besitzer) {
		this.besitzer= besitzer;
		int v =1;
		if (besitzer.getSeite()=="rechts")v=-1;
		//v (vorzeichen) sollte 1 für den linken Spieler und -1 für den rechten Spieler sein
		staerke= 5;
		ruestung= 2;
		name= "S�ldner2";
		preis = 100;
		//ich weiß nicht, ob das nicht eleganter geht..
		int[] a= {1*v,0*v};bewegung.add(a);
		int[] b= {1*v,1*v};bewegung.add(b);
		int[] c= {0*v,1*v};bewegung.add(c);
		int[] d= {-1*v,1*v};bewegung.add(d);
		int[] e= {1*v,-1*v};bewegung.add(e);
		int[] f= {-1*v,0*v};bewegung.add(f);
		int[] g= {0*v,-1*v};bewegung.add(g);
		int[] h= {-1*v,-1*v};bewegung.add(h);
		
		int[] z= {1*v,1*v};angriff.add(z);
		int[] y= {-1*v,1*v};angriff.add(y);
		int[] x= {0*v,1*v};angriff.add(x);
	}

}
//abcdefghijklmnopqrstuvwxyz