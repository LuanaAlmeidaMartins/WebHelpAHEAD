import javafx.scene.paint.Color;

public class ColorButtonStatus {

	private boolean status = false;
	private Color color;
	private String buttonID;
		
	public ColorButtonStatus(Color value, String id) {
		this.buttonID = id;
		setColor(value);
	}
	
	public void setStatusMenu() {
		System.out.println("entrou aqui");
		this.status = !status;
	}

	public void setStatusSubMenu() {
		if (!this.status) {
			this.status = !status;
		}
	}
		
	public void setColor(Color value) {
		this.color = value;
	}
		
	public boolean isActive() {
		return status;
	}
		
	public String getButtonID(){
		return buttonID;
	}
		
	public Color getColor() {
		return color;
	}
}
