package fiuba.algo3.algocraft.vista.observadores.mapa;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import fiuba.algo3.algocraft.controlador.ClickEnParcelaListener;
import fiuba.algo3.algocraft.controlador.ParcelaListener;
import fiuba.algo3.algocraft.modelo.mapa.Coordenada;
import fiuba.algo3.algocraft.modelo.mapa.Mapa;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaEspacio;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaMineral;
import fiuba.algo3.algocraft.modelo.mapa.ParcelaVolcan;
import fiuba.algo3.algocraft.modelo.utilidades.Interactuable;
import fiuba.algo3.algocraft.modelo.utilidades.construcciones.Construccion;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Clon;
import fiuba.algo3.algocraft.modelo.utilidades.unidades.Unidad;
import fiuba.algo3.algocraft.vista.botones.*;

public class VistaMapa extends JPanel implements ObservadorMapa {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static VistaMapa INSTANCIA = null;

	public static final Color PARCELA_TIERRA = Color.lightGray;
    public static final Color PARCELA_ESPACIO = Color.black;
    public static final Color PARCELA_MINERAL = new Color(0x80C0D2);
    public static final Color PARCELA_VOLCAN = new Color(0xA47861);
    
    public static final String BOTON_PARCELA = "botonParcela";
    public static final String BOTON_INTERACTUABLE = "botonInteractuable";

	private static final int INDICE_BOTON_INTERACTUABLE = 1;
    
	private int filas;
	private int columnas;
    private final List<JButton> listaBotonesParcela = new ArrayList<JButton>();
    private final List<JPanel> listaPanelesParcela = new ArrayList<JPanel>();
    private ClickEnParcelaListener clickEnParcelaListener;
    private Coordenada coordenadaSeleccionada;
    private Representador representador;
	private VistaBotonInteractuable botonEnEspera = null;

    public static VistaMapa createInstance(ClickEnParcelaListener clickEnParcelaListener, Mapa mapa) {
        if (INSTANCIA == null)
            INSTANCIA = new VistaMapa(clickEnParcelaListener, mapa);

        return INSTANCIA;
    }

    public static VistaMapa getInstance() {
        return INSTANCIA;
    }

	private VistaMapa(ClickEnParcelaListener clickEnParcelaListener, Mapa mapa) {
	    this.setVisible(false);
		this.filas=  mapa.getFilas();
		this.columnas = mapa.getColumnas();
		this.clickEnParcelaListener = clickEnParcelaListener;
		this.representador = new Representador();
		this.setLocation(0, 0);
		this.setSize(700,700);
		this.setLayout(new GridLayout(columnas,filas));
//		this.setLayout(new GridLayout(20,20)); //esto lo comente para poder usar el window builder

		for(int i=0;i<filas*columnas;i++) {

		    int y = i / columnas;
	        int x = i % columnas;

            VistaBotonParcela buttonActual = new VistaBotonParcela(new Coordenada(x, y));
            ParcelaListener l = clickEnParcelaListener.getParcelaListener();
            buttonActual.addActionListener(l);
            l.setCoordenadasBoton(x,y);
	        listaBotonesParcela.add(buttonActual);

	        JPanel panelBotones = new JPanel();
	        panelBotones.setLayout(new CardLayout());
	        panelBotones.setBackground(PARCELA_TIERRA); //el mapa se crea con colores de tierra
	        this.add(panelBotones);
	        this.listaPanelesParcela.add(panelBotones);
	        panelBotones.add(buttonActual, BOTON_PARCELA);
        }
	}

    public VistaBotonInteractuable getBoton(Coordenada coordenada) {
        JPanel panel = this.getPanel(coordenada);

        return (VistaBotonInteractuable) panel.getComponent(panel.getComponentCount() - 1);
    }
    
    public JPanel getPanel(Coordenada coordenada) {
        int index = coordenada.getY() * this.columnas + coordenada.getX();

        return listaPanelesParcela.get(index);
    }

    private void pintarParcela(Coordenada coordenada, Color color){
    	JPanel panelParcela = this.getPanel(coordenada);
    	panelParcela.setBackground(color);
    }
    
    private void agregarConstruccion(VistaBotonInteractuable buttonActual){   
    	JPanel panelParcela = this.getPanel(this.coordenadaSeleccionada);
    	
    	this.agregarBoton(buttonActual, panelParcela);
    	
    	ParcelaListener l = this.clickEnParcelaListener.getParcelaListener();
    	l.setCoordenadasBoton(this.coordenadaSeleccionada.getX(), this.coordenadaSeleccionada.getY());
    	buttonActual.addActionListener(l);
    }
    
    private void agregarUnidad(VistaBotonInteractuable buttonActual, Coordenada coordenada){   
    	JPanel panelParcela = this.getPanel(coordenada);
    	
    	this.agregarBoton(buttonActual, panelParcela);
    	
    	ParcelaListener l = this.clickEnParcelaListener.getParcelaListener();
    	l.setCoordenadasBoton(coordenada.getX(), coordenada.getY());
    	buttonActual.addActionListener(l);
    }
    
