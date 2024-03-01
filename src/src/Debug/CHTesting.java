//package Debug;
//
//import static org.junit.Assert.*;
//import java.awt.geom.*;
//import org.junit.Test;
//
//public class CHTesting {
//	Utilities.Algorithms.Convex_Hull CH;
//	Point2D.Double[] P;
//	public CHTesting(){CH=new Utilities.Algorithms.Convex_Hull();}
//
//	@Test
//	public void CH2_Test_1(){
//		Point2D.Double[] X=new Point2D.Double[2];;
//		
//		X[0]=new Point2D.Double(4,4);
//		X[1]=new Point2D.Double(2,2);
//		P=CH.ConvexHull(X);
//
//		assertEquals(2,P[0].getX(),0);
//		assertEquals(2,P[0].getY(),0);
//
//		assertEquals(4,P[1].getX(),0);
//		assertEquals(4,P[1].getY(),0);
//
//		X[0]=new Point2D.Double(1,2);
//		X[1]=new Point2D.Double(2,0);
//		P=CH.ConvexHull(X);
//
//		assertEquals(1,P[0].getX(),0);
//		assertEquals(2,P[0].getY(),0);
//
//		assertEquals(2,P[1].getX(),0);
//		assertEquals(0,P[1].getY(),0);
//	}
//
//	@Test
//	public void CH3_Test_1(){
//		Point2D.Double[] X=new Point2D.Double[3];;
//		
//		X[0]=new Point2D.Double(1,0);
//		X[1]=new Point2D.Double(2,2);
//		X[2]=new Point2D.Double(0,1);
//		P=CH.ConvexHull(X);
//
//		assertEquals(0,P[0].getX(),0);
//		assertEquals(1,P[0].getY(),0);
//
//		assertEquals(1,P[1].getX(),0);
//		assertEquals(0,P[1].getY(),0);
//
//		assertEquals(2,P[2].getX(),0);
//		assertEquals(2,P[2].getY(),0);
//	}
//
//	@Test
//	public void CH3_Test_2(){ //2 horizontal points high
//		Point2D.Double[] X=new Point2D.Double[3];;
//		
//		X[0]=new Point2D.Double(5,3);
//		X[1]=new Point2D.Double(0,0);
//		X[2]=new Point2D.Double(2,3);
//		P=CH.ConvexHull(X);
//
//		assertEquals(0,P[0].getX(),0);
//		assertEquals(0,P[0].getY(),0);
//
//		assertEquals(5,P[1].getX(),0);
//		assertEquals(3,P[1].getY(),0);
//
//		assertEquals(2,P[2].getX(),0);
//		assertEquals(3,P[2].getY(),0);
//	}
//
//	@Test
//	public void CH3_Test_3(){ //2 horizontal points low
//		Point2D.Double[] X=new Point2D.Double[3];;
//		
//		X[0]=new Point2D.Double(7,3);
//		X[1]=new Point2D.Double(2,5);
//		X[2]=new Point2D.Double(5,3);
//		P=CH.ConvexHull(X);
//
//		assertEquals(2,P[0].getX(),0);
//		assertEquals(5,P[0].getY(),0);
//
//		assertEquals(5,P[1].getX(),0);
//		assertEquals(3,P[1].getY(),0);
//
//		assertEquals(7,P[2].getX(),0);
//		assertEquals(3,P[2].getY(),0);
//	}
//
//	@Test
//	public void CH3_Test_4(){ //all horizontal
//		Point2D.Double[] X=new Point2D.Double[3];
//
//		X[0]=new Point2D.Double(7,3);
//		X[1]=new Point2D.Double(2,3);
//		X[2]=new Point2D.Double(5,3);
//		P=CH.ConvexHull(X);
//
//		assertEquals(2,P.length);
//
//		assertEquals(2,P[0].getX(),0);
//		assertEquals(3,P[0].getY(),0);
//
//		assertEquals(7,P[1].getX(),0);
//		assertEquals(3,P[1].getY(),0);
//	}
//
//	@Test
//	public void CH_Test_1(){
//		Point2D.Double[] X=new Point2D.Double[4];
//
//		X[0]=new Point2D.Double(2,2);
//		X[1]=new Point2D.Double(0,1);
//		X[2]=new Point2D.Double(3,0.5);
//		X[3]=new Point2D.Double(1,0);
//		P=CH.ConvexHull(X);
//
//		assertEquals(4,P.length);
//
//		assertEquals(0,P[0].getX(),0);
//		assertEquals(1,P[0].getY(),0);
//
//		assertEquals(1,P[1].getX(),0);
//		assertEquals(0,P[1].getY(),0);
//
//		assertEquals(3,P[2].getX(),0);
//		assertEquals(0.5,P[2].getY(),0);
//
//		assertEquals(2,P[3].getX(),0);
//		assertEquals(2,P[3].getY(),0);
//	}
//
//	@Test
//	public void CH_Test_2(){
//		Point2D.Double[] X=new Point2D.Double[6];
//
//		X[0]=new Point2D.Double(1,0);
//		X[1]=new Point2D.Double(2,2);
//		X[2]=new Point2D.Double(2,0);
//		X[3]=new Point2D.Double(0,1);
//		X[4]=new Point2D.Double(1,2);
//		X[5]=new Point2D.Double(3,1);
//		P=CH.ConvexHull(X);
//
//		assertEquals(6,P.length);
//
//		assertEquals(0,P[0].getX(),0);
//		assertEquals(1,P[0].getY(),0);
//
//		assertEquals(1,P[1].getX(),0);
//		assertEquals(0,P[1].getY(),0);
//
//		assertEquals(2,P[2].getX(),0);
//		assertEquals(0,P[2].getY(),0);
//
//		assertEquals(3,P[3].getX(),0);
//		assertEquals(1,P[3].getY(),0);
//
//		assertEquals(2,P[4].getX(),0);
//		assertEquals(2,P[4].getY(),0);
//
//		assertEquals(1,P[5].getX(),0);
//		assertEquals(2,P[5].getY(),0);
//	}
//
//	public void CH_Test_3(){
//		Point2D.Double[] X=new Point2D.Double[10];
//
//		X[0]=new Point2D.Double(4,-4);
//		X[1]=new Point2D.Double(-3,0);
//		X[2]=new Point2D.Double(4,1);
//		X[3]=new Point2D.Double(2,3);
//		X[4]=new Point2D.Double(-2,-4);
//		X[5]=new Point2D.Double(-1,0);
//		X[6]=new Point2D.Double(-4,2);
//		X[7]=new Point2D.Double(-4,-3);
//		X[8]=new Point2D.Double(1,-1);
//		X[9]=new Point2D.Double(2,-5);
//		P=CH.ConvexHull(X);
//
//		assertEquals(7,P.length);
//
//		assertEquals(-4,P[0].getX(),0);
//		assertEquals(-3,P[0].getY(),0);
//
//		assertEquals(-2,P[1].getX(),0);
//		assertEquals(-4,P[1].getY(),0);
//
//		assertEquals(2,P[2].getX(),0);
//		assertEquals(-5,P[2].getY(),0);
//
//		assertEquals(4,P[3].getX(),0);
//		assertEquals(-4,P[3].getY(),0);
//
//		assertEquals(4,P[4].getX(),0);
//		assertEquals(1,P[4].getY(),0);
//
//		assertEquals(2,P[5].getX(),0);
//		assertEquals(3,P[5].getY(),0);
//
//		assertEquals(-4,P[6].getX(),0);
//		assertEquals(2,P[6].getY(),0);
//	}
//}