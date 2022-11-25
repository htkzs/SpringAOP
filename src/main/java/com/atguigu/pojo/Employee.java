package com.atguigu.pojo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter @Getter
public class Employee {
    private int empId;
    private String empName;
    private double salary;
}
