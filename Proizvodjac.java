package energetika;

import java.awt.Color;
import java.util.Random;

public abstract class Proizvodjac extends Parcela implements Runnable { // Producer
	
	protected Baterija bat; // battery
	protected int osnovnoVreme; // starting time
	protected int jedinice = 0; // energy units
	
	protected boolean uspeh = false; // success
	
	private boolean work = false;
	
	private long sleepTime = 300;
	private Thread thread;
	
	public Proizvodjac(int osnovnoVreme, Baterija bat, Color boja, String oznaka) {
		super(oznaka, boja);
		this.osnovnoVreme = osnovnoVreme;
		this.bat = bat;
		thread = new Thread(this);
		thread.start();
	}

	public int ukupnoVreme() { // total time
		Random rand = new Random();
		return osnovnoVreme + rand.nextInt((300 - 0) + 1) + 0;
	}
	
	public abstract void proizvedi(); // produce energy
	
	@Override
	public void run() {
		try {
		while(!thread.isInterrupted()) {
			this.setForeground(Color.WHITE);
			Thread.sleep(ukupnoVreme());
			proizvedi();
			if(uspeh) {
				this.setForeground(Color.red);
			}

			Thread.sleep(sleepTime);
		}		
		} catch (InterruptedException e) {	}
		
		
	}
	
	public void zaustavi() { // stop the producer
		work = false;
		if(thread != null) thread.interrupt();
	}

}
