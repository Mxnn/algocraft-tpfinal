package fiuba.algo3.algocraft.controlador.accionesDeUnidades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fiuba.algo3.algocraft.controlador.comandos.AccionPorTurnoListener;
import fiuba.algo3.algocraft.controlador.comandos.ComandoAtaqueComun;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Unidad;

public class AtacarListener implements ActionListener{
    private Unidad representado;
	private AccionPorTurnoListener accionPorTurnoListener;
	
	public AtacarListener(Unidad representado){
		this.representado = representado;
		this.accionPorTurnoListener = AccionPorTurnoListener.createInstance();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.accionPorTurnoListener.setUnidad(this.representado);
		this.accionPorTurnoListener.setComando(new ComandoAtaqueComun());
		
	}

}
