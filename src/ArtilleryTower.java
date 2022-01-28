import java.awt.Color;

public class ArtilleryTower extends Tower {
	
	ArtilleryTower(double x, double y, int width, int height, int health, int damage, int attackCD, int range,
			Color color) {
		super(x, y, width, height, health, damage, 75, 0, attackCD, range, color, "Artillery Tower", false, true, damage/2, 100, Color.BLACK);
	}
}
