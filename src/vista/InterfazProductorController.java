package vista;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;
import logica.ControladoraProductor;
import modelo.Productor;

public class InterfazProductorController implements Initializable {
    
    ControladoraProductor controladoraProductor;

    //Necesitamos modificar por el tipo de valor que se va a alojar
    //por defecto private TableView<?> XXXXXXX;
    @FXML
    private TableView<Productor> tablaProductores;

    @FXML
    private TextField txtRazonSocial;

    @FXML
    private TextField txtCuit;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelar;
    
    //Inicializamos variables de contexto
    private ContextMenu cmOpciones;

    //Inicializamos variables Objeto para uso interno
    private Productor productorSeleccionado;

    public void limpiar() {
        txtRazonSocial.setText("");
        txtCuit.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtRazonSocial.requestFocus();
    }

    public void listarProductores() {

        controladoraProductor = new ControladoraProductor();

        //Limpiamos los items y columnas para asegurarnos
        tablaProductores.getItems().clear();
        tablaProductores.getColumns().clear();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<Productor> productores = this.controladoraProductor.listarProductores();
         
        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<Productor> data = FXCollections.observableArrayList(productores);
        //Seteamos los valores de las columnas
        TableColumn idCol = new TableColumn("Legajo");
        idCol.setCellValueFactory(new PropertyValueFactory("legajo"));

        TableColumn razonSocialCol = new TableColumn("Razon Social");
        razonSocialCol.setCellValueFactory(new PropertyValueFactory("razonSocial"));

        TableColumn cuitCol = new TableColumn("CUIT");
        cuitCol.setCellValueFactory(new PropertyValueFactory("cuit"));

        TableColumn direccionCol = new TableColumn("Direccion");
        direccionCol.setCellValueFactory(new PropertyValueFactory("direccion"));

        TableColumn telefonoCol = new TableColumn("Telefono");
        telefonoCol.setCellValueFactory(new PropertyValueFactory("telefono"));

        //Seteamos los valores del listado
        tablaProductores.setItems(data);
        tablaProductores.getColumns().addAll(idCol, razonSocialCol, cuitCol, direccionCol, telefonoCol);
    }

    private boolean chequear() {
        var chequear = true;
        // Cargo en variables los datos obtenidos de la interfaz       
        String razonSocial = txtRazonSocial.getText();
        String cuit = txtCuit.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();

        if (razonSocial.isBlank()) {
            chequear = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise la Razón Social");
            a.show();
            txtRazonSocial.requestFocus();
        }
        if (cuit.isBlank()) {
            chequear = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise el cuit");
            a.show();
            txtCuit.requestFocus();
        }
        if (direccion.isBlank()) {
            chequear = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise la dirección");
            a.show();
            txtDireccion.requestFocus();
        }
        if (telefono.isBlank()) {
            chequear = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise el teléfono");
            a.show();
            txtTelefono.requestFocus();
        }
        return chequear;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        limpiar();
        listarProductores();
        
        //Generamos el menu de contexto CLICK DERECHO
        cmOpciones = new ContextMenu();
        MenuItem miEditar = new MenuItem("Editar");

        MenuItem miEliminar = new MenuItem("Eliminar");
        cmOpciones.getItems().addAll(miEditar, miEliminar);

        //evento EDITAR
        miEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaProductores.getSelectionModel().getSelectedIndex();
                productorSeleccionado = tablaProductores.getItems().get(index);              
                txtRazonSocial.setText(productorSeleccionado.getRazonSocial());
                txtCuit.setText(productorSeleccionado.getCuit());
                txtDireccion.setText(productorSeleccionado.getDireccion());
                txtTelefono.setText(productorSeleccionado.getTelefono());
                btnCancelar.setDisable(false);
                btnNuevo.setDisable(true);
            }

        });
        
        //evento ELIMINAR
        miEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaProductores.getSelectionModel().getSelectedIndex();
                productorSeleccionado = tablaProductores.getItems().get(index);                
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirmación");
                a.setHeaderText(null);
                a.setContentText("Estás seguro de eliminar el Productor: " + productorSeleccionado.getRazonSocial()+ "?");
                a.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = a.showAndWait();               
                if (result.get() == ButtonType.OK){
                    try {
                    controladoraProductor = new ControladoraProductor();
                    controladoraProductor.eliminarProductor(productorSeleccionado.getLegajo(), 
                            productorSeleccionado.getRazonSocial(),productorSeleccionado.getCuit(),
                            productorSeleccionado.getDireccion(),productorSeleccionado.getTelefono());
                    // create a alert
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Productor eliminado correctamente");
                    a.show();
                    limpiar();
                    txtRazonSocial.requestFocus();
                    txtRazonSocial.selectAll();
                    listarProductores();
                } catch (Exception ex) {
                    a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al eliminar el Productor");
                    a.show();
                }
                }                               
            }
        });
        tablaProductores.setContextMenu(cmOpciones);
        
    }  
    
    @FXML
    void btnNuevoAction(ActionEvent event) {
        limpiar();
    }
    
    @FXML
    void btnAgregarAction(ActionEvent event) {
        if (productorSeleccionado == null) {
            if (chequear() == true) {
                // Cargo en variables los datos obtenidos de la interfaz       
                String razonSocial = txtRazonSocial.getText();
                String cuit = txtCuit.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                
                try {
                    //Invocamos al metodo de la controladora para dar de alta un nuevo Productor
                    controladoraProductor = new ControladoraProductor();
                    controladoraProductor.altaProductor(razonSocial, cuit, 
                            direccion, telefono);
                    // create a alert
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Productor registrado correctamente");
                    a.show();
                    limpiar();
                    txtRazonSocial.requestFocus();
                    txtRazonSocial.selectAll();
                    listarProductores();
                } catch (Exception ex) {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar el Productor");
                    a.show();
                }
            }
        } else {
            if (chequear() == true) {
                String razonSocial = txtRazonSocial.getText();
                String cuit = txtCuit.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                
                try {
                    controladoraProductor = new ControladoraProductor();
                    controladoraProductor.actualizarProductor(productorSeleccionado.getLegajo(),
                            razonSocial, cuit, direccion, telefono);
                    
                    // create a alert
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Productor actualizado correctamente");
                    a.show();
                    limpiar();
                    txtRazonSocial.requestFocus();
                    txtRazonSocial.selectAll();
                    btnCancelar.setDisable(true);
                    btnNuevo.setDisable(false);
                    listarProductores();
                } catch (Exception ex) {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar el Productor");
                    a.show();
                }
            }
        }
    }

    @FXML
    void btnCancelarAction(ActionEvent event) {
        limpiar();
        txtRazonSocial.requestFocus();
        txtRazonSocial.selectAll();
        btnCancelar.setDisable(true);
        btnNuevo.setDisable(false);
    }    
}
