package presentation

import di.AppModule

fun main() {
    val controller =
        AppModule.provideController()

    controller.start()
}