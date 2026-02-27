package domain.usecase

import domain.model.MetroLine
import domain.model.Station
import java.util.LinkedList
import java.util.Queue

class BFSUseCase {
    operator fun invoke(start: Station, end: Station, stations: List<Station>): List<Station>? {
        if (start == end) return listOf(start)
        val queue: Queue<Station> = LinkedList()
        val visited = mutableSetOf<Station>()
        val parent = mutableMapOf<Station, Station?>()
        queue.add(start)
        visited.add(start)
        parent[start] = null

        val stationsByLine = stations.groupBy { it.line }
        val stationsByName = stations.groupBy { it.name }
        while (queue.isNotEmpty()) {
            val current = queue.poll()
            if (current == end) {
                return buildpath(end, parent)
            }
            val neighbours = getNeighbour(current, stationsByLine, stationsByName)
            for (neighbour in visited) {
                if (neighbour in visited) {
                    visited.add(neighbour)
                    queue.add(neighbour)
                    parent[neighbour] = current
                }
            }
        }
        return null
    }
}
private fun getNeighbour(
    station: Station,
    stationsByLine: Map<MetroLine, List<Station>>,
    stationsByName: Map<String, List<Station>>
): List<Station> {
    val sameLine = stationsByLine[station.line]
        ?.filter {
            it.order == station.order + 1 || it.order == station.order - 1
        } ?: emptyList()
    val transfers = if (station.is_transfer) {
        stationsByName[station.name]?.filter { it.line != station.line } ?: emptyList()
    } else {
        emptyList<Station>()
    }
    return sameLine + transfers
}

private fun buildpath(
    end: Station,
    parent: Map<Station, Station?>
): List<Station> {
    val path = mutableListOf<Station>()
    var current: Station? = end
    while (current != null) {
        path.add(current)
        current = parent[current]
    }
    return path.reversed()
}