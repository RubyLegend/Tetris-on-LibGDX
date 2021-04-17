package com.gdx.tetris;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Figure {
	Rectangle a,b,c,d, //Main blocks
			  e,f,g,h; //Projected blocks
	String type;
	int color;
	int rot = 0;
	int XMIN  = Settings.XMIN;
	int XMAX  = GameScreen.XMAX;
	int YMIN  = Settings.YMIN;
	int YMAX  = GameScreen.YMAX;
	int WIDTH = Settings.WIDTH;
	int COLS  = Settings.COLS;
	int ROWS = Settings.ROWS;
	
	
	//Colors and forms are taken from
	//wiki: https://en.wikipedia.org/wiki/Tetromino
	Figure(String type, int [][] MESH){
		a = new Rectangle();
		b = new Rectangle();
		c = new Rectangle();
		d = new Rectangle();
		e = new Rectangle();
		f = new Rectangle();
		g = new Rectangle();
		h = new Rectangle();
		this.type = type;
		switch(type) {
		case "i": //++
			a.x = XMIN + 3*WIDTH;
			b.x = XMIN + 4*WIDTH;
			c.x = XMIN + 5*WIDTH;
			d.x = XMIN + 6*WIDTH;
			a.y = b.y = c.y = d.y = YMAX;
			color = 2; //cyan
			break;
		case "l": //++
			a.x = XMIN + 4*WIDTH;
			b.x = a.x + WIDTH;
			c.x = d.x = b.x + WIDTH;
			a.y = b.y = c.y = YMAX - WIDTH;
			d.y = a.y + WIDTH;
			color = 4; //orange
			break;
		case "j": //++
			a.x = b.x = XMIN + 4*WIDTH;
			a.y = YMAX;
			b.y = c.y = d.y = YMAX - WIDTH;
			c.x = XMIN + 5*WIDTH;
			d.x = XMIN + 6*WIDTH;
			color = 1; //blue
			break;
		case "o": //++
			a.x = c.x = XMIN + 4*WIDTH;
			b.x = d.x = XMIN + 5*WIDTH;
			a.y = b.y = YMAX;
			c.y = d.y = YMAX - WIDTH;
			color = 7; //yellow
			break;
		case "s": //++
			b.x = c.x = XMIN + 5*WIDTH;
			d.x = XMIN + 6*WIDTH;
			a.x = XMIN + 4*WIDTH;
			a.y = b.y = YMAX - WIDTH;
			c.y = d.y = YMAX;
			color = 3; //green
			break;
		case "t": //++
			a.y = b.y = c.y = YMAX - WIDTH;
			d.y = YMAX;
			a.x = XMIN + 4*WIDTH;
			b.x = d.x = XMIN + 5*WIDTH;
			c.x = XMIN + 6*WIDTH;
			color = 5; //purple
			break;
		case "z": //++
			b.x = c.x = XMIN + 5*WIDTH;
			a.x = XMIN + 4*WIDTH;
			d.x = XMIN + 6*WIDTH;
			a.y = b.y = YMAX;
			c.y = d.y = YMAX - WIDTH;
			color = 6; //red
			break;
		}
		rot = 0;
		Project(MESH);
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
	}
	public void setFigure(String type, int [][] MESH) {
		e.x = 0;
		this.type = type;
		switch(type) {
		case "i": //++
			a.x = XMIN + 3*WIDTH;
			b.x = XMIN + 4*WIDTH;
			c.x = XMIN + 5*WIDTH;
			d.x = XMIN + 6*WIDTH;
			a.y = b.y = c.y = d.y = YMAX;
			color = 2; //cyan
			break;
		case "l": //++
			a.x = XMIN + 4*WIDTH;
			b.x = a.x + WIDTH;
			c.x = d.x = b.x + WIDTH;
			a.y = b.y = c.y = YMAX - WIDTH;
			d.y = a.y + WIDTH;
			color = 4; //orange
			break;
		case "j": //++
			a.x = b.x = XMIN + 4*WIDTH;
			a.y = YMAX;
			b.y = c.y = d.y = YMAX - WIDTH;
			c.x = XMIN + 5*WIDTH;
			d.x = XMIN + 6*WIDTH;
			color = 1; //blue
			break;
		case "o": //++
			a.x = c.x = XMIN + 4*WIDTH;
			b.x = d.x = XMIN + 5*WIDTH;
			a.y = b.y = YMAX;
			c.y = d.y = YMAX - WIDTH;
			color = 7; //yellow
			break;
		case "s": //++
			b.x = c.x = XMIN + 5*WIDTH;
			d.x = XMIN + 6*WIDTH;
			a.x = XMIN + 4*WIDTH;
			a.y = b.y = YMAX - WIDTH;
			c.y = d.y = YMAX;
			color = 3; //green
			break;
		case "t": //++
			a.y = b.y = c.y = YMAX - WIDTH;
			d.y = YMAX;
			a.x = XMIN + 4*WIDTH;
			b.x = d.x = XMIN + 5*WIDTH;
			c.x = XMIN + 6*WIDTH;
			color = 5; //purple
			break;
		case "z": //++
			b.x = c.x = XMIN + 5*WIDTH;
			a.x = XMIN + 4*WIDTH;
			d.x = XMIN + 6*WIDTH;
			a.y = b.y = YMAX;
			c.y = d.y = YMAX - WIDTH;
			color = 6; //red
			break;
		}
		rot = 0;
		Project(MESH);
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
	}
	public void moveDown(int [][] MESH) {
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = 0;
		UnProject(MESH);
		
		if(	a.y != YMIN && b.y != YMIN && c.y != YMIN && d.y != YMIN 
				&& MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)-1] == 0
				&& MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)-1] == 0
				&& MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)-1] == 0
				&& MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)-1] == 0) {

			a.y -= WIDTH;
			b.y -= WIDTH;
			c.y -= WIDTH;
			d.y -= WIDTH;
			
		}
		
		Project(MESH);
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
	}
	public void moveLeft(int [][] MESH) {
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = 0;
		UnProject(MESH);
		if(	a.x != XMIN && b.x != XMIN && c.x != XMIN && d.x != XMIN
			&& MESH[(int) ((a.x-XMIN)/WIDTH)-1][(int) ((a.y-YMIN)/WIDTH)] == 0
			&& MESH[(int) ((b.x-XMIN)/WIDTH)-1][(int) ((b.y-YMIN)/WIDTH)] == 0
			&& MESH[(int) ((c.x-XMIN)/WIDTH)-1][(int) ((c.y-YMIN)/WIDTH)] == 0
			&& MESH[(int) ((d.x-XMIN)/WIDTH)-1][(int) ((d.y-YMIN)/WIDTH)] == 0) {
			
			a.x -= WIDTH;
			b.x -= WIDTH;
			c.x -= WIDTH;
			d.x -= WIDTH;
			
		}
		Project(MESH);
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
	}
	public void moveRight(int [][] MESH) {
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = 0;
		UnProject(MESH);
		if(	a.x != XMAX && b.x != XMAX && c.x != XMAX && d.x != XMAX 
				&& MESH[(int) ((a.x-XMIN)/WIDTH)+1][(int) ((a.y-YMIN)/WIDTH)] == 0
				&& MESH[(int) ((b.x-XMIN)/WIDTH)+1][(int) ((b.y-YMIN)/WIDTH)] == 0
				&& MESH[(int) ((c.x-XMIN)/WIDTH)+1][(int) ((c.y-YMIN)/WIDTH)] == 0
				&& MESH[(int) ((d.x-XMIN)/WIDTH)+1][(int) ((d.y-YMIN)/WIDTH)] == 0) {
			
			a.x += WIDTH;
			b.x += WIDTH;
			c.x += WIDTH;
			d.x += WIDTH;
			
		}
		Project(MESH);
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
	}
	public void Drop(int [][] MESH) {
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = 0;
		UnProject(MESH);
		
		float i,j,k,l;
		for(i = a.y - WIDTH, j = b.y - WIDTH, k = c.y - WIDTH, l = d.y - WIDTH; 
				i >= YMIN && j >= YMIN && k >= YMIN && l >= YMIN;
				i -= WIDTH, j -= WIDTH, k -= WIDTH, l -= WIDTH) {
			if(i == YMIN || j == YMIN || k == YMIN || l == YMIN 
				|| MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((i - WIDTH - YMIN)/WIDTH)] != 0
				|| MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((j - WIDTH - YMIN)/WIDTH)] != 0
				|| MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((k - WIDTH - YMIN)/WIDTH)] != 0
				|| MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((l - WIDTH - YMIN)/WIDTH)] != 0) {
				a.y = i;
				b.y = j;
				c.y = k;
				d.y = l;
				break;
			}
		}
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
	}
	public boolean overlaps(int [][] MESH) {
		boolean answer = false;
		//MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = 0;
		//MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = 0;
		//MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = 0;
		//MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = 0;
		if(MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] != 0 ||
		   MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] != 0 ||
		   MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] != 0 ||
		   MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] != 0)
			answer = true;
		//MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
		//MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
		//MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
		//MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
		return answer;
	}
	public boolean bottom(int [][] MESH) {
		boolean answer = false;
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = 0;
		UnProject(MESH);
		if(a.y == YMIN || MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)-1] != 0 ||
		   b.y == YMIN || MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)-1] != 0 ||
		   c.y == YMIN || MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)-1] != 0 ||
		   d.y == YMIN || MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)-1] != 0)
			answer = true;
		Project(MESH);
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
		return answer;
	}
	//test for SRS wall kick, if needed
	public boolean test_rotation(boolean left, int [][] MESH) {
		//positions
		//0 - spawn
		//1 - clockwise rotation (R)
		//2 - double rotation    (2)
		//3 - counter-clockwise  (L)
		if(left) {
			if(type != "i")
				switch(rot) {
					case 0:{ //0->L
						if(testLeft(0,0,MESH))
							return true;
						else if(testLeft(1,0,MESH))
							return true;
						else if(testLeft(1,1,MESH))
							return true;
						else if(testLeft(0,-2,MESH))
							return true;
						else if(testLeft(1,-2,MESH))
							return true;
						else return false;
					}
					case 1:{ //R->0
						if(testLeft(0,0,MESH))
							return true;
						else if(testLeft(1,0,MESH))
							return true;
						else if(testLeft(1,-1,MESH))
							return true;
						else if(testLeft(0,+2,MESH))
							return true;
						else if(testLeft(1,2,MESH))
							return true;
						else return false;
					}
					case 2:{ //2->R
						if(testLeft(0,0,MESH))
							return true;
						else if(testLeft(-1,0,MESH))
							return true;
						else if(testLeft(-1,1,MESH))
							return true;
						else if(testLeft(0,-2,MESH))
							return true;
						else if(testLeft(-1,-2,MESH))
							return true;
						else return false;
					}
					case 3:{ //L->2
						if(testLeft(0,0,MESH))
							return true;
						else if(testLeft(-1,0,MESH))
							return true;
						else if(testLeft(-1,-1,MESH))
							return true;
						else if(testLeft(0,2,MESH))
							return true;
						else if(testLeft(-1,2,MESH))
							return true;
						else return false;
					}
				}
			else
				switch(rot) {
					case 0:{ //0->L
						if(testLeft(0,0,MESH))
							return true;
						else if(testLeft(-1,0,MESH))
							return true;
						else if(testLeft(2,0,MESH))
							return true;
						else if(testLeft(-1,2,MESH))
							return true;
						else if(testLeft(2,-1,MESH))
							return true;
						else return false;
					}
					case 1:{ //R->0
						if(testLeft(0,0,MESH))
							return true;
						else if(testLeft(2,0,MESH))
							return true;
						else if(testLeft(-1,0,MESH))
							return true;
						else if(testLeft(2,1,MESH))
							return true;
						else if(testLeft(-1,-2,MESH))
							return true;
						else return false;
					}
					case 2:{ //2->R
						if(testLeft(0,0,MESH))
							return true;
						else if(testLeft(1,0,MESH))
							return true;
						else if(testLeft(-2,0,MESH))
							return true;
						else if(testLeft(1,-2,MESH))
							return true;
						else if(testLeft(-2,1,MESH))
							return true;
						else return false;
					}
					case 3:{ //L->2
						if(testLeft(0,0,MESH))
							return true;
						else if(testLeft(-2,0,MESH))
							return true;
						else if(testLeft(1,0,MESH))
							return true;
						else if(testLeft(-2,-1,MESH))
							return true;
						else if(testLeft(1,2,MESH))
							return true;
						else return false;
					}
				}
		}
		else {
			if(type != "i")
				switch(rot) {
					case 0:{ //0->R
						if(testRight(0,0,MESH))
							return true;
						else if(testRight(-1,0,MESH))
							return true;
						else if(testRight(-1,1,MESH))
							return true;
						else if(testRight(0,-2,MESH))
							return true;
						else if(testRight(-1,-2,MESH))
							return true;
						else return false;
					}
					case 1:{ //R->2
						if(testRight(0,0,MESH))
							return true;
						else if(testRight(1,0,MESH))
							return true;
						else if(testRight(1,-1,MESH))
							return true;
						else if(testRight(0,+2,MESH))
							return true;
						else if(testRight(1,2,MESH))
							return true;
						else return false;
					}
					case 2:{ //2->L
						if(testRight(0,0,MESH))
							return true;
						else if(testRight(1,0,MESH))
							return true;
						else if(testRight(1,1,MESH))
							return true;
						else if(testRight(0,-2,MESH))
							return true;
						else if(testRight(1,-2,MESH))
							return true;
						else return false;
					}
					case 3:{ //L->0
						if(testRight(0,0,MESH))
							return true;
						else if(testRight(-1,0,MESH))
							return true;
						else if(testRight(-1,-1,MESH))
							return true;
						else if(testRight(0,2,MESH))
							return true;
						else if(testRight(-1,2,MESH))
							return true;
						else return false;
					}
				}
			else
				switch(rot) {
					case 0:{ //0->L
						if(testRight(0,0,MESH))
							return true;
						else if(testRight(1,0,MESH))
							return true;
						else if(testRight(-2,0,MESH))
							return true;
						else if(testRight(1,-2,MESH))
							return true;
						else if(testRight(-2,1,MESH))
							return true;
						else return false;
					}
					case 1:{ //R->0
						if(testRight(0,0,MESH))
							return true;
						else if(testRight(2,0,MESH))
							return true;
						else if(testRight(-1,0,MESH))
							return true;
						else if(testRight(2,1,MESH))
							return true;
						else if(testRight(-1,-2,MESH))
							return true;
						else return false;
					}
					case 2:{ //2->R
						if(testRight(0,0,MESH))
							return true;
						else if(testRight(1,0,MESH))
							return true;
						else if(testRight(-2,0,MESH))
							return true;
						else if(testRight(1,-2,MESH))
							return true;
						else if(testRight(-2,1,MESH))
							return true;
						else return false;
					}
					case 3:{ //L->2
						if(testRight(0,0,MESH))
							return true;
						else if(testRight(-2,0,MESH))
							return true;
						else if(testRight(1,0,MESH))
							return true;
						else if(testRight(-2,-1,MESH))
							return true;
						else if(testRight(1,2,MESH))
							return true;
						else return false;
					}
				}
		}
		return true;
	}
	private boolean testLeft(int x, int y, int [][] MESH) {
		//Removing block from mesh
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = 0;
		UnProject(MESH);
		RotateLeft(MESH);
		a.x+=x*WIDTH;   a.y+=y*WIDTH;
		b.x+=x*WIDTH;   b.y+=y*WIDTH;
		c.x+=x*WIDTH;   c.y+=y*WIDTH;
		d.x+=x*WIDTH;   d.y+=y*WIDTH;
		if(a.x >= XMIN && b.x >= XMIN && c.x >= XMIN && d.x >= XMIN
				&& a.y >= YMIN && b.y >= YMIN && c.y >= YMIN && d.y >= YMIN
				&& a.x <= XMAX && b.x <= XMAX && c.x <= XMAX && d.x <= XMAX
				&& a.y <= YMAX && b.y <= YMAX && c.y <= YMAX && d.y <= YMAX) {
			if(overlaps(MESH)) {
				a.x-=x*WIDTH;   a.y-=y*WIDTH;
				b.x-=x*WIDTH;   b.y-=y*WIDTH;
				c.x-=x*WIDTH;   c.y-=y*WIDTH;
				d.x-=x*WIDTH;   d.y-=y*WIDTH;
				RotateRight(MESH);
				//Adding block back to mesh
				Project(MESH);
				MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
				MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
				MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
				MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
				return false;
			}
			//Adding block back to mesh
			Project(MESH);
			MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
			return true;
		}
		else {
			a.x-=x*WIDTH;   a.y-=y*WIDTH;
			b.x-=x*WIDTH;   b.y-=y*WIDTH;
			c.x-=x*WIDTH;   c.y-=y*WIDTH;
			d.x-=x*WIDTH;   d.y-=y*WIDTH;
			RotateRight(MESH);
			//Adding block back to mesh
			Project(MESH);
			MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
			return false;
		}
	}
	private boolean testRight(int x, int y, int [][] MESH) {
		//Removing block from mesh
		MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = 0;
		MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = 0;
		UnProject(MESH);
		RotateRight(MESH);
		a.x+=x*WIDTH;   a.y+=y*WIDTH;
		b.x+=x*WIDTH;   b.y+=y*WIDTH;
		c.x+=x*WIDTH;   c.y+=y*WIDTH;
		d.x+=x*WIDTH;   d.y+=y*WIDTH;
		if(a.x >= XMIN && b.x >= XMIN && c.x >= XMIN && d.x >= XMIN
				&& a.y >= YMIN && b.y >= YMIN && c.y >= YMIN && d.y >= YMIN
				&& a.x <= XMAX && b.x <= XMAX && c.x <= XMAX && d.x <= XMAX
				&& a.y <= YMAX && b.y <= YMAX && c.y <= YMAX && d.y <= YMAX) {
			if(overlaps(MESH)) {
				a.x-=x*WIDTH;   a.y-=y*WIDTH;
				b.x-=x*WIDTH;   b.y-=y*WIDTH;
				c.x-=x*WIDTH;   c.y-=y*WIDTH;
				d.x-=x*WIDTH;   d.y-=y*WIDTH;
				RotateLeft(MESH);
				//Adding block back to mesh
				Project(MESH);
				MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
				MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
				MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
				MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
				return false;
			}
			//Adding block back to mesh
			Project(MESH);
			MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
			return true;
		}
		else {
			a.x-=x*WIDTH;   a.y-=y*WIDTH;
			b.x-=x*WIDTH;   b.y-=y*WIDTH;
			c.x-=x*WIDTH;   c.y-=y*WIDTH;
			d.x-=x*WIDTH;   d.y-=y*WIDTH;
			RotateLeft(MESH);
			//Adding block back to mesh
			Project(MESH);
			MESH[(int) ((a.x-XMIN)/WIDTH)][(int) ((a.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((b.x-XMIN)/WIDTH)][(int) ((b.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((c.x-XMIN)/WIDTH)][(int) ((c.y-YMIN)/WIDTH)] = color;
			MESH[(int) ((d.x-XMIN)/WIDTH)][(int) ((d.y-YMIN)/WIDTH)] = color;
			return false;
		}
	}
	//For rotation look in
	//wiki: https://tetris.wiki/Super_Rotation_System
	//P.S. Rotation isn`t optimized for now
	//But it works :)
	public void RotateLeft(int [][] MESH) {
		if(rot == 0) rot = 3;
		else rot--;
		
		
		switch(type) {
		case "i":
			switch(rot) {
			case 0:
				a.y = c.y = d.y = b.y;
				a.x -= 2*WIDTH;
				b.x -= WIDTH;
				//c.x doesn`t change
				d.x += WIDTH;
				break;
			//case 1
			case 1:
				a.x = c.x = d.x = b.x;
				a.y += 2*WIDTH;
				b.y += WIDTH;
				//c.y doesn`t change
				d.y -= WIDTH;
				break;
			//case 2
			case 2:
				a.y = c.y = d.y = b.y;
				a.x += 2*WIDTH;
				b.x += WIDTH;
				//c.x doesn`t change
				d.x -= WIDTH;
				break;
			//case 3
			case 3:
				a.x = c.x = d.x = b.x;
				a.y -= 2*WIDTH;
				b.y -= WIDTH;
				//c.y doesn`t change
				d.y += WIDTH;
				break;
			//case 4
			}
			break;
		//case i
		case "l": // block 'b' doesn`t change from now
			switch(rot) {
			case 0:
				d.y+=2*WIDTH;
				c.x+=WIDTH;
				c.y+=WIDTH;
				a.x-=WIDTH;
				a.y-=WIDTH;
				break;
			//case 1
			case 1:
				d.x+=2*WIDTH;
				c.x+=WIDTH;
				c.y-=WIDTH;
				a.x-=WIDTH;
				a.y+=WIDTH;
				break;
			//case 2
			case 2:
				d.y-=2*WIDTH;
				c.x-=WIDTH;
				c.y-=WIDTH;
				a.x+=WIDTH;
				a.y+=WIDTH;
				break;
			//case 3
			case 3:
				d.x-=2*WIDTH;
				c.x-=WIDTH;
				c.y+=WIDTH;
				a.x+=WIDTH;
				a.y-=WIDTH;
				break;
			//case 4
			}
			break;
		//case l
		case "j":
			switch(rot) {
			case 0:
				a.x-=2*WIDTH;
				b.x-=WIDTH;
				b.y-=WIDTH;
				d.x+=WIDTH;
				d.y+=WIDTH;
				break;
			//case 1
			case 1:
				a.y += 2*WIDTH;
				b.x-=WIDTH;
				b.y+=WIDTH;
				d.y-=WIDTH;
				d.x+=WIDTH;
				break;
			//case 2
			case 2:
				a.x+=2*WIDTH;
				b.x+=WIDTH;
				b.y+=WIDTH;
				d.x-=WIDTH;
				d.y-=WIDTH;
				break;
			//case 3
			case 3:
				a.y -= 2*WIDTH;
				b.x+=WIDTH;
				b.y-=WIDTH;
				d.y+=WIDTH;
				d.x-=WIDTH;
				break;
			//case 4
			}
			break;
		//case j
		case "o":
			//Why do I even need to rotate it?)
			break;
		//case o
		case "s":
			switch(rot) {
			case 0:
				a.x-=WIDTH;
				a.y-=WIDTH;
				c.x-=WIDTH;
				c.y+=WIDTH;
				d.y+=2*WIDTH;
				break;
			//case 1
			case 1:
				a.x-=WIDTH;
				a.y+=WIDTH;
				c.x+=WIDTH;
				c.y+=WIDTH;
				d.x+=2*WIDTH;
				break;
			//case 2
			case 2:
				a.x+=WIDTH;
				a.y+=WIDTH;
				c.x+=WIDTH;
				c.y-=WIDTH;
				d.y-=2*WIDTH;
				break;
			//case 3
			case 3:
				a.x+=WIDTH;
				a.y-=WIDTH;
				c.x-=WIDTH;
				c.y-=WIDTH;
				d.x-=2*WIDTH;
				break;
			//case 4
			}
			break;
		//case s
		case "t":
			switch(rot) {
			case 0:
				d.x = a.x;
				d.y = a.y;
				a.y = c.y = b.y;
				a.x -= WIDTH;
				c.x += WIDTH;
				break;
			//case 1
			case 1:
				d.x = a.x;
				d.y = a.y;
				a.x = c.x = b.x;
				a.y += WIDTH;
				c.y -= WIDTH;
				break;
			//case 2
			case 2:
				d.x = a.x;
				d.y = a.y;
				a.y = c.y = b.y;
				a.x += WIDTH;
				c.x -= WIDTH;
				break;
			//case 3
			case 3:
				d.x = a.x;
				d.y = a.y;
				a.x = c.x = b.x;
				a.y -= WIDTH;
				c.y += WIDTH;
				break;
			//case 4
			}
			break;
		//case t
		case "z":
			switch(rot) {
			case 0:
				a.x-=2*WIDTH;
				b.x-=WIDTH;
				b.y+=WIDTH;
				d.x+=WIDTH;
				d.y+=WIDTH;
				break;
			//case 1
			case 1:
				a.y+=2*WIDTH;
				b.x+=WIDTH;
				b.y+=WIDTH;
				d.x+=WIDTH;
				d.y-=WIDTH;
				break;
			//case 2
			case 2:
				a.x+=2*WIDTH;
				b.x+=WIDTH;
				b.y-=WIDTH;
				d.x-=WIDTH;
				d.y-=WIDTH;
				break;
			//case 3
			case 3:
				a.y-=2*WIDTH;
				b.x-=WIDTH;
				b.y-=WIDTH;
				d.x-=WIDTH;
				d.y+=WIDTH;
				break;
			//case 4
			}
			break;
		//case z
		}
		
		
	}
	public void RotateRight(int [][] MESH) {
		if(rot == 3) rot = 0;
		else rot++;
		
		switch(type) {
		case "i":
			switch(rot) {
			case 0:
				a.x -= WIDTH;
				c.x += WIDTH;
				d.x += 2*WIDTH;
				a.y += 2*WIDTH;
				b.y += WIDTH;
				d.y -= WIDTH;
				break;
			//case 1
			case 1:
				a.y += WIDTH;
				c.y -= WIDTH;
				d.y -= 2*WIDTH;
				a.x += 2*WIDTH;
				b.x += WIDTH;
				//c.x doesn`t change
				d.x -= WIDTH;
				break;
			//case 2
			case 2:
				a.x += WIDTH;
				c.x -= WIDTH;
				d.x -= 2*WIDTH;
				a.y -= 2*WIDTH;
				b.y -= WIDTH;
				d.y += WIDTH;
				break;
			//case 3
			case 3:
				a.y -= WIDTH;
				c.y += WIDTH;
				d.y += 2*WIDTH;
				a.x -= 2*WIDTH;
				b.x -= WIDTH;
				//c.x doesn`t change
				d.x += WIDTH;
				break;
			//case 4
			}
			break;
		//case i
		case "l": // block 'b' doesn`t change from now
			switch(rot) {
			case 0:
				d.x += 2*WIDTH;
				c.x += WIDTH;
				a.x -= WIDTH;
				a.y += WIDTH;
				c.y -= WIDTH;
				break;
			//case 1
			case 1:
				d.y -= 2*WIDTH;
				c.y -= WIDTH;
				c.x -= WIDTH;
				a.x += WIDTH;
				a.y += WIDTH;
				break;
			//case 2
			case 2:
				d.x -= 2*WIDTH;
				c.x -= WIDTH;
				a.x += WIDTH;
				a.y -= WIDTH;
				c.y += WIDTH;
				break;
			//case 3
			case 3:
				d.y += 2*WIDTH;
				c.y += WIDTH;
				c.x += WIDTH;
				a.x -= WIDTH;
				a.y -= WIDTH;
				break;
			//case 4
			}
			break;
		//case l
		case "j":
			switch(rot) {
			case 0:
				a.y += 2*WIDTH;
				b.y += WIDTH;
				d.y -= WIDTH;
				b.x -= WIDTH;
				d.x += WIDTH;
				break;
			//case 1
			case 1:
				a.x += 2*WIDTH;
				b.x += WIDTH;
				d.x -= WIDTH;
				d.y -= WIDTH;
				b.y += WIDTH;
				break;
			//case 2
			case 2:
				a.y -= 2*WIDTH;
				b.y -= WIDTH;
				d.y += WIDTH;
				b.x += WIDTH;
				d.x -= WIDTH;
				break;
			//case 3
			case 3:
				a.x -= 2*WIDTH;
				b.x -= WIDTH;
				d.x += WIDTH;
				d.y += WIDTH;
				b.y -= WIDTH;
				break;
			//case 4
			}
			break;
		//case j
		case "o":
			//Why do I even need to rotate it?)
			break;
		//case o
		case "s":
			switch(rot) {
			case 0:
				a.x-=WIDTH;
				a.y+=WIDTH;
				c.x+=WIDTH;
				c.y+=WIDTH;
				d.x+=2*WIDTH;
				break;
			//case 1
			case 1:
				a.x+=WIDTH;
				a.y+=WIDTH;
				c.x+=WIDTH;
				c.y-=WIDTH;
				d.y-=2*WIDTH;
				break;
			//case 2
			case 2:
				a.x+=WIDTH;
				a.y-=WIDTH;
				c.x-=WIDTH;
				c.y-=WIDTH;
				d.x-=2*WIDTH;
				break;
			//case 3
			case 3:
				a.x-=WIDTH;
				a.y-=WIDTH;
				c.x-=WIDTH;
				c.y+=WIDTH;
				d.y+=2*WIDTH;
				break;
			//case 4
			}
			break;
		//case s
		case "t":
			switch(rot) {
			case 0:
				c.x+=WIDTH;
				c.y-=WIDTH;
				d.y+=WIDTH;
				d.x+=WIDTH;
				a.x-=WIDTH;
				a.y+=WIDTH;
				break;
			//case 1
			case 1:
				c.x-=WIDTH;
				c.y-=WIDTH;
				a.x+=WIDTH;
				a.y+=WIDTH;
				d.x+=WIDTH;
				d.y-=WIDTH;
				break;
			//case 2
			case 2:
				c.x-=WIDTH;
				c.y+=WIDTH;
				d.y-=WIDTH;
				d.x-=WIDTH;
				a.x+=WIDTH;
				a.y-=WIDTH;
				break;
			//case 3
			case 3:
				c.x+=WIDTH;
				c.y+=WIDTH;
				a.x-=WIDTH;
				a.y-=WIDTH;
				d.x-=WIDTH;
				d.y+=WIDTH;
				break;
			//case 4
			}
			break;
		//case t
		case "z":
			switch(rot) {
			case 0:
				a.y+=2*WIDTH;
				b.x+=WIDTH;
				b.y+=WIDTH;
				d.x+=WIDTH;
				d.y-=WIDTH;
				break;
			//case 1
			case 1:
				a.x+=2*WIDTH;
				b.x+=WIDTH;
				b.y-=WIDTH;
				d.x-=WIDTH;
				d.y-=WIDTH;
				break;
			//case 2
			case 2:
				a.y-=2*WIDTH;
				b.x-=WIDTH;
				b.y-=WIDTH;
				d.x-=WIDTH;
				d.y+=WIDTH;
				break;
			//case 3
			case 3:
				a.x-=2*WIDTH;
				b.x-=WIDTH;
				b.y+=WIDTH;
				d.x+=WIDTH;
				d.y+=WIDTH;
				break;
			//case 4
			}
			break;
		//case z
		}
		
	}
	public void UnProject(int [][] MESH) {
		if(e.x != 0) {//If block is projected
			//Unprojecting block
			MESH[(int) ((e.x-XMIN)/WIDTH)][(int) ((e.y-YMIN)/WIDTH)] = 0;
			MESH[(int) ((f.x-XMIN)/WIDTH)][(int) ((f.y-YMIN)/WIDTH)] = 0;
			MESH[(int) ((g.x-XMIN)/WIDTH)][(int) ((g.y-YMIN)/WIDTH)] = 0;
			MESH[(int) ((h.x-XMIN)/WIDTH)][(int) ((h.y-YMIN)/WIDTH)] = 0;
		}
	}
	public void Project(int [][] MESH) {
		UnProject(MESH);
		e.x = a.x;
		f.x = b.x;
		g.x = c.x;
		h.x = d.x;
		float i,j,k,l;
		for(i = a.y, j = b.y, k = c.y, l = d.y; 
				i >= YMIN && j >= YMIN && k >= YMIN && l >= YMIN;
				i -= WIDTH, j -= WIDTH, k -= WIDTH, l -= WIDTH) {
			if(i == YMIN || j == YMIN || k == YMIN || l == YMIN 
				|| MESH[(int) ((e.x-XMIN)/WIDTH)][(int) ((i - WIDTH - YMIN)/WIDTH)] != 0
				|| MESH[(int) ((f.x-XMIN)/WIDTH)][(int) ((j - WIDTH - YMIN)/WIDTH)] != 0
				|| MESH[(int) ((g.x-XMIN)/WIDTH)][(int) ((k - WIDTH - YMIN)/WIDTH)] != 0
				|| MESH[(int) ((h.x-XMIN)/WIDTH)][(int) ((l - WIDTH - YMIN)/WIDTH)] != 0) {
				e.y = i;
				f.y = j;
				g.y = k;
				h.y = l;
				break;
			}
		}
		
		//Projecting block on mesh
		MESH[(int) ((e.x-XMIN)/WIDTH)][(int) ((e.y-YMIN)/WIDTH)] = 10;
		MESH[(int) ((f.x-XMIN)/WIDTH)][(int) ((f.y-YMIN)/WIDTH)] = 10;
		MESH[(int) ((g.x-XMIN)/WIDTH)][(int) ((g.y-YMIN)/WIDTH)] = 10;
		MESH[(int) ((h.x-XMIN)/WIDTH)][(int) ((h.y-YMIN)/WIDTH)] = 10;
		
	}




















}
