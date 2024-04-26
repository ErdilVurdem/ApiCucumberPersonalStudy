package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class HerokuAppStepDefinition {
    String endPoint;
    Response response;
    JSONObject postRequestBody;
    JSONObject bookingDates;
    JsonPath resJP;

    @Given("kullanici hero {string} adresine gider")
    public void kullanici_adresine_gider(String baseUrl) {
        endPoint= ConfigReader.getProperty(baseUrl);
    }

    @Then("kullanici path parametreleri olarak {string} girer")
    public void kullanici_path_parametreleri_olarak_girer(String pathParams) {
        endPoint=endPoint+"/"+pathParams;
    }
    @Then("kullanici post request icin {string},{string}, {int}, true, {string}, {string}, {string} bilgileri ile reqBody olusturur")
    public void kullanici_post_request_icin_true_bilgileri_ile_req_body_olusturur(String firstname, String lastname, Integer totalprice, String checkin, String checkout, String additionalNeeds) {
        postRequestBody=new JSONObject();
        bookingDates=new JSONObject();

        bookingDates.put("checkin",checkin);
        bookingDates.put("checkout",checkout);

        postRequestBody.put("firstname",firstname);
        postRequestBody.put("lastname",lastname);
        postRequestBody.put("totalprice",totalprice);
        postRequestBody.put("depositpaid",false);
        postRequestBody.put("bookingdates",bookingDates);
        postRequestBody.put("additionalneeds",additionalNeeds);
    }

    @Then("kullanici bir post request gonderir ve donen response degerini kaydeder")
    public void kullanici_bir_post_request_gonderir_ve_donen_response_degerini_kaydeder() {
        response=given().when().body(postRequestBody.toString()).contentType(ContentType.JSON).header("Cookie","token=01ddc7dbcc4cf5d").put(endPoint);
        response.prettyPrint();
    }
    @Then("kullanici donen cevabin hero status degerinin {int} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_status_degerinin_oldugunu_kontrol_eder(int statusCode) {
        assertEquals(statusCode,response.getStatusCode());
    }
    @Then("kullanici donen cevabin hero content type degerinin {string} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_content_type_degerinin_oldugunu_kontrol_eder(String contentType) {
        assertEquals(contentType,response.getContentType());
    }
    @Then("kullanici donen cevabin {string} isimli header degerinin {string} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_isimli_header_degerinin_oldugunu_kontrol_eder(String headerAttribute, String headerValue) {
        assertEquals(headerValue,response.getHeader(headerAttribute));
    }
    @Then("kullanici donen cevabin attribute degerlerinin {string},{string}, {int}, false, {string}, {string}, {string} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_attribute_degerlerinin_true_oldugunu_kontrol_eder(String firstname, String lastname, int totalprice, String checkin, String checkout, String additionalNeeds) {
        resJP=response.jsonPath();
        assertEquals(firstname,resJP.getString("firstname"));
        assertEquals(lastname,resJP.getString("lastname"));
        assertEquals(totalprice,resJP.getInt("totalprice"));
        assertEquals(checkin,resJP.getString("bookingdates.checkin"));
        assertEquals(checkout,resJP.getString("bookingdates.checkout"));
        assertEquals(additionalNeeds,resJP.getString("additionalneeds"));
    }
}
