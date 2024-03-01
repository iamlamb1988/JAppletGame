package EObjects.Types.Adapters;

import EObjects.Types.*;
import Images.Animators.*;

public class Adapter{
	private String Name;
	private boolean N,P; //Negative Adapter, Positive Adapter
	private String[] Element; //Fundamental Adapter types
	private Filter F; //Filter of positive adapters
	private Item[] List; //List of attached items
	public Animator Img;

	private void Construct(){
		N=P=false;
		Element=new String[5];
		Element[0]="Gas";    //g
		Element[1]="Liquid"; //l
		Element[2]="Powder"; //p
		Element[3]="Grasp";  //G
		Element[4]="Pouch";  //P
		F=new Filter();
	}

	public Adapter(){
		Name="";
		Construct();
	}

	public Adapter(String Name){
		this.Name=Name;
		Construct();
	}

	public void SetNegativeOn(){N=true;}
	public void SetNegativeOff(){N=false;}
	public void SetPositiveOn(){P=true;}
	public void SetPositveOff(){P=false;}

	public boolean IsNegative(){return N;}
	public boolean IsPositive(){return P;}

	public void SetMaxGVolume(int FilterIndex,double Volume_m3){
		 if(FilterIndex<0){return;}
		 else if(FilterIndex==0){F.Set_MGV(FilterIndex,Volume_m3);}
	}
	public void SetMinGVolume(int FilterIndex,double Volume_m3){
		 if(FilterIndex<0){return;}
		 else if(FilterIndex==0){F.Set_mGV(FilterIndex,Volume_m3);}
	}
	public void SetMaxMass(int FilterIndex,double Mass_kg){
		if(FilterIndex<0){return;}
		else if(FilterIndex==0){F.Set_Mm(FilterIndex,Mass_kg);}
	}
	public void SetMinMass(int FilterIndex,double Mass_kg){
		if(FilterIndex<0){return;}
		else if(FilterIndex==0){F.Set_mm(FilterIndex,Mass_kg);}
	}
	public int GetFilterTermQ(){return 1+F.GetSubsetCount();}
	public String GetFilterExpression(){
		return F.GSE();
	}
	public boolean SetFilter(String StandardExpression){
		boolean tmp=F.SetFilter(StandardExpression);
		if(tmp){F=F.OR;return true;}else return false;
	}

	public void ClearFilter(){F=new Filter();}

	public boolean IsGasOn(){return F.ESwitch[0];}
	public boolean IsLiquidOn(){return F.ESwitch[1];}
	public boolean IsPowderOn(){return F.ESwitch[2];}
	public boolean IsGraspOn(){return F.ESwitch[3];}
	public boolean IsPouchOn(){return F.ESwitch[4];}

	public boolean IsGasOn(int Index){
		if(Index<0){return false;}
		else if(Index==0){return F.ESwitch[0];}
		else return F.GetESwitch(Index,0);
	}

	public boolean IsLiquidOn(int Index){
		if(Index<0){return false;}
		else if(Index==0){return F.ESwitch[1];}
		else return F.GetESwitch(Index,1);
	}

	public boolean IsPowderOn(int Index){
		if(Index<0){return false;}
		else if(Index==0){return F.ESwitch[2];}
		else return F.GetESwitch(Index,2);
	}

	public boolean IsGraspOn(int Index){
		if(Index<0){return false;}
		else if(Index==0){return F.ESwitch[3];}
		else return F.GetESwitch(Index,3);
	}

	public boolean IsPouchOn(int Index){
		if(Index<0){return false;}
		else if(Index==0){return F.ESwitch[4];}
		else return F.GetESwitch(Index,4);
	}

	private boolean IsSwitchOn(int FilterIndex, int SwitchIndex){
		if(FilterIndex<0){return false;}
		else if(FilterIndex==0){return F.ESwitch[SwitchIndex];}
		else return F.GetESwitch(FilterIndex,SwitchIndex);
	}

	public boolean IsMaxGVolumeSet(int FilterIndex){
		if(FilterIndex<0){return false;}
		else if(FilterIndex==0){return F.SMV;}
		else return F.Is_SMV(FilterIndex);
	}

	public boolean IsMinGVolumeSet(int FilterIndex){
		if(FilterIndex<0){return false;}
		else if(FilterIndex==0){return F.SmV;}
		else return F.Is_SmV(FilterIndex);
	}

	public boolean IsMaxMassSet(int FilterIndex){
		if(FilterIndex<0){return false;}
		else if(FilterIndex==0){return F.SMm;}
		else return F.Is_SMm(FilterIndex);
	}

	public boolean IsMinMassSet(int FilterIndex){
		if(FilterIndex<0){return false;}
		else if(FilterIndex==0){return F.Smm;}
		else return F.Is_Smm(FilterIndex);
	}

	//Get the masses and volumes recursively
	public double GetMaxGVolume(int FilterIndex){
		if(FilterIndex<0){return Double.NEGATIVE_INFINITY;}
		else if(FilterIndex==0){return F.MGV;}
		else return F.Get_MGV(FilterIndex);
	}

	public double GetMinGVolume(int FilterIndex){
		if(FilterIndex<0){return Double.NEGATIVE_INFINITY;}
		else if(FilterIndex==0){return F.mGV;}
		else return F.Get_mGV(FilterIndex);
	}

