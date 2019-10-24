package invadem.objects.hints;

import processing.core.PApplet;
import processing.core.PImage;

public class Hint {

    private int x;
    private int y;
    private int width;
    private int height;
    private PImage img;

    public Hint(PImage img, int x, int y, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void draw(PApplet app) {
        app.image(img, x, y, width, height);
    }
}
