package EObjects.Libraries;

import java.awt.geom.Point2D;

import EObjects.*;
import EObjects.Types.*;
import EObjects.Types.Adapters.AdapterMap;
import Shapes.*;
import Images.Animators.*;

public class TargetLib {
	public EObject Bush(int ImgWidth, int ImgHeight,
		double Xps, double Yps, double HP, double MHP, double SP, double MSP){
		//The return class
		class B extends EObject implements Source, Target{
			private Rectangle R;
			private double X,Y,HP,MHP,SP,MSP;

			public B(double Xps, double Yps, double HP, double MHP, double SP, double MSP){
				super(new AnimatorLib().Bush(ImgWidth,ImgHeight));
				this.SetName("Bush");
				X=Xps; Y=Yps;
				R=new Rectangle(ImgWidth,ImgHeight);
				this.HP=Xps;
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
			public boolean Collided(Target X){return false;}
			public double GetPivotX(){return R.GetX();}
			public double GetPivotY(){return R.GetY();};

			public void SetShape(Shape Shape){}
			public void SetShapePivot(Point2D.Double P){R.SetPivot(P.getX(),P.getY());}
			public void IncrX(double dx){X+=dx;}
			public void IncrY(double dy){Y+=dy;}
			public void Incr(double dx,double dy){X+=dx;Y+=dy;}
			public boolean SetAdapterMap(AdapterMap Reject){return false;}

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
		return new B(Xps,Yps,HP,MHP,SP,MSP);
	}

	public EObject Rock(int ImgWidth, int ImgHeight,
			double Xps, double Yps, double HP, double MHP, double SP, double MSP){
			//The return class
			class R extends EObject implements Source, Target{
				private Shape S;
				private double Xps,Yps,HP,MHP,SP,MSP;

				public R(double Xps,double Yps,double HP,double MHP,double SP,double MSP){
					super(new AnimatorLib().Rock(ImgWidth,ImgHeight));
					this.Xps=Xps;
					this.Yps=Yps;
					this.HP=Xps;
					this.MHP=MHP;
					this.SP=Xps;
					this.MHP=MHP;
				}

				public double GetX(){return Xps;}
				public double GetY(){return Yps;}
				public double GetMaxHP(){return MHP;}
				public double GetHP(){return HP;}
				public double GetMaxSp(){return MSP;}
				public double GetSp(){return SP;}
				public boolean Contained(double X,double Y){return S.IsContained(X,Y);}
				public boolean Collided(Target X){return false;}
				public double GetPivotX(){return S.GetX();}
				public double GetPivotY(){return S.GetY();}

				public void SetShape(Shape Shape){}
				public void SetShapePivot(Point2D.Double P){}
				public void IncrX(double dx){Xps+=dx;}
				public void IncrY(double dy){Yps+=dy;}
				public void Incr(double dx,double dy){Xps+=dx;Yps+=dy;}
				public boolean SetAdapterMap(AdapterMap Reject){return false;}

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
			return new R(Xps,Yps,HP,MHP,SP,MSP);
	}
}