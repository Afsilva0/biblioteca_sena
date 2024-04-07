
package Controlador;

import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.VProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class ControladorProducto implements ActionListener {
    private Producto pro;
    private VProducto frmPro;
    private ConsultasProducto proC;

    public ControladorProducto(Producto pro, VProducto frmPro, ConsultasProducto proC) {
        this.pro = pro;
        this.frmPro = frmPro;
        this.proC = proC;
        this.frmPro.btn_agregar.addActionListener(this);
        this.frmPro.btn_buscar.addActionListener(this);
        this.frmPro.btn_eliminar.addActionListener(this);
        this.frmPro.btn_limpiar.addActionListener(this);
        this.frmPro.btn_modificar.addActionListener(this);
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //// boton agregar
        if(e.getSource()==frmPro.btn_agregar){
            pro.setCodigo(frmPro.txt_codigo.getText());
            pro.setNombre(frmPro.txt_nombre.getText());
            pro.setPrecio(Integer.parseInt(frmPro.txt_precio.getText()));
            if(proC.registrar(pro)){
                JOptionPane.showMessageDialog(null, "Producto agregado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al agregar");
                limpiar();
            }
        } //------------------------ fin agregar
        
                //// boton modificar
        if(e.getSource()==frmPro.btn_modificar){
            pro.setId(Integer.parseInt(frmPro.txt_id.getText()));
            pro.setCodigo(frmPro.txt_codigo.getText());
            pro.setNombre(frmPro.txt_nombre.getText());
            pro.setPrecio(Integer.parseInt(frmPro.txt_precio.getText()));
            if(proC.modificar(pro)){
                JOptionPane.showMessageDialog(null, "Producto modificado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            }
        } //------------------------ fin modificar
        
        
                        //// boton eliminar
        if(e.getSource()==frmPro.btn_eliminar){
            pro.setId(Integer.parseInt(frmPro.txt_id.getText()));
            if(proC.eliminar(pro)){
                JOptionPane.showMessageDialog(null, "Producto eliminado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiar();
            }
        } //------------------------ fin eliminar
        
        
                                //// boton buscar
        if(e.getSource()==frmPro.btn_buscar){
            pro.setId(Integer.parseInt(frmPro.txt_id.getText()));
            if(proC.buscar(pro)){
                frmPro.txt_id.setText(String.valueOf(pro.getId()));
                frmPro.txt_codigo.setText(pro.getCodigo());
                frmPro.txt_nombre.setText(pro.getNombre());
                frmPro.txt_precio.setText(String.valueOf(pro.getPrecio()));

            }else{
                JOptionPane.showMessageDialog(null, "Error al buscar");
                limpiar();
            }
        } //------------------------ fin buscar
        
        if(e.getSource()==frmPro.btn_limpiar){
            limpiar();
        }
        
        
    }//fin action
    
    public void limpiar(){
        frmPro.txt_codigo.setText(null);
        frmPro.txt_id.setText(null);
        frmPro.txt_nombre.setText(null);
        frmPro.txt_precio.setText(null);
    }
    
}
