package frsf.isi.died.app.controller;

import java.awt.Container;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BusquedaPanel;
import frsf.isi.died.tp.modelo.productos.Tema;


public class BusquedaController {

	private BusquedaPanel panelBusqueda;
	private MaterialCapacitacionDao materialDAO;
	
	public BusquedaController(BusquedaPanel panel) {
		this.panelBusqueda = panel;
		this.panelBusqueda.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	public void buscarMaterial(String titulo, Double calificacion, String tema,Integer fechaPublicacion) {
		
	}
	
	public void crearPanel() {		
		this.panelBusqueda.setListaMateriales(materialDAO.listaMateriales(),false);
		this.panelBusqueda.construir();
	}

	public BusquedaPanel getPanelBusqueda() {
		return panelBusqueda;
	}

	public void setPanelLibro(BusquedaPanel panelBusqueda) {
		this.panelBusqueda = panelBusqueda;
	}


	
}
