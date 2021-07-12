import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

	Timer frameDraw;

	static final int MENU = 0;
	static final int GAME = 1;
	static final int END = 2;
	static int currentState = MENU;
	int score = 0;

	ObjectManager objectManager = new ObjectManager();

	GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenu(g);
		} else if (currentState == GAME) {
			drawGame(g);
		} else {
			drawEnd(g);
		}

	}

	public void drawMenu(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
	}

	public void drawGame(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		objectManager.draw(g);
	}

	public void drawEnd(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
	}

	public void updateMenuState() {

	}

	public void updateGameState() {
		objectManager.update();
	}

	public void updateEndState() {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				score = 0;
				currentState = MENU;
			} else {
				currentState++;
			}

		}
		if (e.getKeyCode() == 32) {
			objectManager.startWave();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}

		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		objectManager.addTower(e.getX(), e.getY());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
