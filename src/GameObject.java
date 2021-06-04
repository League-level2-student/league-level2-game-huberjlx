import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	double x;
	double y;
	int width;
	int height;
	int health;
	int damage;
	int maxHealth;
	boolean isAlive = true;
	
	Rectangle collisionBox;
	
	public GameObject(double x, double y, int width, int height, int health, int damage) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		this.damage = damage;
		this.maxHealth = health;
		collisionBox = new Rectangle((int)x, (int)y, width, height);
	}

	public void update() {
		collisionBox.setBounds((int)x, (int)y, width, height);
		
	}
	
	public void takeDamage(int damage) {
		this.health -= damage;
		if (this.health <= 0) {
			isAlive = false;
		}
	}
	
	public void drawHP(Graphics g) {
		g.setColor(Color.green);
		double healthPercent = (double)health / (double)maxHealth;
		g.fillRect((int)x, (int)y - 30, (int)(width * healthPercent), 20);
	}
}
