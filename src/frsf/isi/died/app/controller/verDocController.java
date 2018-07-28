package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.DeseosPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.app.vista.material.verDocPanel;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class verDocController {

	private verDocPanel panelDeseos;
	private MaterialCapacitacionDao materialDAO;
	
	public verDocController(verDocPanel panel) {
		this.panelDeseos = panel;
		this.panelDeseos.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarDeseos(MaterialCapacitacion mat) {	
		
	}
	
	public void crearPanel() {
		this.panelDeseos.setListaDocumento(materialDAO.listaDocumentos(),false);
		this.panelDeseos.construir();
	}

	public verDocPanel getPanelDeseos() {
		return panelDeseos;
	}

	public void setPanelDoc(verDocPanel panelDeseos) {
		this.panelDeseos = panelDeseos;
	}
	
	
}
