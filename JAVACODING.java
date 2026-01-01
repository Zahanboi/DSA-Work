import java.util.*;
import java.lang.Math;


public class JAVACODING {
    public static void threeAvg(float a ,float b ,float c) {
		double avg = (a+b+c)/3;
        System.out.println(avg);
	}

	public static void isEven(int a) {
	   boolean sc;
		if(a%2 == 0){
		 sc = true;
		}else {
			sc = false;
			
	} System.out.println(sc);

	}

	public static void palindromeCheck(int n) {
	boolean check;
	int reversed = 0;
	int b = n;
	
	for(int i=1;i<=(n+i-1);i++){
    int a = n % 10;
	reversed = (reversed*10) + a;
    n = n/10;
	System.out.println(n);
	
	}
	
	if(reversed == b){
		check = true;
	}else{
		check = false;
	}
    System.out.println(check);

	}

	public static void arrManipulation(int arr[]) {

	for(int i=0;i<arr.length;i++){
		arr[i] += 1 ;
	}
	
	}

	public static int digitsSum(int a) {
		int ld ;
		int n = 0;
		
		while(a !=0){
        ld = a%10;
		n = n + ld;
		a /= 10;
	   }
	   return n;
	}


	public static void arrSearch(String arr[], String n) {
        
		for(int i=0; i<arr.length; i++){
		if(arr[i] == n){
			System.out.println(i);
		}
		
		}
    }

	public static void arrLargest(int arr[]){
       int b = Integer.MIN_VALUE;
       for(int i=0; i<arr.length; i++){
       if(arr[i]>b){
          b = arr[i];
	   }

		}	
		System.out.println(b);
	}

    public static void arrBinary(int arr[], int n) {
		// mid can't equal to first and last elements
     
     int start = 0;
	 int end = (arr.length - 1);
	int mid = (start + end)/2;
	while(start <= end){
        
		if(arr[mid] == n){

         System.out.println(mid);
          return;
		}else{

		if(arr[mid] > n){
			mid = mid - 1;
		}else{
			mid = mid + 1;
		}

        }
			
	}

	System.out.println("not dere bru");
}

