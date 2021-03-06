package fiuba.algo3.algocraft.controlador.creadoresDeInteractuables;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoHayLugarDisponible;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionNoHaySuministrosDisponibles;
import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionRecursosInsuficientes;
import fiuba.algo3.algocraft.modelo.juego.Juego;
import fiuba.algo3.algocraft.modelo.razas.terran.construcciones.PuertoEstelar;

import java.awt.event.ActionEvent;

public class CrearNaveTransporteListener extends CreadorDeUnidadListener {

	public CrearNaveTransporteListener(Juego modelo, PuertoEstelar representado){
		super(modelo, representado);
	}

	public void actionPerformed(ActionEvent arg0) {
		try {
            ((PuertoEstelar) (this.construccion)).crearNaveTransporte(modelo.getMapa());
        } catch (ExcepcionNoHaySuministrosDisponibles e) {
            this.mostrarError(e.getMessage());
        } catch (ExcepcionNoHayLugarDisponible e) {
            this.mostrarError(e.getMessage());
        } catch (ExcepcionRecursosInsuficientes e) {
            this.mostrarError(e.getMessage());
        }
    }
}
