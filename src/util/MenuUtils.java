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
            String entrada = scanner.nextLine().trim();

            if (entrada.isEmpty()) {
                System.out.println("Erro: O campo não pode estar vazio. Digite um número.");
                continue; 
            }

            try {
                return Integer.parseInt(entrada);
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