 public static void reverseArr(int arr[]){
    int start = 0;
	int end = arr.length - 1;
	int a,b;
	for(int i=0; i<arr.length/2; i++){
    // no need to change middle element in odd number of elements in an array
	
	a = arr[start] ;
    b = arr[end] ;
    arr[start] = b;
	arr[end] = a;
	start++;
	end--;
	
	
	}


 }
 




public static void arrPairs(int arr[]) {
	for(int i=0; i<arr.length; i++){
		for(int j=i; j<arr.length; j++){
	    System.out.print("(" + arr[i] + "," + arr[j]+ ")");
	
	}
	System.out.println();
}
}

public static void maxSubArraySum(int arr[]) {
  int sum = 0;
  
  int b = Integer.MIN_VALUE;
  for(int i = 0; i<=(arr.length-1); i++){
    for(int j = i; j<=(arr.length-1); j++){
		for(int k = i; k<= j; k++){
        sum += arr[k];
      
		}
		
         if(sum > b){
		b = sum;
		}   
		
		sum = 0;
	}
    

  }
System.out.println(b);

}

public static void maxSubArraySumBetter(int arr[]) {
	int sum = 0;
	int b = Integer.MIN_VALUE;
  int prefix[] = new int[arr.length];
  prefix[0]=arr[0];
  for(int i=1;i<=(prefix.length-1);i++){
	prefix[i] = prefix[i-1] + arr[i];
  }
	
	for(int i = 0; i<=(arr.length-1); i++){
		int start = i;

	  for(int j = i; j<=(arr.length-1); j++){
		int end = j;
     sum = start == 0 ? prefix[end] : prefix [end] - prefix[start - 1];
	  if(sum > b){
		b = sum;
	  }
}
	}
   System.out.println(b);

}

public static void maxSubArraySumBest(int arr[]) {
int sum = 0;
int b = Integer.MIN_VALUE;
	for(int i = 0; i<=(arr.length-1); i++){
    sum += arr[i];
	if(sum<0){
		sum = 0;
	} 
     b = Math.max(b, sum);

	}
System.out.println(b);
}

public static void stocksProfit(int arr[]) {
  int buyst0ck = arr[0];
  int sellSt0ck = 0;
  int profit = 0;
  int b = 0;
  int time = 0;
  int time2 = 0;
  for (int i =1;i<arr.length;i++){
  sellSt0ck = arr[i];
  buyst0ck = Math.min(buyst0ck , arr[i]);
  if(buyst0ck==arr[i]){
	time2 = i+1;
	
  }
  profit = sellSt0ck - buyst0ck;
  if(profit>b){
	b = profit;
	time = i+1;
  }
  }
	
System.out.println("best day to earn max profit is " + time + " and best day to buy is " + time2);

}

public static void trappingRainwater(int height[]) {
int left  = 0;
int right = height.length-1;
int total = 0;
int leftmax = 0;
int rightmax = 0;

while (left <= right) {
	if (height[left] <= height[right]) {
		if (height[left] >= leftmax) {
			leftmax = height[left];
		} else {
			total += leftmax - height[left];
		}
		left++;
	} else {
		if (height[right] >= rightmax) {
			rightmax = height[right];
		} else {
			total += rightmax - height[right];
		}
		right--;
	}
}

System.out.println(total);
}

public static void arrSortBubble(int arr[]) {
	
	for(int i = 0;i <arr.length-1; i++){
		int swaps = 0;
		for(int j = 0; j<arr.length-1-i; j++){
			
			if(arr[j]>arr[j+1]){
				int temp = arr[j];
				arr[j] = arr[j+1];
				arr[j+1] = temp;
				swaps++;
			}
			
        }
			if(swaps == 0){
				return;
					}
	}
	
}

public static void arrInsertionSort(int arr[]) {
   
	for (int i = 0; i < arr.length-1; i++) {
		for (int j = 0; j <= i; j++) {
			if(arr[j]>arr[i+1]){
				int temp = arr[j];
				arr[j] = arr[i+1];
				arr[i+1] = temp;
			}
		}
	}


}


public static void arrSelectionSort(int arr[]) {
   int min = Integer.MAX_VALUE;
   int x = 0;
	for (int i = 0; i < arr.length; i++) {
		for (int j = i; j < arr.length; j++) {
		if(min > arr[j]){
        min = arr[j];
		x = j;
		}
	}
	int temp = arr[x];
    arr[x] = arr[i];
	arr[i] = temp;
min = Integer.MAX_VALUE;
}

}

public static void arrPrint(int arr[]) {

for(int i = 0;i < arr.length; i++){

	System.out.print(arr[i] + " ");

}
System.out.println();
}

// public static void arrOcuurence(int arr[]) {
// 	int count =0;
// 	for(int i= 1;i<arr.length - 1; i++){
//     if(arr[i] == arr[i+1]){
// 		count++;
// 	}

// 	if(arr[0] == arr[i+1]){
// 		count++;
// 	}
// int nums[] = {1,2};

//     int min = minSearch(nums); 
// 	}

// }

// public static void arrRotationIndexSearch() {
	
// }
public static void stringPalindrome(String str) {

	for(int i = 0;i <=(str.length()/2);i++){
     if(str.charAt(i)==str.charAt(str.length()-1-i)){ //will run after even middle element
		continue;
	 }
	 else{
		return;
	 }

	}
    System.out.println("positive");
}

public static void findDistance(String str) {
	
int x = 0;
int y = 0;
for (int i =0;i<str.length();i++) {
if(str.charAt(i)== 'W'){
  x += -1;
}	

if(str.charAt(i)== 'E'){
	x += 1;
  }	

  if(str.charAt(i)== 'N'){
	y += 1;
  }	

  if(str.charAt(i)== 'S'){
	y += -1;
  }	

	
}
double a = x*x + y*y;
System.out.println(Math.sqrt(a));

}


public static void SubString(String str , int si , int ei) {

	for(int i = si; i< ei;i++){
    System.out.print(str.charAt(i) + " ");

	}

}

public static void toUpperCase(String str) {
char ch1 = Character.toUpperCase(str.charAt(0));// for first letter

StringBuilder sb = new StringBuilder("");
sb.append(ch1);
	for(int i = 1;i < str.length();i++){
		ch1 = str.charAt(i);
    if(str.charAt(i) == ' ' && i < str.length()){
		ch1 = Character.toUpperCase(str.charAt(i+1));
		sb.append(' ');
        i++; // because if condition met i+1 term already appended
	}
     sb.append(ch1);
	}

sb.toString();
System.out.println(sb);
}

public static void StringCompression(String str) {
	int count = 1;
	int b = 1;
    StringBuilder sb = new StringBuilder("");
   for (int i = 0; i < str.length(); i++) {
	sb.append(str.charAt(i));
	for(int j = i; j<(str.length()-1) ;j++){

	if(str.charAt(j) == str.charAt(j+1) ){
		count++;
        b = count;
		i++;
	}else{
		count = 1;
		break;
	}

}
	if(count>1){
		sb.append(b);
	}
   }
   System.out.println(sb);
	
}

public static void findLowercaseVowels(String str) {
	int count = 0;
	for(int i = 0;i<str.length();i++){
    if(Character.isLowerCase(str.charAt(i)) && (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') ){
		count++;
}
	}
	System.out.println("occurence is : " + count + " times");


}

public static void Anagrams(String str1 , String str2) {
	char []arr = str1.toCharArray(); 
        Arrays.sort(arr); 	
        System.out.println(String.valueOf(arr));

    char []arrr = str2.toCharArray(); 
        Arrays.sort(arrr);
        System.out.println(String.valueOf(arrr));
		int count = 0;
for (int i = 0; i < arrr.length; i++) {
	if (arr.length != arrr.length) {
		System.out.println("bad");
		return;
	}
	
	if(arr[i] == arrr[i]){
	count++;	
}
   if(count == str1.length()-1){
	System.out.println("good");
   }


	}
}

public static int maxArea(int[] height) {
	int i = 0;
	int j = 0;
	int max = 0;
	int Containernew = 0;
	int Containerold = 0;
	int Realmax = 0;
	while(i<=j){
		int length = (j-i);
		Containerold = Containernew;
		int Area = Math.min(height[i] , height[j]);
		Containernew = Area*length;
		j++;
		if(j == height.length){
			i++;
			if(i<(height.length)){
			j = i;
			}
			if(i == height.length){
				j--;
			}
		}
		max = Math.max(Containernew , Containerold);
		Realmax = Math.max(Realmax, max);
	}
	System.out.println(Realmax);
	return max;
}

public static void InsertInArray(int arr[] ,int index ,int b) {
int temp = 0;
int newarr[] = Arrays.copyOf(arr, (arr.length+1));
	temp = newarr[index];
	newarr[index] = b;
// System.out.println(newarr.length);
for (int i = index+1; i < newarr.length; i++) {
 int temp2 = newarr[i];
  newarr[i] = temp;
  temp = temp2;

}
arrPrint(newarr);
}

public static void DeleteInArray(int arr[] , int index) {
	arr[index] = 0;
	for (int i = index; i < (arr.length-1); i++) {
		//swap
		int temp = arr[i];
		arr[i] = arr[i+1];
		arr[i+1] = temp;
 	}
int newarr[] = Arrays.copyOf(arr, (arr.length-1));
arrPrint(newarr);
arrPrint(arr);
}



public static void arrTriplets(int arr[]) {
	for (int i = 0; i < 1; i++) {
	for (int j = i ; j < 2; j++) {
	for (int k = j; k < arr.length; k++) {
		if( (arr[i] + arr[j] + arr[k] == 0) ){
     System.out.print("{" + arr[i]+" "); System.out.print(arr[j]+" "); System.out.print(arr[k]+ "}" + " ");
	 System.out.println();
	 
	}
        
	}	
	}	
		
	}
	System.out.println(" ");

}

public static void FindNoIn2Darr(int arr[][]) {
	int count = 0;
	for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < arr[0].length; j++) {
			if(arr[i][j] == 7){
            count++;
			}
		}
	}
System.out.println(count);
}

