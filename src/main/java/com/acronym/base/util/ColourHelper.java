package com.acronym.base.util;

import com.acronym.base.Base;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * Created by Jared on 6/28/2016.
 */
public class ColourHelper {

    /**
     * Gets the main Colour of an InputStream
     * Has to be called after PreInit
     *
     * @param stream
     * @return The main Colour of the InputStream
     * @throws Exception
     */
    public static int getColour(InputStream stream) throws Exception {
        ImageInputStream is = ImageIO.createImageInputStream(stream);
        Iterator<ImageReader> iter = ImageIO.getImageReaders(is);

        if (!iter.hasNext()) {
            Base.logger.error("Cannot load the specified stream: " + stream);
        }

        ImageReader imageReader = iter.next();
        imageReader.setInput(is);
        Image img = imageReader.read(0).getScaledInstance(1, 1, Image.SCALE_AREA_AVERAGING);
        BufferedImage image = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = image.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        int height = image.getHeight();
        int width = image.getWidth();

        HashMap m = new HashMap();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                Integer counter = (Integer) m.get(rgb);
                if (counter == null)
                    counter = 0;
                m.put(rgb, ++counter);
            }
        }
        return Integer.decode("0x" + getMostCommonColour(m));
    }

    /**
     * Gets the most common Colour from a map
     *
     * @param map
     * @return The most common Colour as a hex string
     */
    public static String getMostCommonColour(Map map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, (o1, o2) -> ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue()));

        if (list.size() == 0) {
            return "FFFFFF";
        }

        Map.Entry me = (Map.Entry) list.get(list.size() - 1);
        int[] rgb = getRGBArr((Integer) me.getKey());

        return String.format("%s%s%s", Integer.toHexString(rgb[0]), Integer.toHexString(rgb[1]), Integer.toHexString(rgb[2]));
    }

    /**
     * Converts an integer into an int array of RBG Colour
     *
     * @param pixel
     * @return int array of RGB Colour
     */
    public static int[] getRGBArr(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        return new int[]{red, green, blue};
    }
}
