package com.golmansax.app

import org.scalatra._

import com.mongodb.casbah.Imports._
import org.bson.types.ObjectId
import com.github.nscala_time.time.Imports._

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

  post("/remove-todo") {
    val query = MongoDBObject("_id" -> new ObjectId(params("id")))
    mongoColl.findAndRemove(query)
    redirect("/")
  }

  post("/mark-complete") {
    val query = MongoDBObject("_id" -> new ObjectId(params("id")))
    val update = $set("completed" -> true)
    mongoColl.update(query, update)
    redirect("/")
  }

}
