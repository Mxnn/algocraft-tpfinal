package fiuba.algo3.algocraft.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fiuba.algo3.algocraft.modelo.excepciones.ExcepcionEnergiaInsuficiente;
import fiuba.algo3.algocraft.modelo.juego.Juego;
import fiuba.algo3.algocraft.modelo.mapa.Coordenada;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;

public class ObjetivoListener implements ActionListener {
	private ControladorAtacar controladorAtacar;
	private Coordenada ubicacion;
	private Juego modelo;
	
	public ObjetivoListener(Juego modelo,Coordenada ubicacionParcela){
		this.modelo = modelo;
		this.ubicacion = ubicacionParcela;
//		this.controladorAtacar = ControladorAtacar.createInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ControladorAtacar.createInstance().atacar(this.modelo.getMapa(),this.ubicacion);
		} catch (ExcepcionEnergiaInsuficiente e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
