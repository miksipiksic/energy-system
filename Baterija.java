package energetika;

public class Baterija {
	
	int energija;
	int kap;
	
	public Baterija(int kap) {
		this.kap = kap;
		this.energija = kap;
	}
	
	public void dopuniBateriju(int dopuna) {
		this.energija += dopuna;
		if (this.energija > kap) {
			this.energija = kap;
		}
	}
	
	public void isprazniBateriju() {
		this.energija = 0;
	}
	
	public boolean baterijaPuna() {
		if(this.energija == kap) {
			return true;
		} else return false;
	}

}
