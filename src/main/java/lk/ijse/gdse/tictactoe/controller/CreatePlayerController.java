package lk.ijse.gdse.tictactoe.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CreatePlayerController {
    public TextField txtName;
    public JFXButton PlayBtn;

    public void CreatePlayerOnAction(ActionEvent event) {
        String name = txtName.getText();
        if (name.isEmpty()) {
            showError("Name cannot be empty!");
            return;
        }
        if (!name.matches("[A-Za-z ]+")) {
            showError("Name can only contain letters and spaces!");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Board.fxml"));
            Pane newRoot = loader.load();
            //given player name
            BoardController controller =loader.getController();
            controller.setPlayerName(name);

            Stage newStage = new Stage(StageStyle.DECORATED);
            newStage.setScene(new Scene(newRoot));
            newStage.show();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to load the new scene.");
        }

    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
