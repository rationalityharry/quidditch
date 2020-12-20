package ru.quidditch.webapp.data.enums;

import java.util.List;

public enum PlayerPosition {

    HUNTER("Охотник"),
    BEATER("Вышибала"),
    DEFENDER("Защитник"),
    NO_POSITION("Без позиции"),
    SEEKER("Искатель");

    private String name;

    PlayerPosition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PlayerPosition getByName(String positionName){
        final PlayerPosition[] result = new PlayerPosition[1];
        List.of(PlayerPosition.values()).forEach(playerPosition -> {
            if (playerPosition.getName().equals(positionName)){
                result[0] = playerPosition;
            }
        });
        return result[0];
    }

    public void setName(String name) {
        this.name = name;
    }
}
