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
    gl.glTranslated(-(Specification.C_BASE_LENGTH + Specification.B_LENGTH) / 2, 0.0, 0.0);
    
    new Back().render(gl);
    gl.glPopMatrix();
    gl.glPushMatrix();
    gl.glTranslated(Specification.C_BASE_LENGTH + (Specification.B_LENGTH + Specification.F_FRONT_LENGTH) / 2, 0.0, 0.0);
    
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
