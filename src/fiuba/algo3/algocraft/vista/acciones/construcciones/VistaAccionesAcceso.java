package fiuba.algo3.algocraft.vista.acciones.construcciones;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import fiuba.algo3.algocraft.controlador.creadoresDeInteractuables.CrearDragonListener;
import fiuba.algo3.algocraft.controlador.creadoresDeInteractuables.CrearZealotListener;
import fiuba.algo3.algocraft.modelo.juego.Juego;
import fiuba.algo3.algocraft.modelo.razas.protoss.construcciones.Acceso;
import fiuba.algo3.algocraft.vista.acciones.VistaAccionesInteractuable;

public class  VistaAccionesAcceso  extends VistaAccionesInteractuable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VistaAccionesAcceso(Juego modelo, Acceso representado) {
		super(modelo, representado);
		
		JButton btnCrearZealot = new JButton("Crear Zealot");
		btnCrearZealot.addActionListener(new CrearZealotListener(modelo, representado));
		GridBagConstraints gbc_btnCrearZealot = new GridBagConstraints();
		gbc_btnCrearZealot.insets = new Insets(0, 0, 5, 0);
		gbc_btnCrearZealot.gridx = 0;
		gbc_btnCrearZealot.gridy = 3;
		add(btnCrearZealot, gbc_btnCrearZealot);
		this.habilitarBoton(btnCrearZealot, representado);
		

		JButton btnCrearDragon = new JButton("Crear Dragon");
		btnCrearDragon.addActionListener(new CrearDragonListener(modelo, representado));
		GridBagConstraints gbc_btnCrearDragon = new GridBagConstraints();
		gbc_btnCrearDragon.insets = new Insets(0, 0, 5, 0);
		gbc_btnCrearDragon.gridx = 0;
		gbc_btnCrearDragon.gridy = 4;
		add(btnCrearDragon, gbc_btnCrearDragon);		
		this.habilitarBoton(btnCrearDragon, representado);
	}
}