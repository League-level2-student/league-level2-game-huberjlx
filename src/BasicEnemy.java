import java.awt.Color;

public class BasicEnemy extends Enemy {

	public BasicEnemy(double x, double y, int width, int height) {
		super(x, y, width, height, 50, 3, 10, 2, 1, false, 1, 1);
		color = Color.yellow;
	}


}
