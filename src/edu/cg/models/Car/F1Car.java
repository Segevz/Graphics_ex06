package edu.cg.models.Car;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.*;

import edu.cg.algebra.Point;
import edu.cg.models.IRenderable;

/**
 * A F1 Racing Car.
 *
 */
public class F1Car implements IRenderable {
	// TODO: Put your implementation from previous exercise.
	// 		 * We deleted all components source files (Back.java, Center.java...), so put your components implementation as well.
	//       * You also need to setup material properties for different
	//       * components of the car.
	

	@Override
	public void render(GL2 gl) {
		// Render the whole car. 
		// Here You should only render the three parts: back, center and front.
		
		// Render center of car
		Center center = new Center();
		gl.glPushMatrix();
		center.render(gl);

		// Render back of car
		Back back = new Back();
		gl.glTranslated((-1) * (Specification.B_LENGTH + Specification.C_BASE_LENGTH) / 2, 0, 0);
		back.render(gl);
		
		// Render front of car
		Front front = new Front();
		gl.glTranslated((Specification.F_FRONT_LENGTH + Specification.B_LENGTH) / 2 + Specification.C_BASE_LENGTH, 0, 0);
		front.render(gl);

		gl.glPopMatrix();
	}

	@Override
	public String toString() {
		return "F1Car";
	}

	@Override
	public void init(GL2 gl) {
	}
	@Override
	public void destroy(GL2 gl) {
	}
}
