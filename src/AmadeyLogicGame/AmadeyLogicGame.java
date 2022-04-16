package AmadeyLogicGame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AmadeyLogicGame {

    private static Stage newStage;
    private static CirSim sim;

    public static Scene load(String[] args) {

        FXMLLoader loader =  new FXMLLoader(ClassLoader.getSystemResource("AmadeyLogicGame/ALG.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        newStage = new Stage();
        newStage.setScene(new Scene(root, 800, 600));

        sim = loader.getController();
        sim.Start(newStage, args[0]);

        return newStage.getScene();

    }

    public static void terminateApp(){
        sim.terminateSim();
        sim = null;
        newStage.close();
    }

}
