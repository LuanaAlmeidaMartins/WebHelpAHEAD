
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

public class BotaoColorPicker extends HBox implements Types {
	private ColorPicker colorPicker;
	private BotaoColorPickerStatus status;

	public BotaoColorPicker(String nome) {
		System.out.println("nome do botao: " + nome);
		colorPicker = new ColorPicker();
		colorPicker.setId(nome);

	}

	public void opcao(String name) {
		colorPicker.setId(colorPicker.getId() + name);
		System.out.println("id:" + colorPicker.getId());
		colorPicker.setPrefHeight(38);
		colorPicker.setPrefWidth(68);
		colorPicker.getStyleClass().addAll("color-picker", "split-button");

		colorPicker.setStyle("-fx-background-image: url('/" + colorPicker.getId()
				+ ".png'); -fx-background-size: 28 28; "
				+ "-fx-background-position: 10px; -fx-background-repeat: no-repeat; "
				+ "-fx-color-label-visible: false; -fx-color-rect-width: 0px; " + "-fx-color-rect-height: 0px;");

		WebHelpBar.hbox.getChildren().add(colorPicker);
	}

	public void actionButton() {
		System.out.println("action");

		WebHelpBar.webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			status = new BotaoColorPickerStatus();
			if (newValue == State.SUCCEEDED) {
				System.out.println("State.SUCCEDED");
				Document doc = WebHelpBar.webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

				colorPicker.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						if (!e.getPickResult().toString().contains("arrow-button")) {
							colorPicker.hide();
							status.setStatusMenu();
							status.setColorName("#666");
							if(colorPicker.getId().contains("Overlay")) {
								WebHelpBar.overlay.setVisible(true);
								status.setForeground(Color.YELLOW);
								createOverlay(status, status.isActive());
							}
							if (colorPicker.getId().contains("Highlight")) {
								WebHelpBar.overlay.setVisible(false);
								aplicarCor(status.getStringToAdd(colorPicker.getId()));

							} else {
								WebHelpBar.applyButtonStatus.setFontStyle(status.getStringToAdd(colorPicker.getId()),
										status.isActive());
								WebHelpBar.applyButtonStatus.applyStyle();
							}

						}
					}
				});

				colorPicker.setOnAction((ActionEvent t) -> {
					status.setStatusSubMenu();
					if(colorPicker.getId().contains("Overlay")) {
						WebHelpBar.overlay.setVisible(true);
						status.setForeground(colorPicker.getValue());
						createOverlay(status, status.isActive());
					}
					status.setColorName(converterColor(colorPicker.getValue()));
					if (colorPicker.getId().contains("Highlight")) {
						aplicarCor(status.getStringToAdd(colorPicker.getId()));

					} else {
						WebHelpBar.applyButtonStatus.setFontStyle(status.getStringToAdd(colorPicker.getId()),
								status.isActive());
						WebHelpBar.applyButtonStatus.applyStyle();
					}
				});
			}
		});
	}

	public String converterColor(Color color) {
		return colorChanelToHex(color.getRed()) + colorChanelToHex(color.getGreen()) + colorChanelToHex(color.getBlue())
				+ colorChanelToHex(color.getOpacity());
	}

	private String colorChanelToHex(double chanelValue) {
		String rtn;
		rtn = Integer.toHexString((int) Math.min(Math.round(chanelValue * 255), 255));
		if (rtn.length() == 1) {
			rtn = "0" + rtn;
		}
		return rtn;
	}

	private void aplicarCor(String string) {
		WebHelpBar.webEngine
				.executeScript("var selection = window.getSelection();" + "var range = selection.getRangeAt(0);"
						+ "var newNode = document.createElement(\"span\");"
						+ "newNode.setAttribute(\"style\", \"background-color:" + string + ";\");"
						+ "range.surroundContents(newNode); ");
	}

	 public void createOverlay(BotaoColorPickerStatus color, boolean actived) {
		    GraphicsContext gc = WebHelpBar.overlay.getGraphicsContext2D();
		    WebHelpBar.overlay.setOpacity(0.5);
		    
		    if (actived == true) {
		      gc.setFill(Color.color(color.getRed(), color.getGreen(), color.getBlue()));
		      System.out.println(color.getRed()+" " +color.getGreen()+"  " +color.getBlue());
		      gc.fillRect(0, -20, 1600, 700);
		    } 
		    else {
		      gc.clearRect(0, -20, 1600, 700);
		    }
		    gc.fill();
		  }

}
