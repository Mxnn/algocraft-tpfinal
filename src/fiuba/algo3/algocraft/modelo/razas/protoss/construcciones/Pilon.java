package fiuba.algo3.algocraft.modelo.razas.protoss.construcciones;

import fiuba.algo3.algocraft.modelo.juego.Jugador;
import fiuba.algo3.algocraft.modelo.utilidades.Costo;
import fiuba.algo3.algocraft.modelo.utilidades.VitalidadProtoss;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.TipoDeConstruccion;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.AdicionalSuministros;

public class Pilon extends AdicionalSuministros {

    public static final int SUMINISTROS_ADICIONALES = 5;
    public static final int TIEMPO_DE_CONSTRUCCION = 6;
    public static final int VIDA_INICIAL = 300;
    public static final int ESCUDO_INICIAL = 300;
    public static int COSTO_MINERAL = 100;
    public static int COSTO_GAS = 0;
    public static Costo COSTO = new Costo(COSTO_MINERAL, COSTO_GAS);
    
    public Pilon(Jugador propietario) {
        super(propietario, new VitalidadProtoss(VIDA_INICIAL, ESCUDO_INICIAL), TIEMPO_DE_CONSTRUCCION, SUMINISTROS_ADICIONALES, COSTO);
    }

    @Override
    public TipoDeConstruccion getTipoDeConstruccion() {
        return TipoDeConstruccion.ADICIONAL_SUMINISTROS;
    }
}

