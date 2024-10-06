package ua.goit;

import ua.goit.exception.HttpWrongStatusException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HttpImageStatusCli {
    public void askStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter HTTP status code: ");
        int code;
        while (true) {
            try {
                code = scanner.nextInt();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Please enter valid number!");
                scanner.next();
            }
        }
        try {
            new HttpStatusImageDownloader().downloadStatusImage(code);
        } catch (HttpWrongStatusException e) {
            System.out.println("There is not any image for HTTP status " + code);
        }
    }

    public static void main(String[] args) {
        new HttpImageStatusCli().askStatus();
    }
}
