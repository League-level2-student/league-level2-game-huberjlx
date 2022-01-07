import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class GameObject {
	double x;
	double y;
	int width;
	int height;
	int health;
	int damage;
	int maxHealth;
	int armor;
	int mr;
	boolean isAlive = true;
	boolean spawnsEnemies;
	
	Rectangle collisionBox;
	
	public GameObject(double x, double y, int width, int height, int health, int damage, boolean spawnsEnemies, int armor, int mr) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.damage = damage;
		this.maxHealth = health;
		this.health = health;
		this.spawnsEnemies = spawnsEnemies;
		this.armor = armor;
		this.mr = mr;
		collisionBox = new Rectangle((int)x, (int)y, width, height);
	}

	public void update() {
		collisionBox.setBounds((int)x, (int)y, width, height);
		
	}
	
	public void takeDamage(int damage, boolean isMagicDamage) {
		if (isMagicDamage) {
			double reducedDamage = this.damage / (0.5 * this.mr);
		}
		else {
			double reducedDamage = this.damage / (0.5 * this.armor);
		}
		this.health -= damage;
		if (this.health <= 1) {
			isAlive = false;
		}
	}
	
	public void drawHP(Graphics g) {
		g.setColor(Color.green);
		double healthPercent = (double)health / (double)maxHealth;
		g.fillRect((int)x, (int)y - 30, (int)(width * healthPercent), 20);
		g.setColor(Color.black);
		g.drawString("   " + health, (int)x, (int)y - 12);
	}
	
}
