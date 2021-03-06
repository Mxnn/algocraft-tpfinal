package fiuba.algo3.algocraft.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionAlcanzadoElMaximoCupoDeJugadores;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionColorEnUso;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionCoordenadaFueraDelMapa;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionElementoNoAdmitidoEnParcela;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEnemigoFueraDeAlcance;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEnergiaInsuficiente;
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
import fiuba.algo3.algocraft.modelo.razas.protoss.Protoss;
import fiuba.algo3.algocraft.modelo.razas.protoss.unidades.Dragon;
import fiuba.algo3.algocraft.modelo.razas.terran.Terran;
import fiuba.algo3.algocraft.modelo.razas.terran.unidades.Marine;
import fiuba.algo3.algocraft.modelo.utilidades.Interactuable;
import fiuba.algo3.algocraft.modelo.utilidades.VitalidadProtoss;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Ataque;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.AtaqueRadiacion;

public class AtaqueRadiacionTest {

	@Test
	public void danya15EnUnAtaque() throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionNombreCorto, ExcepcionNoHaySuministrosDisponibles, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionEnergiaInsuficiente, ExcepcionNoEsElTurnoDelJugador, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionUnidadParaDeMover {
		Juego juego = new Juego();
		Jugador unJugador = new Jugador("Juan", Color.ROJO, Protoss.getInstance());
		juego.agregarJugador(unJugador);
        unJugador.sumarGasVespeno(999);
        unJugador.sumarMinerales(999);
		Mapa mapa = new Mapa(2, 5, 5);
	    unJugador.crearAdicionalDeSuministro(mapa, new Coordenada(3, 3));
	    for(int i= 0; i<=7; i++) {
	        unJugador.terminarTurno(juego);
	    }
	    Interactuable dragon = new Dragon(unJugador);
	    mapa.ubicarElementoEnParcela(new Coordenada (1,1), dragon);

	    while(!dragon.estaCreado()){
	    	unJugador.terminarTurno(juego);
	    }
	    
	    Ataque radiacion = new AtaqueRadiacion(dragon.getParcela());

	    radiacion.tareaDeEntreTurno(mapa);

	    assertEquals(80-15, ((VitalidadProtoss)dragon.getVitalidad()).getEscudo());
	}

	@Test
	public void despuesDeQueElTargetMuereEsDescartable() throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionNombreCorto, ExcepcionNoHaySuministrosDisponibles, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionNoEsElTurnoDelJugador, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionUnidadParaDeMover {
		Juego juego = new Juego(); 
		Jugador unJugador2 = new Jugador("Juan2", Color.VERDE, Terran.getInstance());
		juego.agregarJugador(unJugador2);
		Mapa mapa = new Mapa(2, 5, 5);
	    unJugador2.crearAdicionalDeSuministro(mapa, new Coordenada(3, 1));
	    for(int i= 0; i<=7; i++) {
	        unJugador2.terminarTurno(juego);
	    }
	    Interactuable marine = new Marine(unJugador2);
	    mapa.ubicarElementoEnParcela(new Coordenada(1,1), marine);
	    
	    while(!marine.estaCreado()){
	    	unJugador2.terminarTurno(juego);
	    }

	    Ataque radiacion = new AtaqueRadiacion(marine.getParcela());
	     
	    while(marine.getVida() > 0)
	        radiacion.tareaDeEntreTurno(mapa);
	     
	    assertTrue(radiacion.esDescartable());
	}
	
