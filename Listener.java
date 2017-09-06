import java.awt.*;
import java.awt.event.*;

public class Listener implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(GUIGame.start)){
			GUIGame.startGame = true;
		} else {
			int row = 0, column = 0;
			
			out:
			    for(row = 0; row < GUIGame.board.length; row++){
			        for(column = 0; column < GUIGame.board[row].length; column++){
			            if(e.getSource().equals(GUIGame.board[row][column])) break out;
			        }
			    }

			toggle(row, column);
		}
	}
	
	public void toggle(int r, int c){
		//changing GUIGame.now boolean array
		GUIGame.now[r + 1][c + 1] = !GUIGame.now[r + 1][c + 1];
		
		//changing the color on the grid
		if(GUIGame.board[r][c].getBackground().equals(Color.GREEN)){
			GUIGame.board[r][c].setBackground(Color.BLACK);
		}else{
			GUIGame.board[r][c].setBackground(Color.GREEN);
		}
	}

}
