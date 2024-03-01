package Maps;

import EObjects.*;
import EObjects.Types.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;

public class Map extends JPanel{
	private BufferedImage DBG; //Default background of img (should never be seen theoretically)
	private ArrayList<EObject> I = new ArrayList<EObject>();//WILL CAUSE PROBLEMS IN THE FUTURE OBJECTS WITHOUT COORDINATES

	private int W; //Width of image
	private int H; //Height of image

	public static int SCALE=0;
	public static int ARRAY=1;

	private Graphics2D P;

	private void ConstHelper(int Width, int Height){
		W=Width; H=Height;
		DBG = new BufferedImage(W,H,BufferedImage.TYPE_INT_ARGB);
		P=DBG.createGraphics();
	}

	public Map(int Width, int Height){
		ConstHelper(Width,Height);
		P.setColor(new Color(0,0,0));
		P.fillRect(0,0,W,H);
	}

	public Map(int Width, int Height, BufferedImage Img, int Format){	
		ConstHelper(Width,Height);
		if(Format==0){
			AffineTransform T = new AffineTransform();
			T.scale(((double)Width)/((double)Img.getWidth()),((double)Height)/((double)Img.getHeight()));
			while(!P.drawImage(Img,T,null)){}
		}
		else if(Format==1){
			int WC=Img.getWidth()/W+1; //truncate to an integer value
			int HC=Img.getHeight()/H+1; //truncate to an integer value
			for(int i=0;i<WC;i++){
				for(int j=0;j<HC;j++){
					while(!P.drawImage(DBG,i*Img.getWidth(),j*Img.getHeight(),null)){}
				}
			}
		}
	}

	public void AddEObject(EObject Object){I.add(Object);}
	public EObject RemoveObject(int ObjectIndex){return I.remove(ObjectIndex);}
	public int GetWidth(){return W;}
	public int GetHeight(){return H;}

	public int GetObjectQ(){return I.size();}
	public String GetObjectName(int i){return I.get(i).GetName();}
	public BufferedImage GetBGCopy(){
		BufferedImage Copy = new BufferedImage(W,H,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=Copy.createGraphics();
		while(!g.drawImage(DBG,0,0,null)){}
		return Copy;
	}
	public BufferedImage GetBGRef(){return DBG;}

	public BufferedImage GetObjectImg(int ObjectIndex){return I.get(ObjectIndex).GetRefImg(System.currentTimeMillis());}
	public double GetObjXpos(int ObjectIndex){
		if(I.get(ObjectIndex) instanceof Target){
			Target x=(Target)I.get(ObjectIndex);
			return x.GetX();
		}else return -1; //Error
	}
	public double GetObjYpos(int ObjectIndex){
		if(I.get(ObjectIndex) instanceof Target){
			Target y=(Target)I.get(ObjectIndex);
			return y.GetY();
		}else return -1; //Error
	}
	public double GetObjXpiv(int ObjectIndex){ //shape pivot
		if(I.get(ObjectIndex) instanceof Target){
			Target x=(Target)I.get(ObjectIndex);
			return x.GetPivotX();
		} else return -1;
	}
	public double GetObjYpiv(int ObjectIndex){ //shape pivot
		if(I.get(ObjectIndex) instanceof Target){
			Target y=(Target)I.get(ObjectIndex);
			return y.GetPivotY();
		}else return -1; //Error
	}

	public double Distance(int ObjectIndex_1,int ObjectIndex_2){
		Target A, B;
		if(I.get(ObjectIndex_1) instanceof Target && I.get(ObjectIndex_2) instanceof Target){
			A=(Target)I.get(ObjectIndex_1); B=(Target)I.get(ObjectIndex_2);
		} else return -1;
		double dx=A.GetX()-B.GetX(), dy=A.GetY()-B.GetY();
		return Math.sqrt(dx*dx+dy*dy);
	}
	public boolean IsContained(int ObjectIndex, double X,double Y){
		if(I.get(ObjectIndex) instanceof Target){
			Target T=(Target)I.get(ObjectIndex);
			return T.Contained(X-T.GetX()+T.GetPivotX(),Y-T.GetY()+T.GetPivotY());
		}else return false;
	}
}