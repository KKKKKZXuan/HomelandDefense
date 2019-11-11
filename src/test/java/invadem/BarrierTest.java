package invadem;

import invadem.objects.barriers.Barrier;
import invadem.objects.projectiles.InvProjectile;
import invadem.objects.projectiles.TankProjectile;
import org.junit.Test;
import processing.core.PImage;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BarrierTest {

    private PImage image = new PImage(1, 1);

    @Test
    public void barrierConstruction() {
        Barrier b = new Barrier(
                image, image, image, image,
                1, 1, 10, 10
        );
        b.reduceSustain();
        assertNotNull(b);
    }

    @Test
    public void testBarrierNotDestroyed() {
        Barrier b = new Barrier(
                image, image, image, image,
                1, 1, 10, 10
        );
        assertFalse(b.isDestroyed());
    }

    @Test
    public void testBarrierHitPointsMax() {
        Barrier b = new Barrier(
                image, image, image, image,
                1, 1, 10, 10
        );
        assertEquals(3, b.getSustain());
    }

    @Test
    public void testBarrierHitPointsMax1() {
        Barrier b = new Barrier(
                image, image, image, image,
                1, 1, 10, 10
        );
        b.reduceSustain();
        assertEquals(2, b.getSustain());
    }

    @Test
    public void testBarrierHitPointsMax2() {
        Barrier b = new Barrier(
                image, image, image, image,
                1, 1, 10, 10
        );
        b.reduceSustain();
        b.reduceSustain();
        assertEquals(1, b.getSustain());
    }


    @Test
    public void testBarrierIsDestroyed() {
        Barrier b = new Barrier(
                image, image, image, image,
                1, 1, 10, 10
        );
        b.reduceSustain();
        b.reduceSustain();
        b.reduceSustain();
        assertTrue(b.isDestroyed());
    }

    @Test
    public void testBarrierIntersectWithPlayerProjectile() {
        Barrier b = new Barrier(
                image, image, image, image,
                1, 1, 10, 10
        );
        TankProjectile tankProj = new TankProjectile(
                image,
                1, 1, 1, 3
        );
        assertTrue(new App().check_collection(tankProj, b));
    }

    @Test
    public void testInvaderIntersectWithInvaderProjectile() {
        Barrier b = new Barrier(
                image, image, image, image,
                1, 1, 10, 10
        );
        InvProjectile invProj = new InvProjectile(
                image,
                1, 1, 1, 3
        );
        assertTrue(new App().check_collection(invProj, b));
    }
}
