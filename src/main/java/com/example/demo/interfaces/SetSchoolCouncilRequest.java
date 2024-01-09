package com.example.demo.interfaces;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SetSchoolCouncilRequest {
    private String graduateWorkSchoolCouncil;
    private String graduateWorkId;
}
