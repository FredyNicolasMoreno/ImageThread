package persistance;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

	public FileManager() {
	}
	
	public List<String> getImages(String keyWord) throws IOException{
		List<String> list = new ArrayList<>();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
		URL url = new URL("http://wallpaperswide.com/search.html?q=" + keyWord);
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
	
	public void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}
	
}
