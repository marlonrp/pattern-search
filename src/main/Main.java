package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static List<Integer[]> arrList;
	private static int vectorSize = 0;
	public static void main(String[] args) throws Exception {
		arrList = readTxt("C:\\Users\\Aluno\\Documents\\Marlon\\works\\padrao-matriz\\src\\main\\array.txt");

//		System.out.println("-----------------------------------");
//		for (int i = 0; i < arrList.size(); i++) {
//			showArrayValue(arrList.get(i));
//		}
//		System.out.println("-----------------------------------");

		searchPattern(1);
	}
	
	private static void searchPattern(int patternIndex) throws Exception {
		int matches = 0;
		Integer[][] pattern = getPattern(patternIndex);
		for (int i1 = 0; i1 < arrList.size(); i1++) {
			for (int j1 = 0; j1 < vectorSize; j1++) {
				if (j1 <= (vectorSize - pattern.length) && i1 <= (vectorSize - pattern[0].length)) {
					int equal = 0;
					label: {
		                for (int i2 = 0; i2 < pattern.length; i2++) {
		                    for (int j2 = 0; j2 < pattern.length; j2++) {
		                        if (arrList.get(i1 + i2)[j1 + j2] == pattern[i2][j2]) {
		                            equal++;
		                        } else {
		                            break label;
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
		System.out.println(matches);
		
//		for (let i1 = 0; i1 < arrList.length; i1++) {
//		    for (let j1 = 0; j1 < arrList.length; j1++) {
//
//		        // Validate limits
//		        if (j1 <= (arrList.length - pattern.length) && i1 <= (arrList[0].length - pattern[0].length)) {
//
//		            let equal = 0;
//
//		            // Check Iteration
//		            label: {
//		                for (let i2 = 0; i2 < pattern.length; i2++) {
//		                    for (let j2 = 0; j2 < pattern.length; j2++) {
//		                        if (arrList[i1 + i2][j1 + j2] == pattern[i2][j2]) {
//		                            equal++;
//		                        } else {
//		                            break label;
//		                        }
//		                    }
//		                }
//		            }
//
//		            // Validation
//		            if (equal == (pattern.length * pattern[0].length)) {
//		                matches++;
//		            }
//		        }
//		    }
//		}
	}
	
	private static List<Integer[]> readTxt(String filePath) {
		List<Integer[]> a = new ArrayList<Integer[]>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
		    Integer[] v = null;
		    while (reader.ready()) {
		    	String line = reader.readLine(); 
		    	if (vectorSize == 0) {
		    		vectorSize = line.length();
		    	} else if (vectorSize != line.length()) {
		    		throw new Exception("File does not contain a arrList!");
		    	}
		    	int y = 0;
	    		v = new Integer[vectorSize];
		    	for (int i = 0; i < line.length(); i++) {
					v[y] = Integer.parseInt(String.valueOf(line.charAt(i)));
					++y;
				}
		    	a.add(v);
		    }
		    reader.close();
		} catch (Exception e) {
		    System.err.format("Exception occurred trying to read '%s'.", "arquivo.txt");
		    e.printStackTrace();
		}
		return a;
	}
	
	private static void showArrayValue(Integer[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " | ");
		}
		System.out.println("");
	}
	
	private static Integer[][] getPattern(int pattern) throws Exception {
		try {
			switch (pattern) {
			case 1:
				return new Integer[][] {{0,1,0},{1,1,1},{0,1,0}};
			case 2:
				return new Integer[][] {{1,1,1},{0,101},{0,1,0}};
			case 3:
				return new Integer[][] {{0,0,1},{0,1,0},{1,0,0}};
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
