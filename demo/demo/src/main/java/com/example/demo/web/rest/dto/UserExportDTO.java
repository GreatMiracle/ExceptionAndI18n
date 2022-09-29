package com.example.demo.web.rest.dto;

import com.example.demo.util.annotation.BooleanValue;
import com.example.demo.util.annotation.ExcelColumn;
import com.example.demo.util.annotation.ExcelEntity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ExcelEntity
public class UserExportDTO implements Serializable {

    @ExcelColumn(index = 0, name = "User ID")
    private Integer id;
    @ExcelColumn(index = 1, name = "E-mail")
    private String email;
    @ExcelColumn(index = 2, name = "Full Name")
    private String fullName;
    @ExcelColumn(index = 4, name = "Roles")
    private String roles;
    @ExcelColumn(index = 5, name = "Enabled")
    @BooleanValue(trueValue = "true", falseValue = "false")
    private Boolean enabled;

}
