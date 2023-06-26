package energetika;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Plac extends Panel {

	
	private Parcela [][] resetka;
	private int redovi;
	private int kolone;
	private Parcela izabrana;
	
	Plac(int redovi, int kolone) {
		this.redovi = redovi;
		this.kolone = kolone;
		setLayout(new GridLayout(redovi, kolone, 1, 1));
		resetka = new Parcela[redovi][kolone];
		for(int i = 0; i < redovi; i++) {
			for(int j = 0; j < kolone; j++) {
				if (Math.random() <= 0.7) {
					//resetka[i][j] = new TravnataPovrs();
					this.add(resetka[i][j] = new TravnataPovrs());
				} else {
					//resetka[i][j] = new VodenaPovrs();
					this.add(resetka[i][j] = new VodenaPovrs());
				}
			}
		}
	}
	
	
	public void izaberiParcelu(Parcela p) {
		if(izabrana != null) {
			izabrana.setFont(new Font("Serif", Font.BOLD, 14));
		}
		this.izabrana = p;
		p.izabrana = true;
		p.setFont(new Font("Serif", Font.BOLD, 20));
		p.revalidate();
	}
	
	
	
	
	public void dodajProizvodjaca(Proizvodjac pr) {
		
		if(izabrana!= null) {
			int poz;
			int k = 0, l = 0;
			for(int i = 0; i < redovi; i++) {
				for(int j = 0; j < kolone; j++) {
					if(izabrana == resetka[i][j]) {
						System.out.println("izabrana: " + i + " " + j);
						k = i; l = j;

						System.out.println(k + " " + l);break;
					}
				}
			}
			
			poz = k * redovi + l;
			remove(izabrana);
			//((Proizvodjac) izabrana).zaustavi();
			izabrana = null;
			
			resetka[k][l] = pr;
			add(pr, poz);
			
			int voda = 0;
			if(pr instanceof Hidroelektrana) {
				
				if(!(k - 1 < 0 || l - 1 < 0)) {
					System.out.println("poz: "+ (k - 1) + " " + (l - 1));
					if ((resetka[k-1][l-1] instanceof VodenaPovrs)) voda++;
				}
				if(!(k - 1 < 0 )) {
					System.out.println("poz: " + (k - 1) + " " + (l));
					if ((resetka[k-1][l] instanceof VodenaPovrs)) voda++;
				}
				if(!(k - 1 < 0 || l + 1 > kolone - 1)) {
					System.out.println("poz: " + (k - 1) + " " + (l+1));
					if ((resetka[k-1][l+1] instanceof VodenaPovrs)) voda++;
				}
				if(!(l - 1 < 0)) {
					System.out.println("poz: " + (k) + " " + (l-1));
					if ((resetka[k][l-1] instanceof VodenaPovrs)) voda++;
				}
				if(!( l + 1 > kolone - 1)) {
					System.out.println("poz: " + (k) + " " + (l + 1));
					if ((resetka[k][l+1] instanceof VodenaPovrs)) voda++;
				}
				if(!(k + 1 > redovi - 1 || l - 1 < 0)) {
					System.out.println("poz: " + (k + 1) + " " + (l-1));
					if ((resetka[k+1][l-1] instanceof VodenaPovrs)) voda++;
				}
				if(!(k + 1 > redovi - 1)) {
					System.out.println("poz: " + (k + 1) + " " + (l));
					if ((resetka[k+1][l] instanceof VodenaPovrs)) voda++;
				}
				if(!(k + 1 > redovi - 1 || l + 1 > kolone - 1)) {
					System.out.println("poz: " + (k + 1) + " " + (l + 1));
					if ((resetka[k+1][l+1] instanceof VodenaPovrs)) voda++;
				} 
				
				((Hidroelektrana)pr).postaviVodu(voda);
				System.out.println("voda:" + voda);
			}
			
			resetka[k][l].revalidate();
			
			
		} else {
			((Proizvodjac) pr).zaustavi();
			return;
		}
		
	}

	public void zaustaviProizvodjace() {
		System.out.println("zaustavi proizvodjace");
		for(int i = 0 ; i < redovi; i ++) {
			for(int j = 0; j < kolone; j++) {
				if(resetka[i][j] instanceof Proizvodjac) {
					((Proizvodjac)resetka[i][j]).zaustavi();
				}
			}
		}
		
	}
	
	
}
