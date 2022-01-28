import java.awt.Color;

public class SemiAutoSniperTower extends Tower {
  SemiAutoSniperTower(double x, double y, int width, int height, int health, int damage, int attackCD, int range, Color color) {
    super(x, y, width, height, health, damage, 0, 0, attackCD, range, color, "Semi-Auto Sniper Tower", false, false, 0.0D, 0.0D, Color.black);
    this.statButton1 = new MenuButton(200.0D, 880.0D, 200, 150, "Upgrade", "Damage", 500, "+ 15 damage", 15);
    this.statButton2 = new MenuButton(410.0D, 880.0D, 200, 150, "Upgrade", "Armor Piercing", 500, "+ 10 armor piercing", 10);
    this.statButton3 = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade", "Attack Cooldown", 500, "- 25 ms", 25);
    this.statsButton = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);
  }
  
  public void increaseValue1() {
    if (this.damage < 200) {
      this.damage += this.statButton1.value;
      ObjectManager.money -= this.statButton1.cost;
    } 
  }
  
  public void increaseValue2() {
    if (this.armorPen < 50) {
      this.armorPen += this.statButton2.value;
      ObjectManager.money -= this.statButton2.cost;
    } 
  }
  
  public void increaseValue3() {
    if (this.attackCD > 300L) {
      this.attackCD -= this.statButton3.value;
      ObjectManager.money -= this.statButton3.cost;
    } 
  }
}
