Feature: US 05 Collect Api Get Request
@havaDurumu
  Scenario: US 05 Hava Durumu Get Request
    Given kullanici collect "collectApi" adresine gider
    Then kullanici collect path parametreleri olarak "weather/getWeather?data.lang=tr&data.city=" girer
    And kullanici "ankara" sehrine iliskin GET request gonderir ve donen response degerini kaydeder
    And kullanici donen cevabin collect status degerinin 200 oldugunu kontrol eder
    And kullanici donen cevabin collect content type degerinin "application/json; charset=utf-8" oldugunu kontrol eder
    And kullanici donen cevaptaki city degerinin "ankara" oldugunu kontrol eder
    And kullanici donen cevaptaki success degerinin "true" oldugunu kontrol eder

@nobetciEczane
  Scenario: US 05 Nobetci Eczane Get Request
    Given kullanici collect "collectApi" adresine gider
    Then kullanici collect path parametreleri olarak "health/dutyPharmacy" girer
    Then kullanici diger query parametrelerini key2 degeri "ilce" valeu2 degerini "Sarıyer" olarak girer
    Then kullanici query parametrelerini key degeri "il" value degeri "İstanbul" olarak girer
    Then kullanici donen response degerini kaydeder
    And kullanici donen cevabin collect status degerinin 200 oldugunu kontrol eder
    And kullanici donen cevabin collect content type degerinin "application/json; charset=utf-8" oldugunu kontrol eder
    And kullanici donen cevaptaki success degerinin "true" oldugunu kontrol eder
