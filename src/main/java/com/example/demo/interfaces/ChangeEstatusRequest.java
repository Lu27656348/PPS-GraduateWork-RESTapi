package com.example.demo.interfaces;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeEstatusRequest {

    private Integer estatusCode;
    private String graduateWorkId;
}
