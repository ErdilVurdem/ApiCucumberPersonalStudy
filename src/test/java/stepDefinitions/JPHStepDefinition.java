package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class JPHStepDefinition {
    String endPoint;
    Response response;
    JsonPath resJP;
    JSONObject reqBody;


    @Given("kullanici JPH {string} adresine gider")
    public void kullanici_jph_adresine_gider(String baseUrl) {
        endPoint= ConfigReader.getProperty(baseUrl);
    }
    @Then("kullanici path parametrleri olarak {string} girer")
    public void kullanici_path_parametrleri_olarak_girer(String pathParams) {
        endPoint=endPoint+"/"+pathParams;
    }
    @Then("kullanici post request icin {string},{string}, {int} {int} bilgileri ile reqBody olusturur")
    public void kullanici_post_request_icin_bilgileri_ile_req_body_olusturur(String title, String body, int userId, int id) {
        reqBody=new JSONObject();
        reqBody.put("userId",userId);
        reqBody.put("id",id);
        reqBody.put("title",title);
        reqBody.put("body",body);

    }
    @Then("kullanici post request gonderir ve donen response kaydeder")
    public void kullanici_post_request_gonderir_ve_donen_response_kaydeder() {
        response=given().when().body(reqBody.toString()).contentType(ContentType.JSON).post(endPoint);
    }
    @Then("kullanici donen cevabin JPH status degerinin {int} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_jph_status_degerinin_oldugunu_kontrol_eder(int status) {
        assertEquals(status,response.getStatusCode());
    }
    @Then("kullanici donen cevabin content-type degerinin {string} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_content_type_degerinin_oldugunu_kontrol_eder(String contentType) {
        assertEquals(contentType,response.getContentType());
    }
    @Then("kullanici donen cevabin JPH {string} isimli header degerinin {string} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_jph_isimli_header_degerinin_oldugunu_kontrol_eder(String key1, String value1) {
        assertEquals(value1,response.getHeader(key1));
    }
    @Then("kullanici donen cevabin attribute degerlerinin {string},{string},{int} {int} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_attribute_degerlerinin_oldugunu_kontrol_eder(String title, String body, int userId, int id) {
        resJP=response.jsonPath();
        assertEquals(title,resJP.getString("title"));
        assertEquals(body,resJP.getString("body"));
        assertEquals(userId,resJP.getInt("userId"));
        assertEquals(id,resJP.getInt("id"));
    }

}
