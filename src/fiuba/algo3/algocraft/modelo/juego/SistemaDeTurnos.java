package fiuba.algo3.algocraft.modelo.juego;

import java.util.ArrayList;

import fiuba.algo3.algocraft.modelo.excepciones.*;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.utilidades.Interactuable;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Ataque;
import fiuba.algo3.algocraft.vista.observadores.juego.ObservadorJuego;


public class SistemaDeTurnos {
	private ArrayList<ObservadorJuego> observadores = new ArrayList<ObservadorJuego>();
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorQueJuega;
    private int turno = 0;
    private Mapa mapa;


    public SistemaDeTurnos(ArrayList<Jugador> jugadores, Mapa mapa) {
        this.jugadores = jugadores;
        if (jugadores.size() > 0) 
            jugadorQueJuega = jugadores.get(0);

        this.mapa = mapa;
    }

    public Jugador getJugadorQueJuega() {
        if (this.jugadorQueJuega == null && this.jugadores.size() > 0)
            this.jugadorQueJuega = this.jugadores.get(0);

        return this.jugadorQueJuega;
    }

    public void pasarTurno(Jugador jugador) throws ExcepcionEstadoMuerto, ExcepcionCoordenadaFueraDelMapa {
        if (this.jugadorQueJuega == null && this.jugadores.size() > 0)
            this.jugadorQueJuega = this.jugadores.get(0);

        if (!jugador.equals(this.jugadorQueJuega))
            throw new ExcepcionNoEsElTurnoDelJugador();

        if (this.turno < (this.jugadores.size() - 1))
            this.turno++;
        else
            this.turno = 0;

        this.jugadorQueJuega = this.jugadores.get(this.turno);
        this.tareasDeEntreturno();
    }

    private void tareasDeEntreturno() throws ExcepcionEstadoMuerto{
    	ArrayList<Ataque> ataques = new ArrayList<Ataque>();
    	for (Jugador j: this.jugadores) {
            ArrayList<Interactuable> interactuables = new ArrayList<Interactuable>();
            ataques.addAll(j.getAtaques());
            interactuables.addAll(j.getConstrucciones());
            interactuables.addAll(j.getUnidades());
        
            for (Interactuable i: interactuables) {
                try {
					i.tareaDeEntreTurno(this.mapa);
				} catch (ExcepcionUnidadParaDeMover e) {
					for (ObservadorJuego observador: this.observadores)
		                observador.encolarError(e);
				}
            }
        }
        for (Ataque i: ataques) {
            try {
				i.tareaDeEntreTurno(this.mapa);
			}  catch (ExcepcionEnemigoFueraDeAlcance e) {
				for (ObservadorJuego observador: this.observadores)
	                observador.encolarError(e);
			}
        }

    }

	public void setObservador(ObservadorJuego observadorJuego) {
		this.observadores.add(observadorJuego);
		
	}


}
