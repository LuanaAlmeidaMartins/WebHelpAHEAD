
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;



public class WebHelpBar extends HBox {
	static HBox hbox;
	static WebEngine webEngine;
	static ApplyButtonStatus applyButtonStatus;


	public WebHelpBar(WebView web) {
		System.out.println("Entrou no WebHelp");
		WebHelpBar.webEngine = web.getEngine();
		hbox = new HBox();
		this.getChildren().add(hbox);
	}
}