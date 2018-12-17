
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;

import org.w3c.dom.Document;

public class ColorButton implements Types {
	private ColorPicker colorPicker;
	private ColorButtonStatus status;

	public ColorButton(String nome) {
		colorPicker = new ColorPicker();
		colorPicker.setId(nome);
		colorPicker.setPrefHeight(38);
		colorPicker.setPrefWidth(68);
		colorPicker.getStyleClass().addAll("color-picker", "split-button");
		colorPicker.setStyle("-fx-background-image: url('/" + colorPicker.getId()
				+ ".png'); -fx-background-size: 28 28; "
				+ "-fx-background-position: 10px; -fx-background-repeat: no-repeat; "
				+ "-fx-color-label-visible: false; -fx-color-rect-width: 0px; " 
				+ "-fx-color-rect-height: 0px;");
		status = new ColorButtonStatus(Color.YELLOW, colorPicker.getId());
		WebHelpBar.hbox.getChildren().add(colorPicker);
	}

	public void actionButton() {
		WebHelpBar.webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			status = new ColorButtonStatus(Color.YELLOW, colorPicker.getId());
			if (newValue == State.SUCCEEDED) {
				Document doc = WebHelpBar.webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);
				colorPicker.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						if (!e.getPickResult().toString().contains("arrow-button")) {
							colorPicker.hide();
							status.setStatusMenu();
							new ApplyButton(status).foo();
						}
					}
				});
				colorPicker.setOnAction((ActionEvent t) -> {
					status.setStatusSubMenu();
					status.setColor(colorPicker.getValue());
					new ApplyButton(status).foo();
				});
			}
		});
	}
}
