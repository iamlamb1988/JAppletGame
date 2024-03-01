package EObjects.Types.Adapters;

import java.util.*;
import Images.Animators.*;

public class AdapterMap{
	private String Name;
	private LinkedAdapter[] Node; //array position represents ID number of node
	public Animator D; //Display of all adapters

	public AdapterMap(){
		Name="";
		Node=new LinkedAdapter[0];
	}

	public AdapterMap(String MapName){
		Name=MapName;
		Node=new LinkedAdapter[0];
	}

	public void Add(Adapter NewAdapter){
		LinkedAdapter[] NewNode= new LinkedAdapter[Node.length+1];
		for(int i=0;i<Node.length;i++){NewNode[i]=Node[i];}
		NewNode[Node.length]=Upgrade(NewAdapter);
		Node=NewNode;
	}

	public boolean Connect(int Index1, int Index2){
		if(!IsConnected(Index1,Index2)){
			Node[Index1].Connect(Node[Index2]);
			return true;
		}else return false;
	}

	public boolean Disconnect(int Index1,int Index2){
		for(int i=0;i<Node[Index1].Link.size();i++){
			if(Node[Index1].Link.get(i)==Node[Index2]){
				Node[Index1].Link.remove(i);
				for(int j=0;j<Node[Index2].Link.size();j++){
					if(Node[Index2].Link.get(j)==Node[Index1]){Node[Index2].Link.remove(j);}
				}
				return true;
			}
		}
		return false;
	}

	public Adapter Remove(int Index){return null;}

	public void SetImageLocation(int NodeIndex,int Xpos,int Ypos){
		Node[NodeIndex].X=Xpos;
		Node[NodeIndex].Y=Ypos;
	}

	private LinkedAdapter Upgrade(Adapter OldAdapter){
		LinkedAdapter NewA=new LinkedAdapter(OldAdapter.GetName());
		if(OldAdapter.IsPositive()){NewA.SetPositiveOn();}else{NewA.SetPositveOff();}
		if(OldAdapter.IsNegative()){NewA.SetNegativeOn();}else{NewA.SetNegativeOff();}
		NewA.SetFilter(OldAdapter.GetFilterExpression());
		for(int i=0;i<OldAdapter.GetFilterTermQ();i++){
			if(OldAdapter.IsMaxGVolumeSet(i)){NewA.SetMaxGVolume(i,OldAdapter.GetMaxGVolume(i));}
			if(OldAdapter.IsMinGVolumeSet(i)){NewA.SetMinGVolume(i,OldAdapter.GetMinGVolume(i));}
			if(OldAdapter.IsMaxMassSet(i)){NewA.SetMaxMass(i,OldAdapter.GetMaxMass(i));}
			if(OldAdapter.IsMinMassSet(i)){NewA.SetMinMass(i,OldAdapter.GetMinMass(i));}
		}

		return NewA;
	}

	public String GetName(){return Name;}
	public int GetNodeQ(){return Node.length;}
	public int GetNodeXLoc(int NodeIndex){return Node[NodeIndex].X;}
	public int GetNodeYLoc(int NodeIndex){return Node[NodeIndex].Y;}
	public String GetNodeName(int NodeIndex){return Node[NodeIndex].GetName();}
	public int GetLinkQ(int NodeIndex){return Node[NodeIndex].Link.size();}
	public String GetLinkName(int NodeIndex,int LinkIndex){return Node[NodeIndex].Link.get(LinkIndex).GetName();}
	public boolean IsConnected(int Index1, int Index2){
		for(int i=0;i<Node[Index1].Link.size();i++){if(Node[Index1].Link.get(i)==Node[Index2]){return true;}}
		return false;
	}

	private class LinkedAdapter extends Adapter{
		private ArrayList<LinkedAdapter> Link;
		private int X, Y;

		public LinkedAdapter(String Name){
			super(Name);
			X=Y=0;
			Link=new ArrayList<LinkedAdapter>();
		}

		public void Connect(LinkedAdapter NewLink){
			for(int i=0;i<Link.size();i++){if(Link.get(i)==NewLink){return;}}
			Link.add(NewLink);
			NewLink.Connect(this);
		}
	}
}