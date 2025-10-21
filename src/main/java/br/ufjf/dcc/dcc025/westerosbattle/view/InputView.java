package br.ufjf.dcc.dcc025.westerosbattle.view;

import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;

import java.util.Scanner;

public class InputView {
    private Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public Direction readDirection(){
        System.out.println("Escolha a direção: ");
        for (int i = 0; i < Direction.values().length; i++){
            System.out.println((i + 1) + ". " + Direction.values()[i].name());
        }
        System.out.print("Digite de 1 a " + (Direction.values().length) + ": ");
        int direction = scanner.nextInt() - 1;
        while (direction < 1 || direction >= Direction.values().length){
            System.out.println("Valor inválido (1 a" + Direction.values().length + "), digite novamente: ");
        }
        scanner.nextLine();
        System.out.println("Direction escolhida = " + Direction.values()[direction].name());

        return Direction.values()[direction];
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
        System.out.println(prompt);
        String text = scanner.nextLine().trim();

        while(text.isEmpty()){
            System.out.println("Texto vazio, digite novamente: ");
            text = scanner.nextLine().trim();
        }

        return text;
    }

    public int readChoice(String message, int min, int max){
        int choice = -1;
        System.out.println(message);
        while (choice < min || choice > max){
            if(scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < min || choice > max) {
                    System.out.println("Valor inválido, digite novamente: ");
                }
            }   else{
                    System.out.println("Digite um número válido: ");
                    scanner.nextLine();
                }
        }

        return choice;
    }

    public void closeScanner(){
        scanner.close();
    }
}
