package com.yubrajpokharel.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by yubrajpokharel on 1/4/18.
 */

@Data
@AllArgsConstructor
@ApiModel(value="DifferentModel", description="Sample model for the documentation")
public class User {
    private int id;
    private String fname;
    private String lname;
    private int age;
}
