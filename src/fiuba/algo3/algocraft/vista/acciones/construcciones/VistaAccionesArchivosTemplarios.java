package fiuba.algo3.algocraft.vista.acciones.construcciones;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;

import fiuba.algo3.algocraft.controlador.creadoresDeInteractuables.CrearAltoTemplarioListener;
import fiuba.algo3.algocraft.modelo.juego.Juego;
import fiuba.algo3.algocraft.modelo.razas.protoss.construcciones.ArchivosTemplarios;
import fiuba.algo3.algocraft.vista.acciones.VistaAccionesInteractuable;

public class VistaAccionesArchivosTemplarios  extends VistaAccionesInteractuable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VistaAccionesArchivosTemplarios(Juego modelo, ArchivosTemplarios representado) {
		super(modelo, representado);
		
		JButton btnCrearAltoTemplario = new JButton("Crear Alto Templario");
		btnCrearAltoTemplario.addActionListener(new CrearAltoTemplarioListener(modelo, representado));
		GridBagConstraints gbc_btnCrearAltoTemplario = new GridBagConstraints();
		gbc_btnCrearAltoTemplario.insets = new Insets(0, 0, 5, 0);
		gbc_btnCrearAltoTemplario.gridx = 0;
		gbc_btnCrearAltoTemplario.gridy = 3;
		add(btnCrearAltoTemplario, gbc_btnCrearAltoTemplario);
		this.habilitarBoton(btnCrearAltoTemplario, representado);
			
	}

}