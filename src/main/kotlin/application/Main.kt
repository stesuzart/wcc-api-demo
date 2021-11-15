package application

import dao.LocationDao
import io.javalin.Javalin
import model.Location
import kotlin.random.Random

fun main() {

    //utilize essa variavel para chamar as funções
    val locationDao = LocationDao()

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("NOT FOUND") }
    }.start(7000)

    app.routes {

        //TODO EXERCICIO 1
        app.get("/locations") { ctx ->
            val resultado = locationDao.getAll()
            val resultado0 = resultado.get(Random.Default.nextInt(resultado.size))

            if (resultado0 != null) {
                ctx.status(200)
                ctx.json(resultado0)
            } else {
                ctx.status(404)
            }
        }

        //TODO EXERCICIO 2
        app.get("/locations/{id}") { ctx ->
            val idLocalizacao = ctx.pathParam("id").toInt()
            val resultado: Location? = locationDao.findById(idLocalizacao)
            if (resultado == null) {
                ctx.status(404)
            } else {
                ctx.status(200)
                ctx.json(resultado)
            }
        }

        //TODO DESAFIO 1
        app.post("locations") { ctx ->
            val localizacao = ctx.bodyAsClass<Location>()
            ctx.status(201)
            ctx.result("Created")
            locationDao.save(localizacao.mensagem, localizacao.longitude, localizacao.latitude,localizacao.planeta, localizacao.galaxia)
        }

        //TODO DESAFIO 2
        app.delete("/locations/{id}"){ctx ->
            locationDao.delete(ctx.pathParam("id").toInt())
            ctx.status(204)
        }

        //TODO DESAFIO 3
        app.patch("locations/{id}"){ctx ->
            locationDao.update(ctx.pathParam("id").toInt(),ctx.bodyAsClass<Location>())
            ctx.status(204)
        }
    }
}
