package br.ufjf.dcc.dcc025.westerosbattle.view;

import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;

import java.util.Scanner;

public class InputView {
    private Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public Direction readDirection() {
        Direction[] directions = Direction.values();
        int choice = -1;
        System.out.println("Escolha a direção:");
        for (int i = 0; i < directions.length; i++) {
            System.out.println((i + 1) + ". " + directions[i].name());
        }
        System.out.print("Digite de 1 a " + directions.length + ": ");
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        }
        scanner.nextLine();
        while (choice < 1 || choice > directions.length) {
            System.out.println("Valor inválido (1 a " + directions.length + "), digite novamente:");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            }
            scanner.nextLine();
        }
        Direction selected = directions[choice - 1];
        System.out.println("Direção escolhida = " + selected.name());
        return selected;
    }

    public Boolean readBoolean(String message){
        System.out.print(message + "(S/N) ?");
        String answer = scanner.nextLine().trim().toUpperCase();
        while (!answer.equals("S") && !answer.equals("N")) {
            System.out.print("Opção inválida digite novamente (S/N): ");
            answer = scanner.nextLine().trim().toUpperCase();;
        }

        return answer.equals("S");
    }

    public String readString(String prompt){
        System.out.print(prompt);
        String text = scanner.nextLine().trim();

        while(text.isEmpty()){
            System.out.println("Texto vazio, digite novamente: ");
            text = scanner.nextLine().trim();
        }

        return text;
    }

    public int readChoice(String message, int min, int max) {
        int choice = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print(message + " ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    valid = true;
                } else {
                    System.out.println("Valor fora do intervalo (" + min + " - " + max + ").");
                }
            } else {
                System.out.println("Entrada inválida! Digite um número.");
            }
            scanner.nextLine();
        }
        return choice;
    }

    public void closeScanner(){
        scanner.close();
    }
}
