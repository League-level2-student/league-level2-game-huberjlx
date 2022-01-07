import java.awt.Color;

public class SemiAutoSniperTower extends Tower {
	
	SemiAutoSniperTower(double x, double y, int width, int height, int health, int damage, int attackCD, int range,
			Color color) {
		super(x, y, width, height, health, damage, attackCD, range, color, "Semi-Auto Sniper Tower", false);
		statButton1 = new MenuButton(200, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Damage", 500, "+ 15 damage", 30);
		statButton2 = new MenuButton(410, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Armor Piercing", 500, "+ 10 armor piercing", 10);
		statButton3 = new MenuButton(620, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade", "Attack Cooldown", 500, "- 25 ms", 50);
		MenuButton classButton;
		MenuButton option1Button; 
		MenuButton option2Button;
		statsButton = new MenuButton(620, TowerDefense.HEIGHT - 200, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);
	}
	
	@Override
	public void increaseValue1() {
		if (damage < 200) {
			damage += statButton1.value;
			ObjectManager.money -= statButton1.cost;
		}
	}
	
	@Override
	public void increaseValue2() {
		if (armorPiercing < 50) {
			armorPiercing += statButton2.value;
			ObjectManager.money -= statButton2.cost;
		}
	}
	
	@Override
	public void increaseValue3() {
		if (attackCD > 300) {
			attackCD -= statButton3.value;
			ObjectManager.money -= statButton3.cost;
		}

	}
}
