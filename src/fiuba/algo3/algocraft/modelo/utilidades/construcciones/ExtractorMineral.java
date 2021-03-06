package fiuba.algo3.algocraft.modelo.utilidades.construcciones;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionElementoNoAdmitidoEnParcela;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionParcelaOcupada;
import fiuba.algo3.algocraft.modelo.juego.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaMineral;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaTierra;
import fiuba.algo3.algocraft.modelo.utilidades.Costo;
import fiuba.algo3.algocraft.modelo.utilidades.Vitalidad;

public abstract class ExtractorMineral extends Construccion {
    public static final int CANTIDAD_DE_MINERALES_RECOLECTADOS_POR_TURNO = 10;

    public ExtractorMineral(Jugador propietario, Vitalidad vitalidad, int tiempoDeConstruccion, Costo costo) {
        super(propietario, vitalidad, tiempoDeConstruccion, costo);
    }

    public void recolectarRecursos() {
        (this.propietario).sumarMinerales(CANTIDAD_DE_MINERALES_RECOLECTADOS_POR_TURNO);
    }

    @Override
    public void guardarEnParcela(ParcelaTierra parcela) throws ExcepcionElementoNoAdmitidoEnParcela {
    	throw new ExcepcionElementoNoAdmitidoEnParcela();
    }

    @Override
    public void guardarEnParcela(ParcelaMineral parcela) throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada {
    	parcela.setElemento(this);
    }

    @Override
    public void vivir(Mapa mapa) {
        this.recolectarRecursos();
    }
    
}
