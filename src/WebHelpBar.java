
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javafx.scene.canvas.Canvas;

public class WebHelpBar extends HBox {
	static HBox hbox;
	static WebEngine webEngine;
	static ApplyButtonStatus applyButtonStatus;
	static Canvas overlay;

	public WebHelpBar(WebView web, Canvas overlay) {
		WebHelpBar.overlay = overlay;
		this.setSpacing(2);
		this.setStyle("-fx-padding: 0, 0, 0, 0;");
		
		System.out.println("Entrou no WebHelp");
		
		WebHelpBar.webEngine = web.getEngine();
		hbox = new HBox();
		
		this.getChildren().add(hbox);
	}
	
}
