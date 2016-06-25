package com.golmansax.app

import com.mongodb.casbah.Imports._
import org.scalatra.test.specs2._

// For more on Specs2, see http://etorreborre.github.com/specs2/guide/org.specs2.guide.QuickStart.html
class TodoAppServletSpec extends ScalatraSpec { def is = s2"""
  GET / on TodoAppServlet
    should return status 200      $root200
  """

  val mongoClient = MongoClient()
  val mongoColl = mongoClient("todo_app_in_scala")("todos")
  addServlet(new TodoAppServlet(mongoColl), "/*")

  def root200 = get("/") {
    status must_== 200
  }
}
