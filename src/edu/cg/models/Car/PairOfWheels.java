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
		gl.glTranslated(0.0, 0.0, -0.1);
		GLU glu = new GLU();
		Materials.SetDarkGreyMetalMaterial(gl);
		GLUquadric quad = glu.gluNewQuadric();
		glu.gluCylinder(quad, 0.01, 0.01, 0.2, 20, 1);
		gl.glTranslated(0.0, 0.0, (Specification.PAIR_OF_WHEELS_ROD_DEPTH + Specification.TIRE_DEPTH / 2.0));
		this.wheel.render(gl);
		gl.glTranslated(0.0, 0.0, -(Specification.PAIR_OF_WHEELS_ROD_DEPTH + Specification.TIRE_DEPTH));
		gl.glRotated(180.0, 0.0, 1.0, 0.0);
		this.wheel.render(gl);
		gl.glPopMatrix();
		glu.gluDeleteQuadric(quad);
	}

	@Override
	public void init(GL2 gl) {

	}
	
	@Override
	public String toString() {
		return "PairOfWheels";
	}

	@Override
	public void destroy(GL2 gl) {
	}

}