	public double GetMaxMass(int FilterIndex){
		if(FilterIndex<0){return Double.NEGATIVE_INFINITY;}
		else if(FilterIndex==0){return F.Mm;}
		else return F.Get_Mm(FilterIndex);
	}

	public double GetMinMass(int FilterIndex){
		if(FilterIndex<0){return Double.NEGATIVE_INFINITY;}
		else if(FilterIndex==0){return F.mm;}
		else return F.Get_mm(FilterIndex);
	}

	public boolean IsCompatable(Adapter Z){
		if(!(P && Z.N || N && Z.P)){return false;}
		for(int i=0;i<GetFilterTermQ();i++){
			boolean tmp=true;
			for(int j=0;j<Z.GetFilterTermQ();j++){
				for(int k=0;k<Element.length;k++){
					if(IsSwitchOn(i,k)!=Z.IsSwitchOn(j,k)){tmp=false;break;}
				}
				if(tmp){return true;}
				else tmp=true;
			}
		}
		return false;
	}

	public String GetName(){return Name;}

	private class Filter{
		private Filter OR;
		private boolean[] ESwitch;
		private boolean SMV, SMm, SmV, Smm;
		private Double MGV,Mm, mGV, mm;

		private Filter(){
			MGV=Mm=mGV=mm=null;
			ESwitch=new boolean[Element.length];
			for(byte i=0;i<ESwitch.length;i++){ESwitch[i]=false;}
		}

		private String GSE(){//Get Standard Expression
			String E="";
			if(ESwitch[0]){E+='g';}
			if(ESwitch[1]){E+='l';}
			if(ESwitch[2]){E+='p';}
			if(ESwitch[3]){E+='G';}
			if(ESwitch[4]){E+='P';}
			if(OR!=null){return E+"+"+OR.GSE();}
			else return E;
		}

		private int GetSubsetCount(){
			if(OR==null){return 0;}
			else{return 1+OR.GetSubsetCount();}
		}

		private boolean GetESwitch(int LevelIndex, int SwitchIndex){
			if(LevelIndex>0){return OR.GetESwitch(LevelIndex-1,SwitchIndex);}
			else return ESwitch[SwitchIndex];
		}

		private boolean Is_SMV(int IL){
			if(IL>0){return OR.Is_SMV(IL-1);}
			else return SMV;
		}

		private boolean Is_SmV(int IL){
			if(IL>0){return OR.Is_SmV(IL-1);}
			else return SmV;
		}

		private boolean Is_SMm(int IL){
			if(IL>0){return OR.Is_SMm(IL-1);}
			else return SMm;
		}

		private boolean Is_Smm(int IL){
			if(IL>0){return OR.Is_Smm(IL-1);}
			else return Smm;
		}

		private double Get_MGV(int IL){
			if(IL>0){return OR.MGV;}
			else return MGV;
		}

		private double Get_mGV(int IL){
			if(IL>0){return OR.mGV;}
			else return mGV;
		}

		private double Get_Mm(int IL){
			if(IL>0){return OR.Mm;}
			else return Mm;
		}

		private double Get_mm(int IL){
			if(IL>0){return OR.mm;}
			else return mm;
		}
		private boolean SetFilter(String SE){//Expression must be in standard form (no spaces)
			String[] Sub=Split(SE,'+');
			Filter[] TERM=new Filter[Sub.length];
			for(int i=0;i<TERM.length;i++){
				TERM[i]=new Filter();
				for(int j=0;j<Sub[i].length();j++){
					if(Sub[i].charAt(j)=='g'){TERM[i].ESwitch[0]=true;}
					else if(Sub[i].charAt(j)=='l'){TERM[i].ESwitch[1]=true;}
					else if(Sub[i].charAt(j)=='p'){TERM[i].ESwitch[2]=true;}
					else if(Sub[i].charAt(j)=='G'){TERM[i].ESwitch[3]=true;}
					else if(Sub[i].charAt(j)=='P'){TERM[i].ESwitch[4]=true;}
					else return false;
				}
			}
			for(int i=1;i<TERM.length;i++){TERM[i-1].OR=TERM[i];}
			
			this.OR=TERM[0];
			return true;
		}

		private String[] Split(String E,char c){//Helper method
			int Subcount=1;
			for(int i=0;i<E.length();i++){if(E.charAt(i)==c){Subcount++;}}
			String[] Sub=new String[Subcount];
			Subcount=0;
			for(int i=0;i<Sub.length;i++){Sub[i]="";}
			for(int i=0;i<E.length();i++){
				if(E.charAt(i)!=c){Sub[Subcount]+=E.charAt(i);}
				else{Subcount++;}
			}
			return Sub;
		}

		private void Set_MGV(int FilterIndex,double V){
			if(FilterIndex>0){Set_MGV(FilterIndex-1,V);}
			else{MGV=V;SMV=true;}
		}

		private void Set_mGV(int FilterIndex,double V){
			if(FilterIndex>0){Set_mGV(FilterIndex-1,V);}
			else{mGV=V;SmV=true;}
		}

		private void Set_Mm(int FilterIndex,double m){
			if(FilterIndex>0){Set_mGV(FilterIndex-1,m);}
			else{Mm=m;SMm=true;}
		}

		private void Set_mm(int FilterIndex,double m){
			if(FilterIndex>0){Set_mm(FilterIndex-1,m);}
			else{mm=m;Smm=true;}
		}
	}
}