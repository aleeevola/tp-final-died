package frsf.isi.died.app.vista.material;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class DocumentoTableModel  extends AbstractTableModel {

	private List<MaterialCapacitacion> documentos;
	private String[] columnas = {"Titulo","Fecha"};
	
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	public List<MaterialCapacitacion> getDocumentos() {
		return documentos;
	}

	public void setDocumento(List<MaterialCapacitacion> libros) {
		this.documentos = libros;
	}
	@Override
	public int getRowCount() {
		return documentos.size();
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
			valor = this.documentos.get(rowIndex).getTitulo();
			break;
		/*case 1:
			valor = this.documentos.get(rowIndex).getTitulo();
			break;
		case 2:
			valor = this.libros.get(rowIndex).getPrecioCompra();
			break;
		case 3:
			valor = this.libros.get(rowIndex).getCosto();
			break;
		case 4:
			valor = this.libros.get(rowIndex).getPaginas();
			break;
		case 5:
			valor = this.libros.get(rowIndex).precio();
			break;*/
		case 1:
			valor = this.documentos.get(rowIndex).getFechaPublicacion();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		return valor;
	}

}
