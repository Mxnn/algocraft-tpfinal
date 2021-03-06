package fiuba.algo3.algocraft.vista.acciones.construcciones;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import fiuba.algo3.algocraft.controlador.creadoresDeInteractuables.CrearGolliatListener;
import fiuba.algo3.algocraft.modelo.juego.Juego;
import fiuba.algo3.algocraft.modelo.razas.terran.construcciones.Fabrica;
import fiuba.algo3.algocraft.vista.acciones.VistaAccionesInteractuable;

public class VistaAccionesFabrica extends VistaAccionesInteractuable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VistaAccionesFabrica(Juego modelo, Fabrica representado) {
		super(modelo, representado);
		JButton btnCrearGolliat = new JButton("Crear Golliat");
		btnCrearGolliat.addActionListener(new CrearGolliatListener(modelo, representado));
		GridBagConstraints gbc_btnCrearGolliat = new GridBagConstraints();
		gbc_btnCrearGolliat.insets = new Insets(0, 0, 5, 0);
		gbc_btnCrearGolliat.gridx = 0;
		gbc_btnCrearGolliat.gridy = 3;
		add(btnCrearGolliat, gbc_btnCrearGolliat);
		this.habilitarBoton(btnCrearGolliat, representado);
	}
}

