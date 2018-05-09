package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import persistance.FileManager;

public class ImageManager {

	private List<String> urls;
	private FileManager fileManager;
	
	
	public ImageManager() {
		fileManager = new FileManager();
		urls = new ArrayList<>();
	}
	
	public void setImages(String keyWord) throws IOException {
		urls = fileManager.getImages(keyWord);
		for (String url : urls) {
			int i = 0;
			fileManager.saveImage(url, "src/data/image" + i + ".jpg");
			i++;
		}
	}
	
	
}
