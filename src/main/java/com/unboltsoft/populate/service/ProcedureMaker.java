package com.unboltsoft.populate.service;

import com.unboltsoft.populate.config.ConfigProperties;
import com.unboltsoft.populate.model.Procedure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcedureMaker {
    private final int count;
    private final ConfigProperties configProperties;

    public ProcedureMaker(int count, ConfigProperties configProperties) {
        this.count = count;
        this.configProperties = configProperties;
    }

    public List<Procedure> make() {
        List<Procedure> list = new ArrayList<Procedure>();

        for (int i = 0; i < count; i++) {
            list.add(new Procedure(configProperties.getFakeDescription(),
                    "Description " + i,
                    Collections.singletonList("tag" + i),
                    Collections.singletonList("Step" + i)));
        }

        return list;
    }
}
