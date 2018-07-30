package frsf.isi.died.app.controller;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import frsf.isi.died.app.vista.grafo.ControlPanel;
import frsf.isi.died.app.vista.grafo.GrafoPanel;
import frsf.isi.died.app.vista.material.BusquedaPanel;
import frsf.isi.died.app.vista.material.DeseosPanel;
import frsf.isi.died.app.vista.material.DocumentoPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.app.vista.material.VideoPanel;
import frsf.isi.died.app.vista.material.verDocPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class MenuController {

	private JFrame framePrincipal;
	
	public MenuController(JFrame f) {
		this.framePrincipal = f;
	}
	
	public void showView(TiposAcciones accion) {
		switch (accion) {
		case ABM_LIBROS:
			LibroPanel panelLibros = new LibroPanel();
			LibroController controller = new LibroController(panelLibros);
			controller.crearPanel();
			framePrincipal.setContentPane(controller.getPanelLibro());
			break;
			
		case ABM_VIDEOS:
			VideoPanel panelVideos = new VideoPanel();
			VideoController controller2 = new VideoController(panelVideos);
			controller2.crearPanel();
			framePrincipal.setContentPane(controller2.getPanelVideo());
			break;
			
		case BUSCAR_MAT:
			BusquedaPanel panelBusqueda = new BusquedaPanel();
			BusquedaController controller3 = new BusquedaController(panelBusqueda);
			controller3.crearPanel();
			framePrincipal.setContentPane(controller3.getPanelBusqueda());
			break;
			
		case VER_DESEOS:
			DeseosPanel panelDeseos= new DeseosPanel();
			DeseosController controller5 = new DeseosController(panelDeseos);
			controller5.crearPanel();
			framePrincipal.setContentPane(controller5.getPanelDeseos());
			break;
			
		case VER_DOCUMENTOS:
			verDocPanel panelVerDoc= new verDocPanel();
			verDocController controller6 = new verDocController(panelVerDoc);
			controller6.crearPanel();
			framePrincipal.setContentPane(controller6.getPanelDeseos());
			break;
		

		case VER_GRAFO:
			JPanel panel = new JPanel(new BorderLayout());
			ControlPanel controlPanel = new ControlPanel();
			GrafoPanel grafoPanel = new GrafoPanel();
			GrafoController grfController = new GrafoController(grafoPanel,controlPanel);
			panel.add(controlPanel , BorderLayout.PAGE_START);
			panel.add(grafoPanel , BorderLayout.CENTER);
			
			framePrincipal.setContentPane(panel);
			break;			
		default:
			break;
		}
		framePrincipal.pack();

	}
	
	
}
