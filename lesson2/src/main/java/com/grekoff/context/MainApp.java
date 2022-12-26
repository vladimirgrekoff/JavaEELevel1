package com.grekoff.context;


import com.grekoff.context.application.CartService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.grekoff.context");
        CartService cartService1 = context.getBean(CartService.class);
        CartService cartService2 = context.getBean(CartService.class);

        Scanner sc = new Scanner(System.in);

        inputRequest(cartService1, sc);

        inputRequest(cartService2, sc);

        context.close();
        sc.close();
    }

    private static void inputRequest(CartService cartSrv, Scanner sc){
        String id;

        cartSrv.intro();
        cartSrv.showProducts();

        do {
            id = sc.next();
        } while (cartSrv.checkInput(id));
    }
}

