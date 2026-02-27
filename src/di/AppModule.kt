package di

import data.datasource.MetroJsonDataSource
import data.repo.MetroRepositoryImpl
import domain.usecase.CalculateFareUseCase
import domain.usecase.CalculateTimeUseCase
import domain.usecase.GetDirectionUseCase
import presentation.ConsoleController
import domain.usecase.GetFirstStationUseCase
import domain.usecase.GetLastStationUseCase
import domain.usecases.BFSUseCase
import domain.usecases.FindRouteUseCase

object AppModule {

    fun provideController(): ConsoleController {

        val dataSource =
            MetroJsonDataSource(
                "cairo_metro_structured.json"
            )

        val repository =
            MetroRepositoryImpl(
                dataSource
            )

        val fare = CalculateFareUseCase()
        val time = CalculateTimeUseCase(repository)
        val bfsUseCase = BFSUseCase()

        val findRoute = FindRouteUseCase(
            repository, fare,
            time,
            bfsUseCase
        )
        val getFirstStationUseCase = GetFirstStationUseCase(repository)
        val getLastStationUseCase = GetLastStationUseCase(repository)
        val direction = GetDirectionUseCase(getFirstStationUseCase, getLastStationUseCase)

        return ConsoleController(
            findRoute,
            direction
        )
    }
}