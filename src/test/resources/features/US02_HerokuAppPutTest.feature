Feature: US 02 HerokuApp Put Request
@HeroPut
  Scenario: US 02 HerokuApp Put Request
    Given kullanici hero "herokuApp" adresine gider
    Then kullanici path parametreleri olarak "booking/12" girer
    Then kullanici post request icin "Ahmet","Guler", 899, true, "2020-05-02", "2020-05-12", "Breakfast, Lunch" bilgileri ile reqBody olusturur
    Then kullanici bir post request gonderir ve donen response degerini kaydeder
    Then kullanici donen cevabin hero status degerinin 200 oldugunu kontrol eder
    And kullanici donen cevabin hero content type degerinin "application/json; charset=utf-8" oldugunu kontrol eder
    And kullanici donen cevabin "Server" isimli header degerinin "Cowboy" oldugunu kontrol eder
    And kullanici donen cevabin attribute degerlerinin "Ahmet","Guler", 899, false, "2020-05-02", "2020-05-12", "Breakfast, Lunch" oldugunu kontrol eder
