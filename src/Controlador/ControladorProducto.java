package Controlador;

import Modelo.ConsultasProducto;
import Modelo.Producto;
import Vista.VProducto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorProducto implements ActionListener {

    private Producto producto;
    private VProducto vistaProducto;
    private ConsultasProducto controlProducto;

    public ControladorProducto(Producto producto, VProducto vistaProducto, ConsultasProducto controlProducto) {
        this.producto = producto;
        this.vistaProducto = vistaProducto;
        this.controlProducto = controlProducto;
        this.vistaProducto.btn_agregar.addActionListener(this);
        this.vistaProducto.btn_buscar.addActionListener(this);
        this.vistaProducto.btn_eliminar.addActionListener(this);
        this.vistaProducto.btn_limpiar.addActionListener(this);
        this.vistaProducto.btn_modificar.addActionListener(this);
        this.vistaProducto.btn_vender.addActionListener(this);

        vistaProducto.txt_id.setText(null);
        this.consultarProductos();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //// boton agregar
        if (e.getSource() == vistaProducto.btn_agregar) {
            producto.setCodigo(vistaProducto.txt_codigo.getText());
            producto.setNombre(vistaProducto.txt_nombre.getText());
            producto.setPrecio(Integer.parseInt(vistaProducto.txt_precio.getText()));
            if (controlProducto.registrar(producto)) {
                JOptionPane.showMessageDialog(null, "Producto agregado");
                limpiar();
                consultarProductos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar");
                limpiar();
            }
        } //------------------------ fin agregar

        //// boton modificar
        if (e.getSource() == vistaProducto.btn_modificar) {
            producto.setId(Integer.parseInt(vistaProducto.txt_id.getText()));
            producto.setCodigo(vistaProducto.txt_codigo.getText());
            producto.setNombre(vistaProducto.txt_nombre.getText());
            producto.setPrecio(Integer.parseInt(vistaProducto.txt_precio.getText()));
            if (controlProducto.modificar(producto)) {
                JOptionPane.showMessageDialog(null, "Producto modificado");
                limpiar();
                consultarProductos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al modificar");
                limpiar();
            }
        } //------------------------ fin modificar

        //// boton eliminar
        if (e.getSource() == vistaProducto.btn_eliminar) {
            producto.setId(Integer.parseInt(vistaProducto.txt_id.getText()));
            if (controlProducto.eliminar(producto)) {
                JOptionPane.showMessageDialog(null, "Producto eliminado");
                limpiar();
                consultarProductos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiar();
            }
        } //------------------------ fin eliminar

        //// boton buscar
        if (e.getSource() == vistaProducto.btn_buscar) {
            producto.setCodigo(vistaProducto.txt_codigo.getText());
            if (controlProducto.buscar(producto)) {
                vistaProducto.txt_id.setText(String.valueOf(producto.getId()));
                vistaProducto.txt_codigo.setText(producto.getCodigo());
                vistaProducto.txt_nombre.setText(producto.getNombre());
                vistaProducto.txt_precio.setText(String.valueOf(producto.getPrecio()));

            } else {
                JOptionPane.showMessageDialog(null, "Error al buscar");
                limpiar();
            }
        } //------------------------ fin buscar

        if (e.getSource() == vistaProducto.btn_limpiar) {
            limpiar();
        }

        if (e.getSource() == vistaProducto.btn_vender) {
            venta();
        }

    }//fin action

    public void limpiar() {
        vistaProducto.txt_codigo.setText(null);
        vistaProducto.txt_id.setText(null);
        vistaProducto.txt_nombre.setText(null);
        vistaProducto.txt_precio.setText(null);
    }

    public void consultarProductos() {
        List<Producto> productos = this.controlProducto.all();
        DefaultTableModel model = (DefaultTableModel) vistaProducto.tablaProducto.getModel();

        for (Producto producto : productos) {
            model.addRow(new Object[]{producto.getId(), producto.getCodigo(), producto.getNombre(), producto.getPrecio()});
        }
    }

    public Boolean venta() {
        String nombre = JOptionPane.showInputDialog(null, "Digite su nombre");

        Document document = new Document();
        Date fechaActual = new Date();

        if (vistaProducto.txt_id.getText().isEmpty()) {
            System.err.println("fallo");
            JOptionPane.showMessageDialog(null, "Debe soleccionar un producto");

            return Boolean.FALSE;
        }
        try {
            FileOutputStream ficheroPDF = new FileOutputStream("Venta-" + ".pdf");
            PdfWriter.getInstance(document, ficheroPDF);

            // Inicia documento
            document.open();
            // Se crea titulo
            Paragraph titulo = new Paragraph("Venta de libro", FontFactory.getFont("arial", 22, BaseColor.BLUE));
            titulo.setSpacingAfter(10);
            document.add(titulo);

            // Se crea Nombre comprador
            Paragraph txtNombreComprador = new Paragraph("Nombre comprador : " + nombre, FontFactory.getFont("arial", 15, BaseColor.BLACK));
            txtNombreComprador.setSpacingAfter(7);
            document.add(txtNombreComprador);

            // Se crea fecha actual
            Paragraph txtfechaCompra = new Paragraph("Fecha compra : " + fechaActual, FontFactory.getFont("arial", 15, BaseColor.BLACK));
            txtfechaCompra.setSpacingAfter(20);
            document.add(txtfechaCompra);

            // Se crea tabla
            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("ID");
            tabla.addCell("CODIGO");
            tabla.addCell("NOMBRE");
            tabla.addCell("PRECIO");

            tabla.addCell(vistaProducto.txt_id.getText());
            tabla.addCell(vistaProducto.txt_codigo.getText());
            tabla.addCell(vistaProducto.txt_nombre.getText());
            tabla.addCell(vistaProducto.txt_precio.getText());
            document.add(tabla);

            document.close();
            // Finaliza documento

            System.err.println("Se genero el pdf");
            return Boolean.TRUE;

        } catch (Exception e) {
            System.err.println("Error" + e);
            return Boolean.FALSE;
        }

    }
}
