package br.ufjf.dcc.dcc025.westerosbattle.model.strategy;

import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Board;
import br.ufjf.dcc.dcc025.westerosbattle.model.entities.Character;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.ActionType;
import br.ufjf.dcc.dcc025.westerosbattle.model.enums.Direction;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BotStrategy implements PlayerStrategy {
    private Random random;

    public BotStrategy() {
        this.random = new Random();
    }

    @Override
    public PlayerAction decideTurn(Character actor, List<Character> enemies, Board board) {
        List<Character> aliveEnemies = enemies.stream()
                .filter(Character::isAlive)
                .toList();

        if (aliveEnemies.isEmpty()) {
            return new PlayerAction(ActionType.MOVE, getRandomDirection());
        }
        List<Character> inRange = aliveEnemies.stream().filter(e -> board.distance(actor, e) <= actor.getRange()).toList();
        if (!inRange.isEmpty()) {
            Character weakest = inRange.stream()
                    .min(Comparator.comparingDouble(Character::getHealth))
                    .orElse(inRange.get(0));

            System.out.println("[BOT] " + actor.getName() + " decidiu atacar " + weakest.getName() + " (HP: " + weakest.getHealth() + ")");
            return new PlayerAction(ActionType.ATTACK, weakest);
        }
        Character nearest = findNearestEnemy(actor, aliveEnemies, board);
        Direction dir = getBestDirectionTowards(actor, nearest, board);

        System.out.println("[BOT] " + actor.getName() + " decidiu mover para " + dir + " (aproximando de " + nearest.getName() + ")");
        return new PlayerAction(ActionType.MOVE, dir);
    }

    private Character findNearestEnemy(Character actor, List<Character> enemies, Board board) {
        return enemies.stream()
                .min(Comparator.comparingInt(e -> board.distance(actor, e)))
                .orElse(enemies.get(0));
    }

    private Direction getBestDirectionTowards(Character actor, Character target, Board board) {
        int[] actorPos = board.getPosition(actor);
        int[] targetPos = board.getPosition(target);

        if (actorPos == null || targetPos == null) {
            return getRandomDirection();
        }

        int deltaLine = targetPos[0] - actorPos[0];
        int deltaColumn = targetPos[1] - actorPos[1];

        if (deltaLine == 0 && deltaColumn == 0) {
            return getRandomDirection();
        }


        if (deltaLine > 0 && deltaColumn > 0) return Direction.DIREITA_BAIXO;
        if (deltaLine > 0 && deltaColumn < 0) return Direction.ESQUERDA_BAIXO;
        if (deltaLine < 0 && deltaColumn > 0) return Direction.DIREITA_CIMA;
        if (deltaLine < 0 && deltaColumn < 0) return Direction.ESQUERDA_CIMA;


        if (deltaLine > 0) return Direction.BAIXO;
        if (deltaLine < 0) return Direction.CIMA;


        if (deltaColumn > 0) return Direction.DIREITA;
        if (deltaColumn < 0) return Direction.ESQUERDA;

        return getRandomDirection();
    }

    private Direction getRandomDirection() {
        Direction[] directions = Direction.values();
        return directions[random.nextInt(directions.length)];
    }
}
