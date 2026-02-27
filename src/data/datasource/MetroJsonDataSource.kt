package data.datasource

import com.google.gson.Gson
import data.dto.MetroDto
import data.dto.StationDto
import java.io.File

class MetroJsonDataSource (private val path : String) : MetroDataSource {
    private val gson = Gson()
    private val dto by lazy {
        gson.fromJson(File(path).readText(), MetroDto::class.java)
    }
    override fun loadStation(): List<StationDto> {
        return dto.stations
    }

    override fun getTravelTime(): Int {
        return dto.travel_time_between_stations_minutes
    }
}