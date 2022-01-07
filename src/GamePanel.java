import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{

	Timer frameDraw;

	static final int MENU = 0;
	static final int GAME = 1;
	static final int END = 2;
	static int currentState = MENU;
	int score = 0;
	Tile hoveredTile;

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
		if (hoveredTile != null) {
			hoveredTile.draw(g);
		}
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
		//System.out.println("Clicked");
		if (ObjectManager.getTowerAt(e.getX(), e.getY()) != null && ObjectManager.selectedTower == null) {
			Tower tower = ObjectManager.getTowerAt(e.getX(), e.getY());
			ObjectManager.selectedTower = tower;
			tower.showMenu();
		}
		
		else if (!hoveredTile.getIsOccupied()) {
			if (e.getY() < TowerDefense.HEIGHT - 150 && ObjectManager.selectedTower != null) {
				ObjectManager.selectedTower = null;
			}
			else if (ObjectManager.selectedTower == null) {
				objectManager.addTower(e.getX(), e.getY());
				ObjectManager.selectedTower = null;
			}
		}
		
		if (ObjectManager.selectedTower != null) {
			if (ObjectManager.selectedTower.statButton1.isClicked(e.getX(), e.getY()) && ObjectManager.selectedTower.currentUpgrade == 0) {
				ObjectManager.selectedTower.increaseValue1();
			}
			else if (ObjectManager.selectedTower.statButton2.isClicked(e.getX(), e.getY()) && ObjectManager.selectedTower.currentUpgrade == 0) {
				ObjectManager.selectedTower.increaseValue2();
			}
			else if (ObjectManager.selectedTower.statButton3.isClicked(e.getX(), e.getY()) && ObjectManager.selectedTower.currentUpgrade == 0) {
				ObjectManager.selectedTower.increaseValue3();
			}
			else if (ObjectManager.selectedTower.classButton != null && ObjectManager.selectedTower.classButton.isClicked(e.getX(), e.getY()) && ObjectManager.selectedTower.currentUpgrade == 0) {
				ObjectManager.selectedTower.changeMenu();
			}
			else if (ObjectManager.selectedTower.statsButton != null && ObjectManager.selectedTower.statsButton.isClicked(e.getX(), e.getY()) && ObjectManager.selectedTower.currentUpgrade == 1) {
				ObjectManager.selectedTower.changeMenu();
			}
			else if (ObjectManager.selectedTower.option1Button != null && ObjectManager.selectedTower.option1Button.isClicked(e.getX(), e.getY()) && ObjectManager.selectedTower.currentUpgrade == 1) {
				ObjectManager.selectedTower.option1Upgrade();
			}
			else if (ObjectManager.selectedTower.option2Button != null && ObjectManager.selectedTower.option2Button.isClicked(e.getX(), e.getY()) && ObjectManager.selectedTower.currentUpgrade == 1) {
				ObjectManager.selectedTower.option2Upgrade();
			}
		}
	
		

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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = (e.getX() - 7) / 50;
		int y = (e.getY() - 31) / 50;
		
		hoveredTile = objectManager.grid[x][y];
		
		
		
	}

}
