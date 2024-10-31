package zoo.pubg.service.parser.deserialization.match.included.participant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import zoo.pubg.vo.PlayerId;
import zoo.pubg.vo.PlayerName;

public record PlayerMatchStats(
        PlayerId playerId,
        PlayerName name,
        int dbno, int kills, int assists,
        int headshotKills, float damageDealt, float longestKill, int winPlace) {

    @JsonCreator
    public static PlayerMatchStats fromJson(
            @JsonProperty("playerId") String playerId,
            @JsonProperty("name") String name,
            @JsonProperty("dbno") int dbno,
            @JsonProperty("kills") int kills,
            @JsonProperty("assists") int assists,
            @JsonProperty("headshotKills") int headshotKills,
            @JsonProperty("damageDealt") float damageDealt,
            @JsonProperty("longestKill") float longestKill,
            @JsonProperty("winPlace") int winPlace) {

        PlayerName finalName = new PlayerName((name == null || name.isEmpty()) ? playerId : name);

        return new PlayerMatchStats(
                new PlayerId(playerId),
                finalName,
                dbno, kills, assists, headshotKills, damageDealt, longestKill, winPlace
        );
    }
}
