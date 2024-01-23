package com.example.demo.interfaces;

import lombok.Data;

import java.util.Date;

@Data
public class SetDefenseDateRequest {
    private Date graduateWorkDefenseDate;
    private String graduateWorkDefenseLocation;
    private String graduateWorkId;
}
