package einsnull;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
	
	static Scanner input = new Scanner(System.in);
	
	//Zugreihenfolge: llrrrrllllrrrrllllrrrr....
	//ziehen ist jetzt in Spieler
	static void runde(Spieler links, Spieler rechts, Feld f, GUI anzeige) {
		
		links.ziehen(f, anzeige);//der erste muss nicht Ã¼berprÃ¼ft werden, ob das spiel zuende ist, weil das in der while schleife schon passiert ist.
		
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			links.ziehen(f, anzeige);
		}
		links.resetBereit();
		
		//rechts	
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f, anzeige);
		}
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f, anzeige);
		}
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f, anzeige);
		}
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f, anzeige);
		}
		rechts.resetBereit();
		
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			links.ziehen(f, anzeige);
		}
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			links.ziehen(f, anzeige);
		}
	}

	public static void main(String[] args) {		

		Spieler links = new Spieler();
		//buttonpress kann nicht eine einfache int, die an GUI übergeben wird ändern. 
		//das ist wohl das weniger elegante workaround
		AuswahlGUI auswahl = new AuswahlGUI(links);
		auswahl.setVisible(true);
		while(links.getAktionsAuswahl0()==-1){
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
		if(links.getAktionsAuswahl0()==1){
			rechts = new KI();
		}else{
			rechts = new Spieler();
		}
		rechts.setSeite("rechts");
				
		//GUI
		GUI anzeige = new GUI(spielbrett, links, rechts);
		anzeige.setVisible(true);
		
		//Spieler oder KI auswÃ¤hlen
		anzeige.setup0(links);

		//Diese Namen sollten nur noch auftauchen, wenn setup1 nicht geklappt hat.
		
		/*links.setName("linkeEule");
		rechts.setName("rechteRatte");*/
		anzeige.setup1(links, rechts);
		
		//Karten wÃ¤hlen
		
		//kaufen ist jetz in spieler und wird über GUI aufgerufen
		
		//Karten ausspielen oder benutzen
		while((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) 
				&& (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())
				|| links.getGold()==1000 //da nicht gewartet wird bis kaufen fertig ist..
			){
			runde(links, rechts, spielbrett, anzeige);
		}
		
		//Sieger bekanntgeben
		//TODO: switch from console to GUI
		if(!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) {
			System.out.println(links.getName() + " hat gewonnen.");
		}else {
			if(!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty()) {
				System.out.println(rechts.getName() + " hat gewonnen.");
			}else {
				System.out.println("Die Partie endet unentschieden.");
			}
		}
	}

}
