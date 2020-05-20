package com;



import java.util.ArrayList;
import java.util.List;


public class Test {
	
    public static void main(String[] args) {
        System.out.print("前序：");       
        ArrayList<String> arr= Traversal.preOrderRecursion(Traversal.createBinTree());
        for(String st : arr) {
        	System.out.print(st);
        }

       
    }
}

class BinTreeNode {
    BinTreeNode() {
    }
    BinTreeNode(char data, int flag, BinTreeNode lchild, BinTreeNode rchild) {
        this.data = data;
        this.flag = flag;
        this.lchild = lchild;
        this.rchild = rchild;
    }
    char data;
    int flag;
    BinTreeNode lchild, rchild;

}

class Traversal {


    // 创建一棵二叉树
    public static BinTreeNode createBinTree() {
        BinTreeNode R3 = new BinTreeNode('F', 0, null, null);
        BinTreeNode L2 = new BinTreeNode('D', 0, null, null);
        BinTreeNode R2 = new BinTreeNode('E', 0, null, R3);
        BinTreeNode L1 = new BinTreeNode('B', 0, L2, R2);
        BinTreeNode R1 = new BinTreeNode('C', 0, null, null);
        BinTreeNode T = new BinTreeNode('A', 0, L1, R1);
        return T;
    }


    public static List<String> list =new ArrayList<String>();
    // 前序递归
    public static ArrayList<String> preOrderRecursion(BinTreeNode top) {
        if (top != null) {         
            list.add(String.valueOf(top.data));
            preOrderRecursion(top.lchild);
            preOrderRecursion(top.rchild);
        }
		return (ArrayList<String>) list;
    }

   

}
