package energetika;

import java.awt.Color;
import java.util.Random;

public abstract class Proizvodjac extends Parcela implements Runnable {
	
	protected Baterija bat;
	protected int osnovnoVreme;
	protected int jedinice = 0;
	
	protected boolean uspeh = false;
	
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

	public int ukupnoVreme() {
		Random rand = new Random();
		return osnovnoVreme + rand.nextInt((300 - 0) + 1) + 0;
	}
	
	public abstract void proizvedi();
	
	@Override
	public void run() {
		//thread.start();
		try {
		while(!thread.isInterrupted()) {
			
			System.out.println("run proizvodjaca");
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
	
	public void zaustavi() {
		work = false;
		if(thread != null) thread.interrupt();
		System.out.println("zaustavljen proizvodjac");
	}

}
