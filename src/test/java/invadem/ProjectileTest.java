package invadem;

import invadem.objects.invaders.Invader;
import invadem.objects.projectiles.InvProjectile;
import invadem.objects.projectiles.Projectile;
import invadem.objects.projectiles.TankProjectile;
import invadem.objects.tanks.Tank;
import org.junit.Test;
import processing.core.PImage;

import static org.junit.Assert.*;

public class ProjectileTest {

    private PImage image = new PImage(1, 1);

    @Test
    public void testProjectileConstruction() {
        Projectile proj = new InvProjectile(
                image,
                1, 1, 1, 3
        );
        assertNotNull(proj);
    }

    @Test
    public void testInvaderProjectileIsMoving() {
        InvProjectile proj = new InvProjectile(
                image,
                1, 1, 1, 3
        );
        proj.yTick();
        assertEquals(2, proj.getY());
    }

    @Test
    public void testTankProjectileIsMoving() {
        TankProjectile proj = new TankProjectile(
                image,
                1, 1, 1, 3
        );
        proj.yTick();
        assertEquals(0, proj.getY());
    }

    @Test
    public void testProjectileIntersect() {
        TankProjectile tankProj = new TankProjectile(
                image,
                1, 1, 1, 3
        );
        InvProjectile invProj = new InvProjectile(
                image,
                1, 1, 1, 3
        );
        Invader inv = new Invader(
                image, image, image, image, image, image,
                "regular",
                1, 1, 16, 16
        );
        Tank tank = new Tank(
                image,
                1, 1, 22, 16
        );
        assertTrue(new App().check_collection(tankProj, inv));
        assertFalse(new App().check_collection(invProj, inv));
        assertTrue(new App().check_collection(invProj, tank));
    }

}
