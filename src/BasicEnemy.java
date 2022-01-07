import java.awt.Color;

public class BasicEnemy extends Enemy {
	
	public BasicEnemy(double x, double y, int width, int height) {
		super(x, y, width, height, 50, 3, 3, 2, 1, false, 0, 0);
		color = Color.yellow;
	}
	
	
}
