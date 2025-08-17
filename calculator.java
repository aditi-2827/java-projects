import java.util.Scanner;
public class calculator {
    public static void main(String[] args) {

        double num1;
        double num2;
        char operator;
        double result=0;
        boolean validoperation=true;

        Scanner s1 = new Scanner(System.in);
        System.out.print("enter the first number: ");
        num1=s1.nextDouble();

        System.out.print("enter the second number: ");
        num2=s1.nextDouble();

        System.out.print("Enter the operator (+,-,*,/,^) : ");
        operator = s1.next().charAt(0);

        switch(operator) {
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> {
                if(num2==0){
                    System.out.println("cannot divide by zero");
                }
                else{
                    result = num1/num2;
                }
            }
            case '^' -> result = Math.pow(num1, num2);
            default -> {
                System.out.print("Invalid Operation!");
                validoperation = false;
            }
        }
        if (validoperation){
            System.out.print("result: "+result);
        }
    }
}
