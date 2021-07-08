package vista;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import controlador.ControladoraEmpleado;
import modelo.Empleado;

public class InterfazEmpleadoController implements Initializable {

    ControladoraEmpleado controladoraEmpleado;

    //Necesitamos modificar por el tipo de valor que se va a alojar
    //por defecto private TableView<?> XXXXXXX;
    @FXML
    private TableView<Empleado> tablaEmpleados;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombres;

    @FXML
    private TextField txtCuil;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private DatePicker dateFechaNacimiento;

    @FXML
    private DatePicker dateFechaIngreso;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelar;

    //Inicializamos variables de contexto
    private ContextMenu cmOpciones;

    //Inicializamos variables Objeto para uso interno
    private Empleado empleadoSeleccionado;

    //Creamos una variable Boolean para comprobar si es nuevo empleado o edicion
    boolean isEdit;

    public void limpiar() {
        txtApellido.setText("");
        txtNombres.setText("");
        txtCuil.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        dateFechaNacimiento.setValue(null);
        dateFechaIngreso.setValue(null);
        txtApellido.requestFocus();
    }

    public void listarEmpleados() {

        controladoraEmpleado = new ControladoraEmpleado();

        //Limpiamos los items y columnas para asegurarnos
        tablaEmpleados.getItems().clear();
        tablaEmpleados.getColumns().clear();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<Empleado> empleados = this.controladoraEmpleado.listarEmpleados();

        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<Empleado> data = FXCollections.observableArrayList(empleados);
        //Seteamos los valores de las columnas
        TableColumn idCol = new TableColumn("Legajo");
        idCol.setCellValueFactory(new PropertyValueFactory("legajo"));

        TableColumn apellidoCol = new TableColumn("Apellido");
        apellidoCol.setCellValueFactory(new PropertyValueFactory("apellido"));

        TableColumn nombresCol = new TableColumn("Nombres");
        nombresCol.setCellValueFactory(new PropertyValueFactory("nombres"));

        TableColumn cuilCol = new TableColumn("CUIL");
        cuilCol.setCellValueFactory(new PropertyValueFactory("cuil"));

        TableColumn direccionCol = new TableColumn("Direccion");
        direccionCol.setCellValueFactory(new PropertyValueFactory("direccion"));

        TableColumn telefonoCol = new TableColumn("Telefono");
        telefonoCol.setCellValueFactory(new PropertyValueFactory("telefono"));

        TableColumn fechaNacimientoCol = new TableColumn("Fecha Nac.");
        fechaNacimientoCol.setCellValueFactory(new PropertyValueFactory<Empleado, LocalDate>("fechaNacimiento"));

        TableColumn fechaIngresoCol = new TableColumn("Ingreso");
        fechaIngresoCol.setCellValueFactory(new PropertyValueFactory<Empleado, LocalDate>("fechaIngreso"));

        //Seteamos los valores del listado
        tablaEmpleados.setItems(data);
        tablaEmpleados.getColumns().addAll(idCol, apellidoCol, nombresCol, cuilCol, direccionCol, telefonoCol, fechaNacimientoCol, fechaIngresoCol);
    }

