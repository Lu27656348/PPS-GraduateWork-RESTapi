package com.example.demo.interfaces;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssignReviewerRequest {
    private String graduateWorkReviewer;
    private String graduateWorkId;
    private String graduateWorkCommittee;
}
