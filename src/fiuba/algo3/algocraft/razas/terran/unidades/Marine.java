package fiuba.algo3.algocraft.razas.terran.unidades;

import fiuba.algo3.algocraft.excepciones.ExcepcionNoHaySuministrosDisponibles;
import fiuba.algo3.algocraft.juego.Jugador;
import fiuba.algo3.algocraft.mapa.Parcela;
import fiuba.algo3.algocraft.mapa.ParcelaTierra;
import fiuba.algo3.algocraft.utilidades.Costo;
import fiuba.algo3.algocraft.utilidades.Vitalidad;
import fiuba.algo3.algocraft.utilidades.unidades.Danyo;
import fiuba.algo3.algocraft.utilidades.unidades.RangoAtaque;
import fiuba.algo3.algocraft.utilidades.unidades.UnidadAgresora;

public class Marine extends UnidadAgresora {
    public static int COSTO_MINERAL = 50;
    public static int COSTO_GAS = 0;
    public static Costo COSTO = new Costo(COSTO_MINERAL, COSTO_GAS);

    public static final int VIDA_INICIAL = 40;
    public static final int VISION = 7;
    public static final int TIEMPO_DE_CONSTRUCCION = 3;
    public static final int CUPO_DE_TRANSPORTE = 1;
    public static final int SUMINISTRO = 1;
    public static final int DANYO_AIRE = 6;
    public static final int DANYO_TIERRA = 6;
    public static final int RANGO_AIRE = 4;
    public static final int RANGO_TIERRA = 4;

    public Marine(Jugador propietario) throws ExcepcionNoHaySuministrosDisponibles {
        super(propietario, new Vitalidad(VIDA_INICIAL), TIEMPO_DE_CONSTRUCCION, CUPO_DE_TRANSPORTE, VISION, SUMINISTRO, new RangoAtaque(RANGO_AIRE, RANGO_TIERRA), new Danyo(DANYO_AIRE, DANYO_TIERRA));
    }
    
	public boolean destinacionPermitida(Parcela parcelaDestinacion) {

		return (parcelaDestinacion.getClass() == ParcelaTierra.class);
	}

}