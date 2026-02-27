package data.repo

import data.datasource.MetroDataSource
import data.mapper.MetroMapper
import domain.repository.MetroRepository

class MetroRepositoryImpl(
    private val dataSource: MetroDataSource
) : MetroRepository {

    override fun getStations() =
        dataSource.loadStation()
            .map { MetroMapper.toDomain(it) }

    override fun getTravelTime() = dataSource.getTravelTime()

}