package com.golmansax.app

import com.github.nscala_time.time.Imports._

object ViewHelper {

  def formatTimestamp(timestamp: Long) : String = {
    val time = new DateTime(timestamp * 1000)
    val fmt = DateTimeFormat.forPattern("EE, MMM dd yyyy")
    return fmt.print(time)
  }

}
