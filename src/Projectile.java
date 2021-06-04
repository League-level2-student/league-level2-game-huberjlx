import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject{
	GameObject target;
	public Projectile(double x, double y, int width, int height, int damage) {
		super(x, y, width, height, 1, damage);
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval((int)x, (int)y, width, height);
	}
	
	public void update() {
		super.update();
		calculateMovement();
	}
	public void calculateMovement() {
		double slopeX = x - target.x;
		double slopeY = y - target.y;
		double vX = -slopeX / 1;
		double vY = -slopeY / 1;
		
		x += vX;
		y += vY;
	}
	
}
