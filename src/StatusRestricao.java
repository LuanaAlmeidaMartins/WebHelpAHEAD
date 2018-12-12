import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class StatusRestricao {

	private boolean status = false;
	private final String removeOption = "letter-spacing";
	Properties property = new Properties();

	public boolean isCharSpacing() {
		return status;
	}

	public void setCharSpacing() {
		this.status = !status;
	}

	public void setOptionCharSpacing() {
		if (this.status == false) {
			this.status = !status;
			System.out.println("entrou if " + this.status);
		}
	}

	public String getCharSpacing(String type) {
		try {
			property.load(new FileInputStream(/*
												 * System.getProperty("user.dir") + "/style.txt"
												 */"style.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo style.txt nao encontrado");
		} catch (IOException e) {
			System.out.println("IO");
		}
		return property.getProperty(type);
	}

	public String getRemoveOption() {
		return removeOption;
	}
}
