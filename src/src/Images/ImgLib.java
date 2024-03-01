package Images;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

public class ImgLib {
	private Toolkit tk; // Standard java toolkit

	public ImgLib(){tk = Toolkit.getDefaultToolkit();}

	public BufferedImage GrassImg(int Width, int Height, String imgIndex) {
		String F = "Grass" + imgIndex + ".png";
		Image Grass = tk.getImage(getClass().getResource(F));
		BufferedImage Return = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		while (Grass.getWidth(null) < 0 && Grass.getHeight(null) < 0) {
		} // simply await until image is loaded (Learn how to create exceptions)
		Graphics2D P = Return.createGraphics();
		AffineTransform T = new AffineTransform();
		T.scale((double) Width / ((double) Grass.getWidth(null)), (double) Height / ((double) Grass.getHeight(null)));
		while (!P.drawImage(Grass, T, null)) {
		} // Wait until the image is drawn to the Return BufferedImage
		return Return;
	}

	public BufferedImage PlainImg(int Width, int Height, String imgIndex) {
		String F = "Plain" + imgIndex + ".png";
		Image Plain = tk.getImage(getClass().getResource(F));
		BufferedImage Return = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		while (Plain.getWidth(null) < 0 && Plain.getHeight(null) < 0) {
		} // simply await until image is loaded (Learn how to create exceptions)
		Graphics2D P = Return.createGraphics();
		AffineTransform T = new AffineTransform();
		T.scale((double) Width / ((double) Plain.getWidth(null)), (double) Height / ((double) Plain.getHeight(null)));
		while (!P.drawImage(Plain, T, null)) {
		} // Wait until the image is drawn to the Return BufferedImage
		return Return;
	}

	public BufferedImage RockImg(int Width, int Height, String imgIndex) {
		String F = "Rock" + imgIndex + ".png";
		Image Rock = tk.getImage(getClass().getResource(F));
		BufferedImage Return = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		while(Rock.getWidth(null)<0 && Rock.getHeight(null)<0) {} // simply await until image is loaded (Learn how to create exceptions)
		Graphics2D P=Return.createGraphics();
		AffineTransform T=new AffineTransform();
		T.scale((double) Width/((double)Rock.getWidth(null)),(double)Height/((double)Rock.getHeight(null)));
		while(!P.drawImage(Rock,T,null)){} // Wait until the image is drawn to the Return BufferedImage
		return Return;
	}

	public BufferedImage BuchnerFlaskImg(int Width, int Height, double Angle, String imgIndex){
		String F = "Buchner" + imgIndex + ".png";
		Image Flask = tk.getImage(getClass().getResource(F));
		BufferedImage Return = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		while(Flask.getWidth(null)<0 && Flask.getHeight(null)<0){} // simply await until image is loaded (Learn how to create exceptions)
		Graphics2D P=Return.createGraphics();
		AffineTransform T=new AffineTransform();
		T.scale((double) Width/((double)Flask.getWidth(null)),(double)Height/((double)Flask.getHeight(null)));
		while(!P.drawImage(Flask,T,null)){} // Wait until the image is drawn to the Return BufferedImage
		return Return;
	}

	public BufferedImage StandingManTest(int Width, int Height, char direction, String imgIndex){
		String F;//File Name
		boolean Chiral=false;//Will mirror the image if true
		if(direction=='N'){F="StandNorth"+imgIndex+".png";}
		else if(direction=='S'){F="StandSouth"+imgIndex+".png";}
		else if(direction=='E'){F="Side"+imgIndex+".png";}
		else if(direction=='W'){F="Side"+imgIndex+".png";Chiral=true;}
		else return null; //invalid name

		Image tmp=tk.getImage(getClass().getResource(F));
		AffineTransform T=new AffineTransform();
		BufferedImage Return=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
		while(tmp.getWidth(null)<0 || tmp.getHeight(null)<0){}//Await for extraction
		T.scale(((double)Width)/((double)tmp.getWidth(null)),(double)Height/((double)tmp.getHeight(null)));

		Graphics2D P=Return.createGraphics();
		while(!P.drawImage(tmp,T,null)){}
		if(Chiral){return MirrorV(Return);}
		return Return;
	}

	public BufferedImage WalkingManTest(int Width,int Height,char direction,boolean Chiral,String FrameIndex,String imgIndex) {
		String F; // File Name
		boolean C=Chiral;

		if(direction=='N'){F="WalkNorth"+FrameIndex+"_"+imgIndex+".png";}
		else if(direction=='S'){F="WalkSouth"+FrameIndex+"_"+imgIndex+".png";}
		else if(direction=='E'){F="WalkSide"+FrameIndex+"_"+imgIndex+".png";}
		else if(direction=='W'){F="WalkSide"+FrameIndex+"_"+imgIndex+".png";C=!C;}
		else return null; // invalid name

		Image tmp=tk.getImage(getClass().getResource(F));
		AffineTransform T=new AffineTransform();
		BufferedImage Return=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
		while(tmp.getWidth(null)<0 && tmp.getHeight(null)<0){} // Await for extraction
		T.scale(((double)Width)/((double)tmp.getWidth(null)),(double)Height/((double)tmp.getHeight(null)));

		Graphics2D P = Return.createGraphics();
		while(!P.drawImage(tmp,T,null)){}
		if(C){return MirrorV(Return);}//mirror the image across vertical axis
		return Return;
	}

