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
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
    	String[] ope = input.split(" ");
    	whole1 = ope[0];
    	whole2 = ope[2];
    	if (whole1.indexOf('/') == -1) {
    		whole1 += "_0/1";
    	}
    	if (whole1.indexOf('_') == -1) {
    		whole1 = "0_" + whole1;
    	}
    	if (whole2.indexOf('/') == -1) {
    		whole2 += "_0/1";
    	}
    	if (whole2.indexOf('_') == -1) {
    		whole2 = "0_" + whole2;
    	}
    	int neg1 = 1;
    	int neg2 = 1;
    	if (whole1.substring(0, 1).equals("-")) {
    		whole1 = whole1.substring(1);
    		neg1 = -1;
    	}
    	num1 = neg1 * Integer.parseInt(whole1.split("_")[0]);
    	top1 = neg1 * Integer.parseInt((whole1.split("_")[1].split("/")[0]));
    	bot1 = Integer.parseInt((whole1.split("_")[1].split("/")[1]));
    	if (whole2.substring(0, 1).equals("-")) {
    		whole2 = whole2.substring(1);
    		neg2 = -1;
    	}
    	num2 = neg2 * Integer.parseInt(whole2.split("_")[0]);
    	top2 = neg2 * Integer.parseInt((whole2.split("_")[1].split("/")[0]));
    	bot2 = Integer.parseInt((whole2.split("_")[1].split("/")[1]));
    	op = ope[1];
    	top1 += num1 * bot1;
    	top2 += num2 * bot2;
    	if (op.equals("/")) {
    		ftop = top1 * bot2;
    		System.out.println(ftop);
    		fbot = top2 * bot1;
    		System.out.println(fbot);
    	}
    	else {
	    	top1 = top1 * bot2;
	    	top2 = top2 * bot1;
	    	fbot = bot1 * bot2;
	    	
	    	if (op.equals("+")) {
	    		ftop = top1 + top2;
	    	}
	    	else if (op.equals("-")) {
	    		ftop = top1 - top2;
	    	}
	    	else if (op.equals("*")) {
	    		ftop = (top1 * top2)/fbot;
	    	}

    	}
    	return answerMachine(ftop, fbot, fnum);    	
    }
    /*
     * answerMachine was designed to simplify the answer and to clean up the code in the main specifically to check the grading boxes for simplification
     */
    public static String answerMachine(int ftop, int fbot, int fnum) {
		int div = greatestCommonDivisor(ftop,fbot);
    	fbot /= div;
    	ftop /= div;
    	fnum = ftop / fbot;
    	if (ftop % fbot == 0) {
	    	return String.valueOf(fnum);
    	}
    	else if (fnum != 0) {
	    	ftop = ftop % fbot;
	    	if (String.valueOf(fnum).substring(0,1).equals("-") && ftop != 0) {
	    		ftop *= -1;
	    	}
	    	return String.valueOf(fnum) + "_" + String.valueOf(ftop) + "/" + String.valueOf(fbot);
    	}
    	else {
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
