package com.unboltsoft.populate.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class ProcedureDTO {
  @Id private ObjectId id;

  private String title = "";
  private String description = "";
  private List<String> tags = Collections.emptyList();
  private List<String> steps = Collections.emptyList();

  public ProcedureDTO() {}

  public ProcedureDTO(String title, String description, List<String> tags, List<String> steps) {
    this.title = title;
    this.description = description;
    this.tags = tags;
    this.steps = steps;
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
}
