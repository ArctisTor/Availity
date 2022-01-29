package availity.java;

import availity.java.dao.EnrolleeDAO;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static EnrolleeDAO enrolleeDAO;

    public static void main(String[] args) {

        init();

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter file name (location is resources directory & include csv extension): ");
        String fileName = scan.nextLine();

        try {
           enrolleeDAO.parseCSVFile(fileName);
           enrolleeDAO.parseByCompany(fileName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void init() {
        enrolleeDAO = new EnrolleeDAO();
    }
}
