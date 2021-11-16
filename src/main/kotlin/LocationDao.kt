import java.util.concurrent.atomic.AtomicInteger

class LocationDao {

    val locations = hashMapOf(
        0 to Location( 1, "Voce esta em algum lugar na galaxia Andromedra", 54.56155, 24.57410, "Plutao", "Pluplu"),
        3 to Location( 3, "Hummmm... nao conheco essa localizacao, voce tera que inseri-la", 0.0, 0.0, "galaxia nao identificada :(", "galaxia nao identificada :(")

    )

    var lastId: AtomicInteger = AtomicInteger(locations.size -1)

    fun findById(id: Int): Location? {
        return locations[id]
    }

    fun save(mensagem: String, longitude: Double, latitude: Double, galaxia: String, planeta: String) {
        val id = lastId.incrementAndGet()
        locations.put(id, Location(id = id, mensagem = mensagem, longitude = longitude, latitude = latitude, galaxia = galaxia, planeta = planeta))
    }

    fun update (id: Int, location: Location) {
        locations.put(id, Location(id = location.id, mensagem = location.mensagem, longitude = location.longitude, latitude = location.latitude, galaxia = location.galaxia, planeta = location.planeta))
    }

    fun delete(id: Int) {
        locations.remove(id)
    }
}