public static int GetBit(int n , int i) {
	int BitMask = 1<<i;
	if((n&BitMask) == 0){
		return 0;
	   } 
	   else{
		  return 1;
	   }
}

public static int SetBit(int n , int i) {
	int BitMask = 1<<i;
	return n|BitMask ;
}

public static int ClearBit(int n , int i) {
	int BitMask = ~(1<<i);
	return n&BitMask ;
}

public static int UpdateBit(int n ,int i , int b) {
   int NewBit = b;
   int a = ClearBit(n, i);
  int BitMask = NewBit<<i;
   return BitMask|a;
   

}

public static int Printfac(int n) {
	if((n-1) == 0){
       return 1;
	}
   
    // Printfac(n-1);
    return n*Printfac(n-1);

}

public static int Fibrec(int n) {
	// 0 1 1 2 3 5 8 13 21
   if(n == 0 || n ==1){
	return n;
   } 
   int fnm1 = Fibrec(n-1);
   int fnm2 = Fibrec(n-2);
   int fibn = fnm1 + fnm2;

   return fibn;
}
 
public static boolean Primecheck (int n) {
	
	for (int i = 2; i < n-1; i++) {
		if (n%i == 0) {
			return false;
		}
	}
     
	if (n==1) {
		return false;
	}
	return true;
}

