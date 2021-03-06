package fiuba.algo3.algocraft.modelo;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionConstruccionesRequeridasNoCreadas;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNombreCorto;
import fiuba.algo3.algocraft.modelo.juego.Color;
import fiuba.algo3.algocraft.modelo.juego.Jugador;
import fiuba.algo3.algocraft.modelo.razas.terran.Terran;
import fiuba.algo3.algocraft.modelo.razas.terran.construcciones.CentroDeMineral;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.ExtractorMineral;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.TipoDeConstruccion;

public class CentroDeMineralTest {
    @Test
    public void unCentroDeMineralRecolectaMineralesYSeLosSumaAlJugadorPropietario() throws ExcepcionNombreCorto {
        Jugador unJugadorPropietario = new Jugador("Juan", Color.ROJO, Terran.getInstance());
        ExtractorMineral centroDeMineral = new CentroDeMineral(unJugadorPropietario);

        centroDeMineral.recolectarRecursos();

        Assert.assertEquals(unJugadorPropietario.getMinerales(), Jugador.MINERAL_INICIAL + 10);
    }

    @Test
    public void obtenerTipoDeConstruccionDevuelveElTipoDeConstruccionCorrecto() throws ExcepcionConstruccionesRequeridasNoCreadas, ExcepcionNombreCorto {
        Jugador unJugadorPropietario = new Jugador("Juan", Color.ROJO, Terran.getInstance());
        ExtractorMineral centroDeMineral = new CentroDeMineral(unJugadorPropietario);

        Assert.assertEquals(centroDeMineral.getTipoDeConstruccion(), TipoDeConstruccion.EXTRACTOR_MINERAL);
    }
}
