import java.awt.Color;

public class SniperTower extends Tower {
	String upgradeOption1 = "Semi-Auto Sniper Tower";
	String option1Description = "Decreases attack Cooldown to 400 ms";

	SniperTower(double x, double y, int width, int height, int health, int damage, int attackCD, int range,
			Color color) {
		super(x, y, width, height, health, damage, attackCD, range, color, "Sniper Tower", false);
		statButton1 = new MenuButton(200, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Damage", 1000, "+ 60 damage", 60);
		statButton2 = new MenuButton(410, TowerDefense.HEIGHT - 200, 200, 150, "Get Class ability", "Get supply drop", 1000, "+ 125 money per drop", 125);
		statButton3 = new MenuButton(620, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Attack Cooldown", 500, "- 10 ms", 10);
		classButton = new MenuButton(830, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", "Upgrade Tower", 0, " ", 0);
		option1Button = new MenuButton(200, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", upgradeOption1, 8000, option1Description, 0);
		statsButton = new MenuButton(620, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);
	}
	
	@Override
	public void increaseValue1() {
		if (damage < 350) {
			damage += statButton1.value;
			ObjectManager.money -= statButton1.cost;
		}
	}
	
	@Override
	public void increaseValue2() {
		if (!hasInWaveAbility) {
			hasInWaveAbility = true;
			hasAbility = true;
			ObjectManager.money -= statButton2.cost;
		}
	}
	
	@Override
	public void increaseValue3() {
		if (attackCD >= 550) {
			attackCD -= statButton3.value;
			ObjectManager.money -= statButton3.cost;
		}
	}
	
	@Override
	public void startOfWaveAbility() {
		if (hasInWaveAbility) {
			ObjectManager.money += 125;
		}	
	}
	
	@Override
	public void option1Upgrade() {
		ObjectManager.money -= option1Button.cost;
		isAlive = false;
		Tower tower = new SemiAutoSniperTower(x, y, width, height, health, 50, 600, 9999, color.gray);
		ObjectManager.towers.add(tower);
		ObjectManager.selectedTower = tower;
	}
}
