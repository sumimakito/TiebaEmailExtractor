import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

public class TextVerifyListener implements VerifyListener{

	private int type;
	public TextVerifyListener(int type){
		this.type=type;
	}
	public void verifyText(VerifyEvent e) {
		// TODO Auto-generated method stub
		if(type==1){//只能输入数字
			 boolean b = "0123456789".indexOf(e.text) >= 0 ;
             e.doit = b;  //doit属性如果为true,则字符允许输入,反之不允许
             return;
		}
	}
	
}
