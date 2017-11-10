package services

import play.api.libs.json._
import java.net.URL

import models.{UrlRecord, UrlRecords}

import scala.util.{Failure, Success, Try}

object UrlService {
  def oldUrl(newUrl: String) = {
    UrlRecords.findOld(newUrl) match {
      case Success(c) => c.url
      case Failure(e) => throw new Exception("Url not found in the database")
    }

  }

  def newUrl(url:String, requestUrl:String) = {
    val tiny = makeTiny(url)
    val url2 = "http://localhost:9000/"+tiny //todo get base url
    val record = UrlRecord(None,url = url, newUrl = url2)
    //    val record = UrlRecord()
    UrlRecords.create(record)
    url2
  }


  def toJson(url: String, tiny: String): JsObject ={
    JsObject(Seq(
      "url" -> JsString(url),
      "new url" -> JsString(tiny)
      )
    )
  }

  def makeTiny(url:String):Int={
    validate(url) match {
      case Success(valid) => Math.floor(100000 + Math.random() * 900000).toInt
      case Failure(error) => throw new Exception("Invalid URL")
    }
  }

  def validate(url:String)={
    Try { new URL(url) }
  }
}
