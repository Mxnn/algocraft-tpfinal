package fiuba.algo3.algocraft.RazaTerran;

import fiuba.algo3.algocraft.Excepciones.ExcepcionNoHaySuministrosDisponibles;
import fiuba.algo3.algocraft.Jugador;
import fiuba.algo3.algocraft.RangoAtaque;
import fiuba.algo3.algocraft.UnidadTerrestre;

public class Golliat extends UnidadTerrestre {
    public static final int SUMINISTRO = 2;
    public static final int VIDA_INICIAL = 125;
    public static final int VISION = 8;
    public static final int CUPO_TRANSPORTE = 2;
    public static final int TIEMPO_DE_CONSTRUCCION = 6;

    public Golliat(Jugador propietario) throws ExcepcionNoHaySuministrosDisponibles {
        propietario.incrementarPoblacion(SUMINISTRO);
        this.propietario = propietario;
        this.vida = VIDA_INICIAL;
        /*this.danyo = new Danyo(10, 12);
        this.costo = new Costo(100, 50);*/
        this.vision = VISION;
        this.cupoDeTransporte = CUPO_TRANSPORTE;
        this.rangoAtaque = new RangoAtaque(5, 6);
        this.tiempoDeConstruccion = TIEMPO_DE_CONSTRUCCION;
    }
}