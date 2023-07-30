package energetika;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class Parcela extends Label { // Surface
	
	protected String oznaka; // mark
	protected Color pozadina; // background
	protected boolean izabrana; //chosen surface
	
	public Parcela(String oznaka, Color pozadina) {
		this.oznaka = oznaka;
		this.pozadina = pozadina;
		setForeground(Color.WHITE);
		setBackground(pozadina);
		setFont(new Font("Serif", Font.BOLD, 14));
		setText(oznaka);
		setAlignment(Label.CENTER);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Parcela source = (Parcela) e.getSource();
				((Plac)(source.getParent())).izaberiParcelu(source);
			}
		});
	}
	
	public Parcela() {
		
	}
	
	public void promeniPozadinu(Color c) { // change the background color 
		setBackground(c);
	}
	
	
	
	

}
