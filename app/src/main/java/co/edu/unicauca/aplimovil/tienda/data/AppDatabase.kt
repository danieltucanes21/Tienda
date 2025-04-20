package co.edu.unicauca.aplimovil.tienda.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import edu.unicauca.apimovil.pixelplaza.PublicType
import edu.unicauca.apimovil.pixelplaza.Size
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [User::class, Product::class, CartItem::class, Purchase::class, PurchaseHistory::class, CreditCard::class],
    version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun cartItemDao(): CartItemDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao
    abstract fun creditCardDao(): CreditCardDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration() // Forzar recreación de la base de datos
                    .addCallback(AppDatabaseCallback(context))
                    .build()
                    .also { Instance = it }
            }
        }

        private class AppDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Pre-poblar la base de datos
                CoroutineScope(Dispatchers.IO).launch {
                    //populateDatabase(getDatabase(context).productDao())
                    Instance?.let { db ->
                        populateDatabase(db.productDao())
                    }

                }
            }
        }

        suspend fun populateDatabase(productDao: ProductDao) {
            val products = generateInitialProducts()
            products.forEach {
                try {
                    productDao.insertProduct(it)
                    println("Producto insertado: $it")
                } catch (e: Exception) {
                    println("Error al insertar producto: ${e.message}")
                }
            }
        }

        private fun generateInitialProducts(): List<Product> {
            return listOf(
                Product(
                    id = 0,
                    description = "Camiseta Negra",
                    price = 199.99,
                    imageUrl = "https://assets.dfb.de/uploads/000/289/444/custom_style_1_mitera_hannah-ursula.jpg?1692690626",
                    size = "M",
                    score = "5",
                    color = "Negro",
                    specifications = "100% algodón, transpirable",
                    brand = "Nike",
                    publicType = "MEN"
                ),
                Product(
                    id = 1,
                    description = "Vestido Rojo",
                    price = 349.99,
                    imageUrl = "https://th.bing.com/th/id/OIP.UAiTfjoZB_J1ISwqrzhgWgHaHa?w=481&h=481&rs=1&pid=ImgDetMain",
                    size = "L",
                    score = "4",
                    color = "Rojo",
                    specifications = "Poliéster y elastano, ideal para eventos",
                    brand = "Zara",
                    publicType = "WOMEN"
                ),
                Product(
                    id = 2,
                    description = "Pantalón Deportivo",
                    price = 259.99,
                    imageUrl = "https://th.bing.com/th/id/OIP.73sQuBGcuzn0EVEmGvKlZAHaH5?w=900&h=960&rs=1&pid=ImgDetMain",
                    size = "M",
                    score = "5",
                    color = "Gris",
                    specifications = "Tela ligera, ajuste perfecto",
                    brand = "Adidas",
                    publicType = "MEN"
                ),
                Product(
                    id = 3,
                    description = "Blusa Blanca",
                    price = 299.99,
                    imageUrl = "https://earthfullyliving.com/wp-content/uploads/2022/08/extending-clothes-life-sustainable-fashion-200x300.jpg",
                    size = "M",
                    score = "4",
                    color = "Blanco",
                    specifications = "Tela fresca, ideal para oficina",
                    brand = "H&M",
                    publicType = "WOMEN"
                ),
                Product(
                    id = 4,
                    description = "Zapatillas Running",
                    price = 499.99,
                    imageUrl = "https://images.fashionmodeldirectory.com/images/models/74993/lulu-reynolds-557589-squaremedium.jpg",
                    size = "M",
                    score = "5",
                    color = "Azul",
                    specifications = "Tecnología Air, suela antideslizante",
                    brand = "Nike",
                    publicType = "MEN"
                ),
                Product(
                    id = 5,
                    description = "Sudadera con Capucha",
                    price = 329.99,
                    imageUrl = "https://images.unsplash.com/photo-1590799822755-4ec061a6b2a5?q=80&w=1374&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    size = "M",
                    score = "4",
                    color = "Negro",
                    specifications = "Material térmico, bolsillo frontal",
                    brand = "Puma",
                    publicType = "WOMEN"
                ),
                Product(
                    id = 6,
                    description = "Abrigo Largo",
                    price = 699.99,
                    imageUrl = "https://i.scdn.co/image/ab67616d00001e02e254f7cd902eda979b28c012",
                    size = "M",
                    score = "5",
                    color = "Beige",
                    specifications = "Lana y poliéster, ideal para frío intenso",
                    brand = "Mango",
                    publicType = "WOMEN"
                ),
                Product(
                    id = 7,
                    description = "Chaqueta Deportiva Niño",
                    price = 279.99,
                    imageUrl = "https://jm-moda.si/wp-content/uploads/2019/05/16-2-2-256x256.jpg",
                    size = "M",
                    score = "5",
                    color = "Verde",
                    specifications = "Resistente al agua, transpirable",
                    brand = "The North Face",
                    publicType = "CHILD"
                )
            )
        }
    }
}