package controllers.tweet

import javax.inject.{Inject, Singleton}
import play.api.mvc.ControllerComponents
import play.api.mvc.BaseController
import play.api.mvc.Request
import play.api.mvc.AnyContent


@Singleton
class TweetController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def list() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tweet.list())
  }

}