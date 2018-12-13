import javafx.scene.paint.Color;

public class BotaoColorPickerStatus {
	private boolean status = false;
	private String defaultOption = "";
	private String color;
	double blue = 0.3, red = 1.0, green = 1.0;

	public boolean isActive() {
		return status;
	}

	public void setStatusMenu() {
		this.status = !status;
	}

	public void setStatusSubMenu() {
		if (!this.status) {
			this.status = !status;
			System.out.println("entrou if " + this.status);
		}
	}

	public void setColorName(String color) {
		this.color = color;
	}

	public String getStringToAdd(String type) {
		if(type.equals("FonteCor")) {
			defaultOption = "color: #"+color+";";
		}
		if(type.equals("BackgroundCor")) {
			defaultOption = "background-color: #"+ color + ";";
		}
		if(type.equals("HighlightCor")) {
			defaultOption = "#" + color + ";";
		}
		return defaultOption;
	}

	public String getStringToRemove() {
		return null;
	}

	  public void setForeground(Color value) {
	    this.blue = value.getBlue();
	    this.green=value.getGreen();
	    this.red=getRed();
	  }

	  public double getBlue() {
	    return blue;
	  }

	  public double getRed() {
	    return red;
	  }

	  public double getGreen() {
	    return green;
	  }
}
