/**
 * @(#)Block.java
 *
 *
 * @author 
 * @version 1.00 2015/5/13
 */


import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Block 
{

    private Rectangle block;
	private int locX;
	private int locY;
	private int rWidth = 100;
	private int rHeight = 60;
	private Image pic;//private final Image PIC = new ImageIcon("Pictures/Coin.jpg").getImage();
    
    public Block(int x, int y) 
    {
    	locX = x;
    	locY = y;
    	choosePic();
    }
    
    public void choosePic()
    {
    	int ran = (int) (Math.random() * 3 + 1);
    	if(ran == 1)
    		pic = new ImageIcon("Pictures/Block.png").getImage();
    	else if (ran == 2)
    		pic = new ImageIcon("Pictures/Block_Explosive.png").getImage();
    	else
    		pic = new ImageIcon("Pictures/Block_Mystic.png").getImage();
    }
    public void draw(Graphics2D gr)
	{
		block = new Rectangle(locX, locY, rWidth, rHeight);
		gr.drawImage(pic, block.x, block.y, rWidth, rHeight, null);
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
    	return rWidth;
    }
    
    public int getHeight()
    {
    	return rHeight;
    }
    
    public void remove()
    {
    	rWidth = 0;
    	rHeight = 0;
    }
       

    
    
}