package Shapes;

import java.awt.geom.*;

public interface Shape{
	//Shapes will be encapsulated by the smallest possible rectangle such that the top left corner is 0,0
	public double GetX();
	public double GetY();
	public boolean IsContained(double shapeX, double shapeY);
	public boolean IsCollided(Shape Shape);

	public Point2D.Double[] Encapsulate();
	public void SetShape(double[] X, double[] Y);
	public void SetShape(Point2D.Double[] P);
	public void SetPivot(double X,double Y);
}