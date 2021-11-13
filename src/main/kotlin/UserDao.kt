import java.util.concurrent.atomic.AtomicInteger

class UserDao {
    val users = hashMapOf(
        0 to User(nome = "Amanda", email = "mandy@wcc.com", id = 0),
        1 to User(nome = "Ana Carol", email = "ana@wcc.com", id = 1),
        2 to User(nome = "Angelica", email = "angel@wcc.com", id = 2),
        3 to User(nome = "Barbara", email = "babi@wcc.com", id = 3),
        4 to User(nome = "Beatriz", email = "bea@wcc.com", id = 4),
        5 to User(nome = "Cassiana", email = "cassi@wcc.com", id = 5),
        6 to User(nome = "Danusa", email = "dan@wcc.com", id = 6),
        7 to User(nome = "Dyovana", email = "dyo@wcc.com", id = 7),
        8 to User(nome = "Edith", email = "edith@wcc.com", id = 8),
        9 to User(nome = "Etiene", email = "endy@wcc.com", id = 9),
        10 to User(nome = "Fabiana", email = "fabi@wcc.com", id = 10),
        11 to User(nome = "Isabel", email = "isa@wcc.com", id = 11),
        12 to User(nome = "Isabela", email = "bela@wcc.com", id = 12),
        13 to User(nome = "Jaqueline", email = "jaq@wcc.com", id = 13),
        14 to User(nome = "Larissa", email = "lari@wcc.com", id = 14),
        15 to User(nome = "Luiza", email = "lui@wcc.com", id = 15),
        16 to User(nome = "Maria Luiza", email = "malu@wcc.com", id = 16),
        17 to User(nome = "Maria Paula", email = "mari@wcc.com", id = 17),
        18 to User(nome = "Mayara", email = "may@wcc.com", id = 18),
        19 to User(nome = "Natalia Luiza", email = "nat@wcc.com", id = 19),
        20 to User(nome = "Patricia Silva", email = "patsil@wcc.com", id = 20),
        21 to User(nome = "Patricia Sprovieri", email = "patsp@wcc.com", id = 21),
        22 to User(nome = "Viviane", email = "vivi@wcc.com", id = 22)
    )

    var lastId: AtomicInteger = AtomicInteger(users.size - 1)

    // salvar
    fun save(nome: String, email: String){
        val id = lastId.incrementAndGet()
        users.put(id, User(nome = nome, email = email, id = id))
    }

    // procurar um User através do id
    fun findById(id: Int): User? {
        return users[id]
    }

    // procurar um user por email
    fun findByEmail(email: String): User? {
        return users.values.find { it.email == email }
    }

    // atualizar o User
    fun update(id: Int, user: User){
        users.put(id, User(nome = user.nome, email= user.email, id = id))
    }

    // deletar um User
    fun delete(id: Int) {
        users.remove(id)
    }

}