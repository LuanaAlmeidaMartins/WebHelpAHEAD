import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class BotaoComposto {
	
	ArrayList<RadioMenuItem> item;
	SplitMenuButton botao ;
	StatusRestricao statusRestricao;

	public BotaoComposto(String a) {
		statusRestricao = new StatusRestricao();
		item = new ArrayList<>();
		botao = new SplitMenuButton();
		botao.setId(a);

		File file = new File("icons/" + a + ".png");
		Image image = new Image(file.toURI().toString(), 28, 28, false, false);
		botao.setGraphic(new ImageView(image));

		WebHelpBar.hbox.getChildren().add(botao);
	}

	public void opcao(String a) {
		System.out.println("Botao composto subitem"+ a);
		
		RadioMenuItem aitem = new RadioMenuItem(a);
		aitem.setId(botao.getId() + "." + a);
		item.add(aitem);

		ToggleGroup charSpacingGroup = new ToggleGroup();
		charSpacingGroup.getToggles().add(aitem);
		botao.getItems().add(aitem);
	}

	public void actionButton() {
		WebHelpBar.webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = WebHelpBar.webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

				botao.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						statusRestricao.setCharSpacing();
						if(botao.getId().contains("Regua")) {
							WebHelpBar.overlay.setVisible(true);
							createRuler(statusRestricao.getCharSpacing(botao.getId()), 
									statusRestricao.isCharSpacing());
						}
						else {
						WebHelpBar.applyButtonStatus.setFontStyle(statusRestricao.getCharSpacing(botao.getId()),
								statusRestricao.isCharSpacing());
						}
					}
				});

				for (int i = 0; i < item.size(); i++) {
					int j = i;
					item.get(i).setOnAction(actionEvent -> {
						statusRestricao.setOptionCharSpacing();
						if(botao.getId().contains("Regua")) {
							WebHelpBar.overlay.setVisible(true);
							createRuler(statusRestricao.getCharSpacing(item.get(j).getId()), 
									statusRestricao.isCharSpacing());
						}
						else {
						WebHelpBar.applyButtonStatus.removeFontStyle(statusRestricao.getRemoveOption());
						WebHelpBar.applyButtonStatus.setFontStyle(statusRestricao.getCharSpacing(item.get(j).getId()),
								statusRestricao.isCharSpacing());
						}
					});
				}
			}
		});
	}
	
	public void createRuler(String numeroString, boolean actived) {
	    GraphicsContext gc = WebHelpBar.overlay.getGraphicsContext2D();
	    WebHelpBar.overlay.setOpacity(0.8);
	    gc.setFill(Color.color(0.0, 0.0, 0.0));
	    double num = Double.parseDouble(numeroString);
	    if (actived == true) {
	      gc.fillRect(0, -20, 1600, 700);
	      gc.clearRect(0, 80, 1600, num);
	    } else {
	      gc.clearRect(0, -20, 1600, 700);
	    }
	    gc.fill();
	    as();
	  }

	public void as() {
		
	}
}
