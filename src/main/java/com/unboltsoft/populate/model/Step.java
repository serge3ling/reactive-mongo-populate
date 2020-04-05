package com.unboltsoft.populate.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Document
public class Step {
    @Id
    private ObjectId id;

    private int order;
    private String title = "";
    private String content = "";

    public Step() {}

    public Step(int order, String title, String content) {
        this.order = order;
        this.title = title;
        this.content = content;
    }

    public Step(@NonNull StepDTO stepDTO) {
        this.id = stepDTO.getId();
        this.order = stepDTO.getOrder();
        this.title = stepDTO.getTitle();
        this.content = stepDTO.getContent();
    }

    @NonNull
    public static List<Step> createByDTOList(@NonNull List<StepDTO> stepDTOList) {
        return stepDTOList.stream().map(Step::new).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step that = (Step) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public ObjectId getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
