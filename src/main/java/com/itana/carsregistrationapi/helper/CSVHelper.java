package com.itana.carsregistrationapi.helper;

import com.itana.carsregistrationapi.domain.models.Car;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "month", "make", "fuel_type", "vehicle_type", "number" };
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<Car> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Car> cars = new ArrayList<Car>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
           // System.out.println(csvRecords);
            for (CSVRecord csvRecord : csvRecords) {
                System.out.println(csvRecord.get("month"));
                Car car = new Car(
                        csvRecord.get("month"),
                        csvRecord.get("make"),
                        csvRecord.get("fuel_type"),
                        csvRecord.get("vehicle_type"),
                        Integer.parseInt(csvRecord.get("number"))
                );
                System.out.println(car);
                cars.add(car);
            }
            return cars;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
