package ru.Quidditch.webapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import ru.quidditch.webapp.data.entity.GameEntity;
import ru.quidditch.webapp.data.entity.OperatorEntity;
import ru.quidditch.webapp.data.entity.TeamEntity;
import ru.quidditch.webapp.data.repository.GameRepository;
import ru.quidditch.webapp.data.service.GameService;

import java.util.Date;

public class MatchTests {
    @Autowired
    GameService gameService;
    @Autowired
    GameRepository gameRepository;

    OperatorEntity operator;
    Date date;
    Date time;

    @Before
    public void setUpOperator() {
        operator = new OperatorEntity();
        date = new Date();
        time = new Date();
        gameService = Mockito.mock(GameService.class);
        gameRepository = Mockito.mock(GameRepository.class);
    }

    @Test
    public void matchTest() {
        GameEntity gameEntity1 = new GameEntity();
        gameEntity1.setOperator(operator);
        gameEntity1.setTeam2(null);
        Assert.assertNull( gameEntity1.getTeam2());

        gameEntity1.setLocation("London");
        gameEntity1.setTime(time);
        gameEntity1.setDate(date);
        gameEntity1.setTeam1Score(0);
        gameEntity1.setTeam2Score(0);

    }
}
