import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
  Timer frameDraw;
  
  static final int MENU = 0;
  
  static final int GAME = 1;
  
  static final int END = 2;
  
  static final int PAUSE = 3;
  
  static final int DIRECTIONS = 4;
  
  static boolean isPaused = false;
  
  static int currentState = 0;
  
  int score = 0;
  
  Tile hoveredTile;
  
  ObjectManager objectManager = new ObjectManager();
  
  String startText = "Press 'ENTER' to start";
  
  int baseWidth = this.startText.length() * 8 + 30;
  
  MenuOption startButton = new MenuOption((960 - this.startText.length() * 8 / 2), 540.0D, this.startText.length() * 8 + 30, 50, this.startText);
  
  String directionText = "Directions";
  
  MenuOption directionButton = new MenuOption((960 - this.startText.length() * 8 / 2), 610.0D, this.baseWidth, 50, this.directionText);
  
  String directions1 = "Left click to place a tower. Left Click on";
  
  String directions2 = "a tower to select it and upgrade its statistics";
  
  String directions3 = "or upgrade the type of tower. The tower type";
  
  String directions4 = "will give different (and better) stats, abilities,";
  
  String directions5 = "or types of damage. Press space to start a wave, then enemies";
  
  String directions6 = "will spawn from the black squares and will try to destroy your";
  
  String directions7 = "base, the purple sqaure. Use your towers to kill the";
  
  String directions8 = "enemies which rewards money. Make sure to leave";
  
  String directions9 = "a path for the enemies or they will destroy your towers.";
  
  String resume = "Resume";
  
  MenuOption resumeButton = new MenuOption((960 - this.startText.length() * 8 / 2), 540.0D, this.baseWidth, 50, this.resume);
  
  String quit = "quit";
  
  MenuOption quitButton = new MenuOption((960 - this.startText.length() * 8 / 2), 610.0D, this.baseWidth, 50, this.quit);
  
  GamePanel() {
    this.frameDraw = new Timer(16, this);
    this.frameDraw.start();
  }
  
  public void paintComponent(Graphics g) {
    if (currentState == 0) {
      drawMenu(g);
    } else if (currentState == 1) {
      drawGame(g);
    } else if (currentState == 4) {
      drawDirections(g);
    } else if (currentState == 3) {
      drawPause(g);
    } else {
      drawEnd(g);
    } 
  }
  
  public void drawMenu(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 1920, 1080);
    this.startButton.draw(g);
    this.directionButton.draw(g);
  }
  
  public void drawDirections(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 1920, 1080);
    g.setColor(Color.WHITE);
    Font font = new Font("SansSerif", 0, 20);
    g.setFont(font);
    String text = "Press 'ENTER' To Go Back To Main Menu";
    g.drawString(text, 960 - text.length() * 10 / 2, 108);
    Font textFont = new Font("SansSerif", 0, 18);
    g.setFont(textFont);
    g.drawString(this.directions1, 960 - this.directions1.length() * 8 / 2, 216);
    g.drawString(this.directions2, 960 - this.directions2.length() * 8 / 2, 236);
    g.drawString(this.directions3, 960 - this.directions3.length() * 8 / 2, 256);
    g.drawString(this.directions4, 960 - this.directions4.length() * 8 / 2, 276);
    g.drawString(this.directions5, 960 - this.directions5.length() * 8 / 2, 296);
    g.drawString(this.directions6, 960 - this.directions6.length() * 8 / 2, 316);
    g.drawString(this.directions7, 960 - this.directions7.length() * 8 / 2, 336);
    g.drawString(this.directions8, 960 - this.directions8.length() * 8 / 2, 356);
    g.drawString(this.directions9, 960 - this.directions9.length() * 8 / 2, 376);
  }
  
  public void drawGame(Graphics g) {
    g.setColor(new Color(224, 235, 162));
    g.fillRect(0, 0, 1920, 1080);
    this.objectManager.draw(g);
    if (this.hoveredTile != null)
      this.hoveredTile.draw(g); 
  }
  
  public void drawPause(Graphics g) {
    this.resumeButton.draw(g);
    this.quitButton.draw(g);
  }
  
  public void drawEnd(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, 1920, 1080);
    g.setColor(Color.WHITE);
    Font font = new Font("SansSerif", 0, 20);
    g.setFont(font);
    String text = "Press 'ENTER' To Go Back To Main Menu";
    g.drawString(text, 960 - text.length() * 10 / 2, 108);
    String scoreText = "Enemies Killed: " + ObjectManager.score;
    g.drawString(scoreText, 960 - scoreText.length() * 10 / 2, 324);
  }
  
  public void reset() {
    ObjectManager.score = 0;
    ObjectManager.money = 100;
    ObjectManager.selectedTower = null;
    ObjectManager.towers = new ArrayList<>();
    ObjectManager.enemies = new ArrayList<>();
    ObjectManager.projectiles = new ArrayList<>();
    ObjectManager.enemySpawns = new ArrayList<>();
    ObjectManager.grid = new Tile[39][22];
    ObjectManager.goal = new Goal(800.0D, 400.0D, 45, 45, 200, 0);
    this.objectManager = new ObjectManager();
  }
  
  public void updateMenuState() {}
  
  public void updateGameState() {
    this.objectManager.update();
  }
  
  public void updatePauseState() {}
  
  public void updateEndState() {}
  
  public void keyTyped(KeyEvent e) {}
  
  public void keyPressed(KeyEvent e) {
    if (currentState == 1) {
      if (e.getKeyCode() == 32)
        this.objectManager.startWave(); 
      if (e.getKeyCode() == 27) {
        isPaused = true;
        currentState = 3;
      } 
    } 
    if (currentState == 0 && 
      e.getKeyCode() == 10)
      currentState++; 
    if (currentState == 4 && 
      e.getKeyCode() == 10)
      currentState = 0; 
    if (currentState == 2 && 
      e.getKeyCode() == 10) {
      currentState = 0;
      reset();
    } 
  }
  
  public void keyReleased(KeyEvent e) {}
  
  public void actionPerformed(ActionEvent e) {
    if (currentState == 0) {
      updateMenuState();
    } else if (currentState == 3) {
      updatePauseState();
    } else if (currentState == 1) {
      updateGameState();
    } else if (currentState == 2) {
      updateEndState();
    } 
    repaint();
  }
  
  public void mouseClicked(MouseEvent e) {
    if (currentState == 1) {
      if (ObjectManager.getTowerAt(e.getX(), e.getY()) != null && ObjectManager.selectedTower == null) {
        Tower tower = ObjectManager.getTowerAt(e.getX(), e.getY());
        ObjectManager.selectedTower = tower;
        tower.showMenu();
      } else if (!this.hoveredTile.getIsOccupied()) {
        if (e.getY() < 930 && ObjectManager.selectedTower != null) {
          ObjectManager.selectedTower = null;
        } else if (ObjectManager.selectedTower == null) {
          this.objectManager.addTower(e.getX(), e.getY());
          ObjectManager.selectedTower = null;
        } 
      } 
      if (ObjectManager.selectedTower != null)
        if (ObjectManager.selectedTower.statButton1 != null && ObjectManager.selectedTower.statButton1.isClicked(e.getX(), e.getY()) && ObjectManager.money >= ObjectManager.selectedTower.statButton1.cost && Tower.currentUpgrade == 0) {
          ObjectManager.selectedTower.increaseValue1();
        } else if (ObjectManager.selectedTower.statButton2 != null && ObjectManager.selectedTower.statButton2.isClicked(e.getX(), e.getY()) && ObjectManager.money >= ObjectManager.selectedTower.statButton2.cost && Tower.currentUpgrade == 0) {
          ObjectManager.selectedTower.increaseValue2();
        } else if (ObjectManager.selectedTower.statButton3 != null && ObjectManager.selectedTower.statButton3.isClicked(e.getX(), e.getY()) && ObjectManager.money >= ObjectManager.selectedTower.statButton3.cost && Tower.currentUpgrade == 0) {
          ObjectManager.selectedTower.increaseValue3();
        } else if (ObjectManager.selectedTower.classButton != null && ObjectManager.selectedTower.classButton.isClicked(e.getX(), e.getY()) && Tower.currentUpgrade == 0) {
          ObjectManager.selectedTower.changeMenu();
        } else if (ObjectManager.selectedTower.statsButton != null && ObjectManager.selectedTower.statsButton.isClicked(e.getX(), e.getY()) && Tower.currentUpgrade == 1) {
          ObjectManager.selectedTower.changeMenu();
        } else if (ObjectManager.selectedTower.option1Button != null && ObjectManager.selectedTower.option1Button.isClicked(e.getX(), e.getY()) && ObjectManager.money >= ObjectManager.selectedTower.option1Button.cost && Tower.currentUpgrade == 1) {
          ObjectManager.selectedTower.option1Upgrade();
        } else if (ObjectManager.selectedTower.option2Button != null && ObjectManager.selectedTower.option2Button.isClicked(e.getX(), e.getY()) && ObjectManager.money >= ObjectManager.selectedTower.option2Button.cost && Tower.currentUpgrade == 1) {
          ObjectManager.selectedTower.option2Upgrade();
        }  
    } else if (currentState == 0) {
      if (this.startButton.isClicked(e.getX(), e.getY())) {
        currentState++;
      } else if (this.directionButton.isClicked(e.getX(), e.getY())) {
        currentState = 4;
      } 
    } else if (currentState == 3) {
      if (this.resumeButton.isClicked(e.getX(), e.getY())) {
        currentState = 1;
      } else if (this.quitButton.isClicked(e.getX(), e.getY())) {
        currentState = 2;
      } 
    } 
  }
  
  
  public void mousePressed(MouseEvent e) {}
  
  public void mouseReleased(MouseEvent e) {}
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
  
  public void mouseDragged(MouseEvent e) {}
  
  public void mouseMoved(MouseEvent e) {
    int x = (e.getX() - 7) / 50;
    int y = (e.getY() - 31) / 50;
    if (currentState == 1) {
      this.hoveredTile = ObjectManager.grid[x][y];
    } else if (currentState == 0) {
      if (this.startButton.isClicked(e.getX(), e.getY())) {
        this.startButton.isHighlighted = true;
      } else if (this.directionButton.isClicked(e.getX(), e.getY())) {
        this.directionButton.isHighlighted = true;
      } else {
        this.startButton.isHighlighted = false;
        this.directionButton.isHighlighted = false;
      } 
    } 
  }
}
