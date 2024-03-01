package Driver;

import javax.swing.*;
import java.awt.*;
import EObjects.Types.*;
import Images.Animators.Animator;
import EObjects.Libraries.*;

public class UITest extends JApplet{//The user interface test
	private Target Hero;
	private EquipmentDisplay ED;

	public void init(){
		this.setLayout(null);
		this.setSize(600,600);
		Hero=(Target)new CharacterLib().Spawn_Man(40,60,0,0);
		ED=new EquipmentDisplay(150,300,Hero.GetEquipmentImage());
		ED.setLocation(0,0);
		this.add(ED);
		this.setVisible(true);
		this.setFocusable(true);
	}

	public void paint(Graphics p){
		Graphics2D P=(Graphics2D)p;
		ED.repaint();
	}
}