import java.awt.Color;

public class EvenLessBasicEnemy extends Enemy {

	public EvenLessBasicEnemy(double x, double y, int width, int height, int multiplier) {
		super(x, y, width, height, 105, 100, 10, 2, multiplier, false, 400, 0);
		color = Color.BLUE;
	}
}
