package frsf.isi.died.app.controller;

import java.awt.Container;
import java.awt.List;
import java.util.ArrayList;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.BusquedaPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Tema;


public class BusquedaController {

	private BusquedaPanel panelBusqueda;
	private MaterialCapacitacionDao materialDAO;
	
	public BusquedaController(BusquedaPanel panel) {
		this.panelBusqueda = panel;
		this.panelBusqueda.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	public void buscarMaterial(String titulo, Double calificacion, String tema,String fechaPublicacionDesde, String fechaPublicacionHasta) {		
		this.panelBusqueda.setListaMateriales(materialDAO.buscarMaterial(titulo,calificacion,tema,fechaPublicacionDesde,fechaPublicacionHasta),true);
		
	}
	
	public void crearPanel() {
		this.panelBusqueda.setListaMateriales(new ArrayList<MaterialCapacitacion>(),false);
		this.panelBusqueda.construir();
	}

	public BusquedaPanel getPanelBusqueda() {
		return panelBusqueda;
	}

	public void setPanelMateriales(BusquedaPanel panelBusqueda) {
		this.panelBusqueda = panelBusqueda;
	}


	
}
