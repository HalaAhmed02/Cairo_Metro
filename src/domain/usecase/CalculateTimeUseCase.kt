package domain.usecase

import domain.repository.MetroRepository

class CalculateTimeUseCase(val repo: MetroRepository) {
operator fun invoke(count: Int)= count*repo.getTravelTime()
}