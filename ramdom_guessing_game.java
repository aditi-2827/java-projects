import java.util.Random;
import java.util.Scanner;

public class ramdom_guessing_game {
    public static void main(String[] args) {
        Random r1 = new Random();
        Scanner s1 = new Scanner(System.in);

        int guess;
        int attempts = 0;
        int min=1;
        int max=100;
        int randomNum = r1.nextInt(min, max+1);//first number is insclusive and second number is exclusive

        System.out.println("Number Guessing Game");
        System.out.printf("enter number between %d-%d\n",min,max);

        do{
            System.out.print("Enter a guess: ");
            guess = s1.nextInt();
            attempts++;

            if(guess<randomNum){
                System.out.println("too low , try again");
            }else if(guess>randomNum){
                System.out.println("too high,try again");
            }else{
                System.out.println("correct! the number was "+randomNum);
                System.out.println("number of attempts:"+attempts);
            }
        }
        while(guess !=randomNum);
        System.out.println("you have won");

    }
}
