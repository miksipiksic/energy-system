package energetika;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac {
	
	private int vodene;
	
	public Hidroelektrana(Baterija bat) {
		super(1500, bat, Color.blue, "H");
	//	this.pozadina = Color.BLUE;
	//	this.oznaka = "H";
		this.vodene = 0;
	//	this.osnovnoVreme = 1500;
	}
	
	public void postaviVodu(int voda) {
		this.vodene = voda;
	}
	
	@Override
	public void proizvedi() {
		System.out.println("proizvedi");
		if(vodene != 0) {
			
			uspeh = true;
			for(int i = 0; i < vodene; i++) {
				jedinice++;
			}
			System.out.println(uspeh);
		} else {
			uspeh = false; 
		}
	}
	
}
