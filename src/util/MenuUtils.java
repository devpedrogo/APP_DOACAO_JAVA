package util;

import java.util.Scanner;

public class MenuUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        String entrada = scanner.nextLine();
        while (entrada.trim().isEmpty()) {
            System.out.println("Erro: O campo não pode ser vazio.");
            System.out.print(mensagem);
            entrada = scanner.nextLine();
        }
        return entrada;
    }

    public static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim(); // Lê como String para verificar se está vazio

            if (entrada.isEmpty()) {
                System.out.println("Erro: O campo não pode estar vazio. Digite um número.");
                continue; // Volta para o início do loop
            }

            try {
                return Integer.parseInt(entrada); // Tenta converter a String para int
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite apenas números inteiros válidos.");
            }
        }
    }

}