	public BufferedImage AdapterHead(int Width, int Height,String imgIndex){
		String F = "AdapterHead" + imgIndex + ".png";
		Image Head = tk.getImage(getClass().getResource(F));
		BufferedImage Return = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		while(Head.getWidth(null)<0 && Head.getHeight(null)<0){}//simply await until image is loaded (Learn how to create exceptions)
		Graphics2D P = Return.createGraphics();
		AffineTransform T = new AffineTransform();
		T.scale((double)Width/((double)Head.getWidth(null)),(double) Height/((double)Head.getHeight(null)));
		while(!P.drawImage(Head,T,null)){}//Wait until the image is drawn to the Return BufferedImage
		return Return;
	}

	public BufferedImage AdapterBody(int Width, int Height,String imgIndex){
		String F = "AdapterBody" + imgIndex + ".png";
		Image Head = tk.getImage(getClass().getResource(F));
		BufferedImage Return = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		while(Head.getWidth(null)<0 && Head.getHeight(null)<0){}//simply await until image is loaded (Learn how to create exceptions)
		Graphics2D P = Return.createGraphics();
		AffineTransform T = new AffineTransform();
		T.scale((double)Width/((double)Head.getWidth(null)),(double) Height/((double)Head.getHeight(null)));
		while(!P.drawImage(Head,T,null)){}//Wait until the image is drawn to the Return BufferedImage
		return Return;
	}

	public BufferedImage RightRearArm(int Width, int Height,String imgIndex){
		String F = "RightRearArm"+ imgIndex + ".png";
		Image Head = tk.getImage(getClass().getResource(F));
		BufferedImage Return = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		while(Head.getWidth(null)<0 && Head.getHeight(null)<0){}//simply await until image is loaded (Learn how to create exceptions)
		Graphics2D P = Return.createGraphics();
		AffineTransform T = new AffineTransform();
		T.scale((double)Width/((double)Head.getWidth(null)),(double) Height/((double)Head.getHeight(null)));
		while(!P.drawImage(Head,T,null)){}//Wait until the image is drawn to the Return BufferedImage
		return Return;
	}

	public BufferedImage LeftRearArm(int Width, int Height,String imgIndex){return MirrorV(RightRearArm(Width,Height,imgIndex));}

	public BufferedImage RightForeArm(int Width, int Height,String imgIndex){
		String F = "RightForeArm"+ imgIndex + ".png";
		Image Head = tk.getImage(getClass().getResource(F));
		BufferedImage Return = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		while(Head.getWidth(null)<0 && Head.getHeight(null)<0){}//simply await until image is loaded (Learn how to create exceptions)
		Graphics2D P = Return.createGraphics();
		AffineTransform T = new AffineTransform();
		T.scale((double)Width/((double)Head.getWidth(null)),(double) Height/((double)Head.getHeight(null)));
		while(!P.drawImage(Head,T,null)){}//Wait until the image is drawn to the Return BufferedImage
		return Return;
	}

	public BufferedImage LeftForeArm(int Width, int Height,String imgIndex){return MirrorV(RightForeArm(Width,Height,imgIndex));}

	public BufferedImage RightHand(int Width, int Height,String imgIndex){
		String F = "RightHand"+ imgIndex + ".png";
		Image Head = tk.getImage(getClass().getResource(F));
		BufferedImage Return = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
		while(Head.getWidth(null)<0 && Head.getHeight(null)<0){}//simply await until image is loaded (Learn how to create exceptions)
		Graphics2D P = Return.createGraphics();
		AffineTransform T = new AffineTransform();
		T.scale((double)Width/((double)Head.getWidth(null)),(double) Height/((double)Head.getHeight(null)));
		while(!P.drawImage(Head,T,null)){}//Wait until the image is drawn to the Return BufferedImage
		return Return;
	}

	public BufferedImage LeftHand(int Width, int Height,String imgIndex){return MirrorV(RightHand(Width,Height,imgIndex));}

	private BufferedImage MirrorV(BufferedImage Image){
		BufferedImage Chiral=new BufferedImage(Image.getWidth(),Image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		for(int x=0;x<Chiral.getWidth();x++){
			for(int y=0;y<Chiral.getHeight();y++){
				Chiral.setRGB(x,y,Image.getRGB(Image.getWidth()-1-x,y));
			}
		}
		return Chiral;
	}
}