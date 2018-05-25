package com.iespuerto.pro.propietarioscasasmaven;

import com.iespuerto.pro.propietarioscasasmaven.modelo.Casa;
import com.iespuerto.pro.propietarioscasasmaven.modelo.Casas;
import com.iespuerto.pro.propietarioscasasmaven.modelo.Conectar;
import com.iespuerto.pro.propietarioscasasmaven.modelo.ConectarDB;
import com.iespuerto.pro.propietarioscasasmaven.modelo.Propietario;
import com.iespuerto.pro.propietarioscasasmaven.modelo.Propietarios;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FXMLController implements Initializable {

    private Label label;
    @FXML
    private Button btnModificarPropietario;
    @FXML
    private Button btnAgregarPropietario;
    @FXML
    private Button btnBorrarPropietario;
    @FXML
    private Button btnAnteriorPropietario;
    @FXML
    private Button btnSiguientePropietario;
    @FXML
    private Button btnCancelarCasaPropietario;
    @FXML
    private Button btnAceptarCasaPropietario;
    @FXML
    private Button btnAgregarCasaPropietario;
    @FXML
    private Button btnBorrarCasaPropietario;
    @FXML
    private Button btnAnteriorCasaPropietario;
    @FXML
    private Button btnSiguienteCasaPropietario;
    @FXML
    private Button btnAnteriorCasa;
    @FXML
    private Button btnSiguienteCasa;
    @FXML
    private Button btnAgregarCasa;
    @FXML
    private Button btnBorrarCasa;
    @FXML
    private Button btnModificarCasa;
    @FXML
    private Button btnAnteriorPropietarioCasa;
    @FXML
    private Button btnSiguientePropietarioCasa;
    @FXML
    private Button btnAgregarPropietarioCasa;
    @FXML
    private CheckBox cbxAscensor;
    @FXML
    private CheckBox cbxGaraje;
    @FXML
    private TextField txtDNI;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtDNIPropietario;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtMetros;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtPrecio;
    @FXML
    private Button btnPropietarioAceptar;
    @FXML
    private Button btnPropietarioCancelar;

    ConectarDB conectarDB;
    public ListView<Propietario> propietarios;
    public ListView<Propietarios> propietariosH;
    public ListView<Casa> casas;
    public ListView<Casas> casasH;
    public int posicionPropietarios;
    @FXML
    private Button btnCasaAceptar;
    @FXML
    private Button btnCasaCancelar;
    @FXML
    private Button btnCancelarPropietarioCasa;
    @FXML
    private Button btnAceptarPropietarioCasa;
    @FXML
    private TextField txtNombrePropietario;
    @FXML
    private TextField txtApellidosPropietario;
    @FXML
    private TextField txtIDCasa;
    @FXML
    private TextField txtMetrosCasa;
    @FXML
    private TextField txtDireccionCasa;
    @FXML
    private TextField txtPrecioCasa;
    @FXML
    private CheckBox cbxAscensorCasa;
    @FXML
    private CheckBox cbxGarajeCasa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conectar.crearFactory();
        
        Conectar.obtenerPropietarios();
        Conectar.obtenerCasas();
        //Conectar.cerrarFactory();
        
        conectarDB = new ConectarDB();
        propietarios = new ListView<Propietario>();
        propietariosH = new ListView<Propietarios>();
        casas = new ListView<Casa>();
        casasH = new ListView<Casas>();
        posicionPropietarios = 0;
        posicionCasa = 0;
        System.out.println("\n\n\n\n\n");

        extraerPropietarios();
        extraerCasas();
        mostrarPropietarios();
        mostrarCasas();


    }

    public void extraerCasas() {
        for (int i = 0; i < conectarDB.agregarCasasDB().size(); i++) {
            casas.getItems().add(conectarDB.agregarCasasDB().get(i));
            casasH.getItems().add(Conectar.obtenerCasas().get(i));
        }
        mostrarCasas();

    }

    public void mostrarCasas() {
        txtID.setText(casas.getItems().get(posicionCasa).getId() + "");
        txtMetros.setText(casas.getItems().get(posicionCasa).getMetros() + "");
        txtDireccion.setText(casas.getItems().get(posicionCasa).getDireccion() + "");
        txtPrecio.setText(casas.getItems().get(posicionCasa).getPrecio() + "");
        cbxAscensor.setSelected(casas.getItems().get(posicionCasa).isAscensor());
        cbxGaraje.setSelected(casas.getItems().get(posicionCasa).isGaraje());
    }

    public void extraerPropietarios() {
        for (int i = 0; i < conectarDB.agregarPropietariosDB().size(); i++) {
            propietarios.getItems().add(conectarDB.agregarPropietariosDB().get(i));
            propietariosH.getItems().add(Conectar.obtenerPropietarios().get(i));
        }
        
    }

    public void mostrarPropietarios() {
        txtDNI.setText(propietarios.getItems().get(posicionPropietarios).getDni());
        txtApellidos.setText(propietarios.getItems().get(posicionPropietarios).getApellidos());
        txtNombre.setText(propietarios.getItems().get(posicionPropietarios).getNombre());
    }

    boolean modificarPropietario = false;

    String dniModificar="";
    @FXML
    private void btnModificarPropietarioOnClick(ActionEvent event) {
        modificarPropietario = true;
        dniModificar = txtDNI.getText();
        txtDNI.setDisable(false);
        txtApellidos.setDisable(false);
        txtNombre.setDisable(false);
        btnPropietarioAceptar.setVisible(true);
        btnPropietarioCancelar.setVisible(true);
        btnAnteriorPropietario.setVisible(false);
        btnSiguientePropietario.setVisible(false);
        agregarPropietario = false;
        borrarPropietario = false;
    }

    boolean agregarPropietario = false;

    @FXML
    private void btnAgregarPropietarioOnClick(ActionEvent event) {
        txtDNI.setDisable(false);
        txtApellidos.setDisable(false);
        txtNombre.setDisable(false);
        btnPropietarioAceptar.setVisible(true);
        btnPropietarioCancelar.setVisible(true);
        txtDNI.clear();
        txtApellidos.clear();
        txtNombre.clear();
        agregarPropietario = true;
        modificarPropietario = false;
        borrarPropietario = false;
    }

    boolean borrarPropietario = false;

    @FXML
    private void btnBorrarPropietarioOnClick(ActionEvent event) {
        borrarPropietario = true;
        btnPropietarioAceptar.setVisible(true);
        btnPropietarioCancelar.setVisible(true);
        agregarPropietario = false;
        modificarPropietario = false;
        btnAnteriorPropietario.setVisible(false);
        btnSiguientePropietario.setVisible(false);
    }

    @FXML
    private void btnAnteriorPropietarioOnClick(ActionEvent event) {
        if (posicionPropietarios != 0) {
            posicionPropietarios--;
        }

        mostrarPropietarios();

        casasListView = new ListView<Casa>();
        for (int i = 0; i < propietarios.getItems().get(posicionPropietarios).getCasas().size(); i++) {
            casasListView.getItems().add(propietarios.getItems().get(posicionPropietarios).getCasas().get(i));

        }
    }

    @FXML
    private void btnSiguientePropietarioOnClick(ActionEvent event) {
        if (posicionPropietarios != propietarios.getItems().size() - 1) {
            posicionPropietarios++;
        }

        mostrarPropietarios();

        casasListView = new ListView<Casa>();
        for (int i = 0; i < propietarios.getItems().get(posicionPropietarios).getCasas().size(); i++) {
            casasListView.getItems().add(propietarios.getItems().get(posicionPropietarios).getCasas().get(i));

        }
    }

    boolean modificarCasa = true;

    @FXML
    private void btnModificarCasaOnClick(ActionEvent event) {
        if (modificarCasa == false) {
            modificarCasa = true;
            txtDireccion.setDisable(false);
            txtMetros.setDisable(false);
            txtPrecio.setDisable(false);
            btnCasaAceptar.setVisible(true);
            btnCasaCancelar.setVisible(true);
        } else {
            modificarCasa = false;
            txtDireccion.setDisable(true);
            txtMetros.setDisable(true);
            txtPrecio.setDisable(true);
        }
    }

    ListView<HashMap<String, Casa>> casasPropietario;
    String operacion;

    @FXML
    private void btnPropietarioAceptarOnClick(ActionEvent event) {

        if (agregarPropietario) {
            modificarPropietario = false;
            borrarPropietario = false;

            //posicionPropietarios = propietarios.getItems().size() - 1;
            Propietario p = new Propietario();
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String dni = txtDNI.getText();
            p.setNombre(nombre);
            p.setApellidos(apellidos);
            p.setDni(dni);
            
            conectarDB.agregarPropietario(dni,nombre,apellidos);
            
            propietarios = new ListView<Propietario>();
            extraerPropietarios();
            posicionPropietarios = propietarios.getItems().size() - 1;
            mostrarPropietarios();
            //propietarios.getItems().add(p);
            casasPropietario = new ListView<HashMap<String, Casa>>();
            casasPropietario.getItems().add(p.getCasasMap());
            //posicionPropietarios++;
            propietarios.refresh();
            System.out.println("propietario añadido");
            casasListView = new ListView<Casa>();

            if (!p.getCasas().isEmpty()) {
                for (int i = 0; i < p.getCasas().size(); i++) {
                    casasListView.getItems().add(p.getCasas().get(i));

                }
            }

            agregarPropietario = false;
//            operacion = "";
//            
//            conectarDB.operar(operacion);
        }
        if (modificarPropietario) {
            Propietario p = new Propietario();
            String nombre = txtNombre.getText();
            String apellidos = txtApellidos.getText();
            String dni = txtDNI.getText();
            
            p.setNombre(nombre);
            p.setApellidos(apellidos);
            p.setDni(dni);

            conectarDB.modificarPropietario(dni, nombre, apellidos, dniModificar);
            propietarios = new ListView<Propietario>();
            extraerPropietarios();
            mostrarPropietarios();
            
            modificarPropietario = false;
            btnAnteriorPropietario.setVisible(true);
            btnSiguientePropietario.setVisible(true);
        }
        if (borrarPropietario) {
            String dni = txtDNI.getText();
            conectarDB.borrarPropietario(dni);
            propietarios = new ListView<Propietario>();
            extraerPropietarios();
            posicionPropietarios = propietarios.getItems().size() - 1;
            mostrarPropietarios();
            btnAnteriorPropietario.setVisible(true);
            btnSiguientePropietario.setVisible(true);
            borrarPropietario = false;

        }

        deshabilitarPropietario();
    }

    @FXML
    private void btnPropietarioCancelarOnClick(ActionEvent event) {
        deshabilitarPropietario();
    }

    public void deshabilitarPropietario() {
        txtDNI.setDisable(true);
        txtApellidos.setDisable(true);
        txtNombre.setDisable(true);
        btnPropietarioAceptar.setVisible(false);
        btnPropietarioCancelar.setVisible(false);
        btnAnteriorPropietario.setVisible(true);
        btnSiguientePropietario.setVisible(true);
    }

    int posicionCasa;

    @FXML
    private void btnAnteriorCasaOnClick(ActionEvent event) {
        if (posicionCasa != 0) {
            posicionCasa--;
        }
        txtID.setText(casas.getItems().get(posicionCasa).getId() + "");
        txtMetros.setText(casas.getItems().get(posicionCasa).getMetros() + "");
        txtDireccion.setText(casas.getItems().get(posicionCasa).getDireccion());
        txtPrecio.setText(casas.getItems().get(posicionCasa).getPrecio() + "");
        boolean tieneAscensor = casas.getItems().get(posicionCasa).isAscensor();
        cbxAscensor.setSelected(tieneAscensor);
        boolean tieneGaraje = casas.getItems().get(posicionCasa).isGaraje();
        cbxGaraje.setSelected(tieneGaraje);

    }

    @FXML
    private void btnSiguienteCasaOnClick(ActionEvent event) {
        if (posicionCasa != casas.getItems().size() - 1) {
            posicionCasa++;
        }
        txtID.setText(casas.getItems().get(posicionCasa).getId() + "");
        txtMetros.setText(casas.getItems().get(posicionCasa).getMetros() + "");
        txtDireccion.setText(casas.getItems().get(posicionCasa).getDireccion());
        txtPrecio.setText(casas.getItems().get(posicionCasa).getPrecio() + "");
        boolean tieneAscensor = casas.getItems().get(posicionCasa).isAscensor();
        cbxAscensor.setSelected(tieneAscensor);
        boolean tieneGaraje = casas.getItems().get(posicionCasa).isGaraje();
        cbxGaraje.setSelected(tieneGaraje);

    }

    boolean agregarCasa = false;

    @FXML
    private void btnAgregarCasaOnClick(ActionEvent event) {
        btnCasaAceptar.setVisible(true);
        btnCasaCancelar.setVisible(true);
        txtID.clear();
        txtID.setDisable(true);
        txtMetros.clear();
        txtMetros.setDisable(false);
        txtDireccion.clear();
        txtDireccion.setDisable(false);
        txtPrecio.clear();
        txtPrecio.setDisable(false);
        cbxAscensor.setSelected(false);
        cbxGaraje.setSelected(false);
        agregarCasa = true;
    }

    boolean borrarCasa = false;

    @FXML
    private void btnBorrarCasaOnClick(ActionEvent event) {
        btnCasaAceptar.setVisible(true);
        btnCasaCancelar.setVisible(true);
    }

    @FXML
    private void btnCasaAceptarOnClick(ActionEvent event) {
        if (agregarCasa) {

            String direccion = txtDireccion.getText();
            int metros = Integer.parseInt(txtMetros.getText());
            int precio = Integer.parseInt(txtPrecio.getText());
            //int id = Integer.parseInt(txtID.getText());
            boolean ascensor = cbxAscensor.isSelected();
            boolean garaje = cbxGaraje.isSelected();
            conectarDB.agregarCasa(direccion, metros, ascensor, garaje, precio);
            casas = new ListView<Casa>();
            extraerCasas();
            posicionCasa = casas.getItems().size() - 1;
            mostrarCasas();

        }
        if (modificarCasa) {
            String direccion = txtDireccion.getText();
            int metros = Integer.parseInt(txtMetros.getText());
            int precio = Integer.parseInt(txtPrecio.getText());
            int id = Integer.parseInt(txtID.getText());
            boolean ascensor = cbxAscensor.isSelected();
            boolean garaje = cbxGaraje.isSelected();
            conectarDB.modificarCasa(id, direccion, metros, ascensor, garaje, precio);
            casas = new ListView<Casa>();
            extraerCasas();
            mostrarCasas();
        }
        if (borrarCasa) {
            int id = Integer.parseInt(txtID.getText());
            conectarDB.borrarCasa(id);
            casas = new ListView<Casa>();
            extraerCasas();
            posicionCasa = casas.getItems().size() - 1;
            mostrarCasas();
        }
        deshabilitarCasa();
    }

    @FXML
    private void btnCasaCancelarOnClick(ActionEvent event) {
        deshabilitarCasa();
    }

    public void deshabilitarCasa() {
        btnCasaAceptar.setVisible(false);
        btnCasaCancelar.setVisible(false);
        txtID.setDisable(true);
        txtMetros.setDisable(true);
        txtDireccion.setDisable(true);
        txtPrecio.setDisable(true);
        cbxAscensor.setSelected(false);
        cbxGaraje.setSelected(false);
    }

    @FXML
    private void btnCancelarCasaPropietarioOnClick(ActionEvent event) {
    }

    @FXML
    private void btnAceptarCasaPropietarioOnClick(ActionEvent event) {
        if (agregarCasaPropietario) {
            int id = Integer.parseInt(txtIDCasa.getText());
            for (int i = 0; i < casas.getItems().size(); i++) {
                if (casas.getItems().get(i).getId() == id) {
                    propietarios.getSelectionModel().getSelectedItem().getCasas().get(i);

                    propietarios.getItems().get(posicionPropietarios).add(casas.getItems().get(i));
                    //casasListView.getItems().add(e)
                    txtMetros.setText(casasListView.getItems().get(i).getMetros() + "");
                }

            }

        }
        if (borrarCasaPropietario) {

            propietarios.getItems().get(posicionPropietarios).getCasas().remove(posCasasPropietario);
        }
    }

    boolean agregarCasaPropietario = false;

    @FXML
    private void btnAgregarCasaPropietarioOnClick(ActionEvent event) {
        txtDireccionCasa.clear();
        txtMetrosCasa.clear();
        txtPrecioCasa.clear();
        cbxAscensorCasa.setSelected(true);

        txtIDCasa.clear();
        txtIDCasa.setDisable(false);
        txtDireccionCasa.setDisable(true);
        txtMetrosCasa.setDisable(true);
        txtPrecioCasa.setDisable(true);
        cbxAscensorCasa.setDisable(true);

        btnCancelarCasaPropietario.setVisible(true);
        btnAceptarCasaPropietario.setVisible(true);
        agregarCasaPropietario = true;
    }

    boolean borrarCasaPropietario = false;

    @FXML
    private void btnBorrarCasaPropietarioOnClick(ActionEvent event) {
        btnCancelarCasaPropietario.setVisible(true);
        btnAceptarCasaPropietario.setVisible(true);
        txtDireccionCasa.setDisable(true);
        txtMetrosCasa.setDisable(true);
        txtPrecioCasa.setDisable(true);
        cbxAscensorCasa.setDisable(true);
        borrarCasaPropietario = true;
    }

    int posCasasPropietario = 0;
    ListView<Casa> casasListView;

    @FXML
    private void btnAnteriorCasaPropietarioOnClick(ActionEvent event) {
        if (posCasasPropietario > 0) {
            posCasasPropietario--;
        }
        int id = casasListView.getItems().get(posCasasPropietario).getId();
        String direccion = casasListView.getItems().get(posCasasPropietario).getDireccion();
        int metros = casasListView.getItems().get(posCasasPropietario).getMetros();
        int precio = (int) casasListView.getItems().get(posCasasPropietario).getPrecio();
        boolean ascensor = casasListView.getItems().get(posCasasPropietario).isAscensor();
        //casasArray.getItems().get(posCasasPropietario).isGaraje();
        //int id=casasPropietario.getItems().get(posCasasPropietario).get(this).getId();
        txtIDCasa.setText(id + "");
        txtDireccionCasa.setText(direccion);
        txtMetrosCasa.setText(metros + " ");
        txtPrecioCasa.setText(precio + " ");
        cbxAscensorCasa.setSelected(ascensor);

    }

    @FXML
    private void btnSiguienteCasaPropietarioOnClick(ActionEvent event) {
        if (posCasasPropietario < casasListView.getItems().size()) {
            posCasasPropietario++;
        }
        int id = casasListView.getItems().get(posCasasPropietario).getId();
        String direccion = casasListView.getItems().get(posCasasPropietario).getDireccion();
        int metros = casasListView.getItems().get(posCasasPropietario).getMetros();
        int precio = (int) casasListView.getItems().get(posCasasPropietario).getPrecio();
        boolean ascensor = casasListView.getItems().get(posCasasPropietario).isAscensor();
        //casasArray.getItems().get(posCasasPropietario).isGaraje();
        //int id=casasPropietario.getItems().get(posCasasPropietario).get(this).getId();
        txtIDCasa.setText(id + "");
        txtDireccionCasa.setText(direccion);
        txtMetrosCasa.setText(metros + " ");
        txtPrecioCasa.setText(precio + " ");
        cbxAscensorCasa.setSelected(ascensor);
    }

    @FXML
    private void btnCancelarPropietarioCasa(ActionEvent event) {

    }

    @FXML
    private void btnAceptarPropietarioCasaOnClick(ActionEvent event) {

    }

    @FXML
    private void btnAnteriorPropietarioCasaOnClick(ActionEvent event) {

    }

    @FXML
    private void btnSiguientePropietarioCasaOnClick(ActionEvent event) {

    }

    @FXML
    private void btnAgregarPropietarioCasaOnClick(ActionEvent event) {

    }
}
