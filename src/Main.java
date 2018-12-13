
import java.io.File;
import javafx.geometry.Insets;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

import javafx.scene.layout.StackPane;



abstract class Main$$WebHelpAHEAD extends Application {

	BotaoComposto linhas = null, caracteres = null, paragrafos = null, fonte =null, regua = null;
	BotaoColorPicker background = null, fonteColor=null, highlight=null, overlay=null;
	
	@Override
	public void start (final Stage stage ) {
		
		//Set application icon
		File file = new File("icons/webhelp.png");
		Image image = new Image(file.toURI().toString());
		stage.getIcons().add(image);
		
		//Set application home page
		String homePageUrl = "https://www.w3.org/TR/DOM-Level-2-Core/core.html#ID-F68F082";

		//Create opcaoTamanho WebView
		final WebView browser = new WebView();
		browser.setStyle("fx-padding:100;");
		
		// Get WebEngine via WebView and load home page
		final WebEngine webEngine = browser.getEngine();
		webEngine.load(homePageUrl);
		
		
		//When the website content is bigger than display area, the scroll pane is enabled
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(new Group());
		
		//Create the navigation bar
		//NavigationBar navigationBar = new NavigationBar(browser, homePageUrl);

		Canvas overlay = new Canvas(1300,600);
		overlay.setStyle("-fx-padding: 0, 0, 0, 0;");
		
		// Create the WebHelpDyslexia bar
		WebHelpBar webHelpBar = new WebHelpBar(browser, overlay);
		 
		StackPane stack = new StackPane();
	    stack.getChildren().addAll(browser, overlay);
	    stack.setMargin(browser, new Insets(12, 12, 10, 28));

	    new Main().createWebHelpBar();

		//Create the VBox, add the navigation, menu and webview to the VBox and
		VBox root = new VBox(webHelpBar, stack);
		//navigationBar,
		
		//Create the Scene, add the Scene to the Stage and display the Stage
		Scene scene = new Scene(root, 1600,900);
		stage.setScene(scene);

		stage.show();
 }

	public static void main(String[] args) {
		launch(args);
	}

	public void createWebHelpBar() {
		int i =0;
		System.out.println(i++);
	}
}


abstract class Main$$Overlay extends  Main$$WebHelpAHEAD  {
	public void createWebHelpBar() {
		overlay = new BotaoColorPicker("Overlay");
		super.createWebHelpBar() ;
	}
}

/**
 * TODO description
 */
abstract class Main$$Regua extends  Main$$Overlay  {
	public void createWebHelpBar() {
		regua = new BotaoComposto("Regua");
		super.createWebHelpBar() ;
	}
}

/**
 * TODO description
 */
abstract class Main$$Background extends  Main$$Regua  {

	public void createWebHelpBar() {
		System.out.println("entrou no createBack");
		background = new BotaoColorPicker("Background");
		super.createWebHelpBar() ;
	}
}

/**
 * TODO description
 */
abstract class Main$$Pequeno extends  Main$$Background  {
	
	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		
		if(caracteres!=null) {
			caracteres.opcao("Pequeno");
			caracteres.actionButton() ;
		}
		if(paragrafos!=null) {
			paragrafos.opcao("Pequeno");
			paragrafos.actionButton() ;
		}
		if(linhas!=null) {
			linhas.opcao("Pequeno");
			linhas.actionButton() ;
		}
		if(fonte!=null) {
			fonte.opcao("Pequeno");
			fonte.actionButton() ;
		}
		if(regua!=null) {
			regua.opcao("Pequeno");
			regua.actionButton() ;
		}
	}

}

abstract class Main$$Medio extends  Main$$Pequeno  {
	
	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		
		if(caracteres!=null) {
			caracteres.opcao("Medio");
			caracteres.actionButton() ;
		}
		if(paragrafos!=null) {
			paragrafos.opcao("Medio");
			paragrafos.actionButton() ;
		}
		if(linhas!=null) {
			linhas.opcao("Medio");
			linhas.actionButton() ;
		}
		if(fonte!=null) {
			fonte.opcao("Medio");
			fonte.actionButton() ;
		}
		if(regua!=null) {
			regua.opcao("Medio");
			regua.actionButton() ;
		}
	}

}


abstract class Main$$Cor extends  Main$$Medio  {
	
	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		
		if(background!=null) {
			background.opcao("Cor");
			background.actionButton();
		}
		
		if(fonteColor!=null) {
			fonteColor.opcao("Cor");
			fonteColor.actionButton();
		}
		if(highlight!=null) {
			highlight.opcao("Cor");
			highlight.actionButton();
		}
		if(overlay!=null) {
			overlay.opcao("Cor");
			overlay.actionButton();
		}
	}

}

/**
 * TODO description
 */
abstract class Main$$Fonte extends  Main$$Cor  {

	public void createWebHelpBar() {
		fonteColor = new BotaoColorPicker("Fonte");
		super.createWebHelpBar() ;
	}
}

/**
 * TODO description
 */
abstract class Main$$Linha extends  Main$$Fonte  {
	public void createWebHelpBar() {
		linhas = new BotaoComposto("Linhas");
		super.createWebHelpBar() ;
	}
}


public class Main extends  Main$$Linha  {
	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		BotaoSimples a = new BotaoSimples ("Negrito");
		a.action () ;
	}
}