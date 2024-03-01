//package Debug;
//
//import EObjects.Types.Adapters.*;
//import static org.junit.Assert.*;
//import org.junit.Test;
//
//public class AdapterTesting {
//
//	@Test
//	public void Constructer_1(){
//		Adapter X=new Adapter();
//		assertEquals("",X.GetName());
//	}
//
//	@Test
//	public void Constructer_2(){
//		Adapter X=new Adapter("IAMLAMB");
//		assertEquals("IAMLAMB",X.GetName());
//	}
//
//	@Test
//	public void SetFilter_1(){
//		Adapter X=new Adapter("One term");
//		assertEquals("One term",X.GetName());
//
//		assertEquals(1,X.GetFilterTermQ());
//		assertEquals(false,X.IsGasOn());
//		assertEquals(false,X.IsLiquidOn());
//		assertEquals(false,X.IsPowderOn());
//		assertEquals(false,X.IsGraspOn());
//		assertEquals(false,X.IsPouchOn());
//
//		X.SetFilter("g");
//		assertEquals(1,X.GetFilterTermQ());
//		assertEquals(true,X.IsGasOn());
//		assertEquals(false,X.IsLiquidOn());
//		assertEquals(false,X.IsPowderOn());
//		assertEquals(false,X.IsGraspOn());
//		assertEquals(false,X.IsPouchOn());
//	}
//
//	@Test
//	public void SetFilter_2(){
//		Adapter X=new Adapter("one multi term");
//		assertEquals("one multi term",X.GetName());
//
//		assertEquals(1,X.GetFilterTermQ());
//		assertEquals(false,X.IsGasOn());
//		assertEquals(false,X.IsLiquidOn());
//		assertEquals(false,X.IsPowderOn());
//		assertEquals(false,X.IsGraspOn());
//		assertEquals(false,X.IsPouchOn());
//
//		X.SetFilter("glP");
//		assertEquals(1,X.GetFilterTermQ());
//		assertEquals(true,X.IsGasOn());
//		assertEquals(true,X.IsLiquidOn());
//		assertEquals(false,X.IsPowderOn());
//		assertEquals(false,X.IsGraspOn());
//		assertEquals(true,X.IsPouchOn());
//	}
//
//	@Test
//	public void SetFilter_3(){
//		Adapter X=new Adapter("2 term");
//		assertEquals("2 term",X.GetName());
//
//		assertEquals(1,X.GetFilterTermQ());
//		assertEquals(false,X.IsGasOn());
//		assertEquals(false,X.IsLiquidOn());
//		assertEquals(false,X.IsPowderOn());
//		assertEquals(false,X.IsGraspOn());
//		assertEquals(false,X.IsPouchOn());
//
//		X.SetFilter("Pp+G");
//		assertEquals(2,X.GetFilterTermQ());
//		assertEquals(false,X.IsGasOn());
//		assertEquals(false,X.IsLiquidOn());
//		assertEquals(true,X.IsPowderOn());
//		assertEquals(false,X.IsGraspOn());
//		assertEquals(true,X.IsPouchOn());
//
//		assertEquals(false,X.IsGasOn(1));
//		assertEquals(false,X.IsLiquidOn(1));
//		assertEquals(false,X.IsPowderOn(1));
//		assertEquals(true,X.IsGraspOn(1));
//		assertEquals(false,X.IsPouchOn(1));
//	}
//
//	@Test
//	public void CompareAdapter_1_1(){
//		Adapter X=new Adapter("X");
//		assertEquals("X",X.GetName());
//
//		assertEquals(1,X.GetFilterTermQ());
//		assertEquals(false,X.IsPositive());
//		assertEquals(false,X.IsNegative());
//		assertEquals(false,X.IsGasOn());
//		assertEquals(false,X.IsLiquidOn());
//		assertEquals(false,X.IsPowderOn());
//		assertEquals(false,X.IsGraspOn());
//		assertEquals(false,X.IsPouchOn());
//
//		X.SetFilter("g+Gp");
//
//		assertEquals(2,X.GetFilterTermQ());
//		assertEquals(true,X.IsGasOn());
//		assertEquals(false,X.IsLiquidOn());
//		assertEquals(false,X.IsPowderOn());
//		assertEquals(false,X.IsGraspOn());
//		assertEquals(false,X.IsPouchOn());
//
//		assertEquals(true,X.IsGasOn(0));
//		assertEquals(false,X.IsLiquidOn(0));
//		assertEquals(false,X.IsPowderOn(0));
//		assertEquals(false,X.IsGraspOn(0));
//		assertEquals(false,X.IsPouchOn(0));
//
//		assertEquals(false,X.IsGasOn(1));
//		assertEquals(false,X.IsLiquidOn(1));
//		assertEquals(true,X.IsPowderOn(1));
//		assertEquals(true,X.IsGraspOn(1));
//		assertEquals(false,X.IsPouchOn(1));
//
//		Adapter Y=new Adapter("Y");
//		assertEquals("Y",Y.GetName());
//
//		assertEquals(true,Y.SetFilter("pG+gG+lP"));
//		assertEquals(3,Y.GetFilterTermQ());
//
//		assertEquals(true,Y.IsGasOn(1));
//		assertEquals(false,Y.IsLiquidOn(1));
//		assertEquals(false,Y.IsPowderOn(1));
//		assertEquals(true,Y.IsGraspOn(1));
//		assertEquals(false,Y.IsPouchOn(1));
//
//		assertEquals(false,Y.IsGasOn(2));
//		assertEquals(true,Y.IsLiquidOn(2));
//		assertEquals(false,Y.IsPowderOn(2));
//		assertEquals(false,Y.IsGraspOn(2));
//		assertEquals(true,Y.IsPouchOn(2));
//
//		assertEquals(false,X.IsCompatable(Y));
//		assertEquals(false,Y.IsCompatable(X));
//
//		X.SetNegativeOn();
//		Y.SetPositiveOn();
//
//		assertEquals(true,X.IsCompatable(Y));
//		assertEquals(true,Y.IsCompatable(X));
//	}
//
//	@Test
//	public void Get_Filter_1(){
//		Adapter Y=new Adapter("Y");
//		assertEquals("Y",Y.GetName());
//
//		assertEquals(true,Y.SetFilter("Gp+lpg+lP"));
//		assertEquals(3,Y.GetFilterTermQ());
//		assertEquals("pG+glp+lP",Y.GetFilterExpression());
//	}
//}