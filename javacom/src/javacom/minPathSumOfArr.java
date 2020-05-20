package javacom;

import java.util.*;
 
public class minPathSumOfArr {
    
    static void bfs() {
    	vis = new int[c][cc];
        int head = 0;
        int tail = 0;
        q[tail] = new Node(0,0,-1);
        vis[0][0] = 1;
        tail++;
        while(head < tail) {
            boolean flag = false;//找没找到终点
            for(int i = 0;i < 4;i++) {
                int nx = q[head].x + move[i][0];
                int ny = q[head].y + move[i][1];
                if(check(nx,ny)) {
                    vis[nx][ny] = 1;
                    q[tail] = new Node(nx,ny,head);
                    tail++;
                }
                if(nx == 4 && ny == 4) {
                    flag = true;
                    break;
                }
            }
            if(flag) {
                print(q[tail - 1]);
                break;
            }
            head++;
        }
    }
    
    static void print(Node node) {
        if(node.pre == -1) {
            System.out.println("(" + node.x + "," + node.y + ")");
            return;
        } else {
            print(q[node.pre]);
            System.out.println("(" + node.x + "," + node.y + ")");
        }
    }
    
    static boolean check(int x,int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5 && vis[x][y] != 1 && map[x][y] != 1;
    }
    
    static int c;
    static int cc;	
    static int[][] map ;
    static int[][] vis ;
    static int[][] move = {{0,1},{0,-1},{1,0},{-1,0}};
    static Node[] q = new Node[20];
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
         c =cin.nextInt();
         cc =cin.nextInt();
       
        map=new int[c][cc];
        for(int i = 0;i < c;i++)
            for(int j = 0;j < cc;j++) 
                map[i][j] = cin.nextInt();
        for(int i = 0;i < 20;i++)
            q[i] = new Node();
        bfs();
        cin.close();
    }
}
class Node {
    int x,y,pre;//来到此点的出发点
    Node() {}
    Node(int x,int y,int pre) {
        this.x = x;
        this.y = y;
        this.pre = pre;
    }
}



