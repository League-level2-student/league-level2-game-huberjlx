import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject{
	Enemy target;
	double vX;
	double vY;
	boolean isMagicDamage;
	public Projectile(double x, double y, int width, int height, int damage, Enemy target, boolean isMagicDamage) {
		super(x, y, width, height, 1, damage, false, 0, 0);
		this.target = target;
		double startingX = this.x + width / 2;
		double startingY = this.y + height / 2;
		double targetX = target.path.get(0).x + Tile.WIDTH / 2;
		double targetY = target.path.get(0).y + Tile.HEIGHT / 2;
		double slopeX = targetX - startingX;
		double slopeY = targetY - startingY;
		vX = slopeX / 50.0;
		vY = slopeY / 50.0;
		this.isMagicDamage = isMagicDamage;
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval((int)x, (int)y, width, height);
		//g.setColor(Color.ORANGE);
		//g.drawLine((int)targetX, (int)targetY, (int)startingX, (int)startingY);
	}

	public void update() {
		super.update();
		calculateMovement();
		if (x > TowerDefense.WIDTH || x < 0 || y > TowerDefense.HEIGHT || y < 0) {
			isAlive = false;
		}
	}
	public void calculateMovement() {
		x += vX;
		y += vY;
//		double dist = Math.sqrt(vX * vX + vY * vY);
//		int speed = 10;
//		x += (speed/dist) * vX;
//		y += (speed/dist) * vX;
	}

}
