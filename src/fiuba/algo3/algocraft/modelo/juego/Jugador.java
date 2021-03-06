package fiuba.algo3.algocraft.modelo.juego;

import java.util.ArrayList;
import java.util.Collection;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionConstruccionesRequeridasNoCreadas;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionCoordenadaFueraDelMapa;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionElementoNoAdmitidoEnParcela;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEstadoMuerto;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoEsElTurnoDelJugador;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoHaySuministrosDisponibles;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNombreCorto;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionParcelaOcupada;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionRecursosInsuficientes;
import fiuba.algo3.algocraft.modelo.mapa.Coordenada;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.razas.Raza;
import fiuba.algo3.algocraft.modelo.utilidades.Costo;
import fiuba.algo3.algocraft.modelo.utilidades.Interactuable;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.Construccion;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.ExtractorGas;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.ExtractorMineral;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.TipoDeConstruccion;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.AdicionalSuministros;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Ataque;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Unidad;
import fiuba.algo3.algocraft.vista.observadores.jugador.ObservadorJugador;
import fiuba.algo3.algocraft.vista.observadores.mapa.ObservadorMapa;

public class Jugador {
    public static int LONGITUD_MINIMA_PARA_EL_NOMBRE = 4;
    public static final int GAS_VESPENO_INICIAL = 0;
    public static final int MINERAL_INICIAL = 200;
    public static final int CAPACIDAD_DE_POBLACION_MAXIMA = 200;
    public static final int CAPACIDAD_DE_POBLACION_INICIAL = 0;
    public static final int POBLACION_INICIAL = 0;

    private String nombre;
    private Color color;
    private Raza raza;
    private ArrayList<Construccion> construcciones = new ArrayList<Construccion>();
    private ArrayList<Unidad> unidades = new ArrayList<Unidad>();
    private ArrayList<Ataque> ataques = new ArrayList<Ataque>();
    private int gasVespeno;
    private int mineral;

    private ObservadorJugador observadorJugador;
    private ObservadorMapa observadorMapa;
    
    public Jugador(String nombre, Color color, Raza raza) throws ExcepcionNombreCorto {
        if (nombre.length() < LONGITUD_MINIMA_PARA_EL_NOMBRE)
            throw new ExcepcionNombreCorto();

        this.nombre = nombre;
        this.color = color;
        this.raza = raza;
        this.gasVespeno = GAS_VESPENO_INICIAL;
        this.mineral = MINERAL_INICIAL;
    }

    public void setObservadores(ObservadorMapa observador){
    	this.raza.setObservadores(observador);
        this.observadorMapa = observador;
    }

    public void setObservadorJugador(ObservadorJugador observador) {
        this.observadorJugador = observador;
    }
    
    public String getNombre() {
        return this.nombre;
    }

    public Color getColor() {
        return this.color;
    }

	public Interactuable crearExtractorGas(Mapa mapa, Coordenada coordenada) throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada {
        ExtractorGas unExtractorGas = raza.crearExtractorGas(this);

        if (this.jugadorTieneRecursosNecesarios(unExtractorGas.getCosto())) {
            mapa.ubicarElementoEnParcela(coordenada, unExtractorGas);
            this.deducirCostos(unExtractorGas.getCosto());
            construcciones.add(unExtractorGas);
            this.raza.confirmarCreacionDeConstruccion();
        }
        else
            throw new ExcepcionRecursosInsuficientes();

        return unExtractorGas;
	}

	public Interactuable crearExtractorMineral(Mapa mapa, Coordenada coordenada) throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionParcelaOcupada {
		ExtractorMineral unExtractorMineral = raza.crearExtractorMineral(this);

        if (this.jugadorTieneRecursosNecesarios(unExtractorMineral.getCosto())) {
            mapa.ubicarElementoEnParcela(coordenada, unExtractorMineral);
            this.deducirCostos(unExtractorMineral.getCosto());
            construcciones.add(unExtractorMineral);
            this.raza.confirmarCreacionDeConstruccion();
        }
        else
            throw new ExcepcionRecursosInsuficientes();

        return unExtractorMineral;
	}

