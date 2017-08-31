package einheitenkarten;

import einsnull.Einheit;
import einsnull.Spieler;

public class Soeldner extends Einheit{
	
	public Soeldner(Spieler besitzer) {
		this.besitzer= besitzer;
		bildPfad = "bilder/einheiten/soldner.jpg";
		int vor =1;
		if (besitzer.getSeite()=="rechts")vor=-1;
		//vor (vorzeichen) sollte 1 f√ºr den linken Spieler und -1 f√ºr den rechten Spieler sein
		staerke= 5;
		ruestung= 2;
		name= "Soldner";
		preis = 100;
		tooltipPfad = "Sˆldner_anzeige.jpg";
		//ich weiﬂ nicht, ob das nicht eleganter geht..
		int[] a= {1*vor,0*vor};bewegung.add(a);
		int[] b= {1*vor,1*vor};bewegung.add(b);
		int[] c= {0*vor,1*vor};bewegung.add(c);
		int[] d= {-1*vor,1*vor};bewegung.add(d);
		int[] e= {1*vor,-1*vor};bewegung.add(e);
		int[] f= {-1*vor,0*vor};bewegung.add(f);
		int[] g= {0*vor,-1*vor};bewegung.add(g);
		int[] h= {-1*vor,-1*vor};bewegung.add(h);
		
		int[] z= {1*vor,1*vor};angriff.add(z);
		int[] y= {-1*vor,1*vor};angriff.add(y);
		int[] x= {0*vor,1*vor};angriff.add(x);
		int[] w= {1*vor,0*vor};angriff.add(w);
		int[] v= {-1*vor,0*vor};angriff.add(v);
		int[] u= {0*vor,-1*vor};angriff.add(u);
		int[] t= {1*vor,-1*vor};angriff.add(t);
		int[] s= {-1*vor,-1*vor};angriff.add(s);

	}

}
//abcdefghijklmnopqrstuvwxyz