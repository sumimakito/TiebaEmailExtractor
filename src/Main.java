import java.util.ArrayList;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ProgressBar;

import sun.java2d.pipe.DuctusRenderer;


public class Main {

	protected Shell shlemail;
	private Text text;
	private Text text_1;
	private Label lblemail_1;
	private List list;
	private Text text_2;
	private static Label label_4;
	private static ProgressBar progressBar;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlemail.open();
		shlemail.layout();
		while (!shlemail.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public void doExt(String pid,int p) {
		DataUtils du = new DataUtils();
		java.util.List<String> l = du.getPost(""+pid, p);
		if((l!=null)&&(l.size()!=0)){
			for(int i=0;i<l.size();i++){
				list.add(l.get(i));
			}
		}
		String s = l.toString().replace("[", "").replace("]", "");
		text_1.setText(s);
		label_4.setText("提取完毕。");
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlemail = new Shell(SWT.CLOSE);
		shlemail.setSize(443, 771);
		shlemail.setText("百度贴吧 帖子Email地址提取工具");
		
		label_4 = new Label(shlemail, SWT.NONE);
		label_4.setText("准备就绪。");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		label_4.setBounds(8, 662, 418, 18);
		
		Label label = new Label(shlemail, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(8, 76, 418, 11);
		
		Label lblNewLabel = new Label(shlemail, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel.setBounds(7, 13, 56, 18);
		lblNewLabel.setText("帖子ID:");
		
		lblemail_1 = new Label(shlemail, SWT.NONE);
		lblemail_1.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblemail_1.setBounds(7, 385, 357, 18);
		lblemail_1.setText("已匹配的Email地址(文本) 可直接粘贴至邮箱的\"收件人\"一栏");
		
		text = new Text(shlemail, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(60, 10, 148, 24);
		//text.addVerifyListener(new TextVerifyListener(1)); 
		
		Button btnNewButton = new Button(shlemail, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		btnNewButton.setBounds(343, 7, 81, 28);
		btnNewButton.setText("提取");
		
		btnNewButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("widgetSelected");
				text_1.setText("");
				list.removeAll();
				label_4.setText("Processing...");
				String p = text.getText();
				int maxPage = Integer.parseInt(text_2.getText());
				progressBar.setMaximum(maxPage);
				progressBar.setMinimum(0);
				progressBar.setSelection(0);
				doExt(p,maxPage);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		text_1 = new Text(shlemail, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP
		        | SWT.V_SCROLL | SWT.MULTI);
		text_1.setBounds(7, 407, 420, 236);
		
		list = new List(shlemail, SWT.BORDER | SWT.V_SCROLL);
		list.setBounds(7, 105, 420, 262);
		
		Label label_1 = new Label(shlemail, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(7, 373, 420, 11);
		
		Label lblemail = new Label(shlemail, SWT.NONE);
		lblemail.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		lblemail.setBounds(7, 85, 155, 18);
		lblemail.setText("已匹配的Email地址(列表)");
		
		Label lblSumimakito = new Label(shlemail, SWT.NONE);
		lblSumimakito.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BORDER));
		lblSumimakito.setBounds(8, 721, 361, 14);
		lblSumimakito.setText("Weibo/Github: SumiMakito");
		
		Label label_2 = new Label(shlemail, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(8, 713, 420, 2);
		
		Label label_3 = new Label(shlemail, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(214, 13, 69, 18);
		label_3.setText("最大页码:");
		
		text_2 = new Text(shlemail, SWT.BORDER);
		text_2.setBounds(281, 10, 56, 24);
		
		Label label_5 = new Label(shlemail, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_5.setBounds(8, 649, 418, 11);
		
		progressBar = new ProgressBar(shlemail, SWT.NONE);
		progressBar.setBounds(8, 686, 418, 17);
		
		Label lblNewLabel_1 = new Label(shlemail, SWT.NONE);
		lblNewLabel_1.setBounds(8, 40, 236, 34);
		lblNewLabel_1.setText("例: http://tieba.baidu.com/p/123456789\r\n此处123456789即为帖子ID");
		
		Label lblNewLabel_2 = new Label(shlemail, SWT.NONE);
		lblNewLabel_2.setBounds(278, 42, 148, 32);
		lblNewLabel_2.setText("提取将从第一页开始\r\n停止于设定的最大的页码");
		text_2.addVerifyListener(new TextVerifyListener(1));  

	}
	
	public static void setProgress(int p){
		label_4.setText("正在处理第"+p+"页...");
		progressBar.setSelection(p);
	}
	

}
