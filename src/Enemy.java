import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject {

	public Enemy(double x, double y, int width, int height, int health, int damage) {
		super(x, y, width, height, health, damage);
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)x, (int)y, width, height);
		drawHP(g);
	}
	
	
	public void update() {
		super.update();
		double goalX = ObjectManager.goal.x;
		double goalY = ObjectManager.goal.y;
		
		if (x < goalX) {
			x++;
		}
		if (y < goalY) {
			y++;
		}
		if (x > goalX) {
			x--;
		}
		if (y > goalY) {
			y--;
		}
	}
}
