import java.io.File;

import org.w3c.dom.Document;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javafx.scene.control.Button;

public class BotaoSimples {
	Button botao;
	
	public BotaoSimples(String a) {
	botao = new Button();
	botao.setId(a);
	File file = new File("icons/" + a + ".png");
	Image image = new Image(file.toURI().toString(), 28, 28, false, false);
	botao.setGraphic(new ImageView(image));
	System.out.println(botao.getId());
}

public void action() {
	StatusSemRestricao statusSemRestricao = new StatusSemRestricao();
	WebHelpBar.webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
		if (newValue == State.SUCCEEDED) {
			Document doc = WebHelpBar.webEngine.getDocument();
			WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

			botao.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					statusSemRestricao.setStatus();
					WebHelpBar.applyButtonStatus.setFontStyle(statusSemRestricao.getStyle(botao.getId()),
							statusSemRestricao.getStatus());
				}
			});
		}
	});
	WebHelpBar.hbox.getChildren().add(botao);
}

}
