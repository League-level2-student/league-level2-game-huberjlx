import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MenuOption {
	double x;
	double y;
	int width;
	int height;
	String text;
	boolean isHighlighted;
	
	MenuOption(double x, double y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		//System.out.println(TowerDefense.WIDTH / 2 - (text.length() * 8) + " " + TowerDefense.HEIGHT * 0.5  + " " + (int) (text.length() * 8) + 30 + " " + 50  + " " + TowerDefense.WIDTH  + " " + TowerDefense.HEIGHT); 
	}
	
	public void draw(Graphics g) {
		if (isHighlighted) {
			g.setColor(Color.WHITE);
			g.drawRect((int)x, (int)y, width, height);
			g.fillRect((int)x, (int)y, width, height);
			g.setColor(Color.black);
			Font textFont = new Font("SansSerif", Font.PLAIN, 18);
			g.setFont(textFont);
			g.drawString(text, (int)x + ((width / 2) - (text.length() * 8 / 2)), (int)y + (height / 2));
		}
		else {
			g.setColor(Color.WHITE);
			g.drawRect((int)x, (int)y, width, height);
			Font textFont = new Font("SansSerif", Font.PLAIN, 18);
			g.setFont(textFont);
			g.drawString(text, (int)x + ((width / 2) - (text.length() * 8 / 2)), (int)y + (height / 2));
		}

	}
	
	public boolean isClicked(int x, int y) {
		if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height) {
			return true;
		}
		return false;
	}
}
