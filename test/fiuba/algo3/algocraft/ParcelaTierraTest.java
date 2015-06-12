package fiuba.algo3.algocraft;

import fiuba.algo3.algocraft.Excepciones.*;
import fiuba.algo3.algocraft.RazaTerran.*;
import fiuba.algo3.algocraft.terran.construcciones.Barraca;
import fiuba.algo3.algocraft.terran.construcciones.CentroDeMineral;

import org.junit.Assert;
import org.junit.Test;

public class ParcelaTierraTest {


	@Test
    public void parcelaEmpiezaVacia(){
        Parcela parcela = new ParcelaTierra(new Coordenada(0,0));

        Assert.assertTrue(parcela.estaVacia());
    }

    @Test
    public void devolverElementoDevuelveInteractuableGuardado() throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionCoordenadaFueraDelMapa, ExcepcionNumeroDeBasesInvalido {
        Mapa mapa = new Mapa(2, 5, 5);
        Coordenada coordenada = new Coordenada(3, 3);
        Jugador unJugador = new Jugador("Juan", Color.ROJO, Terran.getInstance());
        unJugador.crearAdicionalDeSuministro(mapa, coordenada);
        Parcela parcela = new ParcelaTierra(new Coordenada(0,0));
        Interactuable marine = new Marine(unJugador);
        parcela.guardarElemento(marine);

        Assert.assertSame(marine, parcela.devolverElemento());
    }

    @Test
    public void estaVaciaDevuelveFalseCuandoEstaOcupada() throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa {
        Mapa mapa = new Mapa(2, 5, 5);
        Coordenada coordenada = new Coordenada(3, 3);
        Jugador unJugador = new Jugador("Juan", Color.ROJO, Terran.getInstance());
        Parcela parcela = new ParcelaTierra(new Coordenada(0,0));
        unJugador.crearAdicionalDeSuministro(mapa, coordenada);
        Interactuable marine = new Marine(unJugador);
        parcela.guardarElemento(marine);

        Assert.assertFalse(parcela.estaVacia());
    }

    @Test(expected = ExcepcionParcelaOcupada.class)
    public void guardarElementoSobreUnaParcelaYaOcupdaLanzaExcepcion() throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionCoordenadaFueraDelMapa, ExcepcionNumeroDeBasesInvalido {
        Mapa mapa = new Mapa(2, 5, 5);
        Coordenada coordenada = new Coordenada(3, 3);
        Jugador unJugador = new Jugador("Juan", Color.ROJO, Terran.getInstance());
        unJugador.crearAdicionalDeSuministro(mapa, coordenada);
        Parcela parcela = new ParcelaTierra(new Coordenada(0,0));
        Interactuable marine = new Marine(unJugador);
        Interactuable golliat = new Golliat(unJugador);

        parcela.guardarElemento(marine);
        parcela.guardarElemento(golliat);
    }
    
    @Test
    public void parcelaGuardaUnidadesVoladoras() throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa {
        Mapa mapa = new Mapa(2, 5, 5);
        Coordenada coordenada = new Coordenada(3, 3);
        Jugador unJugador = new Jugador("Juan", Color.ROJO, Terran.getInstance());
        unJugador.crearAdicionalDeSuministro(mapa, coordenada);
    	Parcela parcela = new ParcelaTierra(new Coordenada(0,0));
    	Interactuable espectro = new Espectro(unJugador);

    	parcela.guardarElemento(espectro);

    	Assert.assertSame(espectro, parcela.devolverElemento());
    }
    
    @Test
    public void parcelaGuardaConstruccion() throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionNumeroDeBasesInvalido, ExcepcionCoordenadaFueraDelMapa {
        Mapa mapa = new Mapa(2, 5, 5);
        Coordenada coordenada = new Coordenada(3, 3);
        Parcela parcela = new ParcelaTierra(new Coordenada(0,0));
        Jugador unJugador = new Jugador("Juan", Color.AZUL, Terran.getInstance());
        unJugador.crearAdicionalDeSuministro(mapa, coordenada);
    	Interactuable construccion = new Barraca(unJugador);

    	parcela.guardarElemento(construccion);

    	Assert.assertSame(construccion, parcela.devolverElemento());
    }
    
    @Test(expected = ExcepcionElementoNoAdmitidoEnParcela.class)
    public void guardarElementoLanzaExcepcionSiElElementoEsUnExtractor() throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada {
        Parcela parcela = new ParcelaTierra(new Coordenada(0,0));
        Jugador unJugador = new Jugador("Juan", Color.AZUL, Terran.getInstance());
        Interactuable construccion = new CentroDeMineral(unJugador);

        parcela.guardarElemento(construccion);
    }
}










