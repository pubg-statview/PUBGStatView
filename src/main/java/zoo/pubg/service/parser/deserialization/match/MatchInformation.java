package zoo.pubg.service.parser.deserialization.match;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import zoo.pubg.service.dto.AssetDto;
import zoo.pubg.service.dto.DeserializedMatchDto;
import zoo.pubg.service.dto.IncludedDto;
import zoo.pubg.service.dto.MatchDataDto;
import zoo.pubg.service.dto.ParticipantDto;
import zoo.pubg.service.dto.RosterDto;
import zoo.pubg.service.parser.deserialization.match.data.Data;
import zoo.pubg.service.parser.deserialization.match.included.Asset;
import zoo.pubg.service.parser.deserialization.match.included.Included;
import zoo.pubg.service.parser.deserialization.match.included.Participant;
import zoo.pubg.service.parser.deserialization.match.included.Roster;

@Getter
public class MatchInformation {

    private Data data;
    private List<Included> included;

    public DeserializedMatchDto toDto() {
        return new DeserializedMatchDto(
                getMatchDataDto(), getIncludedDto()
        );
    }

    private MatchDataDto getMatchDataDto() {
        return data.toDto();
    }

    private IncludedDto getIncludedDto() {
        List<AssetDto> assetDtos = new ArrayList<>();
        List<ParticipantDto> participantDtos = new ArrayList<>();
        List<RosterDto> rosterDtos = new ArrayList<>();

        for (Included inc : included) {
            if (inc instanceof Asset asset) {
                assetDtos.add(asset.toDto());
            } else if (inc instanceof Participant participant) {
                participantDtos.add(participant.toDto());
            } else if (inc instanceof Roster roster) {
                rosterDtos.add(roster.toDto());
            }
        }

        return new IncludedDto(assetDtos, participantDtos, rosterDtos);
    }
}
