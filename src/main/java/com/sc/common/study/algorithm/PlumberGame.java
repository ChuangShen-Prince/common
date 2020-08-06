package com.sc.common.study.algorithm;

/**
 * @Description: 水管工游戏
 */
public class PlumberGame {

    /**
     * 自定义内部类，代表管道所处节点
     */
    static class PlumberNode {
        /** 横坐标 */
        int x;

        /** 纵坐标 */
        int y;

        public PlumberNode(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * 自定义内部类，表示栈，用来记录铺设管道的路径
     */
    static class PlumberStack {
        /** 数据栈 */
        PlumberNode[] data = new PlumberNode[100];

        /** 栈顶指针，初始值为0 */
        int top = 0;
    }

    /** 二维数组模拟水管铺设地图 */
    private static int[][] map = new int[10][10];

    /** 桶，标记已经铺设过的点位 */
    private static int[][] book = new int[10][10];

    /** 地图边界，以及是否到达重点的标记 */
    private static int m = 0, n = 0, flag = 0;

    /**
     * 递归处理每一个单元格位置所能处理的管道摆放方式
     * @param x 单元格横坐标
     * @param y 单元格纵坐标
     * @param front 当前单元格中进水口方向
     * @param stack 表示铺设管道记录铺设路径的栈
     */
    public static void dfs(int x, int y, int front, PlumberStack stack) {

        // 判断是否以及到达终点位置，其中xy都是从0开始计算。
        // 这里因为最后的出水口在地图外面，所以如果到达最后地图外的那个点位，则说明管道铺设完成
        if(x == (m - 1) && y == n) {

            // 更新完成管道的铺设标记
            flag = 1;

            // 找到铺设方案，打印铺设轨迹
            for(int i = 0; i < stack.top; i++) {
                System.out.print(String.format("(%d, %d) ", stack.data[i].x + 1, stack.data[i].y + 1));
            }

            // 到此返回
            return;
        }

        // 判断是否越界，注意如果有上面这层判断，越界判断就得放在下面
        if(x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }

        // 判断当前点位管道是否已经使用过，使用过则掠过，没有使用则继续并加入到桶中标记
        if(book[x][y] == 1) {
            return;
        }
        book[x][y] = 1;

        // 将当前尝试的坐标入栈，然后栈顶指针上移一位
        PlumberNode node = new PlumberNode(x, y);
        stack.data[stack.top] = node;
        stack.top++;

        // 当当前管道是直管的情况
        if(map[x][y] >= 5 && map[x][y] <= 6) {

            // 进水口在左边的情况
            if(front == 1) {
                // 此时只能使用5号这种摆放方式，且下次进水口也是在左边
                dfs(x, y+1, 1, stack);
            }

            // 进水口在上边的情况
            if(front == 2) {
                // 此时只能使用6号这种摆放方式，且下次进水口也是在上边
                dfs(x+1, y, 2, stack);
            }

            // 进水口在右边的情况
            if(front == 3) {
                // 此时只能使用5号这种摆放方式，且下次进水口也是在右边
                dfs(x, y - 1, 3, stack);
            }

            // 进水口在下边的情况
            if(front == 4) {
                // 此时只能使用6号这种摆放方式，且下次进水口也是在下边
                dfs(x - 1, y, 4, stack);
            }

        }

        // 当当前管道是弯管时
        if(map[x][y] >= 1 && map[x][y] <= 4) {

            // 进水口在左边的情况
            if(front == 1) {

                // 此时可以有3，4号两种摆放方式
                // 下一次的进水口就有可能是在上边或者在下边了，这取决于用哪一种摆放方式
                dfs(x + 1, y, 2, stack);
                dfs(x - 1, y, 4, stack);
            }

            // 进水口在上边的情况
            if(front == 2) {

                // 此时可以有1，4号两种摆放方式
                dfs(x, y + 1, 1, stack);
                dfs(x, y - 1, 3, stack);
            }

            // 进水口在右边的情况
            if(front == 3) {

                // 此时可以有1，2号两种摆放方式
                dfs(x - 1, y, 4, stack);
                dfs(x + 1, y, 2, stack);
            }

            // 进水口在下边的情况
            if(front == 4) {

                // 此时可以有2，3号两种摆放方式
                dfs(x, y + 1, 1, stack);
                dfs(x, y - 1, 3, stack);
            }

        }

        // 尝试完这种方式，如果不行则需要回退重新尝试，取消桶标记，同时栈中记录的点位出栈
        book[x][y] = 0;
        stack.top --;
        return;
    }

    public static void main(String[] args) {
        // 铺设管道开始的点位，同时第一个进水方先是1
        int startx = 0, starty = 0, front = 1;

        // 设定游戏地图边界
        m = 5;
        n = 4;

        // 初始化游戏地图
        map[0][0] = 5;
        map[0][1] = 3;
        map[0][2] = 5;
        map[0][3] = 3;
        map[1][0] = 1;
        map[1][1] = 5;
        map[1][2] = 3;
        map[1][3] = 0;
        map[2][0] = 2;
        map[2][1] = 3;
        map[2][2] = 5;
        map[2][3] = 1;
        map[3][0] = 6;
        map[3][1] = 1;
        map[3][2] = 1;
        map[3][3] = 5;
        map[4][0] = 1;
        map[4][1] = 5;
        map[4][2] = 5;

        map[4][3] = 4;

        // 初始化记录管道铺设路径的栈
        PlumberStack stack = new PlumberStack();

        // 开始游戏
        dfs(startx, starty, front, stack);

        if(flag == 0) {
            System.out.println("Impossible!");
        }else {
            System.out.println("\nYes, we did it.");
        }
    }
}
