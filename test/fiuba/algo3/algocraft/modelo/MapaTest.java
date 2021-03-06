package fiuba.algo3.algocraft.modelo;

import java.util.ArrayList;

import org.junit.Assert;
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
import fiuba.algo3.algocraft.modelo.mapa.Parcela;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaVolcan;
import fiuba.algo3.algocraft.modelo.razas.protoss.Protoss;
import fiuba.algo3.algocraft.modelo.razas.terran.Terran;
import fiuba.algo3.algocraft.modelo.razas.terran.unidades.Espectro;
import fiuba.algo3.algocraft.modelo.razas.terran.unidades.Golliat;
import fiuba.algo3.algocraft.modelo.razas.terran.unidades.Marine;
import fiuba.algo3.algocraft.modelo.utilidades.Interactuable;

public class MapaTest {

	@Test
	public void devolverElementoEnParcelaDevuelveElementoGuardado() throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa, ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionNombreCorto, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNoEsElTurnoDelJugador, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionUnidadParaDeMover {
		Mapa mapa = new Mapa(2, 5, 5);
        Coordenada coordenada = new Coordenada(3, 3);
        Juego juego = new Juego();
		Jugador unJugador = new Jugador("Juan", Color.ROJO, Protoss.getInstance());
		juego.agregarJugador(unJugador);         
		unJugador.crearAdicionalDeSuministro(mapa, coordenada);
		for(int i= 0; i<=7; i++){
        	unJugador.terminarTurno(juego);
        }
		Interactuable marine = new Marine(unJugador);

		mapa.ubicarElementoEnParcela(new Coordenada(0,0), marine);

		Assert.assertSame(marine, mapa.devolverElementoEnParcela(new Coordenada(0, 0)));
	}

    @Test
    public void ubicarGolliatEnParcelaLanzaExcepcionSiElElementoNoEsAdmitidoEnLaParcela() throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa, ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionNombreCorto, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNoEsElTurnoDelJugador, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionUnidadParaDeMover {
    	Juego juego = new Juego();
		Jugador unJugador = new Jugador("Juan", Color.ROJO, Protoss.getInstance());
		juego.agregarJugador(unJugador);
        unJugador.sumarGasVespeno(999);
        unJugador.sumarMinerales(999);

        Mapa mapa = new Mapa(2, 5, 5);
        unJugador.crearAdicionalDeSuministro(mapa, new Coordenada(3, 3));
        for(int i= 0; i<=7; i++){
        	unJugador.terminarTurno(juego);
        }
        Interactuable golliat = new Golliat(unJugador);
        Coordenada coordenada = new Coordenada(0,0);

        mapa.ubicarElementoEnParcela(coordenada, golliat);
    }

    @Test
    public void ubicarUnidadVoladoraEnParcelaEspacioLaUbica() throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa, ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionNombreCorto, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNoEsElTurnoDelJugador, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionUnidadParaDeMover {
    	Juego juego = new Juego();
		Jugador unJugador = new Jugador("Juan", Color.ROJO, Protoss.getInstance());
		juego.agregarJugador(unJugador);
        unJugador.sumarGasVespeno(999);
        unJugador.sumarMinerales(999);
		Mapa mapa = new Mapa(2, 5, 5);
        unJugador.crearAdicionalDeSuministro(mapa, new Coordenada(3, 3));
        for(int i= 0; i<=7; i++){
        	unJugador.terminarTurno(juego);
        }
        Interactuable espectro = new Espectro(unJugador);
        Coordenada coordenada = new Coordenada(0,0);

        mapa.ubicarElementoEnParcela(coordenada, espectro);

        Assert.assertSame(espectro, mapa.devolverElementoEnParcela(coordenada));
    }

    @Test
    public void elMapaEmpiezaConParcelasVacias() throws ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa {
        int alturaDelMapa = 10;
        int anchoDelMapa = 10;
        Mapa mapa = new Mapa(2, alturaDelMapa, anchoDelMapa);

        for (int i = 0; i < alturaDelMapa; i++) {
            for (int j = 0; j < anchoDelMapa; j++) {
                Assert.assertNull(mapa.devolverElementoEnParcela(new Coordenada(i, j)));
            }
        }
    }
    
