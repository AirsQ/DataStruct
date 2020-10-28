package org.airs.algorithm;

public class HanoiTower {
    public static void main(String[] args) {

        hanTower(2, 'A', 'B', 'C');

    }

    public static void hanTower(int count, char from, char target, char buffer) {
        if (count == 1) {
            System.out.println(count + ":" + from + "->" + target);
            return;
        }

        hanTower(count - 1, from, buffer, target);
        System.out.println(count + ":" + from + "->" + target);
        hanTower(count - 1, buffer, target, from);
    }

}
