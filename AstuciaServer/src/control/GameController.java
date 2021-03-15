package control;

import comm.Receptor.OnMessageListener;

import java.util.Calendar;
import java.util.UUID;

import com.google.gson.Gson;

import comm.TCPConnection;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.*;
import view.GameWindow;

public class GameController implements OnMessageListener {

	private GameWindow view;
	private TCPConnection connection;

	public GameController(GameWindow view) {
		this.view = view;
		init();
	}

	public void init() {
		connection = TCPConnection.getInstance();
		connection.setListenerOfMessages(this);
		view.init();
		int fil = (int) (3 * Math.random());
		int col = (int) (3 * Math.random());
		view.drawWeakPointInRadar(fil, col);

		view.getSendNameBtn().setOnAction(event -> {
			Gson gson = new Gson();
			String json = gson.toJson(new Name("Name", view.getNameTF().getText()));
			TCPConnection.getInstance().getEmisor().sendMessage(json);
		});

		for (int i = 0; i < view.getAtaque().length; i++) {
			for (int i2 = 0; i2 < view.getAtaque()[i].length; i2++) {
				int[] cells = new int[2];
				cells[0] = i;
				cells[1] = i2;
				view.getAtaque()[i][i2].setOnAction(event -> {
					Gson gson = new Gson();
					String json = gson.toJson(new Attack("Attack", cells));
					TCPConnection.getInstance().getEmisor().sendMessage(json);

				});
			}
		}

	}

	@Override
	public void OnMessage(String msg) {
		Platform.runLater( // run on UI Thread
				() -> {
					Gson gson = new Gson();
					Name message = gson.fromJson(msg, Name.class);
					if (message.getType().equals("Name")) {
						view.setOpponentLabel(message.getName());
					} else {
						Attack messag = gson.fromJson(msg, Attack.class);
						if (messag.getType().equals("Attack")) {

							int[] weakPoint = view.getWeakPoint();
							if ((messag.getCells()[0] == weakPoint[0]) && (messag.getCells()[1] == weakPoint[1])) {
								view.setStatusLabel("El oponente te ha golpeado");

								Gson gsoon = new Gson();
								String json = gsoon.toJson(new Victory("Victory"));
								TCPConnection.getInstance().getEmisor().sendMessage(json);

								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Perdiste");
								alert.setHeaderText("Has perdido el juego");
								alert.setContentText("Tu oponente te ha ganado!");
								alert.showAndWait();
								init();
							} else {
								int a = messag.getCells()[0];
								int b = messag.getCells()[1];
								view.getRadar()[a][b].setStyle("-fx-background-color: red;");

							}

						} else {
							Gson gsooon = new Gson();
							Victory messa = gsooon.fromJson(msg, Victory.class);
							if (messag.getType().equals("Victory")) {
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Ganaste");
								alert.setHeaderText("Has ganado el juego");
								alert.setContentText("Le has ganado a tu oponente!");
								alert.showAndWait();
								init();

							}
						}

					}

				}

		);

	}

}