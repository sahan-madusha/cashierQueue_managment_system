import java.io.*;
import java.io.FileWriter;//data write and read
import java.util.Scanner;

public class Main {

    // Burger count
    static int BurgerQty = 50;

    //contain X represent not Occupied at first X- no O-in
    static String[] queueOne = new String[]{"X","X"};
    static String[] queueTwo = new String[] {"X","X","X"};
    static String[] queueThree = new String[] {"X","X","X","X","X"};


    //queue numbers and customers position
    static String[] validQueueNumbers = new String[] {"1","2","3"};
    static String[] validPositionNumbers = new String[] {"1","2","3","4","5"};

    //manu option
    private static String[] menuCodeArray  = {"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "999","VFQ", "VEQ", "ACQ", "RCQ", "PCQ", "VCS", "SPD", "LPD", "STK", "AFS", "EXT"};

    //array for data processing
    static String[] customersName = new String[]{};
    static String[] savingData = new String[]{};

    // ---- main method ----
    public static void main(String[] args) {
        //start system
        System.out.println( "\nHELLO ! WELCOME TO Cashier Queue \n");

        boolean loopSystem = true;

        while (loopSystem){
            //stay one sec
            stayOneSec();

            //display menu option
            MenuOption();

            //warning msg about burger count
            warningMsgAboutBurgerQty();

            //get menu code
            String menuCode = getMenuCode().toUpperCase();

            //Confirming that the menu codes are correct
            if(isMenuCodeCorrect(menuCode)){

                switch (menuCode) {
                    case "100":
                    case "VFQ":
                        viewAllQueues(queueOne, queueTwo, queueThree);
                        break;
                    case "101":
                    case "VEQ":
                        System.out.println(viewAllEmptyQueues(queueOne) ?  "Queue 1 isn't empty" : "Queue 1 is empty");
                        System.out.println(viewAllEmptyQueues(queueTwo) ?  "Queue 2 isn't empty" : "Queue 2 is empty");
                        System.out.println(viewAllEmptyQueues(queueThree) ?"Queue 3 isn't empty" : "Queue 3 is empty");
                        break;
                    case "102":
                    case "ACQ":
                        if (viewFullQueues(queueOne)){
                            addCustomerQueue(queueOne);
                            System.out.println("New customer added to queue one.");
                        } else if (viewFullQueues(queueTwo)) {
                            addCustomerQueue(queueTwo);
                            System.out.println("New customer added to queue Two.");
                        } else if (viewFullQueues(queueThree)) {
                            addCustomerQueue(queueThree);
                            System.out.println("New customer added to queue Three.");
                        }else {
                            System.out.println("All queues are full.");
                        }
                        break;
                    case "103":
                    case "RCQ":
                        boolean loopMeth103 = true;
                        while (loopMeth103){
                            System.out.print("\nEnter Queue if you want to remove customer: ");
                            Scanner getMenuCode = new Scanner(System.in);
                            String QueueNumber = getMenuCode.nextLine();

                            System.out.print("Enter customer position in queue if you want to remove customer : ");
                            Scanner getCustomerPosition  =  new Scanner(System.in);
                            String customerPosition = getCustomerPosition.nextLine();


                            if (validateQnum_Position(QueueNumber,customerPosition)){

                                if (QueueNumber.equals("1") && checkCustomerAreInQueue(queueOne,customerPosition)){
                                    loopMeth103 = false;
                                    removeCustomerQueue(queueOne,customerPosition);
                                } else if (QueueNumber.equals("2") && checkCustomerAreInQueue(queueTwo,customerPosition)) {
                                    loopMeth103 = false;
                                    removeCustomerQueue(queueTwo,customerPosition);;
                                } else if (QueueNumber.equals("3") && checkCustomerAreInQueue(queueThree,customerPosition)) {
                                    loopMeth103 = false;
                                    removeCustomerQueue(queueThree,customerPosition);
                                }else {
                                    System.out.println("In this queue hasn't any customer or In this Position hasn't any customer.Please ty again !");
                                }

                            }else {
                                System.out.println("\ninvalid Queue number or customer position . please Try again !");
                            }
                        }
                        break;
                    case "104":
                    case "PCQ":
                        boolean loopMeth104 = true;
                        while (loopMeth104){
                            System.out.print("\nEnter Queue if you want to serve customer: ");
                            Scanner getMenuCode = new Scanner(System.in);
                            String QueueNumber = getMenuCode.nextLine();

                            System.out.print("Enter customer position in queue if you want to served customer : ");
                            Scanner getCustomerPosition  =  new Scanner(System.in);
                            String customerPosition = getCustomerPosition.nextLine();

                            System.out.print("Enter customer name : ");
                            Scanner getName  =  new Scanner(System.in);
                            String name = getName.nextLine();

                            if (validateName(name)){

                                if (validateQnum_Position(QueueNumber,customerPosition)){
                                    if (QueueNumber.equals("1") && checkCustomerAreInQueue(queueOne,customerPosition)){
                                        loopMeth104 = false;
                                        BurgerQty = BurgerQty - 5;
                                        removeServedCustomer(queueOne,customerPosition);
                                        addNameInToArray(name);
                                        setSavingData(name,customerPosition,QueueNumber);
                                    } else if (QueueNumber.equals("2") && checkCustomerAreInQueue(queueTwo,customerPosition)) {
                                        loopMeth104 = false;
                                        BurgerQty = BurgerQty - 5;
                                        removeServedCustomer(queueTwo,customerPosition);
                                        addNameInToArray(name);
                                        setSavingData(name,customerPosition,QueueNumber);
                                    } else if (QueueNumber.equals("3") && checkCustomerAreInQueue(queueThree,customerPosition)) {
                                        loopMeth104 = false;
                                        BurgerQty = BurgerQty - 5;
                                        removeServedCustomer(queueThree,customerPosition);
                                        addNameInToArray(name);
                                        setSavingData(name,customerPosition,QueueNumber);
                                    }else {
                                        loopMeth104 = false;
                                        System.out.println("In this queue hasn't any customer or In this Position hasn't any customer.Please ty again !");
                                        System.out.println("If haven't any customer in 1st in queue , please add a customer");
                                    }

                                }else {
                                    System.out.println("\ninvalid Queue number or Qty . you can sell only 5 items for one customer . please Try again !");
                                }
                            }else {
                                System.out.println("invalid name format . Try again");
                            }
                        }
                        break;
                    case "105":
                    case "VCS":
                        customerSortACS();
                        break;
                    case "106":
                    case "SPD":
                        storeDataFile();
                        break;
                    case "107":
                    case "LPD":
                        loadDataFile();
                        break;
                    case "108":
                    case "STK":
                        remainingBurger();
                        break;
                    case "109":
                    case "AFS":
                        boolean loopMethod109 = true;
                        while (loopMethod109){
                            try {
                                System.out.println("\nYou can add up to a maximum of 50 burgers! (exit - 0)");
                                System.out.print("Enter the burger qty to be added : ");
                                Scanner getQty  =  new Scanner(System.in);
                                int addingQty = Integer.parseInt(getQty.nextLine());

                                if ( checkAddingQty(addingQty)){

                                    if (addBurger(addingQty)){
                                        loopMethod109 = false;
                                        System.out.println("\nAdded new burger Stock  , now have in "+BurgerQty+" qty");
                                    }else {
                                        System.out.println("\nCannot add that qty. because the maximum burger stock is 50.");
                                        System.out.println("current burger qty is "+BurgerQty+". you can add only " + (50-BurgerQty) + " burgers to maximum");

                                    }
                                }else {
                                    System.out.println("Invalid input.please try again. you can add only 1-50 integers");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid input.please try again. you can add only 1-50 integers ");
                            }
                        }
                        break;
                    case "999":
                    case "EXT":
                        exit();
                        loopSystem = false;
                        break;
                    default:
                        System.out.println("Something went wrong.");
                        break;
                }

            }else {
                System.out.println("your item code is not valid. please check and try again!");
                //stay one sec
                stayOneSec();
            }
        }
    }

    // ---- methods ----

    //stay one sec
    public static void stayOneSec(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("something went wrong - time delay");
        }
    }

    //MenuOption - method 100
    public static void MenuOption(){
        String[] menuOptions = {
                "*** menu ***\n",
                "100 or VFQ: View all Queues",
                "101 or VEQ: View all Empty Queues",
                "102 or ACQ: Add customer to a Queue",
                "103 or RCQ: Remove a customer from a Queue. (From a specific location)",
                "104 or PCQ: Remove a served customer",
                "105 or VCS: View Customers Sorted in alphabetical order (Do not use library sort routine)",
                "106 or SPD: Store Program Data into file",
                "107 or LPD: Load Program Data from file",
                "108 or STK: View Remaining burgers Stock",
                "109 or AFS: Add burgers to Stock",
                "999 or EXT: Exit the Program"
        };

        for (String item: menuOptions) {
            System.out.println(item);
        }

    }

    //warning msg for stock reaches a value of 10 burgers
    public static void warningMsgAboutBurgerQty(){
        if (BurgerQty <=10){
            System.out.println("\nWarning!: have less count of Burger stock. kindly add new stock.");
            System.out.println("You can choose 108 menu options to add burgers.");
        }
    }

    //get menu code
    public static String getMenuCode(){
        System.out.print("\nEnter menu item code : ");
        Scanner getMenuCode  =  new Scanner(System.in);
        String menuCode = getMenuCode.nextLine();
        return menuCode;
    }

    //Confirming that the menu codes are correct
    public static boolean isMenuCodeCorrect(String menuCode) {
        for (String code : menuCodeArray) {
            if (code.equals(menuCode)) {
                return true;
            }
        }
        return false;
    }

    //**************** *************

    //View all Queues.100
    public static void  viewAllQueues(String[] arrayOne, String[] arrayTwo, String[] arrayThree){
        System.out.println("***********");
        System.out.println("* Cashier *");
        System.out.println("***********");

        /*
         * 000
         * 111
         *  22
         *   3
         *   4
         *   5
         * */

        String[][] arrays = { arrayOne, arrayTwo, arrayThree };

        for (int i = 0; i < 5; i++) {
            String rowOne = (i < arrayOne.length) ? arrayOne[i] : " ";
            String rowTwo = (i < arrayTwo.length) ? arrayTwo[i] : " ";
            String rowThree = (i < arrayThree.length) ? arrayThree[i] : " ";

            System.out.println(" " + rowOne + " " + rowTwo + " " + rowThree);
        }
    }


    //View all Empty Queues. 101
    public static boolean  viewAllEmptyQueues(String[] arr){
        for (int i = 0; i< arr.length;i++){
            if (arr[i].equals("O")){
                return true;
            }
        }
        return false;
    }


    //Add customer to a Queue. 102
    public static void  addCustomerQueue(String[] arr){
        for (int i=0 ; i< arr.length;i++) {
            if (arr[i].equals("X")){
                arr[i] = "O";
                break;
            }
        }
    }
    //view full queue
    public static  boolean viewFullQueues(String[] arr){
        for (var i : arr) {
            if (i.equals("X")){
                return true;
            }
        }
        return false;
    }


    //Remove a customer from a Queue. (From a specific location)103
    public static void  removeCustomerQueue(String[] arr, String customerPosition){
        int cu_Position = Integer.parseInt(customerPosition)-1;
        if (arr[cu_Position].equals("O")) {
            arr[cu_Position] = "X";
            System.out.println("Customer removed");
        } else {
            System.out.println("Something went wrong!");
        }
    }
    //validate qnum and position (103 , 104)
    public static boolean validateQnum_Position(String Qnum , String cusPos){

        //variables for validating
        boolean isQueueValid =false;
        boolean isPositionValid =false;

        String QueueNumber = Qnum;
        String customerPosition = cusPos;

        //validate queue number
        for (String code : validQueueNumbers) {
            if (code.equals(QueueNumber)) {
                isQueueValid = true;
            }
        }

        //validate position
        for (String code : validPositionNumbers) {
            if (code.equals(customerPosition)) {
                isPositionValid = true;
            }
        }
        if(isQueueValid && isPositionValid){ return true; }
        return false;
    }
    //Check if customers are in queue and position
    public static Boolean checkCustomerAreInQueue(String[] arr, String customerPosition){

        int cu_Position = Integer.parseInt(customerPosition)-1;

        for (int i = 0; i< arr.length;i++){
            if (i == cu_Position){
                if (arr[i].equals("O")){
                    return true;
                }
            }
        }
        return false;
    }


    //Remove a served customer 104
    public static void  removeServedCustomer(String[] arr, String customerPosition){
        int cu_Position = Integer.parseInt(customerPosition)-1;
        if (arr[cu_Position].equals("O")) {
            arr[cu_Position] = "X";
            System.out.println("Served Customer removed");
        } else {
            System.out.println("Something went wrong!");
        }
    }
    //validate a name
    public static boolean validateName(String name ){
        // Regular expression pattern for a valid name
        String pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
        return name.matches(pattern);
    };
    //add name into array
    public static void addNameInToArray(String name){
        // Create a new array with increased length
        String[] newArray = new String[customersName.length + 1];

        for (int i = 0; i < customersName.length ; i++) {
            newArray[i] = customersName[i];
        }
        newArray[newArray.length - 1] = name ;
        customersName = newArray;

    }
    //save data to store in file
    public static void setSavingData(String name,String position,String QueueNumber){
        String filePath = "./data.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("\n");
        } catch (IOException e) {
            System.out.println("Error reading the file ");
        }        // Create a new array with increased length
        String[] newArray = new String[savingData.length + 1];

        for (int i = 0; i < savingData.length ; i++) {
            newArray[i] = savingData[i];
        }
        newArray[newArray.length - 1] = "Customer name: "+name +" - Queue number: "+QueueNumber+" - Position in Queue: "+position +" - Burger Qty: "+ 5 +" - Remaining Burger Qty: "+ BurgerQty;;
        savingData = newArray;
    }


