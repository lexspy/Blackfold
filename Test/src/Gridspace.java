//N,S,E,W
public class Gridspace {
	private int xSize = 5, ySize = 5, rooms = 11;
	private node[][] space;
	
	private class node{
		private boolean isRoom;

		private node[] around = new node[4];
		
		public node(node[] around){
			for(int i=0;i<4;i++){
				this.around[i] = around[i];
			}
		}
		
		public node(){
			for(int i=0;i<4;i++){
				this.around[i] = null;
			}
		}

		public boolean isRoom() {
			return isRoom;
		}

		public void setRoom(boolean isRoom) {
			this.isRoom = isRoom;
		}

		public node[] getAround() {
			return around;
		}

		public void setAround(node[] around) {
			this.around = around;
		}
		
		public void setNorth(node Node){
			this.around[0] = Node;
		}
		public void setSouth(node Node){
			this.around[1] = Node;
		}
		public void setEast(node Node){
			this.around[2] = Node;
		}
		public void setWest(node Node){
			this.around[3] = Node;
		}
	}
	
	public Gridspace(){
		int roomCount = rooms;
		space = new node[xSize][ySize];
		space[xSize/2][0].setRoom(true); 
		rooms--;
		
	}
	
	private void surroundings(int x, int y){
		if(x>0)
			space[x-1][y].setEast(space[x][y]);
		if(x<xSize-1)
			space[x+1][y].setWest(space[x][y]);
		if(y>0)
			space[x][y-1].setSouth(space[x][y]);
		if(y<ySize-1)
			space[x][y+1].setNorth(space[x][y]);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
