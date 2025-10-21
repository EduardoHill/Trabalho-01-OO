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
        System.out.print("Digite de 1 a 10");
        int direction = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println("Direction escolhida = " + Direction.values()[direction].name());

        return Direction.values()[direction];
    }

    public Boolean readBoolean(){
        System.out.print("Deseja confirmar a ação (S/N) ?");
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

        if(text.isEmpty()){
            System.out.println("Texto vazio, digite novamente: ");
            text = scanner.nextLine().trim();
        }

        return text;
    }
}
