package vista;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VistaPrincipalController implements Initializable {

    @FXML
    private Button btnEmpleado;

    @FXML
    private Button btnProductor;

    @FXML
    private Button btnLote;

    @FXML
    private Button btnCosecha;

    @FXML
    private Button btnReporte;

    @FXML
    private Button btnSalir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void btnEmpleadoAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InterfazEmpleado.fxml"));
            //Genero una ruta absoluta de la vista con lo siguiente:
            
            Parent root = loader.load();
            
            InterfazEmpleadoController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            //stage.setOnCloseRequest(e -> controlador.closeWindows());
            
            Stage myStage = (Stage) this.btnEmpleado.getScene().getWindow();
            //myStage.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void btnProductorAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InterfazProductor.fxml"));
            //Genero una ruta absoluta de la vista con lo siguiente:
            
            Parent root = loader.load();
            
            InterfazProductorController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            //stage.setOnCloseRequest(e -> controlador.closeWindows());
            
            Stage myStage = (Stage) this.btnProductor.getScene().getWindow();
            //myStage.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnLoteAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InterfazLote.fxml"));
            //Genero una ruta absoluta de la vista con lo siguiente:
            
            Parent root = loader.load();
            
            InterfazLoteController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            //stage.setOnCloseRequest(e -> controlador.closeWindows());
            
            Stage myStage = (Stage) this.btnLote.getScene().getWindow();
            //myStage.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnCosechaAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InterfazCosecha.fxml"));
            //Genero una ruta absoluta de la vista con lo siguiente:
            
            Parent root = loader.load();
            
            InterfazCosechaController controlador = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            //stage.setOnCloseRequest(e -> controlador.closeWindows());
            
            Stage myStage = (Stage) this.btnCosecha.getScene().getWindow();
            //myStage.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnReporteAction(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/InterfazReporte.fxml"));
            //Genero una ruta absoluta de la vista con lo siguiente:
            
            Parent root = loader.load();
            
            InterfazReporteController controlador = loader.getController();
                        
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            //stage.setOnCloseRequest(e -> controlador.closeWindows());
            
            Stage myStage = (Stage) this.btnReporte.getScene().getWindow();
            //myStage.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(VistaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnSalirAction(ActionEvent event) {
        Platform.exit();
    }

}
