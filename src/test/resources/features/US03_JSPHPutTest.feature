Feature: US03 JPH PUT REQUEST
@JPHPut
  Scenario: US03 JPH PUT REQUEST
    Given kullanici JPH "jph" adresine gider
    Then kullanici path parametrleri olarak "posts" girer
    Then kullanici post request icin "Erdil","Hello World", 9 101 bilgileri ile reqBody olusturur
    Then kullanici post request gonderir ve donen response kaydeder
    And kullanici donen cevabin JPH status degerinin 201 oldugunu kontrol eder
    And kullanici donen cevabin content-type degerinin "application/json; charset=utf-8" oldugunu kontrol eder
    And kullanici donen cevabin JPH "Connection" isimli header degerinin "keep-alive" oldugunu kontrol eder
    And kullanici donen cevabin attribute degerlerinin "Erdil","Hello World",9 101 oldugunu kontrol eder