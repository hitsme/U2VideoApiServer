package controllers

import com.sksamuel.elastic4s.http.cat.{CatAliasResponse, CatHealthResponse}
import com.sksamuel.elastic4s.http.search.SearchResponse
import javax.inject.Inject
import org.apache.log4j.Logger
import play.api.mvc.{AbstractController, ControllerComponents}
import services.ElasticSearchService

class DashBoardESController @Inject()(elastic: ElasticSearchService, cc: ControllerComponents) extends AbstractController(cc) {

  import com.sksamuel.elastic4s.http.ElasticDsl._

  private val logger = Logger.getLogger(getClass)

  def index = Action {
    val resp: CatHealthResponse = elastic.client.execute {
      catHealth()
    }.await.result
    Ok(resp.toString)
  }

  def addSearchData(query: String) = Action {
    println(query)
    if (query != null && query != "") {
      elastic.client.execute {
        indexInto("searchdata" / "query").fields("query" -> query)
      }.await
    }
    Ok("done!")
  }

  def searchData(num: Int) = Action {
    val resp = try {
      elastic.client.execute {
        search("searchdata").size(0).aggs {
          termsAgg("searchtop", "query").size(50)
        }
      }.await.result
    } catch {
      case e: Throwable =>
        logger.error("elasticsearch execute error", e)
        null
    }
    var json = ""
    if (resp != null) {
      val queryList = resp.aggregations.terms("searchtop").buckets.seq
      for (i <- 0 to queryList.size - 1) {
        if (i == 0)
          json = "{\"" + queryList(i).key + "\":" + queryList(i).docCount + ","
        else if (i == queryList.size - 1)
          json += "\"" + queryList(i).key + "\":" + queryList(i).docCount + "}"
        else
          json += "\"" + queryList(i).key + "\":" + queryList(i).docCount + ","
      }
    }
    Ok(json)
  }
}
