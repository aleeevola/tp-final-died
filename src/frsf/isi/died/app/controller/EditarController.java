package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.EditarPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class EditarController {

	private EditarPanel panelEditar;
	private MaterialCapacitacionDao materialDAO;
	
	public EditarController(EditarPanel panel) {
		this.panelEditar = panel;
		this.panelEditar.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void crearPanel(MaterialCapacitacion mat) {		
		this.panelEditar.construir();
		this.panelEditar.setVisible(true);	
	}
}
