package EObjects;

import java.awt.image.*;
import Images.Animators.*;

public abstract class EObject{
	private String N; //Name of Object
	private double m; //mass (kg)
	private double GV; //Gaussian Volume of an Object (m^3)
	private Animator I; //Animated images

	public EObject(Animator Animation){I=Animation;}

	public double GetMass(){return m;}
	public String GetName(){return N;}
	public void SetName(String Name){N=Name;}

	//Image Manipulators
	public void Setdt(byte RowIndex, byte ColumnIndex, double differential_time){I.Setdt(RowIndex,ColumnIndex,differential_time);}
	public void Setdt(byte RowIndex, double[] differential_time){I.Setdt(RowIndex,differential_time);}
	public void Setdt(double[][] differential_time){I.Setdt(differential_time);}
	public void SetActiveRowIndex(byte Index){I.SetActiveRowIndex(Index);}
	public void SetSprite(byte RowIndex, byte ColumnIndex, BufferedImage Image){I.SetSprite(RowIndex,ColumnIndex,Image);}
	public void SetSprites(byte RowIndex, BufferedImage[] Images){I.SetSprites(RowIndex,Images);}
	public void SetSpriteRef(byte RowIndex,byte ColumnIndex,BufferedImage Image){I.SetSpriteRef(RowIndex,ColumnIndex,Image);}
	public void SetSpritesRef(byte RowIndex,BufferedImage[] Images){I.SetSpritesRef(RowIndex,Images);}
	public void SetSpritesRef(BufferedImage[][] Images){I.SetSpritesRef(Images);}
	public double GetSumRowdt(byte Index){return I.GetSumRowdt(Index);}
	public byte GetCurrentRowIndex(){return I.GetCurrentRowIndex();}
	public byte GetCurrentColIndex(double Absolute_time){return I.GetCurrentColIndex(Absolute_time);}
	public BufferedImage GetRefImg(double Absolute_time){return I.GetRefImg(Absolute_time);}
}