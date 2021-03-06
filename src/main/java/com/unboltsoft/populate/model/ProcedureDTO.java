package com.unboltsoft.populate.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class ProcedureDTO {
  @JsonSerialize(using = ToStringSerializer.class)
  @Id
  private ObjectId id;

  private String title = "";
  private String description = "";
  private List<Tag> tags = Collections.emptyList();
  private List<ObjectId> steps = Collections.emptyList();

  public ProcedureDTO() {}

  public ProcedureDTO(String title, String description, List<Tag> tags, List<ObjectId> steps) {
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

  public List<Tag> getTags() {
    return tags;
  }

  public List<ObjectId> getSteps() {
    return steps;
  }
}
