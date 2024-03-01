package Maps;

import Images.*;
import EObjects.Libraries.*;
import java.util.*;

public class MapLib{
	public Map RandomBushes(int Width,int Height,int GrassQuantity){
		Map M=new Map(Width,Height,new ImgLib().PlainImg(Width,Height,"1"),1);
		int w=25, h=25;//Grass Width/Height
		Random R=new Random();
		for(int i=0;i<GrassQuantity;i++){M.AddEObject(new TargetLib().Bush(w,h,R.nextInt(Width-w)+w/2,R.nextInt(Height-h)+h/2,1,1,1,1));}

		return M;
	}
}