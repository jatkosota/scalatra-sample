import sample.app._
import org.scalatra._
import javax.servlet.ServletContext

import org.slf4j.LoggerFactory
import com.mchange.v2.c3p0.ComboPooledDataSource
import slick.driver.H2Driver.api._

class ScalatraBootstrap extends LifeCycle {

  val logger = LoggerFactory.getLogger(getClass)

  val cpds = new ComboPooledDataSource
  logger.info("Created c3p0 connection pool")

  override def init(context: ServletContext) {
    val db = Database.forDataSource(cpds)
    context.mount(new SlickApp(db), "/*")
  }

  private def closeDbConnection() {
    logger.info("Closing c3p0 connection pool")
    cpds.close
  }

  override def destroy(context: ServletContext) {
    super.destroy(context)
    closeDbConnection
  }
}
