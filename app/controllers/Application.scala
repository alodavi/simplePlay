package controllers

import models.{UrlRecord, UrlRecords}
import play.api.mvc._
import services.UrlService
import services.UrlService._

import scala.util.{Failure, Success}


object Application  extends Controller {



  def index = Action {
    Ok(views.html.main())
  }

  def sample = Action {
    Ok(views.html.sample())
  }

  def show(name:String) = Action {
    Ok("Hello " + name + "!")
  }

  def newUrl(url:String) = Action{ implicit request =>
    val url2:String =UrlRecords.find(url) match {
      case Success(c) => c.newUrl
      case Failure(e) => UrlService.newUrl(url,request.path)
    }

    val jsonResponse = toJson(url, url2)
    Ok(jsonResponse)
//    val record = UrlRecord(None,url = url, newUrl = newUrl) //todo fix if/else + redirect to website

  }

  def greet(name: String): Result = Ok("Hello " + name)

  def redirect(newUrl : String) = Action { implicit request =>

    val url:String =  "http://localhost:9000/"+ newUrl
      val oldUrl = UrlService.oldUrl(url)
      Redirect(oldUrl)

  }

}
