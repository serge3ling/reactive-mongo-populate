package com.unboltsoft.populate.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StepDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private int order;

    @NonNull
    private String title = "";

    @NonNull
    private String content = "";

    public StepDTO(ObjectId id, int order, String title, String content) {
        this.id = id;
        this.order = order;
        this.title = title;
        this.content = content;
    }

    public StepDTO(@NonNull Step step) {
        this(step.getId(), step.getOrder(), step.getTitle(), step.getContent());
    }

    @NonNull
    public static List<StepDTO> createByStepList(@NonNull List<Step> stepList) {
        return stepList.stream().map(StepDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepDTO that = (StepDTO) o;
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
