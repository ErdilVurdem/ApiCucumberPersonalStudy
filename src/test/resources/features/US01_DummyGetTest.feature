Feature: US 01 Dummy Get request
@DummyGet
  Scenario: US 01 Dummy Get request
    Given kullanici "dummyApi" adresine gider
    Then path parametreleri olarak "api/v1/employee/12" girer
    Then kullanici bir GET request gonderir ve donen response degerini kaydeder
    And kullanici donen cevabin status degerinin 200 oldugunu kontrol eder
    And kullanici donen cevabin content type degerinin "application/json" oldugunu kontrol eder
    And kullanici donen cevaptaki employee_age degerinin 22 oldugunu kontrol eder
    And kullanici donen cevaptaki message degerinin "Successfully! Record has been fetched." oldugunu kontrol eder