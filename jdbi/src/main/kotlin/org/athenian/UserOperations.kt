package org.athenian

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.mapTo
import org.jdbi.v3.sqlobject.kotlin.onDemand
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate
import java.sql.Timestamp
import java.util.*
import java.util.stream.IntStream.range


data class User(val user_id: Long = -1,
                val created: Timestamp = Timestamp(System.currentTimeMillis()),
                val username: String,
                val first: String,
                val last: String,
                val password: String,
                val email: String)

interface UserDao {
    @SqlUpdate("insert into users (username, first, last, password, email) " +
               "values (:user.username, :user.first, :user.last, :user.password, :user.email)")
    @GetGeneratedKeys
    fun insertUser(user: User): User

    @SqlQuery("select * from users")
    fun allUsers(): List<User>
}

fun main(argv: Array<String>) {
    val props = Properties()
    props.setProperty("user", "postgres")
    props.setProperty("password", "")
    props.setProperty("ssl", "" + false)

    val db = Jdbi.create("jdbc:postgresql://localhost:5432/postgres", props)
            .installPlugins()

    val handle = db.open()

    // Clear users table
    handle.execute("delete from users")

    // DAO
    val dao = db.onDemand<UserDao>()
    range(1, 10).forEach {
        val user = User(username = "username_" + it,
                        first = "first_" + it,
                        last = "last_" + it,
                        password = "password_" + it,
                        email = "email_" + it)

        println(dao.insertUser(user))
    }

    println("\n****\n")

    dao.allUsers().forEach(::println)

    println("\n****\n")

    // Non DAO
    handle.createQuery("select * from users").mapTo<User>().list().forEach(::println)
}