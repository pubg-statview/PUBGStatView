package zoo.pubg.service.dto;

public record DeserializedMatchDto(
        MatchDataDto matchDataDto,
        IncludedDto includedDto
) {
}
