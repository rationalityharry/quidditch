package ru.quidditch.webapp.data.service;

import ru.quidditch.webapp.data.entity.NewsEntity;
import ru.quidditch.webapp.data.entity.TeamEntity;

import java.util.List;

public interface NewsService {

    NewsEntity save(NewsEntity newsEntity);

    List<NewsEntity> getNews(TeamEntity team);
}
