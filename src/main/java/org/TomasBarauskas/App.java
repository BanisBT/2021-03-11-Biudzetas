package org.TomasBarauskas;

import org.TomasBarauskas.excetions.NoRecordByID;
import org.TomasBarauskas.modul.ExpenseRecord;
import org.TomasBarauskas.modul.IncomeRecord;
import org.TomasBarauskas.service.Budget;
import org.TomasBarauskas.service.BudgetImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static Budget budget = new BudgetImpl();
    private static Scanner sc = new Scanner(System.in);
    private static App main = new App();

    public static void main(String[] args) {
        budget.addIncomeRecord(100, "Avansas");
        budget.addIncomeRecord(200, "Loterija");
        budget.addIncomeRecord(1000, "Alga");

        budget.addExpenseRecord(10, "Ledai");
        budget.addExpenseRecord(35, "Baras");
        budget.addExpenseRecord(100, "TV");

        String ivestis = "";
        while (!ivestis.equals("8")) {
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
                    System.out.println("Jusu saskaitos balansas yra: " + budget.balance());
                    break;
                case "8":
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
        System.out.println("[7] Gauti saskaitos balansa");
        System.out.println("[8] Iseiti");
    }

    private void addIncomeRecord() {
        System.out.println("Iveskite pajemu suma");
        long incomeAmount = sc.nextLong();
        sc.nextLine();
        System.out.println("Pajemu iraso info");
        String incomeInfo = sc.nextLine();
        budget.addIncomeRecord(incomeAmount, incomeInfo);
    }

    private void addExpenseRecord() {
        System.out.println("Iveskite islaidu suma");
        long expenseAmount = sc.nextLong();
        sc.nextLine();
        System.out.println("Islaidu iraso info");
        String expenseInfo = sc.nextLine();
        budget.addExpenseRecord(expenseAmount, expenseInfo);
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
        long incomeRecordIdToDelete = sc.nextLong();
        sc.nextLine();

        try {
            budget.removeIncomeRecord(incomeRecordIdToDelete);
            System.out.println("Pajemu irasas sekmingai istrintas");
        } catch (NoRecordByID e) {
            System.out.println("Pagal pateikta ID pajemu irasas nerastas");
        }
    }

    private void removeExpenseRecordByID() {
        ArrayList<Long> expensesID = budget.getExpenseRecordsID();
        System.out.println("Koki islaidu irasa noretumet pasalinti?" + "\n" + "Jusu turimu islaidu irasu ID sarasas:");
        System.out.println(expensesID);
        long expenseRecordIdToDelete = sc.nextLong();
        sc.nextLine();

        try {
            budget.removeExpenseRecord(expenseRecordIdToDelete);
            System.out.println("Islaidu irasas sekmingai istrintas");
        } catch (NoRecordByID e) {
            System.out.println("Pagal pateikta ID islaidu irasas nerastas");
        }
    }
}
