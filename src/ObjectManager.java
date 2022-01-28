import java.awt.Color;
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
  
  static int score = 0;
  
  static int money = 100;
  
  int towerCost = 25;
  
  int spawnCounter = 0;
  
  int spawnFrequency = 500;
  
  int waveNum = 0;
  
  boolean inWave = false;
  
  long waveStart;
  
  long waveLength;
  
  static Tower selectedTower;
  
  static ArrayList<Tower> towers = new ArrayList<>();
  
  static ArrayList<Enemy> enemies = new ArrayList<>();
  
  static ArrayList<Projectile> projectiles = new ArrayList<>();
  
  static ArrayList<EnemySpawn> enemySpawns = new ArrayList<>();
  
  static Tile[][] grid = new Tile[39][22];
  
  public static Goal goal = new Goal(800.0D, 400.0D, 45, 45, 200, 0);
  
  ObjectManager() {
    EnemySpawn spawn = new EnemySpawn(50.0D, 50.0D, 45, 45);
    enemySpawns.add(spawn);
    EnemySpawn spawn2 = new EnemySpawn(1500.0D, 700.0D, 45, 45);
    enemySpawns.add(spawn2);
    this.enemySpawn = new Timer(50, this);
    for (int i = 0; i < grid.length; i++) {
      for (int index = 0; index < (grid[i]).length; index++)
        grid[i][index] = new Tile((i * 50), (index * 50)); 
    } 
    Tile tile = findLocation(800.0D, 400.0D);
    tile.setIsOccupied(true);
  }
  
  public void addTower(int x, int y) {
    x = (x - 7) / 50;
    y = (y - 31) / 50;
    x *= 50;
    y *= 50;
    if (isClearLocation(x, y) && money >= this.towerCost && !this.inWave && enemies.size() == 0) {
      Tower tower = new BasicTower(x, y, 45, 45);
      towers.add(tower);
      money -= this.towerCost;
      Tile tile = findLocation(x, y);
      tile.setGameObject(tower);
      tile.setIsOccupied(true);
    } 
  }
  
  public void addEnemy() {
    if (this.spawnCounter > this.spawnFrequency) {
      int location = this.rand.nextInt(enemySpawns.size());
      EnemySpawn spawn = enemySpawns.get(location);
      if (isClearLocation((int)spawn.x, (int)spawn.y)) {
        Enemy enemy;
        if (this.waveNum <= 30) {
          int enemyType = this.rand.nextInt(this.waveNum);
          if (enemyType >= 0 && enemyType < 3) {
            enemy = new BasicEnemy(spawn.x, spawn.y, 45, 45);
          } else if (enemyType >= 3 && enemyType <= 13) {
            enemy = new LessBasicEnemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } else {
            enemy = new EvenLessBasicEnemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } 
        } else if (this.waveNum <= 50) {
          int enemyType = this.rand.nextInt(9);
          if (enemyType == 0) {
            enemy = new BasicEnemy(spawn.x, spawn.y, 45, 45);
          } else if (enemyType >= 1 && enemyType <= 4) {
            enemy = new LessBasicEnemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } else if (enemyType == 5 || enemyType == 6) {
            enemy = new EvenLessBasicEnemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } else {
            enemy = new Enemy_Spawning_Enemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } 
        } else if (this.waveNum <= 75) {
          int enemyType = this.rand.nextInt(9);
          if (enemyType >= 0 && enemyType <= 3) {
            enemy = new LessBasicEnemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } else if (enemyType >= 4 && enemyType <= 6) {
            enemy = new EvenLessBasicEnemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } else {
            enemy = new Enemy_Spawning_Enemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } 
        } else {
          int enemyType = this.rand.nextInt(9);
          if (enemyType >= 0 && enemyType <= 1) {
            enemy = new LessBasicEnemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } else if (enemyType >= 2 && enemyType <= 3) {
            enemy = new EvenLessBasicEnemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } else {
            enemy = new Enemy_Spawning_Enemy(spawn.x, spawn.y, 45, 45, this.waveNum);
          } 
        } 
        enemies.add(enemy);
        this.spawnCounter = 0;
      } 
    } 
    this.spawnCounter += 20 + (int)(10.0D * (0.1D * this.waveNum - 1.0D));
  }
  
  public static void addProjectile(Projectile projectile) {
    projectiles.add(projectile);
  }
  
  public void draw(Graphics g) {
    goal.draw(g);
    for (Tower tower : towers)
      tower.draw(g); 
    for (Enemy enemy : enemies)
      enemy.draw(g); 
    for (Projectile projectile : projectiles)
      projectile.draw(g); 
    for (EnemySpawn spawn : enemySpawns)
      spawn.draw(g); 
    g.drawString("Money: " + money, 810, 45);
    if (this.inWave)
      g.drawString("Wave: " + this.waveNum, 960, 90); 
    if (selectedTower != null) {
      showMenu(g);
      selectedTower.drawRange(g);
    } 
  }
  
  public void update() {
    for (Enemy enemy : enemies)
      enemy.update(); 
    for (Tower tower : towers)
      tower.update(); 
    for (Projectile projectile : projectiles)
      projectile.update(); 
    if (System.currentTimeMillis() > this.waveStart + this.waveLength)
      endWave(); 
    checkAllCollisions();
    removeObjects();
  }
  
  public static void showMenu(Graphics g) {
    g.setColor(Color.magenta);
    g.fillRect(0, 870, 1920, 210);
    g.setColor(Color.black);
    g.drawString(selectedTower.type, 50, 890);
    g.drawString("Damage: " + selectedTower.damage, 50, 915);
    g.drawString("Range: " + selectedTower.range, 50, 940);
    g.drawString("Attack Cooldown: " + selectedTower.attackCD + " ms", 50, 965);
    g.drawString("Has ability: " + selectedTower.hasAbility, 50, 990);
    g.drawString("Armor Pen: " + selectedTower.armorPen, 50, 1015);
    g.drawString("Magic Pen: " + selectedTower.magicPen, 50, 1040);
    if (Tower.currentUpgrade == 0) {
      if (selectedTower.statButton1 != null)
        selectedTower.statButton1.draw(g); 
      if (selectedTower.statButton2 != null)
        selectedTower.statButton2.draw(g); 
      if (selectedTower.statButton3 != null)
        selectedTower.statButton3.draw(g); 
      if (selectedTower.classButton != null)
        selectedTower.classButton.draw(g); 
    } else {
      if (selectedTower.option1Button != null)
        selectedTower.option1Button.draw(g); 
      if (selectedTower.option2Button != null)
        selectedTower.option2Button.draw(g); 
      if (selectedTower.statsButton != null)
        selectedTower.statsButton.draw(g); 
    } 
  }
  
  public static int round(double value) {
    value = (int)(value / 50.0D);
    value *= 50.0D;
    return (int)value;
  }
  
  public static boolean isClearLocation(int x, int y) {
    x = round(x);
    y = round(y);
    Tile tile = findLocation(x, y);
    return !tile.getIsOccupied();
  }
  
  public static Tower getTowerAt(int x, int y) {
    x = (x - 7) / 50;
    y = (y - 31) / 50;
    x *= 50;
    y *= 50;
    Rectangle rect = new Rectangle(x, y, 45, 45);
    for (Tower tower : towers) {
      if (rect.intersects(tower.collisionBox) || rect.intersects(goal.collisionBox))
        return tower; 
    } 
    return null;
  }
  
  public void checkAllCollisions() {
    for (int i = 0; i < enemies.size(); i++) {
      if (enemies.get(i) != null) {
        Enemy enemy = enemies.get(i);
        if (enemy.collisionBox.intersects(goal.collisionBox)) {
          goal.takeDamage(enemy.damage, 0, 0, false, false);
          enemy.reachedGoal = true;
          enemy.isAlive = false;
        } 
        for (Tower tower : towers) {
          if (enemy.collisionBox.intersects(tower.collisionBox))
            tower.takeDamage(enemy.damage, 0, 0, false, false); 
        } 
        for (Projectile projectile : projectiles) {
          if (enemy.collisionBox.intersects(projectile.collisionBox)) {
            enemy.takeDamage(projectile.damage, projectile.armorPen, projectile.magicPen, projectile.isMagicDamage, projectile.isAOE);
            projectile.isAlive = false;
          } 
        } 
      } 
    } 
  }
  
  public void removeObjects() {
    if (!goal.isAlive)
      GamePanel.currentState = 2; 
    int i;
    for (i = 0; i < enemies.size(); i++) {
      if (!((Enemy)enemies.get(i)).isAlive) {
        if (!((Enemy)enemies.get(i)).reachedGoal)
          money += ((Enemy)enemies.get(i)).worth; 
        ((Enemy)enemies.get(i)).onDeath();
        enemies.remove(i);
      } 
    } 
    for (i = 0; i < towers.size(); i++) {
      if (!((Tower)towers.get(i)).isAlive)
        towers.remove(i); 
    } 
    for (i = 0; i < projectiles.size(); i++) {
      if (!((Projectile)projectiles.get(i)).isAlive)
        projectiles.remove(i); 
    } 
  }
  
  public static Tile findLocation(double x, double y) {
    x = round(x);
    y = round(y);
    for (int i = 0; i < grid.length; i++) {
      for (int index = 0; index < (grid[i]).length; index++) {
        if ((grid[i][index]).x == x && (grid[i][index]).y == y)
          return grid[i][index]; 
      } 
    } 
    return null;
  }
  
  public static ArrayList<Tile> findPath(Tile start, Tile end) {
    HashMap<Tile, Tile> cameFrom = new HashMap<>();
    HashMap<Tile, Integer> costs = new HashMap<>();
    PriorityQueue<Tile> frontier = new PriorityQueue<>();
    frontier.add(start);
    cameFrom.put(start, null);
    costs.put(start, Integer.valueOf(0));
    while (frontier.size() > 0) {
      Tile current = frontier.remove();
      if (current == end)
        break; 
      for (Tile next : getNeighborsOf(current)) {
        int newCost = ((Integer)costs.get(current)).intValue() + cost(next);
        if (!costs.containsKey(next) || newCost < ((Integer)costs.get(next)).intValue()) {
          frontier.add(next);
          costs.put(next, Integer.valueOf(newCost));
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
    ArrayList<Tile> path = new ArrayList<>();
    while (current != start) {
      path.add(current);
      current = cameFrom.get(current);
    } 
    ArrayList<Tile> reversePath = new ArrayList<>();
    for (int i = path.size() - 1; i >= 0; i--)
      reversePath.add(path.get(i)); 
    return reversePath;
  }
  
  public static int cost(Tile a) {
    return a.cost;
  }
  
  public static int heuristic(Tile a, Tile b) {
    return (int)(Math.abs(a.x - b.x) + Math.abs(a.y - b.y));
  }
  
  public static ArrayList<Tile> getNeighborsOf(Tile tile) {
    ArrayList<Tile> neighbors = tile.getNeighbors();
    if (neighbors == null) {
      neighbors = new ArrayList<>();
      Tile north = findLocation(tile.x, tile.y + 50.0D);
      Tile south = findLocation(tile.x, tile.y - 50.0D);
      Tile east = findLocation(tile.x + 50.0D, tile.y);
      Tile west = findLocation(tile.x - 50.0D, tile.y);
      if (north != null)
        neighbors.add(north); 
      if (south != null)
        neighbors.add(south); 
      if (east != null)
        neighbors.add(east); 
      if (west != null)
        neighbors.add(west); 
      tile.setNeighbors(neighbors);
    } 
    return neighbors;
  }
  
  public void startWave() {
    if (!this.inWave) {
      this.waveNum++;
      this.enemySpawn.start();
      this.waveStart = System.currentTimeMillis();
      this.waveLength = (30000 + 3000 * this.waveNum / 1000);
      this.inWave = true;
      for (Tower tower : towers)
        tower.startOfWaveAbility(); 
    } 
  }
  
  public void endWave() {
    this.enemySpawn.stop();
    this.inWave = false;
  }
  
  public void actionPerformed(ActionEvent e) {
    addEnemy();
  }
  
  public static double calculateDistance(GameObject a, GameObject b) {
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
  }
}
