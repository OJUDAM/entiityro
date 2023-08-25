package com.ujo.entityro.converting.service;

import com.ujo.entityro.converting.dto.response.SimpleColumn;

import java.util.List;

public interface AnalyzeDdlService {
    List<SimpleColumn> getColumns(String createTableString);
}
