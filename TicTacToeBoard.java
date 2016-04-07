package com.example.beans;

public class TicTacToeBoard {
	
	int[][] positions;
	String status;
	String p1;
	String p2;
	public int[][] getPositions() {
		return positions;
	}
	public void setPositions(int[][] positions) {
		this.positions = positions;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public TicTacToeBoard() {
		positions = new int[3][3];
		int rowLength = positions.length;
		int colLength = positions[0].length;
		for (int i=0; i< rowLength; i++){
			for (int j=0; j< colLength; j++){
				positions[i][j]=0;
			}
		}
		status = "NOTUSED";
	}
	public String startNewGame(String p1, String p2){
		if ("NOTUSED".equals(status)){
			status="USED";
			this.p1=p1;
			this.p2=p2;
			resetBoard();
		}
		return "Board is not available";
	}
	private void resetBoard(){
		for (int i=0; i< positions.length; i++){
			for (int j=0; j< positions[0].length; j++){
				positions[i][j]=0;
			}
		}
	}
	public String playerMove(int playerNum, int posY, int posX){
		if ("NOTUSED".equals(status)){
			return "Game has not started";
		}
		if (positions[posY][posX] == 0){
			positions[posY][posX] = playerNum;
			System.out.println("Player "+playerNum+" Succesfully placed on position: x="+posX + ", y="+posY);
			print();
			int result = checkWinBruteForce();
			if (result ==1){
				System.out.println("Player 1 has won the game");
				status="NOTUSED";
			}else if (result ==2){
				System.out.println("Player 2 has won the game");
				status="NOTUSED";
			}else if (result ==0){
				System.out.println("Game is tied");
				status="NOTUSED";
			}
			return "SUCCESS";
		}else if (positions[posY][posX] == playerNum){
			System.out.println("Player "+playerNum+" has already placed on position: x="+posX + ", y="+posY);
			return "YOU ALREADY PLACED ON THAT POSITION";
		}else if (positions[posY][posX] ==2 || positions[posY][posX] ==1){
			int otherPlayer = playerNum==1? 2:1;
			System.out.println("Player "+otherPlayer+" has already placed on position: x="+posX + ", y="+posY);
			return "OTHER PLAYER ALREADY ON THE POSITION";
		}
		System.out.println("Invalid Move Attemped by Player "+playerNum+" for position: x="+posX + ", y="+posY);
		return "INVALID MOVE";
	}
	public void print(){
		int rowLength=positions.length;
		int colLength=positions[0].length;
		System.out.println("Board Status:");
		for (int row=0; row<rowLength; row++){
			System.out.print("\t");
			for (int col=0; col<colLength; col++){
				System.out.print(positions[row][col]);
			}
			System.out.print("\n");
		}
	};
	
	public int checkWinBruteForce(){
		int[][] winningPositions = new int[][]{{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{3,5,7}};
		//check if p1 won
		for (int i=0; i< winningPositions.length; i++){
			int[] winPositionArray=winningPositions[i];
			boolean p1Won = true;
			boolean p2Won = true;
			for (int j=0; j<3; j++){
				//check if p1 has all matching
				int[] rowAndCol = getRowAndCol(winPositionArray[j]);
				if (positions[rowAndCol[0]][rowAndCol[1]] !=1){
					p1Won = false;
				}
				if (positions[rowAndCol[0]][rowAndCol[1]] !=2){
					p2Won = false;
				}
			}
			if (p1Won ==true) return 1;
			if (p2Won == true) return 2;
		}
		boolean tie=true;
		for (int i=0; i< positions.length; i++){
			for (int j=0; j< positions[0].length; j++){
				if (positions[i][j]==0){
					tie=false;
				}
			}
		}
		if (tie==true) return 0;
		return -1;

	}
	private int[] getRowAndCol(int linearPos){
		int[] rowAndCol = new int[2];
		rowAndCol[0] = linearPos / 3;
		rowAndCol[1] = linearPos % 3;
		return rowAndCol;
	}
	public static void main(String args[]){
		TicTacToeBoard board = new TicTacToeBoard();
		board.startNewGame("name1", "name2");
		board.print();
		board.playerMove(1, 1, 1);
		
		board.playerMove(2,0,0);
		board.playerMove(1, 2, 0);
		board.playerMove(2, 0, 2);
		board.playerMove(1, 2, 2);
		board.playerMove(2, 0, 1); //player two should have won by this and game would have ended
		board.startNewGame("name1", "name2");
		board.print();
		
		
	}
	

}
