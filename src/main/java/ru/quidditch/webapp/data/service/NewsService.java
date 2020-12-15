package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.NewsEntity;
import ru.quidditch.webapp.data.enums.NewsTypes;

import java.util.List;

public interface NewsService {

    NewsEntity save(NewsEntity newsEntity);

    List<NewsEntity> getNews(NewsTypes types);

}
