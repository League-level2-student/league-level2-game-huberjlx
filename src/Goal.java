import java.awt.Color;
import java.awt.Graphics;

public class Goal extends GameObject {
  public Goal(double x, double y, int width, int height, int health, int damage) {
    super(x, y, width, height, health + 1000, damage, false, 50, 50, 0.0D, 0.0D);
  }
  
  public void draw(Graphics g) {
    g.setColor(Color.MAGENTA);
    g.fillRect((int)this.x, (int)this.y, this.width, this.height);
    drawHP(g);
  }
  
  @Override
  public void drawHP(Graphics g) {
    g.setColor(Color.green);
    double healthPercent = (double)this.health / (double)this.maxHealth;
    int rectWidth = (int)((this.width * 3) * healthPercent);
    int rectHeight = 60;
    g.fillRect(960 - this.width / 2, 10, rectWidth, rectHeight);
    g.setColor(Color.black);
    String healthStr = "Base Health: " + this.health;
    g.drawString(healthStr, 960 - this.width / 2, 45);
  }

}
