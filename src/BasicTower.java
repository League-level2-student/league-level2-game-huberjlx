import java.awt.Color;

public class BasicTower extends Tower {

	String upgradeOption1 = "Rifle Tower";
	String option1ButtonDescription = "Has increased range and damage, Increases \n attack Cooldown to 600 ms";

	BasicTower(double x, double y, int width, int height) {
		super(x, y, width, height, 300, 3, 0, 0, 300, 250, Color.ORANGE, "Basic Tower", false, false, 0, 0, Color.black);
		statButton1 = new MenuButton(200, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Damage", 50, "+ 3 damage", 3);
		statButton2 = new MenuButton(410, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Range", 50, "+ 5 range", 5);
		statButton3 = new MenuButton(620, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Attack Cooldown", 50, "- 5 ms", 5);
		classButton = new MenuButton(830, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", "Upgrade Tower", 0, " ", 0);
		option1Button = new MenuButton(200, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", upgradeOption1, 750, option1ButtonDescription, 0);
		statsButton = new MenuButton(410, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);
		
	}

	@Override
	public void increaseValue1() {
		if (damage < 15) {
			damage += statButton1.value;
			ObjectManager.money -= statButton1.cost;
		}
	}

	@Override
	public void increaseValue2() {
		if (range < 325) {
			range += statButton2.value;
			ObjectManager.money -= statButton2.cost;
		}
	}

	@Override
	public void increaseValue3() {
		if (attackCD > 200) {
			attackCD -= statButton3.value;
			ObjectManager.money -= statButton3.cost;
		}

	}

	@Override
	public void option1Upgrade() {
		//System.out.println("Turning into option 1 tower");
		ObjectManager.money -= option1Button.cost;
		isAlive = false;
		Tower tower = new RifleTower(x, y, width, height, health, 25, 500, 350, color.WHITE);
		ObjectManager.towers.add(tower);
		ObjectManager.selectedTower = tower;
	}

}
