import java.util.Scanner;
public class FracCalc {
    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    	Scanner console = new Scanner(System.in);
    	String input = console.nextLine();
    	while (!input.equals("quit")) {
    		System.out.println(produceAnswer(input));
    		input = console.nextLine();
    	}
    	console.close();
    }
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input){ 
    	/* Variables, WOW. Its almost like they correspond to the parts of a fraction that are separated by it being the first one,
    	 *  the second one, and the final output
    	 *  Even more amazing is that top refers to the numerator, bot refers to the denominator, and num refers to the whole number
    	 *  
    	 *  Its pretty spooky that op refers to the operator and that whole refers to the entire mixed fraction with the same one 
    	 *  and two system as before
    	 */
    	int top1 = 0;
    	int num1 = 0;
    	int bot1 = 0;
    	int top2 = 0;
    	int num2 = 0;
    	int bot2 = 0;
    	int ftop = 0;
    	int fbot = 0;
    	int fnum = 0;
    	String op = "0";
    	String whole1 = "0";
    	String whole2 = "0";
    	String answer = ""; // For looping with multiple operators, it allows the previously calculated answer to become the first whole number later
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
    	String[] ope = input.split(" "); // This divides the numbers and the operator. WOW
    	whole1 = ope[0]; // Just some fence post code
    	for (int i = 1; i <= (ope.length-1)/2; i++) { // Multiple equations
	    	whole2 = ope[0 + (2 * i)];
	    	if (whole1.indexOf('/') == -1) { // This bit here is to force formatting so I don't have to change my code later on :)
	    		whole1 += "_0/1";
	    	}
	    	if (whole1.indexOf('_') == -1) { 
	    		whole1 = "0_" + whole1;
	    	}
	    	if (whole2.indexOf('/') == -1) {
	    		whole2 += "_0/1";
	    	}
	    	if (whole2.indexOf('_') == -1) { // This is the end of the forced formatting code
	    		whole2 = "0_" + whole2;
	    	}
	    	int neg1 = 1; // More variables. WOW. They show if there is a negative 
	    	int neg2 = 1;
	    	if (whole1.substring(0, 1).equals("-")) { // This finds a negative in the string if there is one
	    		whole1 = whole1.substring(1);
	    		neg1 = -1;
	    	}
	    	num1 = neg1 * Integer.parseInt(whole1.split("_")[0]); // This fixes the position of the negative after the force formatting
	    	top1 = neg1 * Integer.parseInt((whole1.split("_")[1].split("/")[0])); // This fixes the position of the negative after the force formatting
	    	bot1 = Integer.parseInt((whole1.split("_")[1].split("/")[1])); // This makes the string into an int
	    	if (whole2.substring(0, 1).equals("-")) { // This finds a negative in the string if there is one
	    		whole2 = whole2.substring(1);
	    		neg2 = -1;
	    	}
	    	num2 = neg2 * Integer.parseInt(whole2.split("_")[0]); // This fixes the position of the negative after the force formatting
	    	top2 = neg2 * Integer.parseInt((whole2.split("_")[1].split("/")[0])); // This fixes the position of the negative after the force formatting
	    	bot2 = Integer.parseInt((whole2.split("_")[1].split("/")[1])); // This makes the string into an int
	    	op = ope[-1 + (2 * i)]; // It determines what the operator is and stores it in a variable that I called op
	    	top1 += num1 * bot1;  // 
	    	top2 += num2 * bot2;
	    	if (op.equals("/")) { // this does division
	    		ftop = top1 * bot2;
	    		fbot = top2 * bot1;
	    		if (String.valueOf(top1).substring(0,1).equals("-")) { // This does more negative fixing
	    			bot2 *= -1;
	    		}
	    		if (String.valueOf(top2).substring(0,1).equals("-")) { // This does more negative fixing
	    			bot1 *= -1;
	    		}
	    	}
	    	else {
		    	top1 = top1 * bot2; // this standardizes the stuff prior to other types of operation
		    	top2 = top2 * bot1;
		    	fbot = bot1 * bot2;
	
		    	if (op.equals("+")) { // this does addition
		    		ftop = top1 + top2;
		    	}
		    	else if (op.equals("-")) { // this does subtraction
		    		ftop = top1 - top2;
		    	}
		    	else if (op.equals("*")) { // this does multiplication
		    		ftop = (top1 * top2)/fbot;
		    	}
	    	}
	    	answer = answerMachine(ftop, fbot, fnum); // This is a custom function that didn't come with the original program.
	    	whole1 = answer;
    	}
    	return answer; 
    }
    /*
     * answerMachine was designed to simplify the answer and to clean up the code in the main specifically to check the grading boxes for simplification
     */
    public static String answerMachine(int ftop, int fbot, int fnum) {
		int div = greatestCommonDivisor(ftop,fbot); 
    	fbot /= div;
    	ftop /= div;
    	if (String.valueOf(ftop).indexOf("-") == -1 && String.valueOf(fbot).indexOf("-") != -1) { // Specifically for dividing by a negative
    		fbot = Integer.parseInt(String.valueOf(fbot).substring(1));
    		ftop = Integer.parseInt("-" + String.valueOf(ftop));
    	}
    	fnum = ftop / fbot;
    	if (fnum != 0 && String.valueOf(ftop).indexOf("-") != -1) { // Removes the negative from the numerator if there is a number
    		ftop = Integer.parseInt(String.valueOf(ftop).substring(1));
    	}
    	if (String.valueOf(fbot).indexOf("-") != -1) { // Removes the negative from the denominator
    		fbot = Integer.parseInt(String.valueOf(fbot).substring(1));
    	}
    	if (ftop % fbot == 0) {// if it is a whole number
	    	return String.valueOf(fnum);
    	}
    	else if (fnum != 0) {// if it is a mixed number
	    	ftop = ftop % fbot;
	    	return String.valueOf(fnum) + "_" + String.valueOf(ftop) + "/" + String.valueOf(fbot);
    	}
    	else {// if it is a fraction that is less than one
    		if (String.valueOf(fnum).substring(0,1).equals("-") && ftop != 0) {
        		ftop *= -1;
        	}
        	return String.valueOf(ftop) + "/" + String.valueOf(fbot);
    	}
    }
    /**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
