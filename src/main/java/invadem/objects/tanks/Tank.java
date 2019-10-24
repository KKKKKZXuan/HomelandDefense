package invadem.objects.tanks;

import processing.core.PApplet;
import processing.core.PImage;

public class Tank {

    private int x;
    private int y;
    private int width;
    private int height;
    private PImage img;

    public Tank(PImage img, int x, int y, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void rightTick() {
        this.x += 1;
    }

    public void leftTick() {
        this.x -= 1;
    }

    public int getX() {
        return this.x;
    }

    public void draw(PApplet app) {
        app.image(img, x, y, width, height);
    }
}
