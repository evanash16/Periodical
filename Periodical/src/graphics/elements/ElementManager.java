package graphics.elements;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import graphics.GUI;

public class ElementManager {

	public static Element[][] elements; 
	private static int gridWidth, gridHeight, width, height, level = 1;
	private static boolean levelUp;
	
	public ElementManager(int gridWidth, int gridHeight, int width, int height){
		
		ElementManager.gridWidth = gridWidth;
		ElementManager.gridHeight = gridHeight;
		ElementManager.width = width;
		ElementManager.height = height;
		
		elements = new Element[gridWidth][gridHeight];
		
		for(Element[] e2: elements){
			
			for(Element e: e2){
				
				e = null;
			}
		}
	}
	
	public void generate(){
		
		switch(level){
		
		case 1:
			
			addElement("Begino");
			break;
		case 2:
			
			addElement("Forme");
		}
	}
	
	public static void addElement(String type) {
		
		if(!levelUp){
			
			for(int j = 0; j < elements[0].length; j++){

				for(int i = 0; i < elements.length; i++){
						
					if(elements[i][j] == null || !elements[i][j].isVisible()){
						
						switch(type){
						
						case "Begino":
							
							elements[i][j] = new Begino((GUI.screensize.width - (gridWidth * width)) / 2 + (i * width), (GUI.screensize.height - (gridHeight * height)) / 2 + (j * height), width, height);
							break;
						case "Forme":
							
							elements[i][j] = new Forme((GUI.screensize.width - (gridWidth * width)) / 2 + (i * width), (GUI.screensize.height - (gridHeight * height)) / 2 + (j * height), width, height);
							break;
						case "Choleo":
							
							elements[i][j] = new Choleo((GUI.screensize.width - (gridWidth * width)) / 2 + (i * width), (GUI.screensize.height - (gridHeight * height)) / 2 + (j * height), width, height);
							break;
							
						case "Materas":
								
							elements[i][j] = new Materas((GUI.screensize.width - (gridWidth * width)) / 2 + (i * width), (GUI.screensize.height - (gridHeight * height)) / 2 + (j * height), width, height);
							break;
						case "Reydas":
							
							elements[i][j] = new Reydas((GUI.screensize.width - (gridWidth * width)) / 2 + (i * width), (GUI.screensize.height - (gridHeight * height)) / 2 + (j * height), width, height);
							break;
						}
						return;
					}
				}
			}
		}
	}
	
	public void checkRecipe(){
		
		Scanner scan = null;
		
		try{
			String filePath = getClass().getResource("").getPath().substring(0, getClass().getResource("").getPath().indexOf("bin")) + "src/Files/Recipes";
			scan = new Scanner(new File(filePath));
		} catch(FileNotFoundException e) {System.out.println("File not found");}
		
		ArrayList<String> tempIng = new ArrayList<String>();
		ArrayList<Point> selected = new ArrayList<Point>();
		
		for(int i = 0; i < elements.length; i++){
			
			for(int j = 0; j < elements[0].length; j++){
				
				Element e = elements[i][j];
				
				if(e != null && e.isSelected()){
					
					tempIng.add(e.getType());
					selected.add(new Point(i, j));
				}
			}
		}
		
		String[] ing = new String[tempIng.size()];
		
		for(int i = 0; i < tempIng.size(); i++){
			
			ing[i] = tempIng.get(i);
		}
		
		Arrays.sort(ing);
		
		while(scan.hasNextLine()){
			
			String line = scan.nextLine();
			
			String[] recipe = line.split(":")[0].split(" ");
			String[] output = line.split(":")[1].split(" ");
			
			Arrays.sort(recipe);
			
			if(ing.length == recipe.length){
				
				for(int i = 0; i < ing.length; i++){
					
					if(!ing[i].equals(recipe[i]) || 
							(line.split(":").length > 2 && Integer.parseInt(line.split(":")[2]) > level) || 
							(line.split(":").length > 3) && (Integer.parseInt(line.split(":")[2]) > level || Integer.parseInt(line.split(":")[3]) < level)){

						break;
					}
					
					if(i == ing.length - 1){
						
						for(Point p: selected){
							
							elements[p.x][p.y].close();
						}
						
						for(String s: output){
							
							if(s.equals("Grow")){

								grow();
							}
							
							else{
								
								addElement(s);
							}
						}
						
						return;
					}
				}
			}
		}
	}
	
	private void closeAll(){
		
		for(int i = 0; i < elements.length; i++){
			
			for(int j = 0; j < elements[0].length; j++){
				
				Element e = elements[i][j];
				
				if(e != null){
					
					elements[i][j].close();
				}
			}
		}
	}
	
	private void grow(){
		
		levelUp = true;
		closeAll();
	}
	
	public void update(){
		
		for(int i = 0; i < elements.length; i++){
			
			for(int j = 0; j < elements[0].length; j++){
				
				Element e = elements[i][j];
				
				if(e != null){
					
					e.update();
					
					if(!e.isVisible()){
						
						elements[i][j] = null;
						
						if(levelUp){
							
							ElementManager.gridWidth += 1;
							ElementManager.gridHeight += 1;
							
							elements = new Element[gridWidth][gridHeight];
							
							levelUp = false;
							level++;
						}
					}
				}
			}
		}
	}
	
	public void draw(Graphics g){
		
		ArrayList<Element> selected = new ArrayList<Element>();
		
		for(Element[] e2: elements){
			
			for(Element e: e2){
				
				if(e != null && !e.isSelected()){
					
					e.draw(g);
				}
				
				else if(e != null && e.isSelected()){
					
					selected.add(e);
				}
			}
		}
		
		for(Element e: selected){
			
			e.draw(g);
		}
	}
	
	public Element getTooltip(int x, int y){
		
		for(Element[] e2: elements){
			
			for(Element e: e2){
				
				if(e != null && x > e.getX() && y > e.getY() && x < e.getX() + e.getWidth() && y < e.getY() + e.getHeight()){
					
					return e;
				}
			}
		}
		
		return null;
	}
	
	public boolean click(MouseEvent arg0){
		
		for(Element[] e2: elements){
			
			for(Element e: e2){
				
				if(e != null){
					
					if(arg0.getX() > e.getX() && arg0.getY() > e.getY() && arg0.getX() < e.getX() + e.getWidth() && arg0.getY() < e.getY() + e.getHeight()){
						
						e.click();
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
