package einsnull;

import einheitenkarten.*;
import effektkarten.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	static Scanner input = new Scanner(System.in);
	
	//Zugreihenfolge: llrrrrllllrrrrllllrrrr....
	//ziehen ist jetzt in Spieler
	static void runde(Spieler links, Spieler rechts, Feld f) {
		
		links.ziehen(f);//der erste muss nicht überprüft werden, ob das spiel zuende ist, weil das in der while schleife schon passiert ist.
		
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			links.ziehen(f);
		}
		links.resetBereit();
		
		//rechts	
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f);
		}
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f);
		}
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f);
		}
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			rechts.ziehen(f);
		}
		rechts.resetBereit();
		
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			links.ziehen(f);
		}
		if((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())) {
			links.ziehen(f);
		}
	}
	
	private GUI anzeige;
		
	public void start(){
			anzeige.setVisible(true);
	}
		
	public Main(){
			this.anzeige = new GUI();
	}

	public static void main(String[] args) {
		
		Feld spielbrett = new Feld(6,6);
		Spieler links = new Spieler();links.setSeite("links");

				
		//GUI
		Main spiel = new Main();
		spiel.start();
		
		//Spieler oder KI auswählen
		
		System.out.println("Wollen Sie gegen die KI spielen (1), oder zu zweit an einem Computer?");
		int eingabe= input.nextInt();
		
		Spieler rechts;
		
		if(eingabe == 1) {
			rechts = new KI(); rechts.setSeite("rechts");
		}else {
			rechts = new Spieler();rechts.setSeite("rechts");
		}


	
		//Spieler benennen
		//TODO: switch from console to GUI
		
		/* das ist zu nervig, deswegen vorerst anders
		System.out.println("Bitte geben Sie den Namen des linken Spielers ein.");
		links.setName(input.next());
		System.out.println("Bitte geben Sie den Namen des rechten Spielers ein.");
		rechts.setName(input.next());
		*/
		links.setName("linkeEule");
		rechts.setName("rechteRatte");
		
		//Karten wählen
		
		//kaufen ist jetz in spieler
		links.kaufen();
		rechts.kaufen();
		
		//Karten ausspielen oder benutzen
		while((!links.getHand().isEmpty() || !links.getTruppen().isEmpty()) && (!rechts.getHand().isEmpty() || !rechts.getTruppen().isEmpty())){
			runde(links, rechts, spielbrett);
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
