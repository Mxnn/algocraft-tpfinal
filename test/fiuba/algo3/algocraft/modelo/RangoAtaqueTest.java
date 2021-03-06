package fiuba.algo3.algocraft.modelo;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.algocraft.modelo.utilidades.unidades.RangoAtaque;

public class RangoAtaqueTest {
    @Test
    public void obtenerRangoDeAtaqueAlAireDevuelveElRangoDeAtaqueAlAire() {
        RangoAtaque rango = new RangoAtaque(5, 6);

        Assert.assertEquals(rango.getRangoDeAtaqueEnAire(), 5);
    }

    @Test
    public void obtenerRangoDeAtaqueEnTierraDevuelveElRangoDeAtaqueEnTierra() {
        RangoAtaque rango = new RangoAtaque(5, 6);

        Assert.assertEquals(rango.getRangoDeAtaqueEnTierra(), 6);
    }
}
