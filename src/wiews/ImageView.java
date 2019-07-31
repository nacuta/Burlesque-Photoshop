package wiews;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageView extends Component {

	private BufferedImage image;

	public void setImage(BufferedImage image) {
		System.out.println("Set the image");
		this.image = image;
		Dimension d = new Dimension(image.getWidth(), image.getHeight());
		this.setPreferredSize(d);
		this.setSize(d);
		this.repaint();
		this.revalidate();
	}

	@Override
	public void paint(Graphics g) {
		if(image != null)
			g.drawImage(image, 0, 0, null);
	}
}
