package de.blogspot.hollowspecter.jump.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

import de.blogspot.hollowspecter.jump.other.Constants;

public class GameObj {
	
	protected Image img;
	protected float posX;
	protected float posY;
	private float prevPosX;
	private float prevPosY;
	private float startPosX;
	private float startPosY;
	private float spdX;
	protected float spdY;
	protected String imgPath;
	
	//modifyer
	
	//"collision box"
	protected float xMin, xMax, yMin, yMax = 0;
	protected AABB box;
	
	//gravity
	protected float mass;
	protected boolean apply_gravity;
	
	public GameObj (float posX, float posY, float spdX, float spdY, String imgPath, float mass)
	{
		this.setPosX(posX);
		this.setPosY(posY);
		this.setSpdX(spdX);
		this.setSpdY(spdY);
				
		setStartPosX(posX);
		setStartPosY(posY);
		
		this.mass = mass;
		apply_gravity = false;
		
		this.imgPath = imgPath;
	}
	
	public void init(GameContainer container) throws SlickException
	{
			img = new Image(imgPath);
			xMin = posX - (img.getWidth() / 2);
			xMax = posX + (img.getWidth() / 2);
			yMin = posY - (img.getHeight() / 2);
			yMax = posY + (img.getHeight() / 2);
		
		//box initialiseren xmin xmax ymin ymax
		box = new AABB(xMin, xMax, yMin, yMax);
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		// it's easier to do maths with
		float _delta = delta / 1000.0f;
		
		// save previous position in this frame
		prevPosX = posX;
		prevPosY = posY;

		/*
		 * Gravity stuff! Gravity: Constants.GRAVITY_STRENGTH
		 */
		if (apply_gravity)
		{
			float gravity_speed = this.mass * Constants.GRAVITY_STRENGTH;
			spdY += gravity_speed;
		}
		
		// make it move via delta
		posX += spdX * _delta;
		posY += spdY * _delta;
		
		//update box around the image:
		box.update(xMin, xMax, yMin, yMax);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
			img.drawCentered(posX, posY);
	}
	
	// andere Methoden
	
	public boolean intersectsWith(GameObj obj)
	{
		return Collision.intersect(this.box, obj.box, this.spdX-obj.spdX, this.spdY-obj.spdY, 1.0f, 0.0f);
	}
	
	public boolean intersectsWith(AABB box)
	{
		return Collision.intersect(this.box, box, this.spdX, this.spdY, 1.0f, 0.0f);
	}
	
	// Generierte Getter und Setter

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getPrevPosX() {
		return prevPosX;
	}

	public void setPrevPosX(float prevPosX) {
		this.prevPosX = prevPosX;
	}

	public float getPrevPosY() {
		return prevPosY;
	}

	public void setPrevPosY(float prevPosY) {
		this.prevPosY = prevPosY;
	}

	public float getStartPosX() {
		return startPosX;
	}

	public void setStartPosX(float startPosX) {
		this.startPosX = startPosX;
	}

	public float getStartPosY() {
		return startPosY;
	}

	public void setStartPosY(float startPosY) {
		this.startPosY = startPosY;
	}

	public float getSpdX() {
		return spdX;
	}

	public void setSpdX(float spdX) {
		this.spdX = spdX;
	}

	public float getSpdY() {
		return spdY;
	}

	public void setSpdY(float spdY) {
		this.spdY = spdY;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}