public static void majorityElement(int[] nums) {
	int i = 0;
	int j = 1;
	int a = -1;
	int n = nums.length / 2;
	int count = 1;
	while(i<j){
		if(j<nums.length && i < nums.length){
			if(nums[i] == nums[j]){
				count++;
				j++;
			}else{
				j++;
				System.out.println(j);
			}
		}
		if(j == nums.length){ 
			if(count>n){
				a = nums[i];
			}
			count = 1;
			i++;
			j = i+1;
			
			System.out.println(i);
			System.out.println(j);
		}
	}
	System.out.println(count);

	
}
// public static int Firstocc(int arr[] ,int key , int i){
// if(i == arr.length){
// 	return -1;
// }


// }

// BackTracking

public static void findSubsets(String str, int i , String ans){
	if(i == str.length()){
		if (ans == "") {
			System.out.println("null");
			return;
		} else {
			System.out.println(ans);
			return;
		}

	}

	findSubsets(str, i+1, ans+str.charAt(i));
	findSubsets(str, i+1, ans);
}


public static void findPermutations(String str , String ans) {
	if (str.length() == 0) {
		System.out.println(ans);
		return;
	}

	for (int i = 0; i < str.length(); i++){
	char curr = str.charAt(i);
	String newStr = str.substring(0, i) + str.substring(i + 1);
	findPermutations(newStr, ans+curr);
}

}

public static void printboard(String[][] board){ 
	for (int i = 0; i < board.length; i++) {
		for (int j = 0; j < board[0].length; j++) {
			System.out.print(board[i][j] + " ");
		}
		System.out.println();
	}
}

public static Boolean isSafe(String[][] board, int row , int col){
	//check top
	for (int i = row; i >= 0; i--) {
		if (board[i][col] == "Q") {
			return false;
		}
	}

	//check top left diagonal
	for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
		if (board[i][j] == "Q") {
			return false;
		}
	}

	//check top right diagonal

	for (int i = row, j = col; i >= 0 && j < board[0].length ; i--, j++) {
		if (board[i][j] == "Q") {
			return false;
		}
	}

	return true;

}


public static void Nqueens(String[][] board , int row){
	if(row == board.length){
		printboard(board);
		System.out.println();
		return;	
		// return true;  // do we want to print just one soln
	}

	for (int i = 0; i < board.length; i++) {
		if (isSafe(board, row, i)){
		board[row][i] = "Q";
		// if (Nqueens(board, row+1)) {
		// 	return true;
		// } // for checking if soln exist or not and print just one by using printboard() in main
		Nqueens(board, row+1); // phele sb queens place in starting of every row then for row + 1
		board[row][i] = "x"; //makes last row queen -> x //backtracking
		}
	}

}


public static int GridWays(int i, int j, int m, int n){
	// Base case: reached bottom-right cell
	if(i == m-1 && j == n-1){
		return 1;
	}
	// Out of bounds
	if(i == m || j == n){
		return 0;
	}

	int w1 = GridWays(i, j+1, m, n);
	int w2 = GridWays(i+1, j, m, n);
	return w1 + w2; // this way time complexity very high so use direct trick
}

public static int GridWaysEasy( int m, int n){
	int a = n-1;
	int b = m-1;
	int ans = Printfac((a+b))/(Printfac(a)*Printfac(b));
	return ans;
}

