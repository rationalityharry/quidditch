package ru.quidditch.webapp.data.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.quidditch.webapp.data.entity.NewsEntity;
import ru.quidditch.webapp.data.entity.TeamEntity;
import ru.quidditch.webapp.data.repository.NewsRepository;
import ru.quidditch.webapp.data.service.NewsService;

import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    public NewsRepository newsRepository;

    @Override
    public NewsEntity save(NewsEntity newsEntity) {
        return newsRepository.save(newsEntity);
    }

    @Override
    public List<NewsEntity> getNews(TeamEntity team) {
        return newsRepository.findAllByTeamOrderByDateDesc(team);
    }
}
