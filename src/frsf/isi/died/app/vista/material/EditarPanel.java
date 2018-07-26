package frsf.isi.died.app.vista.material;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.EditarController;
import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.tp.modelo.productos.Libro;

public class EditarPanel extends JDialog{
	

	private JButton btnAgregar;
	private JButton btnCancelar;

	private EditarController controller;
	
	public EditarPanel(Frame parent, boolean modal) {
		super(parent, modal);
		this.setLayout(new GridBagLayout());
		this.setSize(400, 400);
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
		

		btnAgregar = new JButton("Agregar");
		/*btnAgregar.addActionListener( e ->{
			try {
				
			}catch(Exception ex) {
			}
		});*/
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=6;
		this.add(btnAgregar, gridConst);


		btnCancelar= new JButton("Cancelar");
		gridConst.gridx=6;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);
		
	}

	public EditarController getController() {
		return controller;
	}


	public void setController(EditarController controller2) {
		// TODO Auto-generated method stub
		this.controller = controller2;
	}


}