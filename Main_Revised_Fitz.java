package einsnull;


import java.util.Scanner;

public class Main_Revised_Fitz {
        
        /**
         * @author JFitz
         * @description Prüft ob ein einzelner Spieler noch im Spiel ist
         */
        public static boolean bPlayerContinue(Spieler Player) {
            return (!Player.getHand().isEmpty() || !Player.getTruppen().isEmpty());
        }
    
        /** 
         * @author JFitz
         * @description Prüft ob beide Spieler noch im Spiel sind
         * @wissenwertes
         * Hungarian Notation (kleines b für boolean)
         * Objekte groß schreiben, einfache Datentypen/Methoden notieren und klein schreiben
         * Diese Methode, sowie der Prozeß sollten nicht unbedingt in der Main Klasse deklariert sein.
         * Man kann & sollte dies auslagern.
         * 
         * @author wantin in Spieler, oder?
         */
        public static boolean bPlayersContinue(Spieler Drawing, Spieler Pending) {
            return bPlayerContinue(Drawing) && bPlayerContinue(Pending);
        }
        
        /** 
         * @author JFitz
         * @description Emuliert einen Kartenzug.
         * @wissenwertes 
         * Hier wird deutlich, dass eine Auslagerung 
         * des Prozesses in eine eigene Klasse von Vorteil wäre.
         * Die Methode erhält unnötig viele Parameter, da "Spielfeld" keine Membervariable ist.
         * 
         * @author wantin auch das in Spieler, oder?
         */
        public static void emulateDraw(Spieler Drawing, Spieler Pending, Feld Spielfeld) {
            if(bPlayersContinue(Drawing, Pending)) {
		//Drawing.ziehen(Spielfeld);
            }
        }
	
	static Scanner input = new Scanner(System.in);
	
	//Zugreihenfolge: llrrrrllllrrrrllllrrrr....
	//ziehen ist jetzt in Spieler
	/**
	 * @author wantin aber das in Spieler zu verschieben w�re doch etwas komisch, oder?
	 * weil das ist ja nicht nur ein Spieler..
	 * also nach Feld?
	 */
	static void runde(Spieler links, Spieler rechts, Feld spielfeld) {
                
                /** @author wantin 
                 * mein Deutsch ist herrlich
                 * Der erste muss nicht überprüft werden, ob das spiel zuende ist, 
                 * weil das in der while schleife schon passiert ist. 
                 */
		//links.ziehen(spielfeld);        
        emulateDraw(links, rechts, spielfeld);
		links.resetBereit();
		
		//rechts	
        emulateDraw(rechts, links, spielfeld);
        emulateDraw(rechts, links, spielfeld);
        emulateDraw(rechts, links, spielfeld);
        emulateDraw(rechts, links, spielfeld);
		rechts.resetBereit();
		
		emulateDraw(links, rechts, spielfeld);
                emulateDraw(links, rechts, spielfeld);
	}

	public static void main(String[] args) {		

		Feld spielbrett = new Feld(6,6);
		Spieler links = new Spieler();links.setSeite("links");
		Spieler rechts = new Spieler();
				
		//GUI
		GUI anzeige = new GUI(spielbrett, links, rechts);
		anzeige.setVisible(true);
		
		//Spieler oder KI auswählen
		anzeige.setup0(links);

		//Diese Namen sollten nur noch auftauchen, wenn setup1 nicht geklappt hat.
		
		/*links.setName("linkeEule");
		rechts.setName("rechteRatte");*/
		anzeige.setup1(links, rechts);
		
		//Karten wählen
		
		//kaufen ist jetz in spieler und wird �ber GUI aufgerufen
		
		/** Karten ausspielen oder benutzen */
		while(bPlayersContinue(links, rechts) || links.getGold()==1000){
			runde(links, rechts, spielbrett);
		}
		
		//Sieger bekanntgeben
		//TODO: switch from console to GUI
		if(bPlayerContinue(links)) {
			System.out.println(links.getName() + " hat gewonnen.");
		} else {
			if(bPlayerContinue(rechts)) {
				System.out.println(rechts.getName() + " hat gewonnen.");
			}else {
				System.out.println("Die Partie endet unentschieden.");
			}
		}
	}

}
