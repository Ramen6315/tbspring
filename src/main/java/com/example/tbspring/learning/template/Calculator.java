package com.example.tbspring.learning.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calcSum(String filePath) throws IOException {
        CalculatorInterface calculatorInterface = new CalculatorInterface() {
            @Override
            public Integer calValue(BufferedReader bufferedReader) throws IOException {
                return null;
            }
        };
        return calc(filePath,calculatorInterface );
    }

    private int calc(String filePath, CalculatorInterface calculatorInterface) throws IOException {
        BufferedReader br = null;
        Integer sum = 0;
        try {
            br = new BufferedReader(new FileReader(filePath));
            sum = calculatorInterface.calValue(br);
            return sum;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return sum;
    }

    public Integer calcMul(String filePath) throws IOException {
        BufferedReader br = null;
        Integer sum = 0;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = null;
            while((line = br.readLine()) != null) {
                sum *= Integer.valueOf(line);
            }
            return sum;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                br.close();
            }
        }
        return sum;
    }

}
