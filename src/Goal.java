import java.awt.Color;
import java.awt.Graphics;

public class Goal extends GameObject{
	
	public Goal(double x, double y, int width, int height, int health, int damage) {
		super(x, y, width, height, health + 1000, damage);
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect((int)x, (int)y, width, height);
		drawHP(g);
	}

}
