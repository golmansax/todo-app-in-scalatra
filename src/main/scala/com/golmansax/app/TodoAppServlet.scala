package com.golmansax.app

import org.scalatra._

import com.mongodb.casbah.Imports._

class TodoAppServlet(mongoColl: MongoCollection) extends TodoAppStack {

  get("/") {
    contentType="text/html"

    val todos = mongoColl.find()
    jade("index", "todos" -> todos)
  }

  post("/create-todo") {
    val timestamp = System.currentTimeMillis / 1000
    val todo = MongoDBObject("name" -> params("name"), "timestamp" -> timestamp)
    mongoColl += todo

    redirect("/")
  }

}
