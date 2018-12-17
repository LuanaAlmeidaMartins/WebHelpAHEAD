import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

public class Leitor {
	
	Leitor(){
		 try {
		      System.setProperty("freetts.voices",
		          "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		      Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
		      Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
		      synthesizer.allocate();
		      synthesizer.speakPlainText(
		          WebHelpBar.webEngine.executeScript("window.getSelection();").toString(), null);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}

}
