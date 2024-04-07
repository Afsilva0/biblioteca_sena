
package biblioteca_sena;

import Controlador.ControladorPrincipal;
import Controlador.ControladorProducto;
import Modelo.Conexion;
import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.VPrincipal;
import Vista.VProducto;

public class Main {


    public static void main(String[] args) {
        Producto pro = new Producto();
        ConsultasProducto proC = new ConsultasProducto();
        VProducto frmPro = new VProducto();
        ControladorProducto ctrlPro = new ControladorProducto(pro,frmPro, proC);
        VPrincipal frmPri = new VPrincipal();
        ControladorPrincipal ctrlPri = new ControladorPrincipal(frmPri, frmPro);
        ctrlPri.inciar();
        frmPri.setVisible(true);

    }
    
}
