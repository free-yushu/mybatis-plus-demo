package com.demo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * @author 玉书
 * @date 2022/7/15
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class CreateAccountRequest {

    private String token;

    private String userName;

    private Long age;

    private String mobile;

    private String address;

    private String email;

    private String birthDate;

}