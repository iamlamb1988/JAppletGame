package Shapes;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Polygon implements Shape{
	protected Point2D.Double P;//Pivot Point
	protected Point2D.Double[] S;

	public Polygon(){}
	public Polygon(Point2D.Double[] P, Point2D.Double Pivot){
		double Xm=P[0].getX(),Ym=P[0].getY();
		for(int i=1;i<P.length;i++){
			if(P[i].getX()<Xm){Xm=P[i].getX();}
			if(P[i].getY()<Ym){Ym=P[i].getY();}
		}
		Point2D.Double[] S=new Point2D.Double[P.length];
		for(int i=0;i<S.length;i++){
			S[i]=new Point2D.Double(P[i].getX()-Xm,P[i].getY()-Ym);
		}
		this.S=P;
		this.P=new Point2D.Double(Pivot.getX()-Xm,Pivot.getY()-Ym);
	}

	public Point2D.Double[] Encapsulate(){
		double Xmin=S[0].getX(), Xmax=Xmin, Ymin=S[0].getY(), Ymax=Ymin;
		for(int i=1;i<S.length;i++){
			if(S[i].getX()<Xmin){
				Xmin=S[i].getX();
			} else if(S[i].getX()>Xmax){
				Xmax=S[i].getX();
			}
			if(S[i].getY()<Ymin){
				Ymin=S[i].getY();
			} else if(S[i].getY()>Ymax){
				Ymax=S[i].getY();
			}
		}
		Point2D.Double[] P=new Point2D.Double[4];
		P[0]=new Point2D.Double(0,0);
		P[1]=new Point2D.Double(0,Ymax-Ymin);
		P[2]=new Point2D.Double(Xmax-Xmin,Ymax-Ymin);
		P[3]=new Point2D.Double(Xmax-Xmin,0);
		return P;
	}
	public double GetX(){return P.getX();}
	public double GetY(){return P.getY();}
	public boolean IsContained(double X,double Y){
		//if a point touches a vertex than point is contained
		for(int i=0;i<S.length;i++){if(X==S[i].getX() && Y==S[i].getY()){return true;}}

		//check line segments
		double min=0, min2=0, b; //slope, minimum slope, 2nd minimum slope, and y-intercept of origin
		double[] m=new double[S.length];
		if(S[0].getX()==S[S.length-1].getX() && X==S[0].getX() && (Y<S[0].getY() && Y>S[S.length-1].getY() || Y>S[0].getY() && Y<S[S.length-1].getY())){return true;}
		else if(S[0].getX()!=S[S.length-1].getX()){
			m[S.length-1]=min=min2=(S[0].getY()-S[S.length-1].getY())/(S[0].getX()-S[S.length-1].getX());
			if(min2<min){
				double tmp=min;
				min=min2;
				min2=tmp;
			}
			b=S[0].getY()-m[S.length-1]*S[0].getX();
			if((X>S[0].getX() && X<S[S.length-1].getX() || X<S[0].getX() && X>S[S.length-1].getX()) && Y==m[S.length-1]*X+b){return true;}
		}
		for(int i=0;i<S.length-1;i++){
			if(S[i].getX()==S[i+1].getX() && X==S[i].getX() && (Y<S[i].getY() && Y>S[i+1].getY() || Y>S[i].getY() && Y<S[i+1].getY())){return true;}
			else if(S[i].getX()!=S[i+1].getX()){
				m[i]=(S[i].getY()-S[i+1].getY())/(S[i].getX()-S[i+1].getX());
				if(m[i]<min){
					min2=min;
					min=m[i];}
				else if(m[i]<min2){min2=m[i];}
				b=S[i].getY()-m[i]*S[i].getX();
				if((X>S[i].getX() && X<S[i+1].getX() || X<S[i].getX() && X>S[i+1].getX()) && Y==m[i]*X+b){return true;}
			}
		}
		if(S.length<=2){return false;}

		//check by right array from test point exclude all vertex touch points
		int incr=0;
		double M;
		if(min!=min2){M=(min+min2)/(double)2;}
		else if(min==0){M=1;}
		else{M=min/(double)2;}
		b=Y-M*X;
		double Xint, Yint;
		if(S[0].getX()==S[S.length-1].getX()){
			//special vertical case
			Yint=M*S[0].getX()+b;
			if(Yint<S[0].getY() && Yint>S[S.length-1].getY() || Yint>S[0].getY() && Yint<S[S.length-1].getY()){incr++;}
			else if(Y==S[S.length-1].getY()){
				double Ax,Ay,Bx,By,Cx,Cy,A,B,C,T;
				Ax=S[S.length-2].getX()-S[S.length-1].getX();
				Ay=S[S.length-2].getY()-S[S.length-1].getY();
				Bx=S[0].getX()-S[S.length-1].getX();
				By=S[0].getY()-S[S.length-1].getY();
				Cx=X-S[S.length-1].getX();
				Cy=Y-S[S.length-1].getY();
				A=Math.sqrt(Math.pow(Ax,2)+Math.pow(Ay,2));
				B=Math.sqrt(Math.pow(Bx,2)+Math.pow(By,2));
				C=Math.sqrt(Math.pow(Cx,2)+Math.pow(Cy,2));
				T=Math.acos((Ax*Bx+Ay*By)/(A*B));
				if(X<S[S.length-1].getX() && Math.acos((Ax*Cx+Ay*Cy)/(A*C))<T && Math.acos((Bx*Cx+By*Cy)/(B*C))<T){incr++;}
			}
		}else{
			Xint=(S[0].getY()-m[S.length-1]*S[0].getX()-b)/(M-m[S.length-1]);
			if(X<Xint && (Xint<S[0].getX() && Xint>S[S.length-1].getX() || Xint>S[0].getX() && Xint<S[S.length-1].getX())){incr++;}
			else if(S[S.length-1].getY()==M*S[S.length-1].getX()+b){
				double Ax,Ay,Bx,By,Cx,Cy,A,B,C,T;
				Ax=S[S.length-2].getX()-S[S.length-1].getX();
				Ay=S[S.length-2].getY()-S[S.length-1].getY();
				Bx=S[0].getX()-S[S.length-1].getX();
				By=S[0].getY()-S[S.length-1].getY();
				Cx=X-S[S.length-1].getX();
				Cy=Y-S[S.length-1].getY();
				A=Math.sqrt(Math.pow(Ax,2)+Math.pow(Ay,2));
				B=Math.sqrt(Math.pow(Bx,2)+Math.pow(By,2));
				C=Math.sqrt(Math.pow(Cx,2)+Math.pow(Cy,2));
				T=Math.acos((Ax*Bx+Ay*By)/(A*B));
				if(X<S[S.length-1].getX() && Math.acos((Ax*Cx+Ay*Cy)/(A*C))<T && Math.acos((Bx*Cx+By*Cy)/(B*C))<T){incr++;}
			}
		}
		for(int i=0;i<S.length-1;i++){
			if(S[i].getX()==S[i+1].getX()){
				//special vertical case
				Yint=M*S[i].getX()+b;
				if(Yint<=S[i].getY() && Yint>S[i+1].getY() || Yint>=S[i].getY() && Yint<S[i+1].getY()){incr++;}
				else if(S[i].getY()==M*S[i].getX()+b){
					System.out.println("Vertical case index: "+i);
					double Ax,Ay,Bx,By,Cx,Cy,A,B,C,T;
					int Ia=i+1, Ib=i-1;
					if(Ib<0){Ib=S.length-1;}
					Ax=S[Ia].getX()-S[i].getX();
					Ay=S[Ia].getY()-S[i].getY();
					Bx=S[Ib].getX()-S[i].getX();
					By=S[Ib].getY()-S[i].getY();
					Cx=X-S[i].getX();
					Cy=Y-S[i].getY();
					A=Math.sqrt(Math.pow(Ax,2)+Math.pow(Ay,2));
					B=Math.sqrt(Math.pow(Bx,2)+Math.pow(By,2));
					C=Math.sqrt(Math.pow(Cx,2)+Math.pow(Cy,2));
					T=Math.acos((Ax*Bx+Ay*By)/(A*B));
					if(X<S[i].getX() && Math.acos((Ax*Cx+Ay*Cy)/(A*C))<T && Math.acos((Bx*Cx+By*Cy)/(B*C))<T){incr++;}
				}
			}else{
				Xint=(S[i].getY()-m[i]*S[i].getX()-b)/(M-m[i]);
				if(X<Xint && (Xint<S[i].getX() && Xint>S[i+1].getX() || Xint>S[i].getX() && Xint<S[i+1].getX())){incr++;}
				else if(S[i].getY()==M*S[i].getX()+b){
					double Ax,Ay,Bx,By,Cx,Cy,A,B,C,T;
					int Ia=i+1, Ib=i-1;
					if(Ib<0){Ib=S.length-1;}
					Ax=S[Ia].getX()-S[i].getX();
					Ay=S[Ia].getY()-S[i].getY();
					Bx=S[Ib].getX()-S[i].getX();
					By=S[Ib].getY()-S[i].getY();
					Cx=X-S[i].getX();
					Cy=Y-S[i].getY();
					A=Math.sqrt(Math.pow(Ax,2)+Math.pow(Ay,2));
					B=Math.sqrt(Math.pow(Bx,2)+Math.pow(By,2));
					C=Math.sqrt(Math.pow(Cx,2)+Math.pow(Cy,2));
					T=Math.acos((Ax*Bx+Ay*By)/(A*B));
					if(X<S[i].getX() && Math.acos((Ax*Cx+Ay*Cy)/(A*C))<T && Math.acos((Bx*Cx+By*Cy)/(B*C))<T){
						incr++;}
				}
			}
		}
		return incr%2==1;
	}
	public boolean IsCollided(Shape Shape){return false;}
	public void SetShape(double[] X,double[] Y){}
	public void SetShape(Double[] P){}
	public void SetPivot(double X,double Y){P.setLocation(X,Y);}
}