    @Test
    public void elMapaDaCoordenadasAParcelas() throws ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa {
        int alturaDelMapa = 10;
        int anchoDelMapa = 10;
        Mapa mapa = new Mapa(2, alturaDelMapa, anchoDelMapa);

        for (int i = 0; i < alturaDelMapa; i++) {
            for (int j = 0; j < anchoDelMapa; j++) {
            	Parcela parcela = mapa.obtenerParcelaEnCoordenada(new Coordenada(i, j));
                Assert.assertEquals(parcela.getCoordenada(),new Coordenada(i, j) );
            }
        }
    }

    @Test(expected = ExcepcionCoordenadaFueraDelMapa.class)
    public void consultarElElementoEnUnaCoordenadaFueraDelMapaLanzaExcepcion() throws ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa {
        Mapa mapa = new Mapa(2, 10, 10);

        Assert.assertNull(mapa.devolverElementoEnParcela(new Coordenada(11, 10)));
    }

    @Test
    public void obtenerParcelaEnCoordenadaDevuelveLaParcelaCorrecta() throws ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa, ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada, ExcepcionNombreCorto, ExcepcionAlcanzadoElMaximoCupoDeJugadores, ExcepcionNombreEnUso, ExcepcionColorEnUso, ExcepcionNoEsElTurnoDelJugador, ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance, ExcepcionUnidadParaDeMover {
    	Juego juego = new Juego();
		Jugador unJugador = new Jugador("Juan", Color.ROJO, Terran.getInstance());
		
		juego.agregarJugador(unJugador);
        unJugador.sumarGasVespeno(999);
        unJugador.sumarMinerales(999);
        Mapa mapa = new Mapa(2, 10, 10);
        Coordenada coordenada = new Coordenada(5, 5);

        unJugador.crearAdicionalDeSuministro(mapa, new Coordenada(2, 2));
        for(int i= 0; i<=7; i++){
        	unJugador.terminarTurno(juego);
        }
        Interactuable golliat = new Golliat(unJugador);
        mapa.ubicarElementoEnParcela(coordenada, golliat);

        Assert.assertEquals(mapa.obtenerParcelaEnCoordenada(coordenada).devolverElemento(), golliat);
    }

    @Test(expected = ExcepcionCoordenadaFueraDelMapa.class)
    public void obtenerParcelaEnCoordenadaLanzaExcepcionSiLaParcelaEsInvalida() throws ExcepcionCoordenadaFueraDelMapa, ExcepcionNumeroDeBasesInvalido {
        Mapa mapa = new Mapa(2, 10, 10);
        Coordenada coordenada = new Coordenada(11, 5);

        mapa.obtenerParcelaEnCoordenada(coordenada);
    }
    


    @Test
    public void insertarParcelaInsertaParcelaEnElMapa() throws ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa {
        Mapa mapa = new Mapa(2, 20, 20);
        ParcelaVolcan parcela = new ParcelaVolcan(new Coordenada(1, 1));

        mapa.insertarParcela(parcela);

        Assert.assertSame(mapa.obtenerParcelaEnCoordenada(new Coordenada(1, 1)), parcela);
    }
    
    @Test
    public void devolverParcelasEnRadioUnoDevuelveParcelasCorrectas() throws ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa{
    	Mapa mapa = new Mapa(2, 3, 3);
    	
    	ArrayList<Parcela> parcela = mapa.devolverParcelasEnRadioUno(mapa.obtenerParcelaEnCoordenada(new Coordenada(1,1)));
    	ArrayList<Parcela> esperadas = new ArrayList<Parcela>();
    	for (int x = 0; x < 3; x++){
    		for(int y = 0; y<3; y++){
    			esperadas.add(mapa.obtenerParcelaEnCoordenada(new Coordenada(x,y)));
    		}
    	}
    	
    	Assert.assertTrue(parcela.containsAll(esperadas) && esperadas.containsAll(parcela));
    }
    
    @Test
    public void devolverParcelasEnRadioUnoDevuelveParcelasDelMapaEnCasoDeSerBorde() throws ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa{
    	Mapa mapa = new Mapa(2, 3, 3);
    	
    	ArrayList<Parcela> parcela = mapa.devolverParcelasEnRadioUno(mapa.obtenerParcelaEnCoordenada(new Coordenada(2,2)));
    	ArrayList<Parcela> esperadas = new ArrayList<Parcela>();
    	for (int x = 1; x < 3; x++){
    		for(int y = 1; y<3; y++){
    			esperadas.add(mapa.obtenerParcelaEnCoordenada(new Coordenada(x,y)));
    		}
    	}
    	Assert.assertTrue(parcela.containsAll(esperadas) && esperadas.containsAll(parcela));
    }
}
