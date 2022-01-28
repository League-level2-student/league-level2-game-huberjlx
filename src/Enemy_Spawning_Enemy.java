import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Enemy_Spawning_Enemy extends Enemy {
	boolean spawnsEnemies = true;
	
	public Enemy_Spawning_Enemy(double x, double y, int width, int height, int multiplier) {
		super(x, y, width, height, 1000, 600, 300, 1, multiplier, true, 100, 50);
		color = Color.CYAN;
	}
	@Override
	public void onDeath() {
		int modifier = 4 + multiplier % 15;
		for (int i = 0; i < modifier; i++) {	
			Enemy enemy = new LessBasicEnemy(x, y, 45, 45, 20);
			ObjectManager.enemies.add(enemy);
			//System.out.println(enemy.path.size()); 
			
		}
	}
}
