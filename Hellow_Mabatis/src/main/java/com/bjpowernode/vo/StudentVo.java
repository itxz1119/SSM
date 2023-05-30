package com.bjpowernode.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentVo {
    private String name;
    private Integer minAge;
    private Integer maxAge;
    private Integer start;
    private Integer pageSize;
}
