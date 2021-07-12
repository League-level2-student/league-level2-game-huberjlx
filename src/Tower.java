
import java.awt.Color;
import java.awt.Graphics;

public class Tower extends GameObject {
	int range;
	long attackCD;
	long attackTime;
	Enemy target;
	Color color;
	
	Tower(double x, double y, int width, int height, int health, int damage, int attackCD, int range, Color color) {
		super(x, y, width, height, health, damage);
		this.range = range;
		this.attackCD = attackCD;
		this.color = color;
	}
	
	public void update() {
		findTarget();
		if (target != null) {
			//System.out.println((int)ObjectManager.calculateDistance(this, target) + ", range: " + range + ", " + target.isAlive);
			if (target.isAlive == false) target = null;
		}
		if (System.currentTimeMillis() >= attackTime + attackCD && target != null && target.isAlive && ObjectManager.calculateDistance(this, target) < range) {
			attackTime = System.currentTimeMillis();
			fire();
		}
		
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		//if (target != null) g.drawLine((int)x, (int)y, (int)target.x, (int)target.y);
	}
	
	public void fire() {
		Projectile projectile = new Projectile(x + width / 2 - 5, y + height / 2 - 5, 10, 10, damage, target);
		ObjectManager.addProjectile(projectile);
	}
	
	public double findTarget() {
		Enemy target = this.target;
		double closest;
		if (target != null) closest = target.distance;
		else closest = Double.MAX_VALUE;
		double distance = closest;
		for (Enemy enemy : ObjectManager.enemies) {
			//distance = Math.sqrt(Math.pow(enemy.x - x, 2) + Math.pow(enemy.y - y, 2));
			distance = (double) (Math.abs(x - enemy.x) + Math.abs(y - enemy.y));
			enemy.distance = distance;
			if (distance < closest) {
				closest = distance;
				target = enemy;
			}
			
		}
		this.target = target;
		return distance;
	}
}
