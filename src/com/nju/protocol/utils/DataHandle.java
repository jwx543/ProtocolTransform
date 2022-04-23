package com.nju.protocol.utils;

import com.nju.protocol.data.DetectData;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataHandle {

    public List<DetectData> readFile(){
        List<DetectData> result = new ArrayList<>();
        String path = "src/com/nju/protocol/data/datatest.csv";

        CSVReader reader = null;

        try{
            reader = new CSVReader(new FileReader(path));
            String[] line;
            while((line = reader.readNext()) != null){
                DetectData data = new DetectData(Double.valueOf(line[2]),
                        Double.valueOf(line[3]), Double.valueOf(line[4]),
                        Double.valueOf(line[5]), Double.valueOf(line[6]),
                        Integer.valueOf(line[7]));
                result.add(data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        DataHandle d = new DataHandle();
        d.readFile();
    }
}
