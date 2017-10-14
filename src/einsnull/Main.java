package einsnull;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	
	static Scanner inputScanner = new Scanner(System.in);
	
	//Zugreihenfolge: llrrrrllllrrrrllllrrrr....
	//ziehen ist jetzt in Spieler
	static void runde(Spieler links, Spieler rechts, Feld f, GUI anzeige) {
		
		links.ziehen(f, anzeige);//der erste muss nicht überprüft werden, ob das spiel zuende ist, weil das in der while schleif e schon passiert ist.
		
		if ((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			links.ziehen(f, anzeige);
		}
		links.resetBereit();
		
		//rechts	
		if ((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f, anzeige);
		}
		if ((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f, anzeige);
		}
		if ((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f, anzeige);
		}
		if ((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f, anzeige);
		}
		rechts.resetBereit();
		
		if ((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			links.ziehen(f, anzeige);
		}
		if ((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			links.ziehen(f, anzeige);
		}
	}

	public static void main(String[] args) {		

		Spieler links = new Spieler();
		//buttonpress kann nicht eine einfache int, die an GUI uebergeben wird aendern. 
		//das ist wohl das weniger elegante workaround
		AuswahlGUI auswahl = new AuswahlGUI(links);
		auswahl.setVisible(true);
		while (links.getAktionsAuswahl0()==-1) {
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Spieler rechts;
		Feld spielbrett = new Feld(6,6);
		links.setSeite("links");
		if (links.getAktionsAuswahl0()==1) {
			rechts = new KI();
		} else {
			rechts = new Spieler();
		}
		rechts.setSeite("rechts");
				
		//GUI
		GUI anzeige = new GUI(spielbrett, links, rechts);
		anzeige.setVisible(true);
		anzeige.setup1(links, rechts);
		
		//Karten wählen
		//kaufen ist in spieler und wird über GUI aufgerufen
		
		//Karten ausspielen oder benutzen
		while ((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) 
				&& (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())
				|| links.getGold()==1000 //da nicht gewartet wird bis kaufen fertig ist..
			) {
			runde(links, rechts, spielbrett, anzeige);
		}
		
		//Sieger bekanntgeben
		anzeige.siegerBekanntgabe(links, rechts);
	}

}
