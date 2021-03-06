package frsf.isi.died.app.vista.material;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class BusquedaTableModel extends AbstractTableModel{
	
	private List<MaterialCapacitacion> materiales;
	private String[] columnas = {"Titulo","Calificacion","Precio","Fecha publicacion", "Relevancia"};
	
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}


	@Override
	public int getRowCount() {
		return materiales.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = null;
		switch (columnIndex) {
		
		case 0:
			valor = this.materiales.get(rowIndex).getTitulo();
			break;
		case 1:
			valor = this.materiales.get(rowIndex).getCalificacion();
			break;
		case 2:
			valor = this.materiales.get(rowIndex).precio();
			break;
		case 3:
			valor = this.materiales.get(rowIndex).getFechaPublicacion();
			break;
		case 4:
			valor = this.materiales.get(rowIndex).getRelevancia().toString();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		return valor;
	}

	public List<MaterialCapacitacion> getMateriales() {
		return materiales;
	}

	public void setMateriales(List<MaterialCapacitacion> materiales) {
		this.materiales = materiales;
	}
}
