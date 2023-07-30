package energetika;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac { // Hydroelectric Power Plant
	
	private int vodene; // water surface counter
	
	public Hidroelektrana(Baterija bat) {
		super(1500, bat, Color.blue, "H");
		this.vodene = 0;
	}
	
	public void postaviVodu(int voda) { // water surface counter setter
		this.vodene = voda;
	}
	
	@Override
	public void proizvedi() { // produce more energry
		if(vodene != 0) {
			uspeh = true; // success
			for(int i = 0; i < vodene; i++) {
				jedinice++; // energy units
			}
		} else {
			uspeh = false; 
		}
	}
	
}
