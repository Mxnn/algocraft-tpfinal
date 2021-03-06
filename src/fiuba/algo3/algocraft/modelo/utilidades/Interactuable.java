package fiuba.algo3.algocraft.modelo.utilidades;

import fiuba.algo3.algocraft.modelo.excepciones.*;
import fiuba.algo3.algocraft.modelo.juego.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Parcela;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaEspacio;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaMineral;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaTierra;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaVolcan;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Ataque;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Danyo;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.RangoAtaque;

public abstract class Interactuable {
    
	protected Jugador propietario;
	protected Vitalidad vitalidad;
	protected int tiempoDeConstruccion;
	protected Parcela parcelaUbicacion;
    protected Costo costo;

    public Interactuable (Jugador propietario, Vitalidad vitalidad, int tiempoDeConstruccion, Costo costo) {
        this.propietario = propietario;
        this.vitalidad = vitalidad;
        this.tiempoDeConstruccion = tiempoDeConstruccion;
        this.costo = costo;
    }

    public void recibirDanyo(int cantidad) throws ExcepcionEstadoMuerto {
        try {
            (this.vitalidad).recibirDanyo(cantidad);
        }
        catch (ExcepcionEstadoMuerto e) {
            this.destruir();
        }
    }
    
    public abstract void recibirAtaque(Ataque ataque) throws ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance;

    public abstract int seleccionarDanyo(Danyo danyo);
    
    public abstract int seleccionarRango(RangoAtaque rango);

    public void setParcela(Parcela parcela){
    	this.parcelaUbicacion = parcela;
    }
    
    public Parcela getParcela(){
    	return this.parcelaUbicacion;
    }
    
    public int getVida() {
    	return vitalidad.getVida();
    }
    
    public Vitalidad getVitalidad(){
    	return this.vitalidad;
    }

    public abstract void destruir();
    
    public abstract void guardarEnParcela(ParcelaEspacio parcela) throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada;

    public abstract void guardarEnParcela(ParcelaMineral parcela) throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada;

    public abstract void guardarEnParcela(ParcelaVolcan parcela) throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada;

    public abstract void guardarEnParcela(ParcelaTierra parcela) throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada;

    public void tareaDeEntreTurno(Mapa mapa) throws ExcepcionUnidadParaDeMover {
        if (this.estaCreado())
            this.vivir(mapa);
        else
            this.tiempoDeConstruccion--;
    }

    public boolean estaCreado() {
        return (this.tiempoDeConstruccion == 0);
    }

    public void vivir(Mapa mapa) throws ExcepcionUnidadParaDeMover {
        (this.vitalidad).regenerar();
    }

    public Jugador getPropietario() {
        return this.propietario;
    }

    public Costo getCosto() {
        return this.costo;
    }
}
