import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{
	private int maxX = 12;
	private int maxY = 12;
	
	private class Tile{
		//true === open; false === wall
		private Boolean North;
		private Boolean South;
		private Boolean East;
		private Boolean West;
		private Boolean isRoom;
		private int Light = 0;
		private Tile[][] Around = new Tile[3][3]; 
		
		public Tile(Boolean n, Boolean s, Boolean e, Boolean w){
			North = n;
			South = s;
			East = e;
			West = w;
		}
		public Tile(){
			this(false,false,false,false);
		}
		
		public Boolean getNorth(){
			return this.North;
		}
		public Boolean getSouth(){
			return this.South;
		}
		public Boolean getWest(){
			return this.West;
		}
		public Boolean getEast(){
			return this.East;
		}
		
		
		public void setNorth(Boolean north) {
			North = north;
		}
		public void setSouth(Boolean south) {
			South = south;
		}
		public void setEast(Boolean east) {
			East = east;
		}
		public void setWest(Boolean west) {
			West = west;
		}
		
		public int getLight(){
			return this.Light;
		}
		
		//N === 8, S === 2, E === 6, W == 4 dir === Where
		public void lightTravel(int dir, int light){
			if(light>Light){
				this.Light = light;
				switch(dir){
					case 8:
						if(this.South){
							this.Around[1][2].lightTravel(dir,light-1);
						}
						if(this.East){
							this.Around[2][1].lightTravel(4,light/2);
						}
						if(this.West){
							this.Around[0][1].lightTravel(6,light/2);
						}
						break;
					case 2:
						if(this.North){
							this.Around[1][0].lightTravel(dir,light-1);
						}
						if(this.East){
							this.Around[2][1].lightTravel(4,light/2);
						}
						if(this.West){
							this.Around[0][1].lightTravel(6,light/2);
						}
						break;
					case 6:
						if(this.West){
							this.Around[0][1].lightTravel(dir,light-1);
						}
						if(this.North){
							this.Around[1][0].lightTravel(2,light/2);
						}
						if(this.South){
							this.Around[1][2].lightTravel(8,light/2);
						}
						break;
					case 4:
						if(this.East){
							this.Around[2][1].lightTravel(dir,light-1);
						}
						if(this.North){
							this.Around[1][0].lightTravel(2,light/2);
						}
						if(this.South){
							this.Around[1][2].lightTravel(8,light/2);
						}
						break;	
				}		
			}	
		}
		
		public void openDoor(int dir){
			switch(dir){
				case 8:
					if(!this.North){
						this.North = true;
						this.Around[0][1].openDoor(2);
					}
					break;
				case 2:
					if(!this.South){
						this.South = true;
						this.Around[2][1].openDoor(8);
					}
					break;
				case 4:
					if(!this.West){
						this.West = true;
						this.Around[1][0].openDoor(6);
					}
					break;
				case 6:
					if(!this.East){
						this.East = true;
						this.Around[1][2].openDoor(4);
					}
					break;
			}
		}
		
		public void setAround(Tile north, Tile south, Tile east, Tile west){
			this.Around[1][0] = north;
			this.Around[1][2] = south;
			this.Around[0][1] = west;
			this.Around[2][1] = east;
			this.setNorth(north.getSouth());
			this.setSouth(south.getNorth());
			this.setEast(east.getWest());
			this.setWest(west.getEast());
			
		}
	}

	private class Map{
		private Tile[][] tiles = new Tile[maxX][maxY] ;
		public Map(){
			//left side
			for(int lSide=0;lSide<12;lSide++){
				tiles[0][lSide].setWest(true);
			}
			//right side
			for(int rSide=0;rSide<12;rSide++){
				tiles[11][rSide].setEast(true);
			}
			//top side
			for(int tSide=0;tSide<12;tSide++){
				tiles[tSide][0].setNorth(true);
			}
			//bottom side
			for(int bSide=0;bSide<12;bSide++){
				tiles[bSide][0].setSouth(true);
			}
			
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int offset = 10;
		int step = 50;
		g.setColor(Color.BLACK);
		g.drawRect(offset, offset, 600, 600);
		g.fillOval(offset, offset, 45, 45);
		g.drawLine(offset+step, offset + step, offset+2*step, offset+step);
		g.drawLine(offset+3*step, offset + step, offset+4*step, offset+step);
		g.drawLine(offset+8*step, offset + step, offset+9*step, offset+step);
		g.drawLine(offset+10*step, offset + step, offset+11*step, offset+step);
		g.drawLine(offset, offset + 2*step, offset+6*step, offset+2*step);
		g.drawLine(offset+7*step, offset + 2*step, offset+12*step, offset+2*step);
		g.drawLine(offset+step, offset + 3*step, offset+3*step, offset+3*step);
		g.drawLine(offset+5*step, offset + 3*step, offset+8*step, offset+3*step);
		g.drawLine(offset+9*step, offset + 3*step, offset+11*step, offset+3*step);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		JFrame jf = new JFrame();
		
		jf.setTitle("Hide");
		jf.setSize(700,700);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(m);
		
	}

}
