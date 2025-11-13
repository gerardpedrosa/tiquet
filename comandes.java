import java.util.Scanner;
import java.util.*;

public class comandes {

    //variables

    static String nomClient = "";
    static String comanda = "";
    static double total = 0.0;
    static boolean hiHaComanda = false;

    //scanner
    static Scanner esc = new Scanner(System.in);
    public static void main(String[] args) {
        
        int opcio;

        do {
            mostrarMenu();
            opcio = llegirInt();

            switch (opcio) {
                case 1: {
                    novaComanda();
                }
                case 2: {
                    actualitzarComanda();
                }
                case 3: {
                    veureTiquet();
                }
                case 4:{
                    sortir();
                }
                 
                default: {
                    System.out.println("Opció no vàlida");
                }
                
            }
            
        } while (opcio != 4);  
    }
    public static void mostrarMenu() {

        System.out.println("\n----- GESTIÓ COMANDES RESTAURANT -----");
        System.out.println("1. Crear nova comanda");
        System.out.println("2. Actualitzar comanda anterior");
        System.out.println("3. Visualitzar últim tiquet");
        System.out.println("4. Sortir");
    }
    public static void novaComanda() {

        System.out.println("\n----- NOVA COMANDA -----");
        System.out.print("\nIntrodueix el nom del client:");
        nomClient = esc.nextLine();
        comanda = "";
        total = 0;
        afegirProductes();
        hiHaComanda = true;
        System.out.println("\nEstem generant el tiquet…");
        mostrarTiquet();
        System.out.println("\nComanda enregistrada correctament.");

    }
    public static void comandaExistent() {
        
        if (!hiHaComanda) {
            System.out.println("No hi ha cap comanda");
            return;
        }
    }
    public static void veureTiquet() {
        comandaExistent();
        System.out.println("--------------- ÚLTIM TIQUET ---------------");
        mostrarTiquet();
    }
    public static void actualitzarComanda() {
        
        comandaExistent();

        System.out.print("\nIntrodueix un nou producte: ");
        afegirProductes();
        System.out.println("\nS'està actualitzant la comanda…");
        mostrarTiquet();
        System.out.println("\nComanda actualitzada");
    }

    public static void afegirProductes() {
        
        String mes = "s";

        while (mes.equalsIgnoreCase("s")) {
            System.out.print("Introdueix el producte: ");
            String producte = esc.nextLine();

            System.out.println("Introdueix el preu del producte:");
            double preu = llegirDouble();

            System.out.println("Introdueix la quantitat que vols comprar:");
            int quantitat = llegirInt();

            double subtotal = preu * quantitat;

            comanda = comanda + producte + "\t" + quantitat + "\t" + preu + " €\t" + subtotal + " €\n";

            System.out.println("Vols afegir més productes? (s/n):");
            mes = esc.nextLine();
        }

    }
    public static void mostrarTiquet() {
        
    }
    public static void sortir() {

    }
    public static int llegirInt() {
        while (true) {
            try {
                String entrada = esc.nextLine();
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Entrada no vàlida. Si us plau, introdueix un nombre enter: ");
            }
        }
    }
    public static double llegirDouble() {
        while (true) {
            try {
                String entrada = esc.nextLine();
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print("Entrada no vàlida. Si us plau, introdueix un nombre decimal: ");
            }
        }
    }
}