    //View Customers Sorted in alphabetical order (Do not use library sort routine) 105
    public static void  customerSortACS(){
        for (int i = 0; i < customersName.length - 1; i++) {
            for (int j = i + 1; j < customersName.length; j++) {
                if (customersName[i].compareTo(customersName[j]) > 0) {
                    String temp = customersName[i];
                    customersName[i] = customersName[j];
                    customersName[j] = temp;
                }
            }
        }

        System.out.println("Customer's name list ,");

        for (String names : customersName) {
            System.out.println(names);
        }
    }


    //Store Program Data into file 106
    public static void  storeDataFile(){
        String filePath = "./data.txt";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String data : savingData) {
                writer.write(data);
                writer.newLine();// Add a new line after each element
            }
            System.out.println("Data has been written to the your file.");
        } catch (IOException e) {
            System.out.println("Have some errors while writing to the file");
        }
    }


    //Load Program Data from file. 107
    public static void  loadDataFile(){


    }


    //View Remaining burgers Stock. 108
    public static void  remainingBurger(){
        System.out.println("Have "+BurgerQty+" Qty in the stock");
    }


    //Add burgers to Stock. 109
    public static boolean addBurger(int addingQty){
        int newBurgerAdded = BurgerQty + addingQty;

        if (newBurgerAdded > 50){
            return false;
        }else {
            BurgerQty = BurgerQty+addingQty;
            return true;
        }
    }
    //check Adding Qty
    public static boolean checkAddingQty(int qty){
        if (qty >= 0 && qty <= 50){
            return true;
        }else
            return false;
    }


    //exit 999
    public  static  void  exit(){
        System.out.println("Exit the Program");
    }

}