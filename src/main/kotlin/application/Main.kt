package application

import dao.LocationDao
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import model.Location

fun main(){

    //utilize essa variavel para chamar as funções
    val locationDao = LocationDao()

    val app = Javalin.create().apply{
        exception(Exception::class.java) { e, ctx -> e.printStackTrace()}
        error(404) { ctx -> ctx.json("NOT FOUND") }
    }.start(7000)

    app.routes {

        //TODO EXERCICIO 1
        get("/locations") { ctx ->
            ctx.json(locationDao.getAll())
        }

        //TODO EXERCICIO 2
        get("/locations/{id}") { ctx ->
            val id = ctx.pathParam("id").toInt()
            val resultado: Location? = locationDao.findById(id)

            if(resultado != null) {
               ctx.json(locationDao.findById(id)!!)
            } else {
                ctx.status(404)
            }
        }

        //TODO DESAFIO 1
        post("/locations") { ctx->
            val localizacao = ctx.bodyAsClass<Location>()
            locationDao.save(localizacao.mensagem, localizacao.longitude, localizacao.latitude,localizacao.planeta, localizacao.galaxia)
            ctx.status(201)
            ctx.result("Created")

        }

        //TODO DESAFIO 2
        delete("/locations/{id}") { ctx ->
            val id = ctx.pathParam("id").toInt()
            val resultado: Location? = locationDao.findById(id)

            if(resultado != null) {
                locationDao.delete(id)
                ctx.status(204)
            } else {
                ctx.status(404)
            }

        }

        //TODO DESAFIO 3
        patch("/locations/{id}") { ctx ->
            val localizacao = ctx.bodyAsClass<Location>()
            val id = ctx.pathParam("id").toInt()
            val result: Location? = locationDao.findById(id)

            if(result != null) {
                locationDao.update(id, localizacao)
                ctx.status(204)
            } else {
                ctx.status(404)
            }

        }

    }

}
