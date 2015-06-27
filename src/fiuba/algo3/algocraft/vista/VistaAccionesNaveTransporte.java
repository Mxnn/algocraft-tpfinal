package fiuba.algo3.algocraft.vista;

import fiuba.algo3.algocraft.modelo.juego.Juego;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;

public class VistaAccionesNaveTransporte extends VistaAccionesUnidad {

	public VistaAccionesNaveTransporte(Juego modelo) {
		super(modelo);
		
		JButton btnInsertarUnidad = new JButton("Insertar Unidad");
		GridBagConstraints gbc_btnInsertarUnidad = new GridBagConstraints();
		gbc_btnInsertarUnidad.insets = new Insets(0, 0, 5, 0);
		gbc_btnInsertarUnidad.gridx = 0;
		gbc_btnInsertarUnidad.gridy = 2;
		add(btnInsertarUnidad, gbc_btnInsertarUnidad);
		
		JButton btnSacarUnidad = new JButton("Sacar Unidad");
		GridBagConstraints gbc_btnSacarUnidad = new GridBagConstraints();
		gbc_btnSacarUnidad.insets = new Insets(0, 0, 5, 0);
		gbc_btnSacarUnidad.gridx = 0;
		gbc_btnSacarUnidad.gridy = 3;
		add(btnSacarUnidad, gbc_btnSacarUnidad);
	}
}