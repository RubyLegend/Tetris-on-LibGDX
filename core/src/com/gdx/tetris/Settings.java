package com.gdx.tetris;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
import java.util.Scanner; // Import the Scanner class to read text files
import com.badlogic.gdx.Input.Keys;

public class Settings {
	//Settings for the game
	int RotLeft;
	int RotRight;
	int MoveLeft;
	int MoveRight;
	int MoveDown;
	int Drop;
	int Hold;
	int Pause;
	//-----------------------
	static public int WIDTH = 40; //Width of a block
	static public int ROWS = 23; //Number of rows
	static public int COLS = 10; //Number of cols
	static public int XMIN = 640; //|Position
	static public int YMIN = 75;  //|of map
	//Last five settings only changes now inside code
	public Settings() {
		try {
	        File settings = new File("settings.txt");
	        if (settings.createNewFile()) {
	            System.out.println("Settings: File created.");
	            FileWriter myWriter = new FileWriter(settings.getAbsolutePath());
	            myWriter.write("RotLeft: 54\n"
	            			 + "RotRight: 52\n"
	            		     + "MoveLeft: 29\n"
	            			 + "MoveRight: 32\n"
	            		     + "MoveDown: 47\n"
	            			 + "Drop: 62\n"
	            		     + "Hold: 59\n"
	            			 + "Pause: 111\n"); //Writing default settings
	            RotLeft = Keys.Z;
	            RotRight = Keys.X;
	            MoveLeft = Keys.A;
	            MoveRight = Keys.D;
	            MoveDown = Keys.S;
	            Drop = Keys.SPACE;
	            Hold = Keys.SHIFT_LEFT;
	            Pause = Keys.ESCAPE;
	            myWriter.close();
	          } else {
	            System.out.println("Settings: File already exists. " + settings.getAbsolutePath());
	            Scanner myReader = new Scanner(settings);
	            while (myReader.hasNextLine()) {
	              String data = myReader.nextLine();
	              int i = 0;
	              while(data.charAt(i) != ':') i++;
	              i+=2; //To skip space between double colon and keyCode
	              int keyCode = 0;
	              for(; i < data.length(); i++) {
	            	  keyCode*=10;
	            	  keyCode+=Character.getNumericValue(data.charAt(i));
	              }
	              if(data.indexOf("RotLeft: ") != -1) {
	            	  RotLeft = keyCode;
	              }
	              else if(data.indexOf("RotRight: ") != -1) {
	            	  RotRight = keyCode;
	              }
	              else if(data.indexOf("MoveLeft: ") != -1) {
	            	  MoveLeft = keyCode;
	              }
	              else if(data.indexOf("MoveRight: ") != -1) {
	            	  MoveRight = keyCode;
	              }
	              else if(data.indexOf("MoveDown: ") != -1) {
	            	  MoveDown = keyCode;
	              }
	              else if(data.indexOf("Drop: ") != -1) {
	            	  Drop = keyCode;
	              }
	              else if(data.indexOf("Hold: ") != -1) {
	            	  Hold = keyCode;
	              }
	              else if(data.indexOf("Pause: ") != -1) {
	            	  Pause = keyCode;
	              }
	              System.out.println(data);
	            }
	            myReader.close();
	          }
	    } catch (IOException e) {
	          System.out.println("Settings: An error occurred.");
	          e.printStackTrace();
	    }
	}
	
	public void SaveSettings() {
		try {
	        File settings = new File("settings.txt");
	        settings.createNewFile();
	        FileWriter myWriter = new FileWriter(settings.getAbsolutePath());
            myWriter.write("RotLeft: " + RotLeft
            			 + "\nRotRight: " + RotRight
            		     + "\nMoveLeft: " + MoveLeft
            			 + "\nMoveRight: " + MoveRight
            		     + "\nMoveDown: " + MoveDown
            			 + "\nDrop: " + Drop
            		     + "\nHold: " + Hold
            			 + "\nPause: " + Pause + '\n'); //Writing default settings
            myWriter.close();
	    } catch (IOException e) {
	          System.out.println("Settings: An error occurred.");
	          e.printStackTrace();
	    }
	}
	
}
