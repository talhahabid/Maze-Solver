//package grade_12.Bonus;
//
//import java.io.*;
//import java.util.*;
//
//public class MazeTester {
//	static String checkpoint = "";
//	static ArrayList<String> visited = new ArrayList<String>();
//	private static char[][] maze = readFile();
//
//	public static char[][] readFile() {
//		try {
//			BufferedReader file = new BufferedReader(new FileReader("phoneBook.txt"));
//			ArrayList<String> info = new ArrayList<String>();
//			String read = file.readLine();
//
//			while (read != null) {
//				info.add(read);
//				read = file.readLine();
//
//			}
//			char[][] grid = new char[Integer.parseInt(info.get(0))][Integer.parseInt(info.get(1))];
//
//			for (int i = 0; i < grid.length; i++) {
//				for (int j = 0; j < grid[i].length; j++) {
//
//					grid[i][j] = info.get(i + 2).charAt(j);
//				}
//			}
//			file.close();
//			return grid;
//
//		} catch (Exception e) {
//			System.out.println("No work");
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//
//	public static void printMaze() {
//
//		for (int i = 0; i < maze.length; i++) {
//			for (int j = 0; j < maze[i].length; j++) {
//				System.out.print("[" + maze[i][j] + "]");
//
//			}
//			System.out.println();
//		}
//	}
//
//	public static void main(String[] args) {
//
//		printMaze();
//		System.out.println();
//		check(0, 3);
//		check(1, 3);
//		check(4, 1);
//		System.out.println(checkpoint);
//	}
//
//	public static boolean goodSpot(int y, int x) {
//		if (y >= 0 && y < maze.length && x >= 0 && x < maze[0].length) {
//			if (maze[y][x] == 'O' || maze[y][x] == 'E') {
//				return true;
//			}
//
//		}
//		return false;
//	}
//
//	public static void check(int y, int x) {
//
//		printMaze();
//		System.out.println();
//
//		if (goodSpot(y, x - 1) == true && goodSpot(y, x + 1) == true // left right
//				|| goodSpot(y + 1, x) == true && goodSpot(y, x + 1) == true // down right
//				|| goodSpot(y - 1, x) == true && goodSpot(y + 1, x) == true // up down
//				|| goodSpot(y + 1, x) == true && goodSpot(y, x - 1) == true // down left
//				|| goodSpot(y - 1, x) == true && goodSpot(y, x + 1) == true // up right
//				|| goodSpot(y - 1, x) == true && goodSpot(y, x - 1) == true) { // up left
//			checkpoint = y + " " + x;
//
//		}
//
//		if (goodSpot(y + 1, x) == true) {
//			maze[y + 1][x] = '-';
//			visited.add((y + 1) + " " + x);
//			check(y + 1, x);
//
//		} else if (goodSpot(y - 1, x) == true) {
//			maze[y - 1][x] = '-';
//			visited.add((y - 1) + " " + x);
//			check(y - 1, x);
//
//		} else if (goodSpot(y, x + 1) == true) {
//			maze[y][x + 1] = '-';
//			visited.add(y + " " + (x + 1));
//			check(y, x + 1);
//
//		} else if (goodSpot(y, x - 1) == true) {
//			maze[y][x - 1] = '-';
//			visited.add(y + " " + (x - 1));
//			check(y, x - 1);
//
//		} else {
//
//			for (int i = visited.size() - 1; i >= 0; i--) {
//				if (!visited.get(visited.size() - 1).equalsIgnoreCase(checkpoint)) {
//
//					maze[Character.getNumericValue(visited.get(visited.size() - 1).charAt(0))][Character
//							.getNumericValue(visited.get(visited.size() - 1).charAt(2))] = 'X';
//					visited.remove(visited.size() - 1);
//
//				}
//
//			}
////			check(Character.getNumericValue(checkpoint.charAt(0)),Character.getNumericValue(checkpoint.charAt(2)) );
//		}
//
//	}
//
//}