package da;

import javax.swing.JButton;

public class CustomButtons extends JButton{
	 	private int coordX;
	    private int coordY;

	    public CustomButtons(int coordX, int coordY) {
	    	 	super("");
		        this.coordX = coordX;
		        this.coordY = coordY;
			    super.setContentAreaFilled(false);
	    }

	   
	    public int getCoordX() {
	        return coordX;
	    }

	    public int getCoordY() {
	        return coordY;
	    }
}
