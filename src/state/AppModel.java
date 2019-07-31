package state;

import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AppModel {
    private Stack<BufferedImage> images;
    private List<AppModelListener> modelListeners;

    public AppModel() {
        this.images = new Stack<BufferedImage>();

        this.modelListeners = new ArrayList<AppModelListener>();
    }

    public BufferedImage getImage() {
        return images.peek();
    }

    public void setImage(BufferedImage image) {
        this.images.push(image);
        fireUpdate();
    }

    private void fireUpdate() {
        for (AppModelListener up : this.modelListeners) {
            up.onUpdate();
        }
    }

    public void addModelListeners(AppModelListener modelListeners) {
        this.modelListeners.add(modelListeners);
    }

    public boolean canUndo() {
        return this.images.size() > 1;
    }

    public void undo() {
        if (!canUndo()) {
            return;
        }

        this.images.pop();
        fireUpdate();
    }

    public void grayscale() {
        BufferedImage image = this.getImage();
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                Color c = new Color(image.getRGB(i, j));
                int colors = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                Color newColor = new Color(colors, colors, colors);
                c.brighter();
                newImage.setRGB(i, j, newColor.getRGB());

            }
        }
        this.images.push(newImage);
        fireUpdate();
    }

    public void sepiaScale() {
        BufferedImage image = this.getImage();
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {

                Color c = new Color(image.getRGB(i, j));
                int red = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                int green = red;
                int blue = red;

                if (red < 63) {
                    red = ( int ) (red * 1.1);
                    blue = ( int ) (blue * 0.9);
                } else if (red < 192) {
                    red = ( int ) (red * 1.15);
                    blue = ( int ) (blue * 0.85);
                } else {
                    red = Math.min(( int ) (red * 1.08), 255);
                    blue = ( int ) (blue * 0.93);
                }


                Color newColor = new Color(red, green, blue);

                newImage.setRGB(i, j, newColor.getRGB());

            }
        }

        this.images.push(newImage);
        fireUpdate();

    }

    public void cutPic(Rectangle rect) {

        BufferedImage image = this.getImage();
        BufferedImage nextImage = new BufferedImage(( int ) rect.getWidth(), ( int ) rect.getHeight(), image.getType());

        int x = rect.x;

        for (int i = 0; i < rect.width; i++) {
            int y = rect.y;
            for (int j = 0; j < rect.height; j++) {
                nextImage.setRGB(i, j, image.getRGB(x, y));
                y++;
            }
            x++;

        }

        this.images.push(nextImage);
        fireUpdate();

    }


    public void convolution(float[][] kernel) {
        BufferedImage image = this.getImage();
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());


        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                newImage.setRGB(i,j, image.getRGB(i,j));
            }
        }
        for (int i = 0; i < image.getWidth() - 2; i++) {
            for (int j = 0; j < image.getHeight() - 2; j++) {
                int[][] pixels = new int[3][3];

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        pixels[k][l] = image.getRGB(i + k, j + l);
                    }
                }

                float sumR = 0;
                float sumG = 0;
                float sumB = 0;

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        Color c = new Color(pixels[k][l]);
                        sumR += kernel[k][l] * c.getRed();
                        sumG += kernel[k][l] * c.getGreen();
                        sumB += kernel[k][l] * c.getBlue();
                    }
                }

                sumR = clamp(sumR, 0, 255);
                sumG = clamp(sumG, 0, 255);
                sumB = clamp(sumB, 0, 255);


                int nR = (int) sumR;
                int nG = (int) sumG;
                int nB = (int) sumB;

                newImage.setRGB(i+1 , j+1 , new Color(nR, nG, nB).getRGB());
            }
        }
        this.images.push(newImage);
        fireUpdate();


    }
    public float clamp( float val, float min, float max) {
        if(val<min){
            val=0;
        }else if(val>max){
            val=255;
        }
        return val;

    }


}


