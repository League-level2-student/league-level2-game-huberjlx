import java.awt.Color;

public class RifleTower extends Tower {
  String upgradeOption1 = "Sniper Tower";
  String option1Description = "Has infinte range \n Iecreases attack Cooldown to 600 ms";
  String upgradeOption2 = "Infantry Tower";
  String option2Description = "Increases base damage and \n increases attack cooldown to 400 ms";
  
  RifleTower(double x, double y, int width, int height, int health, int damage, int attackCD, int range, Color color) {
    super(x, y, width, height, health, damage, 0, 0, attackCD, range, color, "Rifle Tower", false, false, 0.0D, 0.0D, Color.black);
    this.statButton1 = new MenuButton(200.0D, 880.0D, 200, 150, "Upgrade", "Damage", 200, "+ 10 damage", 10);
    this.statButton2 = new MenuButton(410.0D, 880.0D, 200, 150, "Upgrade", "Range", 200, "+ 10 range", 10);
    this.statButton3 = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade", "Attack Cooldown", 100, "- 15 ms", 15);
    this.classButton = new MenuButton(830.0D, 880.0D, 200, 150, "Tower Upgrade", "Upgrade Tower", 0, " ", 0);
    this.option1Button = new MenuButton(200.0D, 880.0D, 200, 150, "Tower Upgrade", this.upgradeOption1, 1000, this.option1Description, 0);
    this.option2Button = new MenuButton(410.0D, 880.0D, 200, 150, "Tower Upgrade", this.upgradeOption2, 750, this.option2Description, 0);
    this.statsButton = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);
  }
  
  public void increaseValue1() {
    if (this.damage < 200) {
      this.damage += this.statButton1.value;
      ObjectManager.money -= this.statButton1.cost;
    } 
  }
  
  public void increaseValue2() {
    if (this.range < 600) {
      this.range += this.statButton2.value;
      ObjectManager.money -= this.statButton2.cost;
    } 
  }
  
  public void increaseValue3() {
    if (this.attackCD > 400L) {
      this.attackCD -= this.statButton3.value;
      ObjectManager.money -= this.statButton3.cost;
    } 
  }
  
  public void option1Upgrade() {
    ObjectManager.money -= this.option1Button.cost;
    this.isAlive = false;
    Tower tower = new SniperTower(this.x, this.y, this.width, this.height, this.health, 75, 600, 9999, Color.lightGray);
    ObjectManager.towers.add(tower);
    ObjectManager.selectedTower = tower;
  }
  
  public void option2Upgrade() {
    ObjectManager.money -= this.option1Button.cost;
    this.isAlive = false;
    Tower tower = new InfantryTower(this.x, this.y, this.width, this.height, this.health, 75, 400, 500, Color.pink);
    ObjectManager.towers.add(tower);
    ObjectManager.selectedTower = tower;
  }
}
