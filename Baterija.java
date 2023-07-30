package energetika;

public class Baterija { // Battery
	
	int energija; // energy
	int kap; // capacity
	
	public Baterija(int kap) {
		this.kap = kap;
		this.energija = kap;
	}
	
	public void dopuniBateriju(int dopuna) { // add more energy - dopuna
		this.energija += dopuna;
		if (this.energija > kap) {
			this.energija = kap;
		}
	}
	
	public void isprazniBateriju() { // energy = 0
		this.energija = 0;
	}
	
	public boolean baterijaPuna() { // is the battery full
		if(this.energija == kap) {
			return true;
		} else return false;
	}

}
