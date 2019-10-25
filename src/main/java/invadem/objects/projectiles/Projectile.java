package invadem.objects.projectiles;

import processing.core.PApplet;

public abstract class Projectile {

    protected int x;
    protected int y;
    protected int width;
    protected int height;


    public Projectile(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public abstract void draw(PApplet app);
}
