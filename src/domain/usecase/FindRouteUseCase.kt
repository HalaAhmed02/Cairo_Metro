package domain.usecases

import domain.model.*
import domain.repository.MetroRepository
import domain.usecase.CalculateFareUseCase
import domain.usecase.CalculateTimeUseCase

class FindRouteUseCase(
    private val repo: MetroRepository,
    private val calculateFareUseCase: CalculateFareUseCase,
    private val calculateTimeUseCase: CalculateTimeUseCase,
    private val bfsUseCase: BFSUseCase
) {

    operator fun invoke(
        startName: String,
        endName: String
    ): RouteResult {

        val stations = repo.getStations()

        val start = stations.find { it.name.equals(startName.trim(), true) }
            ?: return RouteResult.Error("Start station not found")

        val end = stations.find { it.name.equals(endName.trim(), true) }
            ?: return RouteResult.Error("End station not found")

        val path = bfsUseCase.invoke(start, end, stations)
            ?: return RouteResult.Error("No route found")


        val fare = calculateFareUseCase.invoke(path.size)
        val time = calculateTimeUseCase.invoke(path.size - 1)

        return RouteResult.Success(
            stations = path,
            fare = fare,
            time = time
        )
    }
}