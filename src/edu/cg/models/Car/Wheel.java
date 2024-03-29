package edu.cg.models.Car;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;

import edu.cg.models.IRenderable;

public class Wheel implements IRenderable {

	@Override
	public void render(GL2 gl) {
		GLU glu = new GLU();
		GLUquadric quad = glu.gluNewQuadric();
		Materials.setMaterialTire(gl);
		// Cylinder 
		gl.glPushMatrix();
		gl.glTranslated(0.0, 0.0, -Specification.TIRE_DEPTH/2.0);
		glu.gluCylinder(quad, Specification.TIRE_RADIUS, Specification.TIRE_RADIUS, Specification.TIRE_DEPTH, 40, 1);
		gl.glRotated(180.0, 1.0, 0.0, 0.0);
		// Outer and Inner Disk
		glu.gluDisk(quad, 0.8*Specification.TIRE_RADIUS, Specification.TIRE_RADIUS, 40, 1);
		gl.glRotated(180.0, 1.0, 0.0, 0.0);
		gl.glTranslated(0.0, 0.0, Specification.TIRE_DEPTH);
		glu.gluDisk(quad, 0.8*Specification.TIRE_RADIUS, Specification.TIRE_RADIUS, 40, 1);
		Materials.setMaterialRims(gl);
		glu.gluDisk(quad, 0.0, 0.8*Specification.TIRE_RADIUS, 40, 1);
		gl.glTranslated(0.0, 0.0, -Specification.TIRE_DEPTH);
		gl.glRotated(180.0, 1.0, 0.0, 0.0);
		glu.gluDisk(quad, 0.0, 0.8*Specification.TIRE_RADIUS, 40, 1);
		
		gl.glPopMatrix();
		glu.gluDeleteQuadric(quad);
	}

	@Override
	public void init(GL2 gl) {
	}
	
	@Override
	public String toString() {
		return "Wheel";
	}

	@Override
	public void destroy(GL2 gl) {
	}

}
