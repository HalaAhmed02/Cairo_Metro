package data.mapper

import data.dto.StationDto
import domain.model.MetroLine
import domain.model.Station

object MetroMapper {
    fun toDomain(dto: StationDto): Station {
        return Station(
            id = dto.id,
            name = dto.name,
            order = dto.order,
            is_transfer = dto.is_transfer,
            line = dto.line.toMetroLine(),
            transferLines = dto.transferLines.map { it.toMetroLine() },
        )
    }

    private fun String.toMetroLine(): MetroLine =
        when (this.trim().uppercase()) {
             "FIRST LINE" -> MetroLine.LINE_1
             "SECOND LINE" -> MetroLine.LINE_2
             "THIRD LINE" -> MetroLine.LINE_3
            else -> MetroLine.LINE_0
        }
}