package com.kotlinweather.util.csv;

import com.kotlinweather.entity.CityInfo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name：KotlinWeather
 * Description ：
 *
 * @author ：ZouWeiLin on 2019.03.04
 */
public class CsvFileReader {

    //CSV文件头
    private static final String[] FILE_HEADER = {"City_ID", "City_EN", "City_CN"};

    /**
     * @param fileName
     */
    public static ArrayList<CityInfo> readCsvFile(String fileName) {
        FileReader fileReader = null;
        CSVParser csvFileParser = null;
        //创建CSVFormat（header mapping）
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER);
        try {
            //初始化FileReader object
            fileReader = new FileReader(fileName);
            //初始化 CSVParser object
            csvFileParser = new CSVParser(fileReader, csvFileFormat);
            //CSV文件records
            List<CSVRecord> csvRecords = csvFileParser.getRecords();
            // CSV
            ArrayList<CityInfo> userList = new ArrayList<>();
            //
            for (int i = 1; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);
                //创建用户对象填入数据
                String city_id = record.get("City_ID");
                String city_en = record.get("City_EN");
                String city_cn = record.get("City_CN");
                CityInfo cityInfo = new CityInfo(city_en, city_cn);
                userList.add(cityInfo);
            }
            // 遍历打印
            /*for (CityInfo cityInfo : userList) {
                System.out.println(cityInfo.toString());
            }*/
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (csvFileParser != null) {
                    csvFileParser.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        readCsvFile("D:\\Android\\project\\KotlinWeather\\app\\src\\main\\assets\\china-city-list.csv");
    }

}
