import java.awt.Color;
import java.awt.Graphics;

public class MenuButton {
	double x;
	double y;
	int width;
	int height;
	String type;
	String description; // In what way
	int cost;
	String effect; // What does it do / by how much
	int value; // Value that the button effects the tower by
	
	MenuButton(double x, double y, int width, int height, String type, String description, int cost, String effect, int value) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.description = description;
		this.cost = cost;
		this.effect = effect;
		this.value = value;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.drawRect((int)x, (int)y, width, height);
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, width, height);
		g.setColor(Color.black);
		g.drawString(type, (int)(x + (width / 2) - (type.length() * 6.5 / 2)), (int) y + height / 2 - 20);
		g.drawString(description, (int)(x + (width / 2) - (description.length() * 6.5 / 2)), (int)(y + height / 2));
		g.drawString("Cost: " + cost, (int)(x + (width / 2) - (("Cost: " + cost).length() * 6.5 / 2) - 15), (int) y + height / 2 + 20);
		
		int yMod = 40;
		for (String line : effect.split("\n")) {
			g.drawString(line, (int)(x + (width / 2) - (line.length() * 6.5 / 2)), (int) y + height / 2 + yMod);
			yMod += 20;
		}
	}
	
	public boolean isClicked(int x, int y) {
		if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height) {
			return true;
		}
		return false;
	}
	
	
}
