import java.awt.Color;

public class WizardTower extends Tower {
  WizardTower(double x, double y, int width, int height, int health, int damage, int attackCD, int range, Color color, double splashDamage, double splashRange) {
    super(x, y, width, height, health, damage, 0, 0, attackCD, range, color, "Wizard Tower", true, true, splashDamage, splashRange, Color.magenta);
    this.statButton1 = new MenuButton(200.0D, 880.0D, 200, 150, "Upgrade", "Damage", 200, "+ 10 spell casting", 10);
    this.statButton2 = new MenuButton(410.0D, 880.0D, 200, 150, "Upgrade", "Splash Damage", 350, "+ 20 splash damage range and 10 splash damage", 2);
    this.statButton3 = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade", "Magic Pen", 850, "+20 Magic Pen", 20);
    this.statsButton = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);
  }
  
  public void increaseValue1() {
    if (this.damage < 600) {
      this.damage += this.statButton1.value;
      ObjectManager.money -= this.statButton1.cost;
    } 
  }
  
  public void increaseValue2() {
    if (this.splashRange < 300.0D) {
      this.splashRange += 20.0D;
      this.splashDamage += 10;
      ObjectManager.money -= this.statButton2.cost;
    } 
  }
  
  public void increaseValue3() {
    if (this.magicPen < 100) {
      this.magicPen += this.statButton3.value;
      ObjectManager.money -= this.statButton3.cost;
    } 
  }
}
