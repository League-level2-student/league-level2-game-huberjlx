
import java.awt.Color;
import java.awt.Graphics;

public class Tower extends GameObject {
	int range;
	long attackCD;
	long attackTime;
	int armorPiercing;
	String type;
	Enemy target;
	Color color;
	boolean hasInWaveAbility = false;
	boolean hasAfterWaveAbility = false;
	boolean hasAbility = false;
	boolean isMagicDamage;

	static final int STATUPGRADE = 0;
	static final int CLASSUPGRADE = 1;
	static int currentUpgrade = STATUPGRADE;

	MenuButton statButton1;
	MenuButton statButton2;
	MenuButton statButton3;
	MenuButton classButton;
	MenuButton option1Button;
	MenuButton option2Button;
	MenuButton statsButton;

	Tower(double x, double y, int width, int height, int health, int damage, int attackCD, int range, Color color, String type, boolean isMagicDamage) {
		super(x, y, width, height, health, damage, false, 0, 0);
		this.type = type;
		this.range = range;
		this.attackCD = attackCD;
		this.color = color;
		this.armorPiercing = 0;
		this.isMagicDamage = isMagicDamage;


	}

	public void update() {
		findTarget();
		if (target != null) {
			//System.out.println((int)ObjectManager.calculateDistance(this, target) + ", range: " + range + ", " + target.isAlive);
			if (target.isAlive == false) target = null;
		}
		if (System.currentTimeMillis() >= attackTime + attackCD && target != null && target.isAlive && ObjectManager.calculateDistance(this, target) < range) {
			attackTime = System.currentTimeMillis();
			fire();
		}
		if (hasInWaveAbility) {
			inWaveAbility();
		}

	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
		//if (target != null) g.drawLine((int)x, (int)y, (int)target.x, (int)target.y);
	}

	public void drawRange(Graphics g) {
		int range = (int)(this.range * 1.8);
		g.drawOval((int)x + (width / 2) - (range / 2), (int)y + (height / 2) - (range / 2), range, range);
	}

	public void fire() {
		Projectile projectile = new Projectile(x + width / 2 - 5, y + height / 2 - 5, 10, 10, damage, target, isMagicDamage);
		ObjectManager.addProjectile(projectile);
	}

	public double findTarget() {
		Enemy target = this.target;
		double closest;
		if (target != null) closest = target.distance;
		else closest = Double.MAX_VALUE;
		double distance = closest;
		for (Enemy enemy : ObjectManager.enemies) {
			//distance = Math.sqrt(Math.pow(enemy.x - x, 2) + Math.pow(enemy.y - y, 2));
			distance = (double) (Math.abs((x + width / 2) - (enemy.x + enemy.width / 2)) + Math.abs((y + height / 2) - (enemy.y + enemy.height / 2)));
			enemy.distance = distance;
			if (distance < closest) {
				closest = distance;
				target = enemy;
			}

		}
		this.target = target;
		return distance;
	}

	public void showMenu() {

	}

	public void increaseValue1() {

	}
	public void increaseValue2() {

	}
	public void increaseValue3() {

	}
	public void changeMenu() {
		if (currentUpgrade == CLASSUPGRADE) {
			currentUpgrade = STATUPGRADE;
		}
		else {
			currentUpgrade = CLASSUPGRADE;
		}
	}
	public void option1Upgrade() {
	}
	public void option2Upgrade() {

	}

	public void inWaveAbility() {

	}

	public void startOfWaveAbility() {

	}
}
