package stepDefinitions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import utilities.ConfigReader;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class collectApiStepDefinition {
    Response response;
    String endPoint;
    JsonPath resJP;

    @Given("kullanici collect {string} adresine gider")
    public void kullanici_adresine_gider(String baseUrl) {
        endPoint= ConfigReader.getProperty(baseUrl);
    }

    @Then("kullanici collect path parametreleri olarak {string} girer")
    public void kullanici_collect_path_parametreleri_olarak_girer(String pathParams) {
       endPoint=endPoint+"/"+pathParams;
    }
    @Then("kullanici {string} sehrine iliskin GET request gonderir ve donen response degerini kaydeder")
    public void kullanici_sehrine_iliskin_get_request_gonderir_ve_donen_response_degerini_kaydeder(String sehir) {
        endPoint=endPoint+sehir;
        response=given().when().header("Authorization","apikey 5YRQRBX9CtidVobt8lpO66:3Xa8R7EcccJ8wJMzTwOiHo").get(endPoint);
        response.prettyPrint();
    }
    @Then("kullanici donen cevabin collect status degerinin {int} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_status_degerinin_oldugunu_kontrol_eder(int statusCode) {
        resJP=response.jsonPath();
        assertEquals(statusCode,response.getStatusCode());
    }
    @Then("kullanici donen cevabin collect content type degerinin {string} oldugunu kontrol eder")
    public void kullanici_donen_cevabin_content_type_degerinin_oldugunu_kontrol_eder(String contentType) {
        assertEquals(contentType,response.getContentType());
    }
    @Then("kullanici donen cevaptaki city degerinin {string} oldugunu kontrol eder")
    public void kullanici_donen_cevaptaki_city_degerinin_oldugunu_kontrol_eder(String sehir) {
        assertEquals(sehir,resJP.getString("city"));
    }
    @Then("kullanici donen cevaptaki success degerinin {string} oldugunu kontrol eder")
    public void kullanici_donen_cevaptaki_success_degerinin_oldugunu_kontrol_eder(String bool) {
        boolean expecteValue=Boolean.parseBoolean(bool);
        assertEquals(expecteValue,resJP.getBoolean("success"));
    }

    @Then("kullanici diger query parametrelerini key2 degeri {string} valeu2 degerini {string} olarak girer")
    public void kullanici_diger_query_parametrelerini_key2_degeri_valeu2_degerini_olarak_girer(String ilce, String ilceValue) {
        endPoint=endPoint+"?"+ilce+"="+ilceValue;
    }
    @Then("kullanici query parametrelerini key degeri {string} value degeri {string} olarak girer")
    public void kullanici_query_parametrelerini_key_degeri_value_degeri_olarak_girer(String il, String ilValue) {
        endPoint=endPoint+"&"+il+"="+ilValue;
    }
    @Then("kullanici donen response degerini kaydeder")
    public void kullaniciDonenResponseDegeriniKaydeder() {
        response=given().when().header("Authorization","apikey 5YRQRBX9CtidVobt8lpO66:3Xa8R7EcccJ8wJMzTwOiHo").get(endPoint);
        response.prettyPrint();
    }


}
