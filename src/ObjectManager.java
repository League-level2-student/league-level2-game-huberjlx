import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.Timer;

public class ObjectManager implements ActionListener {
	Random rand = new Random();
	Timer enemySpawn;
	
	int money = 450;
	int towerCost = 250;
	int spawnCounter = 0;
	int spawnFrequency = 500;
	int enemyCounter = 0;
	int waveNum = 0;
	boolean inWave = false;
	long waveStart;
	long waveLength;
	
	ArrayList <Tower> towers = new ArrayList <Tower>();
	static ArrayList <Enemy> enemies = new ArrayList <Enemy>();
	static ArrayList <Projectile> projectiles = new ArrayList <Projectile>();
	static ArrayList <EnemySpawn> enemySpawns = new ArrayList <EnemySpawn>();
	static Tile[][] grid = new Tile[18][16];
	public static Goal goal = new Goal(400, 400, 45, 45, 200, 0);
	
	ObjectManager() {
		EnemySpawn spawn = new EnemySpawn(50, 50, 45, 45);
		enemySpawns.add(spawn);
		EnemySpawn spawn2 = new EnemySpawn(700, 700, 45, 45);
		enemySpawns.add(spawn2);
		enemySpawn = new Timer(50, this);
		for (int i = 0; i < grid.length; i++) {
			for (int index = 0; index < grid[i].length; index ++) {
				grid[i][index] = new Tile(i * 50, index * 50);
			}
		}
	}
	
	public void addTower(int x, int y) {
		x = (x - 7) / 50;
		y = (y - 31) / 50;
		x = x * 50;
		y = y * 50;
		
		if (isClearLocation(x, y, 50, 50) == true && money >= towerCost && inWave == false && enemyCounter == 0) {
			Tower tower = new BasicTower(x, y, 45, 45);
			towers.add(tower);
			money -= towerCost;
			Tile tile = findLocation(x, y);
			tile.setGameObject(tower);
			tile.setIsOccupied(true);	
			
		}
	}
	
	public void addEnemy() {
		if (spawnCounter > spawnFrequency) {
			int location = rand.nextInt(enemySpawns.size());
			EnemySpawn spawn = enemySpawns.get(location);
			if (isClearLocation((int)spawn.x, (int)spawn.y, 50, 50) == true) {
				Enemy enemy;
				if (waveNum <= 5) {
					int enemyType = rand.nextInt(waveNum);
					if (enemyType == 0) enemy = new BasicEnemy(spawn.x, spawn.y, 45, 45);	
					else if (enemyType == 2 || enemyType == 3) enemy = new LessBasicEnemy(spawn.x, spawn.y, 45, 45, waveNum);
					else enemy = new EvenLessBasicEnemy(spawn.x, spawn.y, 45, 45, waveNum);
				}
				else {
					int enemyType = rand.nextInt(8);
					if (enemyType == 0 || enemyType == 1) enemy = new BasicEnemy(spawn.x, spawn.y, 45, 45);
					else if (enemyType == 2 || enemyType == 3 || enemyType == 4 || enemyType == 5) enemy = new LessBasicEnemy(spawn.x, spawn.y, 45, 45, waveNum);	
					else enemy = new EvenLessBasicEnemy(spawn.x, spawn.y, 45, 45, waveNum);
					}
				enemies.add(enemy);
				enemyCounter += 1;
			spawnCounter = 0;
			}
		}
		spawnCounter += 20 + (int)(10 * (0.1 * waveNum - 1)); // Increase this number to increase wave spawn frequency. Decrease this value to decrease spawn frequency.
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
		for (EnemySpawn spawn : enemySpawns) {
			spawn.draw(g);  
		}
		g.drawString("Money: " + money, 30, 30);
		if (inWave) g.drawString("Wave: " + waveNum, 40, 40);
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
		if (System.currentTimeMillis() > waveStart + waveLength) endWave();
		checkAllCollisions();
		removeObjects();
	}
	
	public boolean isClearLocation(int x, int y, int width, int height) {
		Rectangle rect = new Rectangle(x, y, width, height);
		for (Tower tower : towers) {
			if (rect.intersects(tower.collisionBox) || rect.intersects(goal.collisionBox)) {
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
			for (Tower tower : towers) {
				if(enemy.collisionBox.intersects(tower.collisionBox)) {
					tower.takeDamage(enemy.damage);
				}
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
				money += enemies.get(i).worth;
				enemies.remove(i);
				enemyCounter -= 1;
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
	
	public static Tile findLocation(double x, double y) {
		for (int i = 0; i < grid.length; i++) {
			for (int index = 0; index < grid[i].length; index ++) {
				if (grid[i][index].x == x && grid[i][index].y == y) {
					return grid[i][index];
				}
			}
		}
		return null;
	}
	
	public static ArrayList<Tile> findPath(Tile start, Tile end) {
		
		HashMap <Tile, Tile> cameFrom = new HashMap <Tile, Tile>();
		HashMap <Tile, Integer> costs = new HashMap <Tile, Integer>();
		PriorityQueue <Tile> frontier = new PriorityQueue <Tile>();
		
		frontier.add(start);
		cameFrom.put(start, null);
		costs.put(start, 0);
		while (frontier.size() > 0) {
			Tile current = frontier.remove();
			if (current == end) {
				break;
			}
			for (Tile next : getNeighborsOf(current)) {
				int newCost = costs.get(current) + cost(next);
				if (!costs.containsKey(next) || newCost < costs.get(next)) {
					frontier.add(next);
					costs.put(next, newCost);
					int priority = newCost + heuristic(end, next);
					next.priority = priority;
					cameFrom.put(next, current);
				}
			}
		}
		return fillPath(start, end, cameFrom);
		
	}
	
	public static ArrayList<Tile> fillPath(Tile start, Tile end, HashMap<Tile, Tile> cameFrom) {
		Tile current = end;
		ArrayList<Tile> path = new ArrayList<Tile>();
		
		while (current != start) {
			path.add(current);
			current = cameFrom.get(current);
		}
		
		
		ArrayList<Tile> reversePath = new ArrayList<Tile>();
		for (int i = path.size() - 1; i >= 0; i--) {
			reversePath.add(path.get(i));
		}
		
		
		return reversePath;
		
	}
	
	public static int cost(Tile a) {
		return a.cost;
	}
	
	public static int heuristic(Tile a, Tile b) {
		return (int) (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
	}
	
	public static ArrayList<Tile> getNeighborsOf(Tile tile) {
		ArrayList<Tile> neighbors = tile.getNeighbors();
		if (neighbors == null) {
			neighbors = new ArrayList<Tile>();
			Tile north = findLocation(tile.x, tile.y + 50);
			Tile south = findLocation(tile.x, tile.y - 50);
			Tile east = findLocation(tile.x + 50, tile.y);
			Tile west = findLocation(tile.x - 50, tile.y);
			
			if (north != null) neighbors.add(north); 
			if (south != null) neighbors.add(south);
			if (east != null) neighbors.add(east);
			if (west != null) neighbors.add(west);
			tile.setNeighbors(neighbors);
		}
		return neighbors;
	}
	
	public void startWave() {
		if (inWave == false) {
			waveNum += 1;
			enemySpawn.start();
			waveStart = System.currentTimeMillis();
			waveLength = 30000;
			inWave = true;
		}
	}
	
	public void endWave() {
		enemySpawn.stop();
		inWave = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addEnemy();
		
	}
	
	public static double calculateDistance(GameObject a, GameObject b) {
		return (double) (Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
		
	}
}
