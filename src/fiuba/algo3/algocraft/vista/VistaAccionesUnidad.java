package fiuba.algo3.algocraft.vista;
import fiuba.algo3.algocraft.controlador.Controlador;
import fiuba.algo3.algocraft.controlador.CrearScoutListener;
import fiuba.algo3.algocraft.controlador.MoverListener;
import fiuba.algo3.algocraft.modelo.juego.Juego;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class VistaAccionesUnidad extends VistaAcciones {
	public VistaAccionesUnidad(Controlador controlador) {
		super(controlador);
		JButton btnMover = new JButton("Mover");
		btnMover.addActionListener(new MoverListener(controlador));
		GridBagConstraints gbc_btnMover = new GridBagConstraints();
		gbc_btnMover.insets = new Insets(0, 0, 5, 0);
		gbc_btnMover.gridx = 0;
		gbc_btnMover.gridy = 1;
		add(btnMover, gbc_btnMover);
	}

}
