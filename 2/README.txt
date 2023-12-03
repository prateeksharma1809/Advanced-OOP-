Homework Description:

Given are two sets of integers:



An example would be:
SetOfNumbersToUse = { 2, 3, 4, 5 }
SetOfNumberToCalculate = { 1, 2, 5 }

Your Work:

You have to design a program that can determine if the numbers in SetOfNumberToCalculate can be expressed by adding or subtracting numbers from SetOfNumbersToUSe. Every number from SetOfNumbersToUSe can be used 0 or 1 times. If there exists more than one, you have to print the smaller set. If the sets have the same size you can print any chosen set. You need to print only one set.

My solution produces the following output for the above examples.

% java AddAndSubstract
1 can be calculated by the sum of:  -2 3
5 can be calculated by the sum of:  5
7 can be calculated by the sum of:  4 3
Idea for a Solution:

• SetOfNumbersToUSehink about the heart of the problem.

Your output does not have to be identical to mine, but similar.

Requirements:

• You have to name the file AddAndSubstract.java.

• You can only use basic types, control structures

The following code might be useful and you have to use it.

 1      public class AddAndSubstract {
 2          static int[] numberToUse = { 2, 3, 4, 5 };
 3          static int[] numberToCalculate = { 1, 5, 7 };
 4
 5
 6          public static void main( String[] arguments ) {
 7              for  ( int index = 0; index < numberToCalculate.length; index ++ )
 8                      testPropoerty(numberToCalculate[index]);
 9
10          }
11      }
Source Code: Src/22/AddAndSubstract.java

Idea for a Solution:

Design the program on paper and test each functionality before you start coding.

