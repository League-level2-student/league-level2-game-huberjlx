import java.awt.Color;
import java.awt.Graphics;

public class Tower extends GameObject {
  int range;
  
  long attackCD;
  
  long attackTime;
  
  int armorPen;
  
  int magicPen;
  
  String type;
  
  Enemy target;
  
  Color color;
  
  boolean hasInWaveAbility = false;
  
  boolean hasAfterWaveAbility = false;
  
  boolean hasAbility = false;
  
  boolean isMagicDamage;
  
  boolean isAOE;
  
  Color projectileColor;
  
  static final int STATUPGRADE = 0;
  
  static final int CLASSUPGRADE = 1;
  
  static int currentUpgrade = 0;
  
  MenuButton statButton1;
  
  MenuButton statButton2;
  
  MenuButton statButton3;
  
  MenuButton classButton;
  
  MenuButton option1Button;
  
  MenuButton option2Button;
  
  MenuButton statsButton;
  
  Tower(double x, double y, int width, int height, int health, int damage, int armorPen, int magicPen, int attackCD, int range, Color color, String type, boolean isMagicDamage, boolean isAOE, double splashRange, double splashDamage, Color projectileColor) {
    super(x, y, width, height, health, damage, false, 0, 0, splashDamage, splashRange);
    this.type = type;
    this.range = range;
    this.attackCD = attackCD;
    this.color = color;
    this.armorPen = armorPen;
    this.magicPen = magicPen;
    this.isMagicDamage = isMagicDamage;
    this.isAOE = isAOE;
    this.projectileColor = projectileColor;
  }
  
  public void update() {
    findTarget();
    if (this.target != null)
      if (!this.target.isAlive)
        this.target = null;  
    if (System.currentTimeMillis() >= this.attackTime + this.attackCD && this.target != null && this.target.isAlive && ObjectManager.calculateDistance(this, this.target) < this.range) {
      this.attackTime = System.currentTimeMillis();
      fire();
    } 
    if (this.hasInWaveAbility)
      inWaveAbility(); 
  }
  
  public void draw(Graphics g) {
    g.setColor(this.color);
    g.fillRect((int)this.x, (int)this.y, this.width, this.height);
  }
  
  public void drawRange(Graphics g) {
    int range = (int)(this.range * 1.8D);
    g.drawOval((int)this.x + this.width / 2 - range / 2, (int)this.y + this.height / 2 - range / 2, range, range);
  }
  
  public void fire() {
    Projectile projectile = new Projectile(this.x + (this.width / 2) - 5.0D, this.y + (this.height / 2) - 5.0D, 10, 10, this.damage, this.armorPen, this.magicPen, this.target, this.isMagicDamage, this.isAOE, this.projectileColor);
    ObjectManager.addProjectile(projectile);
  }
  
  public double findTarget() {
    double closest;
    Enemy target = this.target;
    if (target != null) {
      closest = target.distance;
    } else {
      closest = Double.MAX_VALUE;
    } 
    double distance = closest;
    for (Enemy enemy : ObjectManager.enemies) {
      distance = Math.abs(this.x + (this.width / 2) - enemy.x + (enemy.width / 2)) + Math.abs(this.y + (this.height / 2) - enemy.y + (enemy.height / 2));
      enemy.distance = distance;
      if (distance < closest) {
        closest = distance;
        target = enemy;
      } 
    } 
    this.target = target;
    return distance;
  }
  
  public void showMenu() {}
  
  public void increaseValue1() {}
  
  public void increaseValue2() {}
  
  public void increaseValue3() {}
  
  public void changeMenu() {
    if (currentUpgrade == 1) {
      currentUpgrade = 0;
    } else {
      currentUpgrade = 1;
    } 
  }
  
  public void option1Upgrade() {}
  
  public void option2Upgrade() {}
  
  public void inWaveAbility() {}
  
  public void startOfWaveAbility() {}
	
}
