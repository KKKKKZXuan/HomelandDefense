package invadem.objects.invaders;

import processing.core.PApplet;
import processing.core.PImage;

public class Invader {

    private int x;
    private int y;
    private int width;
    private int height;
    private PImage img1;
    private PImage img2;
    private PImage img3;
    private PImage now_img;

    public Invader(PImage img1, PImage img2, PImage img3, int x, int y, int width, int height) {
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        now_img = img1;
    }

    private void tick() {
        this.x += 0;
        this.y += 0;
    }

    public void draw(PApplet app) {
        app.image(now_img, x, y, width, height);
        tick();
    }


}
