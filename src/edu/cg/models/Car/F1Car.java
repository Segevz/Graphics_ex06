package edu.cg.models.Car;

import com.jogamp.opengl.*;

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

}
