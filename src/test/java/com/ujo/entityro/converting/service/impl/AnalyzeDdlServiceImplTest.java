package com.ujo.entityro.converting.service.impl;

import com.ujo.entityro.converting.dto.response.SimpleColumn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AnalyzeDdlServiceImplTest {

    @InjectMocks
    private AnalyzeDdlServiceImpl analyzeDdlService;

    private String tableString = "-- gigi.STATION definition\n" +
            "\n" +
            "CREATE TABLE `STATION` (\n" +
            "  `STATION_CODE` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '역 코드',\n" +
            "  `STATION_NAME` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '역 이름',\n" +
            "  `SUBWAY_LINE` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '몇 호선인지',\n" +
            "  `PRIORITY` int DEFAULT '0' COMMENT '주요 역인지',\n" +
            "  `IS_BATCHABLE` int DEFAULT '0' COMMENT '배치 가능 유무',\n" +
            "  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 날짜',\n" +
            "  `UPDATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '최근 수정 날짜',\n" +
            "  PRIMARY KEY (`STATION_CODE`)\n" +
            ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

    @Test
    @DisplayName("ddl 텍스트 컬럼 리스트로 변환")
    void getColumns() {
        //given
        SimpleColumn column1 = SimpleColumn.builder()
                .name("stationCode")
                .type("String")
                .build();
        SimpleColumn column2 = SimpleColumn.builder()
                .name("stationName")
                .type("String")
                .build();
        SimpleColumn column3 = SimpleColumn.builder()
                .name("subwayLine")
                .type("String")
                .build();
        SimpleColumn column4 = SimpleColumn.builder()
                .name("priority")
                .type("int")
                .build();
        SimpleColumn column5 = SimpleColumn.builder()
                .name("isBatchable")
                .type("int")
                .build();
        SimpleColumn column6 = SimpleColumn.builder()
                .name("createdAt")
                .type("LocalDateTime")
                .build();
        SimpleColumn column7 = SimpleColumn.builder()
                .name("updatedAt")
                .type("LocalDateTime")
                .build();

        //when
        List<SimpleColumn> columnList = analyzeDdlService.getColumns(tableString);
        List<SimpleColumn> testList = new ArrayList<>();
        testList.add(column1);testList.add(column2);testList.add(column3);testList.add(column4);
        testList.add(column5);testList.add(column6);testList.add(column7);

        //then
        Assertions.assertEquals(testList.size(), columnList.size());
        Assertions.assertEquals(testList.get(0), columnList.get(0));
        Assertions.assertEquals(testList.get(4), columnList.get(4));
        Assertions.assertEquals(testList.get(6), columnList.get(6));

        System.out.println(tableString);
    }

    @Test
    @DisplayName("column 텍스트 들을 리스트로 변환")
    void subWithParentheses() {
        //when
        String[] subString = analyzeDdlService.subWithParentheses(tableString);

        //test
        for (String test : subString) {
            System.out.println(test);
        }

        //then
        Assertions.assertEquals(8, subString.length);

    }

    @Test
    @DisplayName("컬럼 텍스트 리스트를 SimpleColumn 객체로 변환")
    void getColumnTest() {
        //given
        String stringSource = "STATION_CODE varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 역 코드";
        String intSource = "PRIORITY int DEFAULT 0 COMMENT 주요 역인지";
        String exceptionSource = "PRIMARY KEY (STATION_CODE)";
        String dateSource = "CREATED_AT datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 생성 날짜";

        SimpleColumn stringColumn = SimpleColumn.builder()
                .name("stationCode")
                .type("String")
                .build();
        SimpleColumn intColumn = SimpleColumn.builder()
                .name("priority")
                .type("int")
                .build();
        SimpleColumn exceptionColumn = null;
        SimpleColumn dateColumn = SimpleColumn.builder()
                .name("createdAt")
                .type("LocalDateTime")
                .build();

        //when
        SimpleColumn testString = analyzeDdlService.getColumn(stringSource);
        SimpleColumn testIntString = analyzeDdlService.getColumn(intSource);
        SimpleColumn testExceptionString = analyzeDdlService.getColumn(exceptionSource);
        SimpleColumn testDateString = analyzeDdlService.getColumn(dateSource);

        //test
        System.out.println(testString);
        System.out.println(testIntString);
        System.out.println(testExceptionString);
        System.out.println(testDateString);

        //then
        Assertions.assertEquals(stringColumn, testString);
        Assertions.assertEquals(intColumn, testIntString);
        Assertions.assertEquals(exceptionColumn, testExceptionString);
        Assertions.assertEquals(dateColumn, testDateString);

    }
}