    private void agregarBoton(VistaBotonInteractuable buttonActual, JPanel panelParcela){
    	if(!(panelParcela.getComponentCount() >1)){
    	
        panelParcela.add(buttonActual, BOTON_INTERACTUABLE);
        CardLayout cl = (CardLayout) panelParcela.getLayout();
        cl.show(panelParcela, BOTON_INTERACTUABLE);
    	}
    	
    }
    
    private void desinscribirElemento(Coordenada coordenada){  //metodo para limpiar la parcela si la unidad se mueve en otra parcela
    	JPanel panelParcela = this.getPanel(coordenada);
    	if(this.hayInteractuableEnPanelParcela(panelParcela))
    		panelParcela.remove(INDICE_BOTON_INTERACTUABLE);
    }
    
	public void refrescar(Mapa mapa) {
		for(int x=0; x<this.columnas; x++){
    		for(int y=0; y<this.filas; y++){
    			JPanel panelParcela = this.getPanel(new Coordenada(x,y));
    			if(this.hayInteractuableEnPanelParcela(panelParcela)){
    				VistaBotonRepresentante boton = (VistaBotonRepresentante) this.getBoton(new Coordenada(x,y));
    				boton.habilitarBoton();
    			}
    				
    		}
    	}
	}
	
	private boolean hayInteractuableEnPanelParcela(JPanel panelParcela){
		return (panelParcela.getComponentCount() >1);
	}
	
	public void seleccionarCoordenada(int x, int y){
		this.coordenadaSeleccionada = new Coordenada (x,y);
		
	}
	
	public void activarBoton(Coordenada coordenada){
		JPanel panelParcela = this.getPanel(coordenada);
		Component boton = panelParcela.getComponent(1);
		boton.setEnabled(true);
	}

	
	@Override
	public void crearVistaParcela(ParcelaEspacio parcela) {
		this.pintarParcela(parcela.getCoordenada(), PARCELA_ESPACIO);
	}

	@Override
	public void crearVistaParcela(ParcelaMineral parcela) {
		this.pintarParcela(parcela.getCoordenada(), PARCELA_MINERAL);
	}

	@Override
	public void crearVistaParcela(ParcelaVolcan parcela) {
		this.pintarParcela(parcela.getCoordenada(), PARCELA_VOLCAN);
	}
	
	@Override
	public void detectarMovimiento(Coordenada origen, Coordenada destino) {
		VistaBotonInteractuable botonOrigen = this.getBoton(origen);
		this.desinscribirElemento(origen);
		botonOrigen.removeAllActionListeners();
		this.agregarUnidad(botonOrigen, destino);
	}

	private void ponerColorDeLetraAdecuado(VistaBotonInteractuable boton, Interactuable interactuable) {
        boton.setForeground(this.representador.getColorTexto(interactuable.getPropietario()));
    }

    @Override
    public void crearConstruccion(Construccion construccion) {
        VistaBotonInteractuable boton;
        try {
            Class<?> claseBoton = this.representador.getClaseBoton(construccion);
            boton = (VistaBotonInteractuable) claseBoton.getDeclaredConstructor(construccion.getClass()).newInstance(construccion);
            this.ponerColorDeLetraAdecuado(boton, construccion);

            this.botonEnEspera = boton;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void crearUnidad(Unidad unidad) {
        VistaBotonInteractuable boton;
        try {
            Class<?> claseBoton = this.representador.getClaseBoton(unidad);
            boton = (VistaBotonInteractuable) claseBoton.getDeclaredConstructor(unidad.getClass()).newInstance(unidad);
            this.ponerColorDeLetraAdecuado(boton, unidad);

            Coordenada coordenada = unidad.getParcela().getCoordenada();
            this.agregarUnidad(boton, coordenada);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void crearUnidad(Clon clon) {
        VistaBotonInteractuable botonClonado;
        VistaBotonClon botonClon;
        Unidad unidad = clon.getClonado();
        try {
            Class<?> claseBotonClonado = this.representador.getClaseBoton(unidad);
            Class<?> claseBotonClon = this.representador.getClaseBoton(clon);
            botonClonado = (VistaBotonInteractuable) claseBotonClonado.getDeclaredConstructor(unidad.getClass()).newInstance(unidad);
            botonClon = (VistaBotonClon) claseBotonClon.getDeclaredConstructor(new Class[] {clon.getClass(), VistaBotonInteractuable.class}).newInstance(new Object[] { clon, botonClonado });
            this.ponerColorDeLetraAdecuado(botonClon, clon);

            Coordenada coordenada = clon.getParcela().getCoordenada();
            this.agregarUnidad(botonClon, coordenada);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removerInteractuableDeLaVista(Interactuable interactuable) {
        Coordenada ubicacion = interactuable.getParcela().getCoordenada();
        JPanel panel = this.getPanel(ubicacion);
        panel.remove(panel.getComponentCount() - 1);
    }

    @Override
    public void agregarConstruccionEnEspera() {
        if (this.botonEnEspera != null) {
            this.agregarConstruccion(this.botonEnEspera);
            this.botonEnEspera = null;
        }
    }
}
