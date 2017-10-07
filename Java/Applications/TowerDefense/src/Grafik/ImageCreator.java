package Grafik;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageCreator {
    public static Font f = new Font(Font.SERIF, Font.PLAIN, 10);

    public static ImageIcon createTower(String name, int level)
            throws Exception {
        BufferedImage bimg = loadImage(TDGUI.ppt + name + ".png");
        Graphics2D g2 = bimg.createGraphics();
        g2.setColor(Color.black);
        g2.setFont(f);
        g2.fillRect(bimg.getWidth() - 10, 0, 10, 10);
        g2.setColor(Color.white);
        g2.drawString("" + level, bimg.getWidth() - 8, 9);
        g2.dispose();
        return new ImageIcon(bimg);
    }

    public static ImageIcon createCreep(String name, String conditions)
            throws Exception {
        BufferedImage bimg = loadImage(TDGUI.ppc + name + ".png");
        Graphics2D g2 = bimg.createGraphics();
        g2.setFont(f);
        for (int i = 0; i < conditions.length(); i++) {
            g2.setColor(Color.black);
            g2.fillRect(bimg.getWidth() - (10 * (i + 1)),
                    bimg.getHeight() - 10, 10, 10);
            g2.setColor(Color.white);
            g2.drawString("" + conditions.charAt(i), bimg.getWidth()
                    - (8 * (i + 1)), bimg.getHeight() - 1);
        }
        g2.dispose();
        System.out.println("test");
        return new ImageIcon(bimg);
    }

    public static ImageIcon createRadius(int r) {
        BufferedImage bimg = new BufferedImage(r * 2, r * 2, 6);
        Graphics2D g2 = bimg.createGraphics();
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRect(0, 0, r * 2, r * 2);
        g2.setColor(new Color(0, 0, 255, 75));
        g2.fillArc(0, 0, r * 2, r * 2, 0, 360);
        return new ImageIcon(bimg);
    }

    protected static void removeWhitePNG(String name, String newname)
            throws Exception {
        BufferedImage bimg = loadImage(name + ".png");
        Image img = makeColorTransparent(bimg, Color.white);
        bimg = imageToBufferedImage(img);
        saveBufferedImage(bimg, newname, "PNG");
    }

    public static Image bufferedImagetoImage(BufferedImage bufferedImage) {
        return Toolkit.getDefaultToolkit().createImage(
                bufferedImage.getSource());
    }

    protected static BufferedImage loadImage(String path) throws Exception {
        File in = new File(path);
        return ImageIO.read(in);
    }

    protected static void saveBufferedImage(BufferedImage img, String output,
            String format) throws Exception {
        File out = new File(output + "." + format);
        ImageIO.write(img, format, out);
    }

    protected static BufferedImage imageToBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
                image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return bufferedImage;
    }

    protected static Image makeColorTransparent(BufferedImage im,
            final Color color) {
        ImageFilter filter = new RGBImageFilter() {
            // the color we are looking for... Alpha bits are set to opaque
            public int markerRGB = color.getRGB() | 0xFF000000;

            public final int filterRGB(int x, int y, int rgb) {
                if ((rgb | 0xFF000000) == markerRGB) {
                    // Mark the alpha bits as zero - transparent
                    return 0x00FFFFFF & rgb;
                } else {
                    // nothing to do
                    return rgb;
                }
            }
        };

        ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
        return Toolkit.getDefaultToolkit().createImage(ip);
    }

}
