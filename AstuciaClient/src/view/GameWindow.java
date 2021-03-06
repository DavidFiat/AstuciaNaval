package view;

import java.io.IOException;

import control.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameWindow extends Stage {

	// UI Elements
	private Scene scene;
	private GameController contol;
	private Label[][] radar;
	private Button[][] ataque;
	private TextField nameTF;
	private Button sendNameBtn;
	private Label opponentLabel;
	private Label statusLabel;
	@FXML
	private AnchorPane anchorPane;

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	private int[] weakPoint;
	private int[] attackPoint;

	public int[] getWeakPoint() {
		return weakPoint;
	}

	public GameWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
			Parent parent = loader.load();
			scene = new Scene(parent, 487, 421);
			this.setScene(scene);

			// Referencias
			radar = new Label[3][3];
			radar[0][0] = (Label) loader.getNamespace().get("radar00");
			radar[0][1] = (Label) loader.getNamespace().get("radar01");
			radar[0][2] = (Label) loader.getNamespace().get("radar02");
			radar[1][0] = (Label) loader.getNamespace().get("radar10");
			radar[1][1] = (Label) loader.getNamespace().get("radar11");
			radar[1][2] = (Label) loader.getNamespace().get("radar12");
			radar[2][0] = (Label) loader.getNamespace().get("radar20");
			radar[2][1] = (Label) loader.getNamespace().get("radar21");
			radar[2][2] = (Label) loader.getNamespace().get("radar22");

			ataque = new Button[3][3];
			ataque[0][0] = (Button) loader.getNamespace().get("ataque00");
			ataque[0][1] = (Button) loader.getNamespace().get("ataque01");
			ataque[0][2] = (Button) loader.getNamespace().get("ataque02");
			ataque[1][0] = (Button) loader.getNamespace().get("ataque10");
			ataque[1][1] = (Button) loader.getNamespace().get("ataque11");
			ataque[1][2] = (Button) loader.getNamespace().get("ataque12");
			ataque[2][0] = (Button) loader.getNamespace().get("ataque20");
			ataque[2][1] = (Button) loader.getNamespace().get("ataque21");
			ataque[2][2] = (Button) loader.getNamespace().get("ataque22");

			nameTF = (TextField) loader.getNamespace().get("nameTF");
			sendNameBtn = (Button) loader.getNamespace().get("sendNameBtn");
			opponentLabel = (Label) loader.getNamespace().get("opponentLabel");
			statusLabel = (Label) loader.getNamespace().get("statusLabel");
			weakPoint = new int[2];
			attackPoint = new int[2];
			anchorPane = new AnchorPane();

			contol = new GameController(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Label[][] getRadar() {
		return radar;
	}

	public Button[][] getAtaque() {
		return ataque;
	}

	public TextField getNameTF() {
		return nameTF;
	}

	public Button getSendNameBtn() {
		return sendNameBtn;
	}

	public Label getOpponentLabel() {
		return opponentLabel;
	}

	public Label getStatusLabel() {
		return statusLabel;
	}

	// UI Actions
	public void drawAttackInRadar(int fil, int col) {
		radar[fil][col].setStyle("-fx-background-color: red;");
		setAttackInRadar(fil, col);
	}

	private void setAttackInRadar(int fil, int col) {
		attackPoint[0] = fil;
		attackPoint[1] = col;
	}

	public void drawWeakPointInRadar(int fil, int col) {
		radar[fil][col].setStyle("-fx-background-color: yellow;");
		setWeakPoint(fil, col);
	}

	private void setWeakPoint(int fil, int col) {
		weakPoint[0] = fil;
		weakPoint[1] = col;
	}

	public void setOpponentLabel(String label) {
		opponentLabel.setText(label);
	}

	public void setStatusLabel(String label) {
		opponentLabel.setText(label);

	}

	public void init() {
		for (int i = 0; i < radar.length; i++) {
			for (int i2 = 0; i2 < radar[i].length; i2++) {
				radar[i][i2].setStyle("-fx-background-color: blue;");
			}
		}
	}
}
