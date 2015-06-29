package fiuba.algo3.algocraft.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fiuba.algo3.algocraft.modelo.juego.Juego;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Unidad;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.UnidadAgresora;

public class AtacarListener implements ActionListener{
    private Unidad representado;
	private ControladorAtacar controladorAtacar;
	
	public AtacarListener(Mapa mapa,Unidad representado){
//		this.modelo = modelo;
		this.representado = representado;
		this.controladorAtacar = ControladorAtacar.createInstance(mapa);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.controladorAtacar.setUnidad((UnidadAgresora) this.representado);
		
	}

}