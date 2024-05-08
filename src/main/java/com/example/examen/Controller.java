package com.example.examen;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.examen.dto.CardDto;
import com.example.examen.dto.ErrorResponse;
import com.example.examen.dto.LoginDto;
import com.example.examen.dto.ResponseCard;
import com.example.examen.dto.ResponseLogin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;

import io.sentry.Sentry;


@RestController
public class Controller implements IController {
  @GetMapping("/")
  public String index(){
    try{
      throw new Exception("This is a test xd.");
    } catch (Exception e) {
      Sentry.captureException(e);
    }
    return "Holis from systems architecture";
  } 

  @PostMapping( value = "/login", produces = "application/json")
  public ResponseEntity requestLogin(@RequestBody LoginDto login){
    ObjectMapper mapper = new ObjectMapper();
    String json;
    
    try {
      json = mapper.writeValueAsString(login);
      JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
      JsonSchema jsonsSchema = factory.getSchema(Controller.class.getClassLoader().getResourceAsStream("schemas/login.json"));
      JsonNode jsonNode = mapper.readTree(json);
      Set<ValidationMessage> errors = jsonsSchema.validate(jsonNode);

      String errorsCombined = "";
      for( ValidationMessage error: errors) {
        errorsCombined += error.toString() + "\n";
      }

      if(errors.size() > 0) {
        ErrorResponse errorResponse = new ErrorResponse(errorsCombined.toString());
        return ResponseEntity.badRequest().body(errorResponse);
      }

      ResponseLogin responseLogin = new ResponseLogin("tokensito", 0);
      return ResponseEntity.ok(responseLogin);
    } catch (Exception e) {
      e.printStackTrace();
      ResponseLogin responseLogin = new ResponseLogin("tokensito", 0);
      return ResponseEntity.ok(responseLogin);
    }
  }

  @PostMapping( value = "/payment", produces = "application/json")
  public ResponseEntity requestPayment(@RequestBody CardDto card){
    ObjectMapper mapper = new ObjectMapper();
    
    try {
      String json = mapper.writeValueAsString(card);
      JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);
      JsonSchema jsonsSchema = factory.getSchema(Controller.class.getClassLoader().getResourceAsStream("schemas/card.json"));
      JsonNode jsonNode = mapper.readTree(json);
      Set<ValidationMessage> errors = jsonsSchema.validate(jsonNode);

      String errorsCombined = "";
      for( ValidationMessage error: errors) {
        errorsCombined += error.toString() + "\n";
      }

      if(errors.size() > 0) {
        ErrorResponse errorResponse = new ErrorResponse(errorsCombined.toString());
        return ResponseEntity.badRequest().body(errorResponse);
      }

      ResponseCard responseCard = new ResponseCard(0, "successful");
      return ResponseEntity.ok(responseCard);
    } catch (Exception e) {
      e.printStackTrace();
      ResponseCard responseCard = new ResponseCard(1, "error");
      return ResponseEntity.ok(responseCard);
    }
  }
  
  
}
