import java.awt.Dimension;

import javax.swing.JFrame;
public class TowerDefense {
		JFrame frame;
		GamePanel gamePanel;

		public static final int WIDTH = 1920;
		public static final int HEIGHT = 1080;

		public static void main(String[] args) {

			TowerDefense game = new TowerDefense();
			game.setup();
		}

		public void setup() {
			frame.add(gamePanel);
			frame.addKeyListener(gamePanel);
			frame.addMouseListener(gamePanel);
			frame.addMouseMotionListener(gamePanel);
			frame.setVisible(true);
			frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
		}
		TowerDefense() {
			frame = new JFrame();
			gamePanel = new GamePanel();

		}
	}
