package data.datasource

import data.dto.StationDto

interface MetroDataSource {
    fun loadStation(): List<StationDto>
    fun getTravelTime(): Int
}