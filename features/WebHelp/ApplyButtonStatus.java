import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ApplyButtonStatus {
	private ArrayList<String> arrayOfGeneralStyle = new ArrayList<String>();
	private ArrayList<String> tags = new ArrayList<String>();
	private Document doc;
	boolean fonteStatus = false;
	boolean backgroundStatus = false;
	Element element, body;
	private String backgroundColor;

	public ApplyButtonStatus(Document doc) {
		Tags tags = new Tags();
		this.tags = tags.getTagsName();
		this.doc = doc;
	}

	public String getFontStyle() {
		String style = "";
		for (int i = 0; i < arrayOfGeneralStyle.size(); i++) {
			style += arrayOfGeneralStyle.get(i);
		}
		return style;
	}

	public void applyStyle() {
		body = (Element) doc.getElementsByTagName("body").item(0);
		body.setAttribute("style", backgroundColor);
		// passa em todas as tags
		for (int g = 0; g < tags.size(); g++) {
			for (int i = 0; i < doc.getElementsByTagName(tags.get(g)).getLength(); i++) {
				element = (Element) doc.getElementsByTagName(tags.get(g)).item(i);
				element.setAttribute("style", getFontStyle());
			}
		}
	}

	public void setFontStyle(String applyStyle, Boolean status) {
		if (status) {
			this.arrayOfGeneralStyle.add(applyStyle);
			System.out.println("if " + this.arrayOfGeneralStyle + " " + status);
		} else {
			removeFontStyle(applyStyle);
		}
		applyStyle();
	}

	public void removeFontStyle(String removeString) {
		for (int i = 0; i < arrayOfGeneralStyle.size(); i++) {
			if ((arrayOfGeneralStyle.get(i).contains(removeString))
					|| (arrayOfGeneralStyle.get(i).equals(removeString))) {
				arrayOfGeneralStyle.remove(i);
				System.out.println("else " + this.arrayOfGeneralStyle);
			}
		}
	}
}
