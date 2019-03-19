
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SimpleButtonStatus {
	private boolean align = false;
	private String buttonID;
	Properties property = new Properties();
	
	public SimpleButtonStatus(String id) {
		this.buttonID = id;
	}

	public void setStatus() {
		this.align = !align;
	}

	public boolean getStatus() {
		return align;
	}

	public String getButtonID() {
		return buttonID;
	}
	
	public String getStyle() {
		try {
			property.load(new FileInputStream(/*
												 * System.getProperty("user.dir") + "/style.txt"
												 */"style.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo style.txt nao encontrado");
		} catch (IOException e) {
			System.out.println("IO");
		}
		return property.getProperty(buttonID);
	}
}