package Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import Images.Animators.*;

public class EquipmentDisplay extends JPanel{
	public Animator I;
	public AffineTransform T;
	public EquipmentDisplay(int Width,int Height,Animator EquipmentImage){
		this.setLayout(null);
		this.setSize(Width,Height);
		this.setVisible(true);

		I=EquipmentImage;
		T=new AffineTransform();
		T.scale(((double)Width)/((double)EquipmentImage.GetRefImg(0).getWidth()),((double)Height)/((double)EquipmentImage.GetRefImg(0).getHeight()));
	}

	public void paint(Graphics p){
		Graphics2D P=(Graphics2D)p;
		P.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);
		while(!P.drawImage(I.GetRefImg(1),T,null)){}
	}

	public void SetImageSize(int Width,int Height){}//will set dimensions of Image
	public void SetAdapterSize(int Width, int Height){}//will set size of sub images
}