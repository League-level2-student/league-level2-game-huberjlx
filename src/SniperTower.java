import java.awt.Color;

public class SniperTower extends Tower {
  String upgradeOption1 = "Semi-Auto Sniper Tower";
  
  String option1Description = "Decreases attack Cooldown to 400 ms";
  
  SniperTower(double x, double y, int width, int height, int health, int damage, int attackCD, int range, Color color) {
    super(x, y, width, height, health, damage, 0, 0, attackCD, range, color, "Sniper Tower", false, false, 0.0D, 0.0D, Color.black);
    this.statButton1 = new MenuButton(200.0D, 880.0D, 200, 150, "Upgrade", "Damage", 500, "+ 25 damage", 25);
    this.statButton2 = new MenuButton(410.0D, 880.0D, 200, 150, "Get Class ability", "Get supply drop", 1500, "+ 125 money per drop", 125);
    this.statButton3 = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade", "Attack Cooldown", 500, "- 10 ms", 10);
    this.classButton = new MenuButton(830.0D, 880.0D, 200, 150, "Tower Upgrade", "Upgrade Tower", 0, " ", 0);
    this.option1Button = new MenuButton(200.0D, 880.0D, 200, 150, "Tower Upgrade", this.upgradeOption1, 9000, this.option1Description, 0);
    this.statsButton = new MenuButton(620.0D, 880.0D, 200, 150, "Upgrade Tower Stats", "upgrade stats", 0, " ", 0);
  }
  
  public void increaseValue1() {
    if (this.damage < 200) {
      this.damage += this.statButton1.value;
      ObjectManager.money -= this.statButton1.cost;
    } 
  }
  
  public void increaseValue2() {
    if (!this.hasInWaveAbility) {
      this.hasInWaveAbility = true;
      this.hasAbility = true;
      ObjectManager.money -= this.statButton2.cost;
    } 
  }
  
  public void increaseValue3() {
    if (this.attackCD >= 550L) {
      this.attackCD -= this.statButton3.value;
      ObjectManager.money -= this.statButton3.cost;
    } 
  }
  
  public void startOfWaveAbility() {
    if (this.hasInWaveAbility)
      ObjectManager.money += 125; 
  }
  
  public void option1Upgrade() {
    ObjectManager.money -= this.option1Button.cost;
    this.isAlive = false;
    Tower tower = new SemiAutoSniperTower(this.x, this.y, this.width, this.height, this.health, 100, 400, 9999, Color.gray);
    ObjectManager.towers.add(tower);
    ObjectManager.selectedTower = tower;
  }
}
