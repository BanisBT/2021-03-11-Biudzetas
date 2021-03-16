package org.tomasBarauskas.util;

import org.tomasBarauskas.excetions.NoRecordByID;
import org.tomasBarauskas.modul.ExpenseRecord;
import org.tomasBarauskas.modul.FinanceRecord;
import org.tomasBarauskas.modul.IncomeRecord;
import org.tomasBarauskas.service.Budget;
import org.tomasBarauskas.service.BudgetImpl;
import org.tomasBarauskas.service.FinanceRecordFileManager;
import org.tomasBarauskas.service.FinanceRecordFileManagerImpl;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StartProgram {
    private Budget budget = new BudgetImpl();
    private FinanceRecordFileManager fileManager = new FinanceRecordFileManagerImpl();
    private Scanner sc = new Scanner(System.in);
    private String pathFinanceRecord = "/Users/Gabi/IdeaProjects/2021-03-10-biudzetas/src/main/java/org/TomasBarauskas/files/FinanceRecord.txt";
    private String pathTest = "/Users/Gabi/IdeaProjects/2021-03-10-biudzetas/src/main/java/org/tomasBarauskas/files/Test.ser";
    // Pavel: as gal bendrai siulyciau neprisiristi prie savo pc failines sistemos. Galvokim kad aplikacija gali paleisti bet koks vartotojas. Kitas klausimas apsvarstimui, ar verta bendrai vartotojui rupintis kur saugomas failas?
    // Tomas: Jus turite galvoje, kad faila saugoti kazkur ne savo pc ar, kad failas butu kazkaip prikabintas prie pacios programos?
    // geriau kintamojo path nedaryti, tiesei i metoda kelia nurodyt?

    // Elvinas: kodel jie statiniai?
    // private static Scanner sc = new Scanner(System.in);
    //     private static App main = new App();
    //+    private static String pathFinanceRecord = "/Users/Gabi/IdeaProjects/2021-03-10-biudzetas/src/main/java/org/TomasBarauskas/files/FinanceRecord.txt";

    // Tomas: public static void main(String[] args) statinis metodas tai ir kintamieji buvo statiniai, bet perkelus cia galima jau pakeisti

    public StartProgram() {
    }

    public void StartProgramMenu() {
        budget.addFinanceRecord(new IncomeRecord(100, "Avansas"));
        budget.addFinanceRecord(new IncomeRecord(200, "Loterija"));
        budget.addFinanceRecord(new IncomeRecord(1000, "Alga"));

        budget.addFinanceRecord(new ExpenseRecord(10, "Ledai"));
        budget.addFinanceRecord(new ExpenseRecord(35, "Baras"));
        budget.addFinanceRecord(new ExpenseRecord(100, "TV"));

        String ivestis = "";
        while (!ivestis.equals("10")) {
            startMenu();
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    addRecord();
                    break;
                case "2":
                    getIncomesRecords();
                    break;
                case "3":
                    getExpensesRecords();
                    break;
                case "4":
                    removeIncomeRecordByID();
                    break;
                case "5":
                    removeExpenseRecordByID();
                    break;
                case "6":
                    editFinanceRecord();
                    break;
                case "7":
                    System.out.println("Jusu saskaitos balansas yra: " + budget.balance());
                    break;
                case "8":
                    testSerWrite();
                    // writeFinanceRecordIntoFile();
                    break;
                case "9":
                    testSerRead();
                    // getFinanceRecordsFromFile();
                    break;
                case "10":
                    // Elvinas: 11 galima padaryti konstanta
                    //          String ivestis = "";
                    //-        while (!ivestis.equals("9")) {
                    //+        while (!ivestis.equals("11")) {
                    // Tomas: nesuprantu. Padaryt vietoj 11 pvz. "End"? Ar cia apie enum kalbam?
                    break;
                default:
                    System.out.println("Prasome pasirinkti is meniu opciju");
                    break;
            }
        }
    }

    private void startMenu() {
        System.out.println("[1] Ivesti irasa");
        System.out.println("[2] Gauti pajemu irasus");
        System.out.println("[3] Gauti islaidu irasus");
        System.out.println("[4] Pasalinti pajemu irasa");
        System.out.println("[5] Pasalinti islaidu irasa");
        System.out.println("[6] Iraso redagavimas");
        System.out.println("[7] Gauti saskaitos balansa");
        System.out.println("[8] Irasyti duomenis i faila");
        System.out.println("[9] Gauti irasus is failo");
        System.out.println("[10] Iseiti");
    }

    private void addRecord() {
        boolean isNumber = true;

        while (isNumber) {
            System.out.println("Iveskite iraso suma");
            String recordAmountString = sc.nextLine();

            try {
                float recordAmount = Float.parseFloat(recordAmountString);
                System.out.println("Iraso info");
                String recordInfo = sc.nextLine();
                isNumber = chooseTypeOfRecord(recordAmount, recordInfo);

            } catch (NumberFormatException e) {
                System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, naudokite taska \".\" vietoje kablelio");
            }
        }
        // Elvinas: kartojasi funkcionalumas. galima padaryti nauja funkcija
        //  private void addExpenseRecord() {
        //         System.out.println("Iveskite islaidu suma");
        //-        long expenseAmount = sc.nextLong();
        //-        sc.nextLine();
        //-        System.out.println("Islaidu iraso info");
        //-        String expenseInfo = sc.nextLine();
        //-        budget.addFinanceRecord(new ExpenseRecord(expenseAmount, expenseInfo));
        //+        String expenseAmountString = sc.nextLine();
        //+
        //+        try {
        //+            float expenseAmount = Float.parseFloat(expenseAmountString);
        //+            System.out.println("Islaidu iraso info");
        //+            String expenseInfo = sc.nextLine();
        //+            budget.addFinanceRecord(new ExpenseRecord(expenseAmount, expenseInfo));
        //+        } catch (NumberFormatException e) {
        //+            System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, naudokite taska \".\" vietoje kablelio");
        // Tomas: Teisingai supratau?
    }

    private boolean chooseTypeOfRecord(float recordAmount, String recordInfo) {
        String ivestis = "";
        boolean choiceEnd = true;
        boolean isNumber = true;

        while (choiceEnd) {
            System.out.println("Koks bus iraso tipas  \n[1] - Pajemu irasas [2] - Islaidu irasas");
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    budget.addFinanceRecord(new IncomeRecord(recordAmount, recordInfo));
                    choiceEnd = false;
                    isNumber = false;
                    break;
                case "2":
                    budget.addFinanceRecord(new ExpenseRecord(recordAmount, recordInfo));
                    choiceEnd = false;
                    isNumber = false;
                    break;
                case "3":
                    System.out.println("Galimi tik du varianta iraso");
                    break;
            }
        }
        return isNumber;
    }

    private void getIncomesRecords() {
        List<IncomeRecord> incomeRecords = budget.getIncomeRecords();
        for (IncomeRecord record : incomeRecords) {
            System.out.println(record);
        }
    }

    private void getExpensesRecords() {
        List<ExpenseRecord> expenseRecords = budget.getExpenseRecords();
        for (ExpenseRecord record : expenseRecords) {
            System.out.println(record);
        }
    }

    private void removeIncomeRecordByID() {
        List<Long> incomesID = budget.getIncomeRecordsID();
        System.out.println("Koki pajemu irasa noretumet pasalinti?" + "\n" + "Jusu turimu pajemu irasu ID sarasas:");
        System.out.println(incomesID);
        String incomeRecordIdToDeleteString = sc.nextLine();

        try {
            long incomeRecordIdToDelete = Long.parseLong(incomeRecordIdToDeleteString);
            try {
                budget.removeFinanceRecord(incomeRecordIdToDelete);
                System.out.println("Pajemu irasas sekmingai istrintas");
            } catch (NoRecordByID e) {
                System.out.println("Pagal pateikta ID pajemu irasas nerastas");
            }
        } catch (NumberFormatException e) {
            System.out.println("Klaida! Iraso ID gali buti tik skaicius");
        }
    }

    private void removeExpenseRecordByID() {
        List<Long> expensesID = budget.getExpenseRecordsID();
        System.out.println("Koki islaidu irasa noretumet pasalinti?" + "\n" + "Jusu turimu islaidu irasu ID sarasas:");
        System.out.println(expensesID);
        String expenseRecordIdToDeleteString = sc.nextLine();

        try {
            long expenseRecordIdToDelete = Long.parseLong(expenseRecordIdToDeleteString);
            try {
                budget.removeFinanceRecord(expenseRecordIdToDelete);
                System.out.println("Islaidu irasas sekmingai istrintas");
            } catch (NoRecordByID e) {
                System.out.println("Pagal pateikta ID islaidu irasas nerastas");
            }
        } catch (NumberFormatException e) {
            System.out.println("Klaida! Iraso ID gali buti tik skaicius");
        }
    }

    private void editFinanceRecord() {
        List<Long> financeID = budget.getFinanceRecordsID();
        System.out.println("Kokio iraso informacija noretumet redaguoti?" + "\n" + "Jusu turimu irasu ID sarasas");
        System.out.println(financeID);
        long financeRecordIdToEdit = sc.nextLong();
        sc.nextLine();

        try {
            FinanceRecord financeRecordToEdit = budget.getFinanceRecordByID(financeRecordIdToEdit);
            editFinanceRecordAmount(financeRecordToEdit);
            editFinanceRecordInfo(financeRecordToEdit);

        } catch (NoRecordByID e) {
            System.out.println("Pagal pateikta ID irasas nerastas");
        }
    }

    private void editFinanceRecordAmount(FinanceRecord financeRecord) {
        String ivestis = "";

        while (!ivestis.equals("2")) {
            System.out.println("Suma " + financeRecord.getAmount() + "Eur");
            System.out.println("[1] - Redaguoti  [2] - Toliau");
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    System.out.println("Kokia bus nauja iraso suma");
                    String amountNewString = sc.nextLine();

                    try {
                        float amountNew = Float.parseFloat(amountNewString);
                        financeRecord.setAmount(amountNew);
                        ivestis = "2";
                    } catch (NumberFormatException e) {
                        System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, naudokite taska \".\" vietoje kablelio");
                    }
                    break;
                case "2":
                    break;
                default:
                    System.out.println("Galimi pasirinkimai tik is meniu");
                    break;
            }
        }
    }

    private void editFinanceRecordInfo(FinanceRecord financeRecord) {
        String ivestis = "";

        while (!ivestis.equals("2")) {
            System.out.println("Iraso info - " + financeRecord.getInfo());
            System.out.println("[1] - Redaguoti  [2] - Toliau");
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    System.out.println("Kokia bus nauja iraso informacija");
                    String infoNew = sc.nextLine();
                    financeRecord.setInfo(infoNew);
                    ivestis = "2";
                    break;
                case "2":
                    break;
                default:
                    System.out.println("Galimi pasirinkimai tik is meniu");
                    break;
            }
        }
    }

    private void writeFinanceRecordIntoFile() {
        List<FinanceRecord> financeRecords = budget.getFinanceRecords();
        try {
            fileManager.financeRecordFileWriter(financeRecords, pathTest, false);
            System.out.println("Duomenys sekmingai irasyti i faila");
        } catch (IOException e) {
            System.out.println("Klaida! Nepavyko irasyti duomenu i faila");
        }
    }

    private void getFinanceRecordsFromFile() {
        List<FinanceRecord> financeRecordsFromFile = null;
        try {
            financeRecordsFromFile = fileManager.getFinanceRecordFromFile(pathFinanceRecord);
        } catch (IOException e) {
            System.out.println("Klaida! Nepavyko nuskaityti duomenu is failo");
        }
        System.out.println(financeRecordsFromFile);
    }
    private void testSerWrite(){
        List<FinanceRecord> financeRecordList = budget.getFinanceRecords();
        for (FinanceRecord record : financeRecordList){
            try {
                fileManager.testSerializable(record);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void testSerRead(){
        List<FinanceRecord> records = null;
        try {
             records = fileManager.testGetFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(records);
    }
}
