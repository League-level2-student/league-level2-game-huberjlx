import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.Timer;

public class ObjectManager {
	Timer enemySpawn;
	
	ArrayList <Tower> towers = new ArrayList <Tower>();
	static ArrayList <Enemy> enemies = new ArrayList <Enemy>();
	static ArrayList <Projectile> projectiles = new ArrayList <Projectile>();
	public static Goal goal = new Goal(400, 400, 45, 45, 200, 0);
	
	public void addTower(int x, int y) {
		x = (x - 7) / 50;
		y= (y - 31) / 50;
		x = x * 50;
		y = y * 50;
		
		if (isClearLocation(x, y, 50, 50) == true) {
			Tower tower = new Tower(x, y, 45, 45, 50, 10);
			towers.add(tower);
		}
	}
	
	public void addEnemy(int x, int y) {
		x = (x - 7) / 50;
		y= (y - 31) / 50;
		x = x * 50;
		y = y * 50;
		
		if (isClearLocation(x, y, 50, 50) == true) {
			Enemy enemy = new Enemy(x, y, 45, 45, 50, 1);
			enemies.add(enemy);
		}
	}
	
	public static void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}
	
	public void draw(Graphics g) {
		goal.draw(g);
		for (Tower tower : towers) {
			tower.draw(g);
		}
		for (Enemy enemy : enemies) {
			enemy.draw(g);
		}
		for (Projectile projectile : projectiles) {
			projectile.draw(g);
		}
	}
	
	public void update() {
		for (Enemy enemy : enemies) {
			enemy.update();
		}
		for (Tower tower : towers) {
			tower.update();
		}
		for (Projectile projectile : projectiles) {
			projectile.update();
		}
		checkAllCollisions();
		removeObjects();
	}
	
	public boolean isClearLocation(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x, y, width, height);
		for (Tower tower : towers) {
			if (rect.intersects(tower.collisionBox)) {
				return false;
			}
		}
		return true;
	}
	
	public void checkAllCollisions() {
		for (Enemy enemy : enemies) {
			if (enemy.collisionBox.intersects(goal.collisionBox)) {
				goal.takeDamage(enemy.damage);
				enemy.isAlive = false;
			}
			for (Projectile projectile : projectiles) {
				if (enemy.collisionBox.intersects(projectile.collisionBox)) {
					enemy.takeDamage(projectile.damage);
					projectile.isAlive = false;
				}
			}
		}
	}
	
	public void removeObjects() {
		if (goal.isAlive == false) {
			GamePanel.currentState = GamePanel.END;
		}
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).isAlive == false) {
				enemies.remove(i);
			}
		}
		for (int i = 0; i < towers.size(); i++) {
			if (towers.get(i).isAlive == false) {
				towers.remove(i);
			}
		}
		for (int i = 0; i < projectiles.size(); i ++) {
			if (projectiles.get(i).isAlive == false) {
				projectiles.remove(i);
			}
		}
	}
	
		
}
