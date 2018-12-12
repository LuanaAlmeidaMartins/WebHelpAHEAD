
import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;



abstract class Main$$WebHelpAHEAD extends Application {

	BotaoComposto linhas = null, caracteres = null, paragrafos = null, fonte =null;

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

		// Get WebEngine via WebView and load home page
		final WebEngine webEngine = browser.getEngine();
		webEngine.load(homePageUrl);
		
		//When the website content is bigger than display area, the scroll pane is enabled
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(new Group());
		
		//Create the navigation bar
		//NavigationBar navigationBar = new NavigationBar(browser, homePageUrl);

		// Create the WebHelpDyslexia bar
		WebHelpBar webHelpBar = new WebHelpBar(browser);
		new Main().createWebHelpBar();

		//Create the VBox, add the navigation, menu and webview to the VBox and
		VBox root = new VBox(webHelpBar, browser);
		//navigationBar,
		
		//Create the Scene, add the Scene to the Stage and display the Stage
		Scene scene = new Scene(root);
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

/**
 * TODO description
 */
abstract class Main$$Pequeno extends  Main$$WebHelpAHEAD  {
	
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
	}

}

abstract class Main$$Grande extends  Main$$Medio  {
	
	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		
		if(caracteres!=null) {
			caracteres.opcao("Grande");
			caracteres.actionButton() ;
		}
		
		if(paragrafos!=null) {
			paragrafos.opcao("Grande");
			paragrafos.actionButton() ;
		}
		
		if(linhas!=null) {
			linhas.opcao("Grande");
			linhas.actionButton() ;
		}
		
		if(fonte!=null) {
			fonte.opcao("Grande");
			fonte.actionButton() ;
		}
	}

}

abstract class Main$$Enorme extends  Main$$Grande  {
	
	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		
		if(caracteres!=null) {
			caracteres.opcao("Enorme");
			caracteres.actionButton() ;
		}
		
		if(paragrafos!=null) {
			paragrafos.opcao("Enorme");
			paragrafos.actionButton() ;
		}
		
		if(linhas!=null) {
			linhas.opcao("Enorme");
			linhas.actionButton() ;
		}
		
		if(fonte!=null) {
			fonte.opcao("Enorme");
			fonte.actionButton() ;
		}
	}

}

/**
 * TODO description
 */
abstract class Main$$Alinhamento extends  Main$$Enorme  {
	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		BotaoSimples a = new BotaoSimples ("Alinhamento");
		a.action () ;
	}
}

/**
 * TODO description
 */
abstract class Main$$Linha extends  Main$$Alinhamento  {
	public void createWebHelpBar() {
		linhas = new BotaoComposto("Linhas");
		super.createWebHelpBar() ;
	}
}


abstract class Main$$Italico extends  Main$$Linha  {
	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		BotaoSimples a = new BotaoSimples ("Italico");
		a.action () ;
	}
}


abstract class Main$$Sublinhado extends  Main$$Italico  {

	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		BotaoSimples a = new BotaoSimples ("Sublinhado");
		a.action () ;
	}
}


abstract class Main$$Negrito extends  Main$$Sublinhado  {
	public void createWebHelpBar() {
		super.createWebHelpBar() ;
		BotaoSimples a = new BotaoSimples ("Negrito");
		a.action () ;
	}
}


abstract class Main$$Paragrafo extends  Main$$Negrito  {
	public void createWebHelpBar() {
		paragrafos = new BotaoComposto("Paragrafos");
		super.createWebHelpBar() ;
	}
}


public class Main extends  Main$$Paragrafo  {
	
	public void createWebHelpBar() {
		caracteres = new BotaoComposto("Caracteres");
		super.createWebHelpBar() ;
	}
}