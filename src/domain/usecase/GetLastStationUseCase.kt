package domain.usecase

import domain.model.MetroLine
import domain.repository.MetroRepository

class GetLastStationUseCase (val repository: MetroRepository){
   operator  fun invoke(line: MetroLine): String{
        return repository.getStations()
            .filter { it.line == line }
            .maxBy { it.order }
            .name
    }
}