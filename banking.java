import java.util.Scanner;

public class banking {
    static Scanner s1 = new Scanner(System.in);
    static double balance = 0;
    public static void main(String[] args) {
        //java banking program

        //declare variable
        boolean isRunning = true;
        int choice;

        //display menu
        while (isRunning){
            System.out.println("*****************");
            System.out.println("Banking Program");
            System.out.println("*****************");
            System.out.println("1. Show Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            //get and process users choice
            System.out.print("Enter your choice from above(btn 1-4):");
            choice = s1.nextInt();

            switch (choice){
                case 1 -> showBalance(balance);
                case 2 -> balance +=deposite();
                case 3 -> balance -=withdraw();
                case 4 -> isRunning = false;
                default -> System.out.println("invalid input");
            }
        }
        System.out.println("Thank You!! Have a Nice Day");
        s1.close();
        //showBalance()
        //deposit()
        //withdraw()
        //exit message
    }
    //showBalance()
    static void showBalance(double balance){
        System.out.println("*****************");
        System.out.printf("$%.2f\n",balance);
    }
    static double deposite(){
        double amount;

        System.out.print("Enter an amount to be deposited: ");
        amount = s1.nextDouble();

        if(amount<0){
            System.out.println("amount can't be negative");
            return 0;
        }else{
            return amount;
        }
    }
    static double withdraw(){
        double amount;
        System.out.print("Enter the amount you want to withdraw: ");
        amount = s1.nextDouble();
        if(amount > balance){
            System.out.println("insufficient funds");
            return 0;
        }else if(amount<0){
            System.out.println("amount cant be negative");
            return 0;
        }else{
            return amount;
        }
    }
}
