package invadem;

import invadem.objects.projectiles.InvProjectile;
import invadem.objects.tanks.Tank;
import org.junit.Test;
import processing.core.PImage;

import static org.junit.Assert.*;

public class TankTest {

    private PImage image = new PImage(1, 1);

    @Test
    public void testTankCrate() {
        Tank tank = new Tank(
                image,
                305, 450, 22, 16
        );
        assertFalse(tank.isDead());
    }

    @Test
    public void testTankProjectile() {
        Tank tank = new Tank(
                image,
                305, 450, 22, 16
        );
        InvProjectile invProj = new InvProjectile(
                image,
                1, 1, 1, 3
        );
        assertFalse(new App().check_collection(invProj, tank));
    }

    @Test
    public void testTankIsDead() {
        Tank tank = new Tank(
                image,
                305, 450, 22, 16
        );
        tank.reduceSustain();
        tank.reduceSustain();
        tank.reduceSustain();
        assertTrue(tank.isDead());
    }

}
