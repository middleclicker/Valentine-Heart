import java.lang.Math;
import java.util.ArrayList;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Heart {
  public static int scale = 1;
  public static double increment = 0.1;
  public static String name;
  public static void main(String[] args) {
    name = args[0];
    DecimalFormat df = new DecimalFormat("#.#");
    df.setRoundingMode(RoundingMode.CEILING);
    ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
    for (double x = -scale*2; x < scale*2; x += increment) {
      x = Double.parseDouble(df.format(x));

      double top = Double.parseDouble(df.format(Math.sqrt(Math.pow(scale, 2)-Math.pow((Math.abs(x)-scale),2))));
      double bottom = Double.parseDouble(df.format(scale*Math.acos(1-(1/scale)*Math.abs(x))-Math.PI));

      coords.add(new Coordinate(x, top));
      coords.add(new Coordinate(x, bottom));
    }

    df.setRoundingMode(RoundingMode.FLOOR);
    for (double y = scale; y >= -scale*(Math.PI); y -= increment) {
      y = Double.parseDouble(df.format(y));
      for (int i = 0; i < coords.size(); i++) {
        if (coords.get(i).y == y) {
          System.out.print("#");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }

    // Credits: https://mkyong.com/java/ascii-art-java-example/
    int width = 100;
    int height = 30;

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    g.setFont(new Font("Lucida Bright", Font.BOLD, 14));

    Graphics2D graphics = (Graphics2D) g;
    graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    graphics.drawString("TO: "+name, 10, 20);

    for (int y = 0; y < height; y++) {
      StringBuilder sb = new StringBuilder();
      for (int x = 0; x < width; x++) {
        sb.append(image.getRGB(x, y) == -16777216 ? " " : "$");
      }
      if (sb.toString().trim().isEmpty()) {
        continue;
      }
      System.out.println(sb);
    }
  }
}

class Coordinate {
  double x;
  double y;
  public Coordinate(double x, double y) {
    this.x = x;
    this.y = y;
  }
}
