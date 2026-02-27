package domain.usecase

import domain.model.MetroLine
import domain.repository.MetroRepository

class GetFirstStationUseCase (val repository: MetroRepository) {
    fun execute(line: MetroLine): String{
        return repository.getStations()
            .filter { it.line == line }
            .minBy { it.order }
            .name
    }
}