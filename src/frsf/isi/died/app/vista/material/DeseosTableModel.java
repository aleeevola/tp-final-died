package frsf.isi.died.app.vista.material;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class DeseosTableModel  extends AbstractTableModel {

	private List<MaterialCapacitacion> deseos;
	private String[] columnas = {"Titulo","Relevancia","Calificacion","Precio"};
	
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	public List<MaterialCapacitacion> getLibros() {
		return deseos;
	}

	public void setDeseos(List<MaterialCapacitacion> deseos) {
		this.deseos = deseos;
	}

	@Override
	public int getRowCount() {
		return deseos.size();
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
			valor = this.deseos.get(rowIndex).getTitulo();
			break;
		case 1:
			valor = this.deseos.get(rowIndex).getRelevancia();
			break;
		case 2:
			valor = this.deseos.get(rowIndex).getCalificacion();
			break;
		case 3:
			valor = this.deseos.get(rowIndex).precio();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		return valor;
	}

}
