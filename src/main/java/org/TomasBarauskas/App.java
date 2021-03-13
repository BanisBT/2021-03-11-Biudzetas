package org.TomasBarauskas;

import org.TomasBarauskas.excetions.NoRecordByID;
import org.TomasBarauskas.modul.ExpenseRecord;
import org.TomasBarauskas.modul.FinanceRecord;
import org.TomasBarauskas.modul.IncomeRecord;
import org.TomasBarauskas.service.Budget;
import org.TomasBarauskas.service.BudgetImpl;
import org.TomasBarauskas.service.FinanceRecordFileManager;
import org.TomasBarauskas.service.FinanceRecordFileManagerImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static Budget budget = new BudgetImpl();
    private static FinanceRecordFileManager fileManager = new FinanceRecordFileManagerImpl();
    private static Scanner sc = new Scanner(System.in);
    private static App main = new App();
    private static String pathFinanceRecord = "/Users/Gabi/IdeaProjects/2021-03-10-biudzetas/src/main/java/org/TomasBarauskas/files/FinanceRecord.txt";

    public static void main(String[] args) {
        budget.addFinanceRecord(new IncomeRecord(100, "Avansas"));
        budget.addFinanceRecord(new IncomeRecord(200, "Loterija"));
        budget.addFinanceRecord(new IncomeRecord(1000, "Alga"));

        budget.addFinanceRecord(new ExpenseRecord(10, "Ledai"));
        budget.addFinanceRecord(new ExpenseRecord(35, "Baras"));
        budget.addFinanceRecord(new ExpenseRecord(100, "TV"));

        String ivestis = "";
        while (!ivestis.equals("11")) {
            main.startMenu();
            ivestis = sc.nextLine();

            switch (ivestis) {
                case "1":
                    main.addIncomeRecord();
                    break;
                case "2":
                    main.addExpenseRecord();
                    break;
                case "3":
                    main.getIncomesRecords();
                    break;
                case "4":
                    main.getExpensesRecords();
                    break;
                case "5":
                    main.removeIncomeRecordByID();
                    break;
                case "6":
                    main.removeExpenseRecordByID();
                    break;
                case "7":
                    main.editFinanceRecord();
                    break;
                case "8":
                    System.out.println("Jusu saskaitos balansas yra: " + budget.balance());
                    break;
                case "9":
                    main.writeFinanceRecordIntoFile();
                    break;
                case "10":
                    main.getFinanceRecordsFromFile();
                    break;
                case "11":
                    break;
                default:
                    System.out.println("Prasome pasirinkti is meniu opciju");
                    break;
            }
        }
    }

    private void startMenu() {
        System.out.println("[1] Ivesti pajemu irasa");
        System.out.println("[2] Ivesti islaidu irasa");
        System.out.println("[3] Gauti pajemu irasus");
        System.out.println("[4] Gauti islaidu irasus");
        System.out.println("[5] Pasalinti pajemu irasa");
        System.out.println("[6] Pasalinti islaidu irasa");
        System.out.println("[7] Iraso redagavimas");
        System.out.println("[8] Gauti saskaitos balansa");
        System.out.println("[9] Irasyti duomenis i faila");
        System.out.println("[10] Gauti irasus is failo");
        System.out.println("[11] Iseiti");
    }

    private void addIncomeRecord() {
        boolean isNumber = true;

        while (isNumber) {
            System.out.println("Iveskite pajemu suma");
            String incomeAmountString = sc.nextLine();

            try {
                float incomeAmount = Float.parseFloat(incomeAmountString);
                System.out.println("Pajemu iraso info");
                String incomeInfo = sc.nextLine();
                budget.addFinanceRecord(new IncomeRecord(incomeAmount, incomeInfo));
                isNumber = false;
            } catch (NumberFormatException e) {
                System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, naudokite taska \".\" vietoje kablelio");
            }
        }
    }

    private void addExpenseRecord() {
        System.out.println("Iveskite islaidu suma");
        String expenseAmountString = sc.nextLine();

        try {
            float expenseAmount = Float.parseFloat(expenseAmountString);
            System.out.println("Islaidu iraso info");
            String expenseInfo = sc.nextLine();
            budget.addFinanceRecord(new ExpenseRecord(expenseAmount, expenseInfo));
        } catch (NumberFormatException e) {
            System.out.println("Klaida! Reikalingas skaicius. Jei bandote ivesti skaiciu per kableli, naudokite taska \".\" vietoje kablelio");
        }
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
        ArrayList<Long> incomesID = budget.getIncomeRecordsID();
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
        ArrayList<Long> expensesID = budget.getExpenseRecordsID();
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
        ArrayList<Long> financeID = budget.getFinanceRecordsID();
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
    private void writeFinanceRecordIntoFile(){
        List<FinanceRecord> financeRecords = budget.getFinanceRecords();
        try {
            fileManager.financeRecordFileWriter(financeRecords,pathFinanceRecord,false);
            System.out.println("Duomenys sekmingai irasyti i faila");
        } catch (IOException e) {
            System.out.println("Klaida! Nepavyko irasyti duomenu i faila");
        }
    }
    private void getFinanceRecordsFromFile(){
        List<FinanceRecord> financeRecordsFromFile = null;
        try {
            financeRecordsFromFile = fileManager.getFinanceRecordFromFile(pathFinanceRecord);
        } catch (IOException e) {
            System.out.println("Klaida! Nepavyko nuskaityti duomenu is failo");
        }
        System.out.println(financeRecordsFromFile);
    }
}
