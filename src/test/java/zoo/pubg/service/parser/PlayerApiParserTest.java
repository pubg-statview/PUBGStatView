package zoo.pubg.service.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.jupiter.api.Test;
import zoo.pubg.domain.Player;
import zoo.pubg.service.dto.player.PlayerData;
import zoo.pubg.service.dto.player.PlayerDto;

class PlayerApiParserTest {

    private final static String json = """
            {
                "data": [
                    {
                        "type": "player",
                        "id": "account.a164c65de7bd46f3a0101d4b8ad4a626",
                        "attributes": {
                            "stats": null,
                            "titleId": "pubg",
                            "shardId": "kakao",
                            "patchVersion": "",
                            "banType": "Innocent",
                            "clanId": "clan.96147d243bf044eda155a5e04fe91173",
                            "name": "Lil_Ziu__Vert"
                        },
                        "relationships": {
                            "assets": {
                                "data": []
                            },
                            "matches": {
                                "data": [
                                    {
                                        "type": "match",
                                        "id": "90cc5f77-5d9a-4473-88b4-e55659120632"
                                    },
                                    {
                                        "type": "match",
                                        "id": "e6d426e9-84a8-4f8e-a000-27a3bf7f4194"
                                    }
                                ]
                            }
                        },
                        "links": {
                            "self": "https://api.pubg.com/shards/kakao/players/account.a164c65de7bd46f3a0101d4b8ad4a626",
                            "schema": ""
                        }
                    }
                ],
                "links": {
                    "self": "https://api.pubg.com/shards/kakao/players?filter[playerNames]=Lil_Ziu__Vert"
                },
                "meta": {}
            }""";

    private final PlayerApiParser parser = new PlayerApiParser();

    @Test
    void parse() throws JsonProcessingException {
        PlayerDto playerDto = parser.parse(json);

        assertThat(playerDto.data().size()).isEqualTo(1);

        PlayerData playerData = playerDto.data().get(0);
        Player player = playerData.toEntity();

        assertThat(player.getPlayerId()).isEqualTo("account.a164c65de7bd46f3a0101d4b8ad4a626");
        assertThat(player.getName()).isEqualTo("Lil_Ziu__Vert");
        assertThat(player.getShardId()).isEqualTo("kakao");
        assertThat(player.getClanId()).isEqualTo("clan.96147d243bf044eda155a5e04fe91173");

        List<String> matchIds = playerData.getMatchIds();
        assertThat(matchIds.size()).isEqualTo(2);
    }
}