package org.tomasBarauskas;

import org.tomasBarauskas.util.StartProgram;
// Pavel: paketai neturi tureti didziuju raidziu, tik klases
// Tomas: kazkaip nesuvedziau, kad tai paketas :)

/**
 * Hello world!
 */
public class App {
    // Tomas: visur tiek prirasinejau nes kaip suprantu nesulaukus is Jusu approve'lo del sakos prijungimo visi tie komentarai dingo is sistemos

    public static void main(String[] args) {
        StartProgram startProgram = new StartProgram();
        startProgram.StartProgramMenu();

        // Pavel: App failas kuris turi tik main metoda, neturi tureti jokiu implementaciju. Siulau pakoreguoti ir iskelti i atitinkama struktura kur ateiti bus lengviau palaikyti ir keisti.
        //Max kiek main gali tureti eiluciu 2-3
        // Tomas: teisingai supratau ko praset?

        // Pavel: App.java reiktu refaktorinti perdarant i OOP
        // Tomas: gal galetumet placiau pakomentuot? Zinokit nesuprantu... :(
        // ar turit omenyje, kad metodus kaip pvz: private void editFinanceRecord(), private void editFinanceRecordAmount(FinanceRecord financeRecord),
        // private void editFinanceRecordInfo(FinanceRecord financeRecord) kelti is StartProgram klases i nauja klase ir per ja juos kviesti?

    }
}
