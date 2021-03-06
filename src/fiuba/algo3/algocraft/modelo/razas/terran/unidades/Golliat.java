package fiuba.algo3.algocraft.modelo.razas.terran.unidades;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoHaySuministrosDisponibles;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionRecursosInsuficientes;
import fiuba.algo3.algocraft.modelo.juego.Jugador;
import fiuba.algo3.algocraft.modelo.utilidades.Costo;
import fiuba.algo3.algocraft.modelo.utilidades.Vitalidad;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Danyo;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.RangoAtaque;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.UnidadAgresora;

public class Golliat extends UnidadAgresora {
    public static int COSTO_MINERAL = 100;
    public static int COSTO_GAS = 50;
    public static final int VIDA_INICIAL = 125;
    public static final int VISION = 8;
    public static final int TIEMPO_DE_CONSTRUCCION = 6;
    public static final int CUPO_DE_TRANSPORTE = 2;
    public static final int SUMINISTRO = 2;
    public static final int DANYO_AIRE = 10;
    public static final int DANYO_TIERRA = 12;
    public static final int RANGO_AIRE = 5;
    public static final int RANGO_TIERRA = 6;

    public Golliat(Jugador propietario) throws ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes {
        super(propietario, new Vitalidad(VIDA_INICIAL), TIEMPO_DE_CONSTRUCCION, CUPO_DE_TRANSPORTE, VISION, SUMINISTRO, new RangoAtaque(RANGO_AIRE, RANGO_TIERRA), new Danyo(DANYO_AIRE, DANYO_TIERRA), new Costo(COSTO_MINERAL, COSTO_GAS));
    }
}