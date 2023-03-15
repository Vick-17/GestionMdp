package com.gestionmdp.gestionmdp;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class recupJson {
    public void jsonRecipe(){
        String path = "/passwords.json";

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            objectJson myObject = objectMapper.readValue(new File(path), objectJson.class);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
