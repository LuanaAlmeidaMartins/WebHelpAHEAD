
import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SizeButton {
	
	ArrayList<RadioMenuItem> item;
	SplitMenuButton botao ;
	SizeButtonStatus status;

	public SizeButton(String featureName) {
		status = new SizeButtonStatus(featureName);
		item = new ArrayList<>();
		botao = new SplitMenuButton();
		botao.setId(featureName);

		File file = new File("icons/" + featureName + ".png");
		Image image = new Image(file.toURI().toString(), 28, 28, false, false);
		botao.setGraphic(new ImageView(image));

		WebHelpBar.hbox.getChildren().add(botao);
	}

	public void opcao(String subFeatureName) {
		System.out.println(subFeatureName);
		RadioMenuItem aitem = new RadioMenuItem(subFeatureName);
		aitem.setId(botao.getId() + "." + subFeatureName);
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
						status.setStatusMenu();
						new ApplySizeButton(status).size();
					}
				});

				for (int i = 0; i < item.size(); i++) {
					int j = i;
					item.get(i).setOnAction(actionEvent -> {
						status.setStatusSubMenu();
						status.setSubButtonID(item.get(j).getId());
						new ApplySizeButton(status).size();
					});
				}
			}
		});
	}
}
