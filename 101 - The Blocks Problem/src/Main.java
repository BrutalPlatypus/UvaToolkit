import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ArrayList<LinkedList<Integer>> stacks;
    private static LinkedList<Integer> firstStack;
    private static LinkedList<Integer> secondStack;
    private static int firstIndex;
    private static int secondIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            stacks = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                LinkedList<Integer> stack = new LinkedList<>();
                stack.add(i);
                stacks.add(stack);
            }

            while (scanner.hasNext()) {
                String movementType = scanner.next();
                if (movementType.equals("quit"))
                    break;
                int first = scanner.nextInt();
                String placementType = scanner.next();
                int second = scanner.nextInt();

                if (isValidCommand(first, second)) {
                    setStackAndIndex(first, second);
                    if (movementType.equals("move") && placementType.equals("onto"))
                        moveOnto(first);
                    else if (movementType.equals("move") && placementType.equals("over"))
                        moveOver(first);
                    else if (movementType.equals("pile") && placementType.equals("onto"))
                        pileOnto();
                    else if (movementType.equals("pile") && placementType.equals("over"))
                        pileOver();
                }
            }
            printOutput();
        }
    }

    private static boolean isValidCommand(int first, int second) {
        if (first == second)
            return false;
        for (LinkedList<Integer> stack : stacks) {
            if (stack.contains(first) && stack.contains(second))
                return false;
        }
        return true;
    }

    private static void printOutput() {
        for (int i = 0; i < stacks.size(); i++) {
            System.out.print(i + ":");
            LinkedList<Integer> stack = stacks.get(i);
            for (Integer integer : stack) {
                System.out.print(" " + integer);
            }
            System.out.println();
        }
    }

    private static void setStackAndIndex(int firstElement, int secondElement) {
        for (LinkedList<Integer> stack : stacks) {
            if (stack.contains(firstElement)) {
                firstStack = stack;
                firstIndex = stack.indexOf(firstElement);
            }
            if (stack.contains(secondElement)) {
                secondStack = stack;
                secondIndex = stack.indexOf(secondElement);
            }
        }
    }

    private static void moveOnto(Integer first) {
        for (int i = firstStack.size() - 1; i > firstIndex; i--) {
            int toReturn = firstStack.remove(i);
            stacks.get(toReturn).add(toReturn);
        }

        for (int i = secondStack.size() - 1; i > secondIndex; i--) {
            int toReturn = secondStack.remove(i);
            stacks.get(toReturn).add(toReturn);
        }

        firstStack.remove(first);
        secondStack.add(first);
    }

    private static void moveOver(Integer first) {
        for (int i = firstStack.size() - 1; i > firstIndex; i--) {
            int toReturn = firstStack.remove(i);
            stacks.get(toReturn).add(toReturn);
        }

        firstStack.remove(first);
        secondStack.add(first);
    }

    private static void pileOnto() {
        for (int i = secondStack.size() - 1; i > secondIndex; i--) {
            int toReturn = secondStack.remove(i);
            stacks.get(toReturn).add(toReturn);
        }
        List<Integer> stackToMove = new ArrayList<>(firstStack.subList(firstIndex, firstStack.size()));
        firstStack.removeAll(stackToMove);
        secondStack.addAll(stackToMove);
    }

    private static void pileOver() {
        List<Integer> stackToMove = new ArrayList<>(firstStack.subList(firstIndex, firstStack.size()));
        firstStack.removeAll(stackToMove);
        secondStack.addAll(stackToMove);
    }
}
