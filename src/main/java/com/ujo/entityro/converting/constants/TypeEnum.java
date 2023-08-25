package com.ujo.entityro.converting.constants;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * DB type 과 java type 매칭
 * */
public enum TypeEnum {
    STRING("varchar", "String"),
    INTEGER("int", "int"),
    DATE("datetime", "LocalDateTime");

    private final String dbType;
    private final String javaType;
    TypeEnum(String dbType, String javaType) {
        this.dbType = dbType;
        this.javaType = javaType;
    }

    public String dbType() {
        return dbType;
    }

    public String javaType() {
        return javaType;
    }

    public static final Map<String, TypeEnum> BY_DB_TYPE =
            Stream.of(values()).collect(Collectors.toMap(TypeEnum::dbType, Function.identity()));

    private static final Map<String, TypeEnum> BY_JAVA_TYPE =
            Stream.of(values()).collect(Collectors.toMap(TypeEnum::javaType, Function.identity()));

    public static TypeEnum valueOfDbType(String dbType) {
        return BY_DB_TYPE.get(dbType);
    }
    public static TypeEnum valueOfJavaType(String javaType) {
        return BY_JAVA_TYPE.get(javaType);
    }
}
