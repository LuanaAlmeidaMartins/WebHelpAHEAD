
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class StatusSemRestricao {
	private boolean align = false;
	Properties property = new Properties();

	public void setStatus() {
		this.align = !align;
	}

	public boolean getStatus() {
		return align;
	}

	public String getStyle(String id) {
		try {
			property.load(new FileInputStream(/*
												 * System.getProperty("user.dir") + "/style.txt"
												 */"style.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo style.txt nao encontrado");
		} catch (IOException e) {
			System.out.println("IO");
		}
		return property.getProperty(id);
	}
}