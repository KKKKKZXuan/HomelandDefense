package invadem.objects.invaders;

import processing.core.PApplet;
import processing.core.PImage;

public class Invader {

    private int x;
    private int y;
    private int width;
    private int height;
    private PImage img;
//    protected int[] velocity;

    public Invader(PImage img, int x, int y, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void tick() {
        this.x += 0;
        this.y += 0;
    }

    public void draw(PApplet app) {
        app.image(img, x, y, width, height);
        tick();
    }


}
