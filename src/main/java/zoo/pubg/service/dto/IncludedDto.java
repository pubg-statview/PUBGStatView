package zoo.pubg.service.dto;

import java.util.List;

public record IncludedDto(
        List<AssetDto> assetDtos,
        List<ParticipantDto> participantDtos,
        List<RosterDto> rosterDtos
) {

    public String getTelemetryUrl() {
        if (assetDtos.size() == 0) {
            return "";
        }
        return assetDtos.get(0).url();
    }
}
