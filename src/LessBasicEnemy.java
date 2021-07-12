import java.awt.Color;

public class LessBasicEnemy extends Enemy {

	public LessBasicEnemy(double x, double y, int width, int height, int multiplier) {
		super(x, y, width, height, 175, 10, 5, 1, multiplier);
		color = Color.DARK_GRAY;
	}

}
