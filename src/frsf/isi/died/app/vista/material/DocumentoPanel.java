package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.DocumentoController;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class DocumentoPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private JButton btnDocumento;
	private JTextField txtCosto;
	private int seleccion;

	private DocumentoTableModel tableModel;

	private DocumentoController controller;
	
	public DocumentoPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new DocumentoTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
	
		btnDocumento = new JButton("Crear Documento");
		btnDocumento.addActionListener( e ->{
			System.out.println(tableModel.getDocumentos().get(seleccion).getTitulo());
			//tableModel.getDocumentos().get(seleccion)
			controller.iniciarDoc(tableModel.getDocumentos().get(seleccion));
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=6;
		this.add(btnDocumento, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(5);
		gridConst.gridx=2;
		this.add(txtCosto, gridConst);
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=8;	
		gridConst.gridy=1;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
		
	
		tabla.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseReleased(MouseEvent e) {
		        int r = tabla.rowAtPoint(e.getPoint());
		        seleccion=r;
		        System.out.print(seleccion);
		        }
		    
		});
	}
	


	
/*	//@SuppressWarnings("unused")
	private void jTableMouseClicked(MouseEvent evt){
		int seleccion = tabla.getSelectedRow();
		//int seleccion = tabla.rowAtPoint(evt.getPoint());
		txtCosto.setText(String.valueOf(tabla.getValueAt(seleccion, 0)));
		//System.out.println(+seleccion);
	}*/
	
	public DocumentoController getController() {
		return controller;
	}

	public void setController(DocumentoController controller) {
		this.controller = controller;
	}
	
	public void setListaDocumento(List<MaterialCapacitacion> docuementosLista,boolean actualizar) {
		this.tableModel.setDocumento(docuementosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}

}