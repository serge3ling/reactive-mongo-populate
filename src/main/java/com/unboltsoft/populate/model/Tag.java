package com.unboltsoft.populate.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Tag {
    @Id
    private ObjectId id;

    @NonNull
    private String title = "";

    @NonNull
    private String language = "";

    public Tag() {}

    public Tag(ObjectId id, @NonNull String title, @NonNull String language) {
        this.id = id;
        this.title = title;
        this.language = language;
    }

    public Tag(@NonNull TagDTO tagDTO) {
        this.id = tagDTO.getId();
        this.title = tagDTO.getTitle();
        this.language = tagDTO.getLanguage();
    }

    @NonNull
    public static List<Tag> createByDTOList(@NonNull List<TagDTO> tagDTOList) {
        return tagDTOList.stream().map(Tag::new).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag that = (Tag) o;
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
