package domain.usecase

import domain.model.RouteResult
import domain.repository.MetroRepository

class FindRouteUseCase (val repo: MetroRepository,
    val calculateFareUseCase: CalculateFareUseCase,
    val calculateTimeUseCase: CalculateTimeUseCase,
    val bsfUseCase: BFSUseCase) {
    operator fun invoke(startName: String,endName: String): RouteResult {
        val stations =repo.getStations()
        val start =  stations.find { it.name.equals(startName.trim(), true) }
            ?:  return RouteResult.Error("Station not found")

        val end =  stations.find { it.name.equals(endName.trim(), true) }
            ?:  return RouteResult.Error("Station not found")
        val path=bsfUseCase(start,end,stations)?: return RouteResult.Error("path not found")
        val fare = calculateFareUseCase(path.size)
        val time = calculateTimeUseCase(path.size)
        return RouteResult.Success(stations=stations,fare=fare,time=time)
    }
}
