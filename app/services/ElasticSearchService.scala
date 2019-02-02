package services

import com.sksamuel.elastic4s.http.{ElasticClient, ElasticProperties}
import javax.inject.{Inject, Singleton}
import org.apache.log4j.Logger

import scala.concurrent.ExecutionContext

@Singleton
class ElasticSearchService @Inject()(implicit exec: ExecutionContext){
private val logger:Logger=Logger.getLogger(getClass)
val client: ElasticClient=try{
  ElasticClient(ElasticProperties("http://elastic.itsme.club:9201"))
}catch {
  case e:Throwable=>
    logger.error("connect es error",e)
    null
}

}