    public Interactuable crearAdicionalDeSuministro(Mapa mapa, Coordenada coordenada) throws ExcepcionRecursosInsuficientes, ExcepcionCoordenadaFueraDelMapa, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela {
        AdicionalSuministros adicionalSuministros = raza.crearAdicionalDeSuministros(this);

        if (this.jugadorTieneRecursosNecesarios(adicionalSuministros.getCosto())) {
            mapa.ubicarElementoEnParcela(coordenada, adicionalSuministros);
            this.deducirCostos(adicionalSuministros.getCosto());
            construcciones.add(adicionalSuministros);
            this.raza.confirmarCreacionDeConstruccion();
        }
        else
            throw new ExcepcionRecursosInsuficientes();

        return adicionalSuministros;
    }

    public Interactuable crearCreadorDeUnidadesBasicas(Mapa mapa, Coordenada coordenada) throws ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionCoordenadaFueraDelMapa {
        Construccion creadorDeUnidadesBasicas = raza.crearCreadorDeUnidadesBasicas(this);

        if (this.jugadorTieneRecursosNecesarios(creadorDeUnidadesBasicas.getCosto())) {
            mapa.ubicarElementoEnParcela(coordenada, creadorDeUnidadesBasicas);
            this.deducirCostos(creadorDeUnidadesBasicas.getCosto());
            construcciones.add(creadorDeUnidadesBasicas);
            this.raza.confirmarCreacionDeConstruccion();
        }
        else
            throw new ExcepcionRecursosInsuficientes();

        return creadorDeUnidadesBasicas;
    }

    public Interactuable crearCreadorDeUnidadesAvanzadas(Mapa mapa, Coordenada coordenada) throws ExcepcionConstruccionesRequeridasNoCreadas, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionCoordenadaFueraDelMapa {
        Construccion creadorDeUnidadesTerrestres = raza.crearCreadorDeUnidadesAvanzadas(this);

        if (this.jugadorTieneRecursosNecesarios(creadorDeUnidadesTerrestres.getCosto())) {
            mapa.ubicarElementoEnParcela(coordenada, creadorDeUnidadesTerrestres);
            this.deducirCostos(creadorDeUnidadesTerrestres.getCosto());
            construcciones.add(creadorDeUnidadesTerrestres);
            this.raza.confirmarCreacionDeConstruccion();
        }
        else
            throw new ExcepcionRecursosInsuficientes();

        return creadorDeUnidadesTerrestres;
    }

    public Interactuable crearCreadorDeUnidadesMagicas(Mapa mapa, Coordenada coordenada) throws ExcepcionConstruccionesRequeridasNoCreadas, ExcepcionRecursosInsuficientes, ExcepcionParcelaOcupada, ExcepcionElementoNoAdmitidoEnParcela, ExcepcionCoordenadaFueraDelMapa {
        Construccion creadorDeUnidadesAereas = raza.crearCreadorDeUnidadesMagicas(this);

        if (this.jugadorTieneRecursosNecesarios(creadorDeUnidadesAereas.getCosto())) {
            mapa.ubicarElementoEnParcela(coordenada, creadorDeUnidadesAereas);
            this.deducirCostos(creadorDeUnidadesAereas.getCosto());
            construcciones.add(creadorDeUnidadesAereas);
            this.raza.confirmarCreacionDeConstruccion();
        }
        else
            throw new ExcepcionRecursosInsuficientes();

        return creadorDeUnidadesAereas;
    }

    private boolean jugadorTieneRecursosNecesarios(Costo costo) {
        return((this.getGasVespeno() >= costo.getCostoGas()) && (this.getMinerales() >= costo.getCostoMineral()));
    }
	
	public int cantidadDeConstrucciones() {
        return construcciones.size();
	}

