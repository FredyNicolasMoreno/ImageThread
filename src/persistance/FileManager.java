package persistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

	public List<String> getImages() throws IOException{
		List<String> list = new ArrayList<>();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
		URL url = new URL("http://wallpaperswide.com/");
		URLConnection hc = url.openConnection(proxy);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(hc.getInputStream()));

		String inputLine;
		Pattern p = Pattern.compile("<img[^>]*.jpg");
		while ((inputLine = in.readLine()) != null){
			Matcher m = p.matcher(inputLine);
			if (m.find()) {
				String src = m.group();
				int startIndex = src.indexOf("src=") + 5;
				String srcTag = src.substring(startIndex, src.length());
				list.add(srcTag);
			}
		}
		in.close();
		return list;
	}
	
}
