package availity.java.util;

import availity.java.models.Enrollee;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnrolleeCSVFileWriter {

    private static String resourcesPath
            = System.getProperty("user.dir") + "/src/main/resources/companies/";

    public static void enrolleeCSVAdd(Map.Entry mapElement) {
        String company = (String) mapElement.getKey();
        List<Enrollee> enrolleeList = (List) mapElement.getValue();
        String filePath = EnrolleeCSVFileWriter.resourcesPath+ company+".csv";
        File fileExists = new File(filePath);
        if (fileExists.exists()) {
            EnrolleeCSVFileWriter.modifyFile(enrolleeList, filePath);
        } else {
            EnrolleeCSVFileWriter.createCSVFile(enrolleeList, filePath);
        }
    }


    private static void createCSVFile(List<Enrollee> enrolleeList, String filePath){
        try {
            FileWriter companyFile = new FileWriter(filePath);;
            writeToCSVFile(enrolleeList, filePath);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void modifyFile(List<Enrollee> enrolleeList, String filePath) {
        try {
            FileWriter companyFile = new FileWriter(filePath);;

            //Check to see if file exist && lets get what it currently has
            List<Enrollee> currentEnrollees = EnrolleeCSVFileWriter.getCSVFileEnrollee(filePath);
            if (currentEnrollees.isEmpty()) {
                writeToCSVFile(enrolleeList, filePath);
            } else {
             List<Enrollee> union = new ArrayList<>(currentEnrollees);
             union.addAll(enrolleeList);

             List<Enrollee> intersection = new ArrayList<>(currentEnrollees);
             intersection.retainAll(enrolleeList);

             union.removeAll(intersection);
             writeToCSVFile(union, filePath);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeToCSVFile(List<Enrollee> enrolleeList, String filePath) {
        try {
            //sort by last name then first
            Collections.sort(enrolleeList);
            FileWriter companyFile = new FileWriter(filePath);;

            CSVWriter csvWriter = new CSVWriter(
                    companyFile,
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.RFC4180_LINE_END);
            for (Enrollee e : enrolleeList) {
                csvWriter.writeNext(e.toCSVFormat());
            }
            csvWriter.close();
            companyFile.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static List<Enrollee> getCSVFileEnrollee(String filePath) {
        List currentEnrolleeList = new ArrayList();
        try {
            String rawText[];
            InputStream rawFile = EnrolleeCSVFileWriter
                    .class.getClass().getClassLoader().getResourceAsStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(rawFile, StandardCharsets.UTF_8);

            BufferedReader reader = new BufferedReader(inputStreamReader);

            CSVReader csvReader = new CSVReader(reader);

            while((rawText = csvReader.readNext()) != null) {
                //checking for empty string
                if(rawText.length >= 4) {
                    Enrollee enrollee = new Enrollee(
                            rawText[0].replace("\t", ""), //id
                            rawText[1].replace("\t", ""), //first name
                            rawText[2].replace("\t", ""), //last name
                            Integer.valueOf(rawText[3].replace("\t", "")), //version
                            rawText[4].replace("\t", "") //insurance company
                    );
                    //contains the company
                    if (currentEnrolleeList.contains(enrollee)) {
                        Enrollee inListEnrollee =
                                (Enrollee) currentEnrolleeList.get(currentEnrolleeList.indexOf(enrollee));
                        //If the current Enrollee has a lower version
                        //remove current Enrollee and add new
                        // else do nothing.
                        if (inListEnrollee.getVersion() < enrollee.getVersion()) {
                            currentEnrolleeList.remove(inListEnrollee);
                            currentEnrolleeList.add(enrollee);
                        }
                    }else {
                        currentEnrolleeList.add(enrollee);
                    }
                }
            }
            rawFile.close();
            inputStreamReader.close();
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return currentEnrolleeList;
    }
}
