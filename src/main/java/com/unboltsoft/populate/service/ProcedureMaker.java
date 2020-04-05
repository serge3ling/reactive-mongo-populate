package com.unboltsoft.populate.service;

import com.unboltsoft.populate.config.ConfigProperties;
import com.unboltsoft.populate.model.Procedure;
import com.unboltsoft.populate.model.Tag;
import com.unboltsoft.populate.repository.StepRepository;
import com.unboltsoft.populate.repository.TagRepository;
import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProcedureMaker {
    private Random random = new Random();

    private final ConfigProperties configProperties;

    @NonNull
    private final StepRepository stepRepository;
    @NonNull
    private final TagRepository tagRepository;

    private List<ObjectId> steps;
    private List<Tag> tags;

    public ProcedureMaker(ConfigProperties configProperties,
                          StepRepository stepRepository, TagRepository tagRepository) {
        this.configProperties = configProperties;
        this.stepRepository = stepRepository;
        this.tagRepository = tagRepository;
    }

    private int makeRandomInt(int bound) {
        return random.nextInt(bound);
    }

    private List<ObjectId> readSteps() {
        if (steps == null) {
            steps = stepRepository.findAll().collectList().block()
                    .stream().map(step -> step.getId()).collect(Collectors.toList());
        }

        return steps;
    }

    private List<Tag> readTags() {
        if (tags == null) {
            tags = tagRepository.findAll().collectList().block();
        }

        return tags;
    }

    private List<ObjectId> randomStepIds() {
        int amount = makeRandomInt(readSteps().size());
        HashSet<ObjectId> set = new HashSet<ObjectId>();

        for (int i = 0; i < amount; i++) {
            set.add(readSteps().get(makeRandomInt(readSteps().size())));
        }

        return new ArrayList<ObjectId>(set);
    }

    private List<Tag> randomTags() {
        int amount = makeRandomInt(readTags().size());
        HashSet<Tag> set = new HashSet<Tag>();

        for (int i = 0; i < amount; i++) {
            set.add(readTags().get(makeRandomInt(readTags().size())));
        }

        return new ArrayList<Tag>(set);
    }

    public void haveReady() {
        readSteps();
        readTags();
    }

    public List<Procedure> make(int count) {
        List<Procedure> list = new ArrayList<Procedure>();

        for (int i = 0; i < count; i++) {
            list.add(new Procedure(configProperties.getFakeTitle(),
                    "Description " + i,
                    randomTags(),
                    randomStepIds()));
        }

        return list;
    }
}
