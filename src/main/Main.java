package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
	private static List<Integer[]> arrList;
	private static int matrixSize = 0;
	private static int vectorSize = 0;
	private static int patternToSearch = 1;
	public static void main(String[] args) throws Exception {
		String filePath = null;
		String pattern = null;
		for (int i = 0; i < args.length; i++) {
			if (filePath == null && args[i].contains("-location") && args.length >= (i+1)) {
				filePath = args[i+1];
			} else if (pattern == null && args[i].contains("-pattern") && args.length >= (i+1)) {
				pattern = args[i+1];
			}
		}
		
		if (filePath == null) {
			throw new Exception("Missing mandatory parameter: -location \"file path\"");
		}
		if (pattern != null && pattern.matches("^\\d$")) {
			if (Integer.parseInt(pattern) >= 1 && Integer.parseInt(pattern) <= 3) {
				patternToSearch = Integer.parseInt(pattern);
			} else {
				throw new Exception("Parameter pattern must be between 1, 2 or 3");
			}
		}
		arrList = readFile(filePath);
		searchPattern(patternToSearch);
	}
	
	private static void searchPattern(int patternIndex) throws Exception {
		int matches = 0;
		Integer[][] pattern = getPattern(patternIndex);
		for (int i1 = 0; i1 < matrixSize; i1++) {
			for (int j1 = 0; j1 < vectorSize; j1++) {
				if (i1 <= (matrixSize - pattern.length) && j1 <= (vectorSize - pattern.length)) {
					int equal = 0;
					breakpoint: {
		                for (int i2 = 0; i2 < pattern.length; i2++) {
		                    for (int j2 = 0; j2 < pattern.length; j2++) {
		                    	int val1 = arrList.get(i1 + i2)[j1 + j2];
		                    	int val2 = pattern[i2][j2];
		                        if (val1 == val2) {
		                            equal++;
		                        } else {
		                            break breakpoint;
		                        }
		                    }
		                }
		            }
					if (equal == (pattern.length * pattern[0].length)) {
		                matches++;
		            }
				}
			}
		}
		System.out.println("Total de " + matches + " padrões encontrados para o padrão " + patternToSearch);
	}
	
	private static List<Integer[]> readFile(String filePath) throws Exception {
		List<Integer[]> a = new ArrayList<Integer[]>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
		    Integer[] v = null;
		    while (reader.ready()) {
		    	String line = reader.readLine();
		    	if (line.length() < 3) {
		    		throw new Exception("The matrix must be greater than 2x2!");
		    	}
		    	if (vectorSize == 0) {
		    		vectorSize = line.length();
		    	} else if (vectorSize != line.length()) {
		    		throw new Exception("File does not contain a matrix!");
		    	}
		    	int y = 0;
	    		v = new Integer[vectorSize];
		    	for (int i = 0; i < line.length(); i++) {
					v[y] = Integer.parseInt(String.valueOf(line.charAt(i)));
					++y;
				}
		    	a.add(v);
		    	matrixSize++;
		    }
		    reader.close();
		} catch (Exception e) {
		    System.err.format("Exception occurred trying to read '%s'.", "arquivo.txt");
		    e.printStackTrace();
		}
		if (matrixSize < 3) {
			throw new Exception("The matrix must be greater than 2x2!");
		}
		return a;
	}
	
	private static Integer[][] getPattern(int pattern) throws Exception {
		try {
			switch (pattern) {
			case 1:
				return new Integer[][] {
					{0,1,0},
					{1,1,1},
					{0,1,0}
				};
			case 2:
				return new Integer[][] {
					{1,1,1},
					{0,1,0},
					{0,1,0}
				};
			case 3:
				return new Integer[][] {
					{0,0,1},
					{0,1,0},
					{1,0,0}
				};
			default:
				throw new Exception("Pattern does not exist");
		}
		} catch (Exception e) {
			System.err.format("Exception occurred trying to get '%s'.", "pattern " + pattern);
		    e.printStackTrace();
		}
		return null;
		
	}
}
