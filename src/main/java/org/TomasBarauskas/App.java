package org.TomasBarauskas;

import org.TomasBarauskas.modul.ExpenseRecord;
import org.TomasBarauskas.modul.IncomeRecord;
import org.TomasBarauskas.service.Budget;
import org.TomasBarauskas.service.BudgetImpl;

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
        while (!ivestis.equals("5")) {
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
        System.out.println("[5] Iseiti");
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
}
