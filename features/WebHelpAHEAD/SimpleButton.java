
import java.io.File;
import java.util.Locale;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import org.w3c.dom.Document;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

public class SimpleButton{
	
	Button botao;
	
	public SimpleButton(String a) {
	botao = new Button();
	botao.setId(a);
	File file = new File("icons/" + a + ".png");
	Image image = new Image(file.toURI().toString(), 28, 28, false, false);
	botao.setGraphic(new ImageView(image));
	System.out.println(botao.getId());
	
	WebHelpBar.hbox.getChildren().add(botao);
	}

	public void action() {
		SimpleButtonStatus status = new SimpleButtonStatus(botao.getId());
		WebHelpBar.webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = WebHelpBar.webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);
	
				botao.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						status.setStatus();
						new ApplyButton(status).simple();
					}
				});
			}
		});		
	}
}
