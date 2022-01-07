import java.awt.Color;

public class BasicTower extends Tower {

	String upgradeOption1 = "Rifle Tower";
	String option1ButtonDescription = "Has increased range and damage, Increases \n attack Cooldown to 600 ms";
	String upgradeOption2Button = "Anti-Tank Tower";
	String option2ButtonDescription = "Does 2% more damage to \n enemies with more than 1000 hp";

	BasicTower(double x, double y, int width, int height) {
		super(x, y, width, height, 300, 15, 300, 250, Color.green, "Basic Tower", false);
		statButton1 = new MenuButton(200, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Damage", 500, "+ 30 damage", 30);
		statButton2 = new MenuButton(410, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Range", 500, "+ 25 range", 25);
		statButton3 = new MenuButton(620, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Attack Cooldown", 500, "- 50 ms", 50);
		classButton = new MenuButton(830, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", "Upgrade Tower", 0, " ", 0);
		option1Button = new MenuButton(200, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", upgradeOption1, 2000, option1ButtonDescription, 0);
		option2Button = new MenuButton(410, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", upgradeOption2Button, 2000, option2ButtonDescription, 0);
		statsButton = new MenuButton(620, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);

	}

	@Override
	public void increaseValue1() {
		if (damage < 95) {
			damage += statButton1.value;
			ObjectManager.money -= statButton1.cost;
		}
	}

	@Override
	public void increaseValue2() {
		if (range < 400) {
			range += statButton2.value;
			ObjectManager.money -= statButton2.cost;
		}
	}

	@Override
	public void increaseValue3() {
		if (attackCD > 100) {
			attackCD -= statButton3.value;
			ObjectManager.money -= statButton3.cost;
		}

	}

	@Override
	public void option1Upgrade() {
		//System.out.println("Turning into option 1 tower");
		ObjectManager.money -= option1Button.cost;
		isAlive = false;
		Tower tower = new RifleTower(x, y, width, height, health, 50, 600, 9999, color.WHITE);
		ObjectManager.towers.add(tower);
		ObjectManager.selectedTower = tower;
	}

	@Override
	public void option2Upgrade() {

	}

}