package pl.infirsoft.trayme.exception

class UserNotFoundException(userPassword: String) : RuntimeException("User with id $userPassword not found")