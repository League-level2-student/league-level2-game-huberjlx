import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends GameObject {
	Color color;
	int worth;
	ArrayList <Tile> path;
	int speed;
	static int multiplier;
	double distance = Double.MAX_VALUE;
	boolean reachedGoal = false;
	int armor;
	int mr;
	
	public Enemy(double x, double y, int width, int height, int health, int damage, int worth, int speed, int multiplier, boolean spawnsEnemies, int armor, int mr) {
		super(x, y, width, height, health, damage, spawnsEnemies, armor, mr);
		this.damage = damage + (int)(damage * (0.021 * multiplier));
		this.health = health + (int)(health * (0.015 * multiplier));
		this.maxHealth = this.health;
		this.speed = speed;
		this.worth = worth;
		path = new ArrayList <Tile>();
		Tile start = ObjectManager.findLocation(this.x, this.y);
		Tile end = ObjectManager.findLocation(ObjectManager.goal.x, ObjectManager.goal.y);
		//System.out.println(ObjectManager.findLocation(this.x, this.y) + " x: " + x + " y: " + y);
		path = ObjectManager.findPath(start, end);
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		drawHP(g);
		g.setColor(Color.black);
		//g.drawString("" + (int)distance, (int)x, (int)y + 25);
	}
	
	
	public void update() {
		super.update();
		if (path.size() > 0) {
			double goalX = path.get(0).x;
			double goalY = path.get(0).y;
			
			if (x < goalX) {
				x = x + speed;
			}
			if (y < goalY) {
				y = y + speed;
			}
			if (x > goalX) {
				x = x - speed;
			}
			if (y > goalY) {
				y = y - speed;
			}
			
			if (goalX == x && goalY == y) {
				path.remove(0);
			}

		}
	}
	public void onDeath() {
		
	}
	
}
