package zoo.pubg.service.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import zoo.pubg.service.dto.AssetDto;
import zoo.pubg.service.dto.DeserializedMatchDto;
import zoo.pubg.service.dto.IncludedDto;
import zoo.pubg.service.dto.MatchDataDto;
import zoo.pubg.service.dto.ParticipantDto;
import zoo.pubg.service.dto.RosterDto;
import zoo.pubg.service.parser.deserialization.match.MatchInformation;

class MatchApiParserTest {

    private final static String json = """
                        {
                            "data": {
                                "type": "match",
                                "id": "b79c05af-bfb3-4744-b9d2-9ccdbe4c0746",
                                "attributes": {
                                    "isCustomMatch": false,
                                    "matchType": "official",
                                    "seasonState": "progress",
                                    "createdAt": "2024-10-18T16:02:51Z",
                                    "gameMode": "squad",
                                    "titleId": "bluehole-pubg",
                                    "shardId": "kakao",
                                    "tags": null,
                                    "duration": 1531,
                                    "stats": null,
                                    "mapName": "Baltic_Main"
                                },
                                "relationships": {
                                    "rosters": {
                                        "data": [
                                            {
                                                "type": "roster",
                                                "id": "33111a61-6328-4daa-83a2-6c51f7421930"
                                            }
                                        ]
                                    },
                                    "assets": {
                                        "data": [
                                            {
                                                "type": "asset",
                                                "id": "2b177796-8d6e-11ef-aae5-8e2d0dc0c1b1"
                                            }
                                        ]
                                    }
                                },
                                "links": {
                                    "self": "https://api.pubg.com/shards/kakao/matches/b79c05af-bfb3-4744-b9d2-9ccdbe4c0746",
                                    "schema": ""
                                }
                            },
                            "included": [
                                {
                                    "type": "participant",
                                    "id": "b95daaf8-4f44-446f-95d7-2f2665291b7e",
                                    "attributes": {
                                        "stats": {
                                            "DBNOs": 0,
                                            "assists": 1,
                                            "boosts": 2,
                                            "damageDealt": 48.6,
                                            "deathType": "byplayer",
                                            "headshotKills": 0,
                                            "heals": 0,
                                            "killPlace": 75,
                                            "killStreaks": 0,
                                            "kills": 0,
                                            "longestKill": 0,
                                            "name": "WW_chopretty",
                                            "playerId": "account.11242cc329fc4ca09a21a6f96b5337a4",
                                            "revives": 0,
                                            "rideDistance": 0,
                                            "roadKills": 0,
                                            "swimDistance": 0,
                                            "teamKills": 0,
                                            "timeSurvived": 389,
                                            "vehicleDestroys": 0,
                                            "walkDistance": 267.28897,
                                            "weaponsAcquired": 3,
                                            "winPlace": 21
                                        },
                                        "actor": "",
                                        "shardId": "kakao"
                                    }
                                },
                                {
                                    "type": "roster",
                                    "id": "b180098c-7842-4aa1-8d0d-323d73216a54",
                                    "attributes": {
                                        "shardId": "kakao",
                                        "stats": {
                                            "rank": 14,
                                            "teamId": 5
                                        },
                                        "won": "false"
                                    },
                                    "relationships": {
                                        "team": {
                                            "data": null
                                        },
                                        "participants": {
                                            "data": [
                                                {
                                                    "type": "participant",
                                                    "id": "81af3159-43c3-4130-a64f-3c44ad76ce92"
                                                },
                                                {
                                                    "type": "participant",
                                                    "id": "5a530be5-9016-46ab-8c16-ef52ca05033d"
                                                },
                                                {
                                                    "type": "participant",
                                                    "id": "70a4cb2e-0004-4316-a965-72fe478265df"
                                                },
                                                {
                                                    "type": "participant",
                                                    "id": "67c8c2bf-d022-4142-8602-d34ffb7d813c"
                                                }
                                            ]
                                        }
                                    }
                                },
                                {
                                    "type": "asset",
                                    "id": "2b177796-8d6e-11ef-aae5-8e2d0dc0c1b1",
                                    "attributes": {
                                        "name": "telemetry",
                                        "description": "",
                                        "createdAt": "2024-10-18T16:29:38Z",
                                        "URL": "https://telemetry-cdn.pubg.com/bluehole-pubg/kakao/2024/10/18/16/29/2b177796-8d6e-11ef-aae5-8e2d0dc0c1b1-telemetry.json"
                                    }
                                }
                            ],
                            "links": {
                                "self": "https://api-origin.pubg-odapi.pubg.io/shards/kakao/matches/b79c05af-bfb3-4744-b9d2-9ccdbe4c0746"
                            },
                            "meta": {}
                        }
            """;

    /*
     */

    private final MatchApiParser parser = new MatchApiParser();

    @Test
    void matchDataParseTest() throws JsonProcessingException {
        MatchInformation matchInformation = parser.parse(json);
        DeserializedMatchDto matchDto = matchInformation.toDto();
        MatchDataDto matchDataDto = matchDto.matchDataDto();

        assertThat(matchDataDto.matchId()).isEqualTo("b79c05af-bfb3-4744-b9d2-9ccdbe4c0746");
        assertThat(matchDataDto.mapName()).isEqualTo("Baltic_Main");
    }

    @Test
    void participantParseTest() throws JsonProcessingException {
        MatchInformation matchInformation = parser.parse(json);
        DeserializedMatchDto matchDto = matchInformation.toDto();
        IncludedDto includedDto = matchDto.includedDto();
        ParticipantDto participantDtos = includedDto.participantDtos().get(0);

        assertThat(participantDtos.playerMatchId()).isEqualTo("b95daaf8-4f44-446f-95d7-2f2665291b7e");
        assertThat(participantDtos.playerId()).isEqualTo("account.11242cc329fc4ca09a21a6f96b5337a4");
        assertThat(participantDtos.dbno()).isEqualTo(0);
    }

    @Test
    void rosterParseTest() throws JsonProcessingException {
        MatchInformation matchInformation = parser.parse(json);
        DeserializedMatchDto matchDto = matchInformation.toDto();
        IncludedDto includedDto = matchDto.includedDto();
        RosterDto rosterDto = includedDto.rosterDtos().get(0);

        assertThat(rosterDto.rosterId()).isEqualTo("b180098c-7842-4aa1-8d0d-323d73216a54");
        assertThat(rosterDto.rank()).isEqualTo(14);
        assertThat(rosterDto.playerMatchId().get(0)).isEqualTo("81af3159-43c3-4130-a64f-3c44ad76ce92");
    }

    @Test
    void assetParseTest() throws JsonProcessingException {
        MatchInformation matchInformation = parser.parse(json);
        DeserializedMatchDto matchDto = matchInformation.toDto();
        AssetDto assetDto = matchDto.includedDto().assetDtos().get(0);

        assertThat(assetDto.url()).isEqualTo(
                "https://telemetry-cdn.pubg.com/bluehole-pubg/kakao/2024/10/18/16/29/2b177796-8d6e-11ef-aae5-8e2d0dc0c1b1-telemetry.json");
    }


}
