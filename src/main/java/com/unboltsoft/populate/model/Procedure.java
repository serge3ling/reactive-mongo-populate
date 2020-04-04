package com.unboltsoft.populate.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document
public class Procedure {
  @Id private ObjectId id;

  private String title = "";
  private String description = "";
  private List<String> tags = new ArrayList<>();
  private List<String> steps = new ArrayList<>();
  private boolean inactive = Boolean.FALSE;

  public Procedure() {}

  public Procedure(
      @NonNull String title,
      @NonNull String description,
      @NonNull List<String> tags,
      @NonNull List<String> steps) {
    this.title = title;
    this.description = description;
    this.tags = tags;
    this.steps = steps;
  }

  @NonNull
  public static Procedure createByDTO(@NonNull ProcedureDTO procedureDTO) {
    return new Procedure(
        procedureDTO.getTitle(),
        procedureDTO.getDescription(),
        procedureDTO.getTags(),
        procedureDTO.getSteps());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProcedureDTO that = (ProcedureDTO) o;
    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getTags() {
    return tags;
  }

  public List<String> getSteps() {
    return steps;
  }

  @NonNull
  private String listToString(List<String> list) {
    StringBuilder builder = new StringBuilder("[");

    if (list != null) {
      builder.append(String.join(", ", list.toString()));
    }

    return builder.append("]").toString();
  }

  public boolean isInactive() {
    return inactive;
  }

  public void setInactive(boolean inactive) {
    this.inactive = inactive;
  }

  @NonNull
  @Override
  public String toString() {
    return this.getClass().getName()
        + " { id: \""
        + (id != null ? id.toHexString() : "null")
        + "\", title: \""
        + title
        + "\", description: \""
        + description
        + "\", tags: \""
        + listToString(tags)
        + "\", steps: "
        + listToString(steps)
        + " }";
  }
}
