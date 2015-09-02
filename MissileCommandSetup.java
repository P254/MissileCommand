import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.awt.PointerInfo;
import java.applet.*;
import java.util.ArrayList;

public class MissileCommandSetup extends JFrame implements Runnable
{

public static final int UP = 0;
public static final int DOWN = 1;
public static final int LEFT = 2;
public static final int RIGHT = 3;

public static final int X = 0;
public static final int Y = 1;

public static final int WIDTH = 1000;
public static final int HEIGHT = 600;
public static final double FPS = 30.0;
public static final int FRAMERATE = (int)(1000*(1/FPS));

/*CIRCLE DATA*/

public static final int cX = 400;
public static final int cY = 300;
public static final int rInit = 1;


 int dR = 2;

 /***********/

 /*LINE DATA*/
ArrayList<Point> start= new ArrayList<Point>();
ArrayList<Point> end= new ArrayList<Point>();
ArrayList<Integer> line= new ArrayList<Integer>();
ArrayList<Integer> lineLX= new ArrayList<Integer>();
ArrayList<Integer> lineLY= new ArrayList<Integer>();
ArrayList<ArrayList<Integer>> L = new ArrayList<ArrayList<Integer>>();
int mousePosX=0;
int mousePosY=0;
boolean clicked=false;
int xPos=(int)(Math.random()*900);


/*************/
Point bor=null;
int r = rInit;
boolean go = false;
int score = 0;
Color color = new Color(255,255,255);

String worldString= "";
Graphics bufgfx;
Image bufimg;
BufferedImage grid;
Thread mythread;
Graphics2D gg;
int xLoc=450, yLoc=300;
int m=0;

public int dirX = -1;
public int dirY = -1;

public MissileCommandSetup()
{
super("Moving Exclamation Point ");
addKeyListener(new keyHandler());
addMouseListener(new mouseHandler());
setSize(WIDTH,HEIGHT);
setVisible(true);
Thread animator = new Thread(this);
animator.start();

}

public void getRandom()
{
	bor = new Point((int)(Math.random()*900),0);
}
public void worldSetup(Graphics g)
{
m++;
int w = this.getWidth();
int h = this.getHeight();
grid = (BufferedImage)createImage(w, h);
bufimg = createImage(w,h);
bufgfx = bufimg.getGraphics();

bufgfx.setColor(Color.GREEN);
bufgfx.fillRect(1,1,w,h);
bufgfx.setColor(Color.BLACK);


}


public void update(Graphics g)
{
	paint(g);
}
public void run()
{
while (true)
{
	try
	{
		if(true) //go)
		{
			for (int i=0;i<line.size();i++)
			{
				System.out.println(line.size());
				int t=line.get(i);
				t+=10;
				line.set(i,t);
			}

		}

		repaint();
		Thread.sleep(FRAMERATE);

	}
	catch(InterruptedException e) {}
}
}

public class keyHandler extends KeyAdapter
{
public void keyReleased(KeyEvent e){
	switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT: dirX = -1;
			break;
		case KeyEvent.VK_LEFT: dirX = -1;
			break;
		case KeyEvent.VK_UP: dirY = -1;
			break;
		case KeyEvent.VK_DOWN: dirY = -1;
			break;
	}
}

public void keyPressed(KeyEvent e)
{
	switch(e.getKeyCode()){
		case KeyEvent.VK_RIGHT: dirX = RIGHT;
			break;
		case KeyEvent.VK_LEFT: dirX = LEFT;
			break;
		case KeyEvent.VK_UP: dirY = UP;
		break;
		case KeyEvent.VK_DOWN: dirY = DOWN;
			break;
	}
}
}

	private class mouseHandler extends MouseAdapter
	{
		public void mouseReleased(MouseEvent e){
			go = false;

		}

		public void mousePressed(MouseEvent e)
		{
			start.add(new Point(500,500));
			end.add(e.getPoint());
			line.add(0);
			go = true;
		}
		public void mouseClicked(MouseEvent e) {
			if (e.getY() <450)
			{
				mousePosX=e.getX();
				mousePosY=e.getY();
			}
			//System.out.println(mousePosX + " " + mousePosY);
			//these co-ords are relative to the component
		}

	}

public void paint(Graphics g)
{
		if(m==0)
			worldSetup(bufgfx);

		bufgfx.setColor(color);
		bufgfx.fillRect(0,0,WIDTH,HEIGHT);
		bufgfx.setColor(Color.BLACK);
		bufgfx.fillRect(450,500,100,300);

		bufgfx.setColor(Color.RED);
		bufgfx.fillOval(00,570,100,100);
		bufgfx.fillOval(200,570,100,100);
		bufgfx.fillOval(700,570,100,100);
		bufgfx.fillOval(900,570,100,100);
		bufgfx.setColor(Color.BLACK);
		//System.out.println(go);


		if(start.size() > 0 )
		{
			for (int x=0;x<start.size();x++)
			{

				Point p1= start.get(x);
				Point p2= end.get(x);
				double d = Math.sqrt(Math.pow((p2.getX()-p1.getX()),2)+Math.pow((p2.getY()-p1.getY()),2));

				int x2 = (int)(p1.getX()+((p2.getX()-p1.getX())*line.get(x))/d);
				int y2 = (int)(p1.getY()+((p2.getY()-p1.getY())*line.get(x))/d);
				/*if (lineLX.size() <1)
				{
					lineLX.add(x,x2);
					lineLY.add(x,y2);
				}
				else
				{
					lineLX.set(x,x2);
					lineLY.set(x,y2);
				}
				/*if (lineLX.get(x) < -1000)
				{
					lineLX.remove(x);
					lineLY.remove(x);
					x--;
				}

				System.out.println(lineLX);

				if (x2> 5000)
				{
					start.remove(x);
					end.remove(x);
				}
				x--;*/
				bufgfx.drawLine((int)p1.getX(),(int)p1.getY(),(int)x2,(int)y2);
			}

		}


		g.drawImage(bufimg,0,0,this);

}

public static void main(String args[])
	{
		MissileCommandSetup app = new MissileCommandSetup();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}



