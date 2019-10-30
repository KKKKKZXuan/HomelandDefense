package invadem;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import invadem.objects.barriers.Barrier;
import invadem.objects.hints.Hint;
import invadem.objects.invaders.Invader;
import invadem.objects.projectiles.InvProjectile;
import invadem.objects.projectiles.TankProjectile;
import invadem.objects.tanks.Tank;
import processing.core.PApplet;

public class App extends PApplet {

    private List<Invader> invaders;
    private List<Barrier> barriers;
    private List<Tank> tanks;
    private List<TankProjectile> tankProjectiles;
    private List<InvProjectile> invProjectiles;
    private List<Hint> hints;

    private Random rand;
    private int time = 0;

    //    private boolean isAlive = true;
    private boolean isDead = false;
    private boolean isNextLevel = false;
    private boolean takeIn = true;
    private boolean canTankShot = true;


    public App() {
        //Set up your objects

        invaders = new ArrayList<Invader>();
        barriers = new ArrayList<Barrier>();
        tanks = new ArrayList<Tank>();
        tankProjectiles = new ArrayList<TankProjectile>();
        invProjectiles = new ArrayList<InvProjectile>();
        hints = new ArrayList<Hint>();
        rand = new Random();

    }

    public void setup() {
        frameRate(60);

        System.out.println("Game is setting up");

        hints.clear();

        int inv_x = 180;
        int inv_y = 40;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                invaders.add(new Invader(
                        loadImage("invader1.png"),
                        loadImage("invader2.png"),
                        loadImage("empty.png"),
                        inv_x, inv_y, 16, 16
                ));
                inv_x += 30;
            }
            inv_y += 30;
            inv_x = 180;
        }

        int bar_x = 180;
        int bar_y = 400;
        for (int k = 0; k < 3; k++) {
            barriers.add(new Barrier(
                    loadImage("barrier_left1.png"),
                    loadImage("barrier_left2.png"),
                    loadImage("barrier_left3.png"),
                    loadImage("empty.png"),
                    bar_x, bar_y, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_right1.png"),
                    loadImage("barrier_right2.png"),
                    loadImage("barrier_right3.png"),
                    loadImage("empty.png"),
                    bar_x + 20, bar_y, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_top1.png"),
                    loadImage("barrier_top2.png"),
                    loadImage("barrier_top3.png"),
                    loadImage("empty.png"),
                    bar_x + 10, bar_y, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_solid1.png"),
                    loadImage("barrier_solid2.png"),
                    loadImage("barrier_solid3.png"),
                    loadImage("empty.png"),
                    bar_x, bar_y + 10, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_solid1.png"),
                    loadImage("barrier_solid2.png"),
                    loadImage("barrier_solid3.png"),
                    loadImage("empty.png"),
                    bar_x + 20, bar_y + 10, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_solid1.png"),
                    loadImage("barrier_solid2.png"),
                    loadImage("barrier_solid3.png"),
                    loadImage("empty.png"),
                    bar_x, bar_y + 20, 10, 10
            ));
            barriers.add(new Barrier(
                    loadImage("barrier_solid1.png"),
                    loadImage("barrier_solid2.png"),
                    loadImage("barrier_solid3.png"),
                    loadImage("empty.png"),
                    bar_x + 20, bar_y + 20, 10, 10
            ));

            bar_x += 120;

        }

        tanks.add(new Tank(
                loadImage("tank1.png"),
                300, 450, 22, 16
        ));

    }

    public void settings() {
        size(640, 480);
    }

    public void draw() {
        //Main Game Loop

        background(0);

        if (!isDead && !isNextLevel) {

            if (tanks.size() == 0) {
                setup();
            }

            boolean haveInvShot = true;

            if (invaders.size() == 0) {
                nextLevel();
            }

            for (Invader inv : invaders) {
                inv.draw(this);
                if (time % 300 == 0 && time != 0) {
                    if (haveInvShot) {
                        invShot();
                        haveInvShot = false;
                    }

                }

                if (inv.getY() > 380) {
                    gameOver();
                }

            }
            for (Barrier bar : barriers) {
                bar.draw(this);
            }
            for (Tank tank : tanks) {
                tank.draw(this);
            }
            for (TankProjectile ile : tankProjectiles) {
                ile.draw(this);
            }

            for (InvProjectile ile : invProjectiles) {
                ile.draw(this);
            }

            boolean isInvDisappear = false;

            pointA:

            for (Invader inv : invaders) {
                if (!isInvDisappear) {
                    for (TankProjectile ile : tankProjectiles) {
                        if (check_collection(ile, inv)) {
                            invaders.remove(inv);
                            tankProjectiles.remove(ile);
                            isInvDisappear = true;
                            break pointA;
                        }
                    }
                }
            }

            boolean isTankDisappear = false;

            pointB:

            for (Tank tank : tanks) {
                if (!isTankDisappear) {
                    for (InvProjectile ile : invProjectiles) {
                        if (check_collection(ile, tank)) {
                            tank.reduceSustain();
                            invProjectiles.remove(ile);

                            if (tank.getSustain() == 0) {
                                gameOver();
                            }

                            isTankDisappear = true;
                            break pointB;
                        }
                    }
                }
            }

            boolean isBarDisappearByTank = false;

            pointC:

            for (Barrier bar : barriers) {
                if (!isBarDisappearByTank) {
                    for (TankProjectile ile : tankProjectiles) {
                        if (check_collection(ile, bar)) {
                            bar.reduceSustain();
                            tankProjectiles.remove(ile);

                            if (bar.getSustain() == 0) {
                                barriers.remove(bar);
                            }

                            isBarDisappearByTank = true;
                            break pointC;
                        }
                    }
                }
            }

            boolean isBarDisappearByInv = false;

            pointC:

            for (Barrier bar : barriers) {
                if (!isBarDisappearByInv) {
                    for (InvProjectile ile : invProjectiles) {
                        if (check_collection(ile, bar)) {
                            bar.reduceSustain();
                            invProjectiles.remove(ile);

                            if (bar.getSustain() == 0) {
                                barriers.remove(bar);
                            }

                            isBarDisappearByInv = true;
                            break pointC;
                        }
                    }
                }
            }

        }
        else {
            invaders.clear();
            barriers.clear();
            tanks.clear();
            tankProjectiles.clear();
            invProjectiles.clear();
            for (Hint hint : hints) {
                hint.draw(this);
            }
            if (takeIn) {
                time = -50;
                takeIn = false;
            }

        }

        time += 1;

        if (isDead && time == -1) {
            System.exit(0);
        }

        if (isNextLevel && time == -1) {
            isNextLevel = false;
            takeIn = true;
            setup();
        }

        if (keyPressed) {

//            System.out.println(keyCode);
//            System.out.println(key);


            if (keyCode == 39) {
//                System.out.println("right");
                tanks.get(0).rightTick();
            } else if (keyCode == 37) {
//                System.out.println("left");
                tanks.get(0).leftTick();
            } else if (key == ' ') {
//                System.out.println("shot");
                if (canTankShot) {
                    tankShot();
                    canTankShot = false;
                }
            } else if (key == '`') {
                if (!isDead) {
                    isDead = true;
//                    System.out.println("gameover");
                    gameOver();
                } else {
                    isDead = false;
//                    System.out.println("gamestart");
                }
            } else if (key == 'd') {
                invaders.clear();
            }
        }


    }

    private boolean check_collection(TankProjectile a, Invader b) {
        if ((a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY())) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean check_collection(InvProjectile a, Tank b) {
        if ((a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY())) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean check_collection(TankProjectile a, Barrier b) {
        if ((a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY())) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean check_collection(InvProjectile a, Barrier b) {
        if ((a.getX() < (b.getX() + b.getWidth())) &&
                ((a.getX() + a.getWidth()) > b.getX()) &&
                (a.getY() < (b.getY() + b.getHeight())) &&
                ((a.getHeight() + a.getY()) > b.getY())) {
            return true;
        }
        else {
            return false;
        }
    }


    private void tankShot() {

        if (!isDead) {
            tankProjectiles.add(new TankProjectile(
                    loadImage("projectile.png"),
                    tanks.get(0).getX() + 10, 445, 1, 3
            ));
        }
    }

    private void invShot() {
        int randomInv = rand.nextInt(invaders.size());

        invProjectiles.add(new InvProjectile(
                loadImage("projectile.png"),
                invaders.get(randomInv).getX() + 7, invaders.get(randomInv).getY() + 7, 1, 3
        ));
    }

    private void gameOver() {

        isDead = true;

        hints.add(new Hint(
                loadImage("gameover.png"),
                269, 240, 112, 16
        ));

    }

    private void nextLevel() {

        isNextLevel = true;

        hints.add(new Hint(
                loadImage("nextlevel.png"),
                269, 240, 112, 16
        ));
    }

    public void keyReleased() {
        if (!canTankShot) {
            canTankShot = true;
        }
    }


    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
