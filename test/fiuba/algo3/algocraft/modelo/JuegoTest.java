package fiuba.algo3.algocraft.modelo;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionAlcanzadoElMaximoCupoDeJugadores;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionColorEnUso;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionCoordenadaFueraDelMapa;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionElementoNoAdmitidoEnParcela;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEnemigoFueraDeAlcance;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEntidadEnConstruccion;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEstadoMuerto;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoEsElTurnoDelJugador;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoHayLugarDisponible;
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
import fiuba.algo3.algocraft.modelo.juego.SistemaDeTurnos;
import fiuba.algo3.algocraft.modelo.mapa.Coordenada;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.razas.protoss.Protoss;
import fiuba.algo3.algocraft.modelo.razas.terran.Terran;
import fiuba.algo3.algocraft.modelo.razas.terran.construcciones.Barraca;
import fiuba.algo3.algocraft.modelo.razas.terran.construcciones.DepositoSuministro;
import fiuba.algo3.algocraft.modelo.razas.terran.unidades.Marine;

public class JuegoTest {
    @Test
    public void crearJugadorConNombreAgregaJugadorAlJuego() throws ExcepcionColorEnUso, ExcepcionNombreEnUso, ExcepcionNombreCorto, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNumeroDeBasesInvalido {
        Juego juego = new Juego();

        juego.crearJugador("Juan", Color.ROJO, Terran.getInstance());
        juego.crearJugador("Pedro", Color.VERDE, Protoss.getInstance());

        Assert.assertEquals(juego.getCantidadDeJugadores(), 2);
    }

    @Test(expected = ExcepcionNombreEnUso.class)
    public void crearJugadoresConNombresIgualesLanzaExcepcionNombreEnUso() throws ExcepcionColorEnUso, ExcepcionNombreEnUso, ExcepcionNombreCorto, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNumeroDeBasesInvalido {
        Juego juego = new Juego();

        juego.crearJugador("Juan", Color.ROJO, Terran.getInstance());
        juego.crearJugador("Juan", Color.VERDE, Protoss.getInstance());
    }

    @Test(expected = ExcepcionColorEnUso.class)
    public void crearJugadoresConColoresIgualesLanzaExcepcionColorEnUso() throws ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNombreCorto, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNumeroDeBasesInvalido {
        Juego juego = new Juego();

        juego.crearJugador("Juan", Color.ROJO, Terran.getInstance());
        juego.crearJugador("Pedro", Color.ROJO, Protoss.getInstance());
    }

    @Test(expected = ExcepcionAlcanzadoElMaximoCupoDeJugadores.class)
    public void crearTresJugadoresLanzaExcepcion() throws ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNombreCorto, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNumeroDeBasesInvalido {
        Juego juego = new Juego();

        juego.crearJugador("Juan", Color.ROJO, Terran.getInstance());
        juego.crearJugador("Pablo", Color.AZUL, Terran.getInstance());
        juego.crearJugador("Carlos", Color.VERDE, Terran.getInstance());
    }

    
 
    
    @Test
    public void cadaTurnoSeMueveLasUnidadesQueDebenMover() throws ExcepcionNumeroDeBasesInvalido, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreCorto, ExcepcionCoordenadaFueraDelMapa, ExcepcionNoHaySuministrosDisponibles, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada, ExcepcionNoEsElTurnoDelJugador, ExcepcionRecursosInsuficientes, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionNoHayLugarDisponible, ExcepcionEntidadEnConstruccion, ExcepcionUnidadParaDeMover{
    	Juego elJuego= new Juego();
    	elJuego.setMapaParaTests();
        elJuego.crearJugador("Juan", Color.ROJO, Terran.getInstance());
    	Jugador jugadorUno = elJuego.getJugadores().get(0);
 
        jugadorUno.sumarMinerales(JugadorTest.RECURSOS_SUFFICIENTES);
        jugadorUno.sumarGasVespeno(JugadorTest.RECURSOS_SUFFICIENTES);
        
    	Coordenada coordPartida;
    	Coordenada coordDeDestinacion = new Coordenada (2,5);
        Mapa mapa=elJuego.getMapa();
        SistemaDeTurnos sistema = new SistemaDeTurnos(elJuego.getJugadores(),mapa);
        DepositoSuministro adicional = (DepositoSuministro) jugadorUno.crearAdicionalDeSuministro(mapa, new Coordenada(1,1));
    	
        while (!adicional.estaCreado())
        	jugadorUno.terminarTurno(elJuego);
        
        Barraca barraca = (Barraca) jugadorUno.crearCreadorDeUnidadesBasicas(mapa, new Coordenada(3,3));
        
        while (!barraca.estaCreado())
        	jugadorUno.terminarTurno(elJuego);
    
        Marine marine = barraca.crearMarine(mapa);
        
        coordPartida = marine.getParcela().getCoordenada();

      //  mapa.obtenerParcelaEnCoordenada(coordPartida).guardarElemento(marine);

        while (!marine.estaCreado())
        	jugadorUno.terminarTurno(elJuego);

        marine.moverHasta(coordDeDestinacion);

        sistema.pasarTurno(jugadorUno);    
        sistema.pasarTurno(jugadorUno);    
        sistema.pasarTurno(jugadorUno);    

        Assert.assertTrue(marine.getParcela().getCoordenada()!=coordPartida);
    }
}
