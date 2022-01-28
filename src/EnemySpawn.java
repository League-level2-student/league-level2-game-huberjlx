import java.awt.Color;
import java.awt.Graphics;

public class EnemySpawn extends GameObject{

	public EnemySpawn(double x, double y, int width, int height) {
		super(x, y, width, height, 0, 0, false, 0, 0, 0, 0);
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
