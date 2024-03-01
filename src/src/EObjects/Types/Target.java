package EObjects.Types;

import java.awt.geom.Point2D;
import EObjects.Types.Adapters.*;
import Images.Animators.Animator;
import Shapes.*;

//A Map Object that has a physical appearances and statistical properties NOT images
public interface Target{
	public double GetX(); //Current X position
	public double GetY(); //Current Y position
	public double GetMaxHP();
	public double GetHP();
	public double GetMaxSp();
	public double GetSp();
	public boolean Contained(double ShapeX,double ShapeY); //inclusively contained
	public boolean Collided(Target X);
	public double GetPivotX();
	public double GetPivotY();
	public Animator GetEquipmentImage();
	public boolean IsCompatable(Item X); //Ignores any items attached.
	public boolean IsAttachable(Item X); //This considers if an Item is attached to that slot

	public void SetShape(Shape Shape);
	public void SetShapePivot(Point2D.Double P);
	public void IncrX(double dx);
	public void IncrY(double dy);
	public void Incr(double dx,double dy);
	public boolean SetAdapterMap(AdapterMap Map);
	public boolean Equip(Item X);
	//Attachable items functions
}