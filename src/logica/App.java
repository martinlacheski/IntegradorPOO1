package logica;

//import vista.InterfazEmpleado;

import java.net.URI;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {       
        
        //Genero una ruta absoluta de la vista con lo siguiente:
        //URI uri = Paths.get("src/vista/InterfazEmpleado.fxml").toAbsolutePath().toUri();
        //URI uri = Paths.get("src/vista/InterfazProductor.fxml").toAbsolutePath().toUri();
        //URI uri = Paths.get("src/vista/InterfazLote.fxml").toAbsolutePath().toUri();
        URI uri = Paths.get("src/vista/InterfazCosecha.fxml").toAbsolutePath().toUri();
        
        Parent root = FXMLLoader.load(uri.toURL());
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
}

    public static void main(String[] args) {

        launch(args);
        //InterfazEmpleado ventana = new InterfazEmpleado();
        //ventana.setVisible(true);
        //ventana.setLocationRelativeTo(null);
    }
    
}
