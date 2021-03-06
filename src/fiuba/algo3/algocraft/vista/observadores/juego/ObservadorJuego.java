package fiuba.algo3.algocraft.vista.observadores.juego;

import fiuba.algo3.algocraft.modelo.juego.Jugador;

public interface ObservadorJuego {
	public abstract void nuevoTurno();

	public abstract void hayPerdedor(Jugador perdedor);
	
	public abstract void encolarError(Exception error);
}
