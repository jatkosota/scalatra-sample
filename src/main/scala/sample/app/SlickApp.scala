package sample.app

import org.scalatra._
import slick.driver.H2Driver.api._

class SlickApp(val db: Database) extends ScalatraServlet with FutureSupport with SlickRoutes {

  protected implicit def executor = scala.concurrent.ExecutionContext.Implicits.global
}
