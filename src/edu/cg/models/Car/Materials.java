package edu.cg.models.Car;

import com.jogamp.opengl.GL2;

public class Materials {
	private static final float DARK_GRAY[] = { 0.2f, 0.2f, 0.2f };
	private static final float DARK_RED[] = { 0.25f, 0.01f, 0.01f };
	private static final float RED[] = { 0.7f, 0f, 0f };
	private static final float BLACK[] = { 0.05f, 0.05f, 0.05f };

	public static void SetMetalMaterial(GL2 gl, float[] color) {
		gl.glColor3fv(color, 0);
	}

	public static void SetBlackMetalMaterial(GL2 gl) {
		SetMetalMaterial(gl, BLACK);
	}

	public static void SetRedMetalMaterial(GL2 gl) {
		SetMetalMaterial(gl, RED);
	}

	public static void SetDarkRedMetalMaterial(GL2 gl) {
		SetMetalMaterial(gl, DARK_RED);
	}
	
	public static void SetDarkGreyMetalMaterial(GL2 gl) {
		SetMetalMaterial(gl, DARK_GRAY);
	}

	public static void setMaterialTire(GL2 gl) {
		float col[] = { .05f, .05f, .05f };
		gl.glColor3fv(col,0);
	}

	public static void setMaterialRims(GL2 gl) {
		gl.glColor3fv(DARK_GRAY,0);
	}

    public static void setWoodenBoxMaterial(GL2 gl) {
		float[] ambient = new float[]{0.4f, 0.4f, 0.4f, 1.0f};
		float[] diffuse = new float[]{0.715f, 0.4285f, 0.18145f, 1.0f};
		float[] specular = new float[]{0.393549f, 0.271905f, 0.166720f, 1.0f};
		gl.glMaterialf(1028, 5633, 25.6f);
		gl.glMaterialfv(1028, 4608, ambient, 0);
		gl.glMaterialfv(1028, 4609, diffuse, 0);
		gl.glMaterialfv(1028, 4610, specular, 0);
    }

	public static void setGreenMaterial(GL2 gl) {
		float[] ambient = new float[]{0.0215f, 0.1745f, 0.0215f, 1.0f};
		float[] diffuse = new float[]{0.07569f, 0.61425f, 0.07569f, 1.0f};
		float[] specular = new float[]{0.633f, 0.72781f, 0.632f, 1.0f};
		gl.glMaterialf(1028, 5633, 128.0f);
		gl.glMaterialfv(1028, 4608, ambient, 0);
		gl.glMaterialfv(1028, 4609, diffuse, 0);
		gl.glMaterialfv(1028, 4610, specular, 0);
	}

	public static void setAsphaltMaterial(GL2 gl) {
		float[] ambient = new float[]{0.15375f, 0.15f, 0.16625f, 1.0f};
		float[] diffuse = new float[]{0.68275f, 0.67f, 0.72525f, 1.0f};
		float[] specular = new float[]{0.33274f, 0.328635f, 0.346435f, 1.0f};
		gl.glMaterialf(1028, 5633, 38.4f);
		gl.glMaterialfv(1028, 4608, ambient, 0);
		gl.glMaterialfv(1028, 4609, diffuse, 0);
		gl.glMaterialfv(1028, 4610, specular, 0);
	}

}
