import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {
  Enemy target;
  
  double vX;
  
  double vY;
  
  int armorPen;
  
  int magicPen;
  
  boolean isMagicDamage;
  
  boolean isAOE;
  
  Color color;
  
  public Projectile(double x, double y, int width, int height, int damage, int armorPen, int magicPen, Enemy target, boolean isMagicDamage, boolean isAOE, Color color) {
    super(x, y, width, height, 1, damage, false, 0, 0, 0.0D, 0.0D);
    this.target = target;
    double startingX = this.x + (width / 2);
    double startingY = this.y + (height / 2);
    double targetX = ((Tile)target.path.get(0)).x + 25.0D;
    double targetY = ((Tile)target.path.get(0)).y + 25.0D;
    double slopeX = targetX - startingX;
    double slopeY = targetY - startingY;
    this.vX = slopeX / 50.0D;
    this.vY = slopeY / 50.0D;
    this.isMagicDamage = isMagicDamage;
    this.isAOE = isAOE;
    this.armorPen = armorPen;
    this.magicPen = magicPen;
    this.color = color;
  }
  
  public void draw(Graphics g) {
    g.setColor(this.color);
    g.fillOval((int)this.x, (int)this.y, this.width, this.height);
  }
  
  public void update() {
    super.update();
    calculateMovement();
    if (this.x > 1920.0D || this.x < 0.0D || this.y > 1080.0D || this.y < 0.0D)
      this.isAlive = false; 
  }
  
  public void calculateMovement() {
    this.x += this.vX;
    this.y += this.vY;
  }
}
