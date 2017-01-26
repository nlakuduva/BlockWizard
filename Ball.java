/*
 *	The Ball class assigns the ball coordinates of the top left corner 
 *	and positions it in the middle of the frame and then draws all the parts within
 *	a bounded rectangle. It also has methods to move the ball according to whether 
 *	its clicked or not.
 *	@author: Nandhini Lakuduva, Rafael Canadas, Adam Kosofsky
 *  Period: 3
 *  Date: 5-22-15
*/

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Ball
{
	private Rectangle ball;
	private int locX;
	private int locY;
	private boolean clicked = false;
	private final int R_WIDTH = 35;
	private final int R_HEIGHT = 35;
	private final Image PIC = new ImageIcon("Pictures/Ball.png").getImage();
		
    public Ball(int sliderX , int sliderY, int sWidth)
    {
    	locX = sliderX + sWidth/2 - R_WIDTH/2; //480;
    	locY = sliderY - R_HEIGHT; //492;
    }
    
    public void draw(Graphics2D gr)
	{
		ball = new Rectangle(locX, locY, R_WIDTH, R_HEIGHT);
		gr.drawImage(PIC, ball.x, ball.y, R_WIDTH, R_HEIGHT, null);
	}
	
	public void moveWithSlider(int num)
	{
		if (!clicked)
			locX += num;
	}
	
	public void setClick()
	{
		clicked = true;
	}
	
	public boolean getClicked()
	{
		return clicked;
	}
	
	public void move(int x, int y)
	{
		locX -= x;
		locY -= y;
	}
	
	public int getLocX()
	{
		return locX;
	}
	
	public int getLocY()
	{
		return locY;
	}
	
	public int getDiameter()
	{
		return R_WIDTH;
	}
		
}