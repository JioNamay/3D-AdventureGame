package gameworld.tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {

	private static final int width = 32, height = 32;

	public static BufferedImage grass, flooring;

	public static void init() {
		try {
			grass = ImageIO.read(new File("/Users/Jio/Downloads/textures/grass_texture.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
