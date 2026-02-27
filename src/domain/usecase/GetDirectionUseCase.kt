package domain.usecase

import domain.model.Station

class GetDirectionUseCase ( private val getFirstStationUseCase: GetFirstStationUseCase,
                private val getLastStationUseCase: GetLastStationUseCase
    ){
    operator fun invoke(
        current: Station,
        next : Station,
        ): String{
        val first = getFirstStationUseCase.execute(current.line)
        val last = getLastStationUseCase(current.line) //invoke
        return if(next.order>current.order){
             last
        }else{
             first
        }
    }
}