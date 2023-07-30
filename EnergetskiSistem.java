package energetika;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {
	
	private Plac plac; // field
	private Baterija bat; // battery
	private Panel dugmePanel = new Panel();
	
	
	private void populateWindow(int redovi, int kolone, int kap) { // rows, columns, capacity
		plac = new Plac(redovi, kolone);
		bat = new Baterija(kap);
		
		add(plac, BorderLayout.CENTER);
		
		Button dodaj = new Button("Dodaj"); // add a producer button
		
		dodaj.addActionListener((ae) -> {
			Hidroelektrana h = new Hidroelektrana(bat);
			plac.dodajProizvodjaca(h);	
		});
		
		dugmePanel.add(dodaj);
		add(dugmePanel, BorderLayout.NORTH);
	}
	
	public EnergetskiSistem(int redovi, int kolone, int kap) {
		setBounds(0, 0, 500, 500);
		
		setTitle("Energetski sistem");
		setResizable(false);
		
		populateWindow(redovi, kolone, kap);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				plac.zaustaviProizvodjace();
				dispose();
			
			}
		});
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new EnergetskiSistem(5, 5, 43);
	}
}
