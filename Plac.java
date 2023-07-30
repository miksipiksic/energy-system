package energetika;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Plac extends Panel { // Field

	
	private Parcela [][] resetka; // surface grid
	private int redovi; // rows
	private int kolone; // columns
	private Parcela izabrana; // chosen surface
	
	Plac(int redovi, int kolone) {
		this.redovi = redovi;
		this.kolone = kolone;
		setLayout(new GridLayout(redovi, kolone, 1, 1));
		resetka = new Parcela[redovi][kolone];
		for(int i = 0; i < redovi; i++) {
			for(int j = 0; j < kolone; j++) {
				if (Math.random() <= 0.7) {
					this.add(resetka[i][j] = new TravnataPovrs());
				} else {
					this.add(resetka[i][j] = new VodenaPovrs());
				}
			}
		}
	}
	
	
	public void izaberiParcelu(Parcela p) { // choose a surface
		if(izabrana != null) {
			izabrana.setFont(new Font("Serif", Font.BOLD, 14));
		}
		this.izabrana = p;
		p.izabrana = true;
		p.setFont(new Font("Serif", Font.BOLD, 20));
		p.revalidate();
	}
	
	
	
	
	public void dodajProizvodjaca(Proizvodjac pr) { // add a producer
		
		if(izabrana!= null) {
			int poz;
			int k = 0, l = 0;
			for(int i = 0; i < redovi; i++) {
				for(int j = 0; j < kolone; j++) {
					if(izabrana == resetka[i][j]) {
						k = i; l = j;
					}
				}
			}
			
			poz = k * redovi + l; // save the position of a chosen surface
			remove(izabrana);
			izabrana = null;
			
			resetka[k][l] = pr;
			add(pr, poz);
			
			int voda = 0;
			if(pr instanceof Hidroelektrana) { // count water surfaces
				
				if(!(k - 1 < 0 || l - 1 < 0)) {
					if ((resetka[k-1][l-1] instanceof VodenaPovrs)) voda++;
				}
				if(!(k - 1 < 0 )) {
					if ((resetka[k-1][l] instanceof VodenaPovrs)) voda++;
				}
				if(!(k - 1 < 0 || l + 1 > kolone - 1)) {
					if ((resetka[k-1][l+1] instanceof VodenaPovrs)) voda++;
				}
				if(!(l - 1 < 0)) {
					if ((resetka[k][l-1] instanceof VodenaPovrs)) voda++;
				}
				if(!( l + 1 > kolone - 1)) {
					if ((resetka[k][l+1] instanceof VodenaPovrs)) voda++;
				}
				if(!(k + 1 > redovi - 1 || l - 1 < 0)) {
					if ((resetka[k+1][l-1] instanceof VodenaPovrs)) voda++;
				}
				if(!(k + 1 > redovi - 1)) {
					if ((resetka[k+1][l] instanceof VodenaPovrs)) voda++;
				}
				if(!(k + 1 > redovi - 1 || l + 1 > kolone - 1)) {
					if ((resetka[k+1][l+1] instanceof VodenaPovrs)) voda++;
				} 
				
				((Hidroelektrana)pr).postaviVodu(voda); // set water surface counter
			}
			
			resetka[k][l].revalidate();
			
			
		} else {
			((Proizvodjac) pr).zaustavi();
			return;
		}
		
	}

	public void zaustaviProizvodjace() { // stop the producers
		for(int i = 0 ; i < redovi; i ++) {
			for(int j = 0; j < kolone; j++) {
				if(resetka[i][j] instanceof Proizvodjac) {
					((Proizvodjac)resetka[i][j]).zaustavi();
				}
			}
		}
		
	}
	
	
}
