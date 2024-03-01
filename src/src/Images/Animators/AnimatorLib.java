package Images.Animators;

import Images.ImgLib;
import java.awt.image.*;

public class AnimatorLib {
	private ImgLib L;
	public AnimatorLib(){L= new ImgLib();}

	public Animator Bush(int Width, int Height){
		double[][] DT=new double[1][3];
		for(byte i=0;i<3;i++){
			DT[0][i]=500; //milliseconds
		}

		BufferedImage[][] Imgs = new BufferedImage[1][3];
		Imgs[0][0]=L.GrassImg(Width,Height,"1");
		Imgs[0][1]=L.GrassImg(Width,Height,"2");
		Imgs[0][2]=L.GrassImg(Width,Height,"3");

		Animator A= new Animator(Imgs);
		A.Setdt(DT);
		return A;
	}

	public Animator Rock(int Width,int Height){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.RockImg(Width,Height,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator BuchnerFlask(int Width, int Height, double Angle){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.BuchnerFlaskImg(Width,Height,0,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator Plain(int Width, int Height){
		Animator A=new Animator((byte)1,(byte)1);
		BufferedImage Img= L.PlainImg(Width,Height,"1");
		A.SetSprite((byte)0,(byte)0,Img);
		return A;
	}

	public Animator Man(int Width, int Height){
		BufferedImage[][] I=new BufferedImage[8][];
		double[][] dt=new double[8][];

		for(byte i=0;i<4;i++){
			I[i]=new BufferedImage[1];
			dt[i]=new double[1];
			dt[i][0]=0;
		}
		for(byte i=4;i<8;i++){
			I[i]=new BufferedImage[2];
			dt[i]=new double[2];
			dt[i][0]=dt[i][1]=250;
		}
		I[0][0]=L.StandingManTest(Width,Height,'N',"1");
		I[1][0]=L.StandingManTest(Width,Height,'W',"1");
		I[2][0]=L.StandingManTest(Width,Height,'S',"1");
		I[3][0]=L.StandingManTest(Width,Height,'E',"1");

		I[4][0]=L.WalkingManTest(Width,Height,'N',false,"1","1");
		I[4][1]=L.WalkingManTest(Width,Height,'N',true,"1","1");

		I[5][0]=L.WalkingManTest(Width,Height,'W',false,"1","1");
		I[5][1]=L.WalkingManTest(Width,Height,'W',false,"2","1");

		I[6][0]=L.WalkingManTest(Width,Height,'S',true,"1","1");
		I[6][1]=L.WalkingManTest(Width,Height,'S',false,"1","1");

		I[7][0]=L.WalkingManTest(Width,Height,'E',false,"1","1");
		I[7][1]=L.WalkingManTest(Width,Height,'E',false,"2","1");

		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator AdapterHead(int Width,int Height){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.AdapterHead(Width,Height,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator AdapterBody(int Width,int Height){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.AdapterBody(Width,Height,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator AdapterRightRearArm(int Width,int Height){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.RightRearArm(Width,Height,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator AdapterLeftRearArm(int Width,int Height){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.LeftRearArm(Width,Height,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator AdapterRightForeArm(int Width,int Height){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.RightForeArm(Width,Height,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator AdapterLeftForeArm(int Width,int Height){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.RightForeArm(Width,Height,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator AdapterRightHand(int Width,int Height){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.RightHand(Width,Height,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}

	public Animator AdapterLeftHand(int Width,int Height){
		BufferedImage[][] I=new BufferedImage[1][1];
		double[][] dt=new double[1][1]; dt[0][0]=0;

		I[0][0]=L.LeftHand(Width,Height,"1");
		Animator A=new Animator(I);
		A.Setdt(dt);
		return A;
	}
}