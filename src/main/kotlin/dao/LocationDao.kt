package dao

import model.Location
import java.util.concurrent.atomic.AtomicInteger

class LocationDao {
    private val locationDao = hashMapOf(
        0 to Location(id =0, mensagem = "Voce esta em algum lugar na galaxia Andromedra", longitude = 41.40338, latitude = 2.17403, galaxia = "Andromedra"),
        1 to Location(id =1, mensagem = "Voce esta no Planeta de Gatos Super Carinhosos", longitude = 61.40338, latitude = 6.17403, galaxia = "Andromedra", planeta = "Planeta de Gatos Super Carinhosos"),
        2 to Location(id =2, mensagem = "Faco a minima ideia, nao foi nomeado ainda este planeta", longitude = 51.40338, latitude = 5.17403, galaxia = "Somewhere", planeta = "Stelar"),
        3 to Location(id =3, mensagem = "Hummmm... nao conheco essa localizacao, voce tera que inseri-la", longitude = 0.0, latitude = 0.0)

    )

    var lastId: AtomicInteger = AtomicInteger(locationDao.size - 1)

    fun getAll() = locationDao

    fun getActualLocation(): Location? {
        val copy = locationDao
        val random = (1..locationDao.size).random()
        return copy[random-1]
    }

    fun save(mensagem: String, longitude: Double, latitude: Double, planeta: String? = null, galaxia: String? = null,){
        val id = lastId.incrementAndGet()
        locationDao.put(id, Location(
            mensagem = mensagem,
            longitude = longitude,
            latitude = latitude,
            planeta = planeta,
            galaxia = galaxia,
            id = id))
    }

    fun findById(id: Int): Location? {
        return locationDao[id]
    }

    fun findByPlanet(planeta: String): Location? {
        return locationDao.values.find { it.planeta == planeta }
    }

    fun update(id: Int, location: Location){

        locationDao.put(id,
            Location(
                mensagem = location.mensagem,
                planeta = location.planeta,
                galaxia = location.galaxia,
                latitude = location.latitude,
                longitude = location.longitude,
                id = id))
    }

    fun delete(id: Int) {
        locationDao.remove(id)
    }

}