package pl.infirsoft.trayme.payload

data class SpacePayload(
    val name: String,
    val moduleId: Int
)

data class SpaceUpdatePayload(
    val name: String
)
