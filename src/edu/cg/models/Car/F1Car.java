package edu.cg.models.Car;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.*;

import edu.cg.algebra.Point;
import edu.cg.models.IRenderable;




public class F1Car
  implements IRenderable
{
  public F1Car() {}
  
  public void render(GL2 gl)
  {
    new Center().render(gl);
    gl.glPushMatrix();
    gl.glTranslated(-0.3875D, 0.0D, 0.0D);
    new Back().render(gl);
    gl.glPopMatrix();
    gl.glPushMatrix();
    gl.glTranslated(0.425D, 0.0D, 0.0D);
    new Front().render(gl);
    gl.glPopMatrix();
  }
  

  public String toString()
  {
    return "F1Car";
  }
  


  public void init(GL2 gl) {}
  

  public void destroy(GL2 gl) {}
  

  private Point pointMatrixMult(double[] mat, Point p)
  {
    float temp = 0.0F;
    float[] arr = p.toArray();
    float[] res = new float[3];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        temp = (float)(temp + mat[(4 * r + c)] * arr[c]);
      }
      temp = (float)(temp + mat[(4 * r + 3)]);
      res[r] = temp;
      temp = 0.0F;
    }
    Point resP = new Point(0.0D);
    resP.x = res[0];
    resP.y = res[1];
    resP.z = res[2];
    return resP;
  }
  
  public List<Point> getPointsOnSurface(GL2 gl) {
    LinkedList<Point> retList = new LinkedList();
    double[] temp = new double[16];
    gl.glGetDoublev(2982, temp, 0);
    retList.add(pointMatrixMult(temp, new Point(0.0D)));
    return retList;
  }

}
