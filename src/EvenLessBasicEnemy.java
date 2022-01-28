import java.awt.Color;

public class EvenLessBasicEnemy extends Enemy {

	public EvenLessBasicEnemy(double x, double y, int width, int height, int multiplier) {
		super(x, y, width, height, 50, 20, 30, 5, multiplier, false, 400, 1);
		color = Color.BLUE;
	}
}
