
import java.awt.Color;
import java.awt.Graphics;

public class Tower extends GameObject {
	int range;
	long attackCD;
	long attackTime;
	GameObject target;
	
	Tower(double x, double y, int width, int height, int health, int damage) {
		super(x, y, width, height, health, damage);
		range = 100;
		attackCD = 100;
	}
	
	public void update() {
		findTarget();
		if (System.currentTimeMillis() >= attackTime + attackCD && target != null) {
			attackTime = System.currentTimeMillis();
			fire();
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public void fire() {
		Projectile projectile = new Projectile(x + width / 2 - 5, y + height / 2 - 5, 10, 10, damage);
		projectile.target = target;
		ObjectManager.addProjectile(projectile);
	}
	
	public void findTarget() {
		double closest = Double.MAX_VALUE;
		for (Enemy enemy : ObjectManager.enemies) {
			double distance = Math.sqrt(Math.pow(enemy.x - x, 2) + Math.pow(enemy.y - y, 2));
			if (distance < closest) {
				closest = distance;
				target = enemy;
			}
			
		}
	}
}
