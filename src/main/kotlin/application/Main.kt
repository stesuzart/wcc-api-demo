package application

import dao.LocationDao
import io.javalin.Javalin

fun main(){

    //utilize essa variavel para chamar as funções
    val locationDao = LocationDao()

    val app = Javalin.create().apply{
        exception(Exception::class.java) { e, ctx -> e.printStackTrace()}
        error(404) { ctx -> ctx.json("NOT FOUND") }
    }.start(7000)

    app.routes {

        //TODO EXERCICIO 1
        //insira seu código aqui

        //TODO EXERCICIO 2
        //insira seu código aqui

        /*TODO DESAFIO 1
        * Dica: Utilize essa variavel abaixo para pegar o body da requisição
        *
        * val localizacao = ctx.bodyAsClass<Location>()
        * */

        //TODO DESAFIO 2
        //insira seu código aqui

        //TODO DESAFIO 3
        //insira seu código aqui

    }

}
