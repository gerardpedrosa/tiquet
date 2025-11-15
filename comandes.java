import java.util.Scanner;
import java.util.*;

public class comandes {

    //variables

    String nomClient = "";
    String comanda = "";
    double total = 0.0;
    boolean hiHaComanda = false;
    String producte = "";
    int quantitat = 0;
    double preu = 0.0;

    double subtotal = 0.0;

    //scanner
    Scanner esc = new Scanner(System.in);
    public static void main(String[] args) {
        comandes programa = new comandes();
        programa.inici();
    }
    public void inici() {
        int opcio;
    
        do {
            mostrarMenu();
            opcio = llegirInt();


            switch (opcio) {
                case 1: {
                    novaComanda();
                }
                break;
                case 2: {
                    actualitzarComanda();
                }
                break;
                case 3: {
                    veureTiquet();
                }
                break;
                case 4:{
                    sortir();
                }
                break; 
                default: {
                    System.out.println("Opció no vàlida");
                }
            }
            
        } while (opcio != 4);  
    }
    public void mostrarMenu() {

        System.out.println("\n----- GESTIÓ COMANDES RESTAURANT -----");
        System.out.println("1. Crear nova comanda");
        System.out.println("2. Actualitzar comanda anterior");
        System.out.println("3. Visualitzar últim tiquet");
        System.out.println("4. Sortir");
    }
    public void novaComanda() {

        System.out.println("\n----- NOVA COMANDA -----");
        System.out.print("\nIntrodueix el nom del client:");
        nomClient = esc.nextLine();
        comanda = "";
        total = 0;
        afegirProductes();
        hiHaComanda = true;
        System.out.println("\nEstem generant el tiquet ...");
        mostrarTiquet();
        System.out.println("\nComanda enregistrada correctament.");
        
    }
    public boolean comandaExistent() {
        
        if (!hiHaComanda) {
            System.out.println("No hi ha cap comanda");
            return false;
        }
        return true;

    }
    public void veureTiquet() {

        comandaExistent();
        System.out.println("--------------- ÚLTIM TIQUET ---------------");
        mostrarTiquet();
    }
    public void actualitzarComanda() {
        
        comandaExistent();

        System.out.println("\n----- ACTUALITZAR COMANDA -----");
        afegirProductes();
        System.out.println("\nS'està actualitzant la comanda…");
        mostrarTiquet();
        System.out.println("\nComanda actualitzada");
    }

    public void afegirProductes() {
        
        String mes = "s";

        while (mes.equalsIgnoreCase("s")) {
            System.out.print("Introdueix el producte: ");
            producte = esc.nextLine();

            System.out.println("Introdueix el preu del producte:");
            double preu = llegirDouble();

            System.out.println("Introdueix la quantitat que vols comprar:");
            int quantitat = llegirInt();

            subtotal = preu * quantitat;
            total = total + subtotal;

            comanda = comanda + alinear(producte, quantitat, preu, subtotal) + "\n";
            subtotal = Math.round(subtotal * 100.0) / 100.0;
            preu = Math.round(preu * 100.0) / 100.0;

            System.out.println("Vols afegir més productes? (s/n):");
            mes = esc.nextLine();
            
        }

    }
    
    public void mostrarTiquet() {
        
        comandaExistent();

        System.out.println("=============== TIQUET ===============");
        System.out.println("Client: " + nomClient + "\n");
        System.out.println("Producte"+ "\t" + "Quantitat" + "\t" + "Preu unit." + "\t" +   "Subtotal");
        System.out.println("--------------------------------------------------");
        System.out.println(comanda);
        System.out.println("--------------------------------------------------");

        double iva = total * 0.10;
        double totalAmbIva = total + iva;

        System.out.println("Total sense IVA:                         " + subtotal + " $");
        System.out.println("IVA (10%):                               " + iva + " $");
        System.out.println("TOTAL A PAGAR:                          " + totalAmbIva + " $");
        System.out.println("==================================================");
    }

    public String alinear(String producte, int quantitat, double preu, double subtotal) {
        
        String linea = "";

        for (int i = 0; i < 64; i++) {

            if (i == 0) {
            linea = linea + producte;
            i += producte.length() - 1;
        }
        else if (i == 16) {
            String q = "" + quantitat;
            linea = linea + q;
            i += q.length() - 1;
        }
        else if (i == 32) {
            String p = String.format("%.2f $", preu);
            linea = linea + p;
            i += p.length() - 1;
        }
        else if (i == 48) {
            String s = String.format("%.2f $", subtotal);
            linea = linea + s;
            i += s.length() - 1;
        }
        else {
            linea = linea + " ";
        }
        }
        return linea;
    }
    public void sortir() {
    
        System.out.println("Sortint del programa. Fins aviat!");

    }
    public int llegirInt() {
    int valor = 0;

        if (esc.hasNextInt()) {

            valor = esc.nextInt();
        } 
        else {
            System.out.print("Entrada no vàlida. Introdueix un enter: ");
            esc.next();
            valor = llegirInt();
        }

    esc.nextLine();
    return valor;
}
    public double llegirDouble() {
    
        String entrada = esc.nextLine().replace(",", ".");
        try {
            return Double.parseDouble(entrada);
        } 
        catch (NumberFormatException e) {
            System.out.print("Entrada no vàlida. Introdueix un decimal: ");
            return llegirDouble();
        }
    }
}