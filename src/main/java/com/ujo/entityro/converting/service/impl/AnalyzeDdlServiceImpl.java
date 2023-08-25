package com.ujo.entityro.converting.service.impl;

import com.ujo.entityro.converting.constants.TypeEnum;
import com.ujo.entityro.converting.dto.response.SimpleColumn;
import com.ujo.entityro.converting.service.AnalyzeDdlService;
import com.ujo.entityro.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AnalyzeDdlServiceImpl implements AnalyzeDdlService {

    @Override
    public List<SimpleColumn> getColumns(String createTableString) {
        //반환할 리스트 생성
        List<SimpleColumn> columnList = new ArrayList<>();

        //줄 내림 기호 제거
        createTableString = StringUtils.removeNextLine(createTableString);
        
        //문자 비어 있을 경우 빈 값 반환
        if (StringUtils.isEmpty(createTableString)) {
            return columnList;
        }

        //불필요 문자들 제거
        String[] columnTexts = subWithParentheses(createTableString);

        //text -> 객체로 변환
        for (String columnText : columnTexts) {
            SimpleColumn column = getColumn(columnText);

            //비어있을 경우 다음으로
            if (column == null) {
                continue;
            }

            //리스트에 넣어줌
            columnList.add(column);
        }
        return columnList;
    }

    /**
     * 불필요 문자 제거 후 쉼표(,) 기준으로 배열 생성 후 반환
     * */
    String[] subWithParentheses(String createTableString) {

        //기준 괄호
        String startCharacter = "(";
        String endCharacter = ")";

        //중괄호 있으면 중괄호로 변경
        if (createTableString.contains("{")) {
            startCharacter = "{";
            endCharacter = "}";
        }

        //table 생성 쿼리의 컬럼을 구분하는 여는 괄호의 첫번 째 위치, 닫는 괄호의 마지막 위치를 찾는다.
        int startIndex = createTableString.indexOf(startCharacter)+1;
        int endIndex = createTableString.lastIndexOf(endCharacter);

        //괄호 기준으로 자른다.
        String resultString = createTableString.substring(startIndex, endIndex);

        //문자열 구분 문자 제거
        resultString = resultString.replaceAll("'", "");
        resultString = resultString.replaceAll("`", "");

        //쉼포를 기준으로 잘라서 리스트로 반환
        return resultString.split(",");
    }

    /**
     * 한 컬럼의 정의 된 구문에서  type, name 파싱하여 SimpleColumn 객체 반환
     * */
    SimpleColumn getColumn(String columnString) {
        //좌우 공백 제거
        columnString = columnString.trim();
        
        //공백 1개로 만든 후 공백 기준으로 배열 생성
        String[] strArr = StringUtils.splitWithSpace(columnString);
        
        //배열 길이 최소 2 여야함
        if (strArr.length < 2) {
            return null;
        }
        //이름, 타입 파싱 후 소문자로 변환
        String fieldName = strArr[0].toLowerCase().trim();
        String fieldType = strArr[1].toLowerCase().trim();

        //db Type 에 크기 지정되어 있는 경우
        if (fieldType.contains("(")) {
            int endIndex =fieldType.lastIndexOf("(");

            fieldType = fieldType.substring(0, endIndex);
        }
        
        //db Type 에 없는 타입일 경우 null 반환
        if (!TypeEnum.BY_DB_TYPE.containsKey(fieldType)) {
            return null;
        }

        //camelCase 변환, java type 변환 -> SimpleColumn 생성 후 반환
        return SimpleColumn.builder()
                .name(StringUtils.toCamelCase(fieldName))
                .type(TypeEnum.valueOfDbType(fieldType).javaType())
                .build();
    }
}
