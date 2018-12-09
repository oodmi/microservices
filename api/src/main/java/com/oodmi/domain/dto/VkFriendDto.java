package com.oodmi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VkFriendDto {

    private Long count;

    private String content;

    private String uuid;

    private LocalDateTime time = LocalDateTime.now();

}
