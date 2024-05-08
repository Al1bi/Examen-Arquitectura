package com.example.examen.dto;

public class CardDto {
  private String card_number;
  private String cvv;
  private String expiration_date;

  public CardDto(String card_number, String cvv, String expiration_date){
    this.card_number = card_number;
    this.cvv = cvv;
    this.expiration_date = expiration_date;
  }

  public String getCardNumber() {
    return card_number;
  }

  public void setCardNumber(String card_number) {
    this.card_number = card_number;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getExpirationDate() {
    return expiration_date;
  }

  public void setExpirationDate(String expiration_date) {
    this.expiration_date = expiration_date;
  }

}
