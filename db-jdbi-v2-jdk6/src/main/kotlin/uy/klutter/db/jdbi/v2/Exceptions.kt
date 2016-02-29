package uy.klutter.db.jdbi.v2

import org.skife.jdbi.v2.exceptions.DBIException

class KotlinMemberAccessException : DBIException {

    constructor(string: String, throwable: Throwable) : super(string, throwable) {}

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String) : super(message) {}

    companion object {
        private val serialVersionUID = 1L
    }
}

class NoSuchColumnMapperException : DBIException {
    constructor(string: String, throwable: Throwable) : super(string, throwable) {}

    constructor(cause: Throwable) : super(cause) {}

    constructor(message: String) : super(message) {}

    companion object {
        private val serialVersionUID = 1L
    }
}