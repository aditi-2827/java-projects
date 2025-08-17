import java.util.Random;
import java.util.Scanner;

public class rock_papaer_s {
    public static void main(String[] args) {

        //rock paper scissor game
        //declare variable
        //get choice from the user
        //get random choice from the computer
        //check with the condition
        //ask to play again
        //good bye message

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] choice={"rock","paper","scissor"};
        String playerChoice;
        String computerChoice;
        String playAgain;


        do {
            System.out.print("enter your move (rock, paper, scissor): ");
            playerChoice=scanner.nextLine().toLowerCase();

            if(!playerChoice.equals("rock") && !playerChoice.equals("paper") && !playerChoice.equals("scissor")){
                System.out.println("invalid choice");

            }

            computerChoice = choice[random.nextInt(3)];
            System.out.println("computer choice: "+computerChoice);

            if (playerChoice.equals(computerChoice)){
                System.out.println("its a tie");
            } else if (playerChoice.equals("rock") && computerChoice.equals("scissors")) {
                System.out.println("you win");
            } else if (playerChoice.equals("paper") && computerChoice.equals("rock")) {
                System.out.println("you win");
            }
            else if (playerChoice.equals("scissor") && computerChoice.equals("paper")){
                System.out.println("you win");
            }
            else{
                System.out.println("you loose");
            }

            System.out.print("play again (yes/no) ?: ");
            playAgain = scanner.nextLine().toLowerCase();
        }
        while(playAgain.equals("yes"));

        System.out.println("thanks for playing");

        scanner.close();

    }
}
