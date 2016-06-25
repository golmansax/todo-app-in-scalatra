import com.mongodb.casbah.Imports._
import com.golmansax.app._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    val mongoClient = MongoClient()
    val mongoColl = mongoClient("todo_app_in_scala")("todos")
    context.mount(new TodoAppServlet(mongoColl), "/*")
  }
}
