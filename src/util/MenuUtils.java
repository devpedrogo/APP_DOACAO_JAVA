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

    public static <T extends Enum<T>> T lerEnum(String mensagem, Class<T> enumClass) {
        while (true) {
            System.out.println(mensagem);
            T[] constantes = enumClass.getEnumConstants();
            for (int i = 0; i < constantes.length; i++) {
                System.out.println((i + 1) + ". " + constantes[i]);
            }
            
            int escolha = lerInteiro("Escolha uma opção: ");
            if (escolha > 0 && escolha <= constantes.length) {
                return constantes[escolha - 1];
            }
            System.out.println("Erro: Opção inválida!");
        }
    }

}
