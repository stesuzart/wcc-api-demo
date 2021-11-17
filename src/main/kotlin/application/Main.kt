package application

import dao.LocationDao
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.Context
import model.Location

fun main() {

    //utilize essa variavel para chamar as funções
    val locationDao = LocationDao()

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("NOT FOUND") }
    }.start(7000)

    app.routes {

        //TODO EXERCICIO 1
        get("/locations") { ctx ->
            ctx.json(locationDao.getActualLocation()!!)
            ctx.status(200)
        }


        //TODO EXERCICIO 2
        get("/locations/{Location-id}") { ctx ->
            val id = locationDao.findById(ctx.pathParam("Location-id").toInt())!!
            ctx.json(id)
            ctx.status(200)

        }

        //TODO DESAFIO 1
        post("/locations") { ctx ->
            val localizacao = ctx.bodyAsClass<Location>()
            locationDao.save(
                mensagem = localizacao.mensagem,
                longitude = localizacao.longitude,
                latitude = localizacao.latitude,
                galaxia = localizacao.galaxia,
                planeta = localizacao.planeta
            )
            ctx.status(201)
        }


        //TODO DESAFIO 2
        delete("/locations/{id}") { ctx ->
            val del = locationDao.delete(
                ctx.pathParam("id").toInt()
            )!!
            ctx.json(del)
            ctx.status(204)
        }

//        //TODO DESAFIO 3
       patch("/locations/{id}") { ctx ->
            val Local = ctx.bodyAsClass<Location>()
              locationDao.update(
                id = ctx.pathParam("id").toInt(),
                location = Local

              )
            ctx.status(204)
        }


    }
}







