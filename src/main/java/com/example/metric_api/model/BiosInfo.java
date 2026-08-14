package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BiosInfo {

    private String manufacturer;
    private String biosName;
    private String version;
    private String releaseDate;

    public static void requireNonNull(){

    }


}
