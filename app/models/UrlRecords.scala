package models

import play.api.Play

import scala.slick.driver.PostgresDriver.simple._
import play.api.Play.current
import slick.lifted.TableQuery

import scala.slick.driver.JdbcProfile
import scala.util.Try

case class UrlRecord(id:Option[Long], url:String, newUrl:String)

class UrlRecords(tag: Tag) extends Table[UrlRecord](tag, "url"){
      def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
      def url = column[String]("url")
      def newUrl = column[String]("newUrl")
  def * = (id, url, newUrl)<>(UrlRecord.tupled, UrlRecord.unapply _)
}

object UrlRecords {

  val db = play.api.db.slick.DB
  val urlRecords = TableQuery[UrlRecords]
  def all: List[UrlRecord] = db.withSession{ implicit session =>
    urlRecords.sortBy(_.id.asc.nullsFirst).list
  }

  def create(newUrlRecord:UrlRecord) = db.withTransaction{ implicit session =>
    urlRecords += newUrlRecord
  }

  def find(url:String):Try[UrlRecord] = db.withSession{ implicit session =>

    Try {
      urlRecords.filter(_.url === url).first
    }
  }

  def findOld(url:String):Try[UrlRecord] = db.withSession{ implicit session =>

    Try {
      urlRecords.filter(_.newUrl === url).first
    }
  }

}

//object Tables {
//  case class UrlRecord(id:Long, url:Option[String], newUrl:Option[String])
//
//  class UrlTableDef(tag: Tag) extends Table[UrlRecord](tag, "urls"){
//    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
//    def url = column[Option[String]]("url")
//    def newUrl = column[Option[String]]("newUrl")
//    def * = (id, url,newUrl) <>(UrlRecord.tupled, UrlRecord.unapply _)
//
//  }
//
//}



