package Shapes;

import java.awt.geom.*;

public class Rectangle extends Polygon implements Shape{
	public Rectangle(Point2D.Double[] P, Point2D.Double Pivot){
		super(P,Pivot);
		S=this.Encapsulate();
	}

	public Rectangle(double Width,double Height){
		S=new Point2D.Double[4];
		S[0]=new Point2D.Double(0,0);
		S[1]=new Point2D.Double(0,Height);
		S[2]=new Point2D.Double(Width,Height);
		S[3]=new Point2D.Double(Width,0);
		P=new Point2D.Double((double)Width/((double)2),(double)Height/((double)2));
	}
	public boolean IsContained(double X,double Y){return X>=0 && Y>=0 && X<=S[2].getX() && Y<=S[2].getY();}
	public void SetShape(Point2D.Double[] P){
		double Xmin=P[0].getX(), Xmax=Xmin, Ymin=P[0].getY(), Ymax=Ymin;
		for(int i=1;i<P.length;i++){
			if(P[i].getX()<Xmin){
				Xmin=P[i].getX();
			} else if(P[i].getX()>Xmax){
				Xmax=P[i].getX();
			}
			if(P[i].getY()<Ymin){
				Ymin=P[i].getY();
			} else if(P[i].getY()>Ymax){
				Ymax=P[i].getY();
			}
		}
		S=new Point2D.Double[4];
		S[0]=new Point2D.Double(0,0);
		S[1]=new Point2D.Double(0,Ymax-Ymin);
		S[2]=new Point2D.Double(Xmax-Xmin,Ymax-Ymin);
		S[3]=new Point2D.Double(Xmax-Xmin,0);
	}
}