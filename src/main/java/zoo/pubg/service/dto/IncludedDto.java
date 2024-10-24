package zoo.pubg.service.dto;

import java.util.List;

public record IncludedDto(
        List<AssetDto> assetDtos,
        List<ParticipantDto> participantDtos,
        List<RosterDto> rosterDtos
) {
}
