package org.airs.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HorseChess {

    public static int X = 8;
    public static int Y = 8;
    public static int[][] chess = new int[X][Y];
    public static boolean finished = false;


    public static void main(String[] args) {
        go(new Vec2(4, 4), 1);

        System.out.println(Arrays.deepToString(chess));
    }

    public static void go(Vec2 curPos, int step) {
        chess[curPos.x][curPos.y] = step;

        List<Vec2> nextSteps = getNextSteps(curPos);
        nextSteps.sort((o1, o2) -> {
            int count1 = getNextSteps(o1).size();
            int count2 = getNextSteps(o2).size();

            return Integer.compare(count1, count2);
        });
        Vec2 nextPos;
        while (!nextSteps.isEmpty()) {
            nextPos = nextSteps.remove(0);
            if (chess[nextPos.x][nextPos.y] <= 0) {
                go(nextPos, step + 1);
            }
        }

        if (step < X * Y && !finished) {
//            System.out.println(curPos.toString());
            chess[curPos.x][curPos.y] = 0;
            return;
        }

        finished = true;
    }

    public static List<Vec2> getNextSteps(Vec2 curPos) {
        List<Vec2> result = new ArrayList<>();

        Vec2 next = new Vec2(0, 0);
        int curX = curPos.x;
        int curY = curPos.y;

        if (((next.x = curX - 1) >= 0) && (next.y = curY - 2) >= 0) {
            result.add(new Vec2(next));
        }
        if (((next.x = curX - 2) >= 0) && (next.y = curY - 1) >= 0) {
            result.add(new Vec2(next));
        }

        if (((next.x = curX + 1) < X) && (next.y = curY - 2) >= 0) {
            result.add(new Vec2(next));
        }
        if (((next.x = curX + 2) < X) && (next.y = curY - 1) >= 0) {
            result.add(new Vec2(next));
        }

        if (((next.x = curX - 1) >= 0) && (next.y = curY + 2) < Y) {
            result.add(new Vec2(next));
        }
        if (((next.x = curX - 2) >= 0) && (next.y = curY + 1) < Y) {
            result.add(new Vec2(next));
        }

        if (((next.x = curX + 1) < X) && (next.y = curY + 2) < Y) {
            result.add(new Vec2(next));
        }
        if (((next.x = curX + 2) < X) && (next.y = curY + 1) < Y) {
            result.add(new Vec2(next));
        }

        return result;
    }
}

class Vec2 {
    public int x;
    public int y;

    public Vec2(Vec2 vec2) {
        this.x = vec2.x;
        this.y = vec2.y;
    }

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vec2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