    private boolean chequear() {
        var chequear = true;
        // Cargo en variables los datos obtenidos de la interfaz       
        String apellido = txtApellido.getText();
        String nombres = txtNombres.getText();
        String cuil = txtCuil.getText();
        String direccion = txtDireccion.getText();
        String telefono = txtTelefono.getText();

        if (apellido.isBlank()) {
            chequear = false;
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Revise el Apellido");
            a.show();
            txtApellido.requestFocus();
        }
        if (nombres.isBlank()) {
            chequear = false;
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Revise el nombre");
            a.show();
            txtNombres.requestFocus();
        }
        if (cuil.isBlank()) {
            chequear = false;
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Revise el cuil");
            a.show();
            txtCuil.requestFocus();
        }
        if (direccion.isBlank()) {
            chequear = false;
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Revise la dirección");
            a.show();
            txtDireccion.requestFocus();
        }
        if (telefono.isBlank()) {
            chequear = false;
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Revise el teléfono");
            a.show();
            txtTelefono.requestFocus();
        }
        if (dateFechaNacimiento.getValue() == null) {
            chequear = false;
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Revise la Fecha de Nacimiento");
            a.show();
            dateFechaNacimiento.requestFocus();
        }
        if (dateFechaNacimiento.getValue().equals("")) {
            chequear = false;
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Revise la Fecha de Nacimiento");
            a.show();
            dateFechaNacimiento.requestFocus();
        }
        if (dateFechaIngreso.getValue() == null) {
            chequear = false;
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Revise la Fecha de Ingreso");
            a.show();
            dateFechaIngreso.requestFocus();
        }
        if (dateFechaIngreso.getValue().equals("")) {
            chequear = false;
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Revise la Fecha de Ingreso");
            a.show();
            dateFechaIngreso.requestFocus();
        }
        if ((dateFechaNacimiento.getValue().isAfter(dateFechaIngreso.getValue()))) {
            chequear = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("La fecha de Nacimiento debe ser anterior que la de ingreso");
            a.show();
        } 
        if (isEdit == false) {
            ///CHEQUEAMOS QUE NO SE HAYA REGISTRADO UN EMPLEADO CON EL MISMO CUIL
            controladoraEmpleado = new ControladoraEmpleado();
            //Obtenemos el listado de elementos
            // asociamos los datos
            List<Empleado> empleados = this.controladoraEmpleado.listarEmpleados();
            for (Empleado empleado : empleados) {
                if (empleado.getCuil().equals(cuil)) {
                    chequear = false;
                    Alert a = new Alert(AlertType.ERROR);
                    a.setHeaderText("El CUIL ya se encuentra registrado");
                    a.show();
                    txtCuil.requestFocus();
                    break;
                }
            }
        }
        return chequear;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isEdit = false;
        limpiar();
        listarEmpleados();

        //Generamos el menu de contexto CLICK DERECHO
        cmOpciones = new ContextMenu();
        MenuItem miEditar = new MenuItem("Editar");

        MenuItem miEliminar = new MenuItem("Eliminar");
        cmOpciones.getItems().addAll(miEditar, miEliminar);

        //Evento EDITAR
        miEditar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int index = tablaEmpleados.getSelectionModel().getSelectedIndex();
                empleadoSeleccionado = tablaEmpleados.getItems().get(index);
                System.out.println(empleadoSeleccionado);
                txtApellido.setText(empleadoSeleccionado.getApellido());
                txtNombres.setText(empleadoSeleccionado.getNombres());
                txtCuil.setText(empleadoSeleccionado.getCuil());
                txtDireccion.setText(empleadoSeleccionado.getDireccion());
                txtTelefono.setText(empleadoSeleccionado.getTelefono());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                dateFechaNacimiento.setValue(LocalDate.parse(empleadoSeleccionado.getFechaNacimiento().toString(), formatter));
                dateFechaIngreso.setValue(LocalDate.parse(empleadoSeleccionado.getFechaIngreso().toString(), formatter));

                isEdit = true;
                btnCancelar.setDisable(false);
                btnNuevo.setDisable(true);
            }

        });

        //Evento ELIMINAR
        miEliminar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int index = tablaEmpleados.getSelectionModel().getSelectedIndex();
                empleadoSeleccionado = tablaEmpleados.getItems().get(index);

                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirmación");
                a.setHeaderText(null);
                a.setContentText("Estás seguro de eliminar el Empleado: " + empleadoSeleccionado.getApellido() + ", " + empleadoSeleccionado.getNombres() + "?");
                a.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = a.showAndWait();

                if (result.get() == ButtonType.OK) {
                    try {
                        controladoraEmpleado = new ControladoraEmpleado();
                        controladoraEmpleado.eliminarEmpleado(empleadoSeleccionado.getLegajo(),
                                empleadoSeleccionado.getApellido(), empleadoSeleccionado.getNombres(),
                                empleadoSeleccionado.getCuil(), empleadoSeleccionado.getDireccion(),
                                empleadoSeleccionado.getTelefono(), empleadoSeleccionado.getFechaNacimiento(),
                                empleadoSeleccionado.getFechaIngreso());

                        // create a alert
                        a = new Alert(AlertType.INFORMATION);
                        a.setTitle("Exito");
                        a.setHeaderText(null);
                        a.setContentText("Empleado eliminado correctamente");
                        a.show();
                        limpiar();
                        txtApellido.requestFocus();
                        txtApellido.selectAll();
                        listarEmpleados();
                    } catch (Exception ex) {
                        a = new Alert(AlertType.ERROR);
                        a.setTitle("Error");
                        a.setHeaderText(null);
                        a.setContentText("Ocurrió un error al eliminar el Empleado");
                        a.show();
                    }
                }

            }

        });
        tablaEmpleados.setContextMenu(cmOpciones);
    }

    @FXML
    private void btnNuevoAction(javafx.event.ActionEvent event) {
        limpiar();
    }

    @FXML
    private void btnAgregarAction(javafx.event.ActionEvent event) {
        
        //Si es un nuevo empleado
        if (empleadoSeleccionado == null) {
            if (chequear() == true) {
                // Cargo en variables los datos obtenidos de la interfaz       
                String apellido = txtApellido.getText();
                String nombres = txtNombres.getText();
                String cuil = txtCuil.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                //Obtenemos Fecha Nacimiento
                LocalDate fechaNacimiento = dateFechaNacimiento.getValue();

                //Obtenemos Fecha Ingreso
                LocalDate fechaIngreso = dateFechaIngreso.getValue();

                try {
                    //Invocamos al metodo de la controladora para dar de alta un nuevo Empleado
                    controladoraEmpleado = new ControladoraEmpleado();
                    controladoraEmpleado.altaEmpleado(apellido, nombres, cuil,
                            direccion, telefono, fechaNacimiento, fechaIngreso);

                    // create a alert
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Empleado registrado correctamente");
                    a.show();
                    limpiar();
                    txtApellido.requestFocus();
                    txtApellido.selectAll();
                    listarEmpleados();
                } catch (Exception ex) {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar el Empleado");
                    a.show();
                }
            }
            
        //Si actualizo un empleado
        } else {
            if (chequear() == true) {
                String apellido = txtApellido.getText();
                String nombres = txtNombres.getText();
                String cuil = txtCuil.getText();
                String direccion = txtDireccion.getText();
                String telefono = txtTelefono.getText();
                //Obtenemos Fecha Nacimiento
                LocalDate fechaNacimiento = dateFechaNacimiento.getValue();
                //Obtenemos Fecha Ingreso
                LocalDate fechaIngreso = dateFechaIngreso.getValue();

                try {
                    controladoraEmpleado = new ControladoraEmpleado();
                    controladoraEmpleado.actualizarEmpleado(empleadoSeleccionado.getLegajo(),
                            apellido, nombres, cuil, direccion, telefono, fechaNacimiento, fechaIngreso);

                    // create a alert
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Empleado actualizado correctamente");
                    a.show();
                    limpiar();
                    isEdit = false;
                    txtApellido.requestFocus();
                    txtApellido.selectAll();
                    btnCancelar.setDisable(true);
                    btnNuevo.setDisable(false);
                    listarEmpleados();
                } catch (Exception ex) {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar el Empleado");
                    a.show();
                }
            }
        }
    }

    @FXML
    private void btnCancelarAction(javafx.event.ActionEvent event) {
        isEdit = false;
        limpiar();
        txtApellido.requestFocus();
        txtApellido.selectAll();
        btnCancelar.setDisable(true);
        btnNuevo.setDisable(false);
    }

    public void closeWindows() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaPrincipal.fxml"));
            //Genero una ruta absoluta de la vista con lo siguiente:

            Parent root = loader.load();

            VistaPrincipalController controlador = loader.getController();

            //URI uri = Paths.get("src/vista/InterfazEmpleado.fxml").toAbsolutePath().toUri();
            //Parent root;
            //root = FXMLLoader.load(uri.toURL());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (MalformedURLException ex) {
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
