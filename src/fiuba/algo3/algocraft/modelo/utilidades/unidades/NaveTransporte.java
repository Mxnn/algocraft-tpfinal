package fiuba.algo3.algocraft.modelo.utilidades.unidades;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.algocraft.modelo.excepciones.*;
import fiuba.algo3.algocraft.modelo.juego.Jugador;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.Parcela;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaEspacio;
import fiuba.algo3.algocraft.modelo.utilidades.Costo;
import fiuba.algo3.algocraft.modelo.utilidades.Vitalidad;
import fiuba.algo3.algocraft.vista.observadores.mapa.ObservadorMapa;

public abstract class NaveTransporte extends Unidad {
    protected final int RANGO_DE_INSERCION_DE_UNIDADES = 1;
    protected int lugaresOcupados;
    protected int capacidad;
    protected ArrayList<Unidad> unidades = new ArrayList<Unidad>();

    public NaveTransporte(Jugador propietario, Vitalidad vitalidad, int tiempoDeConstruccion, int cupoDeTransporte, int vision, int suministro, int capacidad, Costo costo) throws ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes {
        super(propietario, vitalidad, tiempoDeConstruccion, cupoDeTransporte, vision, suministro, costo);
        this.lugaresOcupados = 0;
        this.capacidad = capacidad;
    }

    public void insertarUnidad(Unidad unidad) throws ExcepcionNaveDeTransporteLlena, ExcepcionUnidadEnemiga, ExcepcionNoEsUnidadTerrestre, ExcepcionEntidadEnConstruccion, ExcepcionUnidadYaSeEncuentraEnLaNave, ExcepcionUnidadMuyLejos {
        if (!this.estaCreado())
            throw new ExcepcionEntidadEnConstruccion();

        if (!unidad.getPropietario().equals(this.propietario))
            throw new ExcepcionUnidadEnemiga();

        if(this.unidades.contains(unidad))
            throw new ExcepcionUnidadYaSeEncuentraEnLaNave();

        if (unidad.getCupoDeTransporte() == 0)
            throw new ExcepcionNoEsUnidadTerrestre();

        if (unidad.getParcela().devolverDistanciaConParcela(this.getParcela()) > this.RANGO_DE_INSERCION_DE_UNIDADES)
            throw new ExcepcionUnidadMuyLejos();

        int lugaresTotalesOcupadas = lugaresOcupados + unidad.getCupoDeTransporte();
        if (lugaresTotalesOcupadas <= capacidad) {
            lugaresOcupados = lugaresTotalesOcupadas;
            unidades.add(unidad);
            Parcela parcela = unidad.getParcela();
            if (parcela != null) //Fix medio triste
                parcela.vaciarParcela();
           
            for (ObservadorMapa observador: this.observadores)
                observador.removerInteractuableDeLaVista(unidad);
        }
        else
            throw new ExcepcionNaveDeTransporteLlena();
    }

    @Override
    public void destruir() {
        for (Unidad u : unidades) {
            u.destruir();
        }
        super.destruir();
    }

    public int cantidadDeUnidades() {
        return this.unidades.size();
    }

    @Override
    public void guardarEnParcela(ParcelaEspacio parcela) throws ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada {
    	parcela.setElemento(this);
    }

    @Override
    public int seleccionarDanyo(Danyo danyo){
    	return danyo.getDanyoDeAtaqueEnAire();
    }

    @Override
    public int seleccionarRango(RangoAtaque rango){
    	return rango.getRangoDeAtaqueEnAire();
    }

    @Override
    public void recibirAtaque(Ataque ataque) throws ExcepcionEstadoMuerto, ExcepcionEnemigoFueraDeAlcance{
		ataque.atacar(this);
	}

    public void sacarUnidad(Mapa mapa, Unidad unidadASacar) throws ExcepcionNoHayLugarDisponible, ExcepcionEntidadEnConstruccion {
        if (!this.estaCreado())
            throw new ExcepcionEntidadEnConstruccion();

        if (this.unidades.size() > 0) {
            if (this.unidades.indexOf(unidadASacar) != -1) {
                mapa.ubicarCercaDeParcela(this.parcelaUbicacion, unidadASacar);
                this.unidades.remove(unidadASacar);

                for (ObservadorMapa observador: this.observadores)
                    observador.crearUnidad(unidadASacar);
            }
        }
    }

    public List<Unidad> getUnidades() {
        return this.unidades;
    }
}
