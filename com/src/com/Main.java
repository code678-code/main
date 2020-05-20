package com;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
	public final static int MAX_VALUE = 1000;
	public static void main(String[] args) {
    	new Child("mike");
    	if(new File("d:/abc.txt") .exists( ) );
    	int x=2;
    	switch (x) { 
    	case 1: System.out.println(1) ;
    	case 2: 
    	case 3: System.out.println(3) ;
    	case 4: System.out.println(4) ;
    	}
    	String str = "123456789";
    	str =str.substring(1,3);
    	System.out.println(str);
    	
    	
	}
}
class People {

    String name;

    public People() {

        System.out.print(1);

    }

    public People(String name) {

        System.out.print(2);

        this.name = name;

    }

}

class Child extends People {

    People father;
    

    public Child(String name) {

        System.out.print(3);

        this.name = name;

        father = new People(name + ":F");

    }

    public Child() {

        System.out.print(4);

    }

}