    public void sumarGasVespeno(int cantidad) {
        this.gasVespeno = this.gasVespeno + cantidad;
    }

    public int getGasVespeno() {
        return this.gasVespeno;
    }

    public void sumarMinerales(int cantidad) {
        this.mineral = this.mineral+ cantidad;
    }

    public int getMinerales() {
        return this.mineral;
    }

    public int getCapacidadDePoblacion() {
        int cantidad = 0;
        for (Construccion c: this.construcciones) {
        	if ( (c.getTipoDeConstruccion() == TipoDeConstruccion.ADICIONAL_SUMINISTROS) && c.estaCreado()){
        		if (c.getTipoDeConstruccion() == TipoDeConstruccion.ADICIONAL_SUMINISTROS)
        			cantidad += ( (AdicionalSuministros) c).getCantidadDeSuministrosAdicionales();
        	}
        }

        if (cantidad < CAPACIDAD_DE_POBLACION_INICIAL)
            return CAPACIDAD_DE_POBLACION_INICIAL;
        else if (cantidad > CAPACIDAD_DE_POBLACION_MAXIMA)
            return CAPACIDAD_DE_POBLACION_MAXIMA;
        else
            return cantidad;
    }

    public boolean tieneConstruccionDeTipo(TipoDeConstruccion tipo) {
        for (Construccion c : construcciones) {
            if (c.getTipoDeConstruccion() == tipo && c.estaCreado())
                return true;
        }

        return false;
    }

    public int getPoblacion() {
        int cantidad = 0;
        for (Unidad u: this.unidades) {
            cantidad += u.getSuministro();
        }

        return cantidad;
    }

    public void agregarUnidad(Unidad unidad) throws ExcepcionNoHaySuministrosDisponibles, ExcepcionRecursosInsuficientes {
        int total = this.getPoblacion() + unidad.getSuministro();
        if (total > this.getCapacidadDePoblacion())
            throw new ExcepcionNoHaySuministrosDisponibles();
        else {
            if (this.jugadorTieneRecursosNecesarios(unidad.getCosto())) {
                this.deducirCostos(unidad.getCosto());
                this.unidades.add(unidad);
                if (this.observadorJugador != null)
                    this.observadorJugador.actualizar();
            }
            else
                throw new ExcepcionRecursosInsuficientes();
        }
    }
    
    public void agregarAtaque(Ataque ataque){
    	this.ataques.add(ataque);
    }

    public int getCantidadDeUnidades() {
        return this.unidades.size();
    }

    public void eliminarUnidad(Unidad unidad) {
        (this.unidades).remove(unidad);
        if (this.observadorMapa != null)
            this.observadorMapa.removerInteractuableDeLaVista(unidad);
    }

    public void eliminarConstruccion(Construccion construccion) {
        (this.construcciones).remove(construccion);
        if (this.observadorMapa != null)
            this.observadorMapa.removerInteractuableDeLaVista(construccion);
    }

    public Collection<Construccion> getConstrucciones() {
        return this.construcciones;
    }

    public Collection<Unidad> getUnidades() {
        return this.unidades;
    }

    public Collection<Ataque> getAtaques() {
        return this.ataques;
    }

    public void terminarTurno(Juego juego) throws ExcepcionNoEsElTurnoDelJugador, ExcepcionEstadoMuerto, ExcepcionCoordenadaFueraDelMapa {
    	juego.pasarTurno(this);
    }
    
    public boolean esPerdedor(){
    	boolean tieneConstruccionMineral = false;
        for (Construccion c : construcciones) {
            if (c.getTipoDeConstruccion() == TipoDeConstruccion.EXTRACTOR_MINERAL)
            	tieneConstruccionMineral = true;
        }
    	

    	return(this.mineral<=100 && !tieneConstruccionMineral);
    }

    private void deducirCostos(Costo costo) {
        this.mineral -= costo.getCostoMineral();
        this.gasVespeno -= costo.getCostoGas();
        if (this.observadorJugador != null)
            this.observadorJugador.actualizar();
    }
}
