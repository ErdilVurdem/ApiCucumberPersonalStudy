Feature: US04 JPH DELETE REQUEST
  @JPHDelete
  Scenario: US04 JPH DELETE REQUEST
    Given kullanici JPH "jph" adresine gider
    Then kullanici path parametrleri olarak "posts/20" girer
    Then kullanici delete request gonderir
    And kullanici donen cevabin JPH status degerinin 200 oldugunu kontrol eder
    And kullanici donen cevabin response bodysinin null olduğunu kontrol eder
