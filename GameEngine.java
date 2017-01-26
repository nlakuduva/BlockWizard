/**
 * @(#)GameEngine.java
 *
 *
 * @author 
 * @version 1.00 2015/5/13
 */

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.JComponent;

public class GameEngine extends JComponent
{	
	private Slider player;
	private Ball ball;
	private Block[][] blocks;
	private final int B_POS_X = 275;
	private final int B_POS_Y = 50;
	private final int BLOCK_ROWS = 4;
	private final int BLOCK_COLS = 4;
	private final int START_POINTS = 0;
	private final int START_LIVES = 3;
	private int blockPosX;
	private int blockPosY;
	private int points;
	private int lives;
	private int frameWidth;
	private int frameHeight;
	private boolean won;
	private boolean lost;

    public GameEngine(int fWidth, int fHeight) 
    {
    	frameWidth = fWidth;
    	frameHeight = fHeight;
    	lives = START_LIVES;
    	
    	restart();
    	
    	setFocusable(true);
    }
    
    public void paintComponent(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D) g;
    	
    	int width = getWidth();
    	int height = getHeight();
    	
    	player.draw(g2);
    	ball.draw(g2);
		for(int r = 0; r < blocks.length; r++)
		{
			for(int c = 0; c < blocks[0].length; c++)
			{
				blocks[r][c].draw(g2);
			}	
    	}
    	
    	//Text
    	g2.setColor(Color.darkGray);
    	g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		g2.drawString("Points: " + points + " Lives: " + lives, 3, 13);
		
		if(won)
		{
			g2.setColor(Color.magenta);
			g2.setFont(new Font("Goudy Stout", Font.BOLD, 72));
			g2.drawString("You win!!", 150, 300);
			g2.setFont(new Font("Goudy Stout", Font.BOLD, 24));
			//g2.drawString("Click to restart!", 450, 400);
		}
		
		if(lost)
		{
			g2.setColor(Color.red);
			g2.setFont(new Font("Goudy Stout", Font.BOLD, 72));
			g2.drawString("You lost :(", 100, 300);
		}
			
			
    }
    
    public void moveSlider(int num)
    {
    	player.move(num);
    	ball.moveWithSlider(num);
    }
    
    public void mouseClick()
    {
    	ball.setClick();
    }
    
    public boolean getBallClicked()
    {
    	return ball.getClicked();
    }
    
    public void ballMove(int x, int y)
    {
 		ball.move(x, y);
    }
    
    public void restart()
    {
		player = new Slider(frameWidth, frameHeight);
		ball = new Ball(player.getLocX(), player.getLocY(), player.getWidth());
		blocks = new Block[1][1];
		points = START_POINTS;
		//won = false;
		//lost = false;
		
		blockPosX = B_POS_X;
		blockPosY = B_POS_Y;

		for(int r = 0; r < blocks.length; r++)
		{
			for(int c = 0; c < blocks[0].length; c++)
			{
				blocks[r][c] = new Block(blockPosX, blockPosY);
				blockPosX += 115;
			}
			blockPosX = B_POS_X;
			blockPosY += 80;
		}

    }
    
    public Slider getSlider()
    {
    	return player;
    }
    
    public Ball getBall()
    {
    	return ball;
    }
    
    public Block[][] getBlocks()
    {
    	return blocks;
    }
    
    public void decreaseLife()
    {
    	lives--;
    }
    
    public void addPoints()
    {
    	points++;
    }
    
    public int getPoints()
    {
    	return points;
    }
    
    public int getLives()
    {
    	return lives;
    }
    
    public void resetLives()
    {
    	lives = START_LIVES;
    }
    
    public void gameWon()
    {
    	won = true;
    }
    public boolean hasWon()
    {
    	return won;
    }
    public void gameLost()
    {
    	lost = true;
    }
    
    
}