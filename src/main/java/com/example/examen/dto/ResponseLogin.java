package com.example.examen.dto;

public class ResponseLogin {
  private String token;
  private long timestamp;

  public ResponseLogin(String token, long timestamp){
    this.token = token;
    this.timestamp = timestamp;
  }
}
