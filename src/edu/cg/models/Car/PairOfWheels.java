package edu.cg.models.Car;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import edu.cg.models.IRenderable;

public class PairOfWheels implements IRenderable {
	private final Wheel wheel = new Wheel();
	
	@Override
	public void render(GL2 gl) {
		gl.glPushMatrix();
		gl.glTranslated(0.0, 0.0, -Specification.TIRE_DEPTH);
		GLU glu = new GLU();
		Materials.SetDarkGreyMetalMaterial(gl);
		GLUquadric quad = glu.gluNewQuadric();
		glu.gluCylinder(quad, Specification.PAIR_OF_WHEELS_ROD_RADIUS, Specification.PAIR_OF_WHEELS_ROD_RADIUS, Specification.PAIR_OF_WHEELS_ROD_DEPTH, 40, 1);
		// Render both wheels
		gl.glTranslated(0, 0, (Specification.TIRE_DEPTH + Specification.PAIR_OF_WHEELS_ROD_DEPTH) / 2);
		wheel.render(gl);
		gl.glTranslated(0, 0, - Specification.TIRE_DEPTH - Specification.PAIR_OF_WHEELS_ROD_DEPTH);
		gl.glRotated(180.0, 0.0, 1.0, 0.0);
		wheel.render(gl);
		gl.glPopMatrix();
		glu.gluDeleteQuadric(quad);	}

	@Override
	public void init(GL2 gl) {

	}
	
	@Override
	public String toString() {
		return "PairOfWheels";
	}

}
