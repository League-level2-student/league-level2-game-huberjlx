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
	int splashDamage;
	double splashRange;
	boolean isAlive = true;
	boolean spawnsEnemies;

	Rectangle collisionBox;
	
	public GameObject(double x, double y, int width, int height, int health, int damage, boolean spawnsEnemies, int armor, int mr, double splashDamage, double splashRange) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.damage = damage;
		this.maxHealth = health;
		this.health = health;
		this.spawnsEnemies = spawnsEnemies;
		if (armor >= 299) {
			armor = 299;
		}
		this.armor = armor;
		if (mr >= 299) {
			mr = 299;
		}
		this.mr = mr;
		this.splashDamage = (int)splashDamage;
		this.splashRange = splashRange;
		collisionBox = new Rectangle((int)x, (int)y, width, height);
	}

	public void update() {
		collisionBox.setBounds((int)x, (int)y, width, height);

	}
	
	  public void takeDamage(int damage, int armorPen, int magicPen, boolean isMagicDamage, boolean isAOE) {
		    if (isAOE) {
		      double reducedDamage;
		      if (isMagicDamage) {
		        reducedDamage = (damage - (this.mr - magicPen) / 300 * damage);
		      } else {
		        reducedDamage = (damage - (this.armor - armorPen) / 300 * damage);
		      } 
		      this.health = (int)(this.health - reducedDamage);
		      if (this.health <= 1) {
		        this.isAlive = false;
		        ObjectManager.score++;
		      } 
		      for (Enemy enemy : ObjectManager.enemies) {
		        double distance = ObjectManager.calculateDistance(enemy, this);
		        if (distance < this.splashRange)
		          enemy.takeDamage(this.splashDamage, armorPen, magicPen, isMagicDamage, false); 
		      } 
		    } else {
		      double reducedDamage;
		      if (isMagicDamage) {
		        reducedDamage = (damage - (this.mr - magicPen) / 300 * damage);
		      } else {
		        reducedDamage = (damage - (this.armor - armorPen) / 300 * damage);
		      } 
		      this.health = (int)(this.health - reducedDamage);
		      if (this.health <= 1) {
		        this.isAlive = false;
		        ObjectManager.score++;
		      } 
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
