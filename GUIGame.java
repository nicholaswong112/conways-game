import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIGame {
	
	static final int ROWS = 50;
	static final int COLUMNS = 50;
	
	static boolean[][] now = new boolean[ROWS + 2][COLUMNS + 2];
	boolean[][] next = new boolean[ROWS + 2][COLUMNS + 2];
	JFrame frame;
	JPanel grid;
	ActionListener listener;
	static JButton start;
	static JButton[][] board = new JButton[ROWS][COLUMNS];
	static boolean startGame = false;
	
	public GUIGame(){
		initGrids();
		setUpGUI();
		
		while(!startGame){System.out.println("");} //stall until Start is pressed
		
		while(true){
			try{
				Thread.sleep(200);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			iteration();
		}
	}

	//  clears both boolean grids
	public void initGrids(){
		//set all spaces to false or "dead"
		for(int r = 0; r < now.length; r++){
			for(int c = 0; c < now[r].length; c++){
				now[r][c] = false;
				next[r][c] = false;
			}
		}
	}
	
	//  places buttons in panel and panel and button in frame. sets the essentials and visible = true
	public void setUpGUI(){
		frame = new JFrame();
		grid = new JPanel();
		listener = new Listener();
		start = new JButton("Start");
		frame.setLayout(new BorderLayout(2,2));
		grid.setLayout(new GridLayout(ROWS,COLUMNS));
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[r].length; c++){
				board[r][c] = new JButton();
				board[r][c].setBackground(Color.BLACK);
				board[r][c].addActionListener(listener);
				grid.add(board[r][c]);
			}	
		}
		start.addActionListener(listener);
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("The Game of Life - Conway");
		frame.add(grid, BorderLayout.CENTER);
		frame.add(start, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	

	/* ******************************************************************
	 * From here to next large comment are methods for each iteration :
	 * iteration, updateGUI, rises, dies, numAroundAlive, clearNext
	 */
	
	public void iteration(){
		for(int r = 1; r < now.length - 1; r++){
			for(int c = 1; c < now[r].length - 1; c++){
				if(now[r][c]){
					next[r][c] = !dies(r,c);
				} else {
					next[r][c] = rises(r,c);
				}
			}
		}
		updateGUI();
		//now = next;
		for(int r = 1; r < now.length - 1; r++){
			for(int c = 1; c < now[r].length - 1; c++){
				now[r][c] = next[r][c];
			}
		}
		clearNext();
	}

	public void updateGUI(){
		for(int r = 1; r < next.length - 1; r++){
			for(int c = 1; c < next[r].length - 1; c++){
				Color color = next[r][c] ? Color.GREEN : Color.BLACK;
				board[r - 1][c - 1].setBackground(color);
			}
		}
	}
	
	public boolean dies(int r, int c){
		return !(numAroundAlive(r,c) <= 3 && numAroundAlive(r,c) >= 2) ;
	}
	
	public boolean rises(int r, int c){
		return numAroundAlive(r,c) == 3;
	}
	
	public int numAroundAlive(int r, int c){
		int sum = 0;
		if(now[r-1][c-1]) sum++;
		if(now[r-1][c]) sum++;
		if(now[r-1][c+1]) sum++;
		if(now[r][c-1]) sum++;
		if(now[r][c+1]) sum++;
		if(now[r+1][c-1]) sum++;
		if(now[r+1][c]) sum++;
		if(now[r+1][c+1]) sum++;
		return sum;
	}
	
	public void clearNext(){
		for(int r = 0; r < next.length; r++){
			for(int c = 0; c < next[r].length; c++){
				next[r][c] = false;
			}
		}
	}
	
	/*
	 * ********************************************************** 
	 */
	
	
	public static void main(String[] args){
		new GUIGame();
	}
	
	
}
