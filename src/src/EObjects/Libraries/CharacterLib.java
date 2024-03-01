package EObjects.Libraries;

import EObjects.*;
import EObjects.Types.*;
import EObjects.Types.Adapters.*;
import EObjects.Types.Adapters.Libraries.*;
import Shapes.*;
import Images.Animators.*;
import java.awt.geom.*;

public class CharacterLib{
	public EObject Spawn_Man(int Width,int Height,double XPosition, double YPosition){
		Animator A=new AnimatorLib().Man(Width,Height);

		class M extends EObject implements Target{
			Shape R;
			double Xps, Yps;
			AdapterMap E; //Equimpent
			public M(Animator Animation,int Width,int Height,double Xpos,double Ypos){
				super(Animation);
				this.SetName("Name");
				Point2D.Double[] P=new Point2D.Double[2];
				P[0]=new Point2D.Double(0,0);
				P[1]=new Point2D.Double(Width,Height);
				R=new Rectangle(Width,Height);
				Xps=Xpos;
				Yps=Ypos;
				E=new TargetAdapterMapLib().Human();
			}

			public double GetX(){return Xps;}
			public double GetY(){return Yps;}
			public double GetMaxHP(){return 0;}
			public double GetHP(){return 0;}
			public double GetMaxSp(){return 0;}
			public double GetSp(){return 0;}
			public boolean Contained(double X,double Y){return R.IsContained(X,Y);}
			public boolean Collided(Target X){return false;}
			public double GetPivotX(){return R.GetX();}
			public double GetPivotY(){return R.GetY();}
			public Animator GetEquipmentImage(){return E.D;}
			public boolean IsCompatable(Item X){return false;}
			public boolean IsAttachable(Item X){return false;}

			public void SetShape(Shape Shape){}
			public void SetShapePivot(Point2D.Double P){}
			public void IncrX(double dx){Xps+=dx;}
			public void IncrY(double dy){Yps+=dy;}
			public void Incr(double dx,double dy){Xps+=dx;Yps+=dy;}
			public boolean SetAdapterMap(AdapterMap Reject){return false;}
			public boolean Equip(Item X){return false;}
		}
		return new M(A,Width,Height,XPosition,YPosition);
	}
}