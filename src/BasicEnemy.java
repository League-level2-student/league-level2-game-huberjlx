import java.awt.Color;

public class BasicEnemy extends Enemy {
	
	public BasicEnemy(double x, double y, int width, int height) {
		super(x, y, width, height, 50, 3, 1, 2, 1);
		color = Color.yellow;
	}
	
	
}
