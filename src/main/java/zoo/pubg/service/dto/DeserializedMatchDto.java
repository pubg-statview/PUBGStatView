package zoo.pubg.service.dto;

import java.util.List;

public record DeserializedMatchDto(
        MatchDataDto matchDataDto,
        IncludedDto includedDto
) {

    public List<ParticipantDto> getParticipantDtos() {
        return includedDto.participantDtos();
    }

    public List<RosterDto> getRosterDtos() {
        return includedDto.rosterDtos();
    }

    public String getTelemetryUrl() {
        return includedDto.getTelemetryUrl();
    }
}
