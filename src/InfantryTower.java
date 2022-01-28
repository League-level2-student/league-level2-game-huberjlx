import java.awt.Color;

public class InfantryTower extends Tower {
  String upgradeOption1 = "Artillery Tower";
  
  String option1Description = "High Attack CD, High Damage";
  
  String upgradeOption2 = "Wizard Tower";
  
  String option2Description = "Does AOE damage with magic damage";
  
  InfantryTower(double x, double y, int width, int height, int health, int damage, int attackCD, int range, Color color) {
    super(x, y, width, height, health, damage, 0, 0, attackCD, range, color, "Infantry Tower", false, false, 0.0D, 0.0D, Color.black);
    this.statButton1 = new MenuButton(200.0D, 880.0D, 200, 150, "Upgrade", "Damage", 200, "+ 5 damage", 5);
    this.statButton2 = new MenuButton(410.0D, 880.0D, 200, 150, "Upgrade", "range", 150, "+ 10 range", 10);
    this.statButton3 = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade", "Attack Cooldown", 200, "- 10 ms", 10);
    this.classButton = new MenuButton(830.0D, 880.0D, 200, 150, "Tower Upgrade", "Upgrade Tower", 0, " ", 0);
    this.option1Button = new MenuButton(200.0D, 880.0D, 200, 150, "Tower Upgrade", this.upgradeOption1, 18500, this.option1Description, 0);
    this.option2Button = new MenuButton(410.0D, 880.0D, 200, 150, "Tower Upgrade", this.upgradeOption2, 15000, this.option2Description, 0);
    this.statsButton = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);
  }
  
  public void increaseValue1() {
    if (this.damage < 150) {
      this.damage += this.statButton1.value;
      ObjectManager.money -= this.statButton1.cost;
    } 
  }
  
  public void increaseValue2() {
    if (this.range <= 600) {
      this.range += this.statButton2.value;
      ObjectManager.money -= this.statButton2.cost;
    } 
  }
  
  public void increaseValue3() {
    if (this.attackCD >= 450L) {
      this.attackCD -= this.statButton3.value;
      ObjectManager.money -= this.statButton3.cost;
    } 
  }
  
  public void option1Upgrade() {
    ObjectManager.money -= this.option1Button.cost;
    this.isAlive = false;
    Tower tower = new ArtilleryTower(this.x, this.y, this.width, this.height, this.health, 1000, 4500, 600, new Color(91, 130, 101));
    ObjectManager.towers.add(tower);
    ObjectManager.selectedTower = tower;
  }
  
  public void option2Upgrade() {
    ObjectManager.money -= this.option2Button.cost;
    this.isAlive = false;
    Tower tower = new WizardTower(this.x, this.y, this.width, this.height, this.health, 60, 400, 500, Color.cyan, (this.damage / 2), 100.0D);
    ObjectManager.towers.add(tower);
    ObjectManager.selectedTower = tower;
  }
}
