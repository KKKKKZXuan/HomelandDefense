package invadem;

import invadem.objects.invaders.Invader;
import invadem.objects.projectiles.InvProjectile;
import invadem.objects.projectiles.TankProjectile;
import org.junit.Test;
import processing.core.PImage;

import static org.junit.Assert.*;

public class InvaderTest {

    private PImage image = new PImage(1, 1);

    @Test
    public void testInvaderConstruction() {
        Invader inv = new Invader(
                image, image, image, image, image, image,
                "regular",
                1, 1, 16, 16
        );
        assertNotNull(inv);

    }

    @Test
    public void testPowerInvaderIntersectProjectile() {
        Invader inv = new Invader(
                image, image, image, image, image, image,
                "power",
                1, 1, 16, 16
        );
        inv.getShot();
        assertTrue(inv.isDead());
    }

    @Test
    public void testInvaderIsNotDead() {
        Invader inv = new Invader(
                image, image, image, image, image, image,
                "regular",
                1, 1, 16, 16
        );
        assertFalse(inv.isDead());
    }

    @Test
    public void testInvaderIsDead() {
        Invader inv = new Invader(
                image, image, image, image, image, image,
                "regular",
                1, 1, 16, 16
        );
        inv.getShot();
        assertTrue(inv.isDead());
    }

    @Test
    public void testInvaderIntersectWithPlayerProjectile() {
        Invader inv = new Invader(
                image, image, image, image, image, image,
                "regular",
                1, 1, 16, 16
        );
        TankProjectile tankProj = new TankProjectile(
                image,
                1, 1, 1, 3
        );
        assertTrue(new App().check_collection(tankProj, inv));
    }

    @Test
    public void testInvaderIntersectWithInvaderProjectile() {
        Invader inv = new Invader(
                image, image, image, image, image, image,
                "regular",
                1, 1, 16, 16
        );
        InvProjectile invProj = new InvProjectile(
                image,
                1, 1, 1, 3
        );
        assertFalse(new App().check_collection(invProj, inv));
    }

}
