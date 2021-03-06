package fiuba.algo3.algocraft.modelo.razas.terran.construcciones;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEntidadEnConstruccion;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoHayLugarDisponible;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoHaySuministrosDisponibles;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionRecursosInsuficientes;
import fiuba.algo3.algocraft.modelo.juego.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.razas.terran.unidades.Marine;
import fiuba.algo3.algocraft.modelo.utilidades.Costo;
import fiuba.algo3.algocraft.modelo.utilidades.Vitalidad;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.Construccion;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.TipoDeConstruccion;
import fiuba.algo3.algocraft.vista.observadores.mapa.ObservadorMapa;

public class Barraca extends Construccion {
    private static final int TIEMPO_DE_CONSTRUCCION = 12;
    public static final int VIDA_INICIAL = 1000;
    public static int COSTO_MINERAL = 150;
    public static int COSTO_GAS = 0;
    public static Costo COSTO = new Costo(COSTO_MINERAL, COSTO_GAS);
    
	public Barraca(Jugador propietario) {
        super(propietario, new Vitalidad(VIDA_INICIAL), TIEMPO_DE_CONSTRUCCION, COSTO);
    }

    @Override
    public TipoDeConstruccion getTipoDeConstruccion() {
        return TipoDeConstruccion.CREADOR_DE_UNIDADES_BASICAS;
    }

    public Marine crearMarine(Mapa mapa) throws ExcepcionNoHaySuministrosDisponibles, ExcepcionNoHayLugarDisponible, ExcepcionEntidadEnConstruccion, ExcepcionRecursosInsuficientes {
        if (!this.estaCreado())
            throw new ExcepcionEntidadEnConstruccion();

        Marine marine = new Marine(this.propietario);
        mapa.ubicarCercaDeParcela(parcelaUbicacion, marine);

        for (ObservadorMapa observador: this.observadores) {
    		observador.crearUnidad(marine);
    		marine.setObservador(observador);
    	}

        return marine;
    }
}
