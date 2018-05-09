package runner;

import java.io.IOException;

import dao.ImageManager;

public class Run {

	public static void main(String[] args) {

		ImageManager m = new ImageManager();
		try {
			m.setImages("God of war");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
