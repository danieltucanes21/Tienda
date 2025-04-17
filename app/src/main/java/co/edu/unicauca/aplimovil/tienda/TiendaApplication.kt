package co.edu.unicauca.aplimovil.tienda

import android.app.Application
import co.edu.unicauca.aplimovil.tienda.data.AppContainer
import co.edu.unicauca.aplimovil.tienda.data.AppDataContainer

class TiendaApplication : Application(){

    lateinit var container: AppContainer

    override fun onCreate(){
        super.onCreate()
        container = AppDataContainer(this)
    }
}