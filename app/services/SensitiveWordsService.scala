package services

import javax.inject.{Inject, Singleton}
import org.ahocorasick.trie.Trie

import scala.concurrent.ExecutionContext
import scala.io.Source

@Singleton
class SensitiveWordsService @Inject()(implicit exec: ExecutionContext){
  val trie={
    val words:Iterator[String]=Source.fromFile("F:\\project\\U2VideoApiServer\\words\\words.txt").getLines()
    val trieBuilder=Trie.builder
    for(i<-words){
      trieBuilder.addKeyword(i)
    }
    trieBuilder.build
  }

}
