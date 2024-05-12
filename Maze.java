package grade_12.Bonus;

/* @Program Name: Maze.java
 * @Author: Talhah Abid
 * @Date 27/13/2022
 * @Description: Program gets a maze from file and solves it
 */
import java.io.*;
import java.util.*;

public class Maze {

	// variables
	private String checkpoint;
	private String endpoint;
	private String fileName;
	private ArrayList<String> visited = new ArrayList<String>();
	private char[][] maze;

	/* Constructor that */
	public Maze(String fileName) {
		this.fileName = fileName;

		readFile();
		printMaze();

		this.endpoint = getEndPoint();
		check(Character.getNumericValue(getStartPoint().charAt(0)),
				Character.getNumericValue(getStartPoint().charAt(2)));

		System.out.println("\n\n");
		printMaze();
	}

	/** Method that reads file */
	public void readFile() {

		// tries code
		try {

			// reads file and stores it to info
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			ArrayList<String> info = new ArrayList<String>();
			String read = file.readLine();

			while (read != null) {
				info.add(read);
				read = file.readLine();

			}
			// makes a 2d array from the first 2 lines of the file
			char[][] grid = new char[Integer.parseInt(info.get(0))][Integer.parseInt(info.get(1))];

			// stores each char in an element of the 2d array
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {

					grid[i][j] = info.get(i + 2).charAt(j);
				}
			}
			// closes file and sets maze to grid
			file.close();
			this.maze = grid;

			// catches error
		} catch (Exception e) {
			System.out.println("No work");
			e.printStackTrace();
		}

	}

	/** Method that checks if a spot is within boundaries and if it = 'O' or 'E' */
	public boolean goodSpot(int y, int x) {
		if (y >= 0 && y < maze.length && x >= 0 && x < maze[0].length) {
			if (maze[y][x] == 'O' || maze[y][x] == 'E') {
				return true;
			}

		}
		return false;
	}

	/** Method that prints maze */
	public void printMaze() {

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				System.out.print("[" + maze[i][j] + "]");

			}
			System.out.println();
		}
	}

	/**
	 * Method that uses recursion and checks if it can go a path. if not retraces it
	 * steps and goes another way Continues until it solves the maze
	 */
	public void check(int y, int x) {

		// checks if it has been solved
		if (maze[Character.getNumericValue(endpoint.charAt(0))][Character.getNumericValue(endpoint.charAt(2))] == '-') {
			maze[Character.getNumericValue(endpoint.charAt(0))][Character.getNumericValue(endpoint.charAt(2))] = 'E';
			return;
		}

		// looks for checkpoint
		if (goodSpot(y, x - 1) == true && goodSpot(y, x + 1) == true // left right
				|| goodSpot(y + 1, x) == true && goodSpot(y, x + 1) == true // down right
				|| goodSpot(y - 1, x) == true && goodSpot(y + 1, x) == true // up down
				|| goodSpot(y + 1, x) == true && goodSpot(y, x - 1) == true // down left
				|| goodSpot(y - 1, x) == true && goodSpot(y, x + 1) == true // up right
				|| goodSpot(y - 1, x) == true && goodSpot(y, x - 1) == true) { // up left
			checkpoint = y + " " + x;

		}

		/**
		 * Looks for an 'O' in each direction. if true, puts a - and adds that place to
		 * visited
		 */
		if (goodSpot(y + 1, x) == true) {
			maze[y + 1][x] = '-';
			visited.add((y + 1) + " " + x);
			check(y + 1, x);

		} else if (goodSpot(y - 1, x) == true) {
			maze[y - 1][x] = '-';
			visited.add((y - 1) + " " + x);
			check(y - 1, x);

		} else if (goodSpot(y, x + 1) == true) {
			maze[y][x + 1] = '-';
			visited.add(y + " " + (x + 1));
			check(y, x + 1);

		} else if (goodSpot(y, x - 1) == true) {
			maze[y][x - 1] = '-';
			visited.add(y + " " + (x - 1));
			check(y, x - 1);

			// else if it gets stuck, retraces its step until the previous checkpoint and
			// sets the path it took before to Xs
			// repeats method from chckponmt
		} else {

			for (int i = visited.size() - 1; i >= 0; i--) {
				if (!visited.get(visited.size() - 1).equalsIgnoreCase(checkpoint)) {

					maze[Character.getNumericValue(visited.get(visited.size() - 1).charAt(0))][Character
							.getNumericValue(visited.get(visited.size() - 1).charAt(2))] = 'X';
					visited.remove(visited.size() - 1);

				}

			}
			check(Character.getNumericValue(checkpoint.charAt(0)), Character.getNumericValue(checkpoint.charAt(2)));
		}

	}

	public String getStartPoint() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 'S') {
					return (i + " " + j);
				}
			}
		}
		return null;
	}

	public String getEndPoint() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 'E') {
					return (i + " " + j);
				}
			}
		}
		return null;
	}
}
