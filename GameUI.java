/**
 *	The GameUI class instantiates a JFrame and assigns it
 *	a width, height, location, and title. Then it contains classes
 *	that implements ActionListener such as MousePressListener that starts 
 *  the game at a left click, TimeListener that controls the movement of the ball, 
 * 	and KeyPressListener that connects the keyboard commands to the game.
 *	@author: Nandhini Lakuduva, Rafael Canadas, Adam Kosofsky
 *  Period: 3
 *  Date: 5-22-15
*/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class GameUI 
{
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        //sets up frame
        final int FRAME_WIDTH = 1000;
 		final int FRAME_HEIGHT = 600;
        final JFrame frame = new JFrame();
        
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Barnacle Blast");
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//sets up component
		final GameEngine engine = new GameEngine(FRAME_WIDTH, FRAME_HEIGHT);
		frame.add(engine);
		frame.setVisible(true);
		
//		JPanel panel = new JPanel();
//		panel.setBackground(Color.white);
//		frame.add(panel);
		
		
		class KeyPressListener implements KeyListener 
		{
			private final int POS = 15;
			private final int NEG = -15;
			private final int ZERO = 0;
			
			/** Interprets commands recieved from the keyboard to move
			 *	the slider.
	      	 *	@param event key pressed
		 	 */
			public void keyPressed(KeyEvent event)
			{
				if(event.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					if((engine.getSlider().getLocX() + engine.getSlider().getWidth()) > FRAME_WIDTH)
						engine.moveSlider(ZERO);
					else
						engine.moveSlider(POS);
				}
				
				if(event.getKeyCode() == KeyEvent.VK_LEFT)
				{
					if(engine.getSlider().getLocX() < ZERO)
						engine.moveSlider(ZERO);
					else
						engine.moveSlider(NEG);
				}
				
				if(event.getKeyCode() == KeyEvent.VK_ENTER)
					engine.mouseClick();
			}

			// Do-nothing methods
			public void keyReleased(KeyEvent event){}
			public void keyTyped(KeyEvent event){}
		}
		
		KeyListener listener = new KeyPressListener();
		engine.addKeyListener(listener);
		
	  class MousePressListener implements MouseListener
      {  
      	/** when mouse is clicked releases the ball
      	 *	@param event the race going on
	 	 */
         public void mousePressed(MouseEvent event)
         {  
         	if(engine.getLives() == 0) || engine.hasWon())
         	{
         		engine.restart();
         		engine.resetLives();
         	}
         				
            engine.mouseClick();
         }

         // Do-nothing methods
         public void mouseReleased(MouseEvent event) {}
         public void mouseClicked(MouseEvent event) {}
         public void mouseEntered(MouseEvent event) {}
         public void mouseExited(MouseEvent event) {}
      }
         
      MouseListener listenerTwo = new MousePressListener();
      engine.addMouseListener(listenerTwo);

    class TimerListener implements ActionListener
    {
    	private int changeX = 1;
		private int changeY = 1;
		
        public void actionPerformed(ActionEvent event)
        {		
        	engine.repaint();

        	if(engine.getBallClicked() == true)
        	{
        		hitEdge();
        		engine.ballMove(changeX, changeY);	
        		hitSlider();
        		hitBlock();
        	}
        }
        
        public void hitEdge()
        {
        	if(engine.getBall().getLocX() <= 0) //hit left
				changeX = -(changeX);
    		else if(engine.getBall().getLocX() + engine.getBall().getDiameter()  >= (FRAME_WIDTH - 13)) //hit right
    			changeX = -(changeX);	
    		else if(engine.getBall().getLocY() <= 0) //hit top
    			changeY = -(changeY);
    		else if(engine.getBall().getLocY() + engine.getBall().getDiameter()  >= (FRAME_HEIGHT - 33)) //hit bottom
    		{
    			//if(engine.hasWon())
					changeY = -(changeY);
				//else
				{
					engine.decreaseLife();
					engine.restart();
				}
				
    		}
    		
        }
        
        public void hitSlider()
        {
   			Ball b = engine.getBall();
   			int bX = b.getLocX();
        	int bY = b.getLocY();
        	
        	Slider s = engine.getSlider();
        	
			
			//hit top
        	if(b.getLocX() >= s.getLocX() && b.getLocX() <= (s.getLocX() + s.getWidth()) && (b.getLocY() + b.getDiameter()) >= s.getLocY())
				changeY = -(changeY);
			//hit right
//			else if(((bY >= topLeftY && bY <= bottomLeftY) && bX == topRightX)
//        				|| ((bY + b.getDiameter() >= topLeftY && bY + b.getDiameter() <= bottomLeftY) && bX == topRightX)) 
//        		changeX = -(changeX);	
        		
        }
        
        public void hitBlock()
        {
        	Block[][] blocks = engine.getBlocks();
        	Ball b = engine.getBall();
        	
        	int bX = b.getLocX();
        	int bY = b.getLocY();
        	
        	boolean hasCollided  = false;
        	
        	for(int r = 0; r < blocks.length; r++)
        	{
        		for(int c = 0; c < blocks[0].length; c++)
        		{
        			//top 
        			int topLeftX = blocks[r][c].getLocX();
        			int topRightX = topLeftX + blocks[r][c].getWidth();
        			int topLeftY = blocks[r][c].getLocY();
        			int topRightY = topLeftY;
        				
        			//bottom
        			int bottomLeftX = topLeftX;
        			int bottomRightX = topRightX;
        			int bottomLeftY = topLeftY + blocks[r][c].getHeight();
        			int bottomRightY = bottomLeftY;
        	
        			if(((bX >= topLeftX && bX <= topRightX) && bY + b.getDiameter() == topLeftY)
        				|| ((bX + b.getDiameter() >= topLeftX && bX + b.getDiameter() <= topRightX) && bY + b.getDiameter() == topLeftY))//hit top 
        			{
        				changeY = -(changeY);
        				blocks[r][c].remove();
        				engine.addPoints();
        				hasCollided = true;
        				break;
        			}
        			else if(((bX >= topLeftX && bX <= topRightX) && bY == bottomLeftY) //hit bottom left corner 
        				|| ((bX + b.getDiameter() >= topLeftX && bX + b.getDiameter() <= topRightX) && bY == bottomLeftY)) //hit bottom right corner
        			{
        				changeY = -(changeY);
        				blocks[r][c].remove();
        				engine.addPoints();
        				hasCollided = true;
        				break;
        			}
        			else if(((bY >= topLeftY && bY <= bottomLeftY) && bX + b.getDiameter() == topLeftX)
        				|| ((bY + b.getDiameter() >= topLeftY && bY + b.getDiameter() <= bottomLeftY) && bX + b.getDiameter() == topLeftX)) //hit left		
        			{
        				changeX = -(changeX);
        				blocks[r][c].remove();
        				engine.addPoints();
        				hasCollided = true;
        				break;
        			}
        			else if(((bY >= topLeftY && bY <= bottomLeftY) && bX == topRightX)
        				|| ((bY + b.getDiameter() >= topLeftY && bY + b.getDiameter() <= bottomLeftY) && bX == topRightX)) //hit right		
        			{
        				changeX = -(changeX);
        				blocks[r][c].remove();
        				engine.addPoints();
        				hasCollided = true;
        				break;
        			}
        		}
        		  
        		
        		if(hasCollided)
        			break;
        	}
        	
        	
        	if(engine.getPoints() == (blocks.length * blocks[0].length))
        	{
        		engine.gameWon();
        	}
        		
        	if(engine.getLives() == 0)
        	{
        		engine.gameLost();
        	}
        	
        }
        
    }
	 
	ActionListener listenerThree = new TimerListener();
	  
	final int DELAY = 2; // Milliseconds between timer ticks
    Timer t = new Timer(DELAY, listenerThree);
    t.start();
    
    }
}
