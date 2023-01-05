package com.grekoff.context.lesson6;

import com.grekoff.context.lesson6.services.InfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.grekoff.context.lesson6");

        InfoService infoService = context.getBean(InfoService.class);
        Scanner sc = new Scanner(System.in);

        String id;
        infoService.intro();

        do {
            System.out.println("введите команду");
            id = sc.nextLine();
        } while (infoService.checkInput(id));

    }
}
