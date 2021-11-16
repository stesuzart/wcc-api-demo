import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder

fun main() {
    val locationDao = LocationDao()

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(400) { ctx -> ctx.json("NOT FOUND") }
    }.start(7000)

    app.routes {

        ApiBuilder.get("/locations") { ctx ->
            ctx.json(locationDao.locations)
            ctx.status(200)
        }

        ApiBuilder.get("/locations/{id}") { ctx ->
            ctx.json(locationDao.findById(ctx.pathParam("id").toInt())!!)
            ctx.status(200)
        }

        ApiBuilder.post("/locations") { ctx ->
            val location = ctx.bodyAsClass<Location>()
            locationDao.save(
                mensagem = location.mensagem,
                longitude = location.longitude,
                latitude = location.latitude,
                galaxia = location.galaxia,
                planeta = location.planeta
            )
            ctx.status(201)
        }

        ApiBuilder.delete("/locations/{id}") { ctx ->
            locationDao.delete(ctx.pathParam("id").toInt())
            ctx.status(204)
        }

        ApiBuilder.patch("/locations/{id}") { ctx ->
            val location = ctx.bodyAsClass<Location>()
            locationDao.update(
                id = ctx.pathParam("id").toInt(),
                location = location
            )
            ctx.status(204)
        }


    }
}