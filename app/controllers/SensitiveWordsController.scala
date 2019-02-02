package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import services.SensitiveWordsService
class SensitiveWordsController @Inject()(sensitive:SensitiveWordsService, cc:ControllerComponents) extends AbstractController(cc){

  def index=Action{
    val emits=sensitive.trie.parseText("胡景涛江泽民")
    Ok(emits.toString)
  }
}
