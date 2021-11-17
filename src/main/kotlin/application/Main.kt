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
        get("/locations") {
            ctx -> ctx.json(locationDao.getActualLocation()!!)
            ctx.status(200)
        }

        get("/locations/tudo") {
                ctx -> ctx.json(locationDao.getAll()!!)
            ctx.status(200)
        }



        //TODO EXERCICIO 2
        get ("/locations/{id}"){ ctx ->
            ctx.json(locationDao.findById(ctx.pathParam("id").toInt())!!)
            ctx.status(200)

        }

        /*TODO DESAFIO 1
        * Dica: Utilize essa variavel abaixo para pegar o body da requisição
        */
        post("/locations") { ctx ->
            val localizacao = ctx.bodyAsClass<Location>()
            locationDao.save(
                mensagem = localizacao.mensagem,
                longitude = localizacao.longitude,
                latitude = localizacao.latitude,
                planeta = localizacao.planeta,
                galaxia = localizacao.galaxia
            )
            ctx.status(201)
            ctx.html("<b>Olá<b>")

        }

        //TODO DESAFIO 2
        delete("/locations/{id}"){
            ctx ->
            ctx.json(locationDao.delete(ctx.pathParam("id").toInt())!!)
            ctx.status(204)
        }

        //TODO DESAFIO 3
        patch("/locations/{id}"){ ctx ->
            val localizacao = ctx.bodyAsClass<Location>()
            val id = ctx.pathParam("id").toInt()
            locationDao.update(id, localizacao)
            ctx.status(204)

        }

        get("/locations/planet/{nome}"){
            ctx ->
            val nomePlaneta = ctx.pathParam("nome")
            ctx.json(locationDao.findByPlanet(nomePlaneta)!!)
            ctx.status(200)
        }



    }

}
