package availity.java.codeExercise6.dao;

import availity.java.codeExercise6.models.Enrollee;
import availity.java.codeExercise6.util.EnrolleeCSVFileWriter;
import com.opencsv.CSVReader;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@NoArgsConstructor
public class EnrolleeDAO {

    private HashMap<String, Enrollee> enrolleeMap;
    private HashMap<String, List> companyMap;


    public void parseCSVFile(String fileName) {
        this.enrolleeMap = new HashMap<>();

        try {
            String rawText[];
            InputStream rawFile = getClass().getClassLoader().getResourceAsStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(rawFile, StandardCharsets.UTF_8);

            BufferedReader reader = new BufferedReader(inputStreamReader);
            CSVReader csvReader = new CSVReader(reader);


            while((rawText = csvReader.readNext()) != null) {

                //check for empty string
                if(rawText.length >= 4) {
                    Enrollee enrollee = new Enrollee(
                            rawText[0], //id
                            rawText[1], //first name
                            rawText[2], //last name
                            Integer.valueOf(rawText[3]), //version
                            rawText[4] //insurance company
                    );
                    this.enrolleeMap.put(enrollee.getId(), enrollee);
                }
            }

            rawFile.close();
            inputStreamReader.close();
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void parseByCompany(String fileName){
        this.companyMap = new HashMap<>();

        try {
            String rawText[];
            InputStream rawFile = getClass().getClassLoader().getResourceAsStream(fileName);
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
                    if (companyMap.containsKey(enrollee.getInsuranceCompany())) {
                       List enrolleeList = companyMap.get(enrollee.getInsuranceCompany());
//                      //duplicate Enrollee
                        if (enrolleeList.contains(enrollee)) {
                           Enrollee inListEnrollee =
                                   (Enrollee) enrolleeList.get(enrolleeList.indexOf(enrollee));
                            //If the current Enrollee has a lower version
                            //remove current Enrollee and add new
                            // else do nothing.
                           if (inListEnrollee.getVersion() < enrollee.getVersion()) {
                               enrolleeList.remove(inListEnrollee);
                               enrolleeList.add(enrollee);
                           }
                        }else {
                            enrolleeList.add(enrollee);
                        }
                    } else {

                        List newEnrolleeList = new ArrayList();
                        newEnrolleeList.add(enrollee);

                        this.companyMap.put(
                                enrollee.getInsuranceCompany(),
                                newEnrolleeList
                        );
                    }

                }
            }
            rawFile.close();
            inputStreamReader.close();
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //separate company to its own csv file
        if (!this.companyMap.isEmpty()){
            writeToCompanyFile();
        }
    }

    public void writeToCompanyFile() {
        //Nothing to write
        if (this.companyMap.isEmpty()) {
            return;
        }

        Iterator itr = this.companyMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry mapElement =
                    (Map.Entry) itr.next();
            EnrolleeCSVFileWriter.enrolleeCSVAdd(mapElement);
        }
    }

}
