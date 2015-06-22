package fiuba.algo3.algocraft.modelo.mapa;

import java.util.Random;

public class Randomizador {
//	private int max;
//	private int min;
//	
//	public Randomizador(){
//		this.max = max;
//		this.min = min;
//	}
	
//	public int randInt() {
//
//	    // NOTE: Usually this should be a field rather than a method
//	    // variable so that it is not re-seeded every call.
//	    Random rand = new Random();
//
//	    // nextInt is normally exclusive of the top value,
//	    // so add 1 to make it inclusive
//	    int randomNum = rand.nextInt((this.max - this.min) + 1) + this.min;
//
//	    return randomNum;
//	}
	public int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min)) + min;

	    return randomNum;
	}
}
