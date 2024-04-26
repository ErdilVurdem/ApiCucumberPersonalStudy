package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class DummyStepDefiniton {
    String endPoint;
    Response response;
    JsonPath resJP;

    @Given("kullanici {string} adresine gider")
    public void kullanici_adresine_gider(String baseUrl) {
        endPoint= ConfigReader.getProperty(baseUrl);
    }
    @Then("path parametreleri olarak {string} girer")
    public void path_parametreleri_olarak_girer(String pathParams) {
        endPoint=endPoint+"/"+pathParams;
    }
    @Then("kullanici bir GET request gonderir ve donen response degerini kaydeder")
    public void kullanici_bir_get_request_gonderir_ve_donen_response_degerini_kaydeder() {
        response=given().when().get(endPoint);
    }
    @Then("kullanici donen cevabin status degerinin {int} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_status_degerinin_oldugunu_kontrol_eder(int statusCode) {
        assertEquals(statusCode,response.getStatusCode());
    }
    @Then("kullanici donen cevabin content type degerinin {string} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_content_type_degerinin_oldugunu_kontrol_eder(String contentType) {
        assertEquals(contentType,response.getContentType());
    }
    @Then("kullanici donen cevaptaki employee_age degerinin {int} oldugunu kontrol eder")
    public void kullanici_donen_cevaptaki_employee_age_degerinin_oldugunu_kontrol_eder(int employeeAge) {
        resJP=response.jsonPath();
        assertEquals(employeeAge,resJP.getInt("data.employee_age"));
    }
    @Then("kullanici donen cevaptaki message degerinin {string} oldugunu kontrol eder")
    public void kullanici_donen_cevaptaki_message_degerinin_oldugunu_kontrol_eder(String message) {
        assertEquals(message,resJP.get("message"));
    }

}
