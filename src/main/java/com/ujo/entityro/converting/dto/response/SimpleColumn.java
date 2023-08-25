package com.ujo.entityro.converting.dto.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@EqualsAndHashCode
@Getter
public class SimpleColumn {
    private String name;
    private String type;
}
