package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.DeseosPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class DeseosController {

	private DeseosPanel panelDeseos;
	private MaterialCapacitacionDao materialDAO;
	
	public DeseosController(DeseosPanel panel) {
		this.panelDeseos = panel;
		this.panelDeseos.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarDeseos(MaterialCapacitacion mat) {	
		
	}
	
	public void crearPanel() {
		this.panelDeseos.setListaDeseos(materialDAO.listaDeseos(),false);
		this.panelDeseos.construir();
	}

	public DeseosPanel getPanelDeseos() {
		return panelDeseos;
	}

	public void setPanelDeseos(DeseosPanel panelDeseos) {
		this.panelDeseos = panelDeseos;
	}
	
	
}
