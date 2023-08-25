package com.ujo.entityro.converting.service.impl;

import com.ujo.entityro.converting.dto.response.SimpleColumn;
import com.ujo.entityro.converting.service.AnalyzeDdlService;
import com.ujo.entityro.converting.service.ConvertEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConvertEntityServiceImpl implements ConvertEntityService {

    //테이블 생성 구문 분석 서비스 의존성 주입
    private final AnalyzeDdlService analyzeDdlService;

    /**
     * @param createTableString
     * @return pojo 객체 텍스트
     */
    @Override
    public String convertDdlToEntity(String createTableString) {
        //String -> 컬럼 리스트 변환
        List<SimpleColumn> columnList = analyzeDdlService.getColumns(createTableString);

        StringBuilder entity = new StringBuilder();

        for (SimpleColumn column : columnList) {
            entity.append("private ")
                    .append(column.getType())
                    .append(" ")
                    .append(column.getName())
                    .append(";")
                    .append("\n");
        }
        return entity.toString();
    }
}
