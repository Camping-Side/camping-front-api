package com.commerce.song.service;

import com.commerce.song.domain.entity.Resources;

import java.util.List;

public interface ResourcesService {
    Resources getResources(long id);

    List<Resources> getResources();

    void createResources(Resources Resources);

    void deleteResources(long id);
}
