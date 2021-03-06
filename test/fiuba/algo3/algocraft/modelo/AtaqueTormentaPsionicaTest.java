package fiuba.algo3.algocraft.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionAlcanzadoElMaximoCupoDeJugadores;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionColorEnUso;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionCoordenadaFueraDelMapa;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionElementoNoAdmitidoEnParcela;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEnemigoFueraDeAlcance;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEstadoMuerto;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoEsElTurnoDelJugador;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoHaySuministrosDisponibles;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNombreCorto;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNombreEnUso;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNumeroDeBasesInvalido;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionParcelaOcupada;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionRecursosInsuficientes;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionUnidadParaDeMover;
import fiuba.algo3.algocraft.modelo.juego.Color;
import fiuba.algo3.algocraft.modelo.juego.Juego;
import fiuba.algo3.algocraft.modelo.juego.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Coordenada;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.razas.terran.Terran;
import fiuba.algo3.algocraft.modelo.razas.terran.unidades.Golliat;
import fiuba.algo3.algocraft.modelo.razas.terran.unidades.Marine;
import fiuba.algo3.algocraft.modelo.utilidades.Interactuable;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Ataque;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.AtaqueTormentaPsionica;

public class AtaqueTormentaPsionicaTest {

	@Test
	public void despuesDeDosAtaqueEsDescartable() throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionNombreCorto, ExcepcionNoHaySuministrosDisponibles, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNoEsElTurnoDelJugador, ExcepcionUnidadParaDeMover {
		 Jugador unJugador2 = new Jugador("Juan2", Color.VERDE, Terran.getInstance());
		 Juego juego = new Juego();
		 juego.agregarJugador(unJugador2);		 
		 Mapa mapa = new Mapa(2, 5, 5);
	     unJugador2.crearAdicionalDeSuministro(mapa, new Coordenada(3, 1));
	     for(int i= 0; i<=7; i++){
				
	    	 unJugador2.terminarTurno(juego);
	        }
	     Interactuable marine = new Marine(unJugador2);
	     mapa.ubicarElementoEnParcela(new Coordenada(1,1), marine);
	     
	     Ataque tormenta = new AtaqueTormentaPsionica(marine.getParcela());
	     
	     tormenta.tareaDeEntreTurno(mapa);
	     
	     tormenta.tareaDeEntreTurno(mapa);
	     
	     assertTrue(tormenta.esDescartable());
	     
	}
	
	@Test
	public void despuesDeUnAtaqueNoEsDescartable() throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionNombreCorto, ExcepcionNoHaySuministrosDisponibles, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionNoEsElTurnoDelJugador, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionUnidadParaDeMover {
		 Jugador unJugador2 = new Jugador("Juan2", Color.VERDE, Terran.getInstance());
		 Juego juego = new Juego();
		 juego.agregarJugador(unJugador2);
		 Mapa mapa = new Mapa(2, 5, 5);
	     unJugador2.crearAdicionalDeSuministro(mapa, new Coordenada(3, 1));
	     for(int i= 0; i<=7; i++){
	        	unJugador2.terminarTurno(juego);
	        }
	     Interactuable marine = new Marine(unJugador2);
	     mapa.ubicarElementoEnParcela(new Coordenada(1,1), marine);
	     
	     Ataque tormenta = new AtaqueTormentaPsionica(marine.getParcela());
	     
	     tormenta.tareaDeEntreTurno(mapa);
	     
	     assertFalse(tormenta.esDescartable());
	     
	}
	
	@Test
	public void hace50DeDanyoEnUnAtaque() throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionNombreCorto, ExcepcionNoHaySuministrosDisponibles, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNoEsElTurnoDelJugador, ExcepcionUnidadParaDeMover {
		 Jugador unJugador2 = new Jugador("Juan2", Color.VERDE, Terran.getInstance());
		 Juego juego = new Juego();
		 juego.agregarJugador(unJugador2);
		 Mapa mapa = new Mapa(2, 5, 5);
         unJugador2.sumarGasVespeno(999);
         unJugador2.sumarMinerales(999);

	     unJugador2.crearAdicionalDeSuministro(mapa, new Coordenada(3, 1));
	     for(int i= 0; i<=7; i++){
	        	
	    	 unJugador2.terminarTurno(juego);
	        }
	     Interactuable golliat = new Golliat(unJugador2);
	     mapa.ubicarElementoEnParcela(new Coordenada(1,1), golliat);
	     while(!golliat.estaCreado()){
	        	
	    	 unJugador2.terminarTurno(juego);
	        }
	     
	     Ataque tormenta = new AtaqueTormentaPsionica(golliat.getParcela());
	     
	     tormenta.tareaDeEntreTurno(mapa);
	     
	     assertEquals((golliat.getVitalidad()).getVida(), 125-50);     
	}
	
	@Test
	public void hace100DeDanyoEnDosAtaques() throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionNombreCorto, ExcepcionNoHaySuministrosDisponibles, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNoEsElTurnoDelJugador, ExcepcionUnidadParaDeMover {
		 Jugador unJugador2 = new Jugador("Juan2", Color.VERDE, Terran.getInstance());
		 Juego juego = new Juego();
		 juego.agregarJugador(unJugador2);
		 Mapa mapa = new Mapa(2, 5, 5);
         unJugador2.sumarGasVespeno(999);
         unJugador2.sumarMinerales(999);

	     unJugador2.crearAdicionalDeSuministro(mapa, new Coordenada(3, 1));
	     for(int i= 0; i<=7; i++){
	        	
	    	 unJugador2.terminarTurno(juego);
	        }
	     
	     Interactuable golliat = new Golliat(unJugador2);
	     mapa.ubicarElementoEnParcela(new Coordenada(1,1), golliat);

	     while(!golliat.estaCreado()){
	        	
	    	 unJugador2.terminarTurno(juego);
	        }
	     Ataque tormenta = new AtaqueTormentaPsionica(golliat.getParcela());
	     
	     tormenta.tareaDeEntreTurno(mapa);
	     tormenta.tareaDeEntreTurno(mapa);

	     
	     assertEquals((golliat.getVitalidad()).getVida(), 125-100); 
	}

}
