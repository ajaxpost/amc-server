package com.amc;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.amc.mapper")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
        System.out.println(" Amc 服务启动成功 \n" +
                "             __  __    _____ \n" +
                "     /\\     |  \\/  |  / ____|\n" +
                "    /  \\    | \\  / | | |     \n" +
                "   / /\\ \\   | |\\/| | | |     \n" +
                "  / ____ \\  | |  | | | |____ \n" +
                " /_/    \\_\\ |_|  |_|  \\_____|\n" +
                "                             \n" +
                "                             \n");
    }
}