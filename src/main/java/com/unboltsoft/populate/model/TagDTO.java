package com.unboltsoft.populate.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TagDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @NonNull
    private String title = "";

    @NonNull
    private String language = "";

    public TagDTO() {}

    public TagDTO(ObjectId id, String title, String language) {
        this.id = id;
        this.title = title;
        this.language = language;
    }

    public TagDTO(@NonNull String title, @NonNull String language) {
        this.title = title;
        this.language = language;
    }

    public TagDTO(@NonNull Tag tag) {
        this(tag.getId(), tag.getTitle(), tag.getLanguage());
    }

    @NonNull
    public static List<TagDTO> createByTagList(@NonNull List<Tag> tagList) {
        return tagList.stream().map(TagDTO::new).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDTO that = (TagDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public ObjectId getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getLanguage() {
        return language;
    }
}
