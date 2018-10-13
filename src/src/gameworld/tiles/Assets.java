package gameworld.tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {

	private static final int WIDTH = 32, HEIGHT = 32;

	public static BufferedImage grass, flooring, bathroom;

	public static void init() {
		try {
			grass = ImageIO.read(new File("/grass_texture.jpg"));
			bathroom = ImageIO.read(new File("/bathroom_texture.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
