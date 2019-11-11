package invadem.objects.projectiles;

import processing.core.PApplet;
import processing.core.PImage;

public class TankProjectile extends Projectile{

    private PImage img;

    public TankProjectile(PImage img, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.img = img;
    }

    public void yTick() {
        this.y -= 1;
    }

    public void draw(PApplet app) {
        app.image(img, x, y, width, height);
        yTick();
    }

}
