package fiuba.algo3.algocraft.vista.acciones;

import fiuba.algo3.algocraft.controlador.TerminarTurnoListener;
import fiuba.algo3.algocraft.modelo.juego.Juego;
import fiuba.algo3.algocraft.modelo.utilidades.Interactuable;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;

public class VistaAcciones extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String TITULO_DEFAULT = "Clickear parcela para realizar una accion";
	private JLabel titulo;
    protected Juego modelo;
	public VistaAcciones(Juego modelo) {
		this.modelo = modelo;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitulo = new JLabel("Titulo a cambiar");
		this.titulo = lblTitulo;
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		add(lblTitulo, gbc_lblTitulo);
		
		JButton btnTerminarTurno = new JButton("Terminar Turno");
		GridBagConstraints gbc_btnTerminarTurno = new GridBagConstraints();
		gbc_btnTerminarTurno.gridx = 0;
		gbc_btnTerminarTurno.gridy = 9;
        btnTerminarTurno.addActionListener(new TerminarTurnoListener(this.modelo));
		add(btnTerminarTurno, gbc_btnTerminarTurno);
		
		this.setTitulo(TITULO_DEFAULT);
	}
	
	public void setTitulo(String titulo){
		this.titulo.setText(titulo);
	}
	
	public void habilitarBoton(JButton boton, Interactuable i){
		if(this.modelo.getJugadorQueJuega() != i.getPropietario())
			boton.setEnabled(false);
		
	}
}
