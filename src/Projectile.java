import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject{
	Enemy target;
	double vX;
	double vY;
	public Projectile(double x, double y, int width, int height, int damage, Enemy target) {
		super(x, y, width, height, 1, damage);
		this.target = target;
		double slopeX = x - target.path.get(0).x;
		double slopeY = y - target.path.get(0).y;
		vX = -slopeX / 50;
		vY = -slopeY / 50;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval((int)x, (int)y, width, height);
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
	}
	
}
