//package Debug;
//
//import static org.junit.Assert.*;
//import org.junit.Test;
//import EObjects.Types.Adapters.*;
//import EObjects.Types.Adapters.Libraries.*;
//
//public class AdapterMapTesting {
//
//	@Test
//	public void Add_1(){
//		AdapterMap M=new AdapterMap();
//		Adapter X=new Adapter("X");
//		Adapter Y=new Adapter("Y");
//
//		M.Add(X);
//		M.Add(Y);
//
//		assertEquals("X",M.GetNodeName(0));
//		assertEquals("Y",M.GetNodeName(1));
//
//		assertEquals(2,M.GetNodeQ());
//		assertEquals(0,M.GetLinkQ(0));
//		assertEquals(0,M.GetLinkQ(1));
//	}
//
//	public void Connect_1(){
//		AdapterMap M=new AdapterMap();
//		M.Add(new Adapter("X"));
//		M.Add(new Adapter("Y"));
//
//		assertEquals(false,M.IsConnected(0,1));
//		M.Connect(0,1);
//		assertEquals(true,M.IsConnected(0,1));
//
//		M.Disconnect(0,1);
//		assertEquals(false,M.IsConnected(0,1));
//	}
//
//	@Test
//	public void Body_1(){//MAY CHANGE AS UPDATES ARE MADE
//		AdapterMap B=new TargetAdapterMapLib().Human();
//		assertEquals("Equipment",B.GetName());
//		assertEquals(14,B.GetNodeQ());
//
//		assertEquals("Torso",B.GetNodeName(1));
//		assertEquals(5,B.GetLinkQ(1));
//		assertEquals("Head",B.GetLinkName(1,0));
//		assertEquals("Left Rear Arm",B.GetLinkName(1,1));
//		assertEquals("Right Rear Arm",B.GetLinkName(1,2));
//		assertEquals("Left Upper Leg",B.GetLinkName(1,3));
//		assertEquals("Right Upper Leg",B.GetLinkName(1,4));
//
//		assertEquals("Head",B.GetNodeName(0));
//		assertEquals(1,B.GetLinkQ(0));
//		assertEquals("Torso",B.GetLinkName(0,0));
//	}
//}