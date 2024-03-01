//package Debug;
//
//import static org.junit.Assert.*;
//import java.awt.geom.Point2D;
//import org.junit.*;
//import Shapes.*;
//
//
//public class PolygonTesting {
//	@Test
//	public void ContainTest_1() {
//		Point2D.Double[] P = new Point2D.Double[4];
//		P[0]=new Point2D.Double(3,3);
//		P[1]=new Point2D.Double(2,4);
//		P[2]=new Point2D.Double(1,3);
//		P[3]=new Point2D.Double(2,1);
//
//		Polygon S=new Polygon(P,new Point2D.Double()); //pivot is irrelevant in this case
//		assertEquals(true,S.IsContained(3,3));
//		assertEquals(true,S.IsContained(2,1));
//		assertEquals(true,S.IsContained(1,3));
//
//		//points contained on line segments
//		assertEquals(true,S.IsContained(2.5,2));
//		assertEquals(true,S.IsContained(2.5,3.5));
//		assertEquals(true,S.IsContained(1.5,2));
//
//		assertEquals(true,S.IsContained(2,2));
//		assertEquals(true,S.IsContained(2,3.5));
//
//		assertEquals(false,S.IsContained(0,0));
//	}
//
//	@Test
//	public void ContainTest_1_Clockwise() {
//		Point2D.Double[] P = new Point2D.Double[4];
//		P[0]=new Point2D.Double(3,3);
//		P[1]=new Point2D.Double(2,1);
//		P[2]=new Point2D.Double(1,3);
//		P[3]=new Point2D.Double(2,4);
//
//		Polygon S=new Polygon(P,new Point2D.Double()); //pivot is irrelevant in this case
//		assertEquals(true,S.IsContained(3,3));
//		assertEquals(true,S.IsContained(2,1));
//		assertEquals(true,S.IsContained(1,3));
//
//		//points contained on line segments
//		assertEquals(true,S.IsContained(2.5,2));
//		assertEquals(true,S.IsContained(2.5,3.5));
//		assertEquals(true,S.IsContained(1.5,2));
//
//		assertEquals(true,S.IsContained(2,2));
//		assertEquals(true,S.IsContained(2,3.5));
//
//		assertEquals(false,S.IsContained(0,0));
//	}
//
//	@Test
//	public void ContainTest_2(){
//		Point2D.Double[] P = new Point2D.Double[4];
//		P[0]=new Point2D.Double(2,0);
//		P[1]=new Point2D.Double(4,2);
//		P[2]=new Point2D.Double(2,4);
//		P[3]=new Point2D.Double(0,2);
//
//		Polygon S=new Polygon(P,new Point2D.Double()); //pivot is irrelevant in this case
//		assertEquals(true,S.IsContained(2.5,2.5)); //completely contained conventional
//		assertEquals(true,S.IsContained(2,2));
//		assertEquals(true,S.IsContained(1,1)); //Exists on line
//
//		assertEquals(false,S.IsContained(0.8,0.8));
//
//		//vertex intersection cases
//		assertEquals(false,S.IsContained(0,4));
//		assertEquals(false,S.IsContained(0,1));
//		assertEquals(true,S.IsContained(2,3));
//
//		assertEquals(false,S.IsContained(4,4));
//
//		//vertex test points
//		assertEquals(true,S.IsContained(2,4));
//		assertEquals(true,S.IsContained(2,0));
//	}
//
//	@Test
//	public void ContainTest_3(){
//		Point2D.Double[] P = new Point2D.Double[4];
//		P[0]=new Point2D.Double(0,0);
//		P[1]=new Point2D.Double(2,0);
//		P[2]=new Point2D.Double(2,2);
//		P[3]=new Point2D.Double(0,2);
//
//		Polygon S=new Polygon(P,new Point2D.Double()); //pivot is irrelevant in this case
//
//		assertEquals(false,S.IsContained(-1,2));
//		assertEquals(false,S.IsContained(-1,0));
//
//		//vertical loop
//		assertEquals(true,S.IsContained(1,1));
//		assertEquals(false,S.IsContained(-1,3));
//
//		assertEquals(false,S.IsContained(4,4));
//	}
//}