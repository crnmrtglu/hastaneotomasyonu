package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch(str){
			
		case "fill":
			msg="Lütfen tüm alanları doldurunuz";
			break;
			
		case "success":
			msg="İşlem Başarılı";
			break;
			
			
				
			case "error":
				msg="Geçersiz işlem.";
				break;
				default:
					msg=str;
			
				
		}
		JOptionPane.showMessageDialog(null, msg,"Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}

	
	private static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText","iptal");
		UIManager.put("OptionPane.noButtonText","hayır");
		UIManager.put("OptionPane.okButtonText","tamam");
		UIManager.put("OptionPane.yesButtonText","evet");
		
		
	}


	public static boolean confirm(String str) {
		String msg;
		switch(str) {
		case "sure":
			msg="bu islemi gercekletirmek istiyor musun? ";
			break;
			default:
				msg=str;
				break;
		}
		int res=JOptionPane.showConfirmDialog(null, msg,"dikkat",JOptionPane.YES_NO_CANCEL_OPTION);
		if(res==0) {
			return true;
		}
		else {
			return false;
		}
	}

}