public static boolean isSafee(int sudoku[][], int row, int col , int digit){
	// check row
	for (int i = 0; i < 9; i++) {
		if (sudoku[row][i] == digit) {
			return false;
		}
	}

	// check column
	for (int i = 0; i < 9; i++) {
		if (sudoku[i][col] == digit) {
			return false;
		}
	}

	// check 3x3 box meaning check small 2d array
	int startRow = (row/3)*3;
	int startCol = col - col % 3;
	for (int i = startRow; i < startRow + 3; i++) {
		for (int j = startCol; j < startCol + 3; j++) {
			if (sudoku[i][j] == digit) {
				return false;
			}
		}
	}

	return true;
}

public static boolean sudokuSolver(int sudoku[][], int row, int col){
	if (row == 9) {
		return true;
	}

	int nextRow = row;
	int nextCol = col + 1;
	if (col + 1 == 9) {
		nextRow = row + 1;
		nextCol = 0;
	}

	if(sudoku[row][col] != 0){
		return sudokuSolver(sudoku, nextRow, nextCol);
	}

	for (int digit = 0; digit < 10; digit++) {
		if (isSafee(sudoku, row, col, digit)) {
			sudoku[row][col] = digit;
			if (sudokuSolver(sudoku, nextRow, nextCol)) {//if soln exist then until row == 9 no true return so backtrack step also run at row == 9 true comes if bracket cond satisfy and run 
				return true;
			}
			sudoku[row][col] = 0; // backtrack
			
		}
	}

	return false; //no sol

}

public static void printSudoku(int sudoku[][]) {
	for (int i = 0; i < sudoku.length; i++) {
		for (int j = 0; j < sudoku[0].length; j++) {
			if (j % 3 == 0 && j != 0) {
				System.out.print("| ");
			}
			System.out.print(sudoku[i][j] + " ");
		}
		if ((i + 1) % 3 == 0 && i != 8) {
			System.out.println();
			System.out.println("------ -------- -----");
		}else{
		System.out.println();
		}
	}
	System.out.println();
}

public static boolean isSafeMaze(int maze[][], int row, int col, int i, int j){
	if(maze[i][j] == 0){
		return false; // not safe if cell is blocked
	}else{
		return true;
	}
}


public static boolean ratInMaze(int maze[][], int row , int col , int i , int j) {
	//base case
	if (i == row-1 && j == col-1) {
		return true;
	}

	if (i == row || j == col) {
		return false;
	}

	if(isSafeMaze(maze, row, col, i , j)){
		if (ratInMaze(maze, row, col, i+1, j)) {
			return true;
		}
	    
		if (ratInMaze(maze, row, col, i, j+1)) {
			return true;
		}

		return false;
	}

	
	

		
	
	return false; 

}

public static void printMaze(int maze[][]) {
	for (int i = 0; i < maze.length; i++) {
		for (int j = 0; j < maze[0].length; j++) {
			System.out.print(maze[i][j] + " ");
		}
		System.out.println();
	}
	System.out.println();
	
}

    public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	
	// arrTriplets(new int[]{-1,0,1,2,-1,-4});
    // String[] nums = {"ab","b","c","d","e"};  
	// System.out.println(nums[0].length());
	String str = "abc";
	String ans = "";
	String[][] board = new String[5][5];
	for (String[] is : board) {
		Arrays.fill(is, "x");
	}

	int sudoku[][] = {
		{0, 0, 8, 0, 0, 0, 0, 0, 0},
		{4, 9, 0, 1, 5, 7, 0, 0, 2},
		{0, 0, 3, 0, 0, 4, 1, 9, 0},
		{1, 8, 5, 0, 6, 0, 0, 2, 0},
		{0, 0, 0, 0, 2, 0, 0, 6, 0},
		{9, 6, 0, 4 ,0 ,5 ,3 ,0 ,0},
		{0 ,3 ,0 ,0 ,7 ,2 ,0 ,0 ,4},
		{0 ,4 ,9 ,0 ,3 ,0 ,0 ,5 ,7},
		{8 ,2 ,7 ,0 ,0 ,9 ,0 ,1 ,3}
		
	};

	// System.out.println("Given Sudoku : ");
	// printSudoku(sudoku);

	// if (sudokuSolver(sudoku, 0, 0)) {
	// 	System.out.println("Solution Found");
	// 	printSudoku(sudoku);
	// } else {
	// 	System.out.println("No solution found :(");
	// }
	

	int maze[][] = {
		{1, 0, 0, 0},
		{1, 1, 0, 1},
		{0, 1, 0, 0},
		{1, 1, 1, 1}
	};

	System.out.println("Given Maze : ");
	printMaze(maze);

	if (ratInMaze(maze, 4, 4, 0, 0)) {
		printMaze(maze);
	}else {
		System.out.println("No path found in the maze :(");
	}

	//Nqueens(board , 0);
