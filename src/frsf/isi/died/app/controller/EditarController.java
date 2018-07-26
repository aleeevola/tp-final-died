package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.EditarPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Tema;

public class EditarController {

	private EditarPanel panelEditar;
	private MaterialCapacitacionDao materialDAO;
	
	public EditarController(EditarPanel panel) {
		this.panelEditar = panel;
		this.panelEditar.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void crearPanel(MaterialCapacitacion mat) {		
		this.panelEditar.construir(mat);
		this.panelEditar.setVisible(true);
	}
	
	public void editarMaterial(MaterialCapacitacion mat,String titulo,Double costo, Integer calificacion,
			Relevancia relv,Tema tem,Double precio,Integer paginas, Integer duracion) {
		materialDAO.editarMaterial(mat, titulo, costo, calificacion, relv, tem, precio, paginas, duracion);
	}
}
