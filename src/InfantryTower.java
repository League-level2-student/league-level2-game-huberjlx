import java.awt.Color;

public class InfantryTower extends Tower {
	String upgradeOption1 = "Artillery Tower";
	String option1Description = "High Attack CD, High Damage";
	String upgradeOption2 = "Tank Tower";
	String option2Description = "Does AOE damage with minor armor piercing";

	InfantryTower(double x, double y, int width, int height, int health, int damage, int attackCD, int range,
			Color color) {
		super(x, y, width, height, health, damage, attackCD, range, color, "Infantry Tower", false);
		statButton1 = new MenuButton(200, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Damage", 400, "+ 20 damage", 20);
		statButton2 = new MenuButton(410, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "range", 250, "+ 25 range", 25);
		statButton3 = new MenuButton(620, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Attack Cooldown", 500, "- 10 ms", 10);
		classButton = new MenuButton(830, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", "Upgrade Tower", 0, " ", 0);
		option1Button = new MenuButton(200, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", upgradeOption1, 10000, option1Description, 0);
		option2Button = new MenuButton(410, TowerDefense.HEIGHT - 200, 200, 150, "Tower Upgrade", upgradeOption2, 10000, option2Description, 0);
		statsButton = new MenuButton(620, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);
	}
	
	@Override
	public void increaseValue1() {
		if (damage < 600) {
			damage += statButton1.value;
			ObjectManager.money -= statButton1.cost;
		}
	}
	
	@Override
	public void increaseValue2() {
		if (range <= 600) {
			range += statButton2.value;
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
	public void option1Upgrade() {
		ObjectManager.money -= option1Button.cost;
		isAlive = false;
		//Tower tower = new ArtilleryTower(x, y, width, height, health, 50, 600, 9999, color.WHITE);
		//ObjectManager.towers.add(tower);
		//ObjectManager.selectedTower = tower;
	}
	
	@Override
	public void option2Upgrade() {
		ObjectManager.money -= option1Button.cost;
		isAlive = false;
		// Tower tower = new TankTower(x, y, width, height, health, 50, 400, 500, color.WHITE);
		//ObjectManager.towers.add(tower);
		//ObjectManager.selectedTower = tower;
	}
}