package EObjects.Types.Adapters.Libraries;

import EObjects.Types.Adapters.*;
import Images.Animators.Animator;
import Images.Animators.AnimatorLib;
import java.awt.image.*;
import java.awt.*;

public class TargetAdapterMapLib {
	public AdapterMap Human(){
		AdapterMap Body=new AdapterMap("Equipment");
		Adapter[] M=new Adapter[14];
		M[0]=new Adapter("Head");
		M[1]=new Adapter("Torso");
		M[2]=new Adapter("Left Rear Arm");
		M[3]=new Adapter("Left Forearm");
		M[4]=new Adapter("Left Hand");
		M[5]=new Adapter("Right Rear Arm");
		M[6]=new Adapter("Right Forearm");
		M[7]=new Adapter("Right Hand");
		M[8]=new Adapter("Left Upper Leg");
		M[9]=new Adapter("Left Lower Leg");
		M[10]=new Adapter("Left Foot");
		M[11]=new Adapter("Right Upper Leg");
		M[12]=new Adapter("Right Lower Leg");
		M[13]=new Adapter("Right Foot");

		for(byte i=0;i<M.length;i++){Body.Add(M[i]);}

		Body.Connect(0,1);  //Head to Torso
		Body.Connect(1,2);  //Torso to Left Rear Arm
		Body.Connect(2,3);  //Left Rear Arm to Left Forearm
		Body.Connect(3,4);  //Left Forearm to Left Hand
		Body.Connect(5,1);  //Right Rear Arm to Torso
		Body.Connect(5,6);  //Right Forearm to Right Hand
		Body.Connect(8,1);  //Left Upper Leg to Torso
		Body.Connect(9,8);  //Left Upper Leg to Left Lower Leg
		Body.Connect(10,9); //Left foot to Lower Leg
		Body.Connect(11,1); //Right Upper Leg to Torso
		Body.Connect(12,11);//Right Lower Leg to Right Upper Leg
		Body.Connect(13,12);//Right Foot to Right Lower Leg


		M[0].Img=new AnimatorLib().AdapterHead(20,20);
		M[1].Img=new AnimatorLib().AdapterBody(20,20);
		M[2].Img=new AnimatorLib().AdapterLeftRearArm(20,20);
		M[3].Img=new AnimatorLib().AdapterLeftForeArm(20,20);
		M[4].Img=new AnimatorLib().AdapterLeftHand(20,20);
		M[5].Img=new AnimatorLib().AdapterRightRearArm(20,20);
		M[6].Img=new AnimatorLib().AdapterRightForeArm(20,20);
		M[7].Img=new AnimatorLib().AdapterRightHand(20,20);

		//Scrap work for Equipment Display Testing !!WILL NEED REWRITING FOR EFFICIENCY!!
		for(int i=8;i<M.length;i++){M[i].Img=new AnimatorLib().AdapterHead(25,25);}

		//Needs a for loop for Animated Background
		BufferedImage bg=new BufferedImage(150,300,BufferedImage.TYPE_INT_ARGB);
		for(int i=0;i<bg.getWidth();i++){
			for(int j=0;j<bg.getHeight();j++){
				bg.setRGB(i,j,0xFF00FFFF);
			}
		}

		Body.SetImageLocation(0,bg.getWidth()/2-M[0].Img.GetRefImg(0).getWidth()/2,0);
		Body.SetImageLocation(1,bg.getWidth()/2-M[1].Img.GetRefImg(0).getWidth()/2,M[1].Img.GetRefImg(0).getHeight());
		Body.SetImageLocation(2,bg.getWidth()/2+M[2].Img.GetRefImg(0).getWidth()/2,M[2].Img.GetRefImg(0).getHeight());
		Body.SetImageLocation(3,bg.getWidth()/2+(int)(1.5*M[3].Img.GetRefImg(0).getWidth()),M[3].Img.GetRefImg(0).getHeight());
		Body.SetImageLocation(4,bg.getWidth()/2+(int)(2.5*M[4].Img.GetRefImg(0).getWidth()),M[4].Img.GetRefImg(0).getHeight());
		Body.SetImageLocation(5,bg.getWidth()/2-(int)(1.5*M[5].Img.GetRefImg(0).getWidth()),M[5].Img.GetRefImg(0).getHeight());
		Body.SetImageLocation(6,bg.getWidth()/2-(int)(2.5*M[6].Img.GetRefImg(0).getWidth()),M[6].Img.GetRefImg(0).getHeight());
		Body.SetImageLocation(7,0,M[7].Img.GetRefImg(0).getHeight());

		//Each Image on every Adapters Animator will be set up
		Graphics2D P=bg.createGraphics();
		for(int i=0;i<8;i++){while(!P.drawImage(M[i].Img.GetRefImg(0),Body.GetNodeXLoc(i),Body.GetNodeYLoc(i),null)){}}
		Body.D=new Animator(bg);

		return Body;
	}
}