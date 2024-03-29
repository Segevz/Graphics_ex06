package edu.cg.models;

import java.io.File;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import edu.cg.algebra.Vec;

public class SkewedBox implements IRenderable {
	// TODO: Add you implementation from previous exercise.
	//       * Note you may want to enable textures here to render
	//         the wooden boxes.
	
	private double length, height1, height2, depth1, depth2;
	private boolean enableTexture = false;
	private Texture textureBox = null;

	public SkewedBox() {
		length = .8;
		height1 = .7;
		height2 = .5;
		depth1 = .7;
		depth2 = .3;
	};

	public SkewedBox(double length, double h1, double h2, double d1, double d2) {
		this.length = length;
		this.height1 = h1;
		this.height2 = h2;
		this.depth1 = d1;
		this.depth2 = d2;
	}
	
	public SkewedBox(double length,boolean enableTexture) {
		this.length = length;
		this.height1 = length;
		this.height2 = length;
		this.depth1 = length;
		this.depth2 = length;
		this.enableTexture = enableTexture;
	}

	@Override
	public void render(GL2 gl) {
		
		Vec normal = null;
	       if (this.enableTexture) {
	           assert (textureBox != null && gl != null);
	           this.initTexture(gl);
	       }
		
		gl.glNormal3d(1.0, 0.0, 0.0);
		gl.glBegin(7);
		gl.glTexCoord2d(0.0, 0.0);
		gl.glVertex3d(length / 2.0, 0.0, depth2 / 2.0);
		gl.glTexCoord2d(0.0, 1.0);
		gl.glVertex3d(length / 2.0, 0.0, -depth2 / 2.0);
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex3d(length / 2.0, height2, -depth2 / 2.0);
		gl.glTexCoord2d(1.0, 0.0);
		gl.glVertex3d(length / 2.0, height2, depth2 / 2.0);

		gl.glNormal3d(-1.0, 0.0, 0.0);
		gl.glTexCoord2d(0.0, 0.0);
		gl.glVertex3d(-length / 2.0, 0.0, -depth1 / 2.0);
		gl.glTexCoord2d(0.0, 1.0);
		gl.glVertex3d(-length / 2.0, 0.0, depth1 / 2.0);
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex3d(-length / 2.0, height1, depth1 / 2.0);
		gl.glTexCoord2d(1.0, 0.0);
		gl.glVertex3d(-length / 2.0, height1, -depth1 / 2.0);

		normal = new Vec(height1 - height2, 1.0, 0.0).normalize();
        gl.glNormal3d(normal.x, normal.y, normal.z);
		gl.glTexCoord2d(0.0, 0.0);
		gl.glVertex3d(-length / 2.0, height1, depth1 / 2.0);
		gl.glTexCoord2d(0.0, 1.0);
		gl.glVertex3d(length / 2.0, height2, depth2 / 2.0);
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex3d(length / 2.0, height2, -depth2 / 2.0);
		gl.glTexCoord2d(1.0, 0.0);
		gl.glVertex3d(-length / 2.0, height1, -depth1 / 2.0);

		gl.glNormal3d(0.0, -1.0, 0.0);
		gl.glTexCoord2d(0.0, 0.0);
		gl.glVertex3d(-length / 2.0, 0.0, depth1 / 2.0);
		gl.glTexCoord2d(0.0, 1.0);
		gl.glVertex3d(-length / 2.0, 0.0, -depth1 / 2.0);
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex3d(length / 2.0, 0.0, -depth2 / 2.0);
		gl.glTexCoord2d(1.0, 0.0);
		gl.glVertex3d(length / 2.0, 0.0, depth2 / 2.0);

		normal = new Vec(depth1 - depth2, 0.0, 1.0).normalize();
        gl.glNormal3d(normal.x, 0.0, normal.z);
		gl.glTexCoord2d(0.0, 0.0);
		gl.glVertex3d(-length / 2.0, height1, depth1 / 2.0);
		gl.glTexCoord2d(0.0, 1.0);
		gl.glVertex3d(-length / 2.0, 0.0, depth1 / 2.0);
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex3d(length / 2.0, 0.0, depth2 / 2.0);
		gl.glTexCoord2d(1.0, 0.0);
		gl.glVertex3d(length / 2.0, height2, depth2 / 2.0);

		normal = new Vec(this.depth1 - this.depth2, 0.0, -1.0).normalize();
        gl.glNormal3d(normal.x, 0.0, normal.z);
		gl.glTexCoord2d(0.0, 0.0);
		gl.glVertex3d(-length / 2.0, 0.0, -depth1 / 2.0);
		gl.glTexCoord2d(0.0, 1.0);
		gl.glVertex3d(-length / 2.0, height1, -depth1 / 2.0);
		gl.glTexCoord2d(1.0, 1.0);
		gl.glVertex3d(length / 2.0, height2, -depth2 / 2.0);
		gl.glTexCoord2d(1.0, 0.0);
		gl.glVertex3d(length / 2.0, 0.0, -depth2 / 2.0);
		gl.glEnd();
		
		gl.glDisable(GL2.GL_TEXTURE_2D);
	}
	
	private void initTexture(GL2 gl) {
        gl.glEnable(GL2.GL_TEXTURE_2D);
        this.textureBox.bind(gl);
        gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAX_LOD, 1);
    }

	@Override
	public void init(GL2 gl) {
        if (enableTexture) {
            try {
                textureBox = TextureIO.newTexture(new File("Textures/WoodBoxTexture.jpg"), true);
            }
            catch (Exception e) {
                System.err.print("Unable to read texture : " + e.getMessage());
            } 
        }
	}
	
    public void destroy(GL2 gl) {
        if (this.enableTexture) {
            this.textureBox.destroy(gl);
            this.textureBox = null;
        }
    }
	
	@Override
	public String toString() {
		return "SkewedBox";
	}

}
