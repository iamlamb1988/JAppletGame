package EObjects.Libraries;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import EObjects.*;
import EObjects.Types.*;
import EObjects.Types.Adapters.AdapterMap;
import Images.Animators.Animator;
import Images.Animators.AnimatorLib;
import Shapes.Rectangle;
import Shapes.Shape;

public class ItemLib {
	public EObject Flask(int ImgWidth, int ImgHeight,double Xps, double Yps, double HP, double MHP, double SP, double MSP){
		class F extends EObject implements Item, Target{
			private Rectangle R;//Will become a circle when class is completed
			private double X,Y,HP,MHP,SP,MSP;
			public F(double Xps, double Yps, double HP, double MHP, double SP, double MSP){
				super(new AnimatorLib().BuchnerFlask(ImgWidth,ImgHeight,0));//Angle not Yet Implemented
				this.SetName("Erlenmeyer flask");
				X=Xps; Y=Yps;
				R=new Rectangle(ImgWidth,ImgHeight);
				this.HP=HP;
				this.MHP=MHP;
				this.SP=Xps;
				this.MHP=MHP;
			}

			public double GetX(){return X;}
			public double GetY(){return Y;}
			public double GetMaxHP(){return MHP;}
			public double GetHP(){return HP;}
			public double GetMaxSp(){return MSP;}
			public double GetSp(){return SP;}

			public boolean Contained(double ShapeX,double ShapeY){return R.IsContained(ShapeX,ShapeY);} //inclusively contained

			public boolean Collided(Target X){return false;}//NOT DONE
			public double GetPivotX(){return R.GetX();}
			public double GetPivotY(){return R.GetY();};

			public void SetShape(Shape Shape){}
			public void SetShapePivot(Point2D.Double P){R.SetPivot(P.getX(),P.getY());}
			public void IncrX(double dx){X+=dx;}
			public void IncrY(double dy){Y+=dy;}
			public void Incr(double dx,double dy){X+=dx;Y+=dy;}
			public boolean SetAdapterMap(AdapterMap Reject){return false;}

			@Override
			public String GetItemName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public double GetGVolume() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String GetNFilterExp() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String GetPFilterExp() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean IsCompatable(Item X) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean IsAttachable(Item X) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean Equip(Item X) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Animator GetEquipmentImage() {
				// TODO Auto-generated method stub
				return null;
			}
		}
		return new F(Xps,Yps,HP,MHP,SP,MSP);
	}
}
