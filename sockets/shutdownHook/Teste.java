import java.util.Scanner;

class Teste {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println();
                System.out.println("Vou desligar...");
            }
        });
        System.out.println("Olá, estou executando.");
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print("Entre com uma mensagem: ");
            String m = s.nextLine();
        }
    }
}