//System.out.println(2%3); //gives 2
	// System.out.println(GridWays(0, 0, 2, 3)); 
	// System.out.println(GridWaysEasy(2, 3)); 
	// System.out.println(board.length);

	// findSubsets(str, 0, ans);
	// findPermutations(str, ans);
	// nums[0].charAt(0) = '1';
    // majorityElement(height);
	// // maxArea(height);
	// char a = '0';
    // int b = (int)a;
	// // if (a > 65) {
	// 	System.out.println(b);;
	// // }
	// String sh = "abc";
	// System.out.println(sh.length());
	// String s = "abc";
	// StringBuilder str1 = new StringBuilder("");
	// str1.append("abc");
	// String strnew = str1.toString();
	// System.out.println(strnew);
	// System.out.println(str1);
	// for(int i = 0; i<s.length(); i++){
	// 	str.append(s.charAt(i));
	// 	str.deleteCharAt(1);
	// }
	
	// System.out.println(str);

	// trappingRainwater(height);
	// int i = 1;
	// int a = i++;
	// System.out.println(a);
	// int b = ++i;
	// System.out.println(b);
	// int a = sc.nextInt();
	// System.out.println();
	// System.out.println(Fibrec(8));
	// if(Primecheck(11)== true){
	// 	System.out.println("prime found");
	// }
	// else{
	// 	System.out.println("nahh ain't no prime");
	// };

	
	// int arr[] = {1,2,3,4,5,6,7}; 
// InsertInArray(arr, 4, 4);
//   arrBinary(arr, 4);
//     int n = 2;
// 	String str1= new String("der").intern();
// // 	int arr[] = {1,2,3,4,5,5,6};
// // 	// System.out.println(6>>1);
// // // int a = sc.nextInt();
// // // System.out.println(a);
// // // int arr[][]= new int[2][3];
// // // for (int i = 0; i < arr.length; i++) {
// // 	// 	for (int j = 0; j < arr[0].length; j++) {
// // 	// 		arr[i][j] = sc.nextInt();
// // 	// 	}
// // 	// }
// // 	// for (int i = 0; i < arr[0].length; i++) {
// // 	// 	for (int j = 0; j < arr.length; j++) {
// // 	// 		System.out.print(arr[j][i]);
// // 	// 	}
// // 	// 	System.out.println();
// // 	// }
// // 	// int sum = 0;
// // 	// for (int i = 0; i < arr[0].length; i++) {
// // 	// 	sum += arr[1][i];
// // 	// }
// // System.out.println(UpdateBit(15, 2, 0));
// // System.out.println(Printfac(5));
// // System.out.println(str1+"0");
// // if(n == 2){
// // 	System.out.println(str1+"1");
// // }
//  arrInsertionSort(arr);
//  arrPrint(arr);

// class Student{

// void Virgin() {
//     System.out.println("good");
//  }
 

// } 
// String s = "cb34";
// StringBuilder str = new StringBuilder();
//     for (int i = 0; i < s.length(); i++) {
//         str.append(s.charAt(i));
//     }

//     int i = 0;
//     while (i < str.length()) {
//         char ch = str.charAt(i);
//         int b = (int)ch;
//         if (b >= 48 && b <= 57) {
//             str.deleteCharAt(i); // Delete the digit character
//             // No need to increment i here because the length of str has decreased
//         } else {
//             i++; // Only increment i if no deletion occurred
//         }

//         for (int j = i - 1; j >= 0; j--) {
//             char ch1 = str.charAt(j);
//             int c = (int)ch1;
//             if (c > 58) {
//                 str.deleteCharAt(j);
//                 i = 0; // Reset i to 0 to start over
//                 break;
//             }
//         }
//     }
//     System.out.println(str.toString());

// 	}
// }






















	}
}