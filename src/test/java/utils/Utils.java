package utils;

import config.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static int genarateRandomNumber(int min, int max) {
         double randomNumber = Math.random() * (max - min) + min;
         return (int) Math.round(randomNumber);
    }

    public static void saveJSONData(UserModel userModel) throws IOException, ParseException {
        String filePath = "./src/test/resources/User.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName",userModel.getFirstname());
        jsonObject.put("lastName",userModel.getLastname());
        jsonObject.put("email",userModel.getEmail());
        jsonObject.put("password",userModel.getPassword());
        jsonObject.put("phoneNumber",userModel.getPhonenumber());
        jsonObject.put("address",userModel.getAddress());
        jsonArray.add(jsonObject);
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();

    }

    public static JSONObject readJSONData(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));
        return (JSONObject) jsonArray.get(jsonArray.size()-1);

    }
    public static void scrolldown(WebDriver driver, int px) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, "+px+")");
    }


    public static void setToken(String filePath, String token) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONObject obj;

        File file = new File(filePath);

        if (file.exists() && file.length() > 0) {
            try (FileReader reader = new FileReader(file)) {
                obj = (JSONObject) parser.parse(reader);
            }
        } else {
            obj = new JSONObject();
        }

        obj.put("authToken", token);

        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write(obj.toJSONString());
            writer.flush();
        }
    }

    public static String getToken(String filePath)
            throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONObject obj;

        try (FileReader reader = new FileReader(filePath)) {
            obj = (JSONObject) parser.parse(reader);
        }

        return obj.get("authToken").toString();
    }


    public static List<UserModel> readUsersFromCSV(String filePath) throws IOException {
        List<UserModel> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                String[] data = line.split(",", -1);

                UserModel user = new UserModel();
                user.setFirstname(data[0].trim());
                user.setLastname(data[1].trim());
                user.setEmail(data[2].trim());
                user.setPassword(data[3].trim());
                user.setPhonenumber(data[4].trim());
                user.setAddress(data[5].trim());

                users.add(user);
            }
        }
        return users;
    }


}
