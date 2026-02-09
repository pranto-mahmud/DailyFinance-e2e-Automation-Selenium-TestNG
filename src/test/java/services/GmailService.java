package services;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.bytebuddy.asm.Advice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GmailService {
    Properties prop;
    public GmailService() throws IOException {
        prop = new Properties();
        FileInputStream fs = new FileInputStream("./src/test/resources/config.properties");
        prop.load(fs);
    }
 public String getGmailList(){
     RestAssured.baseURI = "https://gmail.googleapis.com";
    Response res= given().contentType("application/json").header("Authorization","Bearer "+prop.get("gmail_token")).
            when().get("/gmail/v1/users/me/messages");

     JsonPath jsonPath=res.jsonPath();
     return jsonPath.get("messages[0].id").toString();
 }
    public String readEmail() throws IOException {
        GmailService gs=new GmailService();
        String messageId= gs.getGmailList();
        RestAssured.baseURI="https://gmail.googleapis.com";
        Response res=given().contentType("application/json").header("Authorization","Bearer "+prop.get("gmail_token")).
                when().get("/gmail/v1/users/me/messages/"+messageId);

        JsonPath jsonPath=res.jsonPath();
        return jsonPath.get("snippet");
    }

    public String resetpasslink() throws IOException {
        GmailService gs=new GmailService();
        String resetPasslink=gs.readEmail();
        resetPasslink=resetPasslink.replace("Click on the following link to reset your password:","").trim();
        return resetPasslink;
    }
}
