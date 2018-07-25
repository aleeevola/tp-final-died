package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import frsf.isi.died.app.controller.DeseosController;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;


public class DeseosPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;


	private DeseosTableModel tableModel;

	private DeseosController controller;

	public DeseosPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new DeseosTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
				
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=7;	
		gridConst.gridy=2;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
		
	}

	public DeseosController getController() {
		return controller;
	}

	public void setController(DeseosController controller) {
		this.controller = controller;
	}
	
	public void setListaDeseos(List<MaterialCapacitacion> deseosLista,boolean actualizar) {
		this.tableModel.setDeseos(deseosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}

	
}