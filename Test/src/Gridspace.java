//N,S,E,W
public class Gridspace {

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
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