	@Test
	public void danyaALosQueEstanARangoUnoDelAfectado() throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionNombreCorto, ExcepcionNoHaySuministrosDisponibles, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNoEsElTurnoDelJugador, ExcepcionUnidadParaDeMover {
		Juego juego = new Juego();  
		Jugador unJugador2 = new Jugador("Juan2", Color.VERDE, Terran.getInstance());
        unJugador2.sumarGasVespeno(999);
        unJugador2.sumarMinerales(999);
		juego.agregarJugador(unJugador2); 
		Mapa mapa = new Mapa(2, 5, 5);
	    unJugador2.crearAdicionalDeSuministro(mapa, new Coordenada(3, 1));
	    for(int i= 0; i<=7; i++){
	        unJugador2.terminarTurno(juego);
	    }

	    Interactuable marine = new Marine(unJugador2);
	    mapa.ubicarElementoEnParcela(new Coordenada(1,1), marine);
	    Interactuable marine2 = new Marine(unJugador2);
	    Interactuable marine3 = new Marine(unJugador2);
	    
	    while(!marine.estaCreado()){
	    	unJugador2.terminarTurno(juego);
	    }

	    Ataque radiacion = new AtaqueRadiacion(marine.getParcela());

	    mapa.ubicarElementoEnParcela(new Coordenada(2,1), marine2);
	    radiacion.tareaDeEntreTurno(mapa);

        assertEquals(marine2.getVida(), Marine.VIDA_INICIAL - AtaqueRadiacion.DANYO_POR_TURNO);

	    mapa.ubicarElementoEnParcela(new Coordenada(3,2), marine2);
	    mapa.ubicarElementoEnParcela(new Coordenada(1,2), marine3);
	    radiacion.tareaDeEntreTurno(mapa);
        assertEquals(marine2.getVida(), Marine.VIDA_INICIAL - 2*AtaqueRadiacion.DANYO_POR_TURNO);
        assertEquals(marine3.getVida(), Marine.VIDA_INICIAL - AtaqueRadiacion.DANYO_POR_TURNO);

	    radiacion.tareaDeEntreTurno(mapa);
        assertEquals(marine2.getVida(), 0);
        assertEquals(marine3.getVida(), Marine.VIDA_INICIAL - 2*AtaqueRadiacion.DANYO_POR_TURNO);
	}

	@Test
	public void danyaALosQueEstanARangoUnoDelAfectadoCuandoSeMueve() throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionNombreCorto, ExcepcionNoHaySuministrosDisponibles, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNoEsElTurnoDelJugador, ExcepcionUnidadParaDeMover {
		Juego juego = new Juego();  
		Jugador unJugador2 = new Jugador("Juan2", Color.VERDE, Terran.getInstance());
		juego.agregarJugador(unJugador2);
        unJugador2.sumarGasVespeno(999);
        unJugador2.sumarMinerales(999);
		Mapa mapa = new Mapa(2, 7, 7);
	    unJugador2.crearAdicionalDeSuministro(mapa, new Coordenada(3, 1));
	    for(int i= 0; i<=7; i++) {
	        unJugador2.terminarTurno(juego);
	    }
	    Interactuable marine = new Marine(unJugador2);
	     
	    Interactuable marine2 = new Marine(unJugador2);
	    Interactuable marine3 = new Marine(unJugador2);

	    mapa.ubicarElementoEnParcela(new Coordenada(6,6), marine);
	    mapa.ubicarElementoEnParcela(new Coordenada(1,1), marine3);
	    mapa.ubicarElementoEnParcela(new Coordenada(3,3), marine2);
	    
	    while(!marine.estaCreado()){
	    	unJugador2.terminarTurno(juego);
	    }

	    Ataque radiacion = new AtaqueRadiacion(marine.getParcela());

	    radiacion.tareaDeEntreTurno(mapa);
        assertEquals(marine2.getVida(), Marine.VIDA_INICIAL);
        assertEquals(marine3.getVida(), Marine.VIDA_INICIAL);

	    mapa.ubicarElementoEnParcela(new Coordenada(2,1), marine);
	    radiacion.tareaDeEntreTurno(mapa);
        assertEquals(marine2.getVida(), Marine.VIDA_INICIAL);

	    mapa.ubicarElementoEnParcela(new Coordenada(4,3), marine);
	    radiacion.tareaDeEntreTurno(mapa);

        assertEquals(marine2.getVida(), Marine.VIDA_INICIAL - AtaqueRadiacion.DANYO_POR_TURNO);
        assertEquals(marine3.getVida(), Marine.VIDA_INICIAL - AtaqueRadiacion.DANYO_POR_TURNO);
	}
}
