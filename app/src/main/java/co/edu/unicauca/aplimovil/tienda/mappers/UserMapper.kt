package co.edu.unicauca.aplimovil.tienda.mappers

import co.edu.unicauca.aplimovil.tienda.data.Product
import co.edu.unicauca.aplimovil.tienda.data.User
import edu.unicauca.apimovil.pixelplaza.ProductInfo
import edu.unicauca.apimovil.pixelplaza.PublicType
import edu.unicauca.apimovil.pixelplaza.Size

class UserMapper {
    companion object {
        fun toUserFromUserBD(user: User): co.edu.unicauca.aplimovil.tienda.models.User {
            return co.edu.unicauca.aplimovil.tienda.models.User(
                id = user.id,
                email = user.email,
                name = user.userName
            )
        }
    }
}