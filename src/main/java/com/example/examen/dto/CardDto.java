package com.example.examen.dto;

public class CardDto {
  private String cardNumber;
  private String cvv;
  private String expirationDate;

  public CardDto(String card_number, String cvv, String expiration_date){
    this.cardNumber = card_number;
    this.cvv = cvv;
    this.expirationDate = expiration_date;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String card_number) {
    this.cardNumber = card_number;
  }

  public String getCvv() {
    return cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(String expiration_date) {
    this.expirationDate = expiration_date;
  }

}
