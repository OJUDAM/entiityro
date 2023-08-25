package com.ujo.entityro.converting.service.impl;

import com.ujo.entityro.converting.service.AnalyzeDdlService;
import com.ujo.entityro.converting.service.ConvertEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConvertEntityServiceImplTest {

    @Mock
    private AnalyzeDdlService analyzeDdlService;
    @InjectMocks
    private ConvertEntityServiceImpl convertEntityService;

//    @BeforeEach
//    void setUp() {
//        this.analyzeDdlService = new AnalyzeDdlServiceImpl();
//        this.convertEntityService = new ConvertEntityServiceImpl(this.analyzeDdlService);
//    }
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
    void convertDdlToEntity() {
        //given(analyzeDdlService.findById(any())).willReturn(Optional.of(new Product("product-test")));
        System.out.println(convertEntityService.convertDdlToEntity(tableString));
    }


}