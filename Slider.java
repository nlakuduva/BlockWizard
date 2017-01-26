/**
 * @(#)Slider.java
 *
 *
 * @author 
 * @version 1.00 2015/5/13
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Slider 
{
	private Rectangle slider;
	private int locX;
	private int locY;
	private final int R_WIDTH = 250;
	private final int R_HEIGHT = 15;
	private final int BUFFER = 15;
	private final Image PIC = new ImageIcon("Pictures/Slider.png").getImage();
		
    public Slider(int fWidth, int fHeight)
    {
    	locX = fWidth/2 - R_WIDTH/2;//395;
    	locY = fHeight - (R_HEIGHT * 2 + BUFFER); //527;
    }
    
    public void draw(Graphics2D gr)
	{
		slider = new Rectangle(locX, locY, R_WIDTH, R_HEIGHT);
		gr.drawImage(PIC, slider.x, slider.y, R_WIDTH, R_HEIGHT, null);
	}
	
	public void move(int num)
	{
		locX += num;
	}
	
	public int getLocX()
	{
		return locX;
	}
	
	public int getLocY()
	{
		return locY;
	}
	
	public int getWidth()
	{
		return R_WIDTH;
	} 
	
	public int getHeight()
	{
		return R_HEIGHT;
	}

}