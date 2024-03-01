package Driver;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import Images.*;
import EObjects.*;
import EObjects.Libraries.*;
import EObjects.Types.*;
import Maps.Map;

public class MAPTest extends JApplet{
	private Map M;
	private BufferedImage BI;//Buffered Image to prevent flicker effect
	private EObject Hero;
	public void init(){
		this.setSize(600,600);
		Random R=new Random();
		BI=new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
		M=new Map(this.getWidth(),this.getHeight(),new ImgLib().PlainImg(this.getWidth(),this.getHeight(),"1"),0);
		TargetLib Lib=new TargetLib();
		CharacterLib CLib=new CharacterLib();
		ItemLib ILib=new ItemLib();

		for(int i=0;i<100;i++){
			int W=26,H=26;
			M.AddEObject(Lib.Bush(W,H,R.nextInt(this.getWidth()-W)+W/2,R.nextInt(this.getWidth()-H)+H/2,5,5,0,0));
		}
		M.AddEObject(ILib.Flask(30,30,200,200,1,1,0,0));

		Hero=CLib.Spawn_Man(50,50,25,25);
		Hero.SetName("Bruce");
		M.AddEObject(Hero);
		new painting().start();
		this.setFocusable(true);

		Controller X=new Controller();
		this.addMouseListener(X);
		this.addMouseMotionListener(X);
		this.addKeyListener(X);
	}

	public void paint(Graphics p){
		Graphics2D P=(Graphics2D)p, G=BI.createGraphics();

		while(!G.drawImage(M.GetBGRef(),0,0,null)){}
		for(int i=0;i<M.GetObjectQ();i++){
			BufferedImage I=M.GetObjectImg(i);
			while(!G.drawImage(I,(int)(M.GetObjXpos(i)-.5*I.getWidth()),(int)(M.GetObjYpos(i)-.5*I.getHeight()),null)){}
		}
		while(!P.drawImage(BI,0,0,null));
	}

	private class painting extends Thread implements Runnable{
		public painting(){setDaemon(true);}
		public void run(){
			while(true){repaint();}
		}
	}

	private class Controller implements KeyListener,MouseListener,MouseMotionListener{
		public void keyPressed(KeyEvent E){
			if(E.getKeyCode()==KeyEvent.VK_DOWN){
				Hero.SetActiveRowIndex((byte)6);
				((Target)Hero).IncrY(2);
			}
			if(E.getKeyCode()==KeyEvent.VK_UP){
				Hero.SetActiveRowIndex((byte)4);
				((Target)Hero).IncrY(-2);
			}
			if(E.getKeyCode()==KeyEvent.VK_RIGHT){
				Hero.SetActiveRowIndex((byte)7);
				((Target)Hero).IncrX(2);
			}
			if(E.getKeyCode()==KeyEvent.VK_LEFT){
				Hero.SetActiveRowIndex((byte)5);
				((Target)Hero).IncrX(-2);
			}
		}
		public void keyReleased(KeyEvent E){
			byte A=(byte)(Hero.GetCurrentRowIndex()-4);
			if(A>=0)Hero.SetActiveRowIndex(A);
		}
		public void keyTyped(KeyEvent E){}

		public void mouseDragged(MouseEvent E){System.out.println("Dragged: ("+E.getX()+","+E.getY()+")");}
		public void mouseMoved(MouseEvent E){System.out.println("Moved: ("+E.getX()+","+E.getY()+")");}
		public void mouseClicked(MouseEvent E){
			System.out.print("Clicking event: ");
			int hero=-1;
			for(int i=M.GetObjectQ()-1;i>=0;i--){
				if(M.GetObjectName(i)=="Bruce"){hero=i;break;}
			}
			for(int i=M.GetObjectQ()-1;i>=0;i--){
				if(i==hero)continue;
				if(M.Distance(hero,i)<=100 && M.IsContained(i,E.getX(),E.getY())){
					M.RemoveObject(i);
					System.out.println("Object Removed");
					return;
				}
			}
		}
		public void mouseEntered(MouseEvent E){System.out.println("Mouse Entered");}
		public void mouseExited(MouseEvent E){System.out.println("Mouse Exited");}
		public void mousePressed(MouseEvent E){System.out.println("Mouse Pressed");}
		public void mouseReleased(MouseEvent E){System.out.println("Mouse Released");}
	}
}