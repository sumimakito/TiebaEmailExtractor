import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.osgi.framework.internal.protocol.bundleresource.Handler;

public class DataUtils {

	public List<String> emailList = new ArrayList<String>();

	public DataUtils() {
		emailList = new ArrayList<String>();
	}

	public List<String> getPost(String pid, int maxPage) {
		try {
			emailList.clear();
			for (int i = 1; i <= maxPage; i++) {
				System.out.println("processing page "+i);
				Main.setProgress(i);
				URL url = new URL("http://tieba.baidu.com/p/" + pid + "?pn="
						+ i);

				URLConnection conn = url.openConnection();
				conn.setReadTimeout(6000);
				InputStream input = conn.getInputStream();

				int count = 0, bufferSize = 8192;
				byte bytes[] = new byte[bufferSize];
				StringBuffer sb = new StringBuffer(bufferSize);
				while ((count = input.read(bytes)) != -1)
					parse(new String(bytes, "GBK"));
		
			}
			removeDuplicate(emailList);
			return emailList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return emailList;
		}
	}

	public void parse(String line) {
		Pattern p = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher m = p.matcher(line);
		while (m.find()) {
			// System.out.println(m.group());
			emailList.add(m.group());
		}
	}

	public void removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
	}
}
