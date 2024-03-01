package Utilities.Algorithms;

import java.awt.geom.*;
import java.util.*;

//will need transfered to the polygon shapes
public class Convex_Hull{
	public Point2D.Double[] ConvexHull(Point2D.Double[] P){
		Point2D.Double[] p=Remove_Duplicates(P);
		if(p.length==1){return p;}
		if(p.length==2){return CH2(p);}
		if(p.length==3){return CH3(p);}

		ArrayList<Point2D.Double> R = new ArrayList<Point2D.Double>();
		ArrayList<Point2D.Double> E = new ArrayList<Point2D.Double>();
		for(int i=0;i<p.length;i++){E.add(p[i]);}

		double Xmin=E.get(0).getX(),Xmax=Xmin;
		int IXmin=0;

		for(int i=1;i<E.size();i++){
			if(E.get(i).getX()<Xmin){
				IXmin=i;
				Xmin=E.get(i).getX();
			}else if(E.get(i).getX()>Xmax){Xmax=E.get(i).getX();}
		}
		if(Xmax==Xmin){
			double Ymin=p[0].getY(),Ymax=p[0].getY();
			int IYmin=0, IYmax=0;
			for(int i=1;i<p.length;i++){
				if(p[i].getY()<Ymin){
					IYmin=i;
					Ymin=p[i].getY();
				}else if(p[i].getY()>Ymax){
					IYmax=i;
					Ymax=p[i].getY();
				}
			}
			Point2D.Double[] V=new Point2D.Double[2];
			V[0]=p[IYmin];V[1]=p[IYmax];
			return V;
		}
		R.add(E.remove(IXmin));

		//Initialize the outline
		double M=0,m=0; //temporarily M represents high slope and m represents low slope
		int IM=0,Im=0;
		for(int i=0;i<E.size();i++){
			double tmpm=(E.get(i).getY()-R.get(0).getY())/(E.get(i).getX()-R.get(0).getX());
			if(tmpm>M){
				IM=i;
				M=tmpm;
			}
			else if(tmpm<m){
				Im=i;
				m=tmpm;
			}
		}

		Point2D.Double Last=E.get(IM); //Last point on the Convex polygon
		R.add(E.remove(Im));

		//NOW m and M represent left and right magnitudes respectively
		m=Math.sqrt((R.get(0).getX()-R.get(1).getX())*(R.get(0).getX()-R.get(1).getX())+(R.get(0).getY()-R.get(1).getY())*(R.get(0).getY()-R.get(1).getY()));
		double Angle=0;
		while(E.size()>0){
			int I=R.size()-1, I1=0;//pivot point, top candidate
			double v1x=R.get(I-1).getX()-R.get(I).getX(), v1y=R.get(I-1).getY()-R.get(I).getY();
			for(int i=0;i<E.size();i++){
				double v2x=E.get(i).getX()-R.get(I).getX(), v2y=E.get(i).getY()-R.get(I).getY();
				M=Math.sqrt((R.get(I).getX()-E.get(i).getX())*(R.get(I).getX()-E.get(i).getX())+(R.get(I).getY()-E.get(i).getY())*(R.get(I).getY()-E.get(i).getY()));
				double tmpAngle=Math.acos((v1x*v2x+v1y*v2y)/(M*m));
				if(tmpAngle>Angle){
					Angle=tmpAngle;
					I1=i;
				}
			}
			if(E.get(I1)==Last){
				R.add(E.remove(I1));
				break;
			}
			R.add(E.remove(I1));
			I1=0;
			m=Math.sqrt((R.get(I).getX()-R.get(I+1).getX())*(R.get(I).getX()-R.get(I+1).getX())+(R.get(I).getY()-R.get(I+1).getY())*(R.get(I).getY()-R.get(I+1).getY()));
			Angle=0;
		}

		//Finalize the Rtrn Array
		Point2D.Double[] Rtrn=new Point2D.Double[R.size()];
		for(int a=0;a<R.size();a++){Rtrn[a]=R.get(a);}
		return Rtrn;
	}

	private Point2D.Double[] Remove_Duplicates(Point2D.Double[] P){
		ArrayList<Point2D.Double> FP=new ArrayList<Point2D.Double>(); //Filtered Points
		FP.add(P[0]);
		boolean duplicate=false;
		for(int i=1;i<P.length;i++){
			for(int j=0;j<FP.size();j++){
				if(P[i].getX()==FP.get(j).getX() && P[i].getY()==FP.get(j).getY()){
					duplicate=true;
					break;
				}
			}
			if(!duplicate){FP.add(P[i]);}
			else{duplicate=false;}
		}
		Point2D.Double[] R=new Point2D.Double[FP.size()];
		for(int i=0;i<R.length;i++){R[i]=FP.get(i);}
		return R;
	}

	private Point2D.Double[] CH2(Point2D.Double[] P){
		if(P[1].getX()<P[0].getX()){
			Point2D.Double t=P[0];
			P[0]=P[1];
			P[1]=t;
		}
		return P;
	}

	private Point2D.Double[] CH3(Point2D.Double[] P){
		int IminX;
		double minX;
		
		Point2D.Double[] R=new Point2D.Double[P.length];
		Point2D.Double A,B;
		minX=P[0].getX(); IminX=0;
		for(int i=1;i<P.length;i++){
			if(P[i].getX()<minX){
				IminX=i;
				minX=P[i].getX();
			}
		}
		R[0]=P[IminX];
		if(IminX==0){A=P[1];B=P[2];}
		else if(IminX==1){A=P[0];B=P[2];}
		else if(IminX==2){A=P[0];B=P[1];}
		else A=B=null;

		if(A.getY()<B.getY()){R[1]=A;R[2]=B;}
		else if(A.getY()>B.getY()){R[1]=B;R[2]=A;}
		else if(A.getY()==B.getY()){
			Point2D.Double[] dP=new Point2D.Double[2];
			dP[0]=A;dP[1]=B;
			dP=CH2(dP);
			if(dP[0].getY()>R[0].getY()){
				R[1]=dP[1];
				R[2]=dP[0];
			}else if(dP[0].getY()<R[0].getY()){
				R[1]=dP[0];
				R[2]=dP[1];
			}else{
				dP[0]=R[0];
				return dP;//Horizontal case
			}
		}
		return R;
	}
}