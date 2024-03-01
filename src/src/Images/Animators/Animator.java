package Images.Animators;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

public class Animator{
	private BufferedImage[][] SS; //Sprite Sheet
	private byte A; //Active Row Animation Index
	private double[][] dt; //time difference between shifts
	private double[] sumdt; //the sum of each frame reference

	public Animator(){A=0;} //null Constructor

	public Animator(byte RowCount, byte ColumnCount){
		SS=new BufferedImage[RowCount][ColumnCount];
		A=0;
		dt = new double[RowCount][ColumnCount];
		sumdt = new double[RowCount];

		for(byte i=0;i<RowCount;i++){
			sumdt[i]=0;
			for(byte j=0;j<ColumnCount;j++){
				dt[i][j]=0;
			}
		}
	}

	public void Construct(BufferedImage[][] I){
		//i & j for row and column k&l is for color coordinates
		A=0;
		SS=new BufferedImage[I.length][];
		dt=new double[I.length][];
		sumdt=new double[I.length];
		for(byte i=0;i<I.length;i++){
			SS[i]=new BufferedImage[I[i].length];
			dt[i]=new double[I[i].length];
			sumdt[i]=0;
			for(byte j=0;j<I[i].length;j++){
				dt[i]=new double[I[i].length];
				SS[i][j]= new BufferedImage(I[i][j].getWidth(),I[i][j].getHeight(),BufferedImage.TYPE_INT_ARGB);
				for(int k=0;k<I[i][j].getWidth();k++){
					for(int l=0;l<I[i][j].getHeight();l++){
						SS[i][j].setRGB(k,l,I[i][j].getRGB(k,l));
					}
				}
			}
		}
	}

	public Animator(BufferedImage[][] I){Construct(I);}

	public Animator(BufferedImage Image){
		BufferedImage[][] Img=new BufferedImage[1][1];
		Img[0][0]=Image;
		Construct(Img);
	}

	public void Setdt(byte RowIndex, byte ColumnIndex, double differential_time){
		sumdt[RowIndex]-=dt[RowIndex][ColumnIndex];
		dt[RowIndex][ColumnIndex]=differential_time;
		sumdt[RowIndex]+=differential_time;
	}

	public void Setdt(byte RowIndex, double[] differential_time){
		sumdt[RowIndex]=0;
		for(byte i=0;i<dt[RowIndex].length;i++){
			dt[RowIndex][i]=differential_time[i];
			sumdt[i]+=differential_time[i];
		}
	}

	public void Setdt(double[][] differential_time){//size of DT must be exact size as the images
		sumdt = new double[differential_time.length];
		for(byte i=0;i<dt.length;i++){
			sumdt[i]=0;
			for(byte j=0;j<dt[i].length;j++){
				dt[i][j]=differential_time[i][j];
				sumdt[i]+=differential_time[i][j];
			}
		}
	}

	public void SetActiveRowIndex(byte Index){A=Index;}

	public void SetSprite(byte RowIndex, byte ColumnIndex, BufferedImage Image){
		SS[RowIndex][ColumnIndex]=new BufferedImage(Image.getWidth(),Image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		for(int i=0;i<Image.getWidth();i++){
			for(int j=0;j<Image.getHeight();j++){
				SS[RowIndex][ColumnIndex].setRGB(i,j,Image.getRGB(i,j));
			}
		}
	}


	public void SetSprites(byte RowIndex, BufferedImage[] Images){
		for(byte i=0;i<SS[i].length;i++){//i is the length of Images
			for(int j=0;j<Images[i].getWidth();j++){
				for(int k=0;k<Images[i].getHeight();k++){
					SS[RowIndex][i].setRGB(j,k,Images[i].getRGB(j,k));
				}
			}
		}
	}

	public void SetSpriteRef(byte RowIndex, byte ColumnIndex, BufferedImage Image){
		SS[RowIndex][ColumnIndex]=Image;
	}

	public void SetSpritesRef(byte RowIndex, BufferedImage[] Images){
		for(byte i=0;i<RowIndex;i++){
			SS[RowIndex][i]=Images[i];
		}
	}

	public void SetSpritesRef(BufferedImage[][] Images){
		SS=new BufferedImage[Images.length][];
		for(byte i=0;i<Images.length;i++){ //number of Rows
			SS[i]=new BufferedImage[Images[i].length];
			for(byte j=0;j<Images[i].length;j++){
				SS[i][j]=Images[i][j];
			}
		}
	}

	//ResizeImage
	public void ResizeImage(int RowIndex, int ColumnIndex, int Width, int Height){
		AffineTransform T=new AffineTransform();
		BufferedImage Scale=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
		T.scale(((double)Width)/((double)SS[RowIndex][ColumnIndex].getWidth()),((double)Height)/((double)SS[RowIndex][ColumnIndex].getHeight()));
		Graphics2D P=Scale.createGraphics();
		while(!P.drawImage(SS[RowIndex][ColumnIndex],T,null)){}
		SS[RowIndex][ColumnIndex]=Scale;
	}

	public void ResizeRow(int RowIndex,int Width, int Height){
		AffineTransform T=new AffineTransform();
		BufferedImage[] Scale=new BufferedImage[SS[RowIndex].length];
		Graphics2D P;
		for(int i=0;i<Scale.length;i++){
			Scale[i]=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
			P=Scale[i].createGraphics();
			T.scale(((double)Width)/((double)SS[RowIndex][i].getWidth()),((double)Height)/((double)SS[RowIndex][i].getHeight()));
			while(!P.drawImage(SS[RowIndex][i],T,null)){}
			T.setToIdentity();
		}
		SS[RowIndex]=Scale;
	}

	public void ResizeAll(int Width, int Height){
		AffineTransform T=new AffineTransform();
		BufferedImage[][] Scale=new BufferedImage[SS.length][];
		Graphics2D P;
		for(int i=0;i<SS.length;i++){
			Scale[i]=new BufferedImage[SS[i].length];
			for(int j=0;j<SS[i].length;j++){
				Scale[i][j]=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_ARGB);
				P=Scale[i][j].createGraphics();
				T.scale(((double)Width)/((double)SS[i][j].getWidth()),((double)Height)/((double)SS[i][j].getHeight()));
				while(!P.drawImage(SS[i][j], T, null)){}
				T.setToIdentity();
			}
		}
		SS=Scale;
	}

	public double GetSumRowdt(byte Index){return sumdt[Index];}

	public byte GetCurrentRowIndex(){return A;}
	public byte GetCurrentColIndex(double Absolute_time){
		if(sumdt[A]==0){return 0;}
		Absolute_time=Absolute_time%sumdt[A]; //Consolidate time in interval [0,sumdt[A]]
		double tmp=0;
		for(byte i=0;i<SS[A].length;i++){
			tmp+=dt[A][i];
			if(Absolute_time<=tmp){return i;}
		}
		return -1; //should never return here... should throw an exception
	}

	public BufferedImage GetRefImg(double Absolute_time){
		return SS[A][GetCurrentColIndex(Absolute_time)];